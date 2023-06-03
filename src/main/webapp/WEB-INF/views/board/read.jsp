<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/yorijori/common/css/reset.css" />
<meta charset="UTF-8">
<title>board</title>
<link rel="stylesheet" href="/yorijori/common/css/board/read.css" />
</head>
<body>
	
	<br/>
	<section class="main-section" style="width: 80% ;margin :auto">
		<table class="table table-bordered">
			<tr>
				<th>번호</th>
				<td>111</td>
				<th>작성자</th>
				<td>김우딩</td>
			</tr>
			<tr>
				<th>날짜</th>
				<td>2023-06-02</td>			
			</tr>	
			<tr>
				<th>제목</th>
				<td colspan="3">제목</td>
			</tr>		
			<tr>
				<th>내용</th>
				<td colspan="3">내용</td>
			</tr>		
		</table>
		
		<div class=" btns">
		
			<button type="button" class="btn btn-primary"
				onclick="location.href='/yorijori/board/write">수정</button>
				
			<button type="button" class="btn btn-primary"
				onclick="location.href='/yorijori/board/list">삭제</button>

			<button type="button" class="btn btn-primary"
				onclick="location.href='/yorijori/board/list'">목록</button>	
		</div>

	</section>	


</body>
</html>