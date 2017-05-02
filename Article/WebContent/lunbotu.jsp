<!-- 测试轮播图 不在系统之内 -->
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.1/jquery.js"></script>
<title>Insert title here</title>
<style type="text/css">
* {
    padding: 0;
    margin: 0;
}

ul li {list-style: none;}

.banner {
    height: 380px;
   
    margin-top: 20px;
    position: relative;
    overflow: hidden;
}
.banner .content {
    width: 1060px;
    height: 450px;
    margin: 20px auto;
    position: relative;
    overflow: hidden;
}
.banner ul li img {
    width: 1060px ;
    height: 380px ;
}
.fl {float: left;}
.fr {float: right;}

.banner .content ul{
    width: 10000px;
    margin-left: -1060;
}

.btn_left, .btn_right{
	transition:all 2s ease 0.6s;/* transition:运动的样式 持续时间 运动形式 延迟时间 */
	opacity: 0;
	cursor: pointer;
    display: inline-block;
    width: 21px;
    height: 150px;
    background: url(picture/banner_tb.png) no-repeat;
    position: absolute;
    left: 0px;
    top: 90px;
}

.btn_right{
    background-position: -29px 0;/* 原图是一个两个方向的箭头 用来切割得到右箭头 */
    left: 1038px;
    top: 90px;
}

.banner .content:hover .btn_left{opacity:1;}
.banner .content:hover .btn_right{opacity:1;}

</style>


	

</head>
<body>

<script type="text/javascript">
$(document).ready(function(){ 
	
	var leftBtn = $(".btn_legt");
	var rightBtn =  $(".btn_right");
	var ul = $('.banner .content ul').eq(0);
	var imgwidth = $('.banner .content ul li').width();//获取轮播图片的宽度
	var len = $('.banner .content ul li').length; //获取总共的图片数量
	var index = 0;
	
	rightBtn.on('click',function(){
		index++;
		movePicture();
		});
	

	
	function movePicture(){
		$('.banner .content ul').animate({'margin-left':-imgwidth * (index)},1000,function(){
				if(index == len){
					$(this).css('margin-left',0);
					index = 0;
					}
				if(index == -1){
					$(this).css('margin-left',-imgwidth * len);
					index = len - 1;
					}
			});
		}
	
}) /* 这是为了防止文档在完全加载（就绪）之前运行 jQuery 代码 */

</script>

	<div class='banner'>
    	<div class='content'>
        	<ul>
  
            <li class='fl'>
                <a href="javascript:void(0)">
                     <img src="picture/2.jpg "/>
                </a>
            </li>
            <li class='fl'>
                <a href="javascript:void(0)">
                    <img src="picture/3.jpg "/>
                </a>
            </li>
            <li class='fl'>
                <a href="javascript:void(0)">
                    <img src="picture/4.jpg "/>
                </a>
            </li>
            <li class='fl'>
                <a href="javascript:void(0)">
                     <img src="picture/5.jpg "/>
                </a>
            </li>
        </ul>
        <i class='btn_left'></i>
		<i class='btn_right'></i>
    	</div>
    	
	</div>
	

</body>

</body>
</html>