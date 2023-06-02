<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>

		<!-- 테스트 -->
	    <!-- 헤더 -->
		<link rel="stylesheet" type="text/css" href="/yorijori/common/css/layout/test.css" />
		<link rel="stylesheet" type="text/css" href="/yorijori/common/css/layout/footer.css" />
	    <script src="https://kit.fontawesome.com/8055a0c4b7.js" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	    

<!--

//-->



	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
</head>
<body>
<!-- Navbar Start -->
<nav class="navbar navbar-expand-lg bg-white navbar-light shadow-sm px-2 py-3 py-lg-0" style="position: sticky; top: 0; z-index: 100;">
    <div class="container">
        <a href="index.html" class="navbar-brand p-0">
            <img src="images/logo.png" alt="" style="height: 40px;">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav mx-auto py-0">
                <a href="index.html" class="nav-item nav-link active">홈페이지</a>
                <a href="about.html" class="nav-item nav-link">커뮤니티</a>
                <a href="/yorijori/cookingclass/list" class="nav-item nav-link">쿠킹 클래스</a>
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">레시피</a>
                    <div class="dropdown-menu m-0">
                        <a href="/yorijori/recipe/insert" class="dropdown-item">레시피 등록</a>
                        <a href="team.html" class="dropdown-item">레시피 2</a>
                        <a href="testimonial.html" class="dropdown-item">레시피 3</a>
                        <a href="appointment.html" class="dropdown-item">레시피 4</a>
                    </div>
                </div>
                <form class="d-flex position-relative"> <!--position-relative 추가-->
                    <input class="form-control me-2 custom-search-input" type="search" placeholder="레시피 검색..." aria-label="Search" 
                    style="border-radius: 30px; text-align: center;   width: 325px; height: 50px; background-color: #f3f3f3;">
                    <button class="btn btn-link custom-search-button" type="submit"><i class="fas fa-search"></i></button>
                </form>

                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle text-center text-dark" data-bs-toggle="dropdown" ><i class="fa-solid fa-user"></i></a>
                    <div class="dropdown-menu m-0">
                        <a href="signup.html" class="dropdown-item">로그인</a>
                        <a href="signup.html" class="dropdown-item">회원가입</a>
                        <a href="/yorijori/mypage" class="dropdown-item">마이페이지</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</nav>

</body>
</html>