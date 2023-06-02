<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>




<link rel="shortcut icon" href="images/icon/favicon13.ico">
<meta charset="UTF-8">
<meta charset="UTF-8">
</head>
<body>
<tiles:insertDefinition name="index" />

	<!-- 헤더 -->
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
	

	<tiles:insertAttribute name="footer"></tiles:insertAttribute>



</body>
</html>
