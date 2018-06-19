<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<title>笨张张商品详情</title>
	<meta name="author" content="Mango Team">
	<meta name="copyright" content="Mango">
		<meta name="keywords" content="京华亿家--大冬瓜">
		<meta name="description" content="京华亿家--大冬瓜">
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/zzsc.css">

<script type="text/javascript" class="library" src="${pageContext.request.contextPath}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" class="library" src="${pageContext.request.contextPath}/js/jquery.colorbox-min.js"></script>
<script type="text/javascript" class="library" src="${pageContext.request.contextPath}/js/zzsc.js"></script>
</head>
<body>
<script type="text/javascript">
	function getSize(){
		
		//alert("aaaaa");
		var sizeValue = null;
		var size = document.getElementsByName("size");
		for(var i = 0;i < size.length;i++){
			if(size[i].checked == true){
				sizeValue = size[i].value;
				break;
			}
		}
		//alert(sizeValue);
		
		var url = "${pageContext.request.contextPath }/product_findSize.action?pid=${model.pid}";
		var param = {"sizeValue" : sizeValue};
		$.post(url,param,function(data){
			if(data == "null" || data == 0){
				
				$("#spanID").html("<font color='red'>(库存不足！)</font>");
				
			}else if(data > 0 || data!= "null"){
				
				$("#spanID").html("<font>(库存数量"+data+")</font>");
				document.getElementById("addCart").disabled = false;
			}
				
		});
		
		
		//选中尺寸后，提交按钮才可以点击
		//document.getElementById("addCart").disabled = false;
	}
</script>
<form action="${pageContext.request.contextPath }/cart_addCart.action" method="post">
<!-- 隐藏字段，保存商品的id -->
<input type="hidden" name="pid" value="${model.pid }"/>
<div class="container header">
	<%@ include file="header.jsp" %>
	<%@ include file="menu.jsp" %>
</div>

<div class="container productContent">
		<div class="span6">
			<div class="hotProductCategory">
				<s:iterator var="ca" value="#request.caList">
				<s:if test="#request.cid==#ca.cid">
					<dl>
						<dt>
						<!-- 一级分类 -->
							<a href="${pageContext.request.contextPath }/product_findByCid?cid=${ca.cid}&page=1">${ca.cname}</a>
						</dt>
						<!-- 二级分类 -->
						<s:iterator var="cas" value="#ca.categorySeconds">
							<dd>
								<a href="${pageContext.request.contextPath }/product_findByCsid?csid=${cas.csid}&page=1">${cas.csname}</a>
							</dd>
						</s:iterator>
					</dl>
				</s:if>
				<s:else>
					<dl>
						<dt>
							<a href="${pageContext.request.contextPath }/product_findByCid?cid=${ca.cid}&page=1">${ca.cname}</a>
						</dt>
						<!-- 二级分类 -->
						<s:iterator var="cas" value="#ca.categorySeconds">
							<dd>
								<a href="${pageContext.request.contextPath }/product_findByCsid?csid=${cas.csid}&page=1">${cas.csname}</a>
							</dd>
						</s:iterator>
					</dl>
				</s:else>
			</s:iterator>
			</div>
			

		</div>
		<div class="span18 last">
			
			<div class="productImage">
					<a title="" style="outline-style: none; text-decoration: none;" id="zoom" href="http://image/r___________renleipic_01/bigPic1ea8f1c9-8b8e-4262-8ca9-690912434692.jpg" rel="gallery">
						<div class="zoomPad">
						
							<div class="con-FangDa" id="fangdajing">
							  <div class="con-fangDaIMg">
							  	<!-- 正常显示的图片-->
							  	<img src="${pageContext.request.contextPath }/${model.image}">
							    <!-- 滑块-->  
							    <div class="magnifyingBegin"></div>
							    <!-- 放大镜显示的图片 -->
							    <div class="magnifyingShow"><img src="${pageContext.request.contextPath }/${model.image}"></div>
							  </div>
							</div>
						
							<%-- <img style="opacity: 1;" title="" class="medium" src="${pageContext.request.contextPath }/${model.image}"> --%>
							<div style="display: block; top: 0px; left: 162px; width: 0px; height: 0px; position: absolute; border-width: 1px;" class="zoomPup"></div>
							<div style="position: absolute; z-index: 5001; left: 312px; top: 0px; display: block;" class="zoomWindow">
								<div style="width: 368px;" class="zoomWrapper"><div style="width: 100%; position: absolute; display: none;" class="zoomWrapperTitle"></div>
									<div style="width: 0%; height: 0px;" class="zoomWrapperImage"><img src="%E5%B0%9A%E9%83%BD%E6%AF%94%E6%8B%89%E5%A5%B3%E8%A3%852013%E5%A4%8F%E8%A3%85%E6%96%B0%E6%AC%BE%E8%95%BE%E4%B8%9D%E8%BF%9E%E8%A1%A3%E8%A3%99%20%E9%9F%A9%E7%89%88%E4%BF%AE%E8%BA%AB%E9%9B%AA%E7%BA%BA%E6%89%93%E5%BA%95%E8%A3%99%E5%AD%90%20%E6%98%A5%E6%AC%BE%20-%20Powered%20By%20Mango%20Team_files/6d53c211-2325-41ed-8696-d8fbceb1c199-large.jpg" style="position: absolute; border: 0px none; display: block; left: -432px; top: 0px;"></div>
								</div>
							</div>
						<div style="visibility: hidden; top: 129.5px; left: 106px; position: absolute;" class="zoomPreload">Loading zoom</div>
						</div>
					</a>
				
			</div>
			<div class="name">${model.pname}</div>
			<div class="sn">
				<div>编号：${model.pid}</div>
			</div>
			<div class="info">
				<dl>
					<dt>BZ价:</dt>
					<dd>
						<strong>￥：${model.shop_price }元</strong>
							参 考 价：
							<del>￥${model.market_price}元</del>
					</dd>
				</dl>
					<dl>
						<dt>促销:</dt>
						<dd>
								<a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)">限时抢购</a>
						</dd>
					</dl>
					<dl>
						<dt>    </dt>
						<dd>
							<span>    </span>
						</dd>
					</dl>
			</div>
				<div class="action">
						<s:if test="#request.cid==1">
							<dt>尺寸:</dt>
							<dd>
								<input type="radio" name="size" value="xl" onclick="getSize()" />XL&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="size" value="xxl" onclick="getSize()" />XXL&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="size" value="s" onclick="getSize()" />S&nbsp;&nbsp;&nbsp;&nbsp;
							</dd>
						</s:if>
						
						<dl class="quantity">
							
							<dt>购买数量:</dt>
							<dd>
								<input id="quantity" name="count" value="1" maxlength="4" onpaste="return false;" type="text">
								<%-- <div>
									<span id="increase" class="increase">&nbsp;</span>
									<span id="decrease" class="decrease">&nbsp;</span>
								</div> --%>
							</dd>
							<dd>
								件
							</dd>
						</dl>
					<div class="buy">
						<s:if test="#request.cid!=1">
							<input id="addCart1" class="addCart" value="加入购物车" type="submit">
						</s:if>
						<s:else>
							<input id="addCart" class="addCart" value="加入购物车" type="submit" disabled="disabled">
							<span id="spanID"></span>
						</s:else>
					</div>
				</div>
			<div id="bar" class="bar">
				<ul>
						<li id="introductionTab">
							<a href="#introduction">商品介绍</a>
						</li>
						
				</ul>
			</div>
				
				<div id="introduction" name="introduction" class="introduction">
					<div class="title">
						<strong>商品介绍</strong>
					</div>
					<div>
						${model.pdesc}
					</div>
				</div>
				
				
				
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
</div>
</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a href="#">关于我们</a>
						|
					</li>
					<li>
						<a href="#">联系我们</a>
						|
					</li>
					<li>
						<a href="#">诚聘英才</a>
						|
					</li>
					<li>
						<a href="#">法律声明</a>
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
						<a >SHOP++官网</a>
						|
					</li>
					<li>
						<a>SHOP++论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2013 Mango商城 版权所有</div>
	</div>
</div>
</form>
</body>
</html>