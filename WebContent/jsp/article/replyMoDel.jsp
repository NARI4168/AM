<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reply Modify And Delete</title>
<style>
#btn1 {
	border: 1px solid olivedrab;
	border-radius: 5px;
	background-color: rgba(0, 0, 0, 0);
	color: olivedrab;
	font-weight: 600;
	pddding: 5px;
}

#btn1:hover {
	background-color: olivedrab;
	color: White;
}
</style>
</head>
<body>
	<div>
		<font size="8" color="olive"> <b>Delete And Modify Of Reply</b>
		</font>
		<p>
	</div>
	<form action="doModifyReply" method="POST">
		<input type="hidden" name="id" value="${reply.id}" />
		<div>
			내용<br>
			<textarea autocomplete="off" placeholder="내용을 입력하세요." name="body">${reply.body}</textarea>
		</div>
		<p>
		<div>
			<button id="btn1" type="submit" style="text-decoration: none">수정</button>					
			<button id="btn1" type="button" onclick="location.href='doDeleteReply?id=${reply.id}'"
				style="text-decoration: none;">삭제</button>			
			<button id="btn1" type="button"
				onclick="location.href='detail?id=${reply.articleId}'"
				style="text-decoration: none">돌아가기</button>		
		</div>
	</form>
</body>
</html>