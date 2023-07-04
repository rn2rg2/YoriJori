function MultiSelectTag (el, customs = {shadow: false, rounded:true}) {
    var element = null
    var options = null
    var customSelectContainer = null
    var wrapper = null
    var btnContainer = null
    var body = null
    var inputContainer = null
    var inputBody = null
    var input = null
    var button = null
    var drawer = null
    var ul = null
    var domParser = new DOMParser()
    init()

    function init() {
        element = document.getElementById(el)
        createElements()
        initOptions()
        enableItemSelection()
        setValues()

        button.addEventListener('click', () => {
            if(drawer.classList.contains('hidden')) {
                initOptions()
                enableItemSelection()
                drawer.classList.remove('hidden')
                input.focus()
            }
        })

        input.addEventListener('keyup', (e) => {
                initOptions(e.target.value)
                enableItemSelection()
        })

        input.addEventListener('keydown', (e) => {
            if(e.key === 'Backspace' && !e.target.value && inputContainer.childElementCount > 1) {
                const child = body.children[inputContainer.childElementCount - 2].firstChild
                const option = options.find((op) => op.value == child.dataset.value)
                option.selected = false
                removeTag(child.dataset.value)
                setValues()
            }
            
        })
        
        window.addEventListener('click', (e) => {   
            if (!customSelectContainer.contains(e.target)){
                drawer.classList.add('hidden')
            }
        });

    }

    function createElements() {
        // Create custom elements
        options = getOptions();
        element.classList.add('hidden')
        
        // .multi-select-tag
        customSelectContainer = document.createElement('div')
        customSelectContainer.classList.add('mult-select-tag')

        // .container
        wrapper = document.createElement('div')
        wrapper.classList.add('wrapper')

        // body
        body = document.createElement('div')
        body.classList.add('body')
        if(customs.shadow) {
            body.classList.add('shadow')
        }
        if(customs.rounded) {
            body.classList.add('rounded')
        }
        
        // .input-container
        inputContainer = document.createElement('div')
        inputContainer.classList.add('input-container')

        // input
        input = document.createElement('input')
        input.classList.add('input')
        input.placeholder = `${customs.placeholder || 'Search...'}`

        inputBody = document.createElement('inputBody')
        inputBody.classList.add('input-body')
        inputBody.append(input)

        body.append(inputContainer)

        // .btn-container
        btnContainer = document.createElement('div')
        btnContainer.classList.add('btn-container')

        // button
        button = document.createElement('button')
        button.type = 'button'
        btnContainer.append(button)

        const icon = domParser.parseFromString(`<svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <polyline points="18 15 12 21 6 15"></polyline></svg>`, 'image/svg+xml').documentElement
        button.append(icon)


        body.append(btnContainer)
        wrapper.append(body)

        drawer = document.createElement('div');
        drawer.classList.add(...['drawer', 'hidden'])
        if(customs.shadow) {
            drawer.classList.add('shadow')
        }
        if(customs.rounded) {
            drawer.classList.add('rounded')
        }
        drawer.append(inputBody)
        ul = document.createElement('ul');
        
        drawer.appendChild(ul)
    
        customSelectContainer.appendChild(wrapper)
        customSelectContainer.appendChild(drawer)

        // Place TailwindTagSelection after the element
        if (element.nextSibling) {
            element.parentNode.insertBefore(customSelectContainer, element.nextSibling)
        }
        else {
            element.parentNode.appendChild(customSelectContainer);
        }
    }

    function initOptions(val = null) {
        ul.innerHTML = ''
        for (var option of options) {
            if (option.selected) {
                !isTagSelected(option.value) && createTag(option)
            }
            else {
                const li = document.createElement('li')
                li.innerHTML = option.label
                li.dataset.value = option.value
                
                // For search
                if(val && option.label.toLowerCase().startsWith(val.toLowerCase())) {
                    ul.appendChild(li)
                }
                else if(!val) {
                    ul.appendChild(li)
                }
            }
        }
    }

    function createTag(option) {
        // Create and show selected item as tag
        const itemDiv = document.createElement('div');
        itemDiv.classList.add('item-container');
        const itemLabel = document.createElement('div');
        itemLabel.classList.add('item-label');
        itemLabel.innerHTML = option.label
        itemLabel.dataset.value = option.value 
        const itemClose = new DOMParser().parseFromString(`<svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="item-close-svg">
                <line x1="18" y1="6" x2="6" y2="18"></line>
                <line x1="6" y1="6" x2="18" y2="18"></line>
                </svg>`, 'image/svg+xml').documentElement
 
        itemClose.addEventListener('click', (e) => {
            const unselectOption = options.find((op) => op.value == option.value)
            unselectOption.selected = false
            removeTag(option.value)
            initOptions()
            setValues()
        })
    
        itemDiv.appendChild(itemLabel)
        itemDiv.appendChild(itemClose)
        inputContainer.append(itemDiv)
    }

    function enableItemSelection() {
        // Add click listener to the list items
        for(var li of ul.children) {
            li.addEventListener('click', (e) => {
                options.find((o) => o.value == e.target.dataset.value).selected = true
                input.value = null
                initOptions()
                setValues()
                input.focus()
            })
        }
    }

    function isTagSelected(val) {
        // If the item is already selected
        for(var child of inputContainer.children) {
            if(!child.classList.contains('input-body') && child.firstChild.dataset.value == val) {
                return true
            }
        }
        return false
    }
    function removeTag(val) {
        // Remove selected item
        for(var child of inputContainer.children) {
            if(!child.classList.contains('input-body') && child.firstChild.dataset.value == val) {
                inputContainer.removeChild(child)
            }
        }
    }
    function setValues() {
        // Update element final values
        for(var i = 0; i < options.length; i++) {
            element.options[i].selected = options[i].selected
        }
     
    }
    function getOptions() {
        // Map element options
        return [...element.options].map((op) => {
            return {
                value: op.value,
                label: op.label,
                selected: op.selected,
            }
        })
    }
}
const make_rcp_list = function(page){
	const pagePerCount = 6;
	const url = "/yorijori/recipe/myrecipe/user/"+page+"/"+pagePerCount;
	getAjaxNoParam(url, function (datas){
		$('#list_page').empty();
		// Create a jQuery element for the outer div
		console.log(datas);
		$('#results').empty();
		$.each(datas, function(index, data) {
			make_rcp_div(data);
		})
	});
}
const make_rcp_div = function(data){
	 var colDiv = $('<div class="col-sm-6 col-lg-4 mb-4"></div>');
	  var candidateListDiv = $('<div class="candidate-list candidate-grid"></div>');
	  var candidateListImageDiv = $('<div class="candidate-list-image"></div>');
	  var imageElement = $('<img class="img-fluid">').attr("src","/yorijori/data/recipethumbnail/"+data.thumbnail);
	  var candidateListDetailsDiv = $('<div class="candidate-list-details"></div>');
	  var candidateListInfoDiv = $('<div class="candidate-list-info"></div>');
	  var candidateListTitleDiv = $('<div class="candidate-list-title"></div>');
	  var h5Element = $('<h5></h5>');
	  var linkElement = $('<a></a>').attr('th:href', '@{/recipe/view/user/{recipeNo}(recipeNo=${data.recipeNo})}').text(data.name);
	  var candidateListOptionDiv = $('<div class="candidate-list-option"></div>');
	  var badge1 = $('<div class="badge badge-dark px-3 rounded-pill font-weight-normal"></div>').text("작성자 " + data.userNickname);
	  var brElement = $('<br>');
	  var badge2 = $('<div class="badge badge-success px-3 rounded-pill font-weight-normal"></div>').text(data.time);
	  var badge3 = $('<div class="badge badge-danger px-3 rounded-pill font-weight-normal">4.22</div>');
	  var candidateListFavouriteTimeDiv = $('<div class="candidate-list-favourite-time justify-content-around"></div>');
	  var ulElement = $('<ul class="list-unstyled list-inline"></ul>');
	  var li1 = $('<li class="pr-2"></li>');
	  var a1 = $('<a class="recipe_info_data order-2"></a>');
	  var i1 = $('<i class="far fa-comments pr-1"></i>').text(data.reviewlist.length);
	  var li2 = $('<li class="pr-2"></li>');
	  var a2 = $('<a class="recipe_info_data order-2"></a>');
	  var i2 = $('<i class="fa-regular fa-eye"></i>').text(data.count);

	  a1.append(i1);
	  a2.append(i2);
	  li1.append(a1);
	  li2.append(a2);
	  ulElement.append(li1);
	  ulElement.append(li2);
	  candidateListFavouriteTimeDiv.append(ulElement);
	  candidateListOptionDiv.append(badge1);
	  candidateListOptionDiv.append(brElement);
	  candidateListOptionDiv.append(badge2);
	  candidateListOptionDiv.append(badge3);
	  candidateListTitleDiv.append(h5Element);
	  candidateListTitleDiv.find('h5').append(linkElement);
	  candidateListInfoDiv.append(candidateListTitleDiv);
	  candidateListInfoDiv.append(candidateListOptionDiv);
	  candidateListDetailsDiv.append(candidateListInfoDiv);
	  candidateListDetailsDiv.append(candidateListFavouriteTimeDiv);
	  candidateListImageDiv.append(imageElement);
	  candidateListDiv.append(candidateListImageDiv);
	  candidateListDiv.append(candidateListDetailsDiv);
	  colDiv.append(candidateListDiv);

	  // Append the colDiv to an existing container
	  // For example, if you have a container with id 'myContainer', you can do:
	  $('#results').append(colDiv);
}

const make_wish_list = function(page){
	const pagePerCount = 6;
	const url = "/yorijori/recipe/mywish/user/"+page+"/"+pagePerCount;
	getAjaxNoParam(url, function (datas){
		$('#results2').empty();
		// Create a jQuery element for the outer div
		console.log(datas);
		$.each(datas, function(index, data) {
			make_wish_div(data);
		})
	});
}
const make_wish_div = function(data){
	
	var colDiv = $('<div class="col-sm-6 col-lg-4 mb-4"></div>');
	  var candidateListDiv = $('<div class="candidate-list candidate-grid"></div>');
	  var candidateListImageDiv = $('<div class="candidate-list-image"></div>');
	  var imageElement = $('<img class="img-fluid">').attr('src', "/yorijori/data/recipethumbnail/"+data.thumbnail);
	  // You can replace the commented-out line with the line below if you need the image source
	  // var imageElement = $('<img class="img-fluid" th:src="@{/images/'+${recipe.thumbnail}+'}" alt="">');
	  var candidateListDetailsDiv = $('<div class="candidate-list-details"></div>');
	  var candidateListInfoDiv = $('<div class="candidate-list-info"></div>');
	  var candidateListTitleDiv = $('<div class="candidate-list-title"></div>');
	  var h5Element = $('<h5></h5>');
	  var linkElement = $('<a></a>').attr('href', "/yorijori/recipe/view/user/"+data.recipeNo).text(data.name);
	  var candidateListOptionDiv = $('<div class="candidate-list-option"></div>');
	  var badge1 = $('<div class="badge badge-dark px-3 rounded-pill font-weight-normal"></div>').text("작성자" + data.userNickname);
	  var brElement = $('<br>');
	  var badge2 = $('<div class="badge badge-success px-3 rounded-pill font-weight-normal"></div>').text(data.time);
	  var badge3 = $('<div class="badge badge-danger px-3 rounded-pill font-weight-normal">d</div>');
	  var candidateListFavouriteTimeDiv = $('<div class="candidate-list-favourite-time justify-content-around"></div>');
	  var ulElement = $('<ul class="list-unstyled list-inline"></ul>');
	  var li1 = $('<li class="pr-2"></li>');
	  var a1 = $('<a class="recipe_info_data order-2"></a>');
	  var i1 = $('<i class="far fa-comments pr-1"></i>').text(data.reviewlist.length);
	  var li2 = $('<li class="pr-2"></li>');
	  var a2 = $('<a class="recipe_info_data order-2"></a>');
	  var i2 = $('<i class="fa-regular fa-eye"></i>').text(data.count);

	  a1.append(i1);
	  a2.append(i2);
	  li1.append(a1);
	  li2.append(a2);
	  ulElement.append(li1);
	  ulElement.append(li2);
	  candidateListFavouriteTimeDiv.append(ulElement);
	  candidateListOptionDiv.append(badge1);
	  candidateListOptionDiv.append(brElement);
	  candidateListOptionDiv.append(badge2);
	  candidateListOptionDiv.append(badge3);
	  candidateListTitleDiv.append(h5Element);
	  candidateListTitleDiv.find('h5').append(linkElement);
	  candidateListInfoDiv.append(candidateListTitleDiv);
	  candidateListInfoDiv.append(candidateListOptionDiv);
	  candidateListDetailsDiv.append(candidateListInfoDiv);
	  candidateListDetailsDiv.append(candidateListFavouriteTimeDiv);
	  candidateListImageDiv.append(imageElement);
	  candidateListDiv.append(candidateListImageDiv);
	  candidateListDiv.append(candidateListDetailsDiv);
	  colDiv.append(candidateListDiv);

	  $('#results2').append(colDiv);
}

const make_board_list = function(page){
	const pagePerCount = 10;
	const url = "/yorijori/board/ajax/list/" + page +"/"+ pagePerCount;
	getAjaxNoParam(url, function (datas){
		$('#board_tr').empty();
		// Create a jQuery element for the outer div
		console.log(datas);
		$.each(datas, function(index, data) {
			var trElement = $('<tr></tr>');
			let commNo = make_board_div(data.commNo);
			let cate =make_board_div(data.category);
			let atag = make_board_adiv(data);
			let count = make_board_div(data.count);
			trElement.append(commNo);
			trElement.append(cate);
			trElement.append(atag);
			trElement.append(count);
			$('#board_tr').append(trElement);
		})
	});
}
const make_board_div = function(data){
	  var tdElement = $('<td></td>');
	  var spanElement = $('<span></span>').text(data);
	  tdElement.append(spanElement);
	  return tdElement
}

const make_board_adiv = function(data){
		let url = "/yorijori/board/read/"+data.commNo+"/0";
		var tdElement = $('<td></td>');
	  var aElement = $('<a></a>').attr("href",url);
	  var spanElement = $('<span></span>').text(data.title);
	  aElement.append(spanElement);
	  tdElement.append(aElement);
	  return tdElement
}



//$(document).on('click', '.btn-container', function() {
//	  // Ajax 요청
//	  $.ajax({
//	    url: '/yorijori/mypage/getprefer', // 데이터를 받아올 URL
//	    type: 'POST', // HTTP 요청 메서드 (GET, POST 등)
//	    success: function(response) {
//	      // 서버로부터 응답을 성공적으로 받았을 때 실행되는 함수
//	      console.log(response); // 받아온 데이터를 출력하거나 원하는 처리를 수행합니다.
//	      
//	      // JSON 배열 데이터를 반복하여 처리하는 예시
//	     
//	    },
//	    error: function(xhr, status, error) {
//	      // 요청이 실패했을 때 실행되는 함수
//	      console.error(error); // 에러 메시지를 출력하거나 처리를 수행합니다.
//	    }
//	  });
//	});



function loadPage1(totalCount, pageSize, fn) {
	let nowPage = 1;
	let totalPages = totalCount / pageSize;

	if (totalCount % pageSize > 0) {
		totalPages++;
	}

	$('#pagination_div1').twbsPagination('destroy');
	$('#pagination_div1').remove();
	$('#pag1').html('<div id="pagination_div1" class="mb-3 pagination_div"></div>');

	$('#pagination_div1').twbsPagination({
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

function loadPage1(totalCount, pageSize, fn) {
	let nowPage = 1;
	let totalPages = totalCount / pageSize;

	if (totalCount % pageSize > 0) {
		totalPages++;
	}

	$('#pagination_div1').twbsPagination('destroy');
	$('#pagination_div1').remove();
	$('#pag1').html('<div id="pagination_div1" class="mb-3 pagination_div"></div>');

	$('#pagination_div1').twbsPagination({
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


function loadPage2(totalCount, pageSize, fn) {
	let nowPage = 1;
	let totalPages = totalCount / pageSize;

	if (totalCount % pageSize > 0) {
		totalPages++;
	}

	$('#pagination_div2').twbsPagination('destroy');
	$('#pagination_div2').remove();
	$('#pag2').html('<div id="pagination_div2" class="mb-3 pagination_div"></div>');

	$('#pagination_div2').twbsPagination({
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

function loadPage3(totalCount, pageSize, fn) {
	let nowPage = 1;
	let totalPages = totalCount / pageSize;

	if (totalCount % pageSize > 0) {
		totalPages++;
	}

	$('#pagination_div3').twbsPagination('destroy');
	$('#pagination_div3').remove();
	$('#pag3').html('<div id="pagination_div3" class="mb-3 pagination_div"></div>');

	$('#pagination_div3').twbsPagination({
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

