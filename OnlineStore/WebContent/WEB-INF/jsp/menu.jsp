<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="span24">
	<ul class="mainNav">
		<li>
			<a href="${pageContext.request.contextPath }/index.action">首页</a>
			|
		</li>
		<s:iterator var="c" value="#session.categorys">
			<li>
				<a href="${pageContext.request.contextPath }/product_findByCid?cid=${c.cid}&page=1"><s:property value="#c.cname" /></a>
				|
			</li>
		</s:iterator>
	</ul>
</div>