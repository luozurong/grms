var data1 = [
             {
                 "id":1,
                 "text":"开启"
             },{
                 "id":0,
                 "text":"关闭"
             }
];
$(function(){

	 refresh();
  
});

function initSelect(){
	//下拉框
	 $('#btnStatus1').combobox({
       limitToList:true,
       value : '1',
       data:data1,
       valueField:'id',
       textField:'text',
       editable : false,
       panelHeight:"auto"
   });
}

function refresh(){
    $.ajax({  
        type : "POST",  //提交方式  
        url : "menuButtonAction!initButton.html",//路径  
        dataType : "json",//数据，这里使用的是Json格式进行传输 
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
            	var data1=result.obj;
            	initSystem(data1);
            } else {  
            	
            }  
        }  
    }); 

}

function initSystem(data1){
	  $('#dg').datagrid({
	        border:true,
	        scrollbarSize:0,
	        nowrap:false,//允许换行
	        data:data1,
	        fitColumns:true,//宽度不自适应
          emptyMsg:'<span>无记录</span>',
	        checkOnSelect:false,//点击该复选框的时候才会选中或取消
	        onLoadSuccess:function(){ //dom操作
	            $('#dg').datagrid('resize');
	            var dataHeight =  $(".datagrid-view").height()-19;
	            $(".datagrid-view").css("height",dataHeight );
	        },
	        columns:[[
	            {
	                field:'ck',
	                title:'',
	                checkbox:true,
	                width:50,
	                align:'left'
	            },
	            {
	                field:'btnId',
	                title:'按钮ID',
	                width:220,
	                align:'left',
	                hidden:'true'
	            },
	            {
	                field:'btnName',
	                title:'按钮名称',
	                width:190,
	                align:'left'
	            },
	            {
	                field:'btnCode',
	                title:'按钮代码',
	                width:190,
	                align:'left'
	            },
	            {
	                field:'btnIcon',
	                title:'按钮图标',
	                width:220,
	                align:'left'
	            },
	            {
	                field:'btnStatus',
	                title:'按钮状态',
	                width:220,
	                align:'left',
	                formatter: function(value){
	                	if(value==0){
	                		return "关闭";
	                	}else if(value==1){
	                		return "开启";
	                	}
	                
	                }
	            },
	            {
	                field:'btnUrl',
	                title:'按钮地址',
	                width:220,
	                align:'left'
	            },
	        
	            {
	                field:'btnOrder',
	                title:'按钮排序',
	                width:220,
	                align:'left' ,
	                hidden:'true'
	            },
	            {
	                field:'btnParentId',
	                title:'父按钮ID',
	                width:220,
	                align:'left' ,
	                hidden:'true'
	            },
	            {
	                field:'createTime',
	                title:'创建时间',
	                width:220,
	                align:'left' 
	            },
	            {
	                field:'modifiedTime',
	                title:'修改时间',
	                width:220,
	                align:'left' 
	            },
	            {
	                field:'handle',
	                title:'操作',
	                width:574,
	                align:'left',
	                formatter: function(value,row,index){
	                    var detailWorkOrder = '<a href="javascript:;" data-id="1" onclick="roleWin(2,\''+index+'\')">查看</a>';
	                    var changeWorkOrder = '<a href="javascript:;" data-id="2" onclick="roleWin(3,\''+index+'\')">编辑</a>';
	                    var msgWorkOrder = '<a href="javascript:;" data-id="3" onclick="dealDeleteOne(\''+index+'\')">删除</a>';
	                    return detailWorkOrder+changeWorkOrder+msgWorkOrder;
	                }
	            }
	        ]]
	    });

}

function dealDeleteOne(index){
    var checkedItem = $('#dg').datagrid('getData').rows[index];
    createConfirm("消息提醒","请确认是否删除",true,false,"删除","取消",okCallbakFunciton,"","","");
    function okCallbakFunciton(){
        $.ajax({
            type : "POST",  //提交方式
            url : "menuButtonAction!deleteButton.html",//路径
            dataType : "json",//数据，这里使用的是Json格式进行传输
            data:  {"id":checkedItem.btnId},
            success : function(result) {//返回数据根据结果进行相应的处理
                if ( result.success) {
                    infoMask("删除成功");
                    refresh();
                } else {
                    infoMask("删除失败");

                }
            }
        });
    }


}

/*点击删除，弹窗*/
function detail(obj) {
    var id = $(obj).attr ("data-id");
    parent.$.messager.confirm({
        title:'消息提醒',
        msg:'当前角色还存在关联账号数据，请优先删除账号数据，否则无法删除该角色。',
        closable:true,
        draggable:false,
        ok:"删除",
        cancel:"取消",
        fn:function(boolean){
            if(boolean){//确定删除操作

            }else{//取消删除

            }
        }
    });
}

function addbutton(){
    $('#content-box').append('<div id="button" class="the-bottom clearfix" ><span class="quxiao fl" >取消</span><span class="comfirm fl" >保存</span></div>');

}
/*点击创建，详情，编辑  弹窗*/
function roleWin(type,index){
	var title='';
	$dom=$('#roleWin_change');
	initSelect();
	if(type==1){
		 title = "添加按钮";
		 addbutton();
		  $("#roleWin_change input").val("");
	      $("#roleWin_change textarea").val("");
		  $("#roleWin_change input").attr("disabled",false);
	      $("#roleWin_change textarea").attr("disabled",false);
	}else if(type==2){
		 title ="查看按钮";
		 initData(index);
	 	 $('#button').remove();
		 $("#roleWin_change input").attr("disabled","disabled");
	     $("#roleWin_change textarea").attr("disabled","disabled");
	}else if(type==3){
		title ="编辑按钮";
	  	initData(index);
		$("#roleWin_change input").attr("disabled",false);
	    $("#roleWin_change textarea").attr("disabled",false);
		 addbutton();	
		 }
    wrapMaskShow();//父级遮罩显示
    $('body').css('overflow','hidden');//禁止滚动
    $dom.window({
        width:580,
        height:550,
        modal:true,
        collapsible:false,
        minimizable:false,
        maximizable:false,
        closable:true,
        draggable:false,
        resizable:false,
        inline:false,
        title:title,
        onClose : function(){
            wrapMaskHide();//父级遮罩隐藏
            $('body').css('overflow','auto');//恢复滚动
        }
    });
    winHiSelfAdaptation($dom);//弹窗定位
    $('.quxiao').click(function(){
        $('#roleWin_change').window('close');
        $("#roleWin_change input").val("");
      	$("#roleWin_change textarea").val("");
    	$('#button').remove();

    })
    $('.comfirm').click(function(){
        $dom.window('close');
   	    savaButton();
   	 $("#roleWin_change input").val("");
   	 $("#roleWin_change textarea").val("");
 	 $('#button').remove();
   
       
    })

}

function savaButton(){
	var btnId=$('#btnId1').val();
	var btnStatus=$('#btnStatus1').combobox('getValue'); 
	var btnName=$('#btnName1').val();
	var btnOrder=$('#btnOrder1').val();
	//url
	var btnUrl=document.getElementById("btnUrl1").value;
	var btnIcon=$('#btnIcon1').val();
	var btnCode=$('#btnCode1').val();
	var note=$("#roleWin_change textarea").val();
	var createTime=$('#createTime1').val();
	var button='{"btnId":"'+btnId+'","btnStatus":"'+btnStatus+
	'","btnName":"'+btnName+'","btnOrder":"'+btnOrder+'","btnUrl":"'+btnUrl+'","btnIcon":"'+btnIcon+
	'","note":"'+note+'","btnCode":"'+btnCode+'","createTime":"'+createTime+'"}';
    $.ajax({  
        type : "POST",  //提交方式  
        url : "menuButtonAction!saveButton.html",//路径  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        data:  {"buttonVo":button},  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
            	infoMask("保存成功");
            	refresh();
                $('#btnName1').val("");
                $('#btnOrder1').val("");
                //url
                $('#btnUrl1').val("");
                $('#btnIcon1').val("");
                $('#btnCode1').val("");
                $("#roleWin_change textarea").val("");
            } else {
                infoMask("保存失败");
            }  
        }  
    }); 
	
/*	alert(menu);
*/}

//初始化按钮数据
function initData(index){
	    var checkedItem=$('#dg').datagrid('getData').rows[index];
	    $('#btnName1').val(checkedItem.btnName);
		var btnUrl1=document.getElementById("btnUrl1").value;
	    document.getElementById("btnUrl1").value=checkedItem.btnUrl;
        $('#btnId1').val(checkedItem.btnId);
        $('#btnCode1').val(checkedItem.btnCode);
	    $('#btnStatus1').combobox('setValue', checkedItem.btnStatus);
	    $('#btnIcon1').val(checkedItem.btnIcon);
	    $('#btnOrder1').val(checkedItem.btnOrder);
	    $('#createTime1').val(checkedItem.createTime);
   
	}
