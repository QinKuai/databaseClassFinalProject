$(function(){

	var vip_list = [
	{
		vip_name:'半年VIP',
		price_y: '128',
		price_x: '99',
	},{
		vip_name:'一年VIP',
		price_y: '228',
		price_x: '199',
	},{
		vip_name:'二年VIP',
		price_y: '428',
		price_x: '329',
	},{
		vip_name:'终身VIP',
		price_y: '888',
		price_x: '666',
	}];

	//click
	$(document).on('click','.__Chong__ ul li',function(){
		$('.__Chong__ ul li').removeClass('__State__Act__');
		$(this).addClass('__State__Act__');
	});

	//tabs
	$(document).on('click','.__Tabs__ ul li',function(){
		var sel = $(this).index();
		$('.__Tabs__ ul li').removeClass('__Act__');
		$(this).addClass('__Act__');
		$('.__Buy__Con .__All__List').removeClass('__State__Show').eq(sel).addClass('__State__Show');
	});

	//加减按钮事件
	$(document).on('click','.add-number .add',function(){
		var _input = $(this).siblings('.number-input');
		$(this).siblings('.number-input').val(parseInt(_input.val()) + 1);
		addMoney();
	});

	$(document).on('click','.add-number .minus',function(){
		var _input = $(this).siblings('.number-input');
		var _val = parseInt(_input.val()) - 1;
		if(_val <0){
			$(this).siblings('.number-input').val('0');
		}else{
			$(this).siblings('.number-input').val(_val);
		}
		addMoney();
	});

	$('.number-input').keyup(function(){
		addMoney();
	});
	$('.number-input').blur(function(){
		var this_val = $(this).val();
		if(this_val == '' || this_val == 'undefind'){
			$(this).val('0');
		}else{
			$(this).val(this_val);
		}
	});

	addMoney();
	//金额计算
	function addMoney(){
		var now_total = 0,
			old_total= 0;
		$('.choose-content ul').each(function(index){
			var oldPrice = $(this).find('.old-price').attr('data-price');
			var nowPrice = $(this).find('.now-price').attr('data-price');
			var numbers = $(this).find('.number-input').val();
			var old_price_total = oldPrice * numbers;
			var now_price_total = nowPrice * numbers;
			// console.log(old_price_total,now_price_total);
			if(now_price_total == 0){
				$(this).find('.now-total-price').html(now_price_total);
			}else{
				$(this).find('.now-total-price').html('¥' + now_price_total);
			}
			now_total += now_price_total;
			old_total += old_price_total;
			$('.total-price span em').html(old_total);
			$('.total-price span i').html(now_total);
			$('.__Pay__Num span small').html('¥' + now_total + '元');
			if(now_total >= 300){
				$('.__Pay__Num button').addClass('state-buys');
			}else{
				$('.__Pay__Num button').removeClass('state-buys');
			}
		})
	}


})