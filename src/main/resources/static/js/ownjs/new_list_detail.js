
var url;
var who;
var countfocus;
var countfavorite;
var upusername;//记录资源上传者姓名
$(function () {

	//滚动鼠标
	var scrollFunc = function (e) {  
	    e = e || window.event;
	    var scroH = $(this).scrollTop();
	    if (e.wheelDelta) {  //判断浏览器IE，谷歌滑轮事件               
	        if (e.wheelDelta > 0) { 
				//当滑轮向上滚动时执行的代码段 
	            if(scroH === 0){
					$('.__New__Su__Cai').removeClass('__Change__Header'); 
	            }
	        }  
	        if (e.wheelDelta < 0) { 
				//当滑轮向下滚动时执行的代码段   
				$('.__New__Su__Cai').addClass('__Change__Header'); 
	        }  
	    } else if (e.detail) {  //Firefox滑轮事件  
	        if (e.detail < 0) { 
				//当滑轮向上滚动时执行的代码段   
	            if(scroH === 0){
	              $('.__New__Su__Cai').removeClass('__Change__Header'); 
	            }
	        }  
	        if (e.detail > 0) { 
				//当滑轮向下滚动时执行的代码段  
				$('.__New__Su__Cai').addClass('__Change__Header'); 
	        }  
	    }  
	}  
	//给页面绑定滑轮滚动事件  Firefox
	if (document.addEventListener) {//firefox  
	    document.addEventListener('DOMMouseScroll', scrollFunc, false);  
	}  
	//滚动滑轮触发scrollFunc方法 ie 谷歌  
	window.onmousewheel = document.onmousewheel = scrollFunc;

})

window.onload = function () {
    
}

$(document).ready(function () {
    var str = window.location.href;
    var index = str.lastIndexOf("\/");
    var resourceid = str.substring(index + 1, str.length);//获得为资源id



    var username = $.cookie('username');
    document.getElementById("my").innerHTML = username;






    //加载资源信息

    //覃脍电脑上这样写
    $.get("data", { resourceid: resourceid },

    //测试用
    //$.get('http://192.168.0.110:8080/finalproject/resource/data?resourceid=6', {},
        function (data, status) {

            var json = JSON.parse(data);

            var resource = json.resource;
            var content = resource.content;
            //覃脍电脑用
            var picaddr = "../" + resource.picaddr;

            //测试用  
            //var picaddr = "http://192.168.0.110:8080/finalproject/" + resource.picaddr;


            var rlike = resource.likes;
            var title = resource.title;
            url = resource.url;
            upusername = resource.username;
            var idcontent = "#detail_content";
            var idtitle = "#detail_title";
            var idimgae = "#detail_img";
            var idlike = "#detail_like";
            var idname = "#detail_username";

            $(idcontent).html(content);
            $(idimgae).attr('src', picaddr);
            $(idtitle).html(title);
            $(idlike).html(rlike);
            $(idname).html(upusername);


        });






    //确认用户身份并加载下载按钮信息
    
    $.get('../resource/downloadcheck', {
        username: username,
        resourceid: resourceid
    },
//    $.get('http://192.168.0.110:8080/finalproject/resource/downloadcheck', {
//        username:"QinKuai",
//        resourceid:6
//    },
        function (data, status) {

        var json = JSON.parse(data);
        var respCode = json.responseCode;
        var owner = json.owner;

   

        if (owner == true)//该人为资源的拥有者
        {

            document.getElementById("download").innerHTML = "修改";
            document.getElementById("down_href").href = "uploading.html";
            who = "owner";
        }
        else {
            var downloaded = json.downloaded;
            if (downloaded == true)//说明下载过这个资源
            {
                document.getElementById("download").innerHTML = "已下载";
                document.getElementById("down_href").href = "javascript:void(0)";
              who="downloaded"

            }
            else//说明未下载过这个资源
            {
                document.getElementById("download").innerHTML = "下载";
                document.getElementById("down_href").href = "javascript:void(0)";
                who = "download";
            }
        }

       

    });

    //加载关注信息
    
    $.get('../follow/is-followed', {
        follower: username,
        following: upusername
    },
//    $.get('http://192.168.0.110:8080/finalproject/follow/is-followed', {
//        follower: "QinKuai",
//        following: "ZWH"
//    },
        function (data, status) {

            var json = JSON.parse(data);
            var respCode = json.responseCode;
            var followed = json.followed;

            
            if (followed == true) {
                document.getElementById("focusinfor").innerHTML = "已关注";
                countfocus = 0;
            }
            else {
                document.getElementById("focusinfor").innerHTML = "未关注";
                countfocus = 1;
            }

        });


    //加载收藏信息

    
    $.get('../resource/downloadcheck', {
        username: username,
        resourceid: resourceid
    },
    //$.get('http://192.168.0.110:8080/finalproject/favorite/is-favorited', {
//        username: "QinKuai",
//        resourceid: 6
//    },
        function (data, status) {

            var json = JSON.parse(data);
            var respCode = json.responseCode;


            if (respCode==206) {
                document.getElementById("collect_infor").innerHTML = "已收藏";
                countfavorite = 0;
            }
            else {
                document.getElementById("collect_infor").innerHTML = "未收藏";
                countfavorite = 1;
            }

        });






  



    

   
    $("#collect").bind("click", function () {
       
        if (countfavorite % 2 == 0) {
            document.getElementById("collect_infor").innerHTML = "未收藏";



            $.get('../favorite/delete-favorite', {
               username: username,
           resourceid: resourceid
            },
            

            function (data, status) {

                var json = JSON.parse(data);
                var respcode = json.responseCode;

                if (respcode == 200) {
                    alert("你取消了收藏");

                }


            });




        }
        else {
            document.getElementById("collect_infor").innerHTML = "收藏";
           


            $.get('../favorite/add-favorite', {
                 username: username,
                resourceid: resourceid
            },
             function (data, status) {

                var json = JSON.parse(data);
                var respcode = json.responseCode;

                if (respcode == 200) {
                    alert("你收藏了此资源");
                }

            });

        }
        countfavorite += 1;
      
    });

   
    $("#focus").bind("click", function () {

        if (countfocus % 2 == 0) {
            document.getElementById("focusinfor").innerHTML = "未关注";


            
            $.get('../follow/cancel', {
               follower: username,
               following: upusername
            },
                //测试用
            
//            $.get('http://192.168.0.110:8080/finalproject/follow/cancel', {
//                follower: "QinKuai",
//                following: "ZWH"
//            },
                function (data, status) {

                    var json = JSON.parse(data);
                    var respcode = json.responseCode;

                    if (respcode == 200) {
                        alert("你取消了关注");
                    }

                });

          


        }
        else {
            document.getElementById("focusinfor").innerHTML = "已关注";

            $.get('../follow/follow', {
                follower: username,
                following: upusername
            },
                //测试用
//                
//                $.get('http://192.168.0.110:8080/finalproject/follow/follow', {
//                    follower: "QinKuai",
//                    following: "ZWH"
//                },
                function (data, status) {

                    var json = JSON.parse(data);
                    var respcode = json.responseCode;

                    if (respcode == 200) {
                        alert("你关注了他");
                    }

                });

        

        }
        countfocus += 1;

    });



    function send() {
        

    }

    $("#down_href").bind("click", function () {
        if (who == 'owner') {

        }
        else if (who == 'downloaded') {
            alert(url);
        }
        else {

            $.MsgBox.Confirm("提示", "确定要进行下载此资源吗？", send);
        }
        
    });

    (function () {
        $.MsgBox = {
            
            Confirm: function (title, msg, callback) {
                GenerateHtml("confirm", title, msg);
                btnOk(callback);
                btnNo();
            }
        }
        //生成Html
        var GenerateHtml = function (type, title, msg) {
            var _html = "";
            _html += '<div id="mb_box"></div><div id="mb_con"><span id="mb_tit">' + title + '</span>';
            _html += '<a id="mb_ico">x</a><div id="mb_msg">' + msg + '</div><div id="mb_btnbox">';
          
            if (type == "confirm") {
                _html += '<input id="mb_btn_ok" type="button" value="确定" />';
                _html += '<input id="mb_btn_no" type="button" value="取消" />';
            }
            _html += '</div></div>';
            //必须先将_html添加到body，再设置Css样式
            $("body").append(_html);
            //生成Css
            GenerateCss();
        }

        //生成Css
        var GenerateCss = function () {
            $("#mb_box").css({
                width: '100%',
                height: '100%',
                zIndex: '99999',
                position: 'fixed',
                filter: 'Alpha(opacity=60)',
                backgroundColor: 'black',
                top: '0',
                left: '0',
                opacity: '0.6'
            });
            $("#mb_con").css({
                zIndex: '999999',
                width: '400px',
                position: 'fixed',
                backgroundColor: 'White',
                borderRadius: '15px'
            });
            $("#mb_tit").css({
                display: 'block',
                fontSize: '14px',
                color: '#444',
                padding: '10px 15px',
                backgroundColor: '#DDD',
                borderRadius: '15px 15px 0 0',
                borderBottom: '3px solid #009BFE',
                fontWeight: 'bold'
            });
            $("#mb_msg").css({
                padding: '20px',
                lineHeight: '20px',
                borderBottom: '1px dashed #DDD',
                fontSize: '13px'
            });
            $("#mb_ico").css({
                display: 'block',
                position: 'absolute',
                right: '10px',
                top: '9px',
                border: '1px solid Gray',
                width: '18px',
                height: '18px',
                textAlign: 'center',
                lineHeight: '16px',
                cursor: 'pointer',
                borderRadius: '12px',
                fontFamily: '微软雅黑'
            });
            $("#mb_btnbox").css({
                margin: '15px 0 10px 0',
                textAlign: 'center'
            });
            $("#mb_btn_ok,#mb_btn_no").css({
                width: '85px',
                height: '30px',
                color: 'white',
                border: 'none'
            });
            $("#mb_btn_ok").css({
                backgroundColor: '#168bbb'
            });
            $("#mb_btn_no").css({
                backgroundColor: 'gray',
                marginLeft: '20px'
            });
            //右上角关闭按钮hover样式
            $("#mb_ico").hover(function () {
                $(this).css({
                    backgroundColor: 'Red',
                    color: 'White'
                });
            }, function () {
                $(this).css({
                    backgroundColor: '#DDD',
                    color: 'black'
                });
            });
            var _widht = document.documentElement.clientWidth; //屏幕宽
            var _height = document.documentElement.clientHeight; //屏幕高
            var boxWidth = $("#mb_con").width();
            var boxHeight = $("#mb_con").height();
            //让提示框居中
            $("#mb_con").css({
                top: (_height - boxHeight) / 2 + "px",
                left: (_widht - boxWidth) / 2 + "px"
            });
        }
        //确定按钮事件
        var btnOk = function (callback) {
            $("#mb_btn_ok").click(function () {
                $("#mb_box,#mb_con").remove();
                
                if (typeof (callback) == 'function') {
                    callback();
                }

               
                 
              $.get('../resource/downloadcheck', {
                username: username,
                   resourceid: resourceid
                 },
//                $.get('http://192.168.0.110:8080/finalproject/resource/download', {
//                    username: "QinKuai",
//                    resourceid: 4
//                },
                    function (data, status) {

                        var json = JSON.parse(data);
                        var respCode = json.responseCode;


                        if (respCode == 200) {
                            alert(url);
                        }

                        


                    });


            });
        }
        //取消按钮事件
        var btnNo = function () {
            $("#mb_btn_no,#mb_ico").click(function () {
                $("#mb_box,#mb_con").remove();
               
            });
        }
    })();






});





  




