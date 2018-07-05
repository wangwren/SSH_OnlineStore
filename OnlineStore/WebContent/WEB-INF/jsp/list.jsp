<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>商品</title>
	<meta name="author" content="Mango Team">
	<meta name="copyright" content="Mango">
	<meta name="keywords" content="蔬菜">
	<meta name="description" content="蔬菜">
	<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css">
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
						<!-- 一级分类 -->
							<a href="${pageContext.request.contextPath }/product_findByCid?cid=${ca.cid}&page=1" style="color:red">${ca.cname}</a>
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
			<!-- 给网站加个球 -->
			<script type="text/javascript" src="//ra.revolvermaps.com/0/0/6.js?i=0q0xm6cy62h&amp;m=7&amp;c=e63100&amp;cr1=ffffff&amp;f=arial&amp;l=0&amp;bv=90&amp;lx=-420&amp;ly=420&amp;hi=20&amp;he=7&amp;hc=a8ddff&amp;rs=80" async="async"></script>
		</div>
		<div class="span18 last">
			
			<form id="productForm" action="${pageContext.request.contextPath}/image/蔬菜 - Powered By Mango Team.htm" method="get">
				<input type="hidden" id="brandId" name="brandId" value="">
				<input type="hidden" id="promotionId" name="promotionId" value="">
				<input type="hidden" id="orderType" name="orderType" value="">
				<input type="hidden" id="pageNumber" name="pageNumber" value="1">
				<input type="hidden" id="pageSize" name="pageSize" value="20">
				
				<!-- 商品 -->
				<div id="result" class="result table clearfix">
					<s:iterator var="p" value="#request.pageList.list">
						<ul>
							<li>
								<a href="${pageContext.request.contextPath }/product_findById.action?pid=${p.pid}">
									<img src="${pageContext.request.contextPath }/${p.image}" width="170" height="170"  style="display: inline-block;">
									   
									<span style='color:green'>
									 ${p.pname}
									</span>
									 
									<span class="price">
										BZ价： ￥${p.shop_price}
									</span>
								</a>
							</li>
						</ul>
					</s:iterator>
				</div>
				
				<!-- 分页 -->
				<div class="pagination">
					第  <s:property value="#request.pageList.page"/>/<s:property value="#request.pageList.totalPage"/>页
					<s:if test="#request.pageList.page != 1">
						<a href="${ pageContext.request.contextPath }/product_findByCid.action?cid=${cid}&page=1" class="firstPage">&nbsp;</a>		
						<a href="${ pageContext.request.contextPath }/product_findByCid.action?cid=${cid}&page=${pageList.page-1}" class="previousPage">&nbsp;</a>	
					</s:if>	
					<s:iterator var="i" begin="1" end="#request.pageList.totalPage" step="1">
						<s:if test="#request.pageList.page==#i">
							<span class="currentPage"><s:property value="#i"/></span>
						</s:if>
						<s:else>
							<a href="${ pageContext.request.contextPath }/product_findByCid.action?cid=${cid }&page=<s:property value="#i"/>"><s:property value="#i"/></a>
						</s:else>
					</s:iterator>
						
					<s:if test="#request.pageList.page != #request.pageList.totalPage">
						<a class="nextPage" href="${ pageContext.request.contextPath }/product_findByCid.action?cid=${cid }&page=${pageList.page+1}">&nbsp;</a>
						<a class="lastPage" href="${ pageContext.request.contextPath }/product_findByCid.action?cid=${cid }&page=${pageList.totalPage }">&nbsp;</a>
					</s:if>
				</div>
			</form>
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
		<div class="copyright">Copyright © 2005-2013 BZZ商城 版权所有</div>
	</div>
</div>
</body></html>