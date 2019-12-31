/** 
 * @param {Object} address，如userAction!save.html
 * @param {Object} params 请求body数据
 * @param {Object} callback_success 请求成功时回调函数
 * @param {Object} showLoadingDialog 界面是否显示加载提示
 * @param {Object} callback_timeout 网络请求超时回调函数
 * @param {Object} callback_complete 请求完成回调函数,无论是成功或超时或失败
 * @param {Object} callback_error 网络断开回调函数
 */
var AJAXUtil = {
		getData: function(address, params, callback_success,callback_timeout,callback_complete,callback_error) {
					//ajax post数据
                    $.ajax({
                         type: "POST", 
                         timeout : 5000, //超时时间设置，单位毫秒
                         async:true,//异步
                         dataType: "json",
                         url:address,
                         data: params,// 要提交的表单
                         success: function(data) {
                            if(callback_complete){
                                callback_complete();
                            }
                            if (data) {
                                callback_success(data);
                            }
                         },
                         error: function (XMLHttpRequest, textStatus, errorThrown) {
                        	 if(textStatus=='error'){
                        		 if(callback_error){
                            		 callback_error();
                            	 }
                            	 if(callback_complete){
    							    	callback_complete();
    							 }
                        	 }
                        	
                        },
                         complete : function(XMLHttpRequest,status){ //请求完成后最终执行参数
                        	 console.log('-----------status----------:::'+status)
						    if(status=='timeout'){//超时,status还有success,error等值的情况
								  	if(callback_timeout){
										callback_timeout();
									}
								  	if(callback_complete){
								    	callback_complete();
								    }
						      }
                         }
                     });
	      },
	 };
