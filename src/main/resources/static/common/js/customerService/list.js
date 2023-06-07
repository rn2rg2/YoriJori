$(document).ready(function(){
    // menu 클래스 바로 하위에 있는 a 태그를 클릭했을때
    $("h6").click(function(){
        var menu = $(this).next(".hide");

        // submenu 가 화면상에 보일때는 위로 보드랍게 접고 아니면 아래로 보드랍게 펼치기
        if( menu.is(":visible") ){
            menu.slideUp();
        }else{
            menu.slideDown();
        }
    });


});
