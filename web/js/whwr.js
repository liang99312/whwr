function parseTime(timeStr){
	var year,month,day,hour,minute;
	year = timeStr.substring(0,4);
	month = timeStr.substring(5,7);
	day = timeStr.substring(8,10);
	hour = timeStr.substring(11,13);
	minute = timeStr.substring(14,16);
	second = timeStr.substring(17,19);
	return new Date(year,month,day,hour,minute,second);
}

function isInteger(str) { 
	//如果为空，则不是 
	if(str == "") 
		return false; 
	if(/^(\-?)(\d+)$/.test(str)) 
		return true; 
	else 
		return false; 
} 

function failureResp(doc){
	if (confirm('操作失败，您要查看错误信息吗？')){ alert(doc); }
}

function checkResult(json){
	switch (json.result){
	case -1:
		alert(json.msg);
		return false;
	case -99:
		logout();
		return false;
	default:
		return true;
	}
}

function logout(){
	alert("您长时间没有操作，请重新登录！");
	if (parent.webObject==null)
		parent.location.href = '/login.jsp';
	else
		parent.webObject.close();
}

$.extend({
	dfAjax:function(opt){
		if (!opt||!opt.url||!opt.data||!opt.fun) return alert("提交数据不正确！");
		var j={"jsonObj":JSON.stringify(opt.data)};
		$.ajax({
			url: opt.url,
			data: j,
			dataType: "json",
			type: "post",
			cache: false,
			error: function(msg, textStatus) { failureResp(msg.responseText); },
			success: function(json) { if (checkResult(json)) { opt.fun(json); } }
		});
	}
});
