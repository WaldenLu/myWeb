<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include  file="common/taglib.jsp"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script  src="${basePath}/static/js/jQuery.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/index.css"/>
<title>首页</title>

<script type="text/javascript">
$(document).ready(function(){ 
	
	var leftBtn = $(".btn_left");
	var rightBtn =  $(".btn_right");
	var ul = $('.banner .content ul').eq(0);
	var imgwidth = $('.banner .content ul li').width();//获取轮播图片的宽度
	var len = $('.banner .content ul li').length - 2; //获取总共的图片数量
	var index = 0;

	/* 节流 */
	var timer = null;
	
	rightBtn.on('click',function(){
		clearTimeout(timer);
		
		timer = setTimeout(function(){
		index++;
		movePicture();
		},500);
		
		});
	leftBtn.on('click',function(){
		clearTimeout(timer);
		timer = setTimeout(function(){
			index--;
			movePicture();
			},500);
	});
	function movePicture(){
		$('.banner .content ul').animate({'margin-left':-imgwidth * (index + 1)},1000,function(){
				
				if(index == len){
					$(this).css('margin-left',-imgwidth);
					index = 0;
					}
				if(index == -1){
					$(this).css('margin-left',-imgwidth * len);
					index = len - 1;
					}
			});
		}
	//定时轮播
	/* function autoMove(){
		
		
		}
	 */
	
	
}) /* 这是为了防止文档在完全加载（就绪）之前运行 jQuery 代码 */

</script>


</head>
<body>


	<div class="header">
		<div class='logo'>article</div>
		<ul>
			<li class='first'><a href="javascript:void(0)">首页</a></li>
			<li><a href="javascript:void(0)">原创故事</a></li>
			<li><a href="javascript:void(0)">热门专题</a></li>
			<li><a href="javascript:void(0)">美文欣赏</a></li>
			<li><a href="javascript:void(0)">小额赞助</a></li>
		</ul>
		
		<div class="login">
		<span><a href="login.jsp">登录</a></span>
		<span>|</span>
		<span><a href="javascript:void(0)">注册</a></span>
		</div>
	</div>
		
	<div class="banner">
			<div class='content'>
				<ul>
					<li class='fl'>
						<a href="javascript:void(0)">
							<img src="${basePath}/static/img/4.jpg">
						</a>
					</li>
					<li class='fl'>
						<a href="javascript:void(0)">
							<img src="${basePath}/static/img/1.jpg">
						</a>
					</li>
					<li class='fl'>
						<a href="javascript:void(0)">
							<img src="${basePath}/static/img/2.jpg">
						</a>
					</li>
					<li class='fl'>
						<a href="javascript:void(0)">
							<img src="${basePath}/static/img/3.jpg">
						</a>
					</li>
					<li class='fl'>
						<a href="javascript:void(0)">
							<img src="${basePath}/static/img/4.jpg">
						</a>
					</li>
					<li class='fl'>
						<a href="javascript:void(0)">
							<img src="${basePath}/static/img/1.jpg">
						</a>
					</li>
				
				</ul>
				<i class='btn_left'></i>
				<i class='btn_right'></i>
			</div>
	</div>
	
	
</body>
</html>