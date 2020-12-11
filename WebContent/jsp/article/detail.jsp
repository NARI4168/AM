<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="articlemanager.dto.Article"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Article article = (Article) request.getAttribute("article");
Map<String, Object> loginedMemberRow = (Map<String, Object>) request.getAttribute("loginedMemberRow");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article Detail</title>
</head>
<body>
	<div>
		<font size="8" color="SkyBlue"> <b>Article Detail</b>
		</font>
		<p>
	</div>

	<div>
		<font size="7"> <b>제목 : <%=article.title%></b></font><br>
		<font size="4"> <b>작성자 : ~~ </b></font><br>
		<font size="3" color="Gray"> 작성일 : <%=article.regDate%></font>
	</div>

	<div>
		<font size="3" color="Gray"> <a
			href="delete?id=<%=article.id%>"
			style="text-decoration: none; color: Gray">삭제</a> | <a
			href="modify?id=<%=article.id%>"
			style="text-decoration: none; color: Gray">수정</a></font>
	</div>
	<br>

	<div>
		<font size="6"> 내용 : <%=article.body%></font>
	</div>
	<br>
	<div>
		<button type="button" onclick="location.href='list'"
			style="text-decoration: none">돌아가기</button>
	</div>

</body>
</html>