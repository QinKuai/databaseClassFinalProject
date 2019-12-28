
$(function(){

	var vip_list = [
	{
		integral:'100',
		price: '10',
	},{
		integral:'200',
		price: '18',
	},{
		integral:'500',
		price: '38',
	},{
		integral:'1000',
		price: '68',
	},{
		integral:'2000',
		price: '128',
	},{
		integral:'5000',
		price: '288',
	}];

	var html='';
		html+='<ul>';
		for(var i=0;i<vip_list.length;i++){
			html +='<li>';
              html +='<div class="__Int__Chong">';
                html +='<span class="__kb__"><small>'+vip_list[i].integral+'</small>积分</span>';
                html +='<span class="__kb__Money__" data-value="'+i+'">'+vip_list[i].price+'元</span>';
              html +='</div>';
            html +='</li>';
		}
		html+='</ul>';
	$('.__Integral__Chong .__Chong__').html(html);

	$('.__Integral__Chong ul li:first-child').addClass('__State__Act__');

	//click
	$(document).on('click','.__Chong__ ul li',function(){
		$('.__Integral__Chong ul li').removeClass('__State__Act__');
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