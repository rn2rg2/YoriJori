/**
 * 냉장고 페이지 함수 모음
 */

// 전역 변수 
let i = 0 ;

function dragStart(e) {
	e.dataTransfer.setData('id', "");
	e.dataTransfer.setData('value',"");
	e.dataTransfer.setData('alt', "");
	e.dataTransfer.setData('id', "");
	console.log(e);
	e.dataTransfer.setData('id', e.target.id);
	e.dataTransfer.setData('value', e.target.attributes[4].nodeValue);
	e.dataTransfer.setData('alt', e.target.alt);
	e.dataTransfer.setData('src', e.target.src);
// setTimeout(() => {
// e.target.classList.add('hide');
// }, 0);
}

function dragEnter(e) {
    e.preventDefault();
    e.target.classList.add('drag-over');
}

function dragOver(e) {
    e.preventDefault();
    e.target.classList.add('drag-over');
}

function dragLeave(e) {
    e.target.classList.remove('drag-over');
}

function drop(e) {
    e.target.classList.remove('drag-over');
    let div = document.createElement('div')
    div.classList.add("tray_info_box");
    div.addEventListener('dragstart', dragStart);
    const id = e.dataTransfer.getData('id');
    const value = e.dataTransfer.getData('value');
    const alt = e.dataTransfer.getData('alt');
    const src = e.dataTransfer.getData('src');
    console.log(id);
    const draggable = document.getElementById(id).cloneNode(true);
    
    const target = { "matlNo" : id , "matlName" : value, "category": alt, "imgPath": src};
    
    make_refri_list(target,"drag");
    
    div.append(draggable);
    if ( e.target.classList[1] == "box" ) {
    	e.target.appendChild(div);
    }
// e.target.appendChild(div);

    // draggable.classList.remove('hide');
}



const test = function(data){
	console.log(data);
}

/* $('.selectpicker').selectpicker(); */
/*
 * getAjax("https://jsonplaceholder.typicode.com/todos", { "id" :
 * 1},function(data){ console.log(data); });
 */


const make_list = function(page){
	const pagePerCount = 6;
	const url = "/yorijori/ingredient/getListByPage";
	let param = {"page":page, "pagePerCount" : pagePerCount}
	postAjax(url, param, function (datas){
		$('#list_page').empty();
		// Create a jQuery element for the outer div
		console.log(datas);
		$.each(datas, function(index, data) {
			make_div(data);
			dragSetting();
		})
	});
}



const make_search_list = function(page){	
		let searchData = $("#inputSearchData").val();
		let pagePerCount = 6;
		let url = "/yorijori/ingredient/getListBySearchData";
		let param = {"page":page, "pagePerCount" : pagePerCount, "searchData" :searchData};
		postAjax(url, param, function (datas){
			console.log(datas);
			$('#list_page').empty();
			$.each(datas, function(index, data) {
				make_div(data);
				dragSetting();
			});
		})
}

const make_refri_list = function(data,type){
	// Create a new list item element
	console.log(data);
	var input_id = $("<input>");
	input_id.attr("type", "hidden");
	input_id.attr("name", "matlList["+[i]+"].matlNo");
	input_id.val(data.matlNo);
	
	var listItem = $("<li>").addClass("mb-5");

	// Create a div element with class "img"
	var imgDiv = $("<div>").addClass("img");

	// Create an img element with empty source and alt attributes
	if (type == "server"){
		data.imgPath = "/yorijori/data/ingredient/" + data.imgPath;
	}
	var img = $("<img>").attr("src",data.imgPath).attr("alt", "").attr("id",data.matlNo);

	// Append the img element to the img div
	imgDiv.append(img);

	// Create a div element with class "txt"
	var txtDiv = $("<div>").addClass("txt text-center");

	// Create a span element with class "tit" and text content
	var titleSpan = $("<span>").addClass("tit").text(data.matlName);

	// Create a div element with class "cooking_info"
	var infoDiv = $("<div>").addClass("cooking_info");

	// Create a span element with class "company_name" and content
	var companySpan = $("<span>").addClass("company_name").html("<em>카테고리 : </em>"+data.category);

	// Append the company span to the info div
	infoDiv.append(companySpan);

	// Append the title span and info div to the txt div
	txtDiv.append(titleSpan, infoDiv);

	// Append the anchor to the list item
	listItem.append(imgDiv, txtDiv, input_id);

	// Append the list item to a parent element (e.g., a ul with id "myList")
	  $('#refri_list').append(listItem); 
	  //개수 추가
	  i++;
	  $('#refri_count').text(i);
}

const make_div = function(data){
	var $div = $('<div>', {
		  class: 'col-xl-4 col-lg-4 col-md-4 mb-1'
		});

		// Create a jQuery element for the inner div
		var $innerDiv = $('<div>', {
		  class: 'bg-white rounded shadow-sm item recipe_info',
		  draggable: 'true'
		});

		// Create a jQuery element for the image
		var $image = $('<img>', {
		  src: "/yorijori/data/ingredient/"+data.imgPath,
		  alt: data.category,
		  class: 'img-thumbnail card-img-top',
		  id: data.matlNo,
		  value : data.matlName
		});

		// Create a jQuery element for the inner div's content
		var $contentDiv = $('<div>', {
		  class: 'p-2 text-center'
		});

		// Create a jQuery element for the heading
		var $heading = $('<h5>');

		// Create a jQuery element for the link
		var $link = $('<a>', {
		  href: '#',
		  class: 'text-dark',
		  text: data.matlName
		});

		// Append the link to the heading
		$heading.append($link);

		// Append the heading to the content div
		$contentDiv.append($heading);

		// Append the image and content div to the inner div
		$innerDiv.append($image, $contentDiv);

		// Append the inner div to the outer div
		$div.append($innerDiv);

		// Append the outer div to the desired container (e.g., body)
		$div.appendTo('#list_page');
}

const clean_refri = function(){
	$('.refri-top').empty();
	$('.refri-bottom').empty();
	$('#refri_list').empty();
	$('#refri_count').text(0);
	i = 0;
}
let imgCount = 0; 
function refriImgView(data){
	var newDiv = $("<div>").addClass("tray_info_box");

	var newImg = $("<img>").attr({
	  src: data.imgPath,
	  alt: "Condiments",
	  class: "img-thumbnail card-img-top",
	  id: data.matlNo,
	  value: data.matlName
	});

	newDiv.append(newImg);
	if ( imgCount % 2 == 0){
		$('.refri-top').append(newDiv);
	} else {
		$('.refri-bottom').append(newDiv);
	}
	imgCount++;
}

function dragSetting(){
	const items = document.querySelectorAll('.item');
	const boxes = document.querySelectorAll('.box');
		items.forEach(item => {
			item.addEventListener('dragstart', dragStart);
		;
		});
		boxes.forEach(box => {
		    box.addEventListener('dragenter', dragEnter)
		    box.addEventListener('dragover', dragOver);
		    box.addEventListener('dragleave', dragLeave);
		    box.addEventListener('drop', drop);
		});
	}


