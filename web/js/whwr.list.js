(function($){
	$.fn.extend({
		popTopBar:function(title){
			var divId = $(this).attr("id");
			var topBarDiv="<div class='topBar'><table><tr><td>"+title+"</td><td style='width:19px'><div class='close' id='"+divId+"_close'></div></td></tr></table></div>";
			$(this).prepend(topBarDiv);
			$(this).find("#"+divId+"_close").click(function(){
				if ($("#"+divId).css("z-index")<10){
					window.parent.$("#divIfm").hide();
				}else{
					$("#"+divId).hide();
					if ($("#board").css("z-index")<10)
						$("#board").hide();
					else
						$("#board").css("z-index", $("#board").css("z-index")-10);
				}
			});
		},
		
		setListBottom:function(shuJu, fun){
			var divId = $(this).attr("id");
			var btmDiv = "<div id='"+divId+"_btm' style='width:100%;text-align:center;margin-top:1px;'>"
					   + "<table style='float:none;margin:auto;width:800px;border:0'><tr>"
					   + "<td style='width:40%; text-align:center;border:0'>"
					   + "第<a id='"+divId+"_yeXu'>0</a>页/共<a id='"+divId+"_zys'>0</a>页/共<a id='"+divId+"_jls'>0</a>条记录"
					   + "</td><td style='width:20%; text-align:right;border:0'>"
					   + "<input type='button' value='第一页'><input type='button' value='上一页'></td>"
					   + "<td style='text-align:left;border:0'>"
					   + "<input type='button' value='下一页'><input type='button' value='最末页' class='btnLp'>"
					   + "直接跳到第<input type='text' id='"+divId+"_thePage' class='thePage'>页"
					   + "<input type='button' class='btnGo'></td></tr></table></div>";
			$(this).parent().append(btmDiv);
			$(this).parent().find("#"+divId+"_btm input[type=button]").each(function(i){
				switch(i){
					case 0:
						$(this).click(function(){
							if (shuJu.yx>1){
								shuJu.yx = 1;
								fun();
							}else{
								alert("已经是第一页了！不能再往前翻页了!");
							}
						});
						break;
					case 1:
						$(this).click(function(){
							if (shuJu.yx>1){
								shuJu.yx = shuJu.yx-1;
								fun();
							}else{
								alert("已经是第一页了！不能再往前翻页了!");
							}
						});
						break;
					case 2:
						$(this).click(function(){
							if (shuJu.yx>=shuJu.zys){
								alert("已经是最后一页了！不能再往后翻页了!");
							}else{
								shuJu.yx = shuJu.yx+1;
								fun();
							}
						});
						break;
					case 3:
						$(this).click(function(){
							if (shuJu.yx>=shuJu.zys){
								alert("已经是最后一页了！不能再往后翻页了!");
							}else{
								shuJu.yx = shuJu.zys;
								fun();
							}
						});
						break;
					case 4:
						$(this).click(function(){
							var zhiDingYeXu = parseInt($("#"+divId+"_thePage").val());
							if (isNaN(zhiDingYeXu)){ return alert('页序数字不正确，请重新输入123！'); }
							if (zhiDingYeXu>shuJu.zys){ return alert('页序数字不正确，请重新输入！'); }
							if (zhiDingYeXu<1){ return alert('页序数字不正确，请重新输入！'); }
							if (zhiDingYeXu==shuJu.yx){ return alert('页序为当前页，请重新输入！'); }
							shuJu.yx = zhiDingYeXu;
							fun();
						});
						break;
					default:
						alert("不可识别的对象！");
				}
			});
		},
		
		setPage:function(yx, zys,jls){
			var divId = $(this).attr("id");
			$("#" + divId + "_yeXu").html(yx);
			$("#" + divId + "_zys").html(zys);
			$("#" + divId + "_jls").html(jls);
		},
		
		tableHilight:function(obj){
			$(this).mouseover(function(){
				$(this).siblings().removeClass("selected");
				$(this).addClass("selected");
			});
			$(this).mousedown(function(){ obj.seq = $(this).prevAll().length-1; });
			return this;
		},
		
		built:function(option){
			var col = $(this).find("tr:first td").size();
                        if(col === 0){
                            col = option.tds;
                        }
			var isKeep = $(this).find("tr:first").hasClass("biaoTou");
			var theWidth;
			if (isKeep){
				$(this).find("tr:gt(0)").remove();
			}else{
				theWidth = $.map($(this).find("tr:first td"), function(e){ return $(e).width(); });
				$(this).find("tr").remove();
			}
				
			//var col = $(this).find("tr:first td").size();
			if ((option)&&(option.data)&&(option.data.length>0)){
				var data=option.data;
				var obj=option.obj;
				var fun=option.dbclick;
				var fit=option.fit;
				for (var i=0; i<data.length; i++){
					var tr = data[i];
					var td = tr.td;
					var s = "";
					for (var j=0; j<col; j++){ s += "<td>"+td[j]+"</td>"; }
					$(this).append((tr.c==null) ? "<tr>"+s+"</tr>" : "<tr class='"+tr.c+"'>"+s+"</tr>");
				}
				if (!fit){
					for (var i=data.length; i<20; i++){
						var s = "";
						for (var j=0; j<col; j++){ s += "<td></td>"; }
						$(this).append("<tr>"+s+"</tr>");
					}
				}
				$(this).find("tr").mousedown(function(){
					obj.seq = $(this).parent().find("tr:first").hasClass("biaoTou") ?  $(this).prevAll().length-1: $(this).prevAll().length;
				});
				if ((obj)&&(fun)&&(typeof(fun)=="function")){
					if (isKeep){
						$(this).find("tr:gt(0)").dblclick(function(){ fun(); });
					}else{
						$(this).find("tr").dblclick(function(){ fun(); });
					}
					$(this).listKeyDown(obj, fun);
				}
			}else{
				if ((option)&&(option.fit==true)) return;
				for (var i=0; i<20; i++){
					var s = "";
					for (var j=0; j<col; j++) s += "<td></td>";
					$(this).append("<tr>"+s+"</tr>");
				}
			}
			if (!isKeep) $(this).setColWidth(theWidth);
			$(this).find("tr").each(function(i){
				$(this).mouseup(function(e){  
		                //如果按下的是右键，则执行函数   
		                if(3 == e.which||1 == e.which){  
		                    $(this).addClass("onc").siblings("tr").removeClass("onc");
		                }  
		            }); 
			});
			return this;
		},
		
		listKeyDown:function(obj, funName){
			$(this).keydown(function(e){
				var event = $.event.fix(e);
				var evObj = event.target;
				switch (event.keyCode){
				case 13:
					var f = eval(funName);
					f();
					break;
				case 38:
					if ((obj.seq<=0)||(obj.seq>=obj.list.length)) obj.seq = obj.list.length;
					--obj.seq;
					$(this).find("tr:gt(0)").removeClass("selected");
					$(this).find("tr:eq("+(obj.seq+1)+")").addClass("selected");
					break;
				case 40:
					if ((obj.seq<0)||(obj.seq>(obj.list.length-2))) obj.seq=-1;
					++obj.seq;
					$(this).find("tr").removeClass("selected");
					$(this).find("tr:eq("+(obj.seq+1)+")").addClass("selected");
					break;
				}
			});
		},
		
		setColWidth:function(opt){
			if (!opt) return;
			$(this).find("tr:eq(0) td:not(:last)").each(function(i){
				if (opt[i]) $(this).width(opt[i]+"px");
			});
		}
	});
})(jQuery);