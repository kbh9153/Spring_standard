<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=utf-8" %>

<html>
<head>
	<title>Home</title>
</head>
<body>
	<p>${year }년 ${month }월 ${day }일은 ${yoil }입니다.</p>
	<p>getYoilMVC 입니다.</p>
	<!-- ${} : EL(Express Language). 모델 객체가 가지고 있는 값이 들어갈 자리 -->
</body>
</html>
