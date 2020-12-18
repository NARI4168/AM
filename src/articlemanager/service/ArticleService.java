package articlemanager.service;

import java.sql.Connection;
import java.util.List;

import articlemanager.dao.ArticleDao;
import articlemanager.dto.Article;
import articlemanager.dto.Reply;

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

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	public void delete(int id) {
		articleDao.delete(id);
	}

	public void update(int id, String title, String body) {
		articleDao.update(id, title, body);
	}

	public void insert(String title, String body, int memberId, String writer) {
		articleDao.insert(title, body, memberId, writer);
	}

	public Article prev_article(int id) {
		return articleDao.prev_article(id);
	}

	public Article next_article(int id) {
		return articleDao.next_article(id);
	}

	public int writeReply(String body, int memberId, int articleId, String writer) {
		return articleDao.writeReply(body, memberId, articleId, writer);
	}

	public List<Reply> getReplies(int id) {
		return articleDao.getReplies(id);
	}

}
