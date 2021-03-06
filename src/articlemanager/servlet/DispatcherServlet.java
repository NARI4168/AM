package articlemanager.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import articlemanager.Config;
import articlemanager.controller.ArticleController;
import articlemanager.controller.MemberController;
import articlemanager.dto.Member;
import articlemanager.exception.SQLErrorException;
import articlemanager.service.MemberService;

@WebServlet("/s/*")
public class DispatcherServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String requestUri = request.getRequestURI();
		String[] requestUriBits = requestUri.split("/");

		if (requestUriBits.length < 5) {
			response.getWriter().append("올바른 요청이 아닙니다.");
			return;
		}

		// 커넥터 드라이버 활성화
		String driverName = Config.getDbDriverClassName();

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.err.printf("[ClassNotFoundException 예외, %s]\n", e.getMessage());
			response.getWriter().append("DB 드라이버 클래스 로딩 실패");
			return;
		}

		// DB 연결
		Connection con = null;

		try {
			con = DriverManager.getConnection(Config.getDBUrl(), Config.getDBId(), Config.getDBPw());

			// 모든 요청을 들어가기 전에 무조건 해야 하는 일 시작
			HttpSession session = request.getSession();

			boolean isLogined = false;
			int loginedMemberId = -1;
			Member loginedMember = null;

			MemberService memberService = new MemberService(con);

			if (session.getAttribute("loginedMemberId") != null) {
				loginedMemberId = (int) session.getAttribute("loginedMemberId");
				isLogined = true;
				loginedMember = memberService.getMemberById(loginedMemberId);
			}

			request.setAttribute("isLogined", isLogined);
			request.setAttribute("loginedMemberId", loginedMemberId);
			request.setAttribute("loginedMember", loginedMember);

			String controllerName = requestUriBits[3];
			String actionMethodName = requestUriBits[4];

			if (controllerName.equals("article")) {
				ArticleController controller = new ArticleController(request, response, con);

				if (actionMethodName.equals("list")) {
					controller.actionlist();
				} else if (actionMethodName.equals("home")) {
					controller.actionHome();
				} else if (actionMethodName.equals("detail")) {
					controller.actionDetail();
				} else if (actionMethodName.equals("doDelete")) {
					controller.actionDoDelete();
				} else if (actionMethodName.equals("modify")) {
					controller.actionModify();
				} else if (actionMethodName.equals("doModify")) {
					controller.actionDoModify();
				} else if (actionMethodName.equals("write")) {
					controller.actionWrite();
				} else if (actionMethodName.equals("doWrite")) {
					controller.actionDoWrite();
				} else if (actionMethodName.equals("doWriteReply")) {
					controller.actionDoWriteReply();
				} else if (actionMethodName.equals("doDeleteReply")) {
					controller.actionDoDeleteReply();
				} else if (actionMethodName.equals("modifyAndDeleteOfReply")) {
					controller.actionModifyAndDeleteOfReply();
				} else if (actionMethodName.equals("doModifyReply")) {
					controller.actionDoModifyReply();
				} else if (actionMethodName.equals("doLike")) {
					controller.actionDoLike();
				} else if (actionMethodName.equals("doSearch")) {
					controller.actionDoSearch();
				}

			}
			if (controllerName.equals("member")) {
				MemberController controller = new MemberController(request, response, con);

				if (actionMethodName.equals("join")) {
					controller.actionJoin();
				} else if (actionMethodName.equals("doJoin")) {
					controller.actionDoJoin();
				} else if (actionMethodName.equals("doLogin")) {
					controller.actionDoLogin();
				} else if (actionMethodName.equals("doLogout")) {
					controller.actionDoLogout();
				} else if (actionMethodName.equals("modify_profile")) {
					controller.actionModify_profile();
				} else if (actionMethodName.equals("doModify_profile")) {
					controller.actionDoModify_profile();
				}else if (actionMethodName.equals("doImageLoad")) {
					controller.actionDoImageLoad();
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SQLErrorException e) {
			e.getOrigin().printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}