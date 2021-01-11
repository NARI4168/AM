package articlemanager.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import articlemanager.dao.ArticleDao;
import articlemanager.dto.Article;
import articlemanager.dto.Like;
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
      //  String keyword;
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

	public Reply getReplyById(int id) {
		return articleDao.getReplyById(id);
	}

	public void deleteReply(int id) {
		articleDao.deleteReply(id);
	}

	public void updateReply(int id, String body) {
		articleDao.updateReply(id, body);
	}

	public int countOfReplyInArticle(int id) {
		int countOfReplyInArticle = articleDao.countReply(id);
		return countOfReplyInArticle;
	}

	public Like likecheck(int memberId, int articleId) {
		return articleDao.likecheck(memberId, articleId);
	}

	public void insertLike(int loginedMemberId, int articleId) {
		articleDao.insertLike(loginedMemberId, articleId);
	}

	public void updateLike(int loginedMemberId, int articleId, int likecheck) {
		articleDao.updateLike(loginedMemberId, articleId, likecheck);
	}

	public int countOfLike(int id) {
		int countOfLike = articleDao.countLike(id);
		return countOfLike;
	}

	public void increaseHit(int id) {
		articleDao.increaseHit(id);
	}

	public List<Article> findKeyword(String keyword) {
		return articleDao.findKeyword(keyword);
	}

}
