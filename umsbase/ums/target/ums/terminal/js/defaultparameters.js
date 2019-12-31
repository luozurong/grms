
$(document).ready(function(){
	
	var volume=$("#volume").val();
	var birghtness=$("#birghtness").val();
	//alert(volume+"----"+birghtness);
    /*默认亮度*/
    (function(){
        var width1;
        $('.slider1').slider({
            mode : 'h',
            value : birghtness,
            min : 0,
            max : 100,
            step : 1,
            onChange : function (newValue, oldValue) {
                width1 = (newValue/100)*500;
                $('.input1').empty().val(newValue);
                $('.slider-run1').css('width', width1);
            }
        });

        var sliderValue1 = $('.slider1').slider('getValue');
        width1 = (sliderValue1/100)*500;
        $('.slider-run1').css('width', width1);
        $('.input1').empty().val(sliderValue1);

        $('.input1').on('keyup',function(){
            var val = $(this).val();
            if(val==''){
                val=0;
            }
            var num = parseInt(val);
            if(num>99){
                num=100;
            }
            $('.input1').empty().val(num);

            width1 = (num/100)*500;
            $('.slider1').slider('setValue', num);
            $('.slider-run1').css('width', width1);
        });
    })();


    /*視頻廣告音量*/
    (function(){
        var width2;
        $('.slider2').slider({
            mode : 'h',
            value : volume,
            min : 0,
            max : 100,
            step : 1,
            onChange : function (newValue, oldValue) {
                width2 = (newValue/100)*500;
                $('.input2').empty().val(newValue);
                $('.slider-run2').css('width', width2);
            }
        });

        var sliderValue1 = $('.slider2').slider('getValue');
        width2 = (sliderValue1/100)*500;
        $('.slider-run2').css('width', width2);
        $('.input2').empty().val(sliderValue1);

        $('.input2').on('keyup',function(){
            var val = $(this).val();
            if(val==''){
                val=0;
            }
            var num = parseInt(val);
            if(num>99){
                num=100;
            }
            $('.input2').empty().val(num);

            width2 = (num/100)*500;
            $('.slider2').slider('setValue', num);
            $('.slider-run2').css('width', width2);
        });
    })();

    /*音頻廣告音量*/
    (function(){
        var width3;
        $('.slider3').slider({
            mode : 'h',
            value : volume,
            min : 0,
            max : 100,
            step : 1,
            onChange : function (newValue, oldValue) {
                width3 = (newValue/100)*500;
                $('.input3').empty().val(newValue);
                $('.slider-run3').css('width', width3);
            }
        });

        var sliderValue3 = $('.slider3').slider('getValue');
        width3 = (sliderValue3/100)*500;
        $('.slider-run3').css('width', width3);
        $('.input3').empty().val(sliderValue3);

        $('.input3').on('keyup',function(){
            var val = $(this).val();
            if(val==''){
                val=0;
            }
            var num = parseInt(val);
            if(num>99){
                num=100;
            }
            $('.input3').empty().val(num);

            width3 = (num/100)*500;
            $('.slider3').slider('setValue', num);
            $('.slider-run3').css('width', width3);
        });
    })();


});


function dosave(){

    $(document.body).css({"overflow":"hidden"});
    $('body').css('overflow','hidden');//禁止滚动
	 var birghtness=$('.slider1').slider('getValue');
	 var volume=$('.slider2').slider('getValue');
	 
	 $.ajax({
		   type: "POST",
		   url: "terminalStatergyAction!saveTermialDefaultParameters.html",
		   data: {"birghtness":birghtness,"volume":volume},
		   success: function(msg){
		     if(msg){
		     	infoMask('保存成功！');

		     }else{
		     	infoMask('保存失败，请查看网络是否有问题！');
		     }
		   }
	});
	
}