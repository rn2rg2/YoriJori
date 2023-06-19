/**
 * 
 */



function dragStart(e) {
	e.dataTransfer.setData('text',"");
	e.dataTransfer.setData('text', e.target.id);
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
    const id = e.dataTransfer.getData('text');
    const draggable = document.getElementById(id).cloneNode(true);
    
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
		$.each(datas, function(index, data) {
		make_div(data);
		dragSetting();
		})
	});
}



const make_search_list = function(page,flag){	
		let searchData = $("#inputSearchData").val();
		let url = "/yorijori/ingredient/getListCount";
		let param = {"searchData" :searchData};
		let pagePerCount = 6;
		postAjax(url, param , function (data){
			pagePerCount = data;
			let url2 = "/yorijori/ingredient/getListBySearchData";
			let param2 = {"page":page, "pagePerCount" : pagePerCount, "searchData" :searchData};
			postAjax(url2, param2, function (datas){
				$('#list_page').empty();
				$.each(datas, function(index, data) {
					make_div(data);
					dragSetting();
				});
			})
		});
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
		  alt: '',
		  class: 'img-thumbnail card-img-top',
		  id: data.matlNo
		});

		// Create a jQuery element for the inner div's content
		var $contentDiv = $('<div>', {
		  class: 'p-2'
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


