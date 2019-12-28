$(function(){

  //马上登录按钮事件
  $('.__Go__To__Login__').click(function(){
    $(".__Account__").click();
    $('.__Changes__Password').removeClass('__State__Show__');
    $('.__Changes__Login__').show();
  });

  //输入框输入时模拟placeholder效果
    var oInput = $(".__Input__ input");
    oInput.focus(function () {
      $(this).siblings("label").hide();
    });
    oInput.blur(function () {
      if($(this).val()==""){
        $(this).siblings("label").show();
      }
    });

  // 输入框内容变化按钮颜色发生变化
  oInput.keyup(function () {
    var username_check = document.getElementById('username-check-login');

    if($('#__Number__').val()!='' && $('#__Password__').val()!=''){ //&& username_check.style.display=="none"){
      $(".__Login__Btn__").removeClass("__State__Off__")
    }else{
      $(".__Login__Btn__").addClass("__State__Off__")
    }
  });

  // 输入框内容变化按钮颜色发生变化
  oInput.keyup(function () {
    if($('#__Res__Mail__').val()!='' && $('#__Res__Code__').val()!='' && $('#__Set__Password__').val()!=''){
      $(".__Res__Btn__").removeClass("__State__Off__")
    }else{
      $(".__Res__Btn__").addClass("__State__Off__")
    }
  });

  // 输入框内容变化按钮颜色发生变化
  oInput.keyup(function () {
    if($('#__Res__Tel__C').val()!='' && $('#__Res__Code__C').val()!='' && $('#__Set__Password__C').val()!=''){
      $(".__Confirm__Btn__").removeClass("__State__Off__")
    }else{
      $(".__Confirm__Btn__").addClass("__State__Off__")
    }
  });

  

  // 登录的回车事件
  $(window).keydown(function(event) {
      if (event.keyCode == 13) {
        $('.__Login__Btn__').trigger('click');
      }
    });

})

function strlen(str) {
    var len = 0;
    for (var i = 0; i < str.length; i++) {
        var c = str.charCodeAt(i);
        if ((c >= 0x0001 && c <= 0x007e) || (c >= 0xff60 && c <= 0xff9f)) {
            len++;
        } else {
            len += 2;
        }
    }
    return len;
}

function username_check(){
  var username = document.getElementById('__Set__Username__');
  var username_check = document.getElementById('username-check');
  
  if(strlen(username.value)>20){
    username_check.style.display = "inline";
  }else{
    username_check.style.display = "none";
  }
}

function username_check_login(){
  var username = document.getElementById('__Number__');
  var username_check = document.getElementById('username-check-login');
  
  if(strlen(username.value)>20){
    username_check.style.display = "inline";
  }else{
    username_check.style.display = "none";
  }
}

function vcode_check(){
  var vcode = document.getElementById('__Res__Code__');
  var vcode_check = document.getElementById('vcode-check');

}

function password_check(e){
  var password = e.value;
  var password_check = document.getElementById('password-check');

  if (strlen(password) < 6 && strlen(password) != 0) {
    password_check.style.display = "inline";
  } else if (strlen(password) > 16) {
    password_check.style.display = "inline";
  } else {
    password_check.style.display = "none";
  }
}

function password_recheck(e){
  var password = document.getElementById('__Set__Password__').value;
  var password2 = e.value;
  var password_recheck = document.getElementById('password-recheck');

  if (password != password2 && password2 != "") {
    password_recheck.style.display = "inline";
  } else{
    password_recheck.style.display = "none";
  }
}

function email_check() {
  var mailAddr = document.getElementById('__Res__Mail__').value;
  var mailMsg = document.getElementById('email-check');
  console.log(mailAddr);
  if (mailAddr == "") {
    mailMsg.style.display = "inline";
  } else if (mailAddr.split("@").length == 1 || mailAddr.split(".").length == 1) {
    mailMsg.style.display = "inline";
  } else {
    mailMsg.style.display = "none";
  }
}
    



