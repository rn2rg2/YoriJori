<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myapp</title>

<style>
.main-section .table th { 
	width: 120px; 
}
.main-section .table td {
width: 280px; text-align: left; 
}
.main-section .table tr:nth-child(4) td {
	height: 300px; 
}
.btn{
  	background-color: orange;
 	border: none;
 	font-size: 15%;
  	padding: 16px 40px;
  	border-radius: 10px;
  	color: white;
 }
.btns {
    padding-left: 8%;
}
</style>
</head>
<body>
	<!-- view.jsp -->
	
	<section class="main-section">
		
		<h1>Board <small>View</small></h1>
		
		<table class="table table-bordered">
			<tr>
				<th>번호</th>
				<td>${ dto.seq }</td>
				<th>이름</th>
				<td>${ dto.name }김ㅇㅇ</td>
			</tr>
			<tr>
				<th>날짜</th>
				<td>${ dto.regdate }</td>
				
			</tr>	
			<tr>
				<th>제목</th>
				<td colspan="3">${ dto.subject }</td>
			</tr>		
			<tr>
				<th>내용</th>
				<td colspan="3">${ dto.content }</td>
			</tr>		
		</table>
		
		<div class=" btns">
		
			<button type="button" class="btn btn-primary"
				onclick="location.href='/myapp/board/edit.do?seq=${ dto.seq }';">수정</button>
				
			<button type="button" class="btn btn-primary"
				onclick="location.href='/myapp/board/del.do?seq=${ dto.seq }';">삭제</button>

			<button type="button" class="btn btn-primary"
				onclick="location.href='/yorijori/boardlist">목록</button>	
		</div>

	</section>	


</body>
</html>