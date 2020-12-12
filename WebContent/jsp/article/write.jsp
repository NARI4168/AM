<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%	boolean isLogined = (boolean) request.getAttribute("isLogined");
int loginedMemberId = (int) request.getAttribute("loginedMemberId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article Write</title>
</head>
<body>
	<div>
		<font size="8" color="Indigo"> <b>Article Write</b>
		</font>
		<p>
	</div>

	<form action="doWrite" method="POST">
		<div>
			제목<br> <input autocomplete="off" placeholder="제목을 입력해주세요."
				name="title" type="text" />
		</div>
		<p>
		<div>
			내용<br>
			<textarea autocomplete="off" placeholder="내용을 입력하세요." name="body"></textarea>
		</div>
		<p>
		<div>
			<button type="submit" onclick="location.href='list'"
				style="text-decoration: none">등록</button>
			<button type="button" onclick="location.href='list'"
				style="text-decoration: none">돌아가기</button>
		</div>
	</form>
<!--  
	<%
		if (!isLogined) {
	%>
	<script>
		alert('로그인 후 이용가능합니다.');
		location.replace('../article/home');
	</script>
	<%
		}
	%>
-->
</body>
</html>