$(function() {
	 $(".error-tip").css('display','none');
	 $("#message-fl").val('');
	$('#get-message').click(function(){ 
		clickGetCode();
	});

    /*用户名、密码等等input输入框:取得焦点时，边框高亮显示; 有输入内容时，显示右边叉号；无内容则隐藏叉号 */
	focusColorAndShowOrHideX("#account",".delete-three");
    focusColorAndShowOrHideX("#inphone",".delete-first");
    focusColorAndShowOrHideX("#message-fl",".delete-second");
    focusColorAndShowOrHideX("#first-password-fl",".delete-first");
    focusColorAndShowOrHideX("#second-password-fl",".delete-second");

	
	$("#inphone").blur(function() {
		//$(".reset-box .error-tip .error-info").css('display','none');
		//var reg = /^1[34578]\d{9}$/; 
		var reg = /^1\d{10}$/; 
		if(!reg.test($("#inphone").val())) {
			if($("#inphone").val() == "") {
				$(".error-tip").html("请填写手机号码");
				 $(".error-tip").css('display','block');
				} else {
				   $(".error-tip").html("手机格式错误");
				   $(".error-tip").css('display','block');
				}
		} else {
			$(".error-tip").css('display','none');
		   $(".error-tip").html("");
		}
	
	});
	
	$("#message-fl").blur(function() {
		//$(".reset-box .error-tip .error-info").css('display','none');
		/*var reg = /^1[34578]\d{9}$/; */
		//四位混合
		//var reg = /^(?!\D+$)(?![^a-zA-Z]+$)\S{4}$/;
		var reg = /^\d{4}$/; //六位数字
		if(!reg.test($("#message-fl").val())) {
			if($("#message-fl").val() == "") {
				$(".error-tip").html("验证码不能为空");
				 $(".error-tip").css('display','block');
				} else {
				   $(".error-tip").html("验证码格式不对");
				   $(".error-tip").css('display','block');
				}
		} else {
			$(".error-tip").css('display','none');
		   $(".error-tip").html("");
		}
	
	});
	
	$('#next-step').click(function(){

        $(".delete-first").hide();
        $(".delete-second").hide();
		//var pattern = /^1[34578]\d{9}$/;
		var pattern = /^1\d{10}$/;
		var phone = $('.inphone').val();
		phone = $.trim(phone); 
		if(phone==''){
			 $(".error-tip").html("请填写手机号码");
			 $(".error-tip").css('display','block');
			 return false;
		}
		if(!pattern.test(phone)){
			 $(".error-tip").html("手机格式不对");
			 $(".error-tip").css('display','block'); 
			 return false;
		}
		var message = $('#message-fl').val();
		message = $.trim(message);
		if(message==''){
			 $(".error-tip").html("请填写验证码");
			 $(".error-tip").css('display','block'); 
			 return false;
		}
		 //验证短信验证 暂时不做先  通过继续 不通过的时候提示
		var pass = getCode("mobile_check_code",''); 
		
		if(pass!='checkAccountAndCode'){
			 $(".error-tip").html(pass);
			 $(".error-tip").css('display','block'); 
			 $('#message-fl').val("");
			return false;
		}
		 
		   //验证完后 清除数据
	    $(".error-tip").css('display','none');
		$(".error-tip").html("");
	    $(".reset-box").css('display','block');
	    $(".find-box").css('display','none');
		
		
		
	});
	
	$('.delete-first').click(function(){ 
		 $(".inphone").val("");
         $(this).hide();
	});
	$('.delete-second').click(function(){ 
		$(".fl").val("");
        $(this).hide();
	});
	
	$('#return-step').click(function(){
		history.back(-1);  
	});
	
	/**
	 * 找回密码 第一个密码删除
	 */
	$('.delete-first').click(function(){
		 /*$(".first-password input[type='text']").each(function(){
			   $(this).val("")
		  });*/
		$('#first-password-fl').val('');
	});
	
	/**
	 * 找回密码 第二个输入密码删除
	 */
	$('.delete-second').click(function(){ 
		$('#second-password-fl').val('');
		/*$(".second-password input[type='text']").each(function(){
			$(this).val("")
		});*/
	});
	
	/**
	 * 6到20位的数字和字母组合
	 */
	  
	  
	
	$("#first-password-fl").blur(function() {
		//$(".reset-box .error-tip .error-info").css('display','none');
		var reg = /^(?!\D+$)(?![^a-zA-Z]+$)\S{6,20}$/;
		if(!reg.test($("#first-password-fl").val())) {
			$("#reset-password-info").css('display','block');
			if($("#first-password-fl").val() == "") {
				   $("#reset-password-info").html('登录密码不可以为空！');
				} else {
				   $("#reset-password-info").html("密码格式错误");
				   $("#first-password-fl").val("");
				}
		} else {
			$("#reset-password-info").css('display','none');
		   $("#errorInfo2").html("");
		}
	
	});
	
	
	$("#second-password-fl").blur(function() {
		//$(".reset-box .error-tip .error-info").css('display','none');
		var reg = /^(?!\D+$)(?![^a-zA-Z]+$)\S{6,20}$/;
		if(!reg.test($("#second-password-fl").val())) {
			$("#reset-password-info").css('display','block');
			if($("#second-password-fl").val() == "") {
				$("#reset-password-info").html('确认密码不可以为空！');
			} else {
				$("#reset-password-info").html("确认密码格式错误");
				$("#second-password-fl").val("");
			}
		} else {
			$("#reset-password-info").css('display','none');
			$("#errorInfo2").html("");
		}
		
	});
	
	$('#reset-next-step').click(function(){ 
		var basePW = $("#first-password-fl").val();
		var validatePW = $("#second-password-fl").val();
		$("#reset-password-info").css('display','block');
		if(basePW==""){
			$("#reset-password-info").html('登录密码不可以为空！');
			return false;
		}
		if(validatePW==""){
			$("#reset-password-info").html('确认密码不可以为空！');
			return false;
		}
		if(basePW!=validatePW){
			$("#reset-password-info").html('两次输入密码不一致');
			return false;
		}
		$("#reset-password-info").css('display','none');
		//提交表单  保存更改密码
		var saveBackMsg =  saveResetPw();
		if(saveBackMsg!='successful'){
			$("#reset-password-info").html(saveBackMsg);
			return false;
		}
		$("#reset-password-info").css('display','none');
		
		$(".reset-box").css('display','none');
		$(".reset-ok").css('display','block');
		
		
		
	});
	
	$('#return-next-step').click(function(){
		$(".error-tip").css('display','none');
		$(".error-tip").html("");
	    $(".find-box").css('display','block');
	    $(".reset-box").css('display','none'); 
	});
	
	$('#gotoLogin').click(function(){ 
		history.back(-1);  
	});
	
	
	$('.validateCodeImg').on('click',function(){
		changValidateCode();
	});

	$('.confirm').click(function(){ 
		//提交验证码  后台判断 不对的话直接返回到登陆页面
		checkValidateCode();
	});
	
	//点击删除显示的图形 页面
	$('.popup-delete').click(function(){
		$(".mask").css('display','none');
		$(".popup").css('display','none');
	});
	
});

function getCode(doneType,isGetCode){
	//var mobile  = $(".inphone").val("");
	var pass = "";
	var mobile = $.trim($(".inphone").val());
	if(doneType=='getcode'){
		params =	{
				mobile:mobile,
				doneType:doneType,
				isGetCode:isGetCode,
				r:new Date().getTime()
				
		 };
	}
	if(doneType=='mobile_check_code'){
		 var message = $('#message-fl').val();
		 var check_code = $.trim(message);
		params =	{
				mobile:mobile,
				check_code:check_code,
				doneType:doneType,
				r:new Date().getTime()
				
		};
	}
	$.ajax({
		type: "POST",
		url: "/ums/getCodeDyByMobile",
		async: false,
		data: params,
		success: function(msg){
			 if(msg == 'sendRandonNum'){
				// alert('验证码已经发送到手机，请查看！');
				//$.messager.alert('', '验证码已经发送到手机，请查看！', 'warning');
				 $.messager.alert({
	        			title:'',
	        			msg:'验证码已经发送到手机，请查看！',
	        			draggable:false
	        		});
			 }else if(msg == 'checkAccountAndCode') {
				pass = msg;
			    return pass;
			}else{
				pass = msg;
			    return pass;
			}
		}
	});
	return pass;
}

//重新获取验证码倒计时方法
var wait = 60;
function time(o) {
    if (wait == 0) {
   		//o.removeClass('wait').addClass('validateCodeImg');
   		o.removeClass('wait');
   		//$('#get-message').css('background-color',"#ff8c40");
   		$("#get-message").css("background-color","#ff8c40");
   		
		o.text('获取验证码');
        wait = 60;
        $('#get-message').unbind("click");
        
    	$('#get-message').click(function(e){
    		clickGetCode();
    	});
	console.debug('timeout');
    } else {
    	o.text(wait+'秒重新发送');
        wait--;
//  	console.debug(wait);
        setTimeout(function() {
            time(o)
        },1000);
    }
}

function beginTimeStart(){
	 $('#get-message').unbind("click");
	 $('#get-message').addClass('wait');
	 //$('#get-message').css("disabled","true");
	 $("#get-message").css("background-color","#c0c0c0");
	time($('#get-message'));	
}



/**
 * 是否弹出小窗口验证
 * 
 */
function validateCodeShowOrHide(){
	 $(".popup-errordetail").css('display','none');
	 $(".mask").css('display','block');
	 $(".popup").css('display','block');
	 changValidateCode();
}

/**
切换验证码
*/
function changValidateCode(){
	$('#validateCodeImg').attr('src','/ums/validateCode?'+new Date().getTime());
	$('.verification_code').val('');
}


/**
 * 判断验证码是否通过
 */
function  checkValidateCode(){
	var vCode=$('.verification_code').val();
	vCode=$.trim(vCode); 
	$.ajaxSetup({ 
	    async : false 
	});
	$.get('/ums/checkValidateCode',{
		vCode:vCode,
		r:new Date().getTime()
	},function(data){
		var isValidate=data;
		if(isValidate=='true'){
			$(".mask").css('display','none');
			$(".popup").css('display','none');
			$('#codeShowOrHide').val("hide");
			// 随带发送验证码给手机
			$("#get-message").trigger("click")
		}else{
			changValidateCode();
			$(".popup-errordetail").css('display','block');
			$(".popup-errordetail").html("验证码错误");
		}
	});

}
/**
 * 获取验证码
 * @returns {Boolean}
 */
function clickGetCode(){
	 $(".error-tip").css('display','none');
	 $(".error-tip").html("");
	var phone =  $('.inphone').val();
	if(phone==null||phone==''){
		 $(".error-tip").html("请填入手机号码");
		 $(".error-tip").css('display','block'); 
		 return false;
	}
	// var pattern = /^1[34578]\d{9}$/; 
	 var pattern = /^1\d{10}$/; 
	if(!pattern.test(phone)){
		 $(".error-tip").html("手机格式不对");
		 $(".error-tip").css('display','block'); 
		 return false;
	}
	//隐藏点击获取的验证码
	//hideGetMessage();
	
	//生成手机验证码
	var backMsg =  getCode("getcode",'');
	var isShow = $('#codeShowOrHide').val();
	if(backMsg=='validateCode'){
		//这个是防刷 已经进行验证了
		if(backMsg!='validateCode'||isShow=='hide'){
			// $('#loginForm').submit();
			 beginTimeStart();
			 getCode("getcode",$('.verification_code').val());
			 $('#codeShowOrHide').val("show");
			 return false;
		 }
		 $('#codeShowOrHide').val("show");
		validateCodeShowOrHide();
		return false;
	}
	if(backMsg!=''){
		 $(".error-tip").html(backMsg);
		 $(".error-tip").css('display','block'); 
		 return false;
	}
		
	//防刷验证------------------------------------------------------------------------
	 beginTimeStart();
	
	 return false;
}

function saveResetPw(){
	var mobile = $.trim($(".inphone").val());
	var pw = $.trim($("#first-password-fl").val());
	var validatepw= $.trim($("#second-password-fl").val());
	 var message = $('#message-fl').val();
	 var check_code = $.trim(message);
	 var pass = "";
	var params =	{
				userAccount:mobile,
				password:pw,
				validatepw:validatepw,
				check_code:check_code,
				r:new Date().getTime()
		};
	
	
	$.ajax({
		type: "POST",
		url: "/ums/modifyPwdServlet",
		async: false,
		data: params,
		success: function(msg){
			 pass = msg;
			 if(msg == 'successful'){
				 //alert("修改密码成功!");
				/* $.messager.alert('系统提示', '修改密码成功!', 'info', function(){
					 //window.location.href = "system/accountmanagement.jsp";
					 return pass;
		            });*/
				 $.messager.alert({
		      			title:'',
		      			msg:'修改密码成功！',
		      			draggable:false,
		      			fn:function(){
		      				return pass;
				            }
		      		});
				 
			 }else{
				// $.messager.alert('', msg, 'warning');
				 $.messager.alert({
	        			title:'',
	        			msg:msg,
	        			draggable:false
	        		});
				 return pass;
			 }
		}
	});
	 return pass;
	 
}
window.onload=function(){
    $("#inphone").val("");
    $("#message-fl").val("");
    $("#first-password-fl").val("");
    $("#second-password-fl").val("");
};
