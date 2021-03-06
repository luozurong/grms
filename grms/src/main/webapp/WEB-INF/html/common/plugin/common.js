$(function(){
	$(document).ready(function(){
		fitCoulms();
	});
	$(window).resize(function(){
		fitCoulms();
	});	
})

//表格宽度自适应
function fitCoulms(){
    $('#dg,#dg1,#dg2').datagrid('resize');
	var width = document.body.offsetWidth;
	/*if(width>1120){
		$('#dg,#dg1,#dg2').datagrid({
			fitColumns:true
		})
	}else{
		$('#dg,#dg1,#dg2').datagrid({
			fitColumns:false
		})
	}*/
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
        closable:true,
        msg:warnTips,
        draggable:false,
        fn:function(){

        }
    });
}

function infoMask(warnTips){
	   
	
}

function successMask(warnTips,url){
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
	var oParent = o.parents('.window');//弹窗
	var h = oParent.height();
	var w = oParent.width();
	var mgt = h/2;//弹窗高度一半
	var mgl = w/2;//宽度一半
	oParent.css({
		'position':'fixed',
		'top':'50%',
		'left':'50%',
        'z-index': '10',
		'margin-top':-mgt+'px',
		'margin-left':-mgl-100+'px'
	});
	if(htmlW<1000){
		oParent.css('margin-left',-mgl+'px');
	}
}



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
*复选与单选框样式调整
*/
$("input[type='checkbox']").click(function(){
    if($(this).is(':checked')){
        $(this).parent().addClass("checkbox-bg");
    }else{
        $(this).parent().removeClass("checkbox-bg");
    }
})
$("input[type='radio']").click(function(){
    $("input[name='"+ $(this).attr('name') +"']").parent().removeClass("radio-bg");
    if($(this).is(':checked')){
        $(this).parent().addClass("radio-bg");
    }
})



//格式化时间
/*$("#startTime").datebox({
    value : '2017-05-22',
    editable : false,
    onSelect : function(beginDate){
        $('.datebox-2').datebox('calendar').calendar({
            validator: function(date){
                return beginDate<=date;//<=
            }
        });
    }
});
$("#endTime").datebox({
    value : '2017-05-22',
    editable : false,
    onSelect : function(endDate){
        $('.datebox-1').datebox('calendar').calendar({
            validator: function(date){
                return endDate>=date;
            }
        });
    }
});*/


/**
*表格中复选框样式
*/
function inputStyle(){
    $(".datagrid-cell-check,.datagrid-header-check").each(function(){
        if($(this).find("input").is(':checked')){
            $(this).addClass('dataChecked');
        }else{
            $(this).removeClass("dataChecked");
        }
    });
}
$(".datagrid-row input").click(function(){
    inputStyle();
});
$(".datagrid-header-check input").click(function(){
    if($(this).is(":checked")){
        $(this).parent().addClass("dataChecked");  
    }else{
        $(this).parent().removeClass("dataChecked");
    }
    setTimeout(function(){
        inputStyle();
    },100)
});
$(".datagrid-row").click(function(){
    setTimeout(function(){
        inputStyle();
    },100)
});


/**
*对话框
*/
function delayWorkOrder(){
    wrapMaskShow();
    $('#delayWorkOrder').dialog({
        title: "延时处理",
        width: 600,
        height: 400,
        closed: false,
        modal: true,
        buttons:[{
            text:'不同意',
            handler:function(){
                $('#delayWorkOrder').dialog('close');
                wrapMaskHide();
            }
        },{
            text:'同意',
            handler:function(){
                $('#delayWorkOrder').dialog('close');
                wrapMaskHide();
            }
        }]
    });
}


/**
*提示框封装
*/
function messagerConfirm(title,messager,callBack){
    wrapMaskShow();
    $.messager.confirm(title,messager,function(r){    
        if (r){    
            callBack(); //执行操作
            wrapMaskHide();    
        }else{
            wrapMaskHide();
        }    
    });
}
//提示框调用
function tip(){
    messagerConfirm("是否要删除","您确认想要删除记录吗？",function(){
        alert('确认删除') //执行操作
    });
}


