package articlemanager.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import articlemanager.dto.Article;
import articlemanager.service.ArticleService;

public class ArticleController {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection con;
	private ArticleService articleService;

	public ArticleController(HttpServletRequest request, HttpServletResponse response, Connection con) {

		this.request = request;
		this.response = response;
		this.con = con;

		articleService = new ArticleService(con);

	}

	public void actionList() throws ServletException, IOException {
		int page = 1;

		if (request.getParameter("page") != null && request.getParameter("page").length() != 0) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		// ArticleService에 호출
		int totalPage = articleService.getForPrintListTotalPage();
		List<Article> articles = articleService.getForPrintArticles(page);

		request.setAttribute("articles", articles);
		request.setAttribute("page", page);
		request.setAttribute("totalPage", totalPage);

		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
	}

	public void actionHome() throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/article/home.jsp").forward(request, response);
	}

	public void actionDetail() throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Article article = articleService.getArticleById(id);

		request.setAttribute("article", article);

		request.getRequestDispatcher("/jsp/article/detail.jsp").forward(request, response);
	}

	public void actionDelete() throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Article article = articleService.getArticleById(id);

		articleService.delete(id);

		response.getWriter()
				.append(String.format("<script> alert('%d번 글이 삭제되었습니다.'); location.replace('list'); </script>", id));
	}

	public void actionModify() throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Article article = articleService.getArticleById(id);

		request.setAttribute("article", article);
		
		request.getRequestDispatcher("/jsp/article/modify.jsp").forward(request, response);

	}

	public void actionDoModify() throws ServletException, IOException {
		actionModify();
		
	
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		
		articleService.update(id, title, body);
		
		response.getWriter().append(String
				.format("<script> alert('%d번 글이 수정되었습니다.'); location.replace('detail?id=%d'); </script>", id, id));
	}

}
