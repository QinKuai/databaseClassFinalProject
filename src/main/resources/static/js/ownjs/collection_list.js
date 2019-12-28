$(function(){

	//编辑按钮点击事件
	$(document).on('click','.__C__Edit',function(){
		$('.__Co__Mask,.__Rename').addClass('__State__Show');
		$('.__Delete').removeClass('__State__Show');
	});

	//删除按钮
	$(document).on('click','.__C__Remove',function(){
		$('.__Co__Mask,.__Delete').addClass('__State__Show');
		$('.__Rename').removeClass('__State__Show');
	});

	//取消按钮
	$(document).on('click','.__Cancel__',function(){
		$('.__Co__Mask').removeClass('__State__Show');
	});
	
	//取消按钮
	$(document).on('click','.__Sure__',function(){
		$('.__Co__Mask').removeClass('__State__Show');
	});
})