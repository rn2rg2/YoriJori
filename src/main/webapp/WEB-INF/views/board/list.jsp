<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css"
	href="/yorijori/common/css/reset.css" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>과제</title>
<meta charset="utf-8">
<link rel="stylesheet" href="/yorijori/common/css/board/list.css" />
</head>
<body>
	<div style="width: 80%; margin: auto">
		<table id="example" class="table table-haver">
			<thead>
			<tr>
				<th style="width: 10%">글번호</th>
				<th style="width: 70%">제목</th>
				<th style="width: 10%">작성자</th>
				<th style="width: 10%">조회수</th>
			</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td><a href="/yorijori/board/read">OK</a></td>
					<td>Filter</td>
					<td>3</td>
				</tr>
			</tbody>
		</table>
	</div>
		<form action="" method="post" style="text-align: center">
			<select name="tag">
				<option value="id">작성자</option>
				<option value="title">제목</option>
				<option value="content">본문</option>
			</select> 
			<input type="text" name="search" /> 
			<input type="submit" value="검색">
			
		</form>
			<div class="col-lg-8 text-center">
				<button type="reset" class="btn btn-default"
						onclick="location.href='/yorijori/board/write'">등록</button>
			</div>
</body>
</html>