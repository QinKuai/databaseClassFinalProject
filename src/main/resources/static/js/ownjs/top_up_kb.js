$(function(){

	var vip_list = [
	{
		integral:'10',
		price: '10',
	},{
		integral:'20',
		price: '18',
	},{
		integral:'44',
		price: '38',
	},{
		integral:'80',
		price: '68',
	},{
		integral:'148',
		price: '128',
	},{
		integral:'344',
		price: '288',
	}];

	var html='';
		html+='<ul>';
		for(var i=0;i<vip_list.length;i++){
			html +='<li>';
              html +='<div class="__Int__Chong">';
                html +='<span class="__kb__"><small>'+vip_list[i].integral+'</small>K币</span>';
                html +='<span class="__kb__Money__" data-value="'+i+'">'+vip_list[i].price+'元</span>';
              html +='</div>';
            html +='</li>';
		}
		html+='</ul>';
	$('.__Kb__Chong .__Chong__').html(html);

	$('.__Kb__Chong ul li:first-child').addClass('__State__Act__');

	//click
	$(document).on('click','.__Chong__ ul li',function(){
		$('.__Kb__Chong ul li').removeClass('__State__Act__');
		$(this).addClass('__State__Act__');
	});


})