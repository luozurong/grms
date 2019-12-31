//需要隐藏的按钮
var mapButton = {};
var userType;
$(function(){
	initButton();
});
function initButton(){
	//
	mapButton['alterUserDetail'] = 1;
	mapButton['alterMobile'] = 1;
	mapButton['alterPassword'] = 1;

	var menuUrl="/ums/personalAction!goPersonal.html";
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
            	if(mapButton['alterUserDetail']==0){
            	  $("#alterUserDetail").hide();
              	}
              	if(mapButton['alterMobile']==0){
              		$("#alterMobile").hide();
              	}
              	if(mapButton['alterPassword']==0){
            	  	$("#alterPassword").hide();
              	}
            }else{
            	userType=0;
            }
            	refresh();
            } else {  
            	
            }  
        }  
    }); 
    
}
function refresh(){
	  $.ajax({  
          type : "POST",  //提交方式  
          url : "personalAction!initUserDetail.html",//路径  
          dataType : "json",//数据，这里使用的是Json格式进行传输  
          success : function(result) {//返回数据根据结果进行相应的处理  
              if ( result.success) {  
              	var data;
              	data=result.obj;
              	init(data);
              } else {  
              	
              }  
          }  
      }); 
}

function init(data){
	$('#userAccount').val(data[0].userAccount);
	$('#userAccount').attr("disabled","disabled");
	$('#userDetailId').val(data[0].userDetailId);

	$('#roleName').val(data[0].roleName);
	$('#roleName').attr("disabled","disabled");

	$('#nickName').val(data[0].nickname);
	$('#name').val(data[0].name);
	$('#mail').val(data[0].email);	
}
function alterDetail(){
	var nickName=$('#nickName').val().trim();
	if(isNull(nickName)&&isLength(nickName)){
		warnMask("请输入昵称(50个字符内)");
		return;
	}
	var name=$('#name').val().trim();
	if(!validateName(name)){
		warnMask("请输格式正确的中文名");
		return;
	}
	var mail=$('#mail').val().trim();
	if(!isNull(mail)){
		if(!validateEmail(mail)){
			warnMask("请输格式正确的邮箱");
			return;
		}
	}
	var userDetailId=$('#userDetailId').val();

	  $.ajax({  
        type : "POST",  //提交方式  
        url : "personalAction!alterUserDetail.html",//路径  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        data:{"nickName":nickName,"name":name,"email":mail,"userDetailId":userDetailId},
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
            	infoMask("修改成功");
            	refresh();
            } else {  
            	
            }  
        }  
    }); 
 
}
function sendMessage(){
	var userAccount=$('#userAccount').val();
	  $.ajax({  
	        type : "POST",  //提交方式  
	        url : "personalAction!sendMessage.html",//路径 
	        data:{"userAccount":userAccount},
	        dataType : "json",//数据，这里使用的是Json格式进行传输  
	        success : function(result) {//返回数据根据结果进行相应的处理  
	            if ( result.success) {  
	            	infoMask("发送验证码成功");
	            } else {
	            	infoMask(result.msg);
	            }  
	        }  
	    }); 

}

function alterMobile(){
	var userAccount=$('#userAccount').val();
	var code=$('#code').val();
	if(isNull(code)){
		warnMask("请输入验证码");
		return;
	}
	var mobileNew=$('#mobileNew').val();
	if(!validatePhone(mobileNew)){
		warnMask("请输入格式正确的手机号码");
		return;
	}
	  $.ajax({  
	        type : "POST",  //提交方式  
	        url : "personalAction!alterMobile.html",//路径 
	        data:{"userAccount":userAccount,"code":code,"mobileNew":mobileNew},
	        dataType : "json",//数据，这里使用的是Json格式进行传输  
	        success : function(result) {//返回数据根据结果进行相应的处理  
	            if ( result.success) {  
	            	infoMask("修改手机号码成功");
	            } else {
	            	infoMask(result.msg);
	            }  
	        }  
	    }); 

}

function alterPassword(){
	var userAccount=$('#userAccount').val();
	var password=$('#password').val();
	if(isNull(password)){
		warnMask("请输入旧密码");
		return;
	}
	var passwordNew=$('#passwordNew').val();
	if(!validatePasswrod(passwordNew)){
		warnMask("请输入正确格式的新密码");
		return;
	}
	if(password==passwordNew){
		warnMask("新密码与旧密码不能一样");
		return;
	}
	var passwordRe=$('#passwordRe').val();
	if(!(passwordNew==passwordRe)){
		warnMask("请输入的确认密码与新密码一致");
		return;
	}
    
	  $.ajax({  
	        type : "POST",  //提交方式  
	        url : "personalAction!alterPassword.html",//路径 
	        data:{"userAccount":userAccount,"password":password,"passwordNew":passwordNew,"passwordRe":passwordRe},
	        dataType : "json",//数据，这里使用的是Json格式进行传输  
	        success : function(result) {//返回数据根据结果进行相应的处理  
	            if ( result.success) {  
	            	infoMask("修改密码成功");
	            } else {
	            	infoMask(result.msg);
	            }  
	        }  
	    }); 

}

