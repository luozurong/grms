/* 表格分页data数据 */
var datagridData;
$(function () {
	$('#box').tree({
        url : "terminalAction!initTress.html",
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
		singleSelect: true,
		iconCls: 'icon-edit',
        emptyMsg: '<span>无记录</span>',
		onLoadSuccess : function(data) {// Fires when data is
			$('#pp').pagination('refresh', {
                total : data.total,
                pageNumber : data.page
		    });
            $('#dg').datagrid('resize');
            toopTip(".normal","正常");
            toopTip(".abnormal","异常");
		},
		onDblClickCell: onClickCell,
		columns : [ [
            {
                field:'terminalName',
                title:'终端机名称',
                width:200,
                align:'center',
                editor:'text'
            },
            {
                field:'serial',
                title:'终端序列号',
                width:200,
                align:'center'
            },
            {
                field:'statusType',
                title:'设备状态',
                width:80,
                align:'center',
            	formatter : function(value, row, index) {
            		if(value==1){
                        var normal = '<span class="handlePic normal" href="#" ></span>';
            			return normal;
            		}else{
                        var abnormal = '<span class="handlePic abnormal" href="#" ></span>';
            			return  abnormal;
            		}
				}
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
		        			pageNo:pageNo,
		        			organizationSeq:communityCode
	        		});
            toopTip(".normal","正常");
            toopTip(".abnormal","异常");
        }
	});


});

window.onload=function(){
	$('#dg').datagrid({url:'terminalAction!terminalList.html' });
};
//树形结构的模糊查找
function doSearch(value){
	$('#box').tree('collapseAll');
	$.ajax({
	 	   type: "post",
	 	   url:'terminalAction!searchTree.html',
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
//		if(editIndexNums!=undefined){
//			editIndexNums = editIndexNums+","+index;
//		}else{
			editIndexNums = index;
//		}
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

            /*$(document).bind("click",function(){
                reject();
            });*/

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

function accept(){
	if (endEditing()){
		var lineNums =undefined;
		if(editIndexNums==undefined){
			infoMask('未修改任何记录!');
		}else{
//			if(String(editIndexNums).length>1){
//				lineNums = editIndexNums.split(",");   //分割获取到改变值的行号
//				for(var i=0;i<lineNums.length;i++){
//					var rows=$('#dg').datagrid('getRows');
//					var row =rows[lineNums[i]];
//					if(i==0){
//						editJson = [{
//							"id":row.id,
//							"terminalName":row.terminalName
//						}]
//					}else{
//						var content={  
//								"id":row.id,
//								"terminalName":row.terminalName
//		            	};
//						editJson.push(content);
//					}
//				}
//				edidName(JSON.stringify(editJson));
//			}else{
				lineNums = editIndexNums;
				var rows=$('#dg').datagrid('getRows');
				var row =rows[lineNums];
				editJson = [{
					"id":row.id,
					"terminalName":row.terminalName
				}]
				edidName(JSON.stringify(editJson));
//			}
			$('#dg').datagrid('acceptChanges');

            //解決某行編輯終端機名稱后，對應設備狀態的toopTip提示異常問題
            toopTip(".normal","正常");
            toopTip(".abnormal","异常");
		}
	}
}
function reject(){
	$('#dg').datagrid('rejectChanges');
}



//导出统计数据
function exportData(){
	 var terminalCount=$('#box').tree("getRoots");
	 if(terminalCount.length==0){
	 	infoMask('没有数据！');
     }else{
    	 window.location.href="terminalAction!exportToExcel.html";
     }
	
       
}

function edidName(data){


	$.ajax({
 	   type: "post",
 	   url:'terminalAction!updateTerminalName.html',
 	   data:'jsonData='+data,
 	   dataType:'JSON',
 	   cache:false,
 	   success:function(msg){
 		   if(msg.result=="success"){
 			  editIndexNums = undefined;
 			  editJson =undefined;
 			  infoMask(msg.success);
 		   }else{
 			  editIndexNums = undefined;
 			  editJson =undefined;
 			  infoMask(msg.error);
 		   }
 	   }
    }); 
}
