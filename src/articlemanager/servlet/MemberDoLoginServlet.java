package articlemanager.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import articlemanager.Config;
import articlemanager.util.DBUtil;
import articlemanager.util.SecSql;

@WebServlet("/member/doLogin")
public class MemberDoLoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

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
			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");

			SecSql sql = SecSql.from("SELECT *");
			sql.append("FROM member");
			sql.append("WHERE loginId =?", loginId);

			Map<String, Object> memberRow = DBUtil.selectRow(con, sql);

			if (memberRow.isEmpty()) {
				response.getWriter().append(
						String.format("<script> alert('%s은 존재하지 않는 아이디입니다.'); history.back(); </script>", loginId));
				return;
			}

			if (((String) memberRow.get("loginPw")).equals(loginPw) == false) {
				response.getWriter()
						.append(String.format("<script> alert('비밀번호가 일치하지 않습니다.'); history.back(); </script>"));
				return;
			}

			HttpSession session = request.getSession();
			session.setAttribute("loginedMemberId", memberRow.get("id"));

			response.getWriter()
					.append(String.format("<script> alert('로그인 성공!'); location.replace('../article/list');</script>"));

		} catch (SQLException e) {
			e.printStackTrace();
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