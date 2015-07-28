$(document).ready(function(){

	// 0.4锟斤拷锟斤拷锟斤拷值锟酵革拷锟斤拷锟�
	$('.banner div').css('opacity',0.2);

	$('.banner').hover(function(){
	
		var el = $(this);
		
		// 锟饺碉拷锟斤拷锟斤拷影锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
		el.find('div').stop().animate({width:200,height:200},'slow',function(){

			el.find('p').fadeIn('fast');
		});

	},function(){

		var el = $(this);
		
		// 锟斤拷锟斤拷锟斤拷锟斤拷
		el.find('p').stop(true,true).hide();
		
		// 去锟斤拷锟斤拷锟斤拷
		el.find('div').stop().animate({width:60,height:60},'fast');

	}).click(function(){
		
		// 锟斤拷锟酵计憋拷锟斤拷锟斤拷锟�
		
		window.open($(this).find('a').attr('href'));
		
	});
});

$(document).ready(function(){
	$('#zzsc li').hover(function(){
		$('.text',this).stop().animate({
			height:'120px'
		});
	},function(){
		$('.text',this).stop().animate({
			height:'49'
		});
	});
	$('#znews li').hover(function(){
		$('.text',this).stop().animate({
			height:'75px'
		});
	},function(){
		$('.text',this).stop().animate({
			height:'45'
		});
	});
	$('#zzFF li').hover(function(){
		$('.text',this).stop().animate({
			height:'120px'
		});
	},function(){
		$('.text',this).stop().animate({
			height:'49'
		});
	});
});
	

$(document).ready(function () {
    $(document).on("click", '#userClause', function (e) {
        e.preventDefault();
        var boxContent = $('#user_clause_win').clone();
        $.colorbox({
            html: boxContent,
            height: "500px",
            scrolling: true,
            onComplete: function () {
                boxContent.show();
            }
        });
    });
});
