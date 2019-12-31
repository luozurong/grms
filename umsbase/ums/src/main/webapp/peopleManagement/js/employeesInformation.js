//树数据渲染
var treejson ;
//	表格数据渲染
var data2 ;

var data1;
//需要隐藏的按钮
var mapButton = {};
var userType;
var departIdChange='';
var pageNoAll='1';
var pageSizeAll='10';
$(function(){
    configMenuItem("人员管理","员工信息管理");
 /*   $(window).resize(function(){
        console.log($(document).width());//浏览器当前窗口文档对象宽度
    });*/
	//初始化页面按钮
	initButton();
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
    
    data1 = [{"id":0,"text":"男"},{"id":1,"text":"女"}];

});
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

function initButton(){
	//
	mapButton['create'] = 1;
	mapButton['select'] = 1;
	mapButton['deleteAll'] = 1;
	mapButton['detail'] = 1;
	mapButton['alter'] = 1;
	mapButton['delete'] = 1;
	var menuUrl="/ums/userDetailAction!goUserDetailList.html";
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

//查询
function findDepart(departId,pageSize,pageNo){
	var createTimeBegin= $(".datebox-1").datebox('getValue');
	var createTimeEnd=$(".datebox-2").datebox('getValue');
	var name=$('#name').val();
	var mobile=$('#mobile').val();
	var post=$('#post').val();
	departId=departIdChange;
	pageSize=pageSizeAll;
	pageNo=pageNoAll;
	var data='{"createTimeBegin":"'+createTimeBegin+'","createTimeEnd":"'+createTimeEnd+'","name":"'+name+
	'","mobile":"'+mobile+'","departId":"'+departId+'","post":"'+post+'","pageNumber":"'+pageNo+'","pageSize":"'+pageSize+'"}';
    $.ajax({  
        type : "POST",  //提交方式  
        url : "userDetailAction!getUserDetailByDepartId.html",//路径  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        data:  {"userDetailQueryBean":data},  
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
            findDepart('','','') ;   

        }
    });
    $(".pagination-page-list").parent().append("条");
    $(".pagination-page-list").parent().prepend("共计"+data2.length+"条,每页显示： ");
}

function refresh(data2){
	//表格数据渲染
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
                field:'name',
                title:'姓名',
                width:190,
                align:'left'
            },
            {
                field:'mobile',
                title:'手机',
                width:280,
                align:'left'
            },
            {
                field:'post',
                title:'职务',
                width:190,
                align:'left'
            },
            {
                field:'departName',
                title:'所属机构',
                width:190,
                align:'left'
            },
            {
                field:'sex',
                title:'性别',
                width:190,
                align:'left',
                formatter: function(value){
                	if(value==0){
                		return "男";
                	}else if(value==1){
                		return "女";
                	}
                
                }
            },
            {
                field:'email',
                title:'邮箱',
                width:190,
                align:'left',
                hidden:'true'
                
            },
            
            {
                field:'userDetailId',
                title:'用户资料ID',
                width:190,
                align:'left',
                hidden:'true'
                
            },
            {
                field:'handle',
                title:'操作',
                width:574,
                align:'left',
                formatter: function(value,row,index){
                	var buttonChoice='';
        			var detailWorkOrder = '<a href="javascript:;" data-id="1" onclick="roleWin(this,\''+index+'\')">详情</a>';
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
            },

        ]],
        onLoadSuccess: function (data) {
            //去掉表格底部多出的19px
            $('#dg').datagrid('resize');
            var dataHeight =  $(".datagrid-view").height()-19;
            $(".datagrid-view").css("height",dataHeight );
        }
    });

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
        	departIdChange=node.id;
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

function initWin(){
    $('.cc-tree').combotree({
        data: treejson,
        //required: true,
        editable:false,
        valueField:'id',
        textField:'text'
    });
    
	//下拉框
	 $('#sex1').combobox({
       limitToList:true,
       value : '0',
       data:data1,
       valueField:'id',
       textField:'text',
       editable : false,
       panelHeight:"auto"
   });
}
function addbutton(){
    $('#roleWin').append('<div id="button" class="the-bottom clearfix" ><span class="quxiao fl" >取消</span><span class="comfirm fl" >保存</span></div>');

}
/*点击创建，详情，编辑  弹窗*/
function roleWin(obj,index){
    var title = "";
    var dataId = $(obj).attr("data-id");
    initWin();
    if(dataId == 0){
        title = "创建";
        addbutton();
		$("#roleWin input").attr("disabled",false);
		$('.cc-tree').combotree('enable');
		$('#sex1').combobox('enable');
    }else if(dataId == 1){
        title = "详情";
        initData(index);
		$("#roleWin input").attr("disabled","disabled");
		$('.cc-tree').combotree('disable');
		$('#sex1').combobox('disable');
    }else if(dataId == 2){
        title = "修改";
		$("#roleWin input").attr("disabled",false);
		$('.cc-tree').combotree('enable');
		$('#sex1').combobox('enable');
        addbutton();
        initData(index);
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
        draggable:true,
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
        $("#roleWin input").val("");
    	$('#button').remove();

    })

    $('.comfirm').click(function(){
        var status=savaUserDetial();
      /*  if(status==0){
        	$("#roleWin input").val("");
        	$('#roleWin').window('close');
        	$('#button').remove();
        }*/

    })

}
function dealDeleteMany(){
	var checkedItems= $('#dg').datagrid('getChecked');
    console.log(checkedItems);
    if(checkedItems.length){
        createConfirm("消息提醒","请确认是否删除",true,false,"删除","取消",deleteMenu,"",checkedItems,"");
    }else{
        infoMask("请勾选要删除的项目");
    }
//	deleteMenu(checkedItems);
	
}

function dealDeleteOne(index){
    var checkedItem = $('#dg').datagrid('getData').rows[index];
    createConfirm("消息提醒","请确认是否删除",true,false,"删除","取消",deleteMenu,"",checkedItem,"");
//	deleteMenu(checkedItem);

}
function deleteMenu(checkedItems){
	 var ids="";
	 if(checkedItems instanceof Array){
		for(var i=0;i<checkedItems.length;i++){
			if(i==0){
				ids=checkedItems[i].userDetailId;	
			}else{
				ids=ids+"'"+checkedItems[i].userDetailId;
			}
		}
	 }else{
		 ids=checkedItems.userDetailId;
	 }
		 $.ajax({  
		        type : "POST",  //提交方式  
		        url : "userDetailAction!deleteUserDetail.html",//路径  
		        dataType : "json",//数据，这里使用的是Json格式进行传输  
		        data:  {"ids":ids},  
		        success : function(result) {//返回数据根据结果进行相应的处理  
		            if ( result.success) { 
		            	infoMask("删除成功");
		                findDepart("","","");
		                  return;
		            } else {  
		            	var idk=result.obj;
		            	var name="";
		            	if(checkedItems instanceof Array){
		            		for(var i=0;i<checkedItems.length;i++){
		            				if(checkedItems[i].userDetailId==idk){
		            					name=checkedItems[i].name;
		            					break;
		            				}	
		            		}
		            	 }else{
		            		 ids=checkedItems.userDetailId;
         					 name=checkedItems.name;

		            	 }
		            	infoMask(name+","+result.msg);

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
function savaUserDetial(){
	var departId=$('.cc-tree').combotree('getValue'); 
	if(departId==null||departId==''){
		warnMask("所属机构不能为空");
		return 1;
	}
	var departName=$('.cc-tree').combotree('getText'); 
	var userDetailId=$('#userDetailId1').val();
	var createTime=$('#createTime1').val();
	var email=$('#email1').val();
	var sex=$('#sex1').combobox('getValue');
	var mobile=$("#mobile1").val().trim();

	var name=$('#name1').val();
	var post=$('#post1').val();
	
	if(post==null||post==''){
		warnMask("职务不能为空");
		return 1;
	}
	if(name==null||name==''){
		warnMask("姓名不能为空");
		return 1;
	}else{
		if(!validateName(name)){
			warnMask("请输格式正确的姓名");
			return 1;
		}
	}
	if(mobile==null||mobile==''){
		warnMask("手机号码不能为空");
		return 1;
	}else{
		if(!validatePhone(mobile)){
			warnMask("请输入格式正确的手机号码");
			return 1;
		}
	}
	if(!isNull(email)){
		if(!validateEmail(email)){
			warnMask("请输格式正确的邮箱");
			return 1;
		}
	}

	var userDetial='{"userDetailId":"'+userDetailId+'","departId":"'+departId+
	'","departName":"'+departName+'","createTime":"'+createTime+'","email":"'+email+'","sex":"'+sex+
	'","mobile":"'+mobile+'","name":"'+name+'","post":"'+post+'"}';
    $.ajax({  
        type : "POST",  //提交方式  
        url : "userDetailAction!saveUserDetail.html",//路径  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        data:  {"userDetial":userDetial},  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
            	infoMask("保存成功");
            	refreshall();
           
            	$("#roleWin input").val("");
            	$('#roleWin').window('close');
            	$('#button').remove();  
            } else {  
            	warnMask(result.msg);
        		return 1;
            }  
        }  
    }); 
/*	alert(menu);
*/}

function initData(index){
		    var checkedItem=$('#dg').datagrid('getData').rows[index];

		    $('#userDetailId1').val(checkedItem.userDetailId);
		    $('#sex1').combobox('setValue',checkedItem.sex);
	        $('#post1').val(checkedItem.post);
		    $('.cc-tree').combotree('setValue', checkedItem.departId);
		    $('#email1').val(checkedItem.email);
		    $('#mobile1').val(checkedItem.mobile);
		    $('#name1').val(checkedItem.name);
		    $('#createTime1').val(checkedItem.createTime);

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