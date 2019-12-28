$(document).ready(function () {
    var str = window.location.href;


    var index = str.lastIndexOf("\/");

    var str = str.substring(index + 1, str.length);//获得资源类型名称

    
    

    var username = $.cookie('username');
    document.getElementById("my").innerHTML = username;



    //覃脍电脑上这样写
    $.get("detail", { typename: str },

    //测试用
    //$.get('http://192.168.0.120:8080/finalproject/resources/detail?typename=movie', {}, 
    		function (data, status) {
        var json = JSON.parse(data);
        var resource = json.resources;
        var amount = json.amount;
       

        $.each(resource, function (index, item) {
            var s = "";

            var content = resource[index].content;
            var title = resource[index].title;

            //覃脍电脑
            var picaddr = "../" + resource[index].picaddr;

            //测试用
            //var picaddr = "http://192.168.0.120:8080/finalproject/" + resource[index].picaddr;

            var resourceid = resource[index].resourceid;
            var likes = resource[index].likes;
            var user = resource[index].username;



            //覃脍电脑上
            var s1 = "../resource/" + resourceid


            //测试
            //var s1 = "new_list_detail.html";

            if (resourceid == 1) {
                document.getElementById("resourcetype").innerHTML = "动漫";
            }
            else if (resourceid == 2) {
                document.getElementById("resourcetype").innerHTML="影视";
            }
            else {
                document.getElementById("resourcetype").innerHTML = "其他";
            }

            document.getElementById("resourceamount").innerHTML = amount;


            s += '<div class="__List__Info__Sigle">' +
                '<a href='+s1+'>' +
                ' <div class="__List__Img">' +
                '<img src='+ picaddr+  ' />' +
                '</div>' +
                '<div class="__List__Info__Det">' +
                ' <div class="__Info_Det">' +
                ' <h3>'+title+'</h3>' +
                '<span><i class="fa fa-fire"></i></span>' +
                '</div>' +
                '<div class="__Info_Small">'+content+'</div>' +
                '<div class="__Info_Tools">' +
                
                '<div class="__Tools">' +
                ' <i class="fa fa-star-o"></i>' +
                '<span>'+likes+'</span>' +
                ' </div>' +
                
                '</div>' +
                ' </div>' +
                ' </a >' +
                '<div class="__List__Footer">' +
                '<a href="home_page.html">' +
                ' <div class="__Header__Images">' +
                '<img src="../static/images/jpg/__header.jpg" />' +
                '</div>' +
                '<span class="_V_" title="vip会员">' +
                '<img src="../static/images/svg/vip_v.svg" />' +
                ' </span>' +
                '<div class="__Header__Name">'+user+'</div>' +
                '</a>' +
                '<div class="__Member isOriginal" title="原创">' +
                '<img src="../static/images/svg/original.svg" />' +
                '</div>' +
                '</div>'
            ' </div >"';

           
            $("#list").append(s);
        });
       
    });



   











	getHeight();

	$(document).on('click','.more-btn',function(){
		var parents = $(this).parents('.__Common__Classify__');
		// $(this).find('span').text();
		if(parents.hasClass('more-classify')){
			parents.removeClass('more-classify');
			$(this).find('span').text('更多');
		}else{
			parents.addClass('more-classify');
			$(this).find('span').text('收起');
		}
		getHeight();
	});

	function getHeight(){
		var Height = $('.__Common__Classify__ >ul').height();
		console.log(Height);
		if(Height>36){
			$('.more-btn').show();
		}else{
			$('.more-btn').hide();
		}
	}
});