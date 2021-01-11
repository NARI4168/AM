<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test Page</title>
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
		<font size="8" color="#ffd400"> <b>Test Page</b>
		</font>
	<p>
	</div>

	이미지 불러오기 테스트!<p>

<img src="https://steemitimages.com/DQmaqNDvQ4BHQcyN2G46XtsydcUvB39p6PTCRbcamAH8hYa/Upset_480_480_0_64000_0_1_0.jpg"/>

<%String URI ="https://steemitimages.com/DQmaqNDvQ4BHQcyN2G46XtsydcUvB39p6PTCRbcamAH8hYa/Upset_480_480_0_64000_0_1_0.jpg";
out.println(URI); %>



<img src="doImageLoad?f=1"/>

<!--<img src="f"/>-->


</body>
</html>