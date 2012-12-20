
/*---------------------------------------------
	Check user agent
  ---------------------------------------------*/

var nut = navigator.userAgent.toLowerCase();
var uaCheck = {
	"ie"		:nut.indexOf("msie") != -1,
	"ie6"		:nut.indexOf("msie 6") != -1,
	"ie7"		:nut.indexOf("msie 7") != -1,
	"ff"		:nut.indexOf("firefox") != -1,
	"safari"	:nut.indexOf("safari") != -1,
	"chrome"	:nut.indexOf("chrome") != -1,
	"opera"		:nut.indexOf("opera") != -1,
	"smartphone"	:nut.indexOf("iphone") != -1 || nut.indexOf("ipad") != -1 || nut.indexOf("ipod") != -1 || nut.indexOf("android") != -1,
	"win"		:navigator.appVersion.indexOf ("Win"),
	"mac"		:navigator.appVersion.indexOf ("Mac")
};
	
function ua(target) {return uaCheck[target];}



/*---------------------------------------------
	JavaScript on
  ---------------------------------------------*/

$("html").addClass("jsOn");
if(ua("win")) $("html").addClass("win");
if(ua("smartphone")) $("html").addClass("smartphone");



/*---------------------------------------------
	Check browser size
  ---------------------------------------------*/

function getBrowserWidth() {
	return window.innerWidth || self.innerWidth || (document.documentElement&&document.documentElement.clientWidth) || document.body.clientWidth;
}

function getBrowserHeight() {
	return window.innerHeight || self.innerHeight || (document.documentElement&&document.documentElement.clientHeight) || document.body.clientHeight;
}



/*---------------------------------------------
	"Back" button
  ---------------------------------------------*/

if(!ua("smartphone") && (ua("opera") || ua("ff") || ua("safari"))) {
	window.onunload = function() {}
}



/*---------------------------------------------
	Openning
  ---------------------------------------------*/

$(window).bind("load.common",function(){
	$("#loader").fadeOut(500,function(){
		if(!ua("ie6"))$("html,body").css("height","auto");
		$("#loader img").remove();
	});
});



/*---------------------------------------------
	Link
  ---------------------------------------------*/

var topID = (ua("ie6")) ? "#wrapper" : (ua("safari")) ? "body" : "html";
var currentURL = location.href;
var currentNohash = currentURL.split("#");

$(window).load(function(){
	$("a").click(function(){
		$(this).blur();
		var link = $(this).attr("href");
		var nohash = link.split("#");
		if(currentNohash[0] == "http://smart4me.net"+nohash[0] || currentNohash[0] == "https://smart4me.net"+nohash[0]) link = "#"+nohash[1];

		if($(this).hasClass("faq_3")) {
		} else if(link.charAt(0)=="#") {
			var offset = $(link).offset();
			$(topID).stop().animate({scrollTop: offset.top}, 800, "easeInOutCubic");
		} else if($(this).hasClass("blank")) {
			window.open(link);
		} else {
			$("#loader").fadeIn(500,function(){
				window.location.href = link;
			});
		}
		return false;
	});
});



/*---------------------------------------------
	News
  ---------------------------------------------*/

function moveNews() {
	$("ul.news li").eq(0).animate({marginTop:"-20px"},800,"easeInOutQuad",function(){
		$(this).remove().appendTo("ul.news").css({marginTop:"0"});
	});
}

$(window).load(function(){
	if($("ul.news li").length > 1){
		var tid = null;
		tid = setInterval("moveNews()",3000);
	}
});


/*---------------------------------------------
	Full screen
  ---------------------------------------------*/

function fullopen(strURL, windowname) {
	var sw = screen.availWidth;
	var sh = screen.availHeight;
	if (ua("win") && ua("chrome")) {
		sw -= 10;
		sh -= 58;
	} else if (ua("win") && ua("safari")) {
		sw -= 16;
		sh -= 36;
	}
	var options = "location=no, menubar=no, status=no, scrollbars=yes, resizable=yes, toolbar=no,left=0,top=0,width=" + sw + ",height=" + sh;
	window.open(strURL, windowname, options);
}
/*---------------------------------------------
	QR code for gallery page
  ---------------------------------------------*/
  
$(window).load(function(){
	$("div.site_wr").hover(function(){
		$(this).find(".qr_img").css({display: "block"});
	}, function() {
		$(this).find(".qr_img").css({display: "none"});
	});
});


/*---------------------------------------------
	Not display social buttons in https page
  ---------------------------------------------*/

var so = "<div id='facebook'>";
so += "<iframe src='http://www.facebook.com/plugins/like.php?app_id=109833182441414&amp;href=http%3A%2F%2Fsmart4me.net%2F&amp;send=false&amp;layout=button_count&amp;width=120&amp;show_faces=false&amp;action=like&amp;colorscheme=light&amp;font=arial&amp;height=21' scrolling='no' frameborder='0' style='border:none; overflow:hidden; width:120px; height:21px;' allowTransparency='true'></iframe>";
so += "</div>";
so += "<div id='sher_wr' class='clfix'>";
so += "<div class='sher'>";
so += "<a href='http://twitter.com/share' class='twitter-share-button' data-url='http://smart4me.net/' data-text='smart4me' data-count='horizontal' data-lang='en'>Tweet</a>";
so += "<script type='text/javascript' src='http://platform.twitter.com/widgets.js'></script>";
so += "</div>";
so += "</div>";

$(window).load(function() {
	var strURL = location.href;
	if(strURL.slice(0, 5) !== "https") {
		$("#sherbox").html(so);
	}
});


