/**
 * Created by dell on 2017/5/26.
 */
var data1 = [
    {
        "id":1,
        "text":"开启"
    },{
        "id":0,
        "text":"关闭"
    }
];
var menuApp = [
             {
                 "id":0,
                 "text":"网页"
             },{
                 "id":1,
                 "text":"app"
             }
         ];
var pageNoAll='1';
var pageSizeAll='10';
$(function(){
    configMenuItem("人员管理","功能按钮管理");
	 findMenu('','');

});

function initSelect(){
	 $('#select_3').combobox({
	       limitToList:true,
	       value : '1',
	       data:data1,
	       valueField:'id',
	       textField:'text',
	       editable : false,
	       panelHeight:"auto"
	  
	   });

	initSelect2();

}
function initSelect2(){
    $.ajax({  
        type : "POST",  //提交方式  
        url : "menuButtonAction!initSystem.html",//路径  
		async: false,
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
            	var data1=result.obj;
            	initSelect3(data1);
            } else {  
            	
            }  
        }  
    }); 
}
function initSelect3(data1){
	//下拉框
	 $('#select_1').combobox({
       limitToList:true,
       value : '',
       data:data1,
       valueField:'id',
       textField:'text',
       editable : false,
       panelHeight:"auto",
       onSelect:function(data){
    	   var newPtion = data.id;
    	   if(newPtion!=''){
    		   var menuApp=$('#select_4').combobox('getValue');
    		   initSelect4(menuApp,data.id);
    	   }
       }
       
   });
	 $('#select_4').combobox({
	       limitToList:true,
	       value : '0',
	       data:menuApp,
	       valueField:'id',
	       textField:'text',
	       editable : false,
	       panelHeight:"auto",
	       onSelect:function(data){
	    	   var newPtion = data.id;
	    	   if(newPtion!=''||newPtion==0){
	    		   var systemId=$('#select_1').combobox('getValue');
	    		   if(systemId!=''){
	    			   initSelect4(newPtion,systemId)
	    		   }
	    	   }
	       }
	  
	   });
}
function initSelect4(menuApp,systemId){
	  $.ajax({  
	        type : "POST",  //提交方式  
	        url : "menuButtonAction!initMenu.html",//路径  
			async: false,
	        dataType : "json",//数据，这里使用的是Json格式进行传输  
	        data:  {"systemId":systemId,"menuApp":menuApp},  
	        success : function(result) {//返回数据根据结果进行相应的处理  
	            if ( result.success) {  
	            	var data1=result.obj;
	            	initSelect5(data1);
	            } else {  
	            	
	            }  
	        }  
	    }); 
}

function initSelect5(data1){
	//下拉框
	 $('#select_2').combobox({
       limitToList:true,
       value : '',
       data:data1,
       valueField:'id',
       textField:'text',
       editable : false,
       panelHeight:"auto"
  
   });
}

function dealDeleteMany(){
	var checkedItems= $('#dg').datagrid('getChecked');
    if(checkedItems.length){
        createConfirm("消息确认","请确认是否删除？",true,false,"删除","取消",deleteMenu,"",checkedItems,"");
    }else{
        infoMask("请选择要删除的项目");
    }


//	deleteMenu(checkedItems);
	
}

function dealDeleteOne(index){
    var checkedItem = $('#dg').datagrid('getData').rows[index];
    createConfirm("消息确认","删除后，此菜单及其菜单下所有按钮信息将被清除，确认删除吗？",true,false,"删除","取消",deleteMenu,"",checkedItem,"");

}

function deleteMenu(checkedItems){
	 var ids="";
	 if(checkedItems instanceof Array){
		for(var i=0;i<checkedItems.length;i++){
			if(i==0){
				ids=checkedItems[i].menuId;	
			}else{
				ids=ids+"'"+checkedItems[i].menuId;
			}
		}
	 }else{
		 ids=checkedItems.menuId;
	 }
		 $.ajax({  
		        type : "POST",  //提交方式  
		        url : "menuButtonAction!deleteMenu.html",//路径  
		        dataType : "json",//数据，这里使用的是Json格式进行传输  
		        data:  {"ids":ids},  
		        success : function(result) {//返回数据根据结果进行相应的处理  
		            if ( result.success) { 
		            	infoMask("删除成功");
		            	findMenu('','');
		                  return;
		            } else {  
		            	infoMask("删除失败");

		            }  
		        }  
		    }); 
}

function selectAll(){
	pageNoAll='1';
	  $('#pp').pagination({
	        pageNumber:1
	     
	    });
	findMenu('','');
}
//查询
function findMenu(pageSize,pageNo){
	var systemName=$('#systemName').val();
	var menuname=$('#menuname').val();
	var menuStatus='';
	if($('#status1').prop('checked'))
	{
		menuStatus='1';
	}
	if($('#status2').prop('checked'))
	{
		menuStatus='0';
	}
	if($('#status1').prop('checked')&&$('#status2').prop('checked')){
		menuStatus=''
	}
	pageNo=pageNoAll;
	pageSize=pageSizeAll;
	var menuQueryBean='{"systemName":"'+systemName+'","menuname":"'+menuname+'","menuStatus":"'+menuStatus+
	'","pageNumber":"'+pageNo+'","pageSize":"'+pageSize+'"}';
	
    $.ajax({  
        type : "POST",  //提交方式  
        url : "menuButtonAction!getMenuButtonByParam.html",//路径  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        data:  {"menuQueryBean":menuQueryBean},  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
            	var data2=result.obj.rows;
            	var total=result.obj.total;
            	refresh(data2);
            	paginationpage(total,data2);
            } else {  
            	
            }  
        }  
    }); 
	 initSelect();
}
function refresh(data2){
//	表格数据渲染
    $('#dg').datagrid({
        border:true,
        scrollbarSize:0,
        nowrap:false,//允许换行
        data:data2,
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
                field:'systemName',
                title:'系统',
                width:220,
                align:'left'
            },
            {
                field:'systemId',
                title:'系统id',
                width:220,
                align:'left',
                hidden:'true'

            },
            {
                field:'menuname',
                title:'菜单名称',
                width:190,
                align:'left'
                
            },
            {
                field:'menuId',
                title:'菜单id',
                width:190,
                align:'left',
                hidden:'true'
            },
            {
                field:'menuUrl',
                title:'菜单地址',
                width:190,
                align:'left',
                hidden:'true'
            },
            {
                field:'menuIcon',
                title:'菜单图标',
                width:190,
                align:'left',
                hidden:'true'
            },
            {
                field:'note',
                title:'备注',
                width:190,
                align:'left',
                hidden:'true'
            },
            {
                field:'menuStatus',
                title:'菜单状态',
                width:280,
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
                field:'menuApp',
                title:'菜单类别',
                width:280,
                align:'left',
                formatter: function(value){
                	if(value==0){
                		return "网页";
                	}else if(value==1){
                		return "app";
                	}
                
                }
            },
            {
                field:'menuOrder',
                title:'菜单顺序',
                width:280,
                align:'left'
            },
            {
                field:'menuParentId',
                title:'父菜单',
                width:280,
                align:'left',
                hidden:'true'
            },
            {
                field:'createTime',
                title:'创建时间',
                width:280,
                align:'left'
            },
            {
                field:'modifiedTime',
                title:'修改时间',
                width:280,
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
                    var buttonWorkOrder = '<a  href="menuButtonAction!goButtonList.html?menuId=\''+row.menuId+'\'&menuApp=\''+row.menuApp+'\'" >按钮管理</a>';
                    return detailWorkOrder+changeWorkOrder+msgWorkOrder+buttonWorkOrder;
                }
            }
        ]]
    });

}

function initButton(menuId){
    $.ajax({  
        type : "POST",  //提交方式  
        url : "menuButtonAction!goButtonList.html",//路径  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        data:  {"menuId":menuId},  
        success : function(result) {//返回数据根据结果进行相应的处理  
             
        }  
    }); 
}
function paginationpage(total,data2){
//  分页
    $('#pp').pagination({
        total:total,
        layout:['list','first','prev','links','next','last','manual'],
        emptyMsg: '<span>无记录</span>',
        showRefresh:true,
        displayMsg:' ',
        pageList:[10,20,30],
        onSelectPage:function (pageNo, pageSize) {
        	pageNoAll=pageNo;
        	pageSizeAll=pageSize;
        	findMenu('','')
        }
    });
    $(".pagination-page-list").parent().append("条");
    $(".pagination-page-list").parent().prepend("共计"+total+"条,每页显示： ");

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
		 title = "添加菜单";
		addbutton();
        $(".content-box").css("height","330px");
	    $("#roleWin_change input").attr("disabled",false);
        $("#roleWin_change textarea").attr("disabled",false);
	
	}else if(type==2){
		 title ="查看菜单详情";
		 initData(index);
	 	 $('#button').remove();
         $(".content-box").css("height","438px");
		 $("#roleWin_change input").attr("disabled","disabled");
	     $("#roleWin_change textarea").attr("disabled","disabled");
	}else if(type==3){
	   	initData(index);
        $(".content-box").css("height","330px");
		$("#roleWin_change input").attr("disabled",false);
	    $("#roleWin_change textarea").attr("disabled",false);
		 title ="编辑菜单";
		 addbutton();

	}
    wrapMaskShow();//父级遮罩显示
    $('body').css('overflow','hidden');//禁止滚动
    $dom.window({
        width:580,
        height:500,
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
   	    savaMenu();
   	 $("#roleWin_change input").val("");
   	 $("#roleWin_change textarea").val("");
 	 $('#button').remove();
   
       
    })

}

function initData(index){
/*	$('#dg').datagrid('clearChecked');
    $('#dg').datagrid('checkRow',index);*/

    var checkedItem=$('#dg').datagrid('getData').rows[index];
    console.log("checkedItem---"+JSON.stringify(checkedItem));
    initSelect4(checkedItem.menuApp,checkedItem.systemId);

    $('#select_1').combobox('setValue', checkedItem.systemId);
    $('#select_2').combobox('setValue', checkedItem.menuParentId);
    $('#select_3').combobox('setValue', checkedItem.menuStatus);
    $('#select_4').combobox('setValue', checkedItem.menuApp);

    $('#menuname1').val(checkedItem.menuname);
    $('#menuId1').val(checkedItem.menuId);

    $('#menuOrder1').val(checkedItem.menuOrder);
    $('#menuIcon1').val(checkedItem.menuIcon);
//    $('#menuUrl1').val(checkedItem.menuUrl);

    document.getElementById("menuUrl1").value=checkedItem.menuUrl;
   $('#createTime1').val(checkedItem.createTime);
    document.getElementById("note21").value=checkedItem.note;


    
}

function savaMenu(){
	var systemId=$('#select_1').combobox('getValue'); 
	var menuParentId=$('#select_2').combobox('getValue'); 
	var menuStatus=$('#select_3').combobox('getValue'); 
	var menuApp=$('#select_4').combobox('getValue'); 
	var menuname=$('#menuname1').val();
	var menuOrder=$('#menuOrder1').val();
	var menuUrl=document.getElementById("menuUrl1").value;
	var menuIcon=$('#menuIcon1').val();
	var note=   document.getElementById("note21").value;
	
	var menuId=$('#menuId1').val();
	var createTime=$('#createTime1').val();
	var menu='{"systemId":"'+systemId+'","menuApp":"'+menuApp+'","menuParentId":"'+menuParentId+'","menuStatus":"'+menuStatus+
	'","menuname":"'+menuname+'","menuOrder":"'+menuOrder+'","menuUrl":"'+menuUrl+'","menuIcon":"'+menuIcon+
	'","note":"'+note+'","menuId":"'+menuId+'","createTime":"'+createTime+'"}';
    $.ajax({  
        type : "POST",  //提交方式  
        url : "menuButtonAction!saveMenu.html",//路径  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        data:  {"menuVo":menu},  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
            	infoMask("保存成功");
              	 findMenu('','');
             	$('#pp').pagination({
            		pageNumber: 1
            	});
                $('#menuname1').val("");
                $('#menuOrder1').val("");
                $('#menuIcon1').val("");                
                document.getElementById("menuUrl1").value="";
                document.getElementById("note21").value="";
            } else {  
            	
            }  
        }  
    }); 
	
/*	alert(menu);
*/}

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


