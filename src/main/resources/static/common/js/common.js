/*
	공통 js function 모음
 */

// 문자열에서 콤마 제거
function fn_parseNumber(num) {
	return ("" + num).replace(/,/g, "");
}

// 숫자에 콤마 넣기
function fn_numberFormat(num) {
	var regexp = /\B(?=(\d{3})+(?!\d))/g;
	return num.toString().replace(regexp, ",");
}

// 숫자인지 확인
function fn_isNumber(val) {
	var pattern = /^[0-9]*$/;
	return pattern.test(val);
}
// 전화번호 포맷팅
function fn_phoneFormatString(phoneNo) {
	var regexp = /(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/;
	return phoneNo.replace(regexp, "$1-$2-$3");
}

// 전화번호 포맷팅.
function fn_phoneFormat(obj) {
	if (typeof obj == "string") {
		return fn_phoneFormatString(obj);
	} else if (typeof obj == "object") {
		obj.value = fn_phoneFormatString(obj.value);
	}
}
/*
 * 현재 페이지 화면 ID반환 getCurrentScreenId(); // 화면ID 반환 @param
 * 
 * @returns {String}
 * 
 * @설 명 : 현재 페이지의 화면ID 반환
 */
function getCurrentScreenId() {
	var currentUrl = location.href; // 현재 윈도우의 문서가 위치하는 url을 String으로 반환한다.
	// location.toString() == location.href
	return currentUrl.substring(currentUrl.lastIndexOf('/') + 1, currentUrl
			.indexOf('.dev')); // '/'문자 부터 문자열의 처음까지
};
/*
 * @param str{String}
 * 
 * @return {Boolean}
 * 
 * @설 명: 공백을 가지고 있는지 확인한다.
 */
function hasSpace(str) {
	var regExp = / /;
	return regExp.test(str);
}

/**
 * 
 * 현재일자 가져오기 yyyymmdd
 * 
 * @returns {string}
 * 
 */

function fn_nowDate(gubun) {

	if (gubun == null || gubun == "undefined" || gubun == "") {

		gubun = "";

	}

	var d = new Date();

	var yy = d.getFullYear();

	var mm = d.getMonth() + 1;

	var dd = d.getDate();

	if (mm < 10)
		mm = "0" + mm;

	if (dd < 10)
		dd = "0" + dd;

	var date = yy + gubun + mm + gubun + dd;

	return date;

}

/*
 * ajax 함수 Get
 */
function getAjax(url, param, callback) {
	$.ajax({
		type : "GET",
		url : url,
		data : param,
		success : function(data, status, xr) {
			return callback(data);
		},
		error : error_run
	});
}
/*
 * ajax 함수 Post
 */
function postAjax(url, param, callback) {
	$.ajax({
		type : "POST",
		url : url,
		data : param,
		success : function(data, status, xr) {
			return callback(data);
		},
		error : error_run
	});
}

function error_run(obj, msg, statusMsg) {
	alert("오류발생 === > " + obj + "," + msg + "," + statusMsg);
}
/*
pagination
*/

function make_pagination(div_id, data, inputSize, fn){
	div_id.pagination({
	    dataSource: data,
	    pageSize: inputSize,
	    showGoInput: true,
	    showGoButton: true,
	    autoHidePrevious: true,
	    autoHideNext: true,
	    callback: function(data, pagination) {
	        // template method of yourself
	    	fn(data);
	    }
	})
}

