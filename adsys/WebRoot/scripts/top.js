

var j = 1;
var tid1 = tid2 = null;
function imageMove() {
	 $("#img_" + j).animate({marginLeft:"-187px"},800,"",
			 function() {
		 		p = $("#slide_inner");
			 	$("#img_"+j).remove().appendTo(p).css({marginLeft:"0"});
			 	j++;
			 	if (j == 17) {
					 j = 1;
				}
			 });
	tid1 = setTimeout("imageMove()",3000);
}

$(window).unbind("load.common");
$(window).load(function(){
	$("#loader").fadeOut(800, function(){
		if(!ua("ie6"))$("html,body").css("height","auto");
		$("#loader img").css({display:"none"});
		var i = 0;
		tid2 = setInterval(function() {
			$("#img_" + i).fadeIn(1000);
			i++;
			if (i == 17) {
				clearInterval(tid2);
				tid1 = setTimeout("imageMove()",1300);
			}
		},50);
	});
});
