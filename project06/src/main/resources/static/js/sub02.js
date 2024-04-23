$(function(){
    $('#zoom').sizeUpDown();

	var class_closed = 'closed';
	$('.questionBox').each(function(){
		var allq = $('.questionBox').find('.question>li');
		var alla = $('.questionBox').find('.answer>li');
		
		function closeAll(){
			allq.addClass(class_closed);
			alla.addClass(class_closed);
		}
		function open(q,a){
			q.removeClass(class_closed);
			a.removeClass(class_closed);
		}
		
		closeAll();
		
		allq.click(function(){
			var q = $(this);
			var a = q.find('.answer>li');
			closeAll();
			open(q,a);
		});
	});
	$('.sm').sidemenu();
	
	$('.print').on('click',function(){
		window.print();
	});
	
	var question = $('.question>li');
	var answer = $('.answer>li');
	$.getJSON('../data/data.json',function(people){
		$.each(people, function(i, person){
			var text_question = person.question;
			var text_answer = person.answer;
			
			var question = text(text_question);
			var answer = text(text_answer);
			
			question.append(question);
			answer.append(answer);
		});
	})
});