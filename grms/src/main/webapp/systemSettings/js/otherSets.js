//初始化按钮

var mapButton = {};
mapButton['btn_voice'] = 1;
$(function(){
	initButton();
	getCustomerType();
	getProjectRole();
});

function initButton(){
	var menuUrl="/grms/otherSets/list.html";
    $.ajax({  
        type : "POST",  //提交方式  
        url : "/grms/initButtonController/getButttonList",//路径  
        data : {"menuUrl":menuUrl},
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        async : false,
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {
            	//比较需要隐藏的按钮
            	var buttonObject = result.button;
            	var admin = result.admin;
            	//如果是管理员
            	if(admin){
            		return;
            	}
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
            	if(mapButton['btn_voice']==0){
            	  $("#btn_voice").hide();
              	}
            }
        }  
    }); 
}


//获取客户类型列表
function getCustomerType(){
	$.ajax({
		type:"get",
		url:"/grms/otherSets/getCustomerType",
		async:true,
		dataType: "json",
		success:function(data){
			if(data.length>0){
				for(var i = 0;i < data.length;i ++){
					var id = data[i].id;
					var value = data[i].name;
					add($("#customerTypeBtn"),'customerType',value,id);
				}
				
			}
		}
	});
}

//获取项目角色列表
function getProjectRole(){
	$.ajax({
		type:"get",
		url:"/grms/otherSets/getProjectRole",
		async:true,
		dataType: "json",
		success:function(data){
			if(data.length>0){
				for(var i = 0;i < data.length;i ++){
					var id = data[i].id;
					var value = data[i].name;
					add($("#projectRoleBtn"),'projectRole',value,id);
				}
			}
		}
	});
}

//删除按钮点击
function del(e,id){
	wrapMaskShow();
	$.messager.confirm('确认','您确认想要删除该选项吗？',function(r){  
		wrapMaskHide();
	    if (r){ 
	    	$.ajax({
	    		type:"get",
	    		url:"/grms/otherSets/delete?id="+id,
	    		async:true,
	    		dataType: "json",
	    		success:function(result){
	    			if(result.success){
	    				infoMask("删除成功");
		    			e.parents('.addbox').remove();
	    			}else{
	    				infoMask("删除失败");
	    			}
	    		}
	    	});
	    }    
	}); 
	wrapMaskHide();
}
//添加
function add(e,name,value,id){
	var addboxHtml = '<div class="addbox"><input type="text" name="'+name+'" id="'+id+'" value="'+value+'" maxlength="20" onblur="inputOnBlur(\''+name+'\',\''+value+'\',\''+id+'\')"/><ins onclick="del($(this),\''+id+'\')"></ins></div>';
	e.before(addboxHtml);
	e.prev('.addbox').children('input').focus();
}

//失去焦点 判断内容是否重复
function inputOnBlur(name) {
	if(name=='customerType'){
		var customerTypeName = new Array();
		$('input[name="customerType"]').each(function(i,ele){
			customerTypeName.push($(ele).val());
		});
		console.log(customerTypeName);
		
		for(var n=0;n<customerTypeName.length;n++){
			if (customerTypeName[n]==customerTypeName[n+1]){
				infoMask("已存在重复客户类型");
			}
		}

	}else if(name=='projectRole'){
		var projectRoleName = new Array();
		$('input[name="projectRole"]').each(function(i,ele){
			projectRoleName.push($(ele).val());
		});
		console.log(projectRoleName);
		
		for(var n=0;n<projectRoleName.length;n++){
			if (projectRoleName[n]==projectRoleName[n+1]){
				infoMask("已存在重复项目角色");
			}
		}
	}
	
}


//保存
function saveSet(){
	var customerType="",projectRole="";//
	var customerTypeName="",projectRoleName="";
	var selVal="";
	var customerTypeDom = $('input[name="customerType"]'),
		projectRoleDom = $('input[name="projectRole"]');
	var returnFlag = true;//判断跳出函数关键字

	if(!returnFlag){return false;}//没选关联结束当前函数
	
	$.each(customerTypeDom, function() {
		if($.trim($(this).val()).length!=0){
			if($.trim($(this).attr("id"))==''){
				var randomId1 = randomString();
				$(this).attr("id",randomId1);
				customerType += $.trim($(this).attr("id"))+",";
				customerTypeName += $.trim($(this).val())+",";
			}else{
				customerType += $.trim($(this).attr("id"))+",";	
				customerTypeName += $.trim($(this).val())+",";
			}
		}
	});
	$.each(projectRoleDom, function() {
		if($.trim($(this).val()).length!=0){
			if($.trim($(this).attr("id"))==''){
				var randomId2 = randomString();
				$(this).attr("id",randomId2);
				projectRole += $.trim($(this).attr("id"))+",";
				projectRoleName += $.trim($(this).val())+",";
			}else{
				projectRole += $.trim($(this).attr("id"))+",";
				projectRoleName += $.trim($(this).val())+",";;	
			}
		}
	});
	
	$.ajax({
		type:"post",
		url:"/grms/otherSets/save",
		data:{"customerType":customerType,"customerTypeName":customerTypeName,"projectRole":projectRole,"projectRoleName":projectRoleName},
		async:true,
		dataType : "json",//数据，这里使用的是Json格式进行传输  
		success:function(resultMap){
			
			if ( resultMap.success) { 
				infoMask('保存成功！');
				location.reload();
				//infoMask(data.msg);
			} else {  
				infoMask('保存失败！');
				location.reload();
            }
		}
	});
	
}
//随机生成32位数
function randomString(len) {
	len = len || 32;
	var $chars = 'abcdefghijkmnopqrstuvwxyz0123456789';    /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
	var maxPos = $chars.length;
	var pwd = '';
	for (i = 0; i < len; i++) {
	pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
		}
	return pwd;
}


//文本,显示时间,类型0表示成功的判断图标显示
var sto = '';
function tips(txt,time,typeFlag){
	clearTimeout(sto);
	$('.autotips').show();
	$('.mes').text(txt);
	if(typeFlag == 0){
		$('.success_icon').show().next('ins').hide();
	}else{
		$('.warning_icon').show().prev('ins').hide();
	}
	sto = setTimeout(function(){
		$('.autotips').hide();
	},time)
}
