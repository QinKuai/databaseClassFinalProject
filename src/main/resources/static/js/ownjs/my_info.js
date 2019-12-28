
var finalname;
var finalmail;
var finalpwd;
$(function(){

	//click
	$(document).on('click','.__DropTit',function(){
		$(this).parents('.__Cont__Drop').toggleClass('open');
	});

  //change user name
  $(document).on('click','.__C__Name',function(){
    $('.__Edit__Component,.__Nickname').show();
  });

  //change telphone
  $(document).on('click','.__C__Number',function(){
    $('.__Edit__Component,.__Tel_Number').show();
  });

  //change
  $(document).on('click','.__C__Psw',function(){
    $('.__Edit__Component,.__Passwords').show();
  });

  //取消
  $(document).on('click','.__Cancle',function(){
    $('.__Edit__Component').hide();
    $(this).parents('._Normal_').hide();
  });

  $(document).ready(function(){
    $.get("http://192.168.0.110:8080/finalproject/user/userhomepage", {
    	username: $.cookie('username')
    }, function (data, status) {
      var json = JSON.parse(data);
      var allproducts = "";
      
      var username = json.user.username;
      finalname = username;
      var email = json.user.email;
      finalmail = email;
      var password = json.password;
      finalpwd = password;
      //alert(password);

      var dark_password = "";

      for(var i=0;i<password.length;i++){
        dark_password += "*";
      }

      $(usernameid).html(username);
      $(emailid).html(email);
      $(passwordid).html(dark_password);

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

  $("#username_b").bind("click", function () {
    var new_username = (document).getElementById("newname");

    $(usernameid).text(new_username);

    finalname = new_username;

    //location=location;
  });

  $("#email_b").bind("click", function () {
    var new_email = (document).getElementById("newemail");

    $("#emailid").html(new_email);
    alert("修改成功");

    finalmail = new_email;
    //location=location;
  });

  $("#password_b").bind("click", function () {
    var new_password = (document).getElementById("newpassword");
    //var dark_newpassword = "";
    //alert(new_password.value);
    // for(var i=0;i<new_password.length;i++){
    //   dark_newpassword += "*";
    // }
    //alert(dark_newpassword);
    $("#passwordid").html(new_password.value);
    alert("修改成功");

    finalpwd = new_password;

    //location=location;
  });

  $("#save_b").bind("click", function () {
    // var new_username = (document).getElementById(usernameid);
    // var new_email = (document).getElementById(emailid);
    // var new_password = (document).getElementById(passwordid);
    alert(finalname);
    alert(finalmail);
    alert(finalpwd.value);

    $.get("http://192.168.0.110:8080/finalproject/user/update", {
              username: finalname,
              uEmail: finalmail,
              uPassword: finalpwd.value
            }, function (data, status) {
              console.log(data);
            });

    
    alert("恭喜您修改成功");
    
  });
    
})