<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="articlemanager.dto.Article"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%	List<Article> articles = (List<Article>) request.getAttribute("articles");
int cPage = (int) request.getAttribute("page");
int totalPage = (int) request.getAttribute("totalPage");
boolean isLogined = (boolean) request.getAttribute("isLogined");
int loginedMemberId = (int) request.getAttribute("loginedMemberId");
Map<String, Object> loginedMemberRow = (Map<String, Object>) request.getAttribute("loginedMemberRow");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article List</title>

<style>
p {
	font-family: "Arial Black", sans-serif;
	font-size: 45px;
	font-weight: bold;
	color: LightCoral;
}
</style>

</head>
<body>

	<div>
		<font size="8" color="LightCoral"> <b>Article List</b></font>
	</div>
	<br>
	<%
		if (isLogined) {
	%>
	<div>
		<font size="5" color="DimGray"><b><%=loginedMemberRow.get("loginId")%>님
				환영합니다! </b></font> <a href="../member/doLogout"
			style="color: Black; font-size: 4;"><b>logout</b></a>
	</div>
	<%
		}
	%>
	<%
		if (!isLogined) {
	%>
	<div>
		<font size="5" color="Red"><b>로그인 후 이용하실 수 있습니다.</b> <a
			href="../article/home" style="color: Black;"><b> login</b></a></font>
	</div>
	<%
		}
	%>
	<br>
	<div>
		<font size="3"> <a href="write"
			style="text-decoration: none; color: Gray"><b>게시물 작성</b></a></font>
	</div>

	<ul>
		<%
			for (Article article : articles) {
		%>
		<li><a href="detail?id=<%=article.id%>"><b><%=article.id%>.
					제목 : <%=article.title%></b></a><br><!--작성자 : %=article.loginId%><br>-->
			작성일 : <%=article.regDate%><p></li>
		<%
			}
		%>
	</ul>

	<style type="text/css">
.page>a.red {
	color: red;
}
</style>
	<div class="page">
		<%
			if (cPage > 1) {
		%>
		<a href="list?page=1" style="text-decoration: none">≪처음 </a>
		<%
			}
		%>
		<%
			int pageSize = 5;
		int from = cPage - pageSize;
		if (from < 1) {
			from = 1;
		}

		int end = cPage + pageSize;
		if (end > totalPage) {
			end = totalPage;
		}

		for (int i = from; i <= end; i++) {
		%>
		<a class="<%=cPage == i ? "red" : ""%>" href="list?page=<%=i%>"><%=i%></a>
		<%
			}
		%>
		<%
			if (cPage < totalPage) {
		%>
		<a href="list?page=<%=totalPage%>" style="text-decoration: none">
			끝≫</a>
		<%
			}
		%>
	</div>
</body>
</html>