(function($){
	$.fn.extend({
		fmBuilt:function(){
			$(this).find("input,select").r2t();
		},
		
		r2t:function(){
			$(this).keydown(function(e){
				var event = $.event.fix(e);
				var evObj = event.target;
				if (event.keyCode===13){
					var list = $(evObj).parentsUntil("table").parent().find("select,input:visible").not($("[readonly]")).not($("[disabled]"));
					var seq = list.index(this)+1;
					if (seq<=list.length){
						list[seq].focus();
						if (list[seq].type==="text") list[seq].select();
					}
				}
			});
		},
		
		inputKit:function(opt){
			var srcData=opt.src;
			var objData=opt.obj;
			var fun = opt.fun;
			if ($("#divTiShi").size()===0) $("body").append("<div id='divTiShi' class='tiShi'><table></table></div>");
			
			$(this).focus(function(){
				var offset = $(this).offset();
				$("#divTiShi table").html("");
				$("#divTiShi").css("top", $(this).outerHeight()+offset.top);
				$("#divTiShi").css("left", offset.left);
				window.preEvData=null;
				$(this).tiShiKit(opt);
				window.inputKitEvObj=this;
				if ((window.inputKitTimer!==null)&&(window.inputKitTimer!==undefined)) clearInterval(window.inputKitTimer);
				window.inputKitTimer=setInterval(function(){ $(window.inputKitEvObj).tiShiKit(opt); }, 200);
				$(this).blur(function(){
					if ((window.inputKitTimer!==null)&&(window.inputKitTimer!==undefined)) clearInterval(window.inputKitTimer);
					window.inputKitTimer=null;
					$("#divTiShi").hide();
					$(window.inputKitEvObj).unbind("blur");
					
					if ($("#divTiShi tr").size()===1){
						var sz = srcData.sz;
						var id=$("#divTiShi tr[class=focus] td:first").html();
						if ((objData===null)||(objData.id!==id)){
							for (var i=0; i<sz.length; i++){
								if (sz[i].id==id){
									if (fun){
										fun(sz[i]);
									}else{
										var u={"id":sz[i].id,"mc":sz[i].mc,"dm":sz[i].dm,"bh":sz[i].bh};
										$(this).val(u.mc);
										if ((objData!==null)&&(objData!==undefined)&&(objData.length>0)) eval(objData+"=u");
									}
									break;
								}
							}
						}
					}else if ($("#divTiShi tr").size()==0){
						if ((objData!==null)&&(objData!==undefined)&&(objData.length>0)) eval(objData+"=null");
					}
				});
			});

			//响应按键事件
			$(this).keydown(function(e){
				var event = $.event.fix(e);
				var evObj = event.target;
				switch (event.keyCode){
				case 13:
					var id=$("#divTiShi tr[class=focus] td:first").html();
					var sz = srcData.sz;
					for (var i=0; i<sz.length; i++){
						if (sz[i].id==id){
							if (fun){
								fun(sz[i]);
							}else{
								var u={"id":sz[i].id,"mc":sz[i].mc,"dm":sz[i].dm,"bh":sz[i].bh};
								$(evObj).val(u.mc);
								if ((objData!==null)&&(objData!==undefined)&&(objData.length>0)) eval(objData+"=u");
							}
							break;
						}
					}
					return;
				case 38:
					var row = $("#divTiShi tr").size();
					if (row>0){
						var i = $("#divTiShi tr").index($("#divTiShi tr[class=focus]")[0]);
						i = (i>0) ? --i : row-1;
						$("#divTiShi tr").removeClass("focus");
						$("#divTiShi tr:eq("+i+")").addClass("focus");
					}
					break;
				case 40:
					var row = $("#divTiShi tr").size();
					if (row>0){
						var i = $("#divTiShi tr").index($("#divTiShi tr[class=focus]")[0]);
						i = ((i<0)||(i==(row-1))) ? 0 : ++i;
						$("#divTiShi tr").removeClass("focus");
						$("#divTiShi tr:eq("+i+")").addClass("focus");
					}
					break;
				}
			});
		},
		
		tiShiKit:function(opt){
			var srcData=opt.src;
			var objData=opt.obj;
			var fun=opt.fun;
			var guoLvTiaoJian=opt.gltj;

			//如果输入框的内容有变动，重新生成提示框
			var evData=$(this).val();
			if (evData!==window.preEvData){
				window.preEvData=evData;
				var count=0;
				var s="";
				var sz=srcData.sz;
				for (var i=0, len=sz.length; i<len; i++){
					if ((sz[i].dm==null)||(sz[i].dm==undefined)) sz[i].dm="";
					if ((guoLvTiaoJian==null)||(guoLvTiaoJian==undefined)){
						if ((sz[i].mc.indexOf(evData)>-1)||(sz[i].dm.indexOf(evData)>-1)){
							s+="<tr><td style='display:none;'>"+sz[i].id+"</td><td>"+sz[i].mc+"</td></tr>";
							if (++count>9) break;
						}
					}else{
						if (((sz[i].mc.indexOf(evData)>-1)||(sz[i].dm.indexOf(evData)>-1))&&(eval(guoLvTiaoJian))){
							s+="<tr><td style='display:none;'>"+sz[i].id+"</td><td>"+sz[i].mc+"</td></tr>";
							if (++count>9) break;
						}
					}
				}
				$("#divTiShi table").html(s);
				$("#divTiShi tr:eq(0)").addClass("focus");
				$("#divTiShi").show();

				$("#divTiShi tr").mouseover(function(){
					$(this).siblings().removeClass("focus");
					$(this).addClass("focus");
				});

				var evObj=this;
				$("#divTiShi tr").mousedown(function(){
					var id=$(this).find("td:first").html();
					for (var i=0; i<sz.length; i++){
						if (sz[i].id==id){
							if (fun){
								fun(sz[i]);
							}else{
								var u={"id":sz[i].id,"mc":sz[i].mc,"bh":sz[i].bh};
								$(evObj).val(u.mc);
								if ((objData!==null)&&(objData!==undefined)&&(objData.length>0)) eval(objData+"=u");
							}
							break;
						}
					}
				});

			};
		},
		
		geXingShuJuBuilt:function(sz, opt){
			if ((sz==null)||(sz==undefined)||(sz.length<1)){
				$(this).parent().parent().hide();
				$(this).html("");
				return;
			}
			$(this).parent().parent().show();
			//var w = $(this).find("td:first").css("width");
			$(this).html("");
			var len=sz.length;
			var col = (len>4) ? 2 : 1;
			var str = (col==2) ? "<tr><td class='title'></td><td style='width:280px'></td><td class='title'></td><td></td></tr>" : "<tr><td class='title'></td><td></td></tr>";
			for (var i=0; i<len; i++){
				if (i%col==0) $(this).append(str);
				var gxsjId="sjId"+i;
				var sj=sz[i];
				var sr;
				var sjgs=$(document).shuJuGeShi(sj);
				switch(sj.sjlx){
				case 0:
					sr="";
					break;
				case 1:
					sr="<input id='"+gxsjId+"' "+sjgs+" type='text'/>";
					break;
				case 2:
					sr="<textarea id='"+gxsjId+"' "+sjgs+" rows='3' cols='50'></textarea>";
					break;
				case 3:
					sr="<select id='"+gxsjId+"' "+sjgs+"></select>";
				default:
					sr="";
				}
				//$(this).append("<tr><td class='title'>"+sj.mc+"：</td><td>"+sr+"</td></tr>");
				$(this).find("td:eq("+(i*2)+")").html(sj.mc);
				$(this).find("td:eq("+(i*2+1)+")").html(sr);
				if (sj.val!==undefined) $("#"+gxsjId).val(sj.val);
				$(document).xiangYingShiJian(gxsjId, sj);
			}
			//$(this).find("tr").mousedown(function(){ opt.seq = $(this).prevAll().length; });
			if (opt!==undefined){
				//$(this).find("td[class='title']").mousedown(function(){ opt.seq = $(this).parentsUntil("table").find("td[class='title']").index(this); });
				$(this).find("td").mousedown(function(){ opt.seq = parseInt($(this).parentsUntil("table").find("td").index(this)/2); });
			}
		},
		
		shuJuGeShi:function(shuJu){
			switch(shuJu.sjgs){
				case 0:
					return "";
				case 1:
					return " class='text'";
				case 2:
					return " class='shuZi'";
				case 3:
					return " class='riLi'";
				default:
					return "";
			}
		},
		
		xiangYingShiJian:function(id, shuJu){
			if (shuJu.sjgs==3){
				$("#"+id).focus(function(e){ calendar(e); });
				return;
			}
			if ($("#divTiShi").size()==0) $("body").append("<div id='divTiShi' class='tiShi'><table></table></div>");
			switch(shuJu.xysj){
				case 0:
					break;
				case 1:
					$("#"+id).inputKit({"src":$(document).piPeiShuJu(shuJu),"obj":""});
					break;
				default:
			}
		},

		piPeiShuJu:function(shuJu){
			switch(shuJu.sjly){
				case 0: return null;
				case 1: return null;
				case 2:
					if (lp_CangKuList.sz.length==0) lp_cxCangKu();
					return lp_CangKuList;
				case 3:
					if (lp_WuZiLeiBieList.sz.length==0) lp_cxWuZiLeiBie();
					return lp_WuZiLeiBieList;
				case 4:
					if (lp_WuZiZiDianList.sz.length==0) lp_cxWuZiZiDian();
					return lp_WuZiZiDianList;
				case 5:
					if (lp_YuanGongList.sz.length==0) lp_cxYuanGong();
					return lp_YuanGongList;
				case 6:
					if (lp_KeHuList.sz.length==0) lp_cxKeHu();
					return lp_KeHuList;
				case 7:
					if (lp_XuQiuLeiBieList.sz.length==0) lp_cxXuQiuLeiBie();
					return lp_XuQiuLeiBieList;
				case 8:
					if (lp_XuQiuBiaoZhunList.sz.length==0) lp_cxXuQiuBiaoZhun();
					return lp_XuQiuBiaoZhunList;
				case 9:
					if (lp_XuQiuList.sz.length==0) lp_cxXuQiu();
					return lp_XuQiuList;
				case 10:
					var fl = shuJu.zdfl;
					if((lp_ZiDianList[fl]==null)||(lp_ZiDianList[fl]==undefined)||(lp_ZiDianList[fl].sz.length==0)){
						//ziDianList[fl]={"sz":[],"xh":-1};
						lp_cxZhuZiZiDian(fl);
					}
					return lp_ZiDianList[fl];
				default: return null;
			}
		}
		
	});
})(jQuery);