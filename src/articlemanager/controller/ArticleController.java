package articlemanager.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import articlemanager.dto.Article;
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

	public void actionlist() throws ServletException, IOException {
		int page = 1;

		if (request.getParameter("page") != null && request.getParameter("page").length() != 0) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		// ArticleService에 호출
		int totalPage = articleService.getForPrintListTotalPage();
		List<Article> articles = articleService.getForPrintArticles(page);

		int memberId = (int) request.getAttribute("loginedMemberId");
		Member member = memberService.getMemberById(memberId);

		request.setAttribute("member", member);
		request.setAttribute("articles", articles);
		request.setAttribute("cPage", page);
		request.setAttribute("totalPage", totalPage);

		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
	}

	public void actionHome() throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/article/home.jsp").forward(request, response);
	}

	public void actionDetail() throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		Article article = articleService.getArticleById(id);
		Article prevArticle = articleService.prev_article(id);
		Article nextArticle = articleService.next_article(id);
		List<Reply> replies = articleService.getReplies(id);

//////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////

		request.setAttribute("article", article);
		request.setAttribute("replies", replies);
		request.setAttribute("prevArticle", prevArticle);
		request.setAttribute("nextArticle", nextArticle);

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

}
