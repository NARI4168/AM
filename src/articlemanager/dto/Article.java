package articlemanager.dto;

import java.util.Map;

public class Article {
	public int id;
	public String title;
	public String body;
	public String regDate;
	public int memberId;
	public String writer;
	public int hit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Article() {

	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public Article(Map<String, Object> row) {
		this.id = (int) row.get("id");
		this.title = (String) row.get("title");
		this.body = (String) row.get("body");
		this.regDate = (String) row.get("regDate");
		this.memberId = (int) row.get("memberId");
		this.writer = (String) row.get("writer");
		this.hit = (int) row.get("hit");
	}

	
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", body=" + body + ", regDate=" + regDate + ", memberId="
				+ memberId + ", writer=" + writer + ", hit=" + hit + "]";
	}


}
