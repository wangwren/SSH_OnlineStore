<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>笨张张商城</title>
<link href="${pageContext.request.contextPath}/css/slider.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container header">
	<%@ include file="header.jsp" %>
	<%@ include file="menu.jsp" %>
</div>	

<div class="container index">
		<div class="span24">
			<div id="hotProduct" class="hotProduct clearfix">
					<div class="title">
						<strong>热门商品</strong>
						<!-- <a  target="_blank"></a> -->
					</div>
					<ul class="tab">
							<li class="current">
								<a href="./蔬菜分类.htm?tagIds=1" target="_blank"></a>
							</li>
							<li>
								<a  target="_blank"></a>
							</li>
							<li>
								<a target="_blank"></a>
							</li>
					</ul>
<!-- 					<div class="hotProductAd">
			<img src="${pageContext.request.contextPath}/image/a.jpg" width="260" height="343" alt="热门商品" title="热门商品">
</div> -->
						<ul class="tabContent" style="display: block;">
							<s:iterator var="p" value="#request.hotList">
								<li>
									<a target="_blank" href="${pageContext.request.contextPath }/product_findById.action?pid=${p.pid}"><img src="${pageContext.request.contextPath}/${p.image}" data-original="http://storage.shopxx.net/demo-image/3.0/201301/0ff130db-0a1b-4b8d-a918-ed9016317009-thumbnail.jpg" style="display: block;"></a>
								</li>
							</s:iterator>
						</ul>
						<ul class="tabContent" style="display: none;">
									<li>
										<a target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/c5b1b396-181a-4805-9e68-9b400d71f91e-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/c5b1b396-181a-4805-9e68-9b400d71f91e-thumbnail.jpg" style="display: block;"></a>
									</li>
									<li>
										<a  target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/4107e1ce-5e7c-4941-bc0f-718f35ba14cd-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/4107e1ce-5e7c-4941-bc0f-718f35ba14cd-thumbnail.jpg" style="display: block;"></a>
									</li>
									<li>
										<a  target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/6f8ae4bf-cbd3-41c7-aa22-0fe81db6add4-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/6f8ae4bf-cbd3-41c7-aa22-0fe81db6add4-thumbnail.jpg" style="display: block;"></a>
									</li>
									<li>
										<a target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/3d835c07-08c5-46d7-912d-adcd41f8c8e6-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/3d835c07-08c5-46d7-912d-adcd41f8c8e6-thumbnail.jpg" style="display: block;"></a>
									</li>
									<li>
										<a  target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/5e5be432-fbee-4bdd-a7bd-a92e01f9bfc4-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/5e5be432-fbee-4bdd-a7bd-a92e01f9bfc4-thumbnail.jpg" style="display: block;"></a>
									</li>
									<li>
										<a target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/cae1bc6b-0159-4ce0-9a9c-4926df231b4f-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/cae1bc6b-0159-4ce0-9a9c-4926df231b4f-thumbnail.jpg" style="display: block;"></a>
									</li>
									<li>
										<a  target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/40e34b2d-d240-446e-9874-89969edbe89f-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/40e34b2d-d240-446e-9874-89969edbe89f-thumbnail.jpg" style="display: block;"></a>
									</li>
									<li>
										<a target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/a8db4410-05e5-4dfa-8217-eb885a104af3-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/a8db4410-05e5-4dfa-8217-eb885a104af3-thumbnail.jpg" style="display: block;"></a>
									</li>
						</ul>
						<ul class="tabContent" style="display: none;">
									<li>
										<a target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/9f164e13-bcaa-48a6-9b35-0ca96629f614-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/9f164e13-bcaa-48a6-9b35-0ca96629f614-thumbnail.jpg" style="display: block;"></a>
									</li>
									<li>
										<a target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/c41d0347-364c-42bb-baeb-25142c1ed167-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/c41d0347-364c-42bb-baeb-25142c1ed167-thumbnail.jpg" style="display: block;"></a>
									</li>
									<li>
										<a target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/2af8be8a-75b9-41ae-b009-a7c54b685a4e-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/2af8be8a-75b9-41ae-b009-a7c54b685a4e-thumbnail.jpg" style="display: block;"></a>
									</li>
									<li>
										<a  target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/1a3ad7de-7ee9-4530-b89a-46375219beb5-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/1a3ad7de-7ee9-4530-b89a-46375219beb5-thumbnail.jpg" style="display: block;"></a>
									</li>
									<li>
										<a target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/ea566af4-0cdb-4017-a8c7-27e407794204-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/ea566af4-0cdb-4017-a8c7-27e407794204-thumbnail.jpg" style="display: block;"></a>
									</li>
									<li>
										<a  target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/dea31d42-fa3e-4b69-a631-51ca7c79f032-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/dea31d42-fa3e-4b69-a631-51ca7c79f032-thumbnail.jpg" style="display: block;"></a>
									</li>
									<li>
										<a  target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/1c81f492-a3d7-4c06-8658-bc2c76808cd3-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/1c81f492-a3d7-4c06-8658-bc2c76808cd3-thumbnail.jpg" style="display: block;"></a>
									</li>
									<li>
										<a target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/f1174ca6-6bdf-4d0b-86e6-5455bc8e89ad-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/f1174ca6-6bdf-4d0b-86e6-5455bc8e89ad-thumbnail.jpg" style="display: block;"></a>
									</li>
						</ul>
			</div>
		</div>
		<div class="span24">
			<div id="newProduct" class="newProduct clearfix">
					<div class="title">
						<strong>最新商品</strong>
						<a  target="_blank"></a>
					</div>
					<ul class="tab">
							<li class="current">
								<a href="./蔬菜分类.htm?tagIds=2" target="_blank"></a>
							</li>
							<li>
								<a  target="_blank"></a>
							</li>
							<li>
								<a target="_blank"></a>
							</li>
					</ul>
<!-- 					<div class="newProductAd">
									<img src="${pageContext.request.contextPath}/image/q.jpg" width="260" height="343" alt="最新商品" title="最新商品">
						</div>
						 -->						
						 <ul class="tabContent" style="display: block;">
						 	<s:iterator var="n" value="#request.newList">
								<li>
									<a href="${pageContext.request.contextPath }/product_findById.action?pid=${n.pid}" target="_blank"><img src="${pageContext.request.contextPath}/${n.image}" data-original="http://storage.shopxx.net/demo-image/3.0/201301/4a51167a-89d5-4710-aca2-7c76edc355b8-thumbnail.jpg" style="display: block;"></a>
								</li>
							</s:iterator>
						</ul>
						<ul class="tabContent" style="display: none;">
									<li>
										<a  target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/9f164e13-bcaa-48a6-9b35-0ca96629f614-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/9f164e13-bcaa-48a6-9b35-0ca96629f614-thumbnail.jpg" style="display: block;"></a>
									</li>
									<li>
										<a target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/1a3ad7de-7ee9-4530-b89a-46375219beb5-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/1a3ad7de-7ee9-4530-b89a-46375219beb5-thumbnail.jpg" style="display: block;"></a>
									</li>
									<li>
										<a target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/7acae4ac-5909-4142-8b20-19c5462859d6-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/7acae4ac-5909-4142-8b20-19c5462859d6-thumbnail.jpg" style="display: block;"></a>
									</li>
									<li>
										<a  target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/ea566af4-0cdb-4017-a8c7-27e407794204-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/ea566af4-0cdb-4017-a8c7-27e407794204-thumbnail.jpg" style="display: block;"></a>
									</li>
									<li>
										<a  target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/dea31d42-fa3e-4b69-a631-51ca7c79f032-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/dea31d42-fa3e-4b69-a631-51ca7c79f032-thumbnail.jpg" style="display: block;"></a>
									</li>
									<li>
										<a  target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/1c81f492-a3d7-4c06-8658-bc2c76808cd3-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/1c81f492-a3d7-4c06-8658-bc2c76808cd3-thumbnail.jpg" style="display: block;"></a>
									</li>
									<li>
										<a  target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/f1174ca6-6bdf-4d0b-86e6-5455bc8e89ad-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/f1174ca6-6bdf-4d0b-86e6-5455bc8e89ad-thumbnail.jpg" style="display: block;"></a>
									</li>
									<li>
										<a  target="_blank"><img src="./Mango商城 - Powered By Mango Team_files/2971c96e-9f11-4491-9faf-9ea7e1fec53c-thumbnail.jpg" data-original="http://storage.shopxx.net/demo-image/3.0/201301/2971c96e-9f11-4491-9faf-9ea7e1fec53c-thumbnail.jpg" style="display: block;"></a>
									</li>
						</ul>
			</div>
		</div>
		<div class="span24">
			<div class="friendLink">
				<dl>
					<dt>新手指南</dt>
							<dd>
								<a  target="_blank">支付方式</a>
								|
							</dd>
							<dd>
								<a  target="_blank">配送方式</a>
								|
							</dd>
							<dd>
								<a  target="_blank">售后服务</a>
								|
							</dd>
							<dd>
								<a  target="_blank">购物帮助</a>
								|
							</dd>
							<dd>
								<a  target="_blank">蔬菜卡</a>
								|
							</dd>
							<dd>
								<a  target="_blank">礼品卡</a>
								|
							</dd>
							<dd>
								<a target="_blank">银联卡</a>
								|
							</dd>
							<dd>
								<a  target="_blank">亿家卡</a>
								|
							</dd>
							
					<dd class="more">
						<a >更多</a>
					</dd>
				</dl>
			</div>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<script type="text/javascript" src="//ra.revolvermaps.com/0/0/6.js?i=0q0xm6cy62h&amp;m=7&amp;c=e63100&amp;cr1=ffffff&amp;f=arial&amp;l=0&amp;bv=90&amp;lx=-420&amp;ly=420&amp;hi=20&amp;he=7&amp;hc=a8ddff&amp;rs=80" async="async"></script>
					</li>
					<li>
						<a>关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a>招贤纳士</a>
						|
					</li>
					<li>
						<a>法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a>服务声明</a>
						|
					</li>
					<li>
						<a>广告声明</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2013 Mango商城 版权所有</div>
	</div>
</div>
</body></html>