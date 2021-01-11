<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article Search</title>

<style>
p {
	font-family: "Arial Black", sans-serif;
	font-size: 45px;
	font-weight: bold;
	color: LightCoral;
	text-decoration: none
}
</style>

</head>
<body>

	<div>
		<a href="list"
			style="text-decoration: none; color: LightCoral; font-size: 50px;"><b>Article
				Search</b></a>
	</div>
	<br>

	<c:if test="${isLogined}">
		<div>
			<font size="5" color="DimGray"><b>${member.loginId}님
					환영합니다!</b></font> <a href="../member/doLogout"
				style="color: Black; font-size: 4;"><b>logout</b></a>
		</div>
	</c:if>

	<c:if test="${!isLogined}">
		<div>
			<font size="5" color="Red"><b>로그인 후 이용하실 수 있습니다.</b> <a
				href="../article/home" style="color: Black;"><b> login</b></a></font>
		</div>
	</c:if>
	<br>



	<form action="doSearch" method="POST">
		<span> <font size="3"> <a href="write"
				style="text-decoration: none; color: Gray"><b>게시물 작성✍</b></a></font>&nbsp;&nbsp;

			<input placeholder="검색어를 입력해주세요" name="keyword" type="text"
			autocomplete="off"
			style="margin-left: 10px; width: 150px; height: 28px; border: 1px solid Gray;">


			<button type="submit"
				onclick="location.href='../article/doSearch?list"
				style="margin-top: 5px; width: 50px; height: 31px; background-color: #ffb6c1; color: #252525;">
				<b>검색</b>
			</button>
		</span>
	</form>





	<ul>
		<c:forEach items="${articles}" var="article">
			<li><a href="detail?id=${article.id}"><b>${article.id}.
						제목 : ${article.title}</b></a><br>작성자 : ${article.writer}<br> 작성일
				: ${article.regDate}
				<p></li>
		</c:forEach>
	</ul>

<!--  	<style type="text/css">
.page>a.red {
	color: red;
}
</style>
	<div class="page">
		<c:if test="${cPage>1}">
			<a href="list?page=1" style="text-decoration: none">≪처음 </a>
		</c:if>
		<c:set var="pageSize" value="3" />
		<c:set var="from" value="${cPage-pageSize}" />
		<c:if test="${from<1}">
			<c:set var="from" value="1" />
		</c:if>
		<c:set var="end" value="${cPage+pageSize}" />
		<c:if test="${end>totalPage}">
			<c:set var="end" value="${totalPage}" />
		</c:if>
		<c:forEach var="i" begin="${from}" end="${end}" step="1">
			<a class="${cPage == i ? 'red' : ' '}" href="list?page=${i}">${i}</a>
		</c:forEach>
		<c:if test="${cPage<totalPage}">
			<a href="list?page=${totalPage}" style="text-decoration: none">
				끝≫</a>
		</c:if>
	</div> -->
</body>
</html>