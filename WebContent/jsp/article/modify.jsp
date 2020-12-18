<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article Modify</title>
<style>
#btn1 {
	border: 1px solid teal;
	border-radius: 5px;
	background-color: rgba(0, 0, 0, 0);
	color: teal;
	font-weight:600;
	pddding: 5px;
}
#btn1:hover {
	background-color: teal;
	color: White;
}
</style>

</head>
<body>
	<div>
		<font size="8" color="Darkcyan"> <b>Article Modify</b>
		</font>
		<p>
	</div>

	<form action="doModify" method="POST">
		<input type="hidden" name="id" value="${article.id}" />
		<div>
			제목<br> <input autocomplete="off" placeholder="제목을 입력해주세요."
				name="title" type="text" value="${article.title}" />
		</div>
		<p>
		<div>
			내용<br>
			<textarea autocomplete="off" placeholder="내용을 입력하세요." name="body">${article.body}</textarea>
		</div>
		<p>
		<div>
			<button id="btn1" type="submit" style="text-decoration: none">수정</button>
			<button id="btn1" type="button"
				onclick="location.href='detail?id=${article.id}'"
				style="text-decoration: none">돌아가기</button>
			<button id="btn1" type="button" onclick="location.href='list'"
				style="text-decoration: none">리스트</button>
		</div>
	</form>

</body>
</html>