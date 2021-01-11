<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join</title>

<style>
#btn1 {
	border: 1px solid peru;
	border-radius: 5px;
	background-color: rgba(0, 0, 0, 0);
	color: peru;
	pddding: 5px;
}

#btn1:hover {
	background-color: peru;
	color: White;
}
</style>
</head>
<body>
	<font size="8" color="peru"> <b>Join</b></font>
	<p>

		<script>
			var JoinForm__submitDone = false;

			function JoinForm__submit(form) {
				if (JoinForm__submitDone) {
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
				form.loginPwConfirm.value = form.loginPwConfirm.value.trim();

				if (form.loginPwConfirm.value.length == 0) {
					alert('비밀번호을 한번 더 입력해주세요.');
					form.loginPwConfirm.focus();
					return;
				}
				if (form.loginPw.value != form.loginPwConfirm.value) {
					alert('비밀번호가 일치하지 않습니다.');
					form.loginPwConfirm.focus();
					return;
				}
				form.name.value = form.name.value.trim();

				if (form.name.value.length == 0) {
					alert('이름을 입력해주세요.');
					form.name.focus();
					return;
				}
				form.submit();
				JoinForm__submitDone = true;
			}
		</script>
	<form action="doJoin" method="POST"
		onsubmit="JoinForm__submit(this); return false;">
		<div>
			아이디 : <input autocomplete="off" placeholder="아이디를 입력해주세요."
				name="loginId" type="text" />
		</div>
		<p>
		<div>
			비밀번호 : <input placeholder="비밀번호을 입력해주세요." name="loginPw"
				type="password" />
		</div>
		<p>
		<div>
			비밀번호 확인 : <input placeholder="비밀번호을 한번 더 입력해주세요."
				name="loginPwConfirm" type="password" />
		</div>
		<p>
		<div>
			이름 : <input autocomplete="off" placeholder="이름을 입력해주세요." name="name"
				type="text" />
		</div>

		<p>
		<div>
			<button id="btn1" type="submit">가입하기</button>
			<button id="btn1" onclick="location.href='../article/home'">돌아가기
			</button>
		</div>
	</form>

</body>
</html>