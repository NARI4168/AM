package articlemanager.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import articlemanager.dto.Article;
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
		System.out.println("11");
		SecSql sql = SecSql.from("UPDATE article");
		sql.append("SET title = ?", title);
		sql.append(", body = ?", body);
		sql.append(", regDate = NOW()");
		sql.append("WHERE id = ?", id);
		System.out.println("22");
		DBUtil.update(con, sql);
		System.out.println("33");
	}

}
