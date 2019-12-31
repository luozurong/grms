var win = $("#roleWin");//区别弹窗里的树
//需要隐藏的按钮
var mapButton = {};
var userType;
var departIdChange='';
var pageNoAll='1';
var pageSizeAll='10';
$(function(){

    configMenuItem("人员管理","责任区域管理");
    //格式化时间
    $(".datebox-1").datebox({
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
    //格式化时间
    $(".datebox-2").datebox({
        value : '2017-05-22',
        editable : false,
        onSelect : function(endDate){
            $('.datebox-1').datebox('calendar').calendar({
                validator: function(date){
                    return endDate>=date;//<=
                }
            });
        }
    });
    $.extend($.fn.tree.methods,{  
        getLeafChildren:function(jq, params){  
            var nodes = [];  
            $(params).next().children().children("div.tree-node").each(function(){  
                nodes.push($(jq[0]).tree('getNode',this));  
            });  
            return nodes;  
        }  
    });  
    initButton();
        $.ajax({  
            type : "POST",  //提交方式  
            url : "organizationAction!getSystemOrganizationOthers.html",//路径  
            dataType : "json",//数据，这里使用的是Json格式进行传输  
            success : function(result) {//返回数据根据结果进行相应的处理  
                if ( result.success) {  
                	var treejson;
                	treejson=result.obj;
                	append(treejson);
                } else {  
                	
                }  
            }  
        }); 

});

function selectAll(){
	pageNoAll='1';
	  $('#pp').pagination({
	        pageNumber:1
	     
	    });
	findDepart('','','');
}
/*
 显示加载中遮罩
 */
function showShade(show , text){
    if(true == show){
        if(text != undefined){
            $('.shadeBox .tipsInfo p').html(text);
        }
        $('.shadeBox').show();
        wrapMaskShow();
    }else{
        $('.shadeBox').hide();
        wrapMaskHide();
    }

}

function initButton(){
	//
	mapButton['assigned'] = 1;
	mapButton['select'] = 1;
	mapButton['detail'] = 1;
	mapButton['alter'] = 1;
	mapButton['adminYun']=1;
	var menuUrl="/ums/areaManagementAction!goAreaManagementList.html";
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
            	if(mapButton['assigned']==0){
            	  $("#assigned").hide();
              	}
              	if(mapButton['select']==0){
              		$("#select").hide();
              	}
              
            }else{
            	userType=0;
            }
            	findDepart("","","");
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
        checkOnSelect: false,
        selectOnCheck: false,
        columns:[[
            {
                field:'ck',
                title:'',
                checkbox:true,
                width:50,
                align:'left'
            },
            {
                field:'userId',
                title:'用户Id',
                width:220,
                align:'left',
                hidden:true
            },
            {
                field:'userAccount',
                title:'帐号',
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
                field:'roleName',
                title:'角色',
                width:190,
                align:'left'
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
                field:'departName',
                title:'机构',
                width:190,
                align:'left'
            },
            {
                field:'userType',
                title:'是否管理员',
                width:190,
                align:'left',
                formatter: function(value){
                	if(value==1){
                		return "否";
                	}else if(value==2){
                		return "是";
                	}else if(value==0){
                		return "-";

                	}
                
                }
                
            },
            {
                field:'handle',
                title:'操作',
                width:414,
                align:'left',
                formatter: function(value,row,index){
                	var buttonChoice='';
                    var detailWorkOrder = '<a href="javascript:;" data-id="1" onclick="roleWin(this,\''+index+'\')">详情</a>';
                    var changeWorkOrder = '<a href="javascript:;" data-id="2" onclick="roleWin(this,\''+index+'\')">修改</a>';
                    
                    var changeUseType ='';
                    if(row.userType=="1"){
                    	changeUseType= '<a href="javascript:;" data-id="2" onclick="changeuUserType(this,\''+index+'\')">设为管理员</a>';
                    }else if(row.userType=="2"){
                        changeUseType= '<a href="javascript:;" data-id="2" onclick="changeuUserType(this,\''+index+'\')">取消管理员</a>';

                    }

                	if(userType!=0){
                		if(mapButton['detail']==1){
                			buttonChoice=buttonChoice+detailWorkOrder;
                		}
                		if(mapButton['alter']==1){
                			buttonChoice=buttonChoice+changeWorkOrder;
                		}
                	    if(mapButton['adminYun']==1){
                			buttonChoice=buttonChoice+changeUseType;

                	    }
                	}else{
                		buttonChoice=detailWorkOrder+changeWorkOrder+changeUseType;
                	}
                	if(row.userType=="0"){
                		buttonChoice=detailWorkOrder+changeWorkOrder;
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
function addbutton(){
    $('#roleWin').append('<div id="button" class="the-bottom clearfix" ><span class="quxiao fl" >取消</span><span class="comfirm fl" >保存</span></div>');

}


function changeuUserType(obj,index){
    var checkedItem = $('#dg').datagrid('getData').rows[index];
    if(checkedItem.userType=="1"){
    	createConfirm("消息提醒","设为管理员后，该账号能够在联享家运维版APP上查看到管理员数据，确认设为管理员吗？",true,false,"确认","取消",changeType,"",checkedItem,"");
    }else if(checkedItem.userType=="2"){
    	createConfirm("消息提醒","取消管理员后，该账号无法在联享家运维版APP上查看到管理员数据，确认取消管理员吗？",true,false,"确认","取消",changeType,"",checkedItem,"");
    }

}

function changeType(checkedItems){
	       var userType;
          if(checkedItems.userType==2){
        	  userType="1";
          }else{
        	  userType="2";
          }
		 $.ajax({  
		        type : "POST",  //提交方式  
		        url : "areaManagementAction!changeUserType.html",//路径  
		        dataType : "json",//数据，这里使用的是Json格式进行传输  
		        data:  {"userId":checkedItems.userId,"userType":userType},  
		        success : function(result) {//返回数据根据结果进行相应的处理  
		            if ( result.success) { 
		            	findDepart("","","");
		                 return;
		            } else {  

		            }  
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
            findDepart('','','') ;   
        }
    });
    $(".pagination-page-list").parent().append("条");
    $(".pagination-page-list").parent().prepend("共计"+total+"条,每页显示： ");
	
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
//分配区域
function initTreeArea(parentId,target,layer,userId,status){
	 $.ajax({  
         type : "POST",  //提交方式  
         url : "areaManagementAction!getSystemArea.html",//路径  
 		 data:{"id":parentId,"layer":layer,"userId":userId,"status":status},
 		 beforeSend: function () {
           showShade(true , "耗时操作，数据加载中");
        	},
         dataType : "json",//数据，这里使用的是Json格式进行传输  
         success : function(result) {//返回数据根据结果进行相应的处理  
        	   if ( result.success) {  
               	var treejson;
               	treejson=result.obj.menuPro;
               	if(parentId==''){
                 	append2(treejson);
                   	initdataList(result.obj.authorizeArea);

            	 }else{
            		 $('#box2').tree('append', {
            				parent: target,
            				data:treejson
            		 })

            	 }
               showShade(false , "耗时操作，数据加载中");

               } else {       	
               }     
         }  
     }); 
	
}


function initTreeArea2(parentId,target,layer,userId,status){
	 $.ajax({  
        type : "POST",  //提交方式  
        url : "areaManagementAction!getSystemArea.html",//路径  
		 data:{"id":parentId,"layer":layer,"userId":userId,"status":status},
		 async: false,
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        success : function(result) {//返回数据根据结果进行相应的处理  
       	   if ( result.success) {  
              	var treejson;
              	treejson=result.obj.menuPro;
           		 $('#box2').tree('append', {
           				parent: target,
           				data:treejson
           		 })

              } else {       	
              }     
        }  
    }); 
	
}
function checkSelect(datajson,data){
	if(userType==0){
		  for(var i=0;i<data.length;i++){
			  var tempId=data[i].id;
			  var treeAll= $('#box2').tree('getRoots');
			  for(var j=0;j<treeAll.length;j++){
				  if(treeAll[j].id==tempId){
					  $('#box2').tree('append', {
							parent: treeAll[j].target,
							data:data[i].children
					 })
				  }
			  }
		  }
	
   }
	if(datajson!=null){
	   for(var i=0;i<datajson.length;i++){
		
		   var id= datajson[i].areaId;
		   var areaNode = $('#box2').tree('find', id);
		   if(areaNode!=null){
			   $("#box2").tree('check', areaNode.target);
		   }       		   
	   }
   }

}
function append2(treejson){
	  //树数据渲染
    $('#box2').tree({
    	data : treejson,
        animate : true,
        checkbox : true,
        cascadeCheck : true,
        onlyLeafCheck : false,
        lines : false,
        dnd : false,
        onCheck : function (node,checked) {

            moveRight2(node);
         /*   if(userType==0){
                moveRight(node);
            }else{
                moveRight2(node);
            }*/

        },  
        onBeforeExpand:function(node,param){                         
      /*     var nodeParent=node;
           var i=0;
           var children=$('#box2').tree('getChildren', node.target)  
           //判断重复加载                                                     
           if(children==null||children.length==0){
               //判断层数
        	   while(nodeParent != null){  
        		   nodeParent = $('#box2').tree('getParent', nodeParent.target)  
        		   i++;  
        	   } 
        	   //少于3层
        	   if(i<3){
        		   initTreeArea2(node.id,node.target,i);
        	   }
        }*/

        } 
    });
    
 /*  var root = $('#box2').tree('getRoot');  
    $("#box2").tree('uncheck',root.target); */
    
}
//查询
function findDepart(departId,pageSize,pageNo){
	
	var userAccount=$('#userAccount').val();
	var name=$('#name').val();
	var mobile=$('#mobile').val();
	var roleName=$('#roleName').val();
	departId=departIdChange;
	pageNo=pageNoAll;
	pageSize=pageSizeAll;
	var areaQueryBean='{"userAccount":"'+userAccount+'","name":"'+name+'","mobile":"'+mobile+
	'","roleName":"'+roleName+'","departId":"'+departId+'","pageNumber":"'+pageNo+'","pageSize":"'+pageSize+'"}';
    $.ajax({  
        type : "POST",  //提交方式  
        url : "areaManagementAction!getUserAccountByDepartId.html",//路径  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        data:  {"areaQueryBean":areaQueryBean},  
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

function cancelLeft() {
    win.find(".list-delete").on("click",function(){
        var wrap = $(this).parent();

        var dataId = wrap.attr("data-id");
        var cancelNode = $('#box2').tree('find', dataId);
        if(cancelNode!=null){
        	$("#box2").tree('uncheck', cancelNode.target);
        }

        wrap.remove();
    });
}

function moveRight(node){

    var nodeParent=node;
    var i=0
    while(nodeParent != null){  
		   nodeParent = $('#box2').tree('getParent', nodeParent.target)  
		   i++;  
	   }
    //1层为省级
	if(i==1){
		//String provinceId,String provinceName,String cityId,String cityName,String layer
		var provinceId=node.id;
		var provinceName=node.text;
		var cityId="";
		var cityName="";
		var layer=1;
		
		if(node.checked){
			
			$.ajax({  
				type : "POST",  //提交方式  
				url : "areaManagementAction!getSystemAreaDy.html",//路径  
				dataType : "json",//数据，这里使用的是Json格式进行传输  
	        	data:  {"provinceId":provinceId,"provinceName":provinceName,"cityId":cityId,"cityName":cityName,"layer":layer},  
	        	success : function(result) {//返回数据根据结果进行相应的处理  
	        		if ( result.success) {  
	        			var data=result.obj;
	        			reMove(provinceId);
	        			initdataList(data);
	        		} else {  
	            	
	        		}  
	        	} ,
	        	error: function (result) {
	               alert("1111111111111111");
	            } 
	    }); 
		}else{
			reMove(provinceId);
		}
		
	 //2层为市级
	}else if(i==2){
		var nodeParentTemp = $('#box2').tree('getParent', node.target)  
		var provinceId=nodeParentTemp.id;
		var provinceName=nodeParentTemp.text;
		var cityId=node.id;
		var cityName=node.text;
		var layer=2;
		if(node.checked){

	    $.ajax({  
	        type : "POST",  //提交方式  
	        url : "areaManagementAction!getSystemAreaDy.html",//路径  
	        dataType : "json",//数据，这里使用的是Json格式进行传输  
	        data:  {"provinceId":provinceId,"provinceName":provinceName,"cityId":cityId,"cityName":cityName,"layer":layer},  
	        success : function(result) {//返回数据根据结果进行相应的处理  
	            if ( result.success) {  
	            	var data=result.obj;
	            	reMove1(cityId);
	            	initdataList(data);
	            } else {  
	            	
	            }  
	        }  ,
        	error: function (result) {
	               alert("1111111111111111");
	            } 
	    }); 
		}else{
			reMove1(cityId);
		}
	//区级
	}else if(i==3){
		var list=$(".list");
		var j=0;
		if(node.checked){
			for(var i=0;i<list.length;i++){
				var id= $(list[i]).attr("data-id");
				if(node.id==id){
					j=1;
					break;
			}
		}
		if(j==0){
			var cityNode= $('#box2').tree('getParent', node.target);  
			var provinceNode=$('#box2').tree('getParent', cityNode.target); 
			var xiaoquming = 	'<div class="list"' + 
				                'data-text="'+node.text+'" data-id="'+ node.id +'" '+
				                'data-cityText="'+cityNode.text+'" data-cityId="'+cityNode.id +'"' +
				                'data-provinceText="'+provinceNode.text+'" data-provinceId="'+ provinceNode.id +'">'+
								'<div>'+ node.text +'</div>'+
								'<div class="list-delete"></div>'+
								'</div>';
			win.find(".right-name").append(xiaoquming);
			}
		}else{
			   var test=$("[data-id="+node.id+"]").remove();

		}
	}

}

function moveRight2(node){

    var nodeParent=node;
    var i=0
    while(nodeParent != null){  
		   nodeParent = $('#box2').tree('getParent', nodeParent.target)  
		   i++;  
	   }
    //1层为省级
	if(i==1){
		//String provinceId,String provinceName,String cityId,String cityName,String layer
		var provinceId=node.id;
		var provinceName=node.text;
		var cityId="";
		var cityName="";
		var layer=1;
		reMove(provinceId);
		if(node.checked){
			 var  nodeCity = $('#box2').tree('getLeafChildren', node.target);
			 for(var i=0;i<nodeCity.length;i++){
				 var areaNode=$('#box2').tree('getLeafChildren', nodeCity[i].target);
				 for(var j=0;j<areaNode.length;j++){
					 var nodeOne=areaNode[j];
					 initdata2(nodeOne.text,nodeOne.id,nodeCity[i].text,nodeCity[i].id,provinceName,provinceId);
				 }
			 }

	          
		}else{
			reMove(provinceId);
		}
		
	 //2层为市级
	}else if(i==2){
		var nodeParentTemp = $('#box2').tree('getParent', node.target)  
		var provinceId=nodeParentTemp.id;
		var provinceName=nodeParentTemp.text;
		var cityId=node.id;
		var cityName=node.text;
		var layer=2;
		if(node.checked){
			 var areaNode=$('#box2').tree('getLeafChildren', node.target);
			 for(var j=0;j<areaNode.length;j++){
				 var nodeOne=areaNode[j];
				 initdata2(nodeOne.text,nodeOne.id,cityName,cityId,provinceName,provinceId);
			 }
	    
		}else{
			reMove1(cityId);
		}
	//区级
	}else if(i==3){
		var list=$(".list");
		var j=0;
		if(node.checked){
			for(var i=0;i<list.length;i++){
				var id= $(list[i]).attr("data-id");
				if(node.id==id){
					j=1;
					break;
			}
		}
		if(j==0){
			var cityNode= $('#box2').tree('getParent', node.target);  
			var provinceNode=$('#box2').tree('getParent', cityNode.target); 
			var xiaoquming = 	'<div class="list"' + 
				                'data-text="'+node.text+'" data-id="'+ node.id +'" '+
				                'data-cityText="'+cityNode.text+'" data-cityId="'+cityNode.id +'"' +
				                'data-provinceText="'+provinceNode.text+'" data-provinceId="'+ provinceNode.id +'">'+
								'<div>'+ node.text +'</div>'+
								'<div class="list-delete"></div>'+
								'</div>';
			win.find(".right-name").append(xiaoquming);
			}
		}else{
			   var test=$("[data-id="+node.id+"]").remove();

		}
	}
	cancelLeft() ;
}
function reMove(nodeId){
	var list=$("[data-provinceId="+nodeId+"]").remove();
}

function reMove1(nodeId){
	var list=$("[data-cityId="+nodeId+"]").remove();
}
function initdataList(data){
	for(var i=0;i<data.length;i++){
		var xiaoquming = 	'<div class="list"' + 
		' data-text="'+data[i].areaName+'" data-id="'+ data[i].areaId+'" data-cityText="'+data[i].cityName+'" '+
		' data-cityId="'+ data[i].cityId+'" '+
		' data-provinceText="'+data[i].provinceName+'" data-provinceId="'+ data[i].provinceId+'">'+
		'<div>'+ data[i].areaName +'</div>'+
		'<div class="list-delete"></div>'+
		'</div>';
		win.find(".right-name").append(xiaoquming);
	}
    cancelLeft();
}
//
function confirmData1(checkedItems){
	var list=$(".list");
	var arr1 = new Array() ;
	var toStr='';
	for(var i=0;i<list.length;i++){
        if(i==0){
        	toStr=$(list[i]).attr("data-id");	
		}else{
			toStr=toStr+"#"+$(list[i]).attr("data-id");
		}
	}
	var ids="";
	for(var i=0;i<checkedItems.length;i++){
		if(i==0){
			ids=checkedItems[i].userId;	
		}else{
			ids=ids+"#"+checkedItems[i].userId;
		}
	}
	$.ajax({  
		      type : "post",  //提交方式  
		      url : "areaManagementAction!addUserArea.html",//路径  
		      dataType : "json",//数据，这里使用的是Json格式进行传输  
		      data:  {"ids":ids,"areaList":toStr},  
		      beforeSend: function () {
		            showShade(true , "耗时操作，数据保存中");

		        	},
		      success : function(result) {//返回数据根据结果进行相应的处理  
		            if ( result.success) { 
			            showShade(false , "");
		            	infoMask("保存成功");
		                  return;
		            } else {  
		            	infoMask("保存失败");

		            }  
		        }  
		    }); 

}


/*点击创建，详情，编辑  弹窗*/
function roleWin(obj,index){
    var title = "";
    addbutton();
    var dataId = $(obj).attr("data-id");
    win.find(".right-name").empty();
    var checkedItems=new Array() ;
    if(dataId == 0){
    	checkedItems= $('#dg').datagrid('getChecked');
    	
    	if(checkedItems.length<=0){
    		warnMask('请选择需要分配的用户');
    		return;
    	}
        initTreeArea('','','','','add');  
        title = "分配责任区域";
        $(".the-bottom").show();
        $(".detail-wrap").css("height","358px");
		showShade(false , "耗时操作，数据加载中");
    }else if(dataId == 1){
        title = "详情";
        var checkedItem = $('#dg').datagrid('getData').rows[index];
        checkedItems[0]=checkedItem;
        $(".the-bottom").hide();
        $(".detail-wrap").css("height","439px");
        initTreeArea('','','',checkedItems[0].userId,'detail');  

/*        initMenuAjax(checkedItems[0].userId);
*/    }else if(dataId == 2){
        title = "修改";
        var checkedItem = $('#dg').datagrid('getData').rows[index];
        checkedItems[0]=checkedItem;

        $(".the-bottom").show();
        $(".detail-wrap").css("height","358px");
        initTreeArea('','','',checkedItems[0].userId,'alter');  

/*        initMenuAjax(checkedItems[0].userId);
*/    }else{

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
    	if(dataId != 1){
    		confirmData1(checkedItems);
    	}
        $('#roleWin').window('close');
    	$('#button').remove();
    })



}

function initMenuAjax(userId){
	$.ajax({  
        type : "POST",  //提交方式  
        url : "areaManagementAction!initUserArea.html",//路径
        data:  {"userId":userId},  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
            	  var datajson=result.obj;
                  initMenuAjax2(userId,datajson)
            } else {  
            	
            }  
        }  
    }); 
	
}

function initMenuAjax2(userId,datajson){
	
	if(userType=='0'){
		$.ajax({  
			type : "POST",  //提交方式  
			url : "areaManagementAction!initUserAreaLeft.html",//路径
			data:  {"userId":userId},  
			dataType : "json",//数据，这里使用的是Json格式进行传输  
			beforeSend: function () {
				},
				success : function(result) {//返回数据根据结果进行相应的处理  
					if ( result.success) {  
						var data=result.obj;
						checkSelect(datajson,data);
						$('#box2').tree('collapseAll');
						showShade(false , "耗时操作，数据加载中");
					} else {  
            	
					}  
				}  
    }); 
		
	}else{
		checkSelect(datajson,'');
		$('#box2').tree('collapseAll');
		showShade(false , "耗时操作，数据加载中");
	}
}

function initdata2(areaName1,areaId1,cityName1,cityId1,provinceName1,provinceId1){
	
		var xiaoquming = '<div class="list"'+
		 'data-text="'+areaName1+'" data-id="'+ areaId1 +'"'+
		 'data-cityText="'+cityName1+'" data-cityId="'+ cityId1+'"'+
         'data-provinceText="'+provinceName1+'" data-provinceId="'+ provinceId1+'">'+
        '<div>'+ areaName1 +'</div>'+
        '<div class="list-delete"></div>'+
        '</div>';
        win.find(".right-name").append(xiaoquming);
    
   
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