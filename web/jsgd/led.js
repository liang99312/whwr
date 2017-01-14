	var ShowCount = 4;//显示数量
	var i;
	var str="";	
	var sWord = new Array();
	//显示内容，数量和上面设置的要吻合。
	sWord[0]="<a href=http://www.0517114.net/view-8213.html target=_blank>淮安泰安航空售票处市区免费送票上门，电话：14752337888</a>";
	sWord[1]="<a href=http://www.0517114.net/view-7730.html target=_blank>淮安金蟾蜍装饰公司打造品牌巨舰！电话：15195391863</a>";
	sWord[2]="<a href=http://www.0517114.net/view-8453.html target=_blank> 东风悦达起亚K2两厢上市有礼，电话：0517-83340018</a>";
	sWord[3]="<a href=http://www.0517114.net/view-8425.html target=_blank>凯延实业有限公司招聘技工,普工,操作工,电话：15952326976</a>";

	function mixArray(source)
	{
		var goal=[];
		for(var i=0;i<source.length;i++)
		{
			var pos=Math.floor(Math.random()*(source.length-i));
			goal[i]=source[pos];
			source[pos]=source[source.length-1-i];
		}
		return goal;
	}
	
	var Ro = new Array();
	for (var x=0;x<sWord.length ;x++ )
	{							   
		Ro[x]=x;	
	}
	Ro=mixArray(Ro);
	

	for (var j=0;j<ShowCount ; j++)
	{

		str += "<li><center>" + sWord[Ro[j]] + "</center></li>";
	}
	str="<ul id=led>" + str + "</ul>";
	document.getElementById("LEDAD").innerHTML=str;