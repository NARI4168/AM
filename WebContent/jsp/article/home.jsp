<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME</title>

<style>
P {
	font-family: "Arial Black", sans-serif;
	font-size: 50px;
	font-weight: bold;
	color: orangered;
	text-shadow: 6px 2px 2px gray;
}
</style>

</head>
<body>

	<p>UNKWN.NOV</p>

	<script>
		var LoginForm__submitDone = false;

		function LoginForm__submit(form) {
			if (LoginForm__submitDone) {
				alert('처리중 입니다.');
				return;
			}

			form.loginId.value = form.loginId.value.trim();

			if (form.loginId.value.length == 0) {
				alert('아이디를 입력해주세요.');
				form.loginId.focus();
				return;
			}
			form.loginPw.value = form.loginPw.value.trim();

			if (form.loginPw.value.length == 0) {
				alert('비밀번호을 입력해주세요.');
				form.loginPw.focus();
				return;
			}

			form.submit();
			LoginForm__submitDone = true;
		}
	</script>

	<form action="../member/doLogin" method="POST"
		onsubmit="LoginForm__submit(this); return false;">

		<div>
			<font size="6" color="Gray"><b>Login</b></font><br> <font
				size="4"><input autocomplete="off" placeholder="아이디"
				name="loginId" type="text"> <br> <input
				autocomplete="off" placeholder="비밀번호" name="loginPw" type="password">
			</font><br>
		</div>
		<div>
			<button type="submit" border="1px"
				style="text-decoration: none; color: white; border: none; background: #ff6425; font-size: 4; width: 168px;">로그인</button>
		</div>

	</form>
	<br>
	<br>
	<font size="6"> <a href="../member/join"
		style="text-decoration: none; color: Gray"><b>Join</b></a>
	</font>
	<br>
	<br>
	<font size="6"> <a href="../article/list"
		style="text-decoration: none; color: Gray; text-shadow: none;"><b>게시물
				보기</b></a>
	</font>

</body>
</html>