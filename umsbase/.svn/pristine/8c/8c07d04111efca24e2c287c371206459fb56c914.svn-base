function selectedCheckboxAll(name){
	$("input[name='"+name+"']:checkbox:checked").each(function(inx,value){
		$(value).attr("checked",false);
	});
}

function selectedCheckbox(name){
	$("input[name='"+name+"All']:checkbox:checked").each(function(inx,value){
		$(value).attr("checked",false);
	});
}

function getSelectceds(name){
	var types = "";
	$("input[name='"+name+"']:checkbox:checked").each(function(inx,value){
		types=types+$(value).val()+",";
	});
	if(types!=""){
		types = types.substring(0,types.length-1);
	}
	return types;
}

/**
 * Created by dell on 2017/1/9.
 */
$(function(){
	$(document).ready(function(){
		fitCoulms();
	});
	$(window).resize(function(){
		fitCoulms();
	});	

//	initMessageNum();
})

//表格宽度自适应
function fitCoulms(){
//    $('#dg,#dg1,#dg2').datagrid('resize');
	var width = document.body.offsetWidth;

	if(width>1120){
		$('#dg').datagrid({
			fitColumns:true
		})
	}else{
		$('#dg').datagrid({
			fitColumns:false
		})
	}
}

//日期控件斜杠格式化
function formatDate(date){
    if( "" != date ){
        var year = date.getFullYear();
        var month = date.getMonth()+1;
        var day = date.getDate();
        if( month < 10 ){
            month = '0' + month;
        }
        if( day < 10 ){
            day = '0' + day;
        }
        return year+'/'+month+'/'+day;
    }else{
        return "";
    }
}

/*  只返回目标节点的第一级子节点，具体的用法和getChildren方法是一样的 */
$.extend($.fn.tree.methods,{
    getLeafChildren:function(jq, params){
        var nodes = [];
        $(params).next().children().children("div.tree-node").each(function(){
            nodes.push($(jq[0]).tree('getNode',this));
        });
        return nodes;
    }
});

//初始化消息的数量
function initMessageNum(){
	$.ajax({
	 	   type: "post",
	 	   url:'/adms/messagesAction!totalCount.html',
	 	   dataType:'JSON',
	 	   cache:false,
	 	   success:function(msg){
	 		   if(msg.result=="success"){
                   if(msg.success>99){
                       $("#mesNum").html("99+");
                   }else{
                       $("#mesNum").html(msg.success);
                   }
	 		   }else{
	 			  $("#mesNum").html("0"); 
	 		   }
	 	   }
	    }); 
}


//跳转到消息管理界面
function goMessage(){
	$('[name="mainFrame"]').attr("src","messagesAction!goList.html");
}

/*用户名、密码等等input输入框:取得焦点时，边框高亮显示; 有输入内容时，显示右边叉号；无内容则隐藏叉号 */
function focusColorAndShowOrHideX(inputId,deleteId){
    $(inputId).on("focus",function(){
        $(this).addClass("focus-color");
        $(this).keyup(function(){
            var deleteX = $(deleteId);
            var inputValue = $(this).val();
            var inputValueLength = inputValue.length;

            if(inputValueLength > 0){
                deleteX.show();
            }else{
                deleteX.hide();
            }
        });
    });
    $(inputId).on("blur",function(){
        $(this).removeClass("focus-color");
    });
    $(deleteId).on("click",function(){
        $(this).hide();
    });

}

/*外层 遮罩显示*/
function wrapMaskShow(){
    var maskTop = window.top.$("#mask-top");
    var maskLeft = window.top.$("#mask-left");
    maskTop.show();
    maskLeft.show();
    $(document.body).css({"overflow":"hidden"});
    $('body').css('overflow','hidden');//禁止滚动
}

/*外层 遮罩隐藏*/
function wrapMaskHide(){
    var maskTop = window.top.$("#mask-top");
    var maskLeft = window.top.$("#mask-left");
    maskTop.hide();
    maskLeft.hide();
    $('body').css('overflow','auto');//恢复滚动
}


function warnMask(warnTips){
    parent.$.messager.alert({
        title:'消息提醒',
        //icon:"info",
        closable:true,
        msg:warnTips,
        draggable:false,
        fn:function(){

        }
    });
}

function infoMask(warnTips){
	parent.$.messager.alert({
		title:'消息提醒',
		msg:warnTips,
		draggable:false,
        fn:function(){
        	//wrapMaskHide();
        }
	});
	
}

function infoMask(warnTips){
	parent.$.messager.alert({
		title:'消息提醒',
		msg:warnTips,
		draggable:false,
        fn:function(){
        	//wrapMaskHide();
        }
	});
	
}
function infoMaskURL(warnTips,url){
	parent.$.messager.alert({
		title:'消息提醒',
		msg:warnTips,
		draggable:false,
        fn:function(){
        	 $("iframe[name='mainFrame']", window.parent.document).attr("src",url);
        }
	});
	
}
function successMask(warnTips,url){
	/*外层 遮罩显示*/
//  wrapMaskShow();
	parent.$.messager.alert({
		title:'',
		msg:warnTips,
		draggable:false,
		fn:function(){
            /*外层 遮罩隐藏*/
        wrapMaskHide();
        $('body').css('overflow','auto');//恢复滚动
		 window.location.href = url;
        }
	});
	
}

//弹窗高度自适应
function winHiSelfAdaptation(o){
	var htmlW =  $(window).width();//子页面宽度
//	console.log(htmlW)
	var oParent = o.parents('.window');//弹窗
	var h = oParent.height();
	var w = oParent.width();
	var mgt = h/2;//弹窗高度一半
	var mgl = w/2;//宽度一半
	oParent.css({
		'position':'fixed',
		'top':'50%',
		'left':'50%',
		'margin-top':-mgt+'px',
		'margin-left':-mgl-100+'px'
	});
	if(htmlW<1000){
		oParent.css('margin-left',-mgl+'px');
	}
}

/**
 * 给时间框控件扩展一个清除的按钮
 */
$.fn.datebox.defaults.cleanText = '清空';

(function ($) {
    var buttons = $.extend([], $.fn.datebox.defaults.buttons);
    buttons.splice(1, 0, {
        text: function (target) {
            return $(target).datebox("options").cleanText
        },
        handler: function (target) {
            $(target).datebox("setValue", "");
            $(target).datebox("hidePanel");
        }
    });
    $.extend($.fn.datebox.defaults, {
        buttons: buttons
    });

})(jQuery);


/*鼠标悬浮（hover）提示框*/
function toopTip(idOrClass,showText){
    $(idOrClass).tooltip({
        position: 'bottom',
        content: '<span style="color:#6A6A6A">' + showText + '</span>',
        onShow: function(){
            $(this).tooltip('tip').css({
                backgroundColor: '#ffffff',
                borderColor: '#ff8c40'
            });
        }
    });
}

/*datagrid 表格复选框选中是否勾选判断*/
function datagridcheck(){
    $(".datagrid-cell-check input").on("click",function(){
        var rowcheck = $(this).parent().parent().parent().hasClass("datagrid-row-checked");//判断是否被勾选
        console.log($(this).parent().parent().parent());
        console.log(rowcheck);
        if(rowcheck){
            console.log("复选框没有被勾选");
        }else{

            //复选框被勾选
            console.log("复选框被勾选");
        }
    });
}

/*tree 树，点击节点，判断该节点左边的复选框选中是否勾选*/
function treecheck(){
    $(".tree-checkbox").next().on("click",function(){
        var rowcheck = $(this).parent().find(".tree-checkbox").hasClass("tree-checkbox1");//判断是否被勾选
        console.log(rowcheck);
        if(rowcheck){
            //复选框被勾选
            console.log("复选框被勾选");
        }else{


            console.log("复选框没有被勾选");
        }
    });
}

//表格复选框样式配合css重写
function loadSpan(){//动态添加标签
	var checkParent = $('.datagrid-cell-check input[type="checkbox"]');
	var checkBorder = '<span class="checkBorder"></span>';
	checkParent.after(checkBorder);
	var headerCheck = $('.datagrid-header-check input[type="checkbox"]');
	if( headerCheck.siblings('.checkBorder').length == 0 ){
		headerCheck.after(checkBorder);
	}
	var checkedDis = $('.datagrid input[type="checkbox"]:disabled');
	checkedDis.siblings('.checkBorder').addClass('checkDis');
//	console.log(checkedDis.siblings('.checkBorder'))
};
function boxCheck(){//勾选手动修改样式
	var checked = $('.datagrid input[type="checkbox"]:checked');
	var checkBorder = checked.siblings('.checkBorder');
	checkBorder.addClass('checkIcon'); 
};
function boxUnCheck(){//取消勾选手动修改样式
	var unchecked = $('.datagrid input[type="checkbox"]').not("input:checked");
	var checkBorder = unchecked.siblings('.checkBorder');
	checkBorder.removeClass('checkIcon');
}

//公共分页方法 带元素ID、 页数、 selectPage方法、s按钮数防止弹窗宽度不够
function comPagination(Id,pagesize,selectPage,s){
//	按钮数不带默认10个
	var links = 0;
	if( s == undefined ){
		links = 10;
	}else{
		links = s;
	}
	$(Id).pagination({
		layout:['first','prev','links','next','last','manual'],
		pageSize:pagesize,
		links:links,
		onSelectPage:selectPage
	})
}

/**
 * 定义对话框对象
 */
var DefineConfirmDialog= new Function();
DefineConfirmDialog.prototype ={
	 title:"",
	 msg:"",
	 closable:true,
	 draggable:false,
	 okButton:"确认",
	 cancelButton:"取消",
     arg1:"",
     arg2:"",
	newConfirm:function(okCallbakFunciton,cancelCallbackFunciton){
		createConfirm(this.title,this.msg,this.closable,this.draggable,this.okButton,this.cancelButton,okCallbakFunciton,cancelCallbackFunciton,this.arg1,this.arg2);
   }
}
//创建对话提示框
function createConfirm(title,msg,closable,draggable,okButton,cancelButton,okCallbakFunciton,cancelCallbackFunciton,arg1,arg2){
    if(arg1 == "undefind" && arg2 =="undefind"){
        createConfirm2(title,msg,closable,draggable,okButton,cancelButton,okCallbakFunciton,cancelCallbackFunciton,"","")
    }else{
        createConfirm2(title,msg,closable,draggable,okButton,cancelButton,okCallbakFunciton,cancelCallbackFunciton,arg1,arg2)
    }

}
function createConfirm2(title,msg,closable,draggable,okButton,cancelButton,okCallbakFunciton,cancelCallbackFunciton,arg1,arg2){
	//wrapMaskShow();
    parent.$.messager.confirm({
        "title":title,
        "msg":msg,
        "closable":closable,
        "draggable":draggable,
        "ok":cancelButton,
        "cancel":okButton,
        "fn":function(boolean){
            if(!boolean){//确定删除操作

            	if(okCallbakFunciton){
                    if(arg1 != "undefind"){
                        okCallbakFunciton(arg1);
                    }else{
                        okCallbakFunciton();
                    }

            	}
            }else{//取消删除
            	if(cancelCallbackFunciton){
                    if(arg2 != "undefind"){
                        cancelCallbackFunciton(arg2);
                    }else{
                        cancelCallbackFunciton();
                    }


            	}

            }
        }
    });
}

wrapMaskHide();

function configMenuItem(menuItemMsg,MenuItemMsgItem){
	menuItemEachWelCome(menuItemMsg);
	$(parent.document).find(".menuItem").each(function(){ 	        	
	    if($(this).text() == menuItemMsg){
	        $(this).next().find(".submenuItem").each(function(){                   
	            if($(this).text() == MenuItemMsgItem){
	                $(this).addClass("submenuItemActive").siblings().removeClass("submenuItemActive");                      
	            }
	        });
	    }
	});
}


function menuItemEachWelCome(msg){
    $(parent.document).find(".menuItem").each(function(){
        $(this).removeClass("open");
        hightPicWelCome();
        if($(this).text() == msg){
            $(this).addClass("open");
            hightPicWelCome();
        }
    });
}
function hightPicWelCome(){
    //隐藏下拉图标
    $(parent.document).find(".menuItem").each(function(){
        if($(this).siblings('.submenu').length == 0){
            $(this).siblings('.icon').hide();
        }
        if($(this).hasClass("open")){
            //$(this).next().slideDown(120);//菜单显示
            $(this).siblings(".logo-img-black").hide();//高亮图片
            $(this).siblings(".logo-img-white").show();
            $(this).prev().addClass("left-border-check");//选择竖条
            $(this).siblings(".icon").css('background-image','url(common/images/nav_ic_down1.png)');//下拉上来icon
        }else{
           // $(this).next().slideUp(120);//菜单收起
            $(this).siblings(".logo-img-black").show();
            $(this).siblings(".logo-img-white").hide();
            $(this).prev().removeClass("left-border-check");
            $(this).siblings(".icon").css('background-image','url(common/images/nav_ic_more1.png)');
        }
    });
}  




