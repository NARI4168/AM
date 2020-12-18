<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article Detail</title>

<style>
#btn1 {
	border: 1px solid Deepskyblue;
	border-radius: 5px;
	background-color: rgba(0, 0, 0, 0);
	color: Deepskyblue;
	pddding: 5px;
}

#btn1:hover {
	background-color: Deepskyblue;
	color: White;
}
</style>
</head>
<body>

	<div>
		<font size="8" color="SkyBlue"> <b>Article Detail</b>
		</font>
		<p>
	</div>
	<div>
		<font size="7"> <b>제목 : ${article.title}</b></font>
		<p>
			<font size="4"> <b>작성자 : ${article.writer} </b></font><br> <font
				size="3" color="Gray"> 작성일 : ${article.regDate}</font><br> <font
				size="3" color="Gray"> <a href="doDelete?id=${article.id}"
				style="text-decoration: none; color: Gray">삭제</a> | <a
				href="modify?id=${article.id}"
				style="text-decoration: none; color: Gray">수정</a></font>
	</div>
	<div>
		<font size="6"> 내용 : ${article.body}</font>
	</div>
	<br>
	<div>
		<button id="btn1"
			onclick="location.href='../article/detail?id=${prevArticle.id}'">◀이전글</button>
		<button id="btn1" onclick="location.href='list'">리스트</button>
		<button id="btn1"
			onclick="location.href='../article/detail?id=${nextArticle.id}'">다음글▶</button>
	</div>
	<br>
	<div class="reply">
		<div>
			<c:forEach items="${replies}" var="reply">
				<font size="4"><b>${reply.writer}</b></font>
				<br>
				<font color="Gray"> ${reply.regDate}</font>
				<p>${reply.body}
				<p><font size="2" color="Gray">수정/삭제    답글 </font>
				<hr align="left" style="border: solid 0.8px Gray;; width: 23%">
			</c:forEach>
		</div>
		<form action="doWriteReply" method="POST">
			<input type="hidden" name="id" value="${article.id}" />
			<div>
				<input placeholder="댓글을 입력해주세요" name="body" type="text"
					autocomplete="off"
					style="margin: 0px; width: 300px; height: 70px; border: 1px dashed Gray;"><br>
			</div>
			<div>
				<button id="btn1" type="submit"
					onclick="location.href='../article/detail?id=${article.id}'"
					style="margin-left: 207px; margin-top: 5px; width: 100px; height: 25px;">
					<b>댓글남기기</b>
				</button>
			</div>
		</form>

	</div>
</body>
</html>