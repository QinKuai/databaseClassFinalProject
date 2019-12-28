var favorites;


// var username1 = "DYB";
// $.cookie('username', username1, {
//     path: '/'
// });
//
// var username = $.cookie('username');
// $('#userName').text(username);



$(function(){
  $(document).on('click','._Delete__Btn__',function(){
    alert("fuck")
    $.MsgBox.Confirm("fuck", "fuck", send)
  });

  $(document).ready(function(){
    

    $.get("http://192.168.0.110:8080/finalproject/user/userfavorites", {
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
      
      favorites = json.favorites;
      var product_num = favorites.length;
      var allproducts = '<div class="__All__List __State__Show">'+
        '<span>共' + product_num +'个收藏</span>'+
        '<ul class="__sh__list">';
      var point = json.user.point;
      $(points).html(point);
      $(rankid).html("等级："+rank);
      $(expid).html("经验值："+exp+"/"+exptop);

      $.each(favorites, function (index, item) {
            
        //alert("fuck");
        //循环获取数据  
        var content = favorites[index].content;
        var picaddr = "http://192.168.0.110:8080/finalproject/" + favorites[index].picaddr;
        var title = favorites[index].title;
        var likes = favorites[index].likes;
        var uploader = favorites[index].username;

        allproducts += '<li>'+
        '<a href="new_list_detail.html">'+
          '<div class="__List__Img">'+
            '<img src='+ picaddr +'/>'+
          '</div>'+
          '<div class="__List__Info__Det">'+
            '<div class="__Info_Det">'+
              '<h3>'+title+'例</h3>'+
              '<span><i class="fa fa-fire"></i></span>'+
            '</div>'+
            '<div class="__Info_Small">'+ content +'</div>'+
            '<div class="__Info_Tools">'+
              '<div class="__Tools">'+
                '<i class="fa fa-star-o"></i>'+
                '<span>'+ likes +'</span>'+
              '</div>'+
            '</div>'+
          '</div>'+
        '</a>'+
        '<div class="__List__Footer">'+
          '<a href="home_page.html">'+
            '<div class="__Header__Images">'+
              '<img src="images/jpg/__header.jpg" />'+
            '</div>'+
            '<span class="_V_" title="vip会员">'+
              '<img src="images/svg/vip_v.svg" />'+
            '</span>'+
            '<div class="__Header__Name">'+uploader+'</div>'+
          '</a>'+
          '<div class="__Member isOriginal" title="原创">'+
            '<img src="images/svg/original.svg" />'+
          '</div></br></br></br>'+
          '<div class="__Button__">'+
          '<button id='+ "cancel" +index +' type="button" class="__Delete__Btn__">取消收藏</button>'+
          '</div>'+
        '</div>'+
      '</li>'
    });
    allproducts += '</ul>'+
                  '</div>';
    $(productlist).html(allproducts);

    for(var i=0;i<product_num;i++){
      $("#cancel"+i).bind("click", function (e) {
        var n = e.target.id.split("l")[1];
        $.MsgBox.Confirm("提示", "您确定不再收藏此资源吗？", send, n);
      });
    }
    })

  });

  // $("#cancel0").bind("click", function () {
  //   alert("fuck");
  //   $.MsgBox.Confirm("提示", "您确定不再收藏此资源吗？", send);
  // });

  function send() {
    alert("取消收藏成功");
  }

  (function () {
    $.MsgBox = {
        
        Confirm: function (title, msg, callback, id) {
            GenerateHtml("confirm", title, msg);
             btnOk(callback, id);
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
      var btnOk = function (callback, rid) {
        $("#mb_btn_ok").click(function () {
            $("#mb_box,#mb_con").remove();
            check = "yes";
            var resourceid = favorites[rid].resourceid;

            $.get("http://192.168.0.110:8080/finalproject/favorite/delete-favorite", {
              username: $.cookie('username'),
              resourceid: resourceid
            }, function (data, status) {

            });

            if (typeof (callback) == 'function') {
                callback();
            }

            location=location;

          });
      }
      //取消按钮事件
      var btnNo = function () {
        $("#mb_btn_no,#mb_ico").click(function () {
            $("#mb_box,#mb_con").remove();
            check = "no";
        });
      }

  })();


});
