package articlemanager.controller;



import java.io.IOException;
import java.sql.Connection;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import articlemanager.dto.Article;
import articlemanager.dto.Like;
import articlemanager.dto.Member;
import articlemanager.dto.Reply;
import articlemanager.service.ArticleService;
import articlemanager.service.MemberService;
import articlemanager.util.Util;

public class ArticleController {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection con;
	private ArticleService articleService;
	private MemberService memberService;

	public ArticleController(HttpServletRequest request, HttpServletResponse response, Connection con) {

		this.request = request;
		this.response = response;
		this.con = con;

		articleService = new ArticleService(con);
		memberService = new MemberService(con);
	}

	public void beforeLoginAction() {
		String referer = (String) request.getHeader("Referer");

		request.getSession().setAttribute("redirectURI", referer);

	}

	public void actionlist() throws ServletException, IOException {
		int page = 1;
		// String keyword = "";

		if (request.getParameter("page") != null && request.getParameter("page").length() != 0) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		// ArticleService에 호출
		List<Article> articles = articleService.getForPrintArticles(page);
		int totalPage = articleService.getForPrintListTotalPage();

		int memberId = (int) request.getAttribute("loginedMemberId");
		Member member = memberService.getMemberById(memberId);
	
		

		request.setAttribute("member", member);
		request.setAttribute("articles", articles);
		request.setAttribute("cPage", page);
		request.setAttribute("totalPage", totalPage);

		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
	}

	public void actionHome() throws ServletException, IOException {

		beforeLoginAction();
		request.getRequestDispatcher("/jsp/article/home.jsp").forward(request, response);
		// beforeAction();
	}

	public void actionDetail() throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		Article article = articleService.getArticleById(id);
		Article prevArticle = articleService.prev_article(id);
		Article nextArticle = articleService.next_article(id);
		List<Reply> replies = articleService.getReplies(id);
		int countOfReplyInArticle = articleService.countOfReplyInArticle(article.id);

		// 좋아요 처리
		boolean isLogined = (boolean) request.getAttribute("isLogined");
		if (isLogined) {
			int loginedMemberId = (int) request.getAttribute("loginedMemberId");
			Like likecheckMap = articleService.likecheck(loginedMemberId, article.id);

			if (likecheckMap == null) {
				// 좋아요를 누른적이 없으면 like테이블에 데이터가 없으므로 0
				request.setAttribute("likecheck", 0);
			} else if (likecheckMap.likecheck == 0) {
				request.setAttribute("likecheck", 0);
			} else {
				request.setAttribute("likecheck", 1);
			}
		}

		int countOfLike = articleService.countOfLike(article.id);

		// 새로고침 조회수 증가 방지
		Cookie cookieValue = null;
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("|" + id + "|")) {
					cookieValue = cookies[i];
				}
			}
		}
		if (cookieValue == null) {
			Cookie newCookie = new Cookie("|" + id + "|", "hit");
			response.addCookie(newCookie);

			articleService.increaseHit(id);
		}

		request.setAttribute("article", article);
		request.setAttribute("replies", replies);
		request.setAttribute("prevArticle", prevArticle);
		request.setAttribute("nextArticle", nextArticle);
		request.setAttribute("countOfReplyInArticle", countOfReplyInArticle);
		request.setAttribute("countOfLike", countOfLike);

		request.getRequestDispatcher("/jsp/article/detail.jsp").forward(request, response);
	}

	public void actionDoDelete() throws ServletException, IOException {

		boolean isLogined = (boolean) request.getAttribute("isLogined");
		if (!isLogined) {
			Util.alertAndMove(response, "로그인 후 이용할 수 있습니다.", "home");
		} else {
			HttpSession session = request.getSession();
			int loginedId = (int) session.getAttribute("loginedMemberId");

			int id = Integer.parseInt(request.getParameter("id"));
			Article article = articleService.getArticleById(id);

			Member member = memberService.getMemberById(loginedId);

			if ((article.memberId == loginedId) || member.isAdmin()) {
				articleService.delete(id);
				Util.alertAndMove(response, id + "번 글이 삭제되었습니다.", "list");
			} else if (article.memberId != loginedId) {
				Util.alertAndBack(response, "권한이 없습니다.");
				return;
			}
		}
	}

	public void actionModify() throws ServletException, IOException {
		boolean isLogined = (boolean) request.getAttribute("isLogined");
		if (!isLogined) {
			Util.alertAndMove(response, "로그인 후 이용할 수 있습니다.", "home");
		} else {
			int id = Integer.parseInt(request.getParameter("id"));

			Article article = articleService.getArticleById(id);

			HttpSession session = request.getSession();
			int loginedId = (int) session.getAttribute("loginedMemberId");
			Member member = memberService.getMemberById(loginedId);

			if ((article.memberId == loginedId) || member.isAdmin()) {

				request.setAttribute("article", article);

				request.getRequestDispatcher("/jsp/article/modify.jsp").forward(request, response);
			} else if (article.memberId != loginedId) {
				Util.alertAndBack(response, "권한이 없습니다.");
				return;
			}
		}
	}

	public void actionDoModify() throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String body = request.getParameter("body");

		articleService.update(id, title, body);
		Util.alertAndMove(response, id + "번 글이 수정되었습니다.", "detail?id=" + id);
	}

	public void actionWrite() throws ServletException, IOException {
		boolean isLogined = (boolean) request.getAttribute("isLogined");
		if (!isLogined) {
			Util.alertAndMove(response, "로그인 후 이용할 수 있습니다.", "home");
		} else {
			request.getRequestDispatcher("/jsp/article/write.jsp").forward(request, response);
		}
	}

	public void actionDoWrite() throws ServletException, IOException {
		HttpSession session = request.getSession();
		int memberId = (int) session.getAttribute("loginedMemberId");

		Member member = memberService.getMemberById(memberId);

		String writer = member.loginId;
		String title = request.getParameter("title");
		String body = request.getParameter("body");

		articleService.insert(title, body, memberId, writer);

		Util.alertAndMove(response, "글이 등록되었습니다.", "list");
	}

	public void actionDoWriteReply() throws ServletException, IOException {

		boolean isLogined = (boolean) request.getAttribute("isLogined");
		if (!isLogined) {
			Util.alertAndMove(response, "로그인 후 이용할 수 있습니다.", "home");
		} else {

			// String getQuery = request.getQueryString();
			// String[] Query = getQuery.split("=");
			// int articleId = Integer.parseInt(Query[1]);

			int articleId = Integer.parseInt(request.getParameter("id"));
			int memberId = (int) request.getAttribute("loginedMemberId");
			String body = request.getParameter("body");
			Member member = memberService.getMemberById(memberId);
			String writer = member.loginId;

			articleService.writeReply(body, memberId, articleId, writer);

			Util.alertAndMove(response, "댓글이 등록되었습니다.", "detail?id=" + articleId);
		}
	}

	public void actionModifyAndDeleteOfReply() throws ServletException, IOException {
		boolean isLogined = (boolean) request.getAttribute("isLogined");
		if (!isLogined) {
			Util.alertAndMove(response, "로그인 후 이용할 수 있습니다.", "home");
		} else {
			int id = Integer.parseInt(request.getParameter("id"));

			Reply reply = articleService.getReplyById(id);

			HttpSession session = request.getSession();
			int loginedId = (int) session.getAttribute("loginedMemberId");
			Member member = memberService.getMemberById(loginedId);

			if ((reply.memberId == loginedId) || member.isAdmin()) {

				request.setAttribute("reply", reply);

				request.getRequestDispatcher("/jsp/article/replyMoDel.jsp").forward(request, response);
			} else if (reply.memberId != loginedId) {
				Util.alertAndBack(response, "권한이 없습니다.");
				return;
			}
		}

	}

	public void actionDoDeleteReply() throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		Reply reply = articleService.getReplyById(id);
		articleService.deleteReply(id);

		Util.alertAndMove(response, "댓글이 삭제되었습니다.", "detail?id=" + reply.articleId);
	}

	public void actionDoModifyReply() throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Reply reply = articleService.getReplyById(id);
		String body = request.getParameter("body");

		articleService.updateReply(id, body);
		Util.alertAndMove(response, "댓글이 수정되었습니다.", "detail?id=" + reply.articleId);

	}

	public void actionDoLike() throws ServletException, IOException {

		boolean isLogined = (boolean) request.getAttribute("isLogined");
		if (isLogined) {

			int articleId = Integer.parseInt(request.getParameter("id"));
			int loginedMemberId = (int) request.getAttribute("loginedMemberId");
			Like likecheckMap = articleService.likecheck(loginedMemberId, articleId);

			if (likecheckMap == null) {
				// 첫 좋아요 누르면 DB에 데이터 생성과 동시에 꽉찬하트 즉, likecheck=1
				articleService.insertLike(loginedMemberId, articleId);
				System.out.println("33");
			} else if (likecheckMap.likecheck == 1) {
				// 누르면 좋아요 취소되야함 즉, likecheck=0 으로 바뀐다
				int likecheck = 0;
				articleService.updateLike(loginedMemberId, articleId, likecheck);
			} else if (likecheckMap.likecheck == 0) {
				int likecheck = 1;
				articleService.updateLike(loginedMemberId, articleId, likecheck);
			}

			Util.reload(response, "detail?id=" + articleId);
		}

	}

	public void actionDoSearch() throws ServletException, IOException {
		String keyword = request.getParameter("keyword");

		// 키워드입력 존재확인
		if (keyword == null || keyword == "") {
			Util.alertAndBack(response, "검색 키워드가 없습니다.");
			return;
		}

		// 검색하기
		List<Article> articles = articleService.findKeyword(keyword);
		if (articles.size() == 0) {
			Util.alertAndBack(response, "검색 키워드가 없습니다.");
			return;
		}

		int keywordTotalCount = articles.size();

		int memberId = (int) request.getAttribute("loginedMemberId");
		Member member = memberService.getMemberById(memberId);

		request.setAttribute("articles", articles);
		request.setAttribute("member", member);

		// Util.alertAndMove(response, keywordTotalCount + "개의 결과물이 있습니다.", "list");
		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);

	}


	
}
