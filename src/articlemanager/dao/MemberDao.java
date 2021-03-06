package articlemanager.dao;

import java.sql.Connection;

import java.util.Map;

import articlemanager.dto.Member;
import articlemanager.util.DBUtil;
import articlemanager.util.SecSql;

public class MemberDao {
	private Connection con;

	public MemberDao(Connection con) {
		this.con = con;
	}

	public Member getMemberByLoginId(String loginId) {

		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM member");
		sql.append("WHERE loginId = ?", loginId);

		Map<String, Object> memberMap = DBUtil.selectRow(con, sql);

		if (memberMap.isEmpty()) {
			return null;
		}
		return new Member(memberMap);
	}
	
	public void join(String loginId, String loginPw, String name) {

		SecSql sql = SecSql.from("INSERT INTO member");
		sql.append("SET loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", name = ?", name);
		sql.append(", regDate = NOW()");

		DBUtil.insert(con, sql);
	}

	public Member getMemberById(int id) {
				
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM member");
		sql.append("WHERE id = ?", id);
		
		Map<String, Object> memberMap = DBUtil.selectRow(con, sql);
	
		if (memberMap.isEmpty()) {
			return null;
		}
		return new Member(memberMap);
	}

	public void profileUpdate(int memberId, String name, String fullPath, String newFileName) {
		SecSql sql = SecSql.from("UPDATE member");
		sql.append("SET name = ?", name);
		if(newFileName != null) {
		sql.append(", imagePath =?", fullPath);
		sql.append(", image =?", newFileName);
		}
		sql.append("WHERE id = ?", memberId);

		
		DBUtil.update(con, sql);		
	}
		
	
}
