$(function(){
	
	isLogin();
	menuItemClick();
	submenuItemClick();
})
//一级菜单点击
function menuItemClick(){
	$('.menuItem').click(function(){
		slide($(this));//二级菜单缩进
		
		$(".menuItem").each(function(index) {
			if($(this).hasClass('menuItemActive')){
				$(this).parent().children().siblings(".logo-img-black").css("display","block");
		    	$(this).parent().children().siblings(".logo-img-white").css("display","none");
			}
		})
		
			
		$('.menuItem').removeClass('menuItemActive');
		$(this).addClass('menuItemActive');
		
		
		//console.log($(this).parent().children().siblings(".logo-img-black").children().attr('src'));
		
		$(this).parent().children().siblings(".logo-img-black").css("display","none");
    	$(this).parent().children().siblings(".logo-img-white").css("display","block");
    	
		if($(this).siblings('.submenu').css('display') =='none'){
			$(this).siblings('.icon').css('background-image','url(common/images/nav_ic_down.png)');
		}else{
			$(this).siblings('.icon').css('background-image','url(common/images/nav_ic_more.png)');
			
		}
		$(this).siblings('.submenu').slideToggle(120);
	});	
};
//二级菜单点击
function submenuItemClick(){
	$('.submenuItem').click(function(){
		$('.submenuItem').removeClass('submenuItemActive');
		$(this).addClass('submenuItemActive');
	})	
};
//二级菜单缩进方法
function slide(o){
	var submenu =o.parent('li').siblings('li').find('.submenu');
	var icon = o.parent('li').siblings('li').find('.icon'); 
	icon.css('background-image','url(common/images/nav_ic_more.png)');
	submenu.slideUp(120);
}



function logout() {
	$.post('/ums/userAction!logout.html', function() {
		window.location.href="login.jsp";
	});
}


$(".menuItem").mouseover(function (e) {
	if($(this).hasClass('menuItemActive'))
		return false;
    var el = e||window.event;
    var s = el.fromElement || el.relatedTarget;
    if (!this.contains(s)) {
    	//console.log($(this).parent().children().siblings(".logo-img-black").children().attr('src'));
    	$(this).parent().children().siblings(".logo-img-black").css("display","none");
    	$(this).parent().children().siblings(".logo-img-white").css("display","block");
    }             
});

$(".menuItem").mouseout(function (e) {
	if($(this).hasClass('menuItemActive'))
		return false;
    var el = e||window.event;
    var s = el.fromElement || el.relatedTarget;
    if (!this.contains(s)) { 
    	$(this).parent().children().siblings(".logo-img-black").css("display","block");
    	$(this).parent().children().siblings(".logo-img-white").css("display","none");
    } 
});
