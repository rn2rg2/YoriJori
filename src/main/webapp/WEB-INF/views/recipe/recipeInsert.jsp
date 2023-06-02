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
<link rel="stylesheet"
	href="/yorijori/common/css/recipe/recipeInsert.css">
</head>
<body>
	<h2>레시피 등록</h2>
	<div class="container">
		<form action="/action_page.php">
			<div class="form-group">
				<label>레시피 제목</label> <input type="text" class="form-control"
					id="email" placeholder="맛좋은 게살 버거" name="email">
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
						<a class="dropdown-item" href="javascript:0">용도1</a>
					</div>
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown">조리방법</button>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="javascript:0">방법1</a>
					</div>
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown">음식분류 1</button>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="javascript:0">한식</a> <a
							class="dropdown-item" href="javascript:0">중식</a>
					</div>
					<button type="button"
						class="btn btn-primary dropdown-toggle last-button"
						data-toggle="dropdown">음식분류 2</button>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="javascript:0">고기류</a> <a
							class="dropdown-item" href="javascript:0">생선류</a>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label>기타</label>
				<div class="category">
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown">인분</button>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="javascript:0">용도1</a>
					</div>
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown">시간</button>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="javascript:0">방법1</a>
					</div>
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown">난이도</button>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="javascript:0">한식</a> <a
							class="dropdown-item" href="javascript:0">중식</a>
					</div>
				</div>
			</div>
			<div class="form-group form-check">
				<label class="form-check-label"> <input
					class="form-check-input" type="checkbox" name="remember">
					Remember me
				</label>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</body>
</html>