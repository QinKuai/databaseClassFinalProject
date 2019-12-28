
$(document).ready(function(){
	alert($.cookie('username'))
	$.get(user/userhomepage", {
    	username: $.cookie('username')
    }, function (data, status) {
        var json = JSON.parse(data);
        var allproducts = "";
        
        var point = json.user.point;
        var followed = json.followed;
        var follow = json.follow;

        var rank = json.user.rank;
        var exp = json.user.exp;
        var exps = json.exps;
        var exptop;

        for(var i=0;i<exps.length;i++){
            if(exps[i].rank==rank){
                exptop=exps[i].exp;
            }
        }

        var uploads = json.uploads;

        var sum = uploads.length;

        $(rankid).html("等级："+rank);
        $(expid).html("经验值："+exp+"/"+exptop);
        $(follow_id).html(follow);
        $(followed_id).html(followed);
        $(product_sum).html("全部作品（"+sum+"）");
        //alert(data);

        $.each(uploads, function (index, item) {
            
            //alert("fuck");
            //循环获取数据  
            var content = uploads[index].content;
            var picaddr = uploads[index].picaddr;
            //var picaddr = "http://192.168.0.110:8080/finalproject/" + uploads[index].picaddr;
            var title = uploads[index].title;
            var likes = uploads[index].likes;

            allproducts += '<div class="__List__Info__Sigle">'+
            '<a href="./new_list_detail.html">'+
                '<div class="__List__Img">'+
                    '<img src=' + picaddr + '/>'+
                '</div>'+
                '<div class="__List__Info__Det">'+
                    '<div class="__Info_Det">'+
                        '<h3>' + title + '</h3>'+
                        '<span><i class="fa fa-fire"></i></span>'+
                    '</div>'+
                    '<div class="__Info_Small">'+ content + '</div>'+
                    '<div class="__Info_Tools">'+
                        
                        '<div class="__Tools">'+
                            '<i class="fa fa-star-o"></i>'+
                            '<span>'+ likes +'</span>'+
                        '</div>'+
                       
                    '</div>'+
                '</div>'+
            '</a>'+
            '<div class="__List__Footer">'+
                // '<a href="home_page.html">'+
                //     '<div class="__Header__Images">'+
                //         '<img src="images/jpg/__head.svg">'+
                //     '</span>'+
                //     '<div class="__Header__Name">我叫er.jpg">'+
                //     '</div>'+
                //     '<span class="_V_" title="vip会员">'+
                //         '<img src="images/svg/vip_v.王大锤</div>'+
                // '</a>'+
                // '<div class="__Member isOriginal" title="原创">'+
                //     '<img src="images/svg/original.svg" />'+
                // '</div>'+
            '</div>'+
        '</div>"';
        });
        //alert(allproducts.length);
        $(productlist).html(allproducts);
    
    });
});