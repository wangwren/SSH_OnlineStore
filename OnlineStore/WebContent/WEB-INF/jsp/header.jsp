<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="span5">
		<div class="logo">
			<a href="./京华亿家/index.htm">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/bzz.png" alt="笨张张"/>
			</a>
		</div>
	</div>
	<div class="span9">
		<div class="headerAd">
			<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
	</div>	
</div>
<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				<s:if test="#session.user != null">
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						<s:property value="#session.user.username"/>|
					</li>
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						<a href="${pageContext.request.contextPath }/order_findMyOrder.action">我的订单</a>|
					</li>
					<li id="headerRegister" class="headerRegister" style="display: list-item;">
						<a href="${pageContext.request.contextPath }/user_quit.action">注销</a>|
					</li>
				</s:if>
				<s:else>
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						<a href="${pageContext.request.contextPath }/user_loginPage.action">登录</a>|
					</li>
					<li id="headerRegister" class="headerRegister" style="display: list-item;">
						<a href="${pageContext.request.contextPath }/user_registPage.action">注册</a>|
					</li>
				</s:else>
				
				
				<li id="headerUsername" class="headerUsername"></li>
				<li id="headerLogout" class="headerLogout">
					<a>[退出]</a>|
				</li>
						<li>
							<a>会员中心</a>
							|
						</li>
						<li>
							<a>购物指南</a>
							|
						</li>
						<li>
							<a>关于我们</a>
							
						</li>
			</ul>
		</div>
		<div class="cart">
			<a  href="${pageContext.request.contextPath }/cart_myCart.action">购物车</a>
		</div>
			<div class="phone">
				客服热线:
				<strong>151 4021 ****</strong>
			</div>
	</div>