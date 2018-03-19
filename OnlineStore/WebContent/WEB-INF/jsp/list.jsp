<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>蔬菜 - Powered By Mango Team</title>
	<meta name="author" content="Mango Team">
	<meta name="copyright" content="Mango">
	<meta name="keywords" content="蔬菜">
	<meta name="description" content="蔬菜">
	<link href="./css/common.css" rel="stylesheet" type="text/css">
	<link href="./css/product.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container header">
	
	<%@ include file="header.jsp" %>
	<%@ include file="menu.jsp" %>
	
</div>	
<div class="container productList">
		<div class="span6">
			<div class="hotProductCategory">
			<s:iterator var="ca" value="#request.caList">
				<s:if test="#request.cid==#ca.cid">
					<dl>
						<dt>
							<a href="${pageContext.request.contextPath }/product_findByCid?cid=${ca.cid}&page=1" style="color:red">${ca.cname}</a>
						</dt>
						<s:iterator var="cas" value="#ca.categorySeconds">
							<dd>
								<a>${cas.csname}</a>
							</dd>
						</s:iterator>
					</dl>
				</s:if>
				<s:else>
					<dl>
						<dt>
							<a href="${pageContext.request.contextPath }/product_findByCid?cid=${ca.cid}&page=1">${ca.cname}</a>
						</dt>
						<s:iterator var="cas" value="#ca.categorySeconds">
							<dd>
								<a >${cas.csname}</a>
							</dd>
						</s:iterator>
					</dl>
				</s:else>
			</s:iterator>
			</div>
		</div>
		<div class="span18 last">
			
			<form id="productForm" action="./image/蔬菜 - Powered By Mango Team.htm" method="get">
				<input type="hidden" id="brandId" name="brandId" value="">
				<input type="hidden" id="promotionId" name="promotionId" value="">
				<input type="hidden" id="orderType" name="orderType" value="">
				<input type="hidden" id="pageNumber" name="pageNumber" value="1">
				<input type="hidden" id="pageSize" name="pageSize" value="20">
					
				<div id="result" class="result table clearfix">
						<ul>
							<li>
								<a href="./京华亿家分页面.htm">
									<img src="./image/4a51167a-89d5-4710-aca2-7c76edc355b8-thumbnail.jpg" width="170" height="170"  style="display: inline-block;">
									   
									<span style='color:green'>
									 大冬瓜
									</span>
									 
									<span class="price">
										亿家价： ￥4.78/份
									</span>
								</a>
							</li>
						</ul>
				</div>
	<div class="pagination">
			<span class="firstPage">&nbsp;</span>
			<span class="previousPage">&nbsp;</span>
				<span class="currentPage">1</span>
				<a href="javascript: $.pageSkip(2);">2</a>
			<a class="nextPage" href="javascript: $.pageSkip(2);">&nbsp;</a>
			
			<a class="lastPage" href="javascript: $.pageSkip(2);">&nbsp;</a>
	</div>
			</form>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="./image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a >关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a >诚聘英才</a>
						|
					</li>
					<li>
						<a >法律声明</a>
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
						<a  target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >SHOP++官网</a>
						|
					</li>
					<li>
						<a >SHOP++论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2013 Mango商城 版权所有</div>
	</div>
</div>
</body></html>