
$(function(){
      $(document).ready(function(){
        
    
        $.get("http://192.168.0.110:8080/finalproject/user/download", {
            username: $.cookie('username')
        }, function (data, status) {
          var json = JSON.parse(data);
          
          var downloads = json.downloads;
          var load_num = downloads.length;
          var resources = json.resources;
          var rank = json.user.rank;
          var exp = json.user.exp;
          var exps = json.exps;
          var exptop;
    
          for(var i=0;i<exps.length;i++){
            if(exps[i].rank==rank){
                exptop=exps[i].exp;
            }
          }

          var allproducts = "";
          var point = json.user.point;
          $(points).html(point);
          $(rankid).html("等级："+rank);
          $(expid).html("经验值："+exp+"/"+exptop);
          
          $.each(downloads, function (index, item) {
                
            //alert("fuck");
            //循环获取数据
            var resourceid = downloads[index].resourceid;
            var content;
            var picaddr;
            var title;
            var likes;

            for(var i=0;i<load_num;i++){
                if(resources[i].resourceid == resourceid){
                    content = resources[i].content;
                    picaddr = "http://192.168.0.110:8080/finalproject/" + resources[i].picaddr;
                    title = resources[i].title;
                    likes = resources[i].likes;
                }
            }
            
            var cost = downloads[index].point;
            var date = formatDate('/Date('+ downloads[index].date+ ')/', 'yyyy-MM-dd HH:mm:ss');
            //alert(date);
            allproducts += '<li>'+
            '<a href="new_list_detail.html">'+
              '<div class="__List__Img">'+
                '<img src="'+picaddr+'">'+
              '</div>'+
              '<div class="__List__Info__Det">'+
                '<div class="__Info_Det">'+
                  '<h3>'+title+'</h3>'+
                  '<span>'+
                    '<i class="fa fa-fire"></i>'+
                  '</span>'+
                '</div>'+
                '<div class="'+content+'">css3,html5,js特效等</div>'+
                '<div class="__Info_Tools">'+
                  '<div class="__Tools">'+
                    '<i class="fa fa-star-o"></i>'+
                    '<span>'+likes+'</span>'+
                  '</div>'+
                '</div>'+
              '</div>'+
            '</a>'+
            '<div class="__List__Footer">'+
             '<div class="__Integral__">'+
                '<span>扣除：<em>'+cost+'积分</em></span>'+
                '<span>日期：<small>'+date+'</small></span>'+
              '</div>'+
            '</div>'+
          '</li>'
        });
        
        $(load_list).html(allproducts);
        })
    
      });

    
      function formatDate(val, formatType) {
        if (val == undefined) {
            return '';
        }
        var reg = /^\/Date\(\d+\)\/$/;
        if(!reg.test(val)) return'';//格式不正确 ，返回空
        var strDate = val.substr(1, val.length - 2);
        var obj = eval('(' + "{ date :new " + strDate + "}" + ')')
        var date = obj.date;
        var year = date.getFullYear();
        var month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1;
        var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
        var datetime = year + '-' + month + '-' + day;
    
        if (formatType == 'yyyy-MM-dd') {
            return datetime;
        } else if (formatType == 'yyyy-MM-dd HH:mm:ss') {
            var hour = date.getHours() < 10 ? '0' + date.getHours() : date.getHours();
            var minute = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes();
            var seconds = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
            //alert(datetime);
            return datetime + ' ' + hour + ':' + minute + ':' + seconds;
        }
        return datetime;
    }
})