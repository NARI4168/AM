package articlemanager.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import articlemanager.dao.ArticleDao;
import articlemanager.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService(Connection con) {
		this.articleDao = new ArticleDao(con);
	}

	public int getItemsInAPage() {
		return 5;
	}

	public int getForPrintListTotalPage() {
		int itemsInAPage = getItemsInAPage();

		int totalCount = articleDao.getTotalCount();
		int totalPage = (int) Math.ceil((double) totalCount / itemsInAPage);
		return totalPage;

	}

	public List<Article> getForPrintArticles(int page) {
		int itemsInAPage = getItemsInAPage();
		int limitFrom = (page - 1) * itemsInAPage;

		List<Article> articles = articleDao.getArticles(limitFrom, itemsInAPage);

		return articles;
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void delete(int id) {
		articleDao.delete(id);
	}

	public void update(int id, String title, String body) {
		 articleDao.update(id, title, body);		
	}

}
