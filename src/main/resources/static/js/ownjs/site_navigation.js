$(document).ready(function(){
  var navContainer = document.getElementById("_Nav_Container");
  var navBox = document.getElementById("Nav_Box");
  var text = document.getElementById("__Text__List");
  var navBoxChild = navBox.children;
  var textChild = text.children;
  var num = navContainer.offsetTop;
  var a = navContainer.offsetHeight;
  window.onscroll = function(){
      var scrollTop = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop;
      if(scrollTop >= num){
          navContainer.className = "__Nav__Tabs __Fixed";
          text.style.paddingTop = a +"px";
      }else{
          navContainer.className = "__Nav__Tabs";
          text.style.paddingTop = "";
      }
      //当导航与相应文档接触的时候自动切换
      //method1
      for(var i=0;i<navBoxChild.length;i++){
          if( scrollTop + a >= textChild[i].offsetTop){
              for(var j=0;j<navBoxChild.length;j++){
                  navBoxChild[j].className = "";
              }
              navBoxChild[i].className = "__Active__";
         }
      }
  };
  for(var i=0;i<navBoxChild.length;i++){
      var interval;
      navBoxChild[i].index = i;
      navBoxChild[i].onclick = function(){
        var self = this;
        // console.log(textChild[self.index].offsetTop);
        document.body.scrollTop = document.documentElement.scrollTop = textChild[self.index].offsetTop - 65;
      };
  }
});
