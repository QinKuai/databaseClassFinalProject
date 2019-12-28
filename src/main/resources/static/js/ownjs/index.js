
$(document).ready(function () {

    var username = $.cookie('username');
    document.getElementById("my").innerHTML = username;

   

   //测试用
    $.get('resource/homepage', {},

    //覃脍电脑
     //$.get('resource/homepage', {},
        function (data, status) {

        var json = JSON.parse(data);
        var movies = json.movies;
        var games = json.games;
        var others = json.others;

            document.getElementById("moviesmore").href = "resources/game";
            document.getElementById("gamesmore").href = "resources/movie";
            document.getElementById("othersmore").href = "resources/other";


          


        $.each(movies, function (index, item) {
            //循环获取数据  
            var content = movies[index].content;
            var picaddr = movies[index].picaddr;

            //测试用
            //var picaddr = "http://192.168.0.110:8080/finalproject/" + picaddr;

            var resourceid = movies[index].resourceid;

            var movie = "movie" + (index + 1);
            var pic = "#moviepic" + (index + 1);
            var infor = "#moviecon" + (index + 1);

     
            content = "<h3>"+content+"</h3>" + "<span>" + content +"……"+ "</span>";

           
            //覃脍电脑上这样写
            document.getElementById(movie).href = "resource/" + resourceid;

            //测试用
            //document.getElementById(movie).href = "new_list_detail.html";

            $(infor).html(content);
            $(pic).attr('src', picaddr);
        }
        );

        $.each(games, function (index, item) {
            //循环获取数据  
            var content = games[index].content;
            var picaddr = games[index].picaddr;

            //测试用
            //var picaddr = "http://192.168.0.110:8080/finalproject/" + picaddr;


            var resourceid = games[index].resourceid;
            var game = "game" + (index + 1);
            var pic = "#gamepic" + (index + 1);
            var infor = "#gamecon" + (index + 1);


            content = "<h3>" + content + "</h3>" + "<span>" + content + "……" + "</span>";

            //覃脍电脑上这样写
            document.getElementById(game).href = "resource/" + resourceid;

            //测试用
            //document.getElementById(game).href = "new_list_detail.html";

            $(infor).html(content);
            $(pic).attr('src', picaddr);
        }
        );

        $.each(others, function (index, item) {
            //循环获取数据  
            var content = others[index].content;
            var picaddr = others[index].picaddr;

            //测试用
            //var picaddr = "http://192.168.0.110:8080/finalproject/" + picaddr;

            var resourceid = others[index].resourceid;
            var other = "others" + (index + 1);
            var pic = "#otherspic" + (index + 1);
            var infor = "#otherscon" + (index + 1);

            //覃脍电脑上这样写
            content = "<h3>" + content + "</h3>" + "<span>" + content + "……" + "</span>";

            //测试用
            //document.getElementById(other).href = "new_list_detail.html";

            document.getElementById(other).href = "resource/" + resourceid;
            $(infor).html(content);
            $(pic).attr('src', picaddr);
        }
        );


    });







	function Particles() {
		this.colors = [
			'15, 77, 116',
		]
		this.blurry = true;
		this.border = false;
		this.minRadius = 1;
		this.maxRadius = 6;
		this.minOpacity = 0.9;
		this.maxOpacity = 1;
		this.minSpeed = 0.2;
		this.maxSpeed = 0.3;
		this.fps = 100;
		this.numParticles = 165;
		this.canvas = document.getElementById('bubble_effects');
		this.ctx = this.canvas.getContext('2d');
	}

	Particles.prototype.init = function() {
		this.createCircle();
	}


	Particles.prototype._rand = function(min, max) {
		return Math.random() * (max - min) + min;
	}


	Particles.prototype.createCircle = function() {
		var particle = [];

		for(var i = 0; i < this.numParticles; i++) {
			var self = this,
				color = self.colors[~~(self._rand(0, self.colors.length))];

			var ran = Math.random();
			ranX = self._rand(0,this.canvas.width);

			particle[i] = {
				radius: self._rand(self.minRadius, self.maxRadius),
				xPos: ranX,
				yPos: self._rand(0, this.canvas.height-35),
				xVelocity: 0,
				yVelocity: self._rand(self.minSpeed, self.maxSpeed),
				color: 'rgba(' + color + ',' + self._rand(self.minOpacity, self.maxOpacity) + ')'
			}

			self.draw(particle, i);
		}
		self.animate(particle);
	}

	Particles.prototype.draw = function(particle, i) {
		var self = this,
			ctx = self.ctx;
			
		if(self.blurry === true) {
			var grd = ctx.createRadialGradient(particle[i].xPos, particle[i].yPos, particle[i].radius, particle[i].xPos, particle[i].yPos, particle[i].radius / 1.25);

			grd.addColorStop(1.000, particle[i].color);
			ctx.fillStyle = grd;
		} else {
			ctx.fillStyle = particle[i].color;
		}

		if(self.border === true) {
			ctx.stroke();
		}

		ctx.beginPath();
		var randomRadius = particle[i].radius>25?(particle[i].radius-20):particle[i].radius;
		ctx.arc(particle[i].xPos, particle[i].yPos, randomRadius, 0, 2 * Math.PI, false);
		ctx.fill();
	}

	Particles.prototype.animate = function(particle) {
		var self = this,
			ctx = self.ctx;

		setInterval(function() {
			self.clearCanvas();
			for(var i = 0; i < self.numParticles; i++) {
				particle[i].xPos += particle[i].xVelocity;
				particle[i].yPos -= particle[i].yVelocity;
				if(particle[i].xPos > self.canvas.width + particle[i].radius || particle[i].yPos <=30) {
					//console.log(1)
					self.resetParticle(particle, i);
				} else {
					self.draw(particle, i);
				}
			}
		}, 1000 / self.fps);
	}

	Particles.prototype.resetParticle = function(particle, i) {
		var self = this;

		var random = self._rand(0, 1);

		if(random > .5) {
			particle[i].xPos = -particle[i].radius;
			particle[i].yPos = self._rand(0, this.canvas.height);
		} else {

			var ran = Math.random();
		ranX = self._rand(0,this.canvas.width);

			particle[i].xPos = ranX;
			particle[i].yPos = this.canvas.height - particle[i].radius;
		}
		self.draw(particle, i);
	}

	Particles.prototype.clearCanvas = function() {
		this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
	}

	var particle = new Particles().init();

	window.onresize=function(){
		self.render;
		// console.log('888');
	};
});


//滚动鼠标
var scrollFunc = function (e) {  
    e = e || window.event;
    var scroH = $(this).scrollTop();
    if (e.wheelDelta) {  //判断浏览器IE，谷歌滑轮事件               
        if (e.wheelDelta > 0) { 
            //当滑轮向上滚动时执行的代码段 
            if(scroH === 0){
              document.getElementById('__Nav__Header__New').className='__Header';
              document.getElementById('__Nav__List__').className='__Nav__List';
              $('.__Search').show();
            }
        }  
        if (e.wheelDelta < 0) { 
            //当滑轮向下滚动时执行的代码段   
            document.getElementById('__Nav__Header__New').className='__Header __State__Nav';  
        }  
    } else if (e.detail) {  //Firefox滑轮事件  
        if (e.detail < 0) { 
            //当滑轮向上滚动时执行的代码段   
            if(scroH === 0){
              document.getElementById('__Nav__Header__New').className='__Header';
            }
        }  
        if (e.detail > 0) { 
            //当滑轮向下滚动时执行的代码段  
            document.getElementById('__Nav__Header__New').className='__Header __State__Nav'; 
        }  
    }  
}  
//给页面绑定滑轮滚动事件  Firefox
if (document.addEventListener) {//firefox  
    document.addEventListener('DOMMouseScroll', scrollFunc, false);  
}  
//滚动滑轮触发scrollFunc方法 ie 谷歌  
window.onmousewheel = document.onmousewheel = scrollFunc;


$(function(){

	// index tab切换
	$('.__Click__Envit').on('click',function(){
		$(this).parent().addClass('__State__active').siblings('li').removeClass('__State__active');
		var __vue = $(this).attr('value');
		var TopHeight = $('.__Common__'+__vue+'').offset().top;
		document.getElementById('__Nav__Header__New').className='__Header __State__Nav';
		$("html, body").animate({scrollTop: TopHeight-20}, 1000);
	});

	$(document).on('click','.__Go__To__Top',function(){
		$('.__Header').removeClass('__State__Nav');
	});

	//滚动监听tab距离顶部高度
	$(window).scroll(function(){
		var __Temp__Top = $('.__Common__template').offset().top,
				__Eff__Top = $('.__Common__effects').offset().top,
				__Gam__Top = $('.__Common__game').offset().top,
				__Foo__Top = $('.__Footer__').offset().top,
				this_scrollTop = $(this).scrollTop() + 70;

		if(__Eff__Top>=this_scrollTop && this_scrollTop>=__Temp__Top){
			$('.__Temp').parent().addClass('__State__active').siblings('li').removeClass('__State__active');
		}else if(__Gam__Top>=this_scrollTop && this_scrollTop>=__Eff__Top){
			$('.__Eff').parent().addClass('__State__active').siblings('li').removeClass('__State__active');
		}else if(__Foo__Top>this_scrollTop && this_scrollTop>=__Gam__Top){
			$('.__Gam').parent().addClass('__State__active').siblings('li').removeClass('__State__active');
		}else{
			$('.__Index__').parent().addClass('__State__active').siblings('li').removeClass('__State__active');
		}
	});

})



