<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="articlemanager.dto.Article"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Article article = (Article) request.getAttribute("article");
 boolean isLogined = (boolean) request.getAttribute("isLogined");
int loginedMemberId = (int) request.getAttribute("loginedMemberId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article Modify</title>
</head>
<body>
	<div>
		<font size="8" color="Darkcyan"> <b>Article Modify</b>
		</font>
		<p>
	</div>

	<form action="doModify" method="POST">
		<input type="hidden" name="id" value="<%=article.id%>" />
		<div>
			제목<br> <input autocomplete="off" placeholder="제목을 입력해주세요."
				name="title" type="text" value="<%=article.title%>" />
		</div>
		<p>
		<div>
			내용<br>
			<textarea autocomplete="off" placeholder="내용을 입력하세요." name="body"><%=article.body%></textarea>
		</div>
		<p>
		<div>
			<button type="submit" 
				style="text-decoration: none">수정</button>
			<button type="button" onclick="location.href='detail?id=<%=article.id%>'"
				style="text-decoration: none">돌아가기</button>
			<button type="button" onclick="location.href='list'"
				style="text-decoration: none">리스트</button>
		</div>
	</form>
	<!--  
	<%
		if (!isLogined) {
	%>
	<script> alert('로그인 후 이용가능합니다.');location.replace('../article/home');</script>
	<%
		}
	%>
-->
</body>
</html>