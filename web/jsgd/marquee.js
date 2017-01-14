window.onload = function() {
  dMarquee('led');
}
function dMarquee(id){
  var speed=10; //速度
  var stop=3000; //停止时间 
  var ul = document.getElementById(id);
  var rows=ul.getElementsByTagName('li').length;
  var height = ul.getElementsByTagName('li')[0].offsetHeight;
  ul.innerHTML += ul.innerHTML;
  var play = function() {
    ul.scrollTop++;
    if(ul.scrollTop==rows*height){
    ul.scrollTop=0;
  }
  if(ul.scrollTop%height==0) {
    setTimeout(play,stop);
  }else{
    setTimeout(play,speed);
  } 
}
 setTimeout(play,stop);
}