$(function(){

	//go to back
	$(document).on('click','.__Go__To__Top',function(){
		Refresh();
	});

	$(window).scroll(function(){ 
		if($(this).scrollTop() > 200){
			$('.__Go__To__Top').addClass('__State__Top');
		}else{
			$('.__Go__To__Top').removeClass('__State__Top');
		}
	});

	//search buttn click
	$(document).on('click','.__Search',function(){
		$('.__Nav__List').addClass('__State__Search_');
		$('.__Seach__Input input').focus();
		$('.__Search').hide();
		$('.__Header').addClass('__State__Nav');
	});

	//close button click
	$(document).on('click','.__Close_',function(){
		$('.__Nav__List').removeClass('__State__Search_');
		$('.__Search').show();
	});

	// //禁止右键
	// document.onmousedown = function(e){
	// 	if ( e.which == 2 ){// 鼠标滚轮的按下，滚动不触发
	// 		return false;
	// 	}
	// 	if( e.which==3 ){// 鼠标右键
	// 		alert('抱歉，您需要正常途径下载此作品！否则平台会追究一定责任！');
	// 		return false;
	// 	}
	// }
	// document.oncontextmenu = function(){
	// 	event.returnValue = false;
	// }
	// // 或者直接返回整个事件
	// document.oncontextmenu = function(){
	// 	return false;
	// }



})

Refresh();

function Refresh(){
	$("html, body").animate({scrollTop: 0}, 500);
}
