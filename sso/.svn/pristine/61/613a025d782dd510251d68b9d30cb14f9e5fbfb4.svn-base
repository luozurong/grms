/**保证登陆页面不被其他页面包含*/
if(window!=parent){
	parent.location=window.location;
}

$(function() {
	 var msgs = $("#error-info-detail").html();
	 if(null==msgs||msgs==''){
		 $("#error-info-detail").css('display','none');
	 }
	 
	 checkValidateCodeShowOrHide();
		 
	$('.register').click(function(){ 
		$("#error-info-detail").css('display','none');
		var password = $("#password").val();
		var userAccount = $("#userAccount").val();
        $("#error-info-detail").html("");
		if(userAccount==null||userAccount==''){
			$("#error-info-detail").prepend("<p>手机号不能为空</p>");
			$("#error-info-detail").css('display','block'); 
			return false;
		}
        if(password==null||password==''){
        	$("#error-info-detail").prepend("<p>密码不能为空</p>"); 
        	$("#error-info-detail").css('display','block'); 
        	return false;
		}
       
		login();
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
		//提交验证码  后台判断 不对的话直接返回到登陆页面
		checkValidateCode();
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
	
	
	function login(){
 		//$('.login_info').hide();
 		var userAccount=$('#userAccount').val();
 		userAccount=$.trim(userAccount);
 		var password=$('#password').val();
 		password=$.trim(password);
 		//验证码，当验证码区域是显示时，才需要验证
 		var vevifiCode_div_visible=$('#vevifiCode_div').is(':visible');
 		var vevifiCode='';
 		if(vevifiCode_div_visible){
 			vevifiCode=$('#vevifiCode').val();
	 		vevifiCode=$.trim(vevifiCode);
	 		if(!vevifiCode){
	 			$("#error-info-detail").prepend("<p>请输入验证码</p>");
	 			$('#vevifiCode').focus();
				$("#error-info-detail").css('display','block'); 
				return ;
	 		}
 		}
 		
 		
 		//隐藏登录按钮,显示登录请求中状态
 		$('#login_btn').hide();
 		$('#login_loading').show();
 		//验证登录
 		$.post('login/loginAction',{
 			userAccount:userAccount,
 			password:password,
 			vevifiCode:vevifiCode,
 			r:new Date().getTime()
 		},function(data){
 			
 			if(data.result=='0'){
 				$('#login_btn').html('登录成功');
 				$('#login_btn').show();
 				$('#login_loading').hide();
 				//登录成功
 				location.href="login/loginedRedirect";
 			}else{//登录失败
 				//重新显示登录按钮
 				$('#login_btn').show();
		 		$('#login_loading').hide();
 				$("#error-info-detail").prepend("<p>"+data.error+"</p>");
 				$("#error-info-detail").css('display','block'); 
 				//判断验证码显示区域的显示和隐藏
 				checkValidateCodeShowOrHide();
 				//验证码不正确，则重新生成一个验证码
 				if(data.result=='3'){
 					changValidateCode();
 				}
 				
 			}
 			
 		},'json');
 	}
	
	/*
	切换验证码
	*/
	function changValidateCode(){
		$('#validateCodeImg').attr('src','/sso/validateCode?'+new Date().getTime());
		$('#vevifiCode').val('');
	}
	//判断验证码显示区域的显示和隐藏
	function  checkValidateCodeShowOrHide(){
		//var passErrerTimsLimitVevifiCodeNeed=${passErrerTimsLimitVevifiCodeNeed};//多少密码错误显示验证码
		var passErrerTimsLimitVevifiCodeNeed=3;//多少密码错误显示验证码
		var userAccount=$('#userAccount').val();
 		userAccount=$.trim(userAccount);
		$.get('/sso/getLoginPassErrerTimes',{
			userAccount:userAccount,
			r:new Date().getTime()
		},function(data){
			//alert("用户当天连续输入密码错误次:"+data);
			var passErrerTimes=parseInt(data);
			$('#passErrerTimes').val(passErrerTimes);
			if(passErrerTimes>=passErrerTimsLimitVevifiCodeNeed){
				$('#vevifiCode_div').show();
			}else{
				$('#vevifiCode_div').hide();
			}
		});
	
	}
	
});
