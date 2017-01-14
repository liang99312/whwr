(function($){
	$.fn.extend({
		tdMouseOver:function(){
			if ($("#dvTdTiShi").size()===0) $("body").append("<div id='dvTdTiShi' style='display: inline;position: relative; width: 150px; height: auto; background: #F8C301; border-radius: 5px; line-height: 30px;'><div style='background: none; position: absolute; top: -24px; left: 20px; width: 0; height: 0; font-size: 0; border: solid 12px; border-color: transparent transparent #F8C301 transparent;'></div><span id='dvTdTiShi_span'></span></div>");
			$(this).find("td").each(function(){
				$(this).mouseover(function(event){
					if($(this).html().length === 0){
						$("#dvTdTiShi").hide();
						return;
					}
					$("#dvTdTiShi_span").html($(this).html());
					$("#dvTdTiShi").css("top", $(this).offset().top + $(this).outerHeight()+12);
					$("#dvTdTiShi").css("left", $(this).offset().left);
					$("#dvTdTiShi").css("z-index", 9999);
					$("#dvTdTiShi").show();
				});
				$(this).mouseout(function(){
					$("#dvTdTiShi").hide();
				});
			});
		}
	});
})(jQuery);