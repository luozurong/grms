//	表格数据渲染
var data2 ;

var systemId="1";
//需要隐藏的按钮
var mapButton = {};
var userType;
var data1 = [{"id":0,"text":"现场运维人员"},{"id":1,"text":"管理人员"}];
var pageNoAll='1';
var pageSizeAll='10';
//用户类型（0为业务员，1为业务管理员，2为合同管理员，3为财务管理员，4为社区运营执行管理员，5为电商运营管理员，6为用户运营执行管理员，7为媒管执行管理员，8为平台支持人员）
var roleType=[{"id":0,"text":"业务员"},{"id":1,"text":"业务管理员"},{"id":2,"text":"合同管理员"}
,{"id":3,"text":"财务管理员"},{"id":4,"text":"社区运营执行管理员"},{"id":5,"text":"电商运营管理员"},{"id":6,"text":"用户运营执行管理员"},
{"id":7,"text":"媒管执行管理员"},{"id":8,"text":"平台支持人员"}];
$(function(){
    configMenuItem("人员管理","角色管理");
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
    
    $('#roleType').combobox({
	       limitToList:true,
	       value : '0',
	       data:roleType,
	       valueField:'id',
	       textField:'text',
	       editable : false,
	       panelHeight:"auto"
	   });
    initButton();
    $("#box1").show();

    

});

function initButton(){
	//
	mapButton['select'] = 1;
	mapButton['create'] = 1;
	mapButton['view'] = 1;
	mapButton['alter'] = 1;
	mapButton['delete'] = 1;
	var menuUrl="/ums/roleAction!goRoleList.html";
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
             
            }else{
            	userType=0;
            }
                findDepart('','');
            } else {  
            	
            }  
        }  
    }); 
    
}

//查询
function findDepart(pageSize,pageNo){
	var createTimeBegin= $(".datebox-1").datebox('getValue');
	var createTimeEnd=$(".datebox-2").datebox('getValue');
	var roleName=$('#roleName').val();
	pageSize=pageSizeAll;
	pageNo=pageNoAll;
	var data='{"createTimeBegin":"'+createTimeBegin+'","createTimeEnd":"'+createTimeEnd+'","roleName":"'+roleName+'","pageNumber":"'+pageNo+'","pageSize":"'+pageSize+'"}';
	
    $.ajax({  
        type : "POST",  //提交方式  
        url : "roleAction!getRoleManagementParam.html",//路径  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        data:  {"roleQueryBean":data},  
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
        selectOnCheck:false,
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
                field:'roleId',
                title:'角色Id',
                width:220,
                align:'left',
                hidden:true
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
                field:'createAccount',
                title:'创建人员',
                width:190,
                align:'left'
            },
            {
                field:'roleName',
                title:'角色',
                width:280,
                align:'left'
            },
            {
                field:'note',
                title:'备注',
                width:190,
                align:'left'
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
                    var msgWorkOrder = '<a href="javascript:;" data-id="3" onclick="checkDel(\''+row.roleId+'\')">删除</a>';
                    if(userType!=0){
                		if(mapButton['view']==1){
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
// 

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
            findDepart('','') ;   
           
        }
    });
    $(".pagination-page-list").parent().append("条");
    $(".pagination-page-list").parent().prepend("共计"+data2.length+"条,每页显示： ");
}

function initMenuApp(type,roleId){
    $.ajax({  
        type : "POST",  //提交方式  
        url : "roleAction!initSystemMenu.html",//路径  
        data:  {"type":type,"roleId":roleId,"menuApp":"1"},  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
            	 var  menuApp=result.obj;
            	  initControl1(menuApp);
            } else {  
            	
            }  
        }  
    });
}
function initControl(menuWeb){
	
	$('#box1').tree({
		//  url : treejson,
		data : menuWeb,
		animate : true,
		checkbox : true,
		cascadeCheck : false,
		onlyLeafCheck : false,
		lines : false,
		dnd : false,
		onLoadSuccess:function(node,data){
			$('#box1').tree('collapseAll');

		}
	});
	
	$('#box1').tree({
		onCheck: function(node){
			choiceMenu(node);  // 在用户点击的时候提示
		}
	});
/*	var nodesWeb = $('#box1').tree('getChecked');  
	if(nodesWeb.length==0){
	        $(".choice1_2").addClass("choiceActive");
	        $(".choice1_1").removeClass("choiceActive");
	        $('#box1').hide();
	}else{
		 $(".choice1_1").addClass("choiceActive");
	     $(".choice1_2").removeClass("choiceActive");
	     $('#box1').show();
	}*/

}
function choiceMenu2(node){
    var  node1=$('#box2').tree('getParent',node.target);
	if(node1!=null){
		$('#box2').tree('check', node1.target);
		choiceMenu2(node1);
	}else{
		return;
	}
	
}

function initControl1(menuApp){
	
	$('#box2').tree({
		//  url : treejson,
		data : menuApp,
		animate : true,
		checkbox : true,
		cascadeCheck : false,
		onlyLeafCheck : false,
		lines : false,
		dnd : false,
		onLoadSuccess:function(node,data){
			$('#box2').tree('collapseAll');

		}
	});
	$('#box2').tree({
		onCheck: function(node){
			choiceMenu2(node);  // 在用户点击的时候提示
		}
	});
	var nodesWeb= $('#box2').tree('getChecked');  
	if(nodesWeb.length==0){
        $(".choice2_2").addClass("choiceActive");
        $(".choice2_1").removeClass("choiceActive");
        $('#box2').hide();
	}else{
		$(".choice2_1").addClass("choiceActive");
		$(".choice2_2").removeClass("choiceActive");
		$('#box2').show();
	}
}
function selectAll(){
	pageNoAll='1';
	  $('#pp').pagination({
	        pageNumber:1
	     
	    });
	findDepart('','');
}


function choiceMenu(node){
    var  node1=$('#box1').tree('getParent',node.target);
	if(node1!=null){
		$('#box1').tree('check', node1.target);
		choiceMenu(node1);
	}else{
		return;
	}
	
}

function initMenu(type,roleId){
    $.ajax({  
        type : "POST",  //提交方式  
        url : "roleAction!initSystemMenu.html",//路径  
        data:  {"type":type,"roleId":roleId,"menuApp":"0"},  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
            	  var  menuWeb=result.obj;
            	  initControl(menuWeb);
            } else {  
            	
            }  
        }  
    }); 
 
}
/*点击创建，详情，编辑  弹窗*/
function roleWin(obj,index){
    var title = "";
    var rowId;
    addbutton();
    $("#button").css({"display":"block"});
    $(".detail-wrap").css({"height":"358"});
    var dataId = $(obj).attr("data-id");
/*    var row=JSON.parse(rowjosn);;
*/


    if(dataId == 0){
        title = "创建";
        type="add";
        initMenu(type,'');
        $('#roleName1').val("");
        $('#note1').val("");
    }else if(dataId == 1){
        var row = $('#dg').datagrid('getData').rows[index];
        $('#roleName1').val(row.roleName);
        $('#note1').val(row.note);
        $("#roleType").combobox("setValue",row.roleType);
        type="alter";
        initMenu(type,row.roleId);
        title = "详情";
        $("#button").css({"display":"none"});
        $(".detail-wrap").css({"height":"439"});
    }else if(dataId == 2){
        title = "修改";
        var row = $('#dg').datagrid('getData').rows[index];
        $('#roleName1').val(row.roleName);
        $('#note1').val(row.note);
        $("#roleType").combobox("setValue",row.roleType);
        type="alter";
        initMenu(type,row.roleId);
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
        cache : false ,
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
    winHiSelfAdaptation($('#roleWin'));//弹窗定位
    $('.quxiao').click(function(){
        $('#roleWin').window('close');
    	$('#roleName1').val('');
    	$('#note1').val('');
    	$('#button').remove();
    })

    $('.comfirm').click(function(){
    	var nodesWeb = $('#box1').tree('getChecked');  
    	var nodesApp='';
    	var roleName = $('#roleName1').val();
    	var roleType = $('#roleType').val();
    	if(roleType==""){
    		infoMask('用户类型不能为空');
    		return;
    	}
    	if(roleName==""){
    		infoMask('角色名称不能为空');
    		return;
    	}
    	var note=$('#note1').val();
    
    	if(dataId==0){
    		var status=saveMenu(nodesWeb,nodesApp,'',roleName,roleType,note,dataId);
    	}else if(dataId == 2){
    		var status=saveMenu(nodesWeb,nodesApp,index,roleName,roleType,note,dataId);

    	}


    })

}

/*function initSelect(type,roleId){
    $('#roleExample').combobox({
        limitToList:true,
        value : '0',
        data:data1,
        valueField:'id',
        textField:'text',
        editable : false,
        panelHeight:"auto",
        onSelect:function(data){
        	initMenuApp(type,roleId);
        }
    });
}*/

function addbutton(){
    $('#roleWin1').find("#button").remove();
    $('#roleWin1').append('<div id="button" class="the-bottom clearfix" ><span class="quxiao fl" >取消</span><span class="comfirm fl" >保存</span></div>');

}


function saveMenu(nodesWeb,nodesApp,index,roleName,roleType,note,dataId){
	var object=new Object();
	object.roleName=roleName;
	object.note=note;
	object.roleType=roleType;
	if(index!=''){
        var row = $('#dg').datagrid('getData').rows[index];
    	object.roleId=row.roleId;
    	object.createTime=row.createTime;
    	object.createAccount=row.createAccount;
	}
	
	if($(".choiceActive").hasClass("choice1_2")){
		nodesWeb=new Array();
	}
	if($(".choiceActive").hasClass("choice2_2")){
		nodesApp=new Array();
	}
	
    $.ajax({  
        type : "POST",  //提交方式  
        url : "roleAction!addSystemMenu.html",//路径  
        data:  {"nodesWeb":JSON.stringify(nodesWeb),"nodesApp":JSON.stringify(nodesApp),"roleVo":JSON.stringify(object),"dataId":dataId},  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
                $('#roleWin').window('close');
    	        $('#roleName1').val('');
    	    	$('#note1').val('');
    	    	$('#button').remove();
         	    infoMask('保存成功');  
         	    $('#pp').empty();
            	findDepart('','');
           } else {
        	   warnMask('角色重复，请重新命名');  
            	return 1;
            }  
        }  
    }); 
	
}

function checkDel(roleId){
    createConfirm("消息提醒","请确认是否删除",true,false,"删除","取消",okCallbakFunciton,"",roleId,"");
    function okCallbakFunciton(){
        $.ajax({
            type : "POST",  //提交方式
            url : "roleAction!deleteRoleById.html",//路径
            async: false,
            data:  {"roleId":roleId},
            dataType : "json",//数据，这里使用的是Json格式进行传输
            success : function(result) {//返回数据根据结果进行相应的处理
                if ( result.success) {
                    var i=result.obj;
                    if(i=='1'){
                        infoMask("删除成功");
                        findDepart('','');
                    }else{
                        infoMask("当前角色还存在关联账号数据，请优先删除账号数据，否则无法删除该角色。");
                    }

                } else {

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