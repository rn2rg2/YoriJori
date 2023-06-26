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

    // draggable.classList.remove('hide');
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
	let url = "/yorijori/mypage/wish/list";
	let param = {"pageNo": page};
	postAjax(url, param, function (datas){
		console.log(datas);
		$('.wishlist_view').empty();
		$.each(datas, function(index, data) {
			make_wish_list(data);
			//dragSetting();
		});
	})
}

const make_wish_list = function( data ){
	console.log(data);
	// Create a jQuery object for the parent container
	var parentContainer = $('<div class="col-xl-6 col-lg-6 col-md-6 mb-1"></div>');

	// Iterate over the recipeWishList data
	$.each(recipeWishList, function(idx, data) {
	  // Create the recipe_info item container
	  var recipeInfoItem = $('<div class="bg-white rounded shadow-sm item recipe_info" draggable="true"></div>');

	  // Create the thumbnail image element
	  var thumbnailImg = $('<img alt="" class="img-thumbnail card-img-top"/>')
	    .attr('src', data.recipeNo.thumbnail)
	    .attr('id', data.recipeNo.recipeNo);

	  // Create the title heading element
	  var titleHeading = $('<h5></h5>')
	    .append($('<a href="#" class="text-dark recipe_name"></a>').text(data.recipeNo.name));

	  // Create the category badges
	  var categoryBadges = $('<div></div>');

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
	});

	// Append the parent container to the desired location in your HTML
	$('.wishlist_view').append(parentContainer);
	
	dragSetting();
}


