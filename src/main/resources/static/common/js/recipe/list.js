/**
 * recipe list js
 */

/*
 * 화면 만들어주는 list 함수
 * 서버 레시피 / 유저레시피 순
 */
const makeList = function(data, index) {

	// Create a container div
	var containerDiv = $('<div>').addClass('col-sm-6 col-lg-4 mb-4');

	// Create candidate-list div
	var candidateListDiv = $('<div>').addClass('candidate-list candidate-grid');

	// Create candidate-list-image div
	var candidateListImageDiv = $('<div>').addClass('candidate-list-image');
	var image = $('<img>').addClass('img-fluid').attr('src', data.attFileNoMain).attr('alt', '');
	candidateListImageDiv.append(image);

	// Create candidate-list-details div
	var candidateListDetailsDiv = $('<div>').addClass('candidate-list-details');

	// Create candidate-list-info div
	var candidateListInfoDiv = $('<div>').addClass('candidate-list-info');

	// Create candidate-list-title div
	var candidateListTitleDiv = $('<div>').addClass('candidate-list-title');
	var title = $('<h5>').append($('<a>').attr('href', '/yorijori/recipe/view/server/' + data.rcpSeq).text(data.rcpNm));
	candidateListTitleDiv.append(title);

	// Create candidate-list-option div
	var candidateListOptionDiv = $('<div>').addClass('candidate-list-option');
	var badge1 = $('<div>').addClass('badge badge-danger px-3 rounded-pill font-weight-normal').text(data.rcpPat2);
	var ul = $('<ul>').addClass('list-unstyled');
	candidateListOptionDiv.append(badge1);
	candidateListOptionDiv.append($('<br>'));
	candidateListOptionDiv.append(ul);

	candidateListInfoDiv.append(candidateListTitleDiv);
	candidateListInfoDiv.append(candidateListOptionDiv);

	// Create candidate-list-favourite-time div
	var candidateListFavouriteTimeDiv = $('<div>').addClass('candidate-list-favourite-time');
	if (data.reviewList.length > 0) {
		var favouriteLink = $('<a>').addClass('candidate-list-favourite order-2 red_heart').attr('onclick', 'clickHeart(addHeart)').html('<i class="far fa-heart"></i>');
	} else {
		var favouriteLink = $('<a>').addClass('candidate-list-favourite order-2').attr('onclick', 'clickHeart(addHeart)').html('<i class="far fa-heart"></i>');
	}
	var timeSpan = $('<span>').addClass('candidate-list-time order-1').html('<i class="far fa-solid fa-utensils pr-1"></i>' + "조리방법 : " + data.recipeDes);
	candidateListFavouriteTimeDiv.append(favouriteLink);
	candidateListFavouriteTimeDiv.append(timeSpan);

	candidateListDetailsDiv.append(candidateListInfoDiv);
	candidateListDetailsDiv.append(candidateListFavouriteTimeDiv);

	candidateListDiv.append(candidateListImageDiv);
	candidateListDiv.append(candidateListDetailsDiv);

	containerDiv.append(candidateListDiv);
	$('.recipe_content').append(containerDiv);
}

/*const makeUserRecipeList = function (data, index){
	  var candidateList = $('<div>').addClass('col-sm-6 col-lg-4 mb-4');
	  var candidateGrid = $('<div>').addClass('candidate-list candidate-grid');
	  var candidateImage = $('<div>').addClass('candidate-list-image');
	  var image = $('<img>').addClass('img-fluid').attr('src', 'https://recipe1.ezmember.co.kr/cache/recipe/2021/12/13/9ac22b498a2442f29302df533a8731711.jpg').attr('alt', '');
	  var candidateDetails = $('<div>').addClass('candidate-list-details');
	  var candidateInfo = $('<div>').addClass('candidate-list-info');
	  var candidateTitle = $('<div>').addClass('candidate-list-title');
	  var title = $('<h5>').append($('<a>').attr('href', '/yorijori/recipe/view').text(data.name));
	  var candidateOption = $('<div>').addClass('candidate-list-option');
	  var badge1 = $('<div>').addClass('badge badge-dark px-3 rounded-pill font-weight-normal').text(data.userId);
	  var lineBreak = $('<br>');
	  var badge2 = $('<div>').addClass('badge badge-success px-3 rounded-pill font-weight-normal').text(data.time);
	  var badge3 = $('<div>').addClass('badge badge-danger px-3 rounded-pill font-weight-normal').text('4.22');
	  var hr = $('<hr>');
	  var meta = $('<div>').addClass('meta meta-style2');
	  var ul = $('<ul>').addClass('list-unstyled');
	  var li1 = $('<li>').append($('<i>').addClass('fa-solid fa-eye')).append(data.count);
	  var li2 = $('<li>').append($('<i>').addClass('fa-solid fa-thumbs-up')).append('353');
	  var li3 = $('<li>').append($('<a>').attr('href', '#').append($('<i>').addClass('fas fa-comments'))).append(' 58');
	  var favouriteTime = $('<div>').addClass('candidate-list-favourite-time');
	  var favourite = $('<a>').addClass('candidate-list-favourite order-2').click(clickHeart).append($('<i>').addClass('far fa-heart'));
	  var time = $('<span>').addClass('candidate-list-time order-1').append($('<i>').addClass('far fa-clock pr-1')).append(data.date);

	  ul.append(li1, li2, li3);
	  meta.append(ul);
	  candidateOption.append(badge1, lineBreak, badge2, badge3, hr, meta);
	  candidateTitle.append(title);
	  candidateInfo.append(candidateTitle, candidateOption);
	  candidateDetails.append(candidateInfo);
	  favouriteTime.append(favourite, time);
	  candidateDetails.append(favouriteTime);
	  candidateImage.append(image);
	  candidateGrid.append(candidateImage, candidateDetails);
	  candidateList.append(candidateGrid);

	  $('.recipe_content').append(candidateList);
}*/
const getServiceRecipeByDB = function(pNumber){
	let url = "/yorijori/recipe/list/server/" + pNumber;
	$.ajax({
		url : url,
		type : "get",
		success : function(data){
			console.log(data);
			$('.recipe_content').empty();
			$.each(data, function(index, item) {
				makeList(item, index);
			}) 
		}
		
	})
}
const getListCount = function(){
	let url = "/yorijori/recipe/list/servercount"
	let totalCount = 0;
	let pageSize = 9;
	$.ajax({
		url : url,
		type : "get",
		success : function(data){
			console.log(data);

			totalCount = data;
			loadPage(totalCount, pageSize, getServiceRecipeByDB);
		}
		
	})
	
}


const move_list = function(page) {
	let url = "/yorijori/recipe/list"
	window.location.href = url + "/user/" + page;
}
const move_list_api = function(page) {
	let url = "/yorijori/recipe/list"
	window.location.href = url + "/server/" + page;
}

/*
	전체 클릭한 필터 종류
*/
function filterOn() {
	checkedValue();
}

function checkedValue() {
	var checkboxes = document.querySelectorAll('input[type="checkbox"]');
	var checkedValues = [];

	for (var i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i].checked) {
			checkedValues.push(checkboxes[i].id);
		}
	}

	console.log(checkedValues);
}

function handleCheckboxChange(checkbox) {
	var checkboxes = $('.dateposted');
	for (var i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i] !== checkbox) {
			checkboxes[i].checked = false;
		}
	}
}

function handlespecialism(checkbox) {
	var checkboxes = $('.specialism');
	for (var i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i] !== checkbox) {
			checkboxes[i].checked = false;
		}
	}
}

function handleMenutype(checkbox) {
	var checkboxes = $('.menutype');
	for (var i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i] !== checkbox) {
			checkboxes[i].checked = false;
		}
	}
}

function handleExperience(checkbox) {
	var checkboxes = $('.experience');
	for (var i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i] !== checkbox) {
			checkboxes[i].checked = false;
		}
	}
}

function handleCheckboxChange(checkbox) {
	var checkboxes = $('.likeCount');
	for (var i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i] !== checkbox) {
			checkboxes[i].checked = false;
		}
	}
}