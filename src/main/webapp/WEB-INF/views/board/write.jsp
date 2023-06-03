

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/yorijori/common/css/reset.css" />
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

<link rel="stylesheet" href="/yorijori/common/css/board/write.css" />

<title>글쓰기</title>
</head>
<body>

	<div style="width: 80%; margin: auto;">
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

			<textarea id="summernote" name="content"
				style="width: 100%; height: 500px;"></textarea>
			<div class="form-group">
				<div class="col-md-2 text-right">
					<label for="files" class="control-label"></label>
				</div>
				<div class="col-md-8">
					<input type="file" class="form-control input-lg" name="files"
						id="files" placeholder="파일선택" multiple="multiple">
				</div>
			</div>
			

			<div class="form-group">
				<div class="col-lg-5 text-center"></div>
				<div class="col-lg-1 text-center">
					<button type="submit" class="btn btn-success">등록</button>
				</div>
				<div class="col-lg-1 text-center">
					<button type="reset" class="btn btn-default" style="width: 100px;"
						onclick="location.href='/yorijori/board/list'">취소</button>
				</div>
			</div>
		</form>
	</div>

</body>
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
</html>



