/* 表格分页data数据 */
var datagridData;

var sel=0; 

$(function () {
	var id=$("#bagid").val();
	$('#box').tree({
        url : "stratergyBagAction!initTress.html?id="+id,
        method:"get",
        animate:false,
        onlyLeafCheck:true,
        lines:false
    });
	
    //	表格数据渲染
    datagridData=$('#dg').datagrid({    
		scrollbarSize : 0,
		nowrap : false,// 允许换行
//		url:'terminalAction!terminalList.html',
		method : 'post',
		louomsg: '正在加载中，请稍等... ',
		fitColumns : true,// 宽度自适应
		checkOnSelect : false,// 点击该复选框的时候才会选中或取消
		iconCls: 'icon-edit',
        emptyMsg: '<span>无记录</span>',
		onLoadSuccess : function(data) {// Fires when data is
			$('.datagrid input').prop('disabled',true);//复选框置灰
			$('.datagrid input').prop('checked',true);
			$('#pp').pagination('refresh', {
                total : data.total,
                pageNumber : data.page
		    });
            $('#dg').datagrid('resize');
            toopTip(".normal","正常");
            toopTip(".abnormal","异常");
       
      	 //修改复选框默认样式      
      	 
			loadSpan();
            boxCheck();
			boxUnCheck();
       
		},
		onCheckAll:function(){
			boxCheck();
		},
		onCheck:function(){
			boxCheck();
		},
		onUncheckAll: function () {
			boxUnCheck();
		},
		onUncheck: function () {
			boxUnCheck();
		},
		onDblClickCell: onClickCell,
		columns : [ [
		     {
		     field:'id',
		     title:'',
		     // width:'5%',
		     width:60,
		     checkbox:true,
		     align:'center'
		     },
            {
                field:'terminalName',                
                title:'终端机名称',
                width:200,
                align:'center',
                
            },
            {
                field:'serial',
                title:'终端序列号',
                width:200,
                align:'center'
            }
				] ]    
    });
    //分页
    $('#pp').pagination({ 
		layout:['first','prev','links','next','last','manual'],
		pageSize:10,
		onSelectPage:function (pageNo, pageSize) {
	        var start = (pageNo - 1) * pageSize; 
	        var end = start + pageSize; 
	        var communityCode =$("#communityCode").val();
	        $("#dg").datagrid("reload", 
	        		{  
	        				pageNumber:pageNo,
		        			organizationSeq:communityCode
	        		});
            toopTip(".normal","正常");
            toopTip(".abnormal","异常");
        }
	});


});

window.onload=function(){
	var id=$("#bagid").val();
	$('#dg').datagrid({url:'stratergyBagAction!terminalList.html?id='+id });
};
//树形结构的模糊查找
function doSearch(value){
	var id=$("#bagid").val();
	$('#box').tree('collapseAll');
	
			$.ajax({
			 	   type: "post",
			 	   url:'stratergyBagAction!searchTree.html?id='+id,
			 	   data:'communityName='+value,
			 	   dataType:'JSON',
			 	   cache:false,
			 	   success:function(msg){
			 		   if(msg.result=="success"){
			 			   if(msg.opendId=="未搜索到小区!"){
			 			   		infoMask('未搜索到小区!');
			 			   }else{
			 				  var opendId =msg.opendId;
				 			  opendId = opendId.split(",");
				 			  for(var i=0;i<opendId.length;i++){
				 				 var node = $('#box').tree('find', opendId[i]);
					 			  $('#box').tree('expandTo', node.target).tree('select', node.target);
				 			  }  
			 			   }
			 		   }else{
			 			  editIndexNums = undefined;
			 			  editJson =undefined;
			 			  infoMask('请输入小区名称!');
			 		   }
			 	   }
			    }); 
	
	
}

function search(id){
	$("#communityCode").val(id);

		$("#dg").datagrid('load',
				  {
					 organizationSeq:id
			  		}); 
		 	
	
	
	
}

function searchKey(fuzzyKey){
	var communityCode=$("#communityCode").val();

	
		$("#dg").datagrid('load',
	 	{
			 organizationSeq:communityCode,
			 fuzzyKey:fuzzyKey
  		}); 
	
	
}

$.extend($.fn.datagrid.methods, {
	editCell: function(jq,param){
		return jq.each(function(){
			var opts = $(this).datagrid('options');
			var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor1 = col.editor;
				if (fields[i] != param.field){
					col.editor = null;
				}
			}
			$(this).datagrid('beginEdit', param.index);
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor = col.editor1;
			}
		});
	}
});
var editIndex = undefined;
var editIndexNums = undefined;
var editJson =undefined;
function endEditing(){
	if (editIndex == undefined){return true}
	if ($('#dg').datagrid('validateRow', editIndex)){
		$('#dg').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}

function onClickCell(index, field){
	if (endEditing()){
		$('#dg').datagrid('selectRow', index)
		.datagrid('editCell', {index:index,field:field});
		editIndex = index;
		if(editIndexNums!=undefined){
			editIndexNums = editIndexNums+","+index;
		}else{
			editIndexNums = index;
		}

        var inputEditor = $("input.datagrid-editable-input");//單元格input元素

        //input生成后，自動獲取聚焦
        inputEditor.focus();

        //input失去焦點，撤銷編輯操作，恢復原來的值，不保存
        inputEditor.bind("blur",function(evt){
            reject();
        });

        //按鍵按下，如果是回車鍵，則保存編輯內容
        inputEditor.bind("keypress",function(evt){
            var keyCode = evt.keyCode;
            if(keyCode == 13){  //回车事件
//                alert(evt.keyCode);
                $(this).unbind("blur");
                accept();

            }
        });

        /*
        * input獲取焦點后，綁定blur事件
        * 在input已獲取焦點的前提下，點擊文檔任何位置，取消編輯內容
        * 如果是回車鍵按下，則取消綁定blur，保存編輯內容
        * */

        inputEditor.bind("focus",function(evt){

            $(this).bind("blur",function(evt){
                reject();
            });

            $(document).bind("click",function(){
                reject();
            });

            $(this).bind("keypress",function(){
                var keyCode = evt.keyCode;
                if(keyCode == 13){  //回车事件
//                alert(evt.keyCode);
                    $(this).unbind("blur");
                    accept();
                }
            });

        });



		
	}
}






//编辑按钮
function onchangeArea(m){
	var id=$("#bagid").val();	
	window.location.href="stratergyBagAction!goAreaEidtPage.html?id="+id
	
}


//取消按钮
function cancelArea(){
	sel=0;
	$('.edit').show();
	$('.saveCancel').hide();
	var id=$("#bagid").val();
	//console.log(id);
	
	$('#box').tree({
        url : "stratergyBagAction!initTress.html?id="+id,
		checkbox:false
	});
	$('#dg').datagrid({url:'stratergyBagAction!terminalList.html?id='+id });
}



/*遍历选中节点，高亮显示*/
function treeSelect(){
    $(".tree-checkbox").each(function(){
        if($(this).hasClass("tree-checkbox1")){
            $(this).parent().addClass("tree-node-selected");
        }else{
            $(this).parent().removeClass("tree-node-selected");
        }
    });
}