package articlemanager.dto;

import java.util.Map;

public class Member {
	public int id;
	public String loginId;
	public String loginPw;
	public String name;
	public String regDate;
	public String imagePath;
	public String image;
	
	
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPw() {
		return loginPw;
	}

	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	
	public Member() {

	}

	public Member(Map<String, Object> row) {
		this.id = (int) row.get("id");
		this.loginId = (String) row.get("loginId");
		this.loginPw = (String) row.get("loginPw");
		this.name = (String) row.get("name");	
		this.regDate = (String) row.get("regDate");	
		this.image = (String) row.get("image");
		this.imagePath = (String) row.get("imagePath");
	}

	public boolean isAdmin() {
		return loginId.equals("admin");
	}

}
