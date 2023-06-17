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
	console.log(page);
	const url = "/yorijori/ingredient/getListByPage";
	let param = {"page":page, "pagePerCount" : pagePerCount}
	postAjax(url, param, function (data){
		console.log(data);
		
	})
	
}

const clean_refri = function(){
	$('.refri-top').empty();
	$('.refri-bottom').empty();
}


/*
 * 실행시 load되는 곳
 */

