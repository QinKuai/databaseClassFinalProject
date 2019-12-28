
$(function(){

	var vip_list = [
	{
		name_class:'three_vip',
		vip_name:'30天VIP',
		price_y: '38',
		price_x: '28',
	},{
		name_class:'nine_vip',
		vip_name:'90天VIP',
		price_y: '88',
		price_x: '68',
	},{
		name_class:'half_vip',
		vip_name:'半年VIP',
		price_y: '158',
		price_x: '128',
	},{
		name_class:'one_vip',
		vip_name:'一年VIP',
		price_y: '388',
		price_x: '228',
	},{
		name_class:'two_vip',
		vip_name:'二年VIP',
		price_y: '588',
		price_x: '428',
	},{
		name_class:'long_vip',
		vip_name:'终身VIP',
		price_y: '1188',
		price_x: '888',
	}];

	var html='';
		html+='<ul>';
		for(var i=0;i<vip_list.length;i++){
			html += '<li>';
	            html += '<div class="__Vip__Left">';
	              html += '<span class="__Vip__Img '+vip_list[i].name_class+'"></span>';
	              html += '<div class="__Vip__Title">';
	                html += '<span>'+vip_list[i].vip_name+'</span>';
	              html += '</div>';
	            html += '</div>';
	            html += '<div class="__Vip__Right">';
	              html += '<div class="__Right__Title__">打折下载全部积分作品</div>';
	              html += '<div class="__Right__Con__">';
	                html += '<span>现价：¥<small data-value="'+i+'">'+vip_list[i].price_x+'</small></span>';
	                html += '<span class="__Price__">原价：'+vip_list[i].price_y+'</span>';
	              html += '</div>';
	            html += '</div>';
	          html += '</li>';
		}
		html+='</ul>';
	$('.__Vip__Chong .__Chong__').html(html);

	$('.__Chong__ ul li:first-child').addClass('__State__Act__');

	//click
	$(document).on('click','.__Chong__ ul li',function(){
		$('.__Chong__ ul li').removeClass('__State__Act__');
		$(this).addClass('__State__Act__');
	});

	$(document).ready(function(){
		$.get("http://192.168.0.110:8080/finalproject/user/userhomepage", {
			username: $.cookie('username')
		}, function (data, status) {
		  var json = JSON.parse(data);

		  var rank = json.user.rank;
      var exp = json.user.exp;
      var exps = json.exps;
      var exptop;

      for(var i=0;i<exps.length;i++){
        if(exps[i].rank==rank){
            exptop=exps[i].exp;
        }
      }

      var point = json.user.point;
      $(points).html(point);
      $(rankid).html("等级："+rank);
	  $(expid).html("经验值："+exp+"/"+exptop);
	});
});


})