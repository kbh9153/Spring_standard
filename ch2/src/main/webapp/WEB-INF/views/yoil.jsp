<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<P>${year}년 ${month}월 ${day}일은 ${yoil}요일입니다.</P>
</body>
</html>

<!-- ${} : EL(Express Language). 모델 객체가 가지고 있는 값이 들어갈 자리 -->
