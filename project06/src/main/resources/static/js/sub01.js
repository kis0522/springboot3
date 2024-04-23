$(function(){
	var guideClass = 'guide';
	$('.guideText').each(function(){
		var guideText = this.defaultValue;
		var userPWD = $('#userPWD');
		var userId = $('#userId');
		var oneId = $('#oneId');
		var element = $(this);
		userId.focus(function(){
			if(userId.val()===guideText){
				userId.val('');
				userId.removeClass(guideClass);
			}
			//$('#userPWD').attr('type','password');
		});
		userPWD.focus(function(){
			if(userPWD.val()===guideText){
				userPWD.val('');
				userPWD.removeClass(guideClass);
			}
			$('#userPWD').attr('type','password');
		});
		oneId.focus(function(){
			if(oneId.val()===guideText){
				oneId.val('');
				oneId.removeClass(guideClass);
			}
		});
		
		element.blur(function(){
			if(element.val()===''){	
				element.val(guideText);
				element.addClass(guideClass);
				$('#userPWD').attr('type','text');
			}
		});
		if(element.val()===guideText){			
			element.addClass(guideClass);
		}
	});
	
	$('section').each(function(){
		var topDiv = $(this);
		var anchors = topDiv.find('.tabLogin>a');
		var panelDivs = topDiv.find('div.login');
		var lastAnchor;
		var lastPanel;
		
		anchors.show();
		lastAnchor = anchors.filter('.active');
		lastPanel = $(lastAnchor.attr('href'));
		panelDivs.hide();
		lastPanel.show();
		
		anchors.click(function(event){
			event.preventDefault();
			var currentAnchor = $(this);
			var currentPanel = $(currentAnchor.attr('href'));
			
			//같은 탭이라면 중단
			if(currentAnchor.get(0) === lastAnchor.get(0)){
				return;
			};
			
			lastPanel.stop().hide(0,function(){
				lastAnchor.removeClass('active');
				currentAnchor.addClass('active');
				currentPanel.stop().show(0);
				lastAnchor = currentAnchor;
				lastPanel = currentPanel;
			});
		});
	});
	
	function time(){
		var s = 59;
		var m = 2;
		
		setInterval(function(){
			$('.time').html("남은 시간 : "+m+"분 "+s+"초");
			if(s == 0 && m >= 1){
				s = 60;
				m--;
			}
			s--;
			if(m == 0 && s <= -1){
				$('.time').html("세션이 만료되었습니다.");
				s = 0;
			} 
		},1000);
		if(m == 0 && s == 0){
			clearInterval(time); 
		} 
	}
	time();
});

		