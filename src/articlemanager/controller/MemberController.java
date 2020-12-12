package articlemanager.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import articlemanager.dto.Member;
import articlemanager.service.MemberService;

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
			response.getWriter().append(
					String.format("<script> alert('%s(은)는 이미 사용중인 아이디 입니다.'); history.back(); </script>", loginId));
		} else if (member == null) {
			memberService.join(loginId, loginPw, name);
			response.getWriter().append(
					String.format("<script> alert('회원이 생성되었습니다.');location.replace('../article/home'); </script>"));
		}
	}

	public void actionDoLogin() throws ServletException, IOException {
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");

		Member member = memberService.getMemberByLoginId(loginId);
		
		
		if (member == null) {
			response.getWriter().append(
					String.format("<script> alert('%s은(는) 존재하지 않는 로그인 아이디 입니다.'); history.back(); </script>", loginId));
		}
		
		
		if (((String) member.loginPw).equals(loginPw) == false) {
			response.getWriter()
					.append(String.format("<script> alert('비밀번호가 일치하지 않습니다.'); history.back(); </script>"));
			return;
		}
	
		request.setAttribute("loginedMemberId",member.loginId);
	
		response.getWriter()
				.append(String.format("<script> alert('로그인 성공!'); location.replace('../article/list');</script>"));
	}

	public void actionDoLogout() throws ServletException, IOException {
		
		request.removeAttribute("loginedMemberId");
		String.format("<script> alert('로그아웃 되었습니다.'); location.replace('../article/home'); </script>");
	}

}
