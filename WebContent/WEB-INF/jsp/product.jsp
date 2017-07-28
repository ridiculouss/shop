<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>网上商城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css" />


</head>
<body>

<%@ include file="menu.jsp" %>

<div class="container productContent">
		<div class="span6">
			<div class="hotProductCategory">
				<s:iterator value="#session.cList" var="c">
					<dl>
						<dt>
							<a href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="#c.c_id" />&page=1"><s:property value="#c.c_name" /></a>
						</dt>
						<s:iterator var="cs" value="#c.categorySeconds">
							<dd>
								<a href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="#cs.cs_id"/>&page=1"><s:property value="#cs.cs_name" /></a>
							</dd>
						</s:iterator>	
					</dl>
				</s:iterator>
			</div>
		</div>
		<div class="span18 last">
			<div class="productImage">	
				<img style="opacity: 1;" title="" class="medium" src="${pageContext.request.contextPath }/<s:property value="model.image" />" />			
			</div>
			<div class="name"><s:property value="model.name" /></div>
			<div class="sn">
				<div>编号：<s:property value="model.id" /></div>
			</div>
			<div class="info">
				<dl>
					<dt>商城价:</dt>
					<dd>
						<strong>￥：<s:property value="model.shopPrice" />&nbsp;元</strong>
							参 考 价：
							<del>￥<s:property value="model.marketPrice" />元</del>
					</dd>
				</dl>
					<dl>
						<dt>促销:</dt>
						<dd>
							<a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)">限时抢购</a>
						</dd>
					</dl>
			</div>
				<div class="action">
					
						<dl class="quantity">
							<dt>购买数量:</dt>
							<dd>
								<input id="quantity" name="quantity" value="1" maxlength="4" onpaste="return false;" type="text" />
							</dd>
							<dd>
								件
							</dd>
						</dl>
					<div class="buy">
							<input id="addCart" class="addCart" value="加入购物车" type="button" />
				
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
						<strong><s:property value="model.description" /></strong>
					</div>
					<div>
						<img src="${pageContext.request.contextPath }/<s:property value="model.image" />" />
					</div>
				</div>
				
				
				
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950" />
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
		<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>