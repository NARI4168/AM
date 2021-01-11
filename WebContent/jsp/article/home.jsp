<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME</title>

<style>
P {
	text-align: center;
	font-family: "Arial Black", sans-serif;
	font-size: 50px;
	font-weight: bold;
	color: orangered;
	text-shadow: 6px 2px 2px gray;
	font-family: "Arial Black", sans-serif;
}

P1 { 
	text-align: center;
	font-family: "Arial Black", sans-serif;
	font-size: 50px;
	font-weight: bold;
	color: teal;
	text-shadow: 5px 0px 2px white;
}

P2 {
	font-family: "Arial Black", sans-serif;
	font-size: 40px;
	font-weight: bold;
	color: #eda5aa;
	text-shadow: 1px 1px 0px #c0c0c0;
}

</style>

</head>
<body bgcolor="#353535">

	<script
		src="https://s3.ap-northeast-2.amazonaws.com/materials.spartacodingclub.kr/xmas/snow.js"></script>
	<p1>UNKWN.NOV</p1>
	<form action="../member/doLogin" method="POST">
		<div>
			<p2>Login</p2>
			<br> <font size="4"><input autocomplete="off"
				placeholder="아이디" name="loginId" type="text"> <br> <input
				autocomplete="off" placeholder="비밀번호" name="loginPw" type="password">
			</font><br>
		</div>
		<div>
			<button type="submit" border="1px"
				style="text-decoration: none; color: white; border: none; background: teal; font-size: 4; width: 168px;">로그인</button>
		</div>
	</form>
	<br>
	<br>
	<a href="../member/join" style="text-decoration: none"><p2>Join</p2></a>
	<br>
	<br>
	<a href="../article/list" style="text-decoration: none"><p2>게시물
		보기</p2></a>

</body>
</html>