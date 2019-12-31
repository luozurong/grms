$(function(){
});


function allMsg(msg,status,index){
	$("#msg"+index).hide();
	$("#main"+index).text(msg);
	
	$("#msg"+index).show();
	$("#goto"+index).click(function(){
		goToOrder(status);
		$("#msg"+index).hide();
	});
	
	//$("#mainFrame").attr("src","");
}


/***********退出*************/
function logout(){
	window.location.href="/grms/nologin";
}


hightPic();
$(".menuUl li").click(function(){
	$(this).find(".menuItem").addClass("open");
	$(this).siblings().find(".menuItem").removeClass("open");
	hightPic();
});
//个人信息跳转
function setPerson(){
	$("#mainFrame").attr("src","/ums/personalAction!goPersonal.html");
}
//点击账号名称，跳转到个人设置
$(".admin").click(function(){
    menuItemEach("系统设置");
    $(".menuItem").each(function(){
        if($(this).text() == "系统设置"){
            $(this).next().find(".submenuItem").each(function(){
                if($(this).text() == "个人设置"){
                    $(this).addClass("submenuItemActive").siblings().removeClass("submenuItemActive");
                }
            });
        }
    });
});
//工单信息下拉
$(".mesNum-pop div").click(function(){
	if($(this).is(".workOrderMsg")){
		menuItemEach("工单管理");
	}
	if($(this).is(".set")){
		menuItemEach("系统设置");
		$(".menuItem").each(function(){
			if($(this).text() == "系统设置"){
				$(this).next().find(".submenuItem").each(function(){
					if($(this).text() == "基础设置"){
						$(this).addClass("submenuItemActive").siblings().removeClass("submenuItemActive");
					}
				});
			}
		});
	}
});


function menuItemEach(msg){
	$(".menuItem").each(function(){
		$(this).removeClass("open");
		hightPic();
		if($(this).text() == msg){
			$(this).addClass("open");
			hightPic();
		}
	});
}
	
//二级菜单显示
$('.submenuItem').click(function(){
	$('.submenuItem').removeClass('submenuItemActive');
	$(this).addClass('submenuItemActive');
});

//一级菜单显示高亮图标，菜单下拉，上拉
function hightPic(){
	//隐藏下拉图标
	$(".menuItem").each(function(){
		if($(this).siblings('.submenu').length == 0){
			$(this).siblings('.icon').hide();
		}
		if($(this).hasClass("open")){
			$(this).next().slideDown(120);//菜单显示
			$(this).siblings(".logo-img-black").hide();//高亮图片
			$(this).siblings(".logo-img-white").show();
			$(this).prev().addClass("left-border-check");//选择竖条
			$(this).siblings(".icon").css('background-image','url(common/images/nav_ic_down1.png)');//下拉上来icon
		}else{
			$(this).next().slideUp(20);//菜单收起
			$(this).siblings(".logo-img-black").show();
			$(this).siblings(".logo-img-white").hide();
			$(this).prev().removeClass("left-border-check");
			$(this).siblings(".icon").css('background-image','url(common/images/nav_ic_more1.png)');
		}
	});
}

//工单消息的显示
$(".mesIcon,.mesNum-pop").hover(function(){
	$(".mesNum-pop").show();
},function(){
	$(".mesNum-pop").hide();
});

/*********提示框***********/
$("#img1").click(function(){
	$("#msg1").hide();
});
$("#img2").click(function(){
	$("#msg2").hide();
});
$("#img3").click(function(){
	$("#msg3").hide();
});
$("#img4").click(function(){
	$("#msg4").hide();
});
$("#img5").click(function(){
	$("#msg5").hide();
});
$("#img6").click(function(){
	$("#msg6").hide();
});
$("#img7").click(function(){
	$("#msg7").hide();
});
$("#img8").click(function(){
	$("#msg8").hide();
});
$("#img9").click(function(){
	$("#msg9").hide();
});

function leftshoworhide(e){
	var id = e.attr('id');
	if(id=='zhankai'){
		e.attr('id','bihe');
		$('#cc').css('padding-left','0px');
		$('#mask-left').css('margin-left','-200px');
	}else{
		e.attr('id','zhankai');
		$('#cc').css('padding-left','200px');
		$('#mask-left').css('margin-left','0px');
	}
}


