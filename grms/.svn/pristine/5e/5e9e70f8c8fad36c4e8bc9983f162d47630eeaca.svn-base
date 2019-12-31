//页面布局尺寸调整
autoHeightAndWidth();
function autoHeightAndWidth(){
	var bodyWidth = $("body").width();
	var bodyHeight = $("body").height();
	var headerHeight = $(".head").height();
	var leftWidth = $(".left").width();
	$(".left").css("height",bodyHeight-headerHeight)
	$(".center").css("width",bodyWidth-leftWidth <= 1150 ? 1150 : bodyWidth-leftWidth);
	$(".center").css("height",bodyHeight-headerHeight);
	$(".center").show();
}
window.onresize = function(){
	autoHeightAndWidth();
};


//左侧菜单栏效果
/*$(".menu-item-word").click(function(){
	var that = this;
	//图片icon颜色变换
	var src = $(this).find('.menu-item-pic').attr("src");
	var imgUrl = src.replace('02','01');
	$(this).find('.menu-item-pic').attr("src",imgUrl);
	for(var i = 0 ; i < $(this).parent().siblings().length; i++){
		var srcSiblings = $(this).parent().siblings().eq(i).find(".menu-item-pic").attr("src");
		var imgUrlSiblings = srcSiblings.replace('01','02');
		$(this).parent().siblings().eq(i).find(".menu-item-pic").attr("src",imgUrlSiblings);
	}
	//判断是否是有子模块
	if($(this).next().find('div').length == 0){
		var pageUrl = $(that).find(".menu-item-name a").attr("href");
		console.log(pageUrl);
		$(".center iframe").attr("src",pageUrl);
		setTimeout(function(){
			//展示子类动画、小三角
			$(that).parent().addClass("menu-item-active").find(".menu-item-link").slideDown(300);
			$(that).parent().siblings().removeClass("menu-item-active").find(".menu-item-link").slideUp(300);
			$(that).parent().siblings().find(".menu-item-more").attr("src","../common/images/nav_ic_more1.png");
			$(that).parent().find(".menu-item-more").attr("src","../common/images/nav_ic_down1.png")
		},300)
		return false;
	}else{
		if(!$(this).parent().hasClass("menu-item-active")){
			$(this).parent().addClass("menu-item-active").find(".menu-item-link").slideDown(300);
			$(this).parent().siblings().removeClass("menu-item-active").find(".menu-item-link").slideUp(300);
			$(this).parent().siblings().find(".menu-item-more").attr("src","../common/images/nav_ic_more1.png");
			$(this).parent().find(".menu-item-more").attr("src","../common/images/nav_ic_down1.png")
		}else{
			var src = $(this).find('.menu-item-pic').attr("src");
			var imgUrl = src.replace('01','02');
			$(this).find('.menu-item-pic').attr("src",imgUrl);
			$(this).parent().find(".menu-item-link").slideUp(300);
			setTimeout(function(){
				$(that).parent().removeClass("menu-item-active")
				$(that).parent().find(".menu-item-more").attr("src","../common/images/nav_ic_more1.png")
			},300);
			$(this).parent().siblings().removeClass("menu-item-active");
		}
	}
});*/

$(".menu-item-word").click(function(){
	if($(this).hasClass("menu-item-active")){
		$(this).removeClass("menu-item-active");
	}else{
		$(this).addClass("menu-item-active");
		$(this).parent().siblings().find(".menu-item-word").removeClass("menu-item-active");
	}
	menuItemActive();
});

function menuItemActive(){
	for(var i = 0; i < $(".menu-item-word").length; i++){
		if($(".menu-item-word").eq(i).hasClass("menu-item-active")){
			$(".menu-item-word").eq(i).next().slideDown(300);
			$(".menu-item-word").eq(i).next().addClass("menu-link-active");
			var src = $(".menu-item-word").eq(i).find('.menu-item-pic').attr("src");
			var imgUrl = src.replace('02','01');
			$(".menu-item-word").eq(i).find('.menu-item-pic').attr("src",imgUrl);
			$(".menu-item-word").eq(i).find(".menu-item-more").attr("src","../common/images/nav_ic_down1.png")
		}
		if(!$(".menu-item-word").eq(i).hasClass("menu-item-active")){
			$(".menu-item-word").eq(i).next().slideUp(300);
			$(".menu-item-word").eq(i).next().removeClass("menu-link-active");
			var src = $(".menu-item-word").eq(i).find('.menu-item-pic').attr("src");
			var imgUrl = src.replace('01','02');
			$(".menu-item-word").eq(i).find('.menu-item-pic').attr("src",imgUrl);
			$(".menu-item-word").eq(i).find(".menu-item-more").attr("src","../common/images/nav_ic_more1.png")
		}
	}
}
menuItemActive();

//获取页面数据
$(".menu-item-link div a").click(function(){
	$(this).parent().addClass('link-active');
	$(this).parent().siblings().removeClass('link-active');
	$(this).parent().parent().parent().parent().siblings().find(".menu-item-link div").removeClass('link-active');
	var pageUrl = $(this).attr("href");
	$(".center iframe").attr("src",pageUrl);
	return false;
});
