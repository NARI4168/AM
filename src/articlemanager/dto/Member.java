package articlemanager.dto;

import java.util.Map;

public class Member {
	public int id;
	public String loginId;
	public String loginPw;
	public String name;

	public Member() {

	}

	public Member(Map<String, Object> row) {
		this.id = (int) row.get("id");
		this.loginId = (String) row.get("loginId");
		this.loginPw = (String) row.get("loginPw");
		this.name = (String) row.get("name");		
	}


}
