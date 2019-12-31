
/*下拉框data数据*/
var optionValues = [
    {
        "id":0,
        "text":"能"
    },{
        "id":1,
        "text":"否"
    }
];
$(function(){
	var enablePromotionActive = $("#enablePromotionActive").val();
	var tsmg="";
	if(enablePromotionActive==0){
		tsmg="能";
	}else{
		tsmg="否";
	}
    /*下拉框*/
    $('#cc1').combobox({
        limitToList:true,
        value : tsmg,
        data:optionValues,
        valueField:'id',
        textField:'text',
        editable : false,
        panelHeight:"auto",
        onSelect:function(newValue, oldValue){
        	$("#enablePromotionActive").attr("value",newValue.id);
        }
    });

    //inpupt框聚焦高亮显示
    focusColorAndShowOrHideX(".input1");
});


function validateForm(){

	$('#communityForm').form('submit', {
		url : 'communityAction!communitySave.html',
		
		onSubmit : function() {
			return true;
		},
		success : function(msg) {
				//可以定义为对应消息框
				msg = eval('(' + msg + ')');  // change the JSON string to javascript object    
        	   if(msg.result=='success'){
					successMask(msg.success,'communityAction!goCommunityList.html');
        	   }else{
        	   		infoMask(msg.error);
        	   }
							
		}
	});
}

function goback(){
	window.location.href="communityAction!goCommunityList.html";
}