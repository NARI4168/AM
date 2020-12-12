package articlemanager.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import articlemanager.dao.MemberDao;
import articlemanager.dto.Member;

public class MemberService {
	private MemberDao memberDao;

	public MemberService(Connection con) {
		this.memberDao = new MemberDao(con);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public void join(String loginId, String loginPw, String name) {
		memberDao.join(loginId, loginPw, name);
	}


}
