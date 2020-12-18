package articlemanager.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import articlemanager.dto.Member;
import articlemanager.service.MemberService;
import articlemanager.util.Util;

public class MemberController {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection con;
	private MemberService memberService;

	public MemberController(HttpServletRequest request, HttpServletResponse response, Connection con) {

		this.request = request;
		this.response = response;
		this.con = con;

		memberService = new MemberService(con);
	}

	public void actionJoin() throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/member/join.jsp").forward(request, response);
	}

	public void actionDoJoin() throws ServletException, IOException {
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		String name = request.getParameter("name");

		Member member = memberService.getMemberByLoginId(loginId);

		if (member != null) {
			Util.alertAndBack(response, (loginId + "(은)는 이미 사용중인 아이디 입니다."));
		} else if (member == null) {
			memberService.join(loginId, loginPw, name);
			Util.alertAndMove(response, "가입이 완료되었습니다.", "../article/home");
		}
	}

	public void actionDoLogin() throws ServletException, IOException {
		String loginId = request.getParameter("loginId");
		if (loginId.length() == 0) {
			Util.alertAndBack(response, "아이디를 입력해주세요.");
			return;
		}

		String loginPw = request.getParameter("loginPw");
		if (loginPw.length() == 0) {
			Util.alertAndBack(response, "비밀번호를 입력해주세요.");
			return;
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			Util.alertAndBack(response, (loginId + "은(는) 존재하지 않는 아이디 입니다."));
			return;
		}

		if (((String) member.loginPw).equals(loginPw) == false) {
			Util.alertAndBack(response, "비밀번호가 일치하지 않습니다.");
			return;
		}

		HttpSession session = request.getSession();
		session.setAttribute("loginedMemberId", member.id);
		Util.alertAndMove(response, "로그인 성공!", "../article/list");
	}

	public void actionDoLogout() throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("loginedMemberId");
		Util.alertAndMove(response, "로그아웃 되었습니다.", "../article/home");
	}

}
