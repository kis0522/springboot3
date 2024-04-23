$.fn.sizeUpDown = function(){
	base = 100;
	var mybody = $('body');
	
	$('#zoom li').on('click',function(){
		var zNum = $('#zoom li').index(this);
		if(zNum==0){
			base += 10;
		} else if(zNum==1){
			base = 100;
		} else{
			base -= 10;
		}
		mybody.css('overflow-x', 'auto').css('transform-origin', '50% 0%').css('transform', 'scale('+base/100+')').css('zoom', base+'%');
		return false;
	});
	return this;
};