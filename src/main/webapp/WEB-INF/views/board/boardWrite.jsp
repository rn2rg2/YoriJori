<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- include libraries(jQuery, bootstrap) -->
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<!-- include summernote css/js-->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
<!-- include summernote-ko-KR -->
<script src="/resources/js/summernote-ko-KR.js"></script>
<title>글쓰기</title>

<script>
	$(document).ready(function() {
		$('#summernote').summernote({
			placeholder : 'content',
			minHeight : 370,
			maxHeight : null,
			focus : true,
			lang : 'ko-KR'
		});
	});
</script>
 <style type="text/css">
 .btn-success, .btn-default{
  
  background-color: orange;
  border: none;
  font-size: 15%;
  padding: 16px 40px;
  border-radius: 10px;
  color: white;
 }
 </style>
</head>
<body>

	<div style="width: 60%; margin: auto;">
		<form method="post" action="/write">
			<div class="mb-3">

				<label for="title">제목</label> <input type="text"
					class="form-control" name="title" id="title"
					placeholder="제목을 입력해 주세요">

			</div>
			<div class="mb-3">
				<label for="reg_id">작성자</label> <input type="hidden" name="id"
					value="${user.id}">


			</div>
			<br />
			<textarea id="summernote" name="content"></textarea>
			<div class="form-group">
				<div class="col-md-2 text-right">
					<label for="files" class="control-label"></label>
				</div>
				<div class="col-md-8">
					<input type="file" class="form-control input-lg" name="files"
						id="files" placeholder="파일선택" multiple="multiple">
				</div>
			</div>
			<br/>
			<div class="form-group">
				<div class="col-lg-5 text-center"></div>
				<div class="col-lg-1 text-center">
					<button type="submit" class="btn btn-success"
						style="width: 100px; ">등록</button>
				</div>
				<div class="col-lg-1 text-center">
					<button type="reset" class="btn btn-default"
						style="width: 100px;"
						onclick="location.href='/yorijori/boardlist'">취소</button>
				</div>

			</div>
		</form>
	</div>

</body>
</html>

