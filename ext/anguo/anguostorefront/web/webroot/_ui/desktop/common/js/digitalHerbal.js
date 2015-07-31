$(function() {
    var $tabList = $('.tabList');

    $tabList.each(function() {
        $(this).find('.tabNav .tab').click(function(){
            $me = $(this);
            $tabContainer = $tabList.find('.tabContainer');
            var index = $me.index();
            $me.addClass('on').siblings().removeClass('on');
            $target = $tabContainer.find('.tabCont').eq(index);
            $target.show().siblings().hide();
        });
    });
});