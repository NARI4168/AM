package articlemanager.util;

import articlemanager.dto.Member;

public class Session {
	public int loginedMemberId;
	public Member loginedMember;
	// public int likecheck;

	public Session() {
		loginedMemberId = -1;
		// likecheck = 0;
	}

	public void login(Member member) {
		loginedMemberId = member.id;
		loginedMember = member;

	}

	public void logout() {
		loginedMemberId = -1;
		loginedMember = null;
		// likecheck = 0;

	}

	public boolean isLogined() {
		return loginedMemberId != -1;
	}
}
