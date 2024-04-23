$(function(){
    $('#zoom').sizeUpDown();

	var defaultTop = 100;
	$(window).on('scroll',function(){
		var scv = $(window).scrollTop();
		if(scv<250) scv=250;
		$('.sm').stop().animate({top:scv+defaultTop+'px'},1000);
	});
	$('.sm').css({top:'350px'},1000);
});