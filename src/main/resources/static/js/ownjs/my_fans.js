
var followers;
$(function(){
    $(document).ready(function(){
        $.get("http://192.168.0.120:8080/finalproject/user/follower", {
            username: $.cookie('username')
        }, function (data, status) {
          var json = JSON.parse(data);
          
          var point = json.user.point;
          followers = json.followersinfo;
          var rank = json.user.rank;
          var exp = json.user.exp;
          var exps = json.exps;
          var exptop;
    
          for(var i=0;i<exps.length;i++){
            if(exps[i].rank==rank){
                exptop=exps[i].exp;
            }
          }

          var all = "";
          var follower_num = followers.length;
          //alert(eval("json.refollow."+"ZWH"));

          $(points).html(point);
          $(followernum).html("您共有"+follower_num+"个粉丝哦～");
          $(rankid).html("等级："+rank);
          $(expid).html("经验值："+exp+"/"+exptop);

          $.each(followers, function (index, item) {
            var username = followers[index].username;
            var upload_num = eval("json.uploads."+username);
            var follow_num = eval("json.followers."+username);
            var resourses = eval("json.resources."+username);
            

            //alert(resourses[1].content);

            if(resourses.length==0){
            }else if (resourses.length==1){
                all += '<li>'+
                '<div class="__List__Left">'+
                  '<a class="__List__Header" href="home_page.html">'+
                    '<img src="images/jpg/__header.jpg" />'+
                  '</a>'+
                  '<div class="__List__Info">'+
                    '<h3>'+ username +'</h3>'+
                    '<a><em>上传 '+ upload_num +'</em> | <em>粉丝 ' + follow_num +'</em></a>'+
                    '<button type="button" id='+ "follow"+index+' class="__GZ__">取消关注</button>'+
                  '</div>'+
                '</div>'+
                '<div class="__List__right">'+
                  '<a href='+ resourses[0].url +'>'+
                    '<img src='+ resourses[0].picaddr +'/>'+
                '</div>'+
              '</li>'; 
            }else if(resourses.length==2){
                all += '<li>'+
            '<div class="__List__Left">'+
              '<a class="__List__Header" href="home_page.html">'+
                '<img src="images/jpg/__header.jpg" />'+
              '</a>'+
              '<div class="__List__Info">'+
                '<h3>'+ username +'</h3>'+
                '<a><em>上传 '+ upload_num +'</em> | <em>粉丝 ' + follow_num +'</em></a>'+
                '<button type="button" id='+ "follow"+index+' class="__GZ__">取消关注</button>'+
              '</div>'+
            '</div>'+
            '<div class="__List__right">'+
              '<a href='+ resourses[0].url +'>'+
                '<img src='+ resourses[0].picaddr +'/>'+
              '</a>'+
              '<a href='+ resourses[1].url +'>'+
                '<img src='+ resourses[1].picaddr +'/>'+
              '</a>'+
            '</div>'+
          '</li>';
            }else {
                all += '<li>'+
            '<div class="__List__Left">'+
              '<a class="__List__Header" href="home_page.html">'+
                '<img src="images/jpg/__header.jpg" />'+
              '</a>'+
              '<div class="__List__Info">'+
                '<h3>'+ username +'</h3>'+
                '<a><em>上传 '+ upload_num +'</em> | <em>粉丝 ' + follow_num +'</em></a>'+
                '<button type="button" id='+ "follow"+index+' class="__GZ__">取消关注</button>'+
              '</div>'+
            '</div>'+
            '<div class="__List__right">'+
              '<a href='+ resourses[0].url +'>'+
                '<img src='+ resourses[0].picaddr +'/>'+
              '</a>'+
              '<a href='+ resourses[1].url +'>'+
                '<img src='+ resourses[1].picaddr +'/>'+
              '</a>'+
              '<a href='+ resourses[2].url +'>'+
                '<img src='+ resourses[2].picaddr +'/>'+
              '</a>'+
            '</div>'+
          '</li>';
            }
          });
          $(follower_list).html(all);
          for(var i=0;i<follower_num;i++){
            $("#follow"+i).bind("click", function (e) {
              var n = e.target.id.split("w")[1];
              //alert("json.refollow."+followers[n].username);
              if(eval("json.refollow."+followers[n].username)==true){
                $.MsgBox.Confirm("提示", "您确定不再关注此用户吗？", send, n);
              }else{
                follow(n);
              }
              
            });
          }
          for(var i=0;i<follower_num;i++){
            if(eval("json.refollow."+followers[i].username)==false){
              //alert(followers[i].username);
              $("#follow"+i).text("关注");
            }
          }
          })

    });

    function follow(n){
      $.get("http://192.168.0.120:8080/finalproject/follow/follow", {
                follower: $.cookie('username'),
                following: followers[n].username
              }, function (data, status) {
  
              });
      location=location;
          
    }

    function send() {
      alert("取消关注成功");
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
              var followerid = followers[rid].username;
              //alert(followerid);

              $.get("http://192.168.0.120:8080/finalproject/follow/cancel", {
                follower: $.cookie('username'),
                following: followerid
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


})