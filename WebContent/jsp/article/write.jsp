<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article Write</title>
<style>
#btn1 {
	border: 1px solid plum;
	border-radius: 5px;
	background-color: rgba(0, 0, 0, 0);
	color: plum;
	font-weight:600;
	pddding: 5px;
}
#btn1:hover {
	background-color: plum;
	color: White;
}
</style>

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
			<button id="btn1" type="submit" onclick="location.href='list'"
				style="text-decoration: none">등록</button>
			<button id="btn1" type="button" onclick="location.href='list'"
				style="text-decoration: none">돌아가기</button>
		</div>
	</form>
 
</body>
</html>