package articlemanager.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import articlemanager.dto.Member;
import articlemanager.service.MemberService;
import articlemanager.util.Util;

public class MemberController {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection con;
	private MemberService memberService;
	private FileInputStream fin;
	private FileOutputStream fout;
	
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
		// String referer = (String) request.getHeader("Referer");
		// System.out.println(referer);
		// request.getSession().setAttribute("redirectURI", referer);
		// System.out.println("redirectURI");

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

		String redirectURI = (String) session.getAttribute("redirectURI");
		// ***수정***
		if (redirectURI == null) {

			Util.alertAndMove(response, "로그인 성공!", "../article/list");
		} else {
			String[] Query = redirectURI.split("/s/");
			// System.out.println(Query[1]);

			Util.alertAndMove(response, "로그인 성공!", "../" + Query[1]);
		}
	}

	public void actionDoLogout() throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("loginedMemberId");
		Util.alertAndMove(response, "로그아웃 되었습니다.", "../article/list");
	}

	public void actionModify_profile() throws ServletException, IOException {
		HttpSession session = request.getSession();
		int loginedId = (int) session.getAttribute("loginedMemberId");
		Member member = memberService.getMemberById(loginedId);

		
		//System.out.println(member.imagePath);
		String savePath = request.getSession().getServletContext().getRealPath("upload");
		String img ="bagic.png";
		String bagicImage = savePath + File.separator + img;	
	//	System.out.println(bagicImage);
		request.setAttribute("member", member);
		request.setAttribute("bagicImage", bagicImage);

		request.getRequestDispatcher("/jsp/member/profile.jsp").forward(request, response);
	}

	public void actionDoModify_profile() throws ServletException, IOException {
		
		// 파일 저장 경로
		String root = request.getSession().getServletContext().getRealPath("/");
		String savePath = root + "upload"; 
		int maxSize = 1024 * 1024 * 100;
	
		
		//int read = 0;
	//	byte[] buf = new byte[1024];
		//FileInputStream fin = null;
	//	FileOutputStream fout = null;
		
		// System.out.println(savePath);

		MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());

		String member_Id = multi.getParameter("id");
		int memberId = Integer.parseInt(member_Id);
		String name = multi.getParameter("name");
	
	
		//중복된 파일이름이 있을 수 있으므로 newFileName이 실제로 서버에 저장된 파일이름이다.
		// 업로드 파일명(실제 사용자가 저장한 이름)
		//String uploadFile = multi.getOriginalFileName("imgFile");
		String newFileName = multi.getFilesystemName("imgFile");  
		
	
	     String fullPath = savePath + File.separator + newFileName;	
	
	     /*
		
		// 업로드된 파일 객체 생성
		 File oldFile = new File(savePath + uploadFile);

		// 실제 저장될 파일 객체 생성
		 File newFile = new File(savePath + newFileName);
		 
		  if(!oldFile.renameTo(newFile)){
			  
	            // rename이 되지 않을경우 강제로 파일을 복사하고 기존파일은 삭제
	 
	            buf = new byte[1024];
	            fin = new FileInputStream(oldFile);
	            fout = new FileOutputStream(newFile);
	            read = 0;
	            while((read=fin.read(buf,0,buf.length))!=-1){
	                fout.write(buf, 0, read);
	            }
	             
	            fin.close();
	            fout.close();
	            oldFile.delete();
	        }  
	*/
		 
		memberService.profileUpdate(memberId, name, fullPath , newFileName);
		//request.setAttribute("newFile", newFile);
		Util.alertAndMove(response, "변경", "../article/list");
	}
	
	public void actionDoImageLoad() throws ServletException, IOException {
		
		System.out.println("1111111");
		
		int memberId = (int) request.getAttribute("loginedMemberId");
		Member member = memberService.getMemberById(memberId);


		System.out.println("222222");
		
	//	String imgPath = member.imagePath;	
		
		//System.out.println(imgPath);
		
		File f = new File(member.imagePath);
		
		System.out.println("4444");
		if (!f.exists()) {
		System.out.println("없음");
		}
		System.out.println("55555");
		
		fin = new FileInputStream(member.imagePath);
		int read = 0;
		byte[] buf = new byte[1024];
		
		
		System.out.println("66666");
		
		while((read=fin.read(buf,0,buf.length))!=-1){
            fout.write(buf, 0, read);
        }
		System.out.println("777");
	}


}
