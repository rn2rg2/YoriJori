/*
	공통 js function 모음

 */
/**
 * 
 * 입력값이 null 인지 체크한다
 * 
 */

function isNull(input) {

	if (input.value == null || input.value == "") {

		return true;

	} else {

		return false;

	}

}

/**
 * 
 * 입력값이 스페이스 이외의 의미있는 값이 있는지 체크한다
 * 
 * if (isEmpty(form.keyword)){
 * 
 * alert('값을 입력하여주세요'); }
 * 
 */

function isEmpty(input) {
	if (input.val() == null || input.val().replace(/ /gi, "") == "") {
		return true;

	} else {

		return false;

	}

}

/*
 * sweetalert
 * 
 */
function error_alert(msg, input) {
	Swal.fire(msg, '<b style="color:red;">' + input + '</b>칸을 확인해주세요', 'error')
}

/**
 * 
 * 입력값에 특정 문자가 있는지 체크하는 로직이며
 * 
 * 특정문자를 허용하고 싶지 않을때 사용할수도 있다
 * 
 * if (containsChars(form.name, "!,*&^%$#@~;")){
 * 
 * alert("특수문자를 사용할수 없습니다"); }
 * 
 */

function containsChars(input, chars) {

	for (var i = 0; i < input.value.length; i++) {

		if (chars.indexOf(input.value.charAt(i)) != -1) {

			return true;

		}

	}

	return false;

}

/**
 * 
 * 입력값이 특정 문자만으로 되어있는지 체크하며
 * 
 * 특정문자만을 허용하려 할때 사용한다.
 * 
 * if (containsChars(form.name, "ABO")){
 * 
 * alert("혈액형 필드에는 A,B,O 문자만 사용할수 있습니다."); }
 * 
 */

function containsCharsOnly(input, chars) {

	for (var i = 0; i < input.value.length; i++) {

		if (chars.indexOf(input.value.charAt(i)) == -1) {

			return false;

		}

	}

	return true;

}

/**
 * 
 * 입력값이 알파벳인지 체크 *
 */

function isAlphabet(input) {

	var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	return containsCharsOnly(input, chars);

}

/**
 * 
 * 입력값이 알파벳 대문자인지 체크한다
 * 
 */

function isUpperCase(input) {

	var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	return containsCharsOnly(input, chars);

}

/**
 * 
 * 입력값이 알파벳 소문자인지 체크한다
 * 
 */

function isLowerCase(input) {

	var chars = "abcdefghijklmnopqrstuvwxyz";

	return containsCharsOnly(input, chars);

}

/**
 * 
 * 입력값이 숫자만 있는지 체크한다.
 * 
 */

function isNumer(input) {

	var chars = "0123456789";

	return containsCharsOnly(input, chars);

}

/**
 * 
 * 입려값이 알파벳, 숫자로 되어있는지 체크한다
 * 
 */

function isAlphaNum(input) {

	var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	return containsCharsOnly(input, chars);

}

/**
 * 
 * 입력값이 숫자, 대시"-" 로 되어있는지 체크한다
 * 
 * 전화번호나 우편번호, 계좌번호에 - 체크할때 유용하다
 * 
 */

function isNumDash(input) {

	var chars = "-0123456789";

	return containsCharsOnly(input, chars);

}

/**
 * 
 * 입력값이 숫자, 콤마',' 로 되어있는지 체크한다
 * 
 */

function isNumComma(input) {

	var chars = ",0123456789";

	return containsCharsOnly(input, chars);

}

/**
 * 
 * 입력값이 사용자가 정의한 포맷 형식인지 체크
 * 
 * 자세한 format 형식은 자바스크립트의 'reqular expression' 참고한다
 * 
 */

function isValidFormat(input, format) {

	if (input.value.search(format) != -1) {

		return true; // 올바른 포멧형식

	}

	return false;

}

/**
 * 
 * 입력값이 이메일 형식인지 체크한다
 * 
 * if (!isValidEmail(form.email)){
 * 
 * alert("올바른 이메일 주소가 아닙니다"); }
 * 
 */

function isValidEmail(input) {

	var format = /^((\w|[\-\.])+)@((\w|[\-\.])+)\.([A-Za-z]+)$/;

	return isValidFormat(input, format);

}

/**
 * 
 * 입력값이 전화번호 형식(숫자-숫자-숫자)인지 체크한다
 * 
 */

function isValidPhone(input) {

	var format = /^(\d+)-(\d+)-(\d+)$/;

	return isValidFormat(input, format);

}

/**
 * 
 * 입력값의 바이트 길이를 리턴한다.
 * 
 * if (getByteLength(form.title) > 100){
 * 
 * alert("제목은 한글 50자 (영문 100자) 이상 입력할수 없습니다"); }
 * 
 */

function getByteLength(input) {

	var byteLength = 0;

	for (var inx = 0; inx < input.value.charAt(inx); inx++) {

		var oneChar = escape(input.value.charAt(inx));

		if (oneChar.length == 1) {

			byteLength++;

		} else if (oneChar.indexOf("%u") != -1) {

			byteLength += 2;

		} else if (oneChar.indexOf("%") != -1) {

			byteLength += oneChar.length / 3;

		}

	}

	return byteLength;

}

/**
 * 
 * 입력값에서 콤마를 없앤다
 * 
 */

function removeComma(input) {
	return input.value.replace(/,/gi, "");
}

/**
 * 
 * 선택된 라디오버튼이 있는지 체크한다
 * 
 */

function hasCheckedRadio(input) {
	if (input.length > 1) {

		for (var inx = 0; inx < input.length; inx++) {
			if (input[inx].checked)
				return true;
		}

	} else {
		if (input.checked)
			return true;
	}
	return false;
}

/**
 * 
 * 선택된 체크박스가 있는지 체크
 * 
 */

function hasCheckedBox(input) {
	return hasCheckedRadio(input);
}

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
 * 사이 공백 제거 함수
 */
function replaceSpace(data) {
	return data.replace(/ /g, "");
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
function getAjaxNoParam(url, callback) {
	$.ajax({
		type : "GET",
		url : url,
		success : function(data, status, xr) {
			return callback(data);
		},
		error : error_run
	});
}
/*
 * ajax 함수 Post
 */
function postAjaxNoParam(url, callback) {
	$.ajax({
		type : "POST",
		url : url,
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
 * pagination makePagination(pagination 띄울 페이지, 전체 페이지 수 ( 전체 게시물 / 한화면 게시물 ),
 * 현재페이지, 실행할 함수)
 */

function makePagination(div_id, totalPages, visiblePages, currentPage, fn) {

	div_id.twbsPagination({
		totalPages : totalPages,
		// 페이지당 보이는 글의수는
		visiblePages : visiblePages,
		// 로딩시 뜨는 페이지
		startPage : currentPage,
		// " « "라는 문자열로 최신글 방향을 표시
		first : '<span sris-hidden="true">«</span>',
		// " » "라는 문자열로 마지막글 방향을 표시
		last : '<span sris-hidden="true">»</span>',

		prev : "이전",
		next : "다음",
		initiateStartPageClick : false, // onPageClick 자동호출 방지
		onPageClick : function(event, page) {
			console.log(page);
			fn(page - 1);
		}
	});
}

function makePageAjax(div_id, totalPages, visiblePages, fn) {
	// Destroy existing pagination if it exists
	$('#pagination_div').twbsPagination('destroy');
	$('#pagination_div').remove();
	$('#pag').html('<div id="pagination_div" class="mb-3"></div>');

	div_id.twbsPagination({
		totalPages : totalPages,
		// 페이지당 보이는 글의수는
		visiblePages : visiblePages,
		// " « "라는 문자열로 최신글 방향을 표시
		first : '<span sris-hidden="true">«</span>',
		// " » "라는 문자열로 마지막글 방향을 표시
		last : '<span sris-hidden="true">»</span>',

		prev : "이전",
		next : "다음",
		initiateStartPageClick : false, // onPageClick 자동호출 방지
		onPageClick : function(event, page) {
			fn(page - 1);
		}
	});
}

function loadPage(totalCount, pageSize, fn) {
	let nowPage = 1;
	let totalPages = totalCount / pageSize;

	if (totalCount % pageSize > 0) {
		totalPages++;
	}

	$('#pagination_div').twbsPagination('destroy');
	$('#pagination_div').remove();
	$('#pag').html('<div id="pagination_div" class="mb-3"></div>');

	$('#pagination_div').twbsPagination({
		totalPages : totalPages,
		visiblePages : pageSize,
		first : '<span sris-hidden="true">«</span>',
		prev : "이전",
		next : "다음",
		last : '<span sris-hidden="true">»</span>',
		initiateStartPageClick : false, // onPageClick 자동호출 방지
		onPageClick : function(event, page) {
			nowPage = page;
			fn(page - 1);
		}
	});
}
