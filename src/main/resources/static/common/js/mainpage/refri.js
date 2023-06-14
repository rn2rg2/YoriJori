/**
 * 
 */
const items = document.querySelectorAll('.item');
const boxes = document.querySelectorAll('.box');
items.forEach(item => {
	item.addEventListener('dragstart', dragStart);
});

boxes.forEach(box => {
    box.addEventListener('dragenter', dragEnter)
    box.addEventListener('dragover', dragOver);
    box.addEventListener('dragleave', dragLeave);
    box.addEventListener('drop', drop);
});

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
    console.log(e);
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
	console.log(page);
	let param = {"page":page, "pagePerCount" : pagePerCount}
	getAjax(url, param, function (data){
		//console.log(data);
		
	})
	
}


/*
 * 실행시 load되는 곳
 */
$(document).ready(function(){
	// 재료 전체 수
	var totalPages = $('#countVal').val();
	// 페이지별 개수
	const pageId = $('#pagination_div');
	const visiblePages = 6;
	
	/*
	 * 페이지 네이션 생성
	 */
	makePagination(pageId, totalPages , visiblePages, make_list);
	
	
	});

