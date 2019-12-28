
$(function(){

	//tabs
	$(document).on('click','.__Tabs__ ul li',function(){
		var sel = $(this).index();
		$('.__Tabs__ ul li').removeClass('__Act__');
		$(this).addClass('__Act__');
		$('.__Buy__Con .__All__List').removeClass('__State__Show').eq(sel).addClass('__State__Show');
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