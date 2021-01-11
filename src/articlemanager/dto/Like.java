package articlemanager.dto;

import java.util.Map;

public class Like {
	public int id;
	public int articleId;
	public int memberId;
	public int likecheck;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getLikecheck() {
		return likecheck;
	}

	public void setLikecheck(int likecheck) {
		this.likecheck = likecheck;
	}
	
	public Like(Map<String, Object> row) {
		this.id = (int) row.get("id");
		this.memberId = (int) row.get("memberId");
		this.articleId = (int) row.get("articleId");
		this.likecheck = (int) row.get("likecheck");
	}
}
