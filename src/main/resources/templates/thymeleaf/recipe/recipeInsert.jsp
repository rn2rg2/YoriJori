<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시피 등록</title>
<link rel="stylesheet" type="text/css"
	href="/yorijori/common/css/reset.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://kit.fontawesome.com/8b61787525.js"
	crossorigin="anonymous"></script>
	
	<!-- include summernote css/js-->
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
	
	
<link rel="stylesheet"
	href="/yorijori/common/css/recipe/recipeInsert.css">
	<script>
$(document).ready(function() {
	  $('#summernote').summernote({
 	    	placeholder: 'content',
	        minHeight: 370,
	        maxHeight: null,
	        focus: true, 
	        lang : 'ko-KR'
	  });
	});
</script>
</head>
<body>

<%-- <tiles:insertAttribute name="header" /> --%>


	<h2>레시피 등록</h2>
	<div class="container">
		<form action="/action_page.php">
		
			<div class="form-group">
				<label>레시피 제목</label> <input type="text" class="form-control"
					id="" placeholder="맛좋은 게살 버거" name="l">
			</div>
			
			<div class="form-group">
				<label>레시피 한줄소개</label> <textarea
					class="form-control des" id="des" placeholder="향긋한 게살향이 듬뿍나는 맛있는 게살버거~~"
					name="pswd"></textarea>
			</div>
			
			<div class="form-group">
				<label>카테고리</label>
				<div class="category">
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown">음식용도</button>
					<div class="dropdown-menu">
						<a class="dropdown-item insert-item" href="javascript:0">용도1</a>
					</div>
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown">조리방법</button>
					<div class="dropdown-menu">
						<a class="dropdown-item insert-item" href="javascript:0">방법1</a>
					</div>
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown">음식분류 1</button>
					<div class="dropdown-menu">
						<a class="dropdown-item insert-item" href="javascript:0">한식</a> <a
							class="dropdown-item insert-item" href="javascript:0">중식</a>
					</div>
					<button type="button"
						class="btn btn-primary dropdown-toggle last-button"
						data-toggle="dropdown">음식분류 2</button>
					<div class="dropdown-menu">
						<a class="dropdown-item insert-item" href="javascript:0">고기류</a> <a
							class="dropdown-item insert-item" href="javascript:0">생선류</a>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label>기타</label>
				<div class="category">
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown">인분</button>
					<div class="dropdown-menu">
						<a class="dropdown-item insert-item" href="javascript:0">용도1</a>
					</div>
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown">시간</button>
					<div class="dropdown-menu">
						<a class="dropdown-item insert-item" href="javascript:0">방법1</a>
					</div>
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown">난이도</button>
					<div class="dropdown-menu">
						<a class="dropdown-item insert-item" href="javascript:0">한식</a> <a
							class="dropdown-item insert-item" href="javascript:0">중식</a>
					</div>
				</div>
			</div>

			
			<br/>
			<br/>
			
			<div class="form-group imp-group">
				<label>재료</label>
				<input type="text" class="form-control"
					id="" placeholder="재료이름 검색" name="">
					<br>
				<div class="imp-list">
					<button type="button" class="btn btn-primary insert imp">양배추</button>
					<button type="button" class="btn btn-primary insert imp">버터</button>
					<button type="button" class="btn btn-primary insert imp">사과</button>
					<button type="button" class="btn btn-primary insert imp selected">양배추</button>
					<button type="button" class="btn btn-primary insert imp">돼지고기</button>
					<button type="button" class="btn btn-primary insert imp">사과</button>
					<button type="button" class="btn btn-primary insert imp">양배추</button>
					<button type="button" class="btn btn-primary insert imp">사과</button>
					<button type="button" class="btn btn-primary insert imp">양배추</button>
					<button type="button" class="btn btn-primary insert imp">버터</button>
					<button type="button" class="btn btn-primary insert imp">사과</button>
					<button type="button" class="btn btn-primary insert imp">버터</button>
					<button type="button" class="btn btn-primary insert imp">설탕</button>
					<button type="button" class="btn btn-primary insert imp">양배추</button>
					<button type="button" class="btn btn-primary insert imp">양배추</button>
					<button type="button" class="btn btn-primary insert imp selected">사과</button>
					<button type="button" class="btn btn-primary insert imp">양배추</button>
					<button type="button" class="btn btn-primary insert imp">버터</button>
					<button type="button" class="btn btn-primary insert imp">사과</button>
					<button type="button" class="btn btn-primary insert imp">양배추</button>
					<button type="button" class="btn btn-primary insert imp">소다</button>
					<button type="button" class="btn btn-primary insert imp">양배추</button>
					<button type="button" class="btn btn-primary insert imp">양배추</button>
					<button type="button" class="btn btn-primary insert imp">사과</button>
					<button type="button" class="btn btn-primary insert imp">양배추</button>
				</div>
				<br/>
				<label>재료량</label>
				<div class="imp-quantity-div">
					<table class="imp-quantity">
						<tbody>
							<tr>
								<td><input type="text" value="사과"></td>
								<td><input type="text" value="" placeholder="ex) 1/2개"><td>
								<td><input type="text" value="양배추"></td>
								<td><input type="text" value="" placeholder="ex) 100g"><td>
							</tr>
							<tr>
								<td><input type="text" value="재료3"></td>
								<td><input type="text" placeholder="ex) 한스푼"><td>
								<td><input type="text" value="재료4"></td>
								<td><input type="text" placeholder="ex) 500ml"><td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			
			<br/>
			<br/>
			
			<div class="form-group">
				<label>요리순서</label>
				<span class="cooking-step">STEP 1</span>
				<textarea id="summernote" name="editordata"></textarea> 
			</div>
			
			
			
			<div class="form-group">
				<label>요리팁</label>
				<textarea
					class="form-control des" id="des" placeholder="게살 잘 바르는 팁!!!"
					name="pswd"></textarea>
			</div>
			
			<div class="form-group">
				<label>태그</label>
				<input type="text" class="form-control"
					id="" placeholder="딸기, 키위, 다이어트, 비만" name="l">
			</div>
			<button type="submit" class="btn btn-primary insert submit-btn">Submit</button>
		</form>
	</div>
	<%-- <tiles:insertAttribute name="footer" /> --%>
	

</body>
</html>