<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile Update</title>
<style>
#btn1 {
	border: 1px solid #ffd400;
	border-radius: 5px;
	background-color: rgba(0, 0, 0, 0);
	color: #ffd400;
	font-weight: 600;
	pddding: 5px;
	color: #ffd400;
}

#btn1:hover {
	background-color: #ffd400;
	color: White;
}
</style>

</head>
<body>
	<div>
		<font size="8" color="#ffd400"> <b>Profile Update</b>
		</font>
		<p>
	</div>
	<form action="doModify_profile" method="POST"
		enctype="multipart/form-data">
		<input type="hidden" name="id" value="${member.id}" />
		<div class="profile_photo">
			<span class="photo"> 
			
			<!--기본이미지--> 
			<c:if
					test="${member.imagePath eq null}">
					<img src="https://mblogthumb-phinf.pstatic.net/20150427_73/ninevincent_1430122793329pvryW_JPEG/kakao_7.jpg?type=w2" width="100" height="100">
				</c:if>&nbsp; 
				<!--이미지가 있을경우--> 
				<c:if test="${member.imagePath ne null}">		
					<img src="https://mblogthumb-phinf.pstatic.net/20150427_258/ninevincent_1430122792883HktTY_JPEG/kakao_6.jpg?type=w2" width="100" height="100" alt="" />
				</c:if>
				
				
				<!-- <label for="imgFile"> 사진선택 </label> <input type="file"
		id="imgFile" name="imgFile" style="display: none" accept="image/*">-->

				<input type="file" id="imgFile" name="imgFile" accept="image/*">
			</span>
		</div>
		<p>
		<div>
			name &nbsp; <input autocomplete="off" placeholder="" name="name"
				type="text" value="${member.name}" />
		</div>
		<p>
		<div>
			<button id="btn1" type="submit" style="text-decoration: none">변경</button>

			<button id="btn1" type="button" onclick="location.href='list'"
				style="text-decoration: none">돌아가기</button>
		</div>
	</form>
</body>
</html>