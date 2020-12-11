<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<font size="8" color="peru"> <b>Login</b></font><p>

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

	<form action="doLogin" method="POST"
		onsubmit="LoginForm__submit(this); return false;">
		<div>
			아이디 : <input placeholder="아이디를 입력해주세요."
				name="loginId" type="text" />
		</div><p>
		<div>
			비밀번호 : <input placeholder="비밀번호을 입력해주세요."
				name="loginPw" type="password" />
		</div><p>
		<div>
			<button type="submit">로그인</button>
			<button type="button">
				<a href="../article/home" style="text-decoration: none;">돌아가기</a>
			</button>
		</div>
	</form>

</body>
</html>