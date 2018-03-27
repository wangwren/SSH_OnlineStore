<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>订单页面</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="container header">
	<%@ include file="header.jsp" %>
	<%@ include file="menu.jsp" %>
</div>
<div class="container cart">

		<div class="span24">
		
			<div class="step step1">
				<ul>
					
					<li class="current"></li>
					<li style="color: red">以下是您的订单列表</li>
				</ul>
			</div>
	
		
				<table>
					<tbody>
						<s:iterator var="order" value="#request.orders">
							<tr>
								<th colspan="2">
									订单号：${order.oid}
								</th>   
								<th colspan="2">
									金额：<font color="red">${order.total }</font>
								</th>
								<th>
									状态：<s:if test="#order.state==0">
											<a href="${pageContext.request.contextPath }/order_findByOid?oid=${order.oid}"><font color="red">未付款</font></a>
										</s:if>
										<s:elseif test="#order.state==1">
											已付款
										</s:elseif>
										<s:elseif test="#order.state==2">
											<a href="#"><font color="red">确认收货</font></a>
										</s:elseif>
										<s:elseif test="#order.state==1">
											已收货
										</s:elseif>
								</th>
							</tr>
						<tr>
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
						</tr>
						<s:iterator var="item" value="#order.orderItems">
							<tr>
								<td width="60">
									<input type="hidden" name="pid" value="${item.product.pid }"/>
									<img src="${pageContext.request.contextPath }/${item.product.image}"/>
								</td>
								<td>
									<a target="_blank">${item.product.pname}</a>
								</td>
								<td>
									${item.product.shop_price}/元
								</td>
								<td class="quantity" width="60">
									${item.count }
									
								</td>
								<td width="140">
									<span class="subtotal">￥${item.subtotal }</span>
								</td>
							</tr>
						</s:iterator>
					</s:iterator>
				</tbody>
			</table>
				
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
</body>
</html>