/**
 * Created by dell on 2017/1/9.
 */
$(function() {
	/*var sessionInfo_userId = '${sessionInfo.userId}';
	if (sessionInfo_userId) { 目的是，如果已经登陆过了，那么直接跳转到index页面 
		window.location.href = "index.jsp";
	}*/
	 
	//$("#error-info-detail").css('background','url("")');
	 var msgs = $("#error-info-detail").html();
	 if(null==msgs||msgs==''){
		 $("#error-info-detail").css('display','none');
	 }
		 
	$('.register').click(function(){ 
		//var password = hex_md5($("#password").val()).toUpperCase();
		//$("#error-info-detail").css('display','none');
		 $("#error-info-detail").css('display','none');
		var password = $("#password").val();
		var userAccount = $("#userAccount").val();
        $("#error-info-detail").html("");
		if(userAccount==null||userAccount==''){
			$("#error-info-detail").prepend("<p>手机号不能为空</p>");
			$("#error-info-detail").css('display','block'); 
			return false;
		}
		
		/*var pattern = /^1[34578]\d{9}$/; 
    	if(!pattern.test($.trim(userAccount))){
    		$("#error-info-detail").prepend("<p>手机号格式不对</p>");
			$("#error-info-detail").css('display','block'); 
			return false;
    	}*/
        if(password==null||password==''){
        	$("#error-info-detail").prepend("<p>密码不能为空</p>"); 
        	$("#error-info-detail").css('display','block'); 
        	return false;
		}
        
        var isShow = $('#codeShowOrHide').val();
        var isTure= checkValidateCodeShowOrHide();
        //判断是否超过错误的数次 超过的时候就弹出小窗口
		 if(isTure=='true'||isShow=='show'){//弹窗验证通过了 并且存在 条件 密码不正确的时候 进入 验证码过后  再进来时候  
			 if(isTure!='true'||isShow=='hide'){
				 console.log('-------------------1-------------------');
				// $('#loginForm').submit();
				 login();
				 return false;
			 }
			 $('#codeShowOrHide').val("show");
			 validateCodeShowOrHide();
			 return false;
		 }
        //---------------------------
        /*if(isTure=='true'){//弹窗验证通过了 并且存在 条件 密码不正确的时候 进入 验证码过后  再进来时候  
			 validateCodeShowOrHide();
			 return false;
		 }*/
		 //----------------------------
		 console.log('-------------------2-------------------');
		//$('#loginForm').submit();
		 login();
		//$("#error-info-detail").css('display','block'); 
	});
	
	//点击enter 键确认
	 $(document).keydown(function(event){
		    if(event.keyCode==13){
		    	if($('#codeShowOrHide').val()!='show'){
		    		 $(".register").click();
		    	}
		    }
	 });
	
	$('.password-delete').click(function(){ 
        $("#password").val("");
        $(this).hide();
	});
	
	$('.phone-delete').click(function(){ 
		$("#userAccount").val("");
        $(this).hide();
	});

    /*用户名、密码等等input输入框:取得焦点时，边框高亮显示; 有输入内容时，显示右边叉号；无内容则隐藏叉号 */
    focusColorAndShowOrHideX("#userAccount",".phone-delete");
    focusColorAndShowOrHideX("#password",".password-delete");


	
	$('.confirm').click(function(){ 
		//var  verification_code = $('.verification_code').val();
		
		//提交验证码  后台判断 不对的话直接返回到登陆页面
		checkValidateCode();
		
		/*if(verification_code==vevifiCode){
			if(vevifiCode!=null&&vevifiCode!=null){
				$(".mask").css('display','none');
				$(".popup").css('display','none');
			}
			
		}else{
			$(".popup-errordetail").css('display','block');
			$(".popup-errordetail").html("验证码错误");
		}*/
	});
	
	$('.validateCodeImg').click(function(){ 
		changValidateCode();
	});
	
	//点击删除显示的图形 页面
	$('.popup-delete').click(function(){
		$(".mask").css('display','none');
		$(".popup").css('display','none');
		$('#codeShowOrHide').val("");
	});
	
});

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
			 //调用提交登录按钮……
			 $(".register").trigger("click");
		}else{
			changValidateCode();
			$(".popup-errordetail").css('display','block');
			$(".popup-errordetail").html("验证码错误");
		}
	});

}


/**
 * 判断验证码显示区域的显示和隐藏
 */
/*function  checkValidateCodeShowOrHide(){
	//多少密码错误显示验证码
	var passErrerTimsLimitVevifiCodeNeed=${passErrerTimsLimitVevifiCodeNeed};//多少密码错误显示验证码
	var userAccount=$('#userAccount').val();
		userAccount=$.trim(userAccount);
	$.get('/ums/getLoginPassErrerTimes',{
		userAccount:userAccount,
		r:new Date().getTime()
	},function(data){
		//alert("用户当天连续输入密码错误次:"+data);
		var passErrerTimes=parseInt(data);
		$('#passErrerTimes').val(passErrerTimes);
		if(passErrerTimes>=passErrerTimsLimitVevifiCodeNeed){
			return true;
		}
	});

}*/

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
window.onload=function(){
    $("#password").val("");
    $("#userAccount").val("");
};

function login(){
	var password = $("#password").val();
	var userAccount = $("#userAccount").val();
	$.getJSON('/ums/userAction!login.html',{
		userAccount:userAccount,
		password:password,
		q:new Date().getTime()
	},function(data){
		console.log(data.success==true);
		if(data.success==true){
			window.location.href = "login.jsp";
		}else{
			$("#error-info-detail").css('display','block'); 
			$('#error-info-detail').html(data.msg);
		}
	});
}