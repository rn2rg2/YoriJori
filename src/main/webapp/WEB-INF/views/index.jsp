<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>


	<link rel="stylesheet" type="text/css" href="/yorijori/common/css/layout/test.css" />
	<link rel="stylesheet" type="text/css" href="/yorijori/common/css/layout/footer.css" />
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
	<script src="https://kit.fontawesome.com/8055a0c4b7.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="shortcut icon" href="images/icon/favicon13.ico">
	<meta charset="UTF-8">
<meta charset="UTF-8">
</head>
<body>
		<!-- 헤더 -->
    	<tiles:insertAttribute name="header"></tiles:insertAttribute>
    	
    	 <div class="container-xxl py-5">
        <div class="container">
            <div class="row g-0 gx-5 align-items-end">
                <div class="col-lg-6">
                    <div class="section-header text-start mb-5 wow fadeInUp" data-wow-delay="0.1s"
                        style="max-width: 500px;">
                        <h1 class="display-5 mb-3">레시피 랭킹</h1>
                    </div>
                </div>
            </div>
            
            <div class="tab-content">
                <div id="tab-1" class="tab-pane fade show p-0 active">
                    <div class="row g-4">
                        <div class="col-xl-3 col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                            <div class="product-item">
                                <div class="position-relative bg-light overflow-hidden">
                                    <img  class="img-fluid w-100" src="images/main/product-1.jpg" alt="" width="512px" height="512px">
                                    <div
                                        class="bg-secondary rounded text-white position-absolute start-0 top-0 m-4 py-1 px-3">
                                        1위</div>
                                </div>
                                <div class="text-center p-4">
                                    <a class="d-block h5 mb-2" href="">된장찌개</a>
                                    <div style="font-size: 18px;"><i class="fa fa-heart me-2" style="color: red;"></i>3952</div>
                                </div>
                                <div class="d-flex border-top">
                                    <small class="w-50 text-center border-end py-2">
                                        <a class="text-body"><i class="fa fa-clock-o text-primary me-2"></i>15분</a>
                                    </small>
                                    <small class="w-50 text-center py-2">
                                        <a class="text-body" href=""><i class="fa fa-heart text-primary me-2"></i>찜하기</a>
                                    </small>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-3 col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
                            <div class="product-item">
                                <div class="position-relative bg-light overflow-hidden">
                                    <img class="img-fluid w-100" src="images/main/product-2.jpg" alt="">
                                    <div
                                        class="bg-secondary rounded text-white position-absolute start-0 top-0 m-4 py-1 px-3">
                                        2위</div>
                                </div>
                                <div class="text-center p-4">
                                    <a class="d-block h5 mb-2" href="">가지볶음</a>
                                    <div style="font-size: 18px;"><i class="fa fa-heart me-2" style="color: red;"></i>3952</div>
                                </div>
                                <div class="d-flex border-top">
                                    <small class="w-50 text-center border-end py-2">
                                        <a class="text-body"><i class="fa fa-clock-o text-primary me-2"></i>30분</a>
                                    </small>
                                    <small class="w-50 text-center py-2">
                                        <a class="text-body" href=""><i class="fa fa-heart text-primary me-2"></i>찜하기</a>
                                    </small>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-3 col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.5s">
                            <div class="product-item">
                                <div class="position-relative bg-light overflow-hidden">
                                    <img class="img-fluid w-100" src="images/main/product-3.jpg" alt="">
                                    <div
                                        class="bg-secondary rounded text-white position-absolute start-0 top-0 m-4 py-1 px-3">
                                        3위</div>
                                </div>
                                <div class="text-center p-4">
                                    <a class="d-block h5 mb-2" href="">장조림</a>
                                    <div style="font-size: 18px;"><i class="fa fa-heart me-2" style="color: red;"></i>3952</div>
                                </div>
                                <div class="d-flex border-top">
                                    <small class="w-50 text-center border-end py-2">
                                        <a class="text-body"><i class="fa fa-clock-o text-primary me-2"></i>25분</a>
                                    </small>
                                    <small class="w-50 text-center py-2">
                                        <a class="text-body" href=""><i class="fa fa-heart text-primary me-2"></i>찜하기</a>
                                    </small>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-3 col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.7s">
                            <div class="product-item">
                                <div class="position-relative bg-light overflow-hidden">
                                    <img class="img-fluid w-100" src="images/main/product-4.jpg" alt="">
                                    <div
                                        class="bg-secondary rounded text-white position-absolute start-0 top-0 m-4 py-1 px-3">
                                        4위</div>
                                </div>
                                <div class="text-center p-4">
                                    <a class="d-block h5 mb-2" href="">오이 소박이</a>
                                    <div style="font-size: 18px;"><i class="fa fa-heart me-2" style="color: red;"></i>3952</div>
                                </div>
                                <div class="d-flex border-top">
                                    <small class="w-50 text-center border-end py-2">
                                        <a class="text-body"><i class="fa fa-clock-o text-primary me-2"></i>10분</a>
                                        
                                    </small>
                                    <small class="w-50 text-center py-2">
                                        <a class="text-body" href=""><i class="fa fa-heart text-primary me-2"></i>찜하기</a>
                                    </small>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-3 col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.5s">
                            <div class="product-item">
                                <div class="position-relative bg-light overflow-hidden">
                                    <img class="img-fluid w-100" src="images/main/product-3.jpg" alt="">
                                    <div
                                        class="bg-secondary rounded text-white position-absolute start-0 top-0 m-4 py-1 px-3">
                                        3위</div>
                                </div>
                                <div class="text-center p-4">
                                    <a class="d-block h5 mb-2" href="">장조림</a>
                                    <div style="font-size: 18px;"><i class="fa fa-heart me-2" style="color: red;"></i>3952</div>
                                </div>
                                <div class="d-flex border-top">
                                    <small class="w-50 text-center border-end py-2">
                                        <a class="text-body"><i class="fa fa-clock-o text-primary me-2"></i>25분</a>
                                    </small>
                                    <small class="w-50 text-center py-2">
                                        <a class="text-body" href=""><i class="fa fa-heart text-primary me-2"></i>찜하기</a>
                                    </small>
                                </div>
                            </div>
                        </div>
                                                <div class="col-xl-3 col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.5s">
                            <div class="product-item">
                                <div class="position-relative bg-light overflow-hidden">
                                    <img class="img-fluid w-100" src="images/main/product-5.jpg" alt="">
                                    <div
                                        class="bg-secondary rounded text-white position-absolute start-0 top-0 m-4 py-1 px-3">
                                        3위</div>
                                </div>
                                <div class="text-center p-4">
                                    <a class="d-block h5 mb-2" href="">된장찌개</a>
                                    <div style="font-size: 18px;"><i class="fa fa-heart me-2" style="color: red;"></i>3952</div>
                                </div>
                                <div class="d-flex border-top">
                                    <small class="w-50 text-center border-end py-2">
                                        <a class="text-body"><i class="fa fa-clock-o text-primary me-2"></i>25분</a>
                                    </small>
                                    <small class="w-50 text-center py-2">
                                        <a class="text-body" href=""><i class="fa fa-heart text-primary me-2"></i>찜하기</a>
                                    </small>
                                </div>
                            </div>
                        </div>
                                                <div class="col-xl-3 col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.5s">
                            <div class="product-item">
                                <div class="position-relative bg-light overflow-hidden">
                                    <img class="img-fluid w-100" src="images/main/product-8.jpg" alt="">
                                    <div
                                        class="bg-secondary rounded text-white position-absolute start-0 top-0 m-4 py-1 px-3">
                                        3위</div>
                                </div>
                                <div class="text-center p-4">
                                    <a class="d-block h5 mb-2" href="">가지볶음</a>
                                    <div style="font-size: 18px;"><i class="fa fa-heart me-2" style="color: red;"></i>3952</div>
                                </div>
                                <div class="d-flex border-top">
                                    <small class="w-50 text-center border-end py-2">
                                        <a class="text-body"><i class="fa fa-clock-o text-primary me-2"></i>25분</a>
                                    </small>
                                    <small class="w-50 text-center py-2">
                                        <a class="text-body" href=""><i class="fa fa-heart text-primary me-2"></i>찜하기</a>
                                    </small>
                                </div>
                            </div>
                        </div>
                                                <div class="col-xl-3 col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.5s">
                            <div class="product-item">
                                <div class="position-relative bg-light overflow-hidden">
                                    <img class="img-fluid w-100" src="images/main/product-4.jpg" alt="">
                                    <div
                                        class="bg-secondary rounded text-white position-absolute start-0 top-0 m-4 py-1 px-3">
                                        3위</div>
                                </div>
                                <div class="text-center p-4">
                                    <a class="d-block h5 mb-2" href="">오이 소박이</a>
                                    <div style="font-size: 18px;"><i class="fa fa-heart me-2" style="color: red;"></i>3952</div>
                                </div>
                                <div class="d-flex border-top">
                                    <small class="w-50 text-center border-end py-2">
                                        <a class="text-body"><i class="fa fa-clock-o text-primary me-2"></i>25분</a>
                                    </small>
                                    <small class="w-50 text-center py-2">
                                        <a class="text-body" href=""><i class="fa fa-heart text-primary me-2"></i>찜하기</a>
                                    </small>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 text-center wow fadeInUp" data-wow-delay="0.1s">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
        	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
</body>
</html>
