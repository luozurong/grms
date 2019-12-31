
//	var treejson=[{"children":[{"id":"2","parentId":"1","text":"广州市"}],"id":"1","text":"广东省"}];

//需要隐藏的按钮
var mapButton = {};
var userType;
$(function () {
    configMenuItem("人员管理","组织机构管理");
	
	initButton();
    $.ajax({  
        type : "POST",  //提交方式  
        url : "organizationAction!getSystemOrganization.html",//路径  
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

function refresh(){
    $.ajax({  
        type : "POST",  //提交方式  
        url : "organizationAction!getSystemOrganization.html",//路径  
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
}
function initButton(){
	mapButton['create'] = 1;
	mapButton['edit'] = 1;
	mapButton['delete'] = 1;
	var menuUrl="/ums/organizationAction!goOrganizationList.html";
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
              	if(mapButton['edit']==0){
              		$("#edit").hide();
              	}
              	if(mapButton['delete']==0){
            	  	$("#delete").hide();
              	}
            }else{
            	userType=0;
            }
            } else {  
            	
            }  
        }  
    }); 
    
}

function append( treejson){

    $('#box').tree({
        data : treejson,
        animate : false,
        checkbox : false,
        cascadeCheck : false,
        onlyLeafCheck : true,
        lines : false,
        dnd : false,
        onLoadSuccess:function(node,data){
        }
    });

}
//点击删除按钮
function confirmDelete(node){
	if(node){
        createConfirm("消息提醒","删除后，此机构及下级机构的信息都将被清除，确认删除吗？",true,false,"删除","取消",okCallbakFunciton,"","","");
	
        function okCallbakFunciton(){
            dataDel(node);
        }

		
	}else{
		infoMask('请选择要删除的机构');
	}

}
//判断该用户是否具备操作权限
function check(status){
    var node = $("#box").tree("getSelected");
    if(status=='create'){
    	if(!node){
    		infoMask('请选择要创建机构的父级机构');
    		return;
    	}
    }else if(status=='edit'){
    	if(!node){
    		infoMask('请选择要修改的机构');
    		return;
    	}
    }else if(status=='delete'){
    	if(!node){
    		infoMask('请选择要删除的机构');
    		return;
    	}
    }else{
    	infoMask('程序错误');
		return;
    }
    $.ajax({  
        type : "POST",  //提交方式  
        url : "organizationAction!checkAuth.html",//路径  
        data:{"orgaId":node.id,"status":status},
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
            	if(status=='create'){
            		addOrg(node);
            	}else if(status=='edit'){
            		updateOrg(node);
            	}else if(status=='delete'){
            		confirmDelete(node);
            	}
            } else {  
        		infoMask('您不具备对该机构的操作权限');

            }  
        }  
    }); 
}
//点击创建按钮
function addOrg(node){
	if(node){
		$("#parentOrga").val(node.text);
		$("#parentOrgaId").val(node.id);
		$("#status").val("create")
	}else{
		infoMask('请选择要创建机构的父级机构');
	}
}
function isNull(str) {
	if (str == "") return true;
	var regu = /^[ ]+$/;
	var re = new RegExp(regu);
	return re.test(str);
	}
//点击确定按钮
function confirmAdd(){
	var node = $("#box").tree("getSelected");
	var status=$("#status").val();
	if(status=="create"){
		if(!isNull($("#Orga").val())){
			if(node&&node.text==$("#parentOrga").val()){
				var orga=$("#Orga").val();
				var parentOrgaId=$("#parentOrgaId").val();
				var orgaDesc=$("#orgaDesc").val();
				var success=dataAdd(parentOrgaId,orga,orgaDesc);
		/*		if(success!=1){
					infoMask('后台程序错误');
                    return;
				}*/
			/*	$("#box").tree("append",{  
					parent: node.target,  
					data:     [{  
					"text":$("#Orga").val() ,
					"attributes":$("#orgaDesc").val()
					}]  
				}); */
				$('#box').find('.tree-node-selected').removeClass('tree-node-selected');
				$("#parentOrga").val("");
			}else{
				infoMask('请选择父级机构');
			}
		}else{
            infoMask('请输入要创建的机构名字');
		}
   }else{
		var parentNode = $("#box").tree("getParent",node.target);
		// 更新选择的节点文本
		var orga=$("#Orga").val();
		if(orga.trim()==""){
			infoMask('更新节点名称不能为空');
			return;
		}
		var attributes=$("#orgaDesc").val();
		dataEdit(node.id,orga,attributes);
		if(orga!=""){
			$('#box').tree('update', {
				target: node.target,
				text: orga,
				attributes:attributes
			});
		}
		
	 
 }
	//父ID
	var parentOrga=$("#parentOrga").val();
	var parentOrgaId=$("#parentOrgaId").val();
	
	var orga=$("#Orga").val();
	var orgaId=$("#OrgaId").val();
	var orgaDesc=$("#orgaDesc").val();
	//alert("111111111");
	$("#Orga").val("");
	$("#orgaDesc").val("");
	
	//        data: {"orderId":orderId,"commant":commant},  

}

function dataAdd(parentOrgaId,orga,orgaDesc){
	
    var check_res;

    $.ajax({  
        type : "POST",  //提交方式  
        url : "organizationAction!addSystemOrganization.html",//路径  
        data:  {"parentOrgaId":parentOrgaId,"orga":orga,"orgaDesc":orgaDesc},  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
                check_res="1";
                refresh()
            }  
        }
    }); 
    
    return check_res;
}

function dataEdit(orgaId,orga,orgaDesc){
	
    var check_res;

    $.ajax({  
        type : "POST",  //提交方式  
        url : "organizationAction!editSystemOrganization.html",//路径  
        data:  {"orga":orga,"orgaId":orgaId,"orgaDesc":orgaDesc},  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
                check_res="1";
                infoMask("保存成功");

            } else{
                infoMask("保存失败");
            }
        }
    }); 
    
    return check_res;
}


function dataDel(node){
	
    var check_res;

    $.ajax({  
        type : "POST",  //提交方式  
        url : "organizationAction!delSystemOrganization.html",//路径  
        data:  {"orgaId":node.id},  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
                check_res="1";
                $('#box').tree('remove', node.target);
                if(node.text==$("#parentOrga").val()){
                    $("#parentOrga").val("");
                    $("#Orga").val("");
                }
            }  else{
                infoMask('当前机构或子机构存在关联账号数据，请优先删除账号数据，否则无法删除该机构。');
            }
        }
    }); 
    
    return check_res;
}

//点击修改按钮
function updateOrg(node){

	if(node){
        var parentNode = $("#box").tree("getParent",node.target);

		$("#parentOrga").val(parentNode.text);
		$("#parentOrgaId").val(parentNode.id);
		$("#Orga").val(node.text);
		$("#OrgaId").val(node.id);
		$("#status").val("edit");
		$("#orgaDesc").val(node.attributes);
	}else {
        infoMask('请选择要修改的机构');
    }
	
}
