
var uploads;
$(function(){
    $(document).ready(function(){
        $.get("http://192.168.0.110:8080/finalproject/user/userhomepage", {
            username: $.cookie('username')
        }, function (data, status) {
          var json = JSON.parse(data);
          
          uploads = json.uploads;
          var product_num = uploads.length;
          var rank = json.user.rank;
          var exp = json.user.exp;
          var exps = json.exps;
          var exptop;

      for(var i=0;i<exps.length;i++){
        if(exps[i].rank==rank){
            exptop=exps[i].exp;
        }
      }
          
          var allproducts = '<div class="__All__List __State__Show">'+
            '<span>您成功上传了' + product_num +'个资源</span>'+
            '<ul class="__sh__list">';
          var point = json.user.point;
          $(points).html(point);
          $(rankid).html("等级："+rank);
          $(expid).html("经验值："+exp+"/"+exptop);
    
          $.each(uploads, function (index, item) {
                
            //alert("fuck");
            //循环获取数据  
            var content = uploads[index].content;
            var picaddr = "http://192.168.0.110:8080/finalproject/" + uploads[index].picaddr;
            var title = uploads[index].title;
            var likes = uploads[index].likes;
    
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
              '<div class="__Button__">'+
                '<button id='+ "modify" +index +' type="button" class="__Delete__Btn__ __State__Off__">修改</button>'+
              '</div>'+
              '<div class="__Button__">'+
                '<button id='+ "delete" +index +' type="button" class="__Delete__Btn__ __State__Off__">删除</button>'+
              '</div>'+
            '</div>'+
          '</li>'
        });
        allproducts += '</ul>'+
                      '</div>';
        //alert(allproducts.length);
        $(productlist).html(allproducts);
        
        for(var i=0;i<product_num;i++){
            $("#delete"+i).bind("click", function (e) {
              var n = e.target.id.split("elete")[1];
              $.MsgBox.Confirm("提示", "您确定要删除该资源吗？", send, n);
            });
        }

       })

    });

    function send() {
      alert("删除资源成功");
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
              var resourceid = uploads[rid].resourceid;

              // alert(resourceid);
  
              $.get("http://192.168.0.110:8080/finalproject/resource/delete", {
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



})