/*  
===============================================================================
WResize is the jQuery plugin for fixing the IE window resize bug
...............................................................................
                                               Copyright 2007 / Andrea Ercolino
-------------------------------------------------------------------------------
LICENSE: http://www.opensource.org/licenses/mit-license.php
WEBSITE: http://noteslog.com/
===============================================================================
*/
//
//( function( $ )
//{
//	$.fn.wresize = function( f )
//	{
//		version = '1.1';
//		wresize = {fired: false, width: 0};
//
//		function resizeOnce()
//		{
//			if ( $.browser.msie )
//			{
//				if ( ! wresize.fired )
//				{
//					wresize.fired = true;
//				}
//				else
//				{
//					var version = parseInt( jQuery.browser.version, 10 );
//					wresize.fired = false;
//					if ( version < 7 )
//					{
//						return false;
//					}
//					else if ( version == 7 )
//					{
//						var width = $( window ).width();
//						if ( width != wresize.width )
//						{
//							wresize.width = width;
//							return false;
//						}
//					}
//				}
//			}
//
//			return true;
//		}
//
//		function handleWResize( e )
//		{
//			if ( resizeOnce() )
//			{
//				return f.apply(this, [e]);
//			}
//		}
//
//		this.each( function()
//		{
//			if ( this == window )
//			{
//				$( this ).resize( handleWResize );
//			}
//			else
//			{
//				$( this ).resize( f );
//			}
//		});
//
//		return this;
//	};
//
//})( jQuery );

$(document).ready(function(){
	setSize();
	$("iframe[name='banner_top']").attr("src","whwr/home/biaoTi.html");
	$("iframe[name='hidden_top']").attr("src","/gr/ht.do");
	$("iframe[name='menu']").attr("src","whwr/home/caidan.html");
	$("iframe[name='hidden_left']").attr("src","/luopan/zhuoMian/yinCangCaiDan.html");
	$("iframe[name='bottom']").attr("src","whwr/home/yeJiao.html");
});


function setSize(){
	var w = $(window).width();
	var h = $(window).height();
	$("#middle").width(w-6);
	$("#middle").height(h-$("#banner").height()-$("#bottom").height()-6);
	$("#content").width($("#middle").width()-$("#menu").width()-12);
	$("#content").height($("#middle").height()-5);
	$("#workspace").width($("#content").width()-11);
	$("#workspace").height($("#content").height()-31);
}

javascript:window.history.forward(1);