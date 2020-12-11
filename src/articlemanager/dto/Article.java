package articlemanager.dto;

import java.util.Map;

public class Article {
	public int id;
	public String title;
	public String body;
	public String regDate;
	public int memberId;



	public Article() {
		
	}
	
	public Article(Map<String, Object> row) {
		this.id = (int) row.get("id");
		this.title = (String) row.get("title");
		this.body = (String) row.get("body");
		this.regDate = (String) row.get("regDate");
		this.memberId = (int) row.get("memberId");
	
	}

}
