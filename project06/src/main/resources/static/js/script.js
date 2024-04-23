$(function(){
	var current = new Date();
	var Hours = current.getHours();
	var dayNames= ["일요일","월요일","화요일","수요일","목요일","금요일","토요일"];
	current.setDate(current.getDate());
	
	$('#zoom').sizeUpDown();
	
	$('.today').append(current.getFullYear() + "년 " + (current.getMonth()+1) + '월 ' + current.getDate() + '일 ' + dayNames[current.getDay()]);
	setInterval(function(){
		var seconds = new Date().getSeconds();
		$("#sec").html((seconds < 10 ? "0" : "") + seconds);
		},1000);
	setInterval(function(){
		var minutes = new Date().getMinutes();
		$("#minute").html((minutes < 10 ? "0" : "") + minutes);
		},1000);
	setInterval(function(){
		var hours = new Date().getHours();
		$("#hours").html((hours < 10 ? "0" : "") + hours);
	}, 1000);
	switch(Hours)
	{
		case 0 : 
		case 1 : 
		case 2 : 
		case 3 : 
		case 22 : 
		case 23 : 
			$('#weather').css('background','url(images/night2.jpg) no-repeat');
			$('#weather').css('background-size','cover');
		break
		
		case 4 : 
		case 5 : 
		case 6 : 
			$('#weather').css('background','url(images/day1.jpg) no-repeat');
			$('#weather').css('background-size','cover');
		break
		
		case 7 : 
		case 8 : 
		case 9 : 
		case 10 : 
			$('#weather').css('background','url(images/day2.jpg) no-repeat');
			$('#weather').css('background-size','cover');
		break
		
		case 11 : 
		case 12 : 
		case 13 : 
		case 14 : 
		case 15 : 
			$('#weather').css('background','url(images/day3.jpg) no-repeat');
			$('#weather').css('background-size','cover');
		break
		
		case 16 : 
		case 17 : 
		case 18 : 
			$('#weather').css('background','url(images/day4.jpg) no-repeat');
			$('#weather').css('background-size','cover');
		break
		
		case 19 : 
		case 20 : 
		case 21 : 
			$('#weather').css('background','url(images/night1.jpg) no-repeat');
			$('#weather').css('background-size','cover');
		break

		default: alert("존재하지 않는 시간 입니다.")
	}	
	
	$('#libraryCulturalEvents').each(function(){
		var topDiv = $(this);
		var anchors = topDiv.find('#culturalEventBenner>a');
		var title = topDiv.find('#culturalEventBenner>h2');
		var panelDivs = topDiv.find('div.cont');
		var lastAnchor;
		var lastPanel;
		
		anchors.show();
		lastAnchor = anchors.filter('.on');
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
			
			lastPanel.stop().slideUp(300,function(){
				lastAnchor.removeClass('on');
				currentAnchor.addClass('on');
				title.addClass('off');
				currentPanel.stop().slideDown(300);
				lastAnchor = currentAnchor;
				lastPanel = currentPanel;
				lastAnchor.prev().prev().prev().removeClass('off');
			});
		});
	});

	var interval = 3000;
	$('#calendarAndUsageGuide').each(function(){
		var timer;
		var container = $(this);
		function switchImg(){
			var anchors = container.find('.cont1');
			var first = anchors.eq(0);
			var second = anchors.eq(1);
			first.hide().appendTo(container);
			second.fadeIn(1000);
		}
		function startTimer(){
			timer = setInterval(switchImg,interval);
		}
		function stopTimer(){
			clearInterval(timer);
		}
		startTimer();
		container.hover(stopTimer,startTimer);
	});
	$('#calendarAndUsageGuide').find('.fi').show();

	$('.guideText').each(function(){
		var guideText = this.defaultValue;
		var element = $(this);
		element.focus(function(){
			if(element.val()===guideText){
				element.val('');
				element.removeClass(guideText);
			}
		});
		
		element.blur(function(){
			if(element.val()===''){	
				element.val(guideText);
				element.addClass(guideText);
			}
		});
	});
	$('input.guideText').css('color','#555555');
	
	$('#footerCenter').each(function(){
		$('.sideBook.active').next().show();
		var detailwidth=$('.detail').width();
		
		$('.sideBook').each(function(index){
			$(this).attr('data-index',index);
		}).click(function(){
			var index = $(this).attr('data-index');
			moveSlider(index);
		});
		
		function moveSlider(index){
			var willMoveLeft = index*detailwidth;
			$('.sideBook[data-index='+index+']').addClass('active');
			$('.sideBook[data-index!='+index+']').removeClass('active');
			$('.sideBook').next().hide().animate({left: -1420},500);;
			$('.sideBook.active').next().show().animate({right: 1420},500);
			randomNumber = index;
		};
		
		var size = $('.sideBook').size();
		var randomNumber = Math.round(Math.random()*(size-1));
		moveSlider(randomNumber);
	});
			
	$.getJSON('http://api.openweathermap.org/data/2.5/forecast?id=1846898&appid=006e96b85a6e610cdcc05433485c41b3&units=metric',function(data){
		var $ctemp = data.list[2].main.temp;
		var $cmintemp = data.list[2].main.temp_min;
		var $cmaxtemp = data.list[2].main.temp_max;
		var $now = new Date(Date.now());
		var month = $now.getMonth()+1;
		var $cDate = data.list[2].dt_txt;
		var $cicon = data.list[2].weather[0].icon;
		var $chumidity = data.list[2].main.humidity;
		var $cfeelsLike = data.list[2].main.feels_like;
		
		$('.ctemp').append($ctemp + ' ℃ ');
		$('.cmintemp').append($cmintemp + ' ℃ ');
		$('.cmaxtemp').append($cmaxtemp + ' ℃ ');
		$('.cfeelsLike').append($cfeelsLike + ' ℃ ');
		$('.cicon').append('<img src="http://openweathermap.org/img/wn/' + $cicon + '.png"/>');
		$('.cDate').append($cDate);
		$('.chumidity').append($chumidity + ' % ');
	});
	
 	var sildemenuWidth=$('.sildemenu').width();
	var sildemenusize=$('#hotIssue>img').size();
	
	function moveSlider(index){
		$('#hotIssue').stop().animate({right:index*sildemenuWidth},'500');
		if(index >= sildemenusize){index = 0;}
		randomNumber = index;
	};
	
	var timer1;
	function sildemenustart(){
		timer1 = setInterval(function(){
			randomNumber++;
			if(randomNumber==sildemenusize){randomNumber=0;}
			moveSlider(randomNumber);
		},5000);
	}
	function sidemenustop(){
		clearInterval(timer1);
	}
	
	var cunt = 0;
	$('.stopStart').click(function(){
		if(cunt == 0){
			$(this).find('img').attr('src','images/start.png');
			sidemenustop();
			cunt++;
		}else{
			$(this).find('img').attr('src','images/stop.png');
			sildemenustart();
			cunt = 0;
		}
	});
	
	$('.left').click(function(){
		randomNumber--;
		if(randomNumber<0){randomNumber=(sildemenusize-1);}
		moveSlider(randomNumber);
	});
	$('.right').click(function(){
		randomNumber++;
		if(randomNumber==sildemenusize){randomNumber=0;}
		moveSlider(randomNumber);
	});
	
	var randomNumber = Math.round(Math.random()*(sildemenusize-1));
	moveSlider(randomNumber);
	sildemenustart();
	
	/* 팝업연동 */
	if($.cookie('qwewq')!='123456') $('.popup').show();
	$('.popup').css('cursor','move').draggable();
	$('.popup .close').on('click',function(){
		$('.popup').fadeOut('fast');
		return false;
	});
	$('.popup .onedayClose').on('click',function(){
		$.cookie('qwewq','123456',{expires:1});
		$('.popup').fadeOut('fast');
		return false;
	});
});