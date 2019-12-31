
/*下拉框data数据*/
var data1 = [
    {
        "id":1,
        "text":"能"
    },{
        "id":2,
        "text":"否"
    }
];
$(function(){
    /*下拉框*/
    $('#cc1,#cc2,#cc3,#cc4,#cc5,#cc6').combobox({
        //				    url:'combobox_data.json',
        //				    method:'get',
        //					showItemIcon:true,
        limitToList:true,
        value : '能',
        data:data1,
        valueField:'id',
        textField:'text',
        editable : false
    });
});

function goback(){
	//window.location.href="communityAction!goCommunityList.html";
	history.back(-1);
}