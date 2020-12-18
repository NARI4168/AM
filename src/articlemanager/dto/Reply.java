package articlemanager.dto;

import java.util.Map;

public class Reply {
	public int id;
	public String body;
	public String regDate;
	public int memberId;
	public int articleId;
	public String writer;

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public Reply(Map<String, Object> row) {
		this.id = (int) row.get("id");
		this.body = (String) row.get("body");
		this.regDate = (String) row.get("regDate");
		this.memberId = (int) row.get("memberId");
		this.articleId = (int) row.get("articleId");
		this.writer = (String) row.get("writer");
	}

}
