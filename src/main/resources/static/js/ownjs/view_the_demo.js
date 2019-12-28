$(function(){

	GetHeight();

	$(window).resize(function() {
		GetHeight();
	});

	function GetHeight(){
		var Hight = document.documentElement.clientHeight;
		var Iframe = document.getElementById('__iframes');
		Iframe.style.height = Hight - 70 +"px";
	}

	$(document).on('click','.__Demo__List ul li',function(){
		$('.__Demo__List ul li').removeClass('__State__Active');
		$(this).addClass('__State__Active');

		if($(this).find('.__Demo__Common').hasClass('__Demo__PC')){
			$('#__Iframe__').removeClass();
		}else if($(this).find('.__Demo__Common').hasClass('__Demo__SPiad')){
			$('#__Iframe__').removeClass().addClass('__Iframes__SPiad');
		}else if($(this).find('.__Demo__Common').hasClass('__Demo__Piad')){
			$('#__Iframe__').removeClass().addClass('__Iframes__Piad');
		}else if($(this).find('.__Demo__Common').hasClass('__Demo__SPhone')){
			$('#__Iframe__').removeClass().addClass('__Iframes__SPhone');
		}else if($(this).find('.__Demo__Common').hasClass('__Demo__Phone')){
			$('#__Iframe__').removeClass().addClass('__Iframes__Phone');
		}

	})



})