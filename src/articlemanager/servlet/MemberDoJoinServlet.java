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

import articlemanager.Config;
import articlemanager.util.DBUtil;
import articlemanager.util.SecSql;

@WebServlet("/member/doJoin")
public class MemberDoJoinServlet extends HttpServlet {

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
			String name = request.getParameter("name");

			SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
			sql.append("FROM member");
			sql.append("WHERE loginId =?", loginId);
			boolean usableLoginId = DBUtil.selectRowIntValue(con, sql) == 0;

			if (usableLoginId == false) {
				response.getWriter().append(
						String.format("<script> alert('%s은 이미 사용중인 아이디입니다.'); history.back(); </script>", loginId));
				return;
			}

			sql = SecSql.from("INSERT INTO member");
			sql.append("SET loginId = ?", loginId);
			sql.append(", loginPw = ?", loginPw);
			sql.append(", name = ?", name);

			int id = DBUtil.insert(con, sql);

			response.getWriter().append(String
					.format("<script> alert('%d번 회원이 생성되었습니다.');location.replace('../article/home'); </script>", id));

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