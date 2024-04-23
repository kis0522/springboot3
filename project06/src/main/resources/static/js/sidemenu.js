$.fn.sidemenu = function(){
	sidemenu = $(this).find('li');
	sidemenu2 = $(this).find('ul');
	sidemenu2.hide();
	sidemenu.hover(
		function(){
			$(this).find('ul').stop(true, true).slideDown();
		},
		function(){
			$(this).find('ul').stop(false, false).slideUp();
		}
	);
	return this;
};