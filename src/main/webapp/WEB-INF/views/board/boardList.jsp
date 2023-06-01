<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>과제</title>
<meta charset="utf-8">
<style type="text/css">
 .btn{
  
  background-color: orange;
  border: none;
  font-size: 15%;
  padding: 16px 40px;
  border-radius: 10px;
  color: white;
 }</style>
</head>
<body>

	<div class="container">

		<table id="example" class="table table-haver">
			<thead>

				<th>글번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>조회수</th>

			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>Filter</td>
					<td><a href="/yorijori/boardread">OK</a></td>
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
						style="width: 100px;"
						onclick="location.href='/yorijori/boardwrite'"
						>등록</button>
			</div>
</body>
</html>