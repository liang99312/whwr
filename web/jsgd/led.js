	var ShowCount = 4;//��ʾ����
	var i;
	var str="";	
	var sWord = new Array();
	//��ʾ���ݣ��������������õ�Ҫ�Ǻϡ�
	sWord[0]="<a href=http://www.0517114.net/view-8213.html target=_blank>����̩��������Ʊ�����������Ʊ���ţ��绰��14752337888</a>";
	sWord[1]="<a href=http://www.0517114.net/view-7730.html target=_blank>���������װ�ι�˾����Ʒ�ƾ޽����绰��15195391863</a>";
	sWord[2]="<a href=http://www.0517114.net/view-8453.html target=_blank> �����ô�����K2�����������񣬵绰��0517-83340018</a>";
	sWord[3]="<a href=http://www.0517114.net/view-8425.html target=_blank>����ʵҵ���޹�˾��Ƹ����,�չ�,������,�绰��15952326976</a>";

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