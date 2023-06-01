<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	<script src="https://kit.fontawesome.com/8b61787525.js" crossorigin="anonymous"></script>

<link rel="stylesheet" href="/yorijori/common/css/cookingclass/classListSlider.css" />
<link rel="stylesheet" href="/yorijori/common/css/cookingclass/classListShine.css" />
<title>쿠킹클래스 리스트</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="swiper-container">
		<h2 class="cookingclass-font">추천 클래스 목록</h2>
		<swiper-container class="mySwiper" pagination="true"
			pagination-clickable="true" navigation="true" space-between="30"
			centered-slides="true" autoplay-delay="2500"
			autoplay-disable-on-interaction="false"> <swiper-slide>
		<img
			src="https://recipe1.ezmember.co.kr/cache/recipe/2020/03/09/03ad3def1a2fd41666413d41c27d93a41.png" />
		<span>여기에 이미지에 대한 설명 작성</span></swiper-slide> <swiper-slide> <img
			src="https://recipe1.ezmember.co.kr/cache/recipe/2020/03/09/03ad3def1a2fd41666413d41c27d93a41.png" />
		<span>여기에 이미지에 대한 설명 작성</span></swiper-slide> <swiper-slide>
		<img
			src="https://recipe1.ezmember.co.kr/cache/recipe/2020/03/09/03ad3def1a2fd41666413d41c27d93a41.png" />
		<span>여기에 이미지에 대한 설명 작성</span></swiper-slide> <swiper-slide>
		<img
			src="https://recipe1.ezmember.co.kr/cache/recipe/2020/03/09/03ad3def1a2fd41666413d41c27d93a41.png" />
		<span>여기에 이미지에 대한 설명 작성</span></swiper-container>
	</div>
	<div class="all-cookingclass">
		<h2 class="cookingclass-font">전체 쿠킹 클래스 목록</h2>
		<div class="dropdown">
			<input type="text" class="search-bar" placeholder="입력하세요!"/>
			<button type="button" class="btn btn-primary dropdown-toggle"
				data-toggle="dropdown">카테고리 선택</button>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="javascript:0">한식</a> 
				<a class="dropdown-item"
					href="11">중식</a> 
				<a class="dropdown-item-text" href="11">일식</a>
				<a class="dropdown-item-text" href="11">양식</a>
				<a class="dropdown-item-text" href="11">기타</a>
			</div>
			<div class="hover14 column">
				<div>
					<figure>
						<img
							src="https://as2.ftcdn.net/v2/jpg/05/95/49/73/1000_F_595497335_LXmQUCnyuAiYErNn4y5MvTeyqShW8MlX.jpg" />
					</figure>
					<span class="className">클래스이름</span>
					<span class="classPrice">&#8361 10,000</span>
				</div>
				<div>
					<figure>
						<img src="https://d20aeo683mqd6t.cloudfront.net/ko/articles/title_images/000/040/606/medium/IMG_8956.jpg?2020" />
					</figure>
					<span class="className">클래스이름</span>
					<span class="classPrice">&#8361 10,000</span>
				</div>
				<div>
					<figure>
						<img src="https://rimage.gnst.jp/livejapan.com/public/article/detail/a/00/00/a0000370/img/basic/a0000370_main.jpg?20201002142956" />
					</figure>
					<span class="className">클래스이름</span>
					<span class="classPrice">&#8361 10,000</span>
				</div>
				<div>
					<figure>
						<img src="https://img.freepik.com/free-photo/cheesy-tokbokki-korean-traditional-food-on-black-board-background-lunch-dish_1150-42988.jpg?w=2000" />
					</figure>
					<span class="className">클래스이름</span>
					<span class="classPrice">&#8361 10,000</span>
				</div>
				<div>
					<figure>
						<img
							src="https://as2.ftcdn.net/v2/jpg/05/95/49/73/1000_F_595497335_LXmQUCnyuAiYErNn4y5MvTeyqShW8MlX.jpg" />
					</figure>
					<span class="className">클래스이름</span>
					<span class="classPrice">&#8361 10,000</span>
				</div>
				<div>
					<figure>
						<img src="https://d20aeo683mqd6t.cloudfront.net/ko/articles/title_images/000/040/606/medium/IMG_8956.jpg?2020" />
					</figure>
					<span class="className">클래스이름</span>
					<span class="classPrice">&#8361 10,000</span>
				</div>
				<div>
					<figure>
						<img src="https://rimage.gnst.jp/livejapan.com/public/article/detail/a/00/00/a0000370/img/basic/a0000370_main.jpg?20201002142956" />
					</figure>
					<span class="className">클래스이름</span>
					<span class="classPrice">&#8361 10,000</span>
				</div>
				<div>
					<figure>
						<img src="https://img.freepik.com/free-photo/cheesy-tokbokki-korean-traditional-food-on-black-board-background-lunch-dish_1150-42988.jpg?w=2000" />
					</figure>
					<span class="className">클래스이름</span>
					<span class="classPrice">&#8361 10,000</span>
				</div><div>
					<figure>
						<img
							src="https://as2.ftcdn.net/v2/jpg/05/95/49/73/1000_F_595497335_LXmQUCnyuAiYErNn4y5MvTeyqShW8MlX.jpg" />
					</figure>
					<span class="className">클래스이름</span>
					<span class="classPrice">&#8361 10,000</span>
				</div>
				<div>
					<figure>
						<img src="https://d20aeo683mqd6t.cloudfront.net/ko/articles/title_images/000/040/606/medium/IMG_8956.jpg?2020" />
					</figure>
					<span class="className">클래스이름</span>
					<span class="classPrice">&#8361 10,000</span>
				</div>
				<div>
					<figure>
						<img src="https://rimage.gnst.jp/livejapan.com/public/article/detail/a/00/00/a0000370/img/basic/a0000370_main.jpg?20201002142956" />
					</figure>
					<span class="className">클래스이름</span>
					<span class="classPrice">&#8361 10,000</span>
				</div>
				<div>
					<figure>
						<img src="https://img.freepik.com/free-photo/cheesy-tokbokki-korean-traditional-food-on-black-board-background-lunch-dish_1150-42988.jpg?w=2000" />
					</figure>
					<span class="className">클래스이름</span>
					<span class="classPrice">&#8361 10,000</span>
				</div>
			</div>
		</div>
		<script
			src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-element-bundle.min.js"></script>
</body>
</html>