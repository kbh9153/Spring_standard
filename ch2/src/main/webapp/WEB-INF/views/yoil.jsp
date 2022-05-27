<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h3>year=<%=request.getParameter("year") %></h3>
	<P>${myDate.year}년 ${myDate.month}월 ${myDate.day}일은 ${yoil}요일입니다.</P>
</body>
</html>
