/**
 * 
 */
let dropdata = {};

function dragStart(e) {
	e.dataTransfer.setData('text',"");
	e.dataTransfer.setData('text', e.target.id);
	//console.log(e.target.attributes[4].nodeValue);
	let data_drag = e.target.attributes[4].nodeValue;
	
	jsonData = JSON.parse(data_drag);
	
	dropdata['recipeNo'] = jsonData.recipeNo.recipeNo;
	dropdata['recipeName'] = jsonData.recipeNo.name;
	dropdata['purpose'] = jsonData.recipeNo.categorylist[0].categoryNo.name;
	dropdata['country'] = jsonData.recipeNo.categorylist[1].categoryNo.name;
	dropdata['type'] = jsonData.recipeNo.categorylist[2].categoryNo.name;
	dropdata['food'] = jsonData.recipeNo.categorylist[3].categoryNo.name;
	
	
	
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
    e.target.innerHTML = "";
    let div = document.createElement('div')
    div.classList.add("tray_info_box");
    div.addEventListener('dragstart', dragStart);
    
    const id = e.dataTransfer.getData('text');
    const draggable = document.getElementById(id).cloneNode(true);
    
    div.append(draggable);
    console.log(e.target.classList[2]);
    if ( e.target.classList[2] == "box" ) {
    	e.target.appendChild(div);
    }
//    e.target.appendChild(div);

    // draggable.classList.remove('hide');\
    
//    dropdata['recipeNo'] 
//	dropdata['purpose'] 
//	dropdata['country'] 
//	dropdata['type'] 
//	dropdata['food']
    const dropId = dropdata['recipeNo']
    console.log(dropId)
    make_card_body(dropId);
    
}

const clearTray = function (){
	$('.box').empty();
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

const get_wish_data = function(page){	
	let pagePerCount = 4;
	let url = "/yorijori/mypage/wish/list/" + page + "/" + pagePerCount;
	$.ajax({
		type : "GET",
		url : url,
		success : function(datas, status, xr) {
			//console.log(datas);
			$('.wishlist_view').empty();
			make_wish_list(datas);
//			$.each(datas, function(index, data) {
//				//dragSetting();
//			});
		},
		error : error_run
	});
}

const make_wish_list = function( datas ){
	console.log(datas);
	// Create a jQuery object for the parent container

	// Iterate over the recipeWishList data
	$.each(datas, function(idx, data) {
		console.log(data);
	var parentContainer = $('<div class="col-xl-6 col-lg-6 col-md-6 mb-5"></div>');
	  // Create the recipe_info item container
	  var recipeInfoItem = $('<div class="bg-white rounded shadow-sm item recipe_info" draggable="true"></div>');

	  // Create the thumbnail image element
	  var thumbnailImg = $('<img alt="" class="img-thumbnail card-img-top"/>')
	    .attr('src', "/yorijori/data/recipethumbnail/"+data.recipeNo.thumbnail)
	    .attr('id', data.recipeNo.recipeNo)
	    .attr('value', JSON.stringify(data));

	  // Create the title heading element
	  var titleHeading = $('<h5></h5>')
	    .append($('<a href="#" class="text-dark recipe_name"></a>').text(data.recipeNo.name));

	  // Create the category badges
	  var categoryBadges = $('<div style="text-align:center;"></div>');

	  $.each(data.recipeNo.categorylist, function(index, category) {
	    var categoryBadge = $('<div class="badge badge-danger px-3 rounded-pill"></div>')
	      .append($('<p class="small text-light mb-0"></p>').text(category.categoryNo.name));

	    categoryBadges.append(categoryBadge);
	  });

	  // Append all the elements to the recipe_info item container
	  recipeInfoItem.append(thumbnailImg);
	  recipeInfoItem.append($('<div class="p-2"></div>')
	    .append(titleHeading)
	    .append(categoryBadges));

	  // Append the recipe_info item container to the parent container
	  parentContainer.append(recipeInfoItem);
	  $('.wishlist_view').append(parentContainer);
	});

	// Append the parent container to the desired location in your HTML
	
	
	dragSetting();
}
const make_card_body = function (dropId){
		dropId = "dropId"+dropId
	  var colDiv = $('<div>').addClass('col-md-6');
	  var cardBodyDiv = $('<div>').addClass('py-0 card-body mb-1').attr('id', dropId);

	  colDiv.append(cardBodyDiv);
	  $('.ingredients-result').append(colDiv);
	  
	make_card_rcp_name(dropdata['recipeName'], dropId );
	make_my_tray_list("음식용도", dropdata['purpose'],dropId);
	make_my_tray_list("국가별", dropdata['country'] ,dropId);
	make_my_tray_list('조리법', dropdata['type'],dropId );
	make_my_tray_list('식품별', dropdata['food'] ,dropId);
}
const make_card_rcp_name = function(data, dropId){
	var div = $('<div>').addClass('position-relative align-items-center py-2 border-bottom border-200 row');
	var col = $('<div>').addClass('py-1 col');
	var dFlex = $('<div>').addClass('d-flex align-items-center');
	var h4 = $('<h4>').addClass('d-flex mb-0').attr('align', 'center');
	var a = $('<a>').addClass('text-800 stretched-link').attr('href', '#!').text(data);

	h4.append(a);
	dFlex.append(h4);
	col.append(dFlex);
	div.append(col);
	
	$("#"+dropId).append(div);
}




const make_my_tray_list = function(type, data, dropId){
 	var div = $('<div>').addClass('position-relative align-items-center py-2 border-bottom border-200 row');
	  var col = $('<div>').addClass('py-1 col');
	  var dFlex = $('<div>').addClass('d-flex align-items-center');
	  var h6 = $('<h6>').addClass('d-flex mb-0').attr('align', 'center');
	  var a = $('<a>').addClass('text-800 stretched-link').attr('href', '#!').text(type);
	  var span = $('<span>').addClass('ms-2 text-primary badge rounded-pill bg-200').text(data);

	  h6.append(a, span);
	  dFlex.append(h6);
	  col.append(dFlex);
	  div.append(col);

	  $("#"+dropId).append(div);
}


