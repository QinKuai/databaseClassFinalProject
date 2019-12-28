$(function () {


	var dropz = new Dropzone("#dropz", {
		url: "#",
		maxFiles: 1,
		maxFilesize: 1,
		acceptedFiles: ".zip,.rar",
		parallelUploads: 1,
		init: function() {
			this.on("addedfile", function(file) {
				// setTimeout(function(){
				// 	document.querySelector('.logo').src = file.dataURL;
				// }, 3000);
				console.log('addedfile>>>>>>>');
				
			});
			this.on("success", function(file) {
				console.log("File " + file.name + "uploaded");
			});
		}
	});

	var dropz_show_pic = new Dropzone("#dropz_show_pic", {
		url: "#",
		maxFiles: 1,
		maxFilesize: 1,
		acceptedFiles: ".jpg,.png",
		parallelUploads: 1,
		init: function() {
			this.on("addedfile", function(file) {
				// setTimeout(function(){
				// 	document.querySelector('.logo').src = file.dataURL;
				// }, 3000);
				console.log('addedfile');
				
			});
			this.on("success", function(file) {
				console.log("File " + file.name + "uploaded");
			});
		}
	});

	var dropz_show_cover = new Dropzone("#dropz_show_cover", {
		url: "#",
		maxFiles: 1,
		maxFilesize: 1,
		acceptedFiles: ".jpg,.png",
		parallelUploads: 1,
		init: function() {
			this.on("addedfile", function(file) {
				// setTimeout(function(){
				// 	document.querySelector('.logo').src = file.dataURL;
				// }, 3000);
				console.log('addedfile>>>');
				
			});
			this.on("success", function(file) {
				console.log("File " + file.name + "uploaded");
			});
		}
	});


	$(document).on('click','.DropdownToggle',function(){
		$('.Dropdown').not($(this).parent()).removeClass('__State__Show');
		$(this).parent().toggleClass('__State__Show');
	});

	$(document).on('click','.__Add__Tags__ .__Uploading__List__ >span',function(){
		$(this).toggleClass('__Select__');
    });




})


$(document).ready(function () {

//    var username1 = "qinkuai";
//    $.cookie('username', username1, {
//        path: '/'
//    });

    var username = $.cookie('username');
    //document.getElementById("my").innerHTML = username;


    document.getElementById("my").innerHTML = "sss";

    $('#upload').click(function () {

        
        var formData = new FormData($('#uploadForm')[0]);

        var title = $('#resourcename').val();
        var type = $('#resourcetype option:selected').val();//Ϊ��Ӧ����
        var point = $('#resourcepoint').val();
        var content = $('#resourcecontent').val();
        var resourceurl = $('#resourceurl').val();


        url = url + "?"

        var url = "resource/add?"

        

        url = url + "username=";
        url = url + "qinkuai";
        url = url + "&restype=";
        url = url + type;
        url = url + "&title=";
        url = url + title;
        url = url + "&url=";
        url = url + resourceurl;
        url = url + "&content=";
        url = url + content;
        url = url + "&point=";
        url = url + point;

      
        console.log(url);
        $.ajax({
            type: 'POST',
            url: url,
            data: formData,
            processData: false,   // jQuery��Ҫȥ�����͵�����
            contentType: false,   // jQuery��Ҫȥ����Content-Type����ͷ
            success: function (data) {
                var result = JSON.parse(data);
                alert(result.responseCode);
                console.log(result.responseCode)
            },

            error:function() {
                alert("Error Happen");
            }



        });


 
       
    });





   
});