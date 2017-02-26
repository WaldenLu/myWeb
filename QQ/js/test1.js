  /*封装一个根据className获得元素的方法来解决ie浏览器没有getElementsByClassName的问题*/
   function getByClass(clsName,parent){//第二个元素是可选的，若有 则传父元素下的子元素 若不传递默认为document
  var oParent=parent?document.getElementById(parent):document,
      eles=[],
      elements=oParent.getElementsByTagName('*');

  for(var i=0,l=elements.length;i<l;i++){
    if(elements[i].className==clsName){
      eles.push(elements[i]);
    }
  }
  return eles;
}

window.onload=drag;

function drag(){
  var oTitle = getByClass('login_logo_webqq','loginPanel')[0];//getByClass返回一个数组，取数组中的第一个元素。
  //拖拽
  oTitle.onmousedown = fnDown;
  //关闭
  var oClose = document.getElementById('ui_boxyClose');
  oClose.onclick = function(){
    document.getElementById('loginPanel').style.display = 'none';
  }
  //切换状态
  var loginState=document.getElementById('loginState'),
       stateList=document.getElementById('loginStatePanel'),
       lis=stateList.getElementsByTagName('li'),
       stateTxt=document.getElementById('login2qq_state_txt'),
       loginStateShow=document.getElementById('loginStateShow');

   loginState.onclick=function(e){
     e = e || window.event;
     if(e.stopPropagation){
          e.stopPropagation();
     }else{
          e.cancelBubble=true;
     }
     stateList.style.display='block';
   }
      //鼠标滑过、离开和点击状态列表时
 for(var i=0,l=lis.length;i<l;i++){
      lis[i].onmouseover=function(){
        this.style.background='#567';
      }
      lis[i].onmouseout=function(){
        this.style.background='#FFF';
      }
      lis[i].onclick=function(e){
        e = e || window.event;
        if(e.stopPropagation){
          e.stopPropagation();
        }else{
          e.cancelBubble=true;
        }// 这里要加这个条件是因为根据事件冒泡，点击这个li的时候，会逐级向上调用函数，最后还是会调用到前面设置的点击状态显示stateList的函数 第30行左右的代码 所以这里要设置一个阻止冒泡
        var id=this.id;
        stateList.style.display='none';
        stateTxt.innerHTML=getByClass('stateSelect_text',id)[0].innerHTML;
        loginStateShow.className='';
        loginStateShow.className='login-state-show '+id;
      }//这个部分由在线状态Icon和文字组成，所以是两个样式，两个样式之间用空格隔开。
   }
   document.onclick=function(){//如果上面for循环块不想执行，即不想选择状态，直接点击页面空白处使得面板隐藏注意这里也有一个事件冒泡document是最外层对象
      stateList.style.display='none';
   }
}

/*

function fnDown(){
  var oDrag = document.getElementById('loginPanel');
  document.onmousemove=function(evnet){
  //鼠标移动时重复触发这个事件
  evnet = evnet || window.event;
  //如下代码的含义是面板的距离左边和上边的距离等于光标的(X,Y)这个距离是相对于面板的左上角的那个点来说的 这样写的问题就是不管我按下光标时在面板的那个位置，面板都会让自己的左上角和鼠标对其，因此重写函数fnDown
  oDrag.style.left=event.clientX+'px';
  oDrag.style.top=event.clientY+'px';
  } 
}
*/
function fnDown(event){
  event = event || window.event;
  var oDrag=document.getElementById('loginPanel'),
      // 光标按下时光标和面板之间的距离
      disX=event.clientX-oDrag.offsetLeft,
      disY=event.clientY-oDrag.offsetTop;
  // 移动
  document.onmousemove=function(event){
    event = event || window.event;
    fnMove(event,disX,disY);
  }
  // 释放鼠标
  document.onmouseup=function(){
    document.onmousemove=null;
    document.onmouseup=null;
  }
}

function fnMove(e,posX,posY){
  var oDrag = document.getElementById('loginPanel')
  var l = e.clientX-posX,
      t = e.clientY-posY,
      winW = document.documentElement.clientWidth || document.body.clientWidth,
      winH = document.documentElement.clientHeight || document.body.clientHeight,
      maxW = winW - oDrag.offsetWidth - 10,//右侧关闭按钮溢出登录界面10px，如果这里不减10的话，结果就是向右侧拖动时，X按钮会有一部分到界面外。
      maxH = winH - oDrag.offsetHeight;

  if (l<0) {
    l=0;
  } else if(l > maxW){
    l=maxW;
  }

  if(t<0){//这里同理，防止关闭按钮‘X’溢出到界面外
    t=10;
  }else if(t > maxH){
    t = maxH;
  }
  oDrag.style.left = l+'px';
  oDrag.style.top = t+'px';
}