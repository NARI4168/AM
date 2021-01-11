package articlemanager.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Util {
	public static boolean empty(HttpServletRequest req, String paramName) {
		String paramValue = req.getParameter(paramName);

		return empty(paramValue);
	}

	public static boolean empty(Object obj) {
		if (obj == null) {
			return true;
		}

		if (obj instanceof String) {
			return ((String) obj).trim().length() == 0;
		}

		return true;
	}

	public static boolean isNum(HttpServletRequest req, String paramName) {
		String paramValue = req.getParameter(paramName);

		return isNum(paramValue);
	}

	public static boolean isNum(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj instanceof Long) {
			return true;
		} else if (obj instanceof Integer) {
			return true;
		} else if (obj instanceof String) {
			try {
				Integer.parseInt((String) obj);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}

		return false;
	}

	public static int getInt(HttpServletRequest req, String paramName) {
		return Integer.parseInt(req.getParameter(paramName));
	}

	public static void printEx(String errName, HttpServletResponse resp, Exception e) {
		try {
			resp.getWriter()
					.append("<h1 style='color:red; font-weight:bold; text-align:left;'>[에러 : " + errName + "]</h1>");

			resp.getWriter().append("<pre style='text-align:left; font-weight:bold; font-size:1.3rem;'>");
			e.printStackTrace(resp.getWriter());
			resp.getWriter().append("</pre>");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static String getString(HttpServletRequest req, String paramName) {
		return req.getParameter(paramName);
	}

	// alert Util
	public static void init(HttpServletResponse response) {
		response.setContentType("text/html; charset=euc-kr");
		response.setCharacterEncoding("euc-kr");
	}

	public static void alert(HttpServletResponse response, String alertText) throws IOException {
		init(response);
		PrintWriter out = response.getWriter();
		out.println("<script>alert('" + alertText + "'); </script>  ");
		out.flush();
	}

	public static void alertAndBack(HttpServletResponse response, String alertText) throws IOException {
		init(response);
		PrintWriter out = response.getWriter();
		out.println("<script>alert('" + alertText + "'); history.back(); </script>  ");
		out.flush();
	}

	public static void alertAndMove(HttpServletResponse response, String alertText, String nextPage)
			throws IOException {
		init(response);
		PrintWriter out = response.getWriter();
		out.println("<script>alert('" + alertText + "'); location.href='" + nextPage + "';</script> ");
		out.flush();
	}

	public static void reload(HttpServletResponse response, String nextPage) throws IOException {
		init(response);
		PrintWriter out = response.getWriter();
		out.println("<script>location.href='" + nextPage + "';</script>");
		out.flush();
	}

	/*
	 * //파일카피 public static void copyFileUsingChannel(File source, File dest) throws
	 * IOException { FileChannel sourceChannel = null; FileChannel destChannel =
	 * null; FileInputStream fs = null; FileOutputStream fo = null; try { fs = new
	 * FileInputStream(source); fo = new FileOutputStream(dest); sourceChannel =
	 * fs.getChannel(); destChannel = fo.getChannel();
	 * destChannel.transferFrom(sourceChannel, 0, sourceChannel.size()); }catch
	 * (Exception e) { e.printStackTrace(); }finally{ if(fs != null){fs.close();}
	 * if(fo != null){fo.close();} if(fo != null){sourceChannel.close();}
	 * if(destChannel != null){destChannel.close();} } }
	 * 
	 * 
	 * public static boolean blobToFile(Object item,String file_path) throws
	 * Exception{ try { BLOB blob = (oracle.sql.BLOB)item; //item은 데이터베이스 조회 결과이다.
	 * 여기서는 Object로 표시하였다. byte [] content = blob.getBytes( 1, ( int ) blob.length()
	 * ); FileUtils.writeByteArrayToFile(new File(file_path), content); } catch
	 * (Exception e) { e.printStackTrace(); return false; } return true; }
	 */
}
