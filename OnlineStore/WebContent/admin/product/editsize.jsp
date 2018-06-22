<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery/jquery.datepick.css" type="text/css">
	</HEAD>
	<body>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/product_updateOrSaveSize.action" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>库存</strong>
						</TD>
					</tr>

				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
	
								<td align="center" width="10%">
									序号
								</td>
								<td align="center" width="17%">
									XL
								</td>
								<td align="center" width="17%">
									XXL
								</td>
								<td align="center" width="8%">
									S
								</td>
								<td align="center" width="12%">
									M
								</td>
							</tr>
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<%-- <input type="hidden" name="size.id" value="${size.id}"> --%>
									<input type="hidden" name="size.id" value="${pid}">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">
										${size.id}
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">
										<input type="text" value="${size.xlSize}" name="size.xlSize" width="20%">
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">
										<input type="text" value="${size.xxlSize}" name="size.xxlSize" width="20%">
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">
										<input type="text" value="${size.ssSize}" name="size.ssSize" width="20%">
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="12%">
										<input type="text" value="${size.mmSize}" name="size.mmSize" width="20%">
									</td>
								</tr>
								<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
									<td align="center" width="10%" colspan="5">
										<input type="submit" value="保存">
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>