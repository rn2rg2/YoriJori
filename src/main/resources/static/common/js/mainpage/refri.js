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
//    e.target.appendChild(div);

    // draggable.classList.remove('hide');
}

//페이지별 개수
const totalPages = 35;
const visiblePages = 6;
const pageId = $('#pagination_div');
const make_list = function(data){
	const list_container = $('.ingredient_list_view');
	let viewHtml = "";
	for ( let i = 0 ; i< data.length; i ++ ){
		
	}
	
	console.log(data);
}
//페이지 네이션 생성
makePagination(pageId, totalPages , visiblePages);

