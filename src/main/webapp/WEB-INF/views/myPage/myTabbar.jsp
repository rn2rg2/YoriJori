<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sliding Bootstrap Tabs</title>
<style type="text/css">
.tabs {
	margin: 0;
	padding: 0;
	list-style: none;
	display: table; /* [1] */
	table-layout: fixed; /* [2] */
	width: 100%; /* [3] */
}
.tabs__item {
	display: table-cell; /* [4] */
}
.tabs__link {
	display: block; /* [5] */
}
/**
 * Primary nav. Extends `.tabs`.
 *
 * 1. Stop tabs’ corners leaking out beyond our 4px round.
 */
.primary-nav {
	text-align: center;
	border-radius: 4px;
	overflow: hidden; /* [1] */
}
.primary-nav a {
	padding: 1em;
	background-color: #808080; /* 탭 배경색 원하시는대로 하시면 됩니다*/
	color: #fff;
	font-weight: bold;
	text-decoration: none;
}
.primary-nav a:hover {
	background-color: #e4e4e4; /* 호버 됐을시 현재 배경 색깔 */
	color: #ffae88;
}
</style>
</head>

<body>
	<div>
		<tiles:insertAttribute name="header"/>
	</div>
		<br/>
	<ul class="tabs  primary-nav">
		<li class="tabs__item"><a href="/yorijori/mypage/profile" class="tabs__link">기본정보</a></li>
		<li class="tabs__item"><a href="/yorijori/mypage/recipelist" class="tabs__link">기록보관소</a></li>
		<li class="tabs__item"><a href="/yorijori/mypage/commentlist" class="tabs__link">댓글 / 리뷰</a></li>
		<li class="tabs__item"><a href="/yorijori/mypage/chat" class="tabs__link">유저 쪽지</a></li>
		<li class="tabs__item"><a href="/yorijori/mypage/refri" class="tabs__link">나만의 냉장고</a></li>
		<li class="tabs__item"><a href="/yorijori/mypage/tray" class="tabs__link">나만의 식판</a></li>	
	</ul>
	<div>
		<tiles:insertAttribute name="content"/>
	</div>
	<div>
		<tiles:insertAttribute name="footer"/>
	</div>
</body>
</html>