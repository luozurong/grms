//树数据渲染
var treejson;
//	表格数据渲染
//需要隐藏的按钮
var mapButton = {};
var userType;
var departIdChange='';
var pageNoAll='1';
var pageSizeAll='10';
var dataArea=[{"id":0,"text":"个人数据"},{"id":1,"text":"部门数据"},{"id":2,"text":"全局数据"}];
$(function(){
    configMenuItem("人员管理","账号管理");

    //格式化时间
    $(".datebox-1").datebox({
        value : '',
        editable : false,
        onSelect : function(beginDate){
            $('.datebox-2').datebox('calendar').calendar({
                validator: function(date){
                    return beginDate<=date;//<=
                }
            });
        }
    });
    //格式化时间
    $(".datebox-2").datebox({
        value : '',
        editable : false,
        onSelect : function(endDate){
            $('.datebox-1').datebox('calendar').calendar({
                validator: function(date){
                    return endDate>=date;//<=
                }
            });
        }
    });
    
    $('#dataArea1').combobox({
	       limitToList:true,
	       value : '0',
	       data:dataArea,
	       valueField:'id',
	       textField:'text',
	       editable : false,
	       panelHeight:"auto"
	   });
    
    $('#dataArea2').combobox({
	       limitToList:true,
	       value : '0',
	       data:dataArea,
	       valueField:'id',
	       textField:'text',
	       editable : false,
	       panelHeight:"auto"
	   });
    initButton();

});

function initButton(){
	//
	mapButton['create'] = 1;
	mapButton['select'] = 1;
	mapButton['deleteAll'] = 1;
	mapButton['detail'] = 1;
	mapButton['alter'] = 1;
	mapButton['delete'] = 1;

	var menuUrl="/ums/userAction!goUserManagementList.html";
    $.ajax({  
        type : "POST",  //提交方式  
        url : "initButtonAction!initMenuButton.html",//路径  
        data:{"menuUrl":menuUrl},
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {
            	
            	//比较需要隐藏的按钮
            	var buttonObject= result.obj;
            	var admin=result.msg;
            	if(admin!="admin"){
            	var temp=1;
            	for(var prop in mapButton){
            		temp=1;
            		for(var j=0;j<buttonObject.length;j++){
            			if(prop==buttonObject[j].resourceCode){
            				temp=0;
            				break;
            				}
            			}
            			if(temp==1){
            				mapButton[prop]=0;
            			}
            		}
            	if(mapButton['create']==0){
            	  $("#create").hide();
              	}
              	if(mapButton['select']==0){
              		$("#select").hide();
              	}
              	if(mapButton['deleteAll']==0){
              		$("#deleteAll").hide();
              	}
            }else{
            	userType=0;
            }
                refreshall();
            } else {  
            	
            }  
        }  
    }); 
    
}
function selectAll(){
	pageNoAll='1';
	  $('#pp').pagination({
	        pageNumber:1
	     
	    });
	findDepart('','','');
}
function refreshall(){
    $.ajax({  
        type : "POST",  //提交方式  
        url : "organizationAction!getSystemOrganizationOthers.html",//路径  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
            	treejson=result.obj;
            	append(treejson);
            } else {  
            	
            }  
        }  
    }); 
    
    findDepart("","","");
}

function append(treejson){
    //树数据渲染
    $('#box').tree({
//        url : treejson,
        data : treejson,
        animate : false,
        checkbox : false,
        cascadeCheck : false,
        onlyLeafCheck : false,
        lines : false,
        dnd : false,
        onClick : function (node) {
        	departIdChange=node.id
        	findDepart("","","");
         },
         onDblClick:function(node){
        	 departIdChange='';
        	 if(node){  
        	        $('#box').find('.tree-node-selected').removeClass('tree-node-selected');  
        	    }   
         }
         
    });
}

//查询
function findDepart(departId,pageSize,pageNo){
	//var v = $('#dd').datebox('getValue');
	var createTimeBegin= $(".datebox-1").datebox('getValue');
	var createTimeEnd=$(".datebox-2").datebox('getValue');
	var userAccount=$('#userAccount').val();
	var name=$('#name').val();
	var mobile=$('#mobile').val();
	var roleName=$('#roleName').val(); 
	departId=departIdChange;
	pageSize=pageSizeAll;
	pageNo=pageNoAll;
	var data='{"createTimeBegin":"'+createTimeBegin+'","createTimeEnd":"'+createTimeEnd+'","name":"'+name+
	'","mobile":"'+mobile+'","userAccount":"'+userAccount+'","departId":"'+departId+'","roleName":"'+roleName+'","pageNumber":"'+pageNo+'","pageSize":"'+pageSize+'"}';
    $.ajax({  
        type : "POST",  //提交方式  
        url : "userAction!getUserManagementByDepartParam.html",//路径  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        data:  {"userManagementQueryBean":data},  
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
	
}

function refresh(data2){
//	表格数据渲染
    $('#dg').datagrid({
        border:true,
        scrollbarSize:0,
        nowrap:false,//允许换行
        data:data2,
        emptyMsg:'<span>无记录</span>',
        fitColumns:true,//宽度不自适应
        checkOnSelect:false,//点击该复选框的时候才会选中或取消
        columns:[[
            {
                field:'ck',
                title:'',
                checkbox:true,
                width:50,
                align:'left'
            },
            {
                field:'createTime',
                title:'创建时间',
                width:220,
                align:'left'
            },
            {
                field:'userAccount',
                title:'帐号',
                width:190,
                align:'left'
            },
            {
                field:'name',
                title:'姓名',
                width:190,
                align:'left'
            },
            {
                field:'mobile',
                title:'手机',
                width:190,
                align:'left'
            },
            {
                field:'roleName',
                title:'角色',
                width:190,
                align:'left'
            },
            {
                field:'roleType',
                title:'用户类型',
                width:220,
                align:'left',  
                formatter: function(value){
                	//用户类型（0为业务员，1为业务管理员，2为合同管理员，3为财务管理员，4为社区运营执行管理员，5为电商运营管理员，6为用户运营执行管理员，7为媒管执行管理员，8为平台支持人员）
                	if(value==0){
                		return "业务员";
                	}else if(value==1){
                		return "业务管理员";
                	}else if(value==2){
                		return "合同管理员";
                	}else if(value==3){
                		return "财务管理员";
                	}else if(value==4){
                		return "社区运营执行管理员";
                	}else if(value==5){
                		return "电商运营管理员";
                	}else if(value==6){
                		return "用户运营执行管理员";
                	}else if(value==7){
                		return "媒管执行管理员";
                	}else if(value==8){
                		return "平台支持人员";
                	}
                
                }
               
            },
            {
                field:'dataArea',
                title:'数据域',
                width:190,
                align:'left',
                formatter: function(value){
                	if(value==0){
                		return "个人数据";
                	}else if(value==1){
                		return "部门数据";
                	}else if(value==2){
                		return "全局数据";

                	}
                
                }
            },
            {
                field:'roleId',
                title:'角色id',
                width:190,
                align:'left',
                hidden:true
            },
            {
                field:'userDetailId',
                title:'用户资料Id',
                width:190,
                align:'left',
                hidden:true
            },
            {
                field:'departId',
                title:'机构Id',
                width:190,
                align:'left',
                hidden:true
            },
            {
                field:'userId',
                title:'用户Id',
                width:190,
                align:'left',
                hidden:true
            },
            {
                field:'handle',
                title:'操作',
                width:574,
                align:'left',
                formatter: function(value,row,index){
                	var buttonChoice='';
                    var detailWorkOrder = '<a href="javascript:;" data-id="1" onclick="roleWinDetail(this,\''+index+'\')">详情</a>';
                    var changeWorkOrder = '<a href="javascript:;" data-id="2" onclick="roleWin(this,\''+index+'\')">修改</a>';
                    var msgWorkOrder = '<a href="javascript:;" data-id="3" onclick="dealDeleteOne(\''+index+'\')">删除</a>';
                	if(userType!=0){
                		if(mapButton['detail']==1){
                			buttonChoice=detailWorkOrder;
                		}
                		if(mapButton['alter']==1){
                			buttonChoice=buttonChoice+changeWorkOrder;
                		}
                		if(mapButton['delete']==1){
                			buttonChoice=buttonChoice+msgWorkOrder
                		}
                	}else{
                		buttonChoice=detailWorkOrder+changeWorkOrder+msgWorkOrder;
                	}
                    
                    return buttonChoice;
                }
            }
        ]],
        onLoadSuccess: function (data) {
            //去掉表格底部多出的19px
            $('#dg').datagrid('resize');
            var dataHeight =  $(".datagrid-view").height()-19;
            $(".datagrid-view").css("height",dataHeight );
        }
    });
}
function paginationpage(total,data2){
    //分页
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
            findDepart("",'','') ;   

        }
    });
    $(".pagination-page-list").parent().append("条");
    $(".pagination-page-list").parent().prepend("共计"+data2.length+"条,每页显示： ");
}
function addbutton(){
    $('#roleWin').append('<div id="button" class="the-bottom clearfix" ><span class="quxiao fl" >取消</span><span class="comfirm fl" >保存</span></div>');

}
/*点击详情*/
function roleWinDetail(obj,index){
	initData();
	title = "详情";
    var checkedItem = $('#dg').datagrid('getData').rows[index];
    $("#roleSelect2").combotree("setValue",checkedItem.roleId);
    $("#departSelect2").combotree("setValue",checkedItem.departId);
    $("#theName").html(checkedItem.name);
    $("#userAccount2").val(checkedItem.userAccount);
    $("#dataArea2").combobox("setValue",checkedItem.dataArea);
    $("#dataArea2").combobox('disable');
    $('#roleSelect2').combotree('disable');
	$('#departSelect2').combotree('disable');
	$("#theName").attr("disabled","disabled");
	$("#userAccount2").attr("disabled","disabled");
    wrapMaskShow();//父级遮罩显示
    $('body').css('overflow','hidden');//禁止滚动
    $('#roleWin_detail').window({
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
        title:"详情",
        //ok:"删除",
        //cancel:"取消",
        onClose : function(){
            wrapMaskHide();//父级遮罩隐藏
            $('body').css('overflow','auto');//恢复滚动
        }
    });
    winHiSelfAdaptation($('#roleWin_detail'));//弹窗定位
}


/*点击创建，详情，编辑  弹窗*/
function roleWin(obj,index){
    var title = "";
    var dataId = $(obj).attr("data-id");
    initData();
    addbutton();
   $(".right-name").empty();
   $(".left-name").empty();
    if(dataId == 0){
        title = "创建";
        $("#userAccount1").val('');
        $("#userAccount1").removeAttr("disabled");
    }else if(dataId == 2){
        var checkedItem = $('#dg').datagrid('getData').rows[index];
    	initdata3(checkedItem);
        title = "修改";
		$("#userAccount1").attr("disabled","disabled");
    }else{

    }
    wrapMaskShow();//父级遮罩显示
    $('body').css('overflow','hidden');//禁止滚动
    $('#roleWin').window({
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
        //ok:"删除",
        //cancel:"取消",
        onClose : function(){
            wrapMaskHide();//父级遮罩隐藏
            $('body').css('overflow','auto');//恢复滚动
        }
    });
    winHiSelfAdaptation($('#roleWin'));//弹窗定位
    $('.quxiao').click(function(){
        $('#roleWin').window('close');
    	$('#button').remove();

    })

    $('.comfirm').click(function(){
    	var status;
    	if(dataId != 1){
    		status=confirmData1(dataId);
    	}
 
    })

}


function confirmData1(dataId){
    var data = new Object(); 
	if(dataId!=1){
		var list=$(".right-name .list");
		var arr1 = new Array() ;
		
		if(list.length<=0){
			infoMask("请选择人员");
			return 0;
		}
		//修改只能选一个人
		if(dataId==2){
			if(list.length>1){
				infoMask("修改只能选一个人员");
				return 0;
			}
		}
		
		for(var i=0;i<list.length;i++){
	        var id= $(list[i]).attr("data-id");
	        arr1[i]=id;
		}
		var roleId = $("#roleSelect").combotree("getValue");
		if(roleId==null||roleId==''){
			infoMask("请选择角色");
			return 0;
		}
		var dataArea = $("#dataArea1").combotree("getValue");
		if(dataArea==null||dataArea==''){
			infoMask("请选择数据域");
			return 0;
		}
		var roleName = $("#roleSelect").combotree("getText");
		var userAccount=$("#userAccount1").val();
		var roleType = $("#roleType").val();

		var userId=$("#userId1").val();
		if(userAccount==null||userAccount==''){
			infoMask("请填写用户前缀");
			return 0;
		}
		data.ids=arr1;
		data.roleId=roleId;
		data.roleName=roleName;
		data.userAccount=userAccount;
		data.userId=userId;
		data.dataArea=dataArea;
		data.roleType=roleType;
	}
	var	 toStr = JSON.stringify(data);
	
	$.ajax({  
	      type : "POST",  //提交方式  
	      url : "userAction!saveOrUpdateAccount.html",//路径  
	      dataType : "json",//数据，这里使用的是Json格式进行传输  
	      data:  {"addUserVo":toStr},  
	      success : function(result) {//返回数据根据结果进行相应的处理  
	            if ( result.success) { 
	            	infoMask("保存成功");
	                findDepart("","","");
	            /*	$('#pp').pagination({
	            		pageNumber: 1
	            	});*/
	                $('#roleWin').window('close');
	            	$('#button').remove();
	                  return 1;
	            } else {  
	            	infoMask(result.msg);
					return 0;

	            }  
	        }  
	    }); 
	
	return 1;
}
function initData(){
    $.ajax({  
        type : "POST",  //提交方式  
        async:false,
        url : "userAction!getRole.html",//路径  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
            	var data=result.obj;
            	initRole(data);
            	initRole2(data);
            } else {  
            	
            }  
        }  
    }); 
	
}

function initRole(data){
	   $('#roleSelect').combotree({
	        data: data,
	        //required: true,
	        editable:false,
	        valueField:'id',
	        textField:'text'
	    });
	   $('#departSelect').combotree({
	        data: treejson,
	        //required: true,
	        editable:false,
	        valueField:'id',
	        textField:'text',
	        onSelect:function(node) {
	        	  initData2(node.id)
	        }
	        
	    });
}

function initRole2(data){
	   $('#roleSelect2').combotree({
	        data: data,
	        //required: true,
	        editable:false,
	        valueField:'id',
	        textField:'text'
	    });
	   $('#departSelect2').combotree({
	        data: treejson,
	        //required: true,
	        editable:false,
	        valueField:'id',
	        textField:'text',
	        onSelect:function(node) {
	        	  initData2(node.id)
	        }
	        
	    });
}
function initData2(departId){
	  $.ajax({  
	        type : "POST",  //提交方式  
	        url : "userAction!getUserDetail.html",//路径  
	        async:false,
	        data:{"departId":departId},
	        dataType : "json",//数据，这里使用的是Json格式进行传输  
	        success : function(result) {//返回数据根据结果进行相应的处理  
	            if ( result.success) {  
	            	var data=result.obj;
	            	append2(data)
	            } else {  
	            	
	            }  
	        }  
	    }); 
}

function initdata3(checkedItem){
	 	var bb=$('#roleSelect').combotree('tree').tree("getChildren");
	 	
	 	if(bb.length==0){
	 	$('#roleSelect').combotree('loadData', [{
    		id: checkedItem.roleId,
    		text: checkedItem.roleName
    	}]);
		}
	   $("#roleSelect").combotree("setValue",checkedItem.roleId);
	
       var name=checkedItem.name;//名字
       var userDetailId=checkedItem.userDetailId;
       $("#departSelect").combotree("setValue",checkedItem.departId);

       var htmlTep='<div class="list" data-id="'+userDetailId+'"><div>'+name+'</div><div class="list-delete" >&times;</div></div>';
       $(".right-name").append(htmlTep);
       $("#userAccount1").val(checkedItem.userAccount);
       $("#userId1").val(checkedItem.userId);
       $("#dataArea1").combobox("setValue",checkedItem.dataArea);

       $("#roleType").val(checkedItem.roleType);

}

function append2(data){
	$(".left-name").empty();
    for(var i=0;i<data.length;i++){
        var id=data[i].id;
        var text=data[i].text;
        var htmlTep=' <div class="list" data-id="'+id+'"><a>'+text+'</a></div>';
		$(".left-name").append(htmlTep);
    }
    $(".left-name .list").click(function(){
    	if(!$(this).find("a").hasClass("active")){
    		$(this).find("a").addClass("active");
    		var name=$(this).find("a").text();
    		var id=$(this).attr("data-id");
    		var htmlTep='<div class="list" data-id="'+id+'"><div>'+name+'</div><div class="list-delete" >&times;</div></div>';
    		$(".right-name").append(htmlTep);
    	}
    })
    $(".right-name").on("click",".list-delete",function(){
    	var delId=$(this).parent().attr("data-id");
    	$(".left-name .list").each(function(){
    		if($(this).attr("data-id")==delId){
    			$(this).find("a").removeClass("active");
    		}
    	})
    	$(this).parent().remove();
    })
}
function dealDeleteMany(){
	var checkedItems= $('#dg').datagrid('getChecked');
    console.log(checkedItems);
    if(checkedItems.length){
        createConfirm("消息提醒","请确认是否删除帐号",true,false,"删除","取消",deleteMenu,"",checkedItems,"");
    }else{
        infoMask("请勾选要删除的项目");
    }
//	deleteMenu(checkedItems);
	
}

function dealDeleteOne(index){
    var checkedItem = $('#dg').datagrid('getData').rows[index];
    createConfirm("消息提醒","请确认是否删除帐号",true,false,"删除","取消",deleteMenu,"",checkedItem,"");
//	deleteMenu(checkedItem);

}
function deleteMenu(checkedItems){
	 var ids="";
	 if(checkedItems instanceof Array){
		for(var i=0;i<checkedItems.length;i++){
			if(i==0){
				ids=checkedItems[i].userId;	
			}else{
				ids=ids+"'"+checkedItems[i].userId;
			}
		}
	 }else{
		 ids=checkedItems.userId;
	 }
		 $.ajax({  
		        type : "POST",  //提交方式  
		        url : "userAction!deleteAccountRole.html",//路径  
		        dataType : "json",//数据，这里使用的是Json格式进行传输  
		        data:  {"ids":ids},  
		        success : function(result) {//返回数据根据结果进行相应的处理  
		            if ( result.success) { 
		            	infoMask("删除成功");
		                findDepart("","","");
		                  return;
		            } else {  
		            	infoMask(result.msg);

		            }  
		        }  
		    }); 
}


/*点击删除*/
function detail(obj) {
    var id = $(obj).attr ("data-id");
    parent.$.messager.confirm({
        title:'消息提醒',
        msg:'当前角色还存在关联账号数据，请优先删除账号数据，否则无法删除该角色。',
        draggable:false,
        closable:true,
        ok:"删除",
        cancel:"取消",
        fn:function(boolean){
            if(boolean){//确定删除操作

            }else{//取消删除

            }
        }
    });
}

