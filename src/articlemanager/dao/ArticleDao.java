package articlemanager.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import articlemanager.dto.Article;
import articlemanager.dto.Reply;
import articlemanager.util.DBUtil;
import articlemanager.util.SecSql;

public class ArticleDao {
	private Connection con;

	public ArticleDao(Connection con) {
		this.con = con;
	}

	public int getTotalCount() {
		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
		sql.append("FROM article");
		int totalCount = DBUtil.selectRowIntValue(con, sql);

		return totalCount;
	}

	public List<Article> getArticles(int limitFrom, int limitCount) {

		SecSql sql = SecSql.from("SELECT a.*, m.loginId");
		sql.append("FROM article AS a");
		sql.append("JOIN member AS m");
		sql.append("ON m.id = a.memberId");
		sql.append("ORDER BY id DESC");
		sql.append("LIMIT ?, ?", limitFrom, limitCount);

		List<Map<String, Object>> articleRows = DBUtil.selectRows(con, sql);

		List<Article> articles = new ArrayList<>();

		for (Map<String, Object> articleRow : articleRows) {
			articles.add(new Article(articleRow));
		}

		return articles;
	}

	public List<Article> getArticles() {
		List<Article> articles = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("ORDER BY id DESC");

		List<Map<String, Object>> articleMapList = DBUtil.selectRows(con, sql);

		for (Map<String, Object> articleMap : articleMapList) {
			articles.add(new Article(articleMap));
		}

		return articles;
	}

	public Article getArticleById(int id) {

		SecSql sql = SecSql.from("SELECT a.*, m.loginId ");
		sql.append("FROM article AS a");
		sql.append("JOIN member AS m");
		sql.append("ON m.id = a.memberId");
		sql.append("WHERE a.id = ?", id);

		Map<String, Object> articleMap = DBUtil.selectRow(con, sql);

		return new Article(articleMap);
	}

	public void delete(int id) {

		SecSql sql = SecSql.from("DELETE");
		sql.append("FROM article");
		sql.append("WHERE id = ?", id);

		DBUtil.delete(con, sql);
	}

	public void update(int id, String title, String body) {

		SecSql sql = SecSql.from("UPDATE article");
		sql.append("SET title = ?", title);
		sql.append(", body = ?", body);
		sql.append(", regDate = NOW()");
		sql.append("WHERE id = ?", id);

		DBUtil.update(con, sql);
	}

	public void insert(String title, String body, int memberId, String writer) {

		SecSql sql = SecSql.from("INSERT INTO article");
		sql.append("SET title = ?", title);
		sql.append(", body = ?", body);
		sql.append(", regDate = NOW()");
		sql.append(", memberId =?", memberId);
		sql.append(", writer =?", writer);

		DBUtil.insert(con, sql);
	}

	public Article prev_article(int id) {
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE id<?", id);
		sql.append("ORDER BY id DESC");
		sql.append("LIMIT 1");

		Map<String, Object> articleMap = DBUtil.selectRow(con, sql);

		return new Article(articleMap);
	}

	public Article next_article(int id) {
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE id>?", id);
		sql.append("ORDER BY id");
		sql.append("LIMIT 1");

		Map<String, Object> articleMap = DBUtil.selectRow(con, sql);

		return new Article(articleMap);
	}

	public int writeReply(String body, int memberId, int articleId, String writer) {

		SecSql sql = SecSql.from("INSERT INTO articleReply");
		sql.append("SET body = ?", body);
		sql.append(", regDate = NOW()");
		sql.append(", memberId =?", memberId);
		sql.append(", articleId =?", articleId);
		sql.append(", writer =?", writer);

		return DBUtil.insert(con, sql);
	}

	public List<Reply> getReplies(int id) {

		SecSql sql = SecSql.from("SELECT r.*, m.loginId");
		sql.append("FROM articleReply AS r");
		sql.append("JOIN member AS m");
		sql.append("ON m.id = r.memberId");
		sql.append("WHERE articleId = ?", id);
		sql.append("ORDER BY id DESC");
		
		List<Map<String, Object>> replyMapList = DBUtil.selectRows(con, sql);
		List<Reply> Replies = new ArrayList<>();

		for (Map<String, Object> replyMap : replyMapList) {
			Replies.add(new Reply(replyMap));
		}

		return Replies;
	}

}
