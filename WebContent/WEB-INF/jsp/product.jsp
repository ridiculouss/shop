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
						<dl>
							<dt>
								<a href="${pageContext.request.contextPath}/蔬菜分类.htm">蔬菜</a>
							</dt>
									<dd>
										<a >无公害蔬菜</a>
									</dd>
									<dd>
										<a>特菜类</a>
									</dd>
									<dd>
										<a>有机蔬菜</a>
									</dd>
									<dd>
										<a>蔬菜套餐</a>
									</dd>
						</dl>
						<dl>
							<dt>
								<a>水果</a>
							</dt>
									<dd>
										<a>国产</a>
									</dd>
									<dd>
										<a>进口</a>
									</dd>
									
						</dl>
						<dl>
							<dt>
								<a >肉类</a>
							</dt>
									<dd>
										<a>猪肉</a>
									</dd>
									<dd>
										<a>牛羊肉</a>
									</dd>
									<dd>
										<a>家禽</a>
									</dd>
									<dd>
										<a>鱼</a>
									</dd>
						</dl>
						<dl>
							<dt>
								<a>蛋、奶及肉制品类</a>
							</dt>
									<dd>
										<a>蛋</a>
									</dd>
									<dd>
										<a>奶</a>
									</dd>
									<dd>
										<a>豆制品</a>
									</dd>
						</dl>
						<dl>
							<dt>
								<a >干果</a>
							</dt>
									<dd>
										<a>干制坚果</a>
									</dd>
									<dd>
										<a>干制果实/果肉</a>
									</dd>
									<dd>
										<a >干制种仁</a>
									</dd>
									<dd>
										<a>    </a>
									</dd>
						</dl>
						<dl>
							<dt>
								<a >古薯杂粮</a>
							</dt>
									<dd>
										<a >米类</a>
									</dd>
									<dd>
										<a>杂粮</a>
									</dd>
									<dd>
										<a>面粉</a>
									</dd>
									<dd>
										<a >薯类</a>
									</dd>
						</dl>
						<dl>
							<dt>
								<a>油</a>
							</dt>
									<dd>
										<a >茶油</a>
									</dd>
									<dd>
										<a >核桃油</a>
									</dd>
									<dd>
										<a >橄榄油</a>
									</dd>
									<dd>
										<a>芥花籽油</a>
									</dd>
						</dl>
						<dl>
							<dt>
								<a>水、软饮</a>
							</dt>
									<dd>
										<a >水</a>
									</dd>
									<dd>
										<a>软饮</a>
									</dd>
						</dl>
						<dl>
							<dt>
								<a >茶</a>
							</dt>
									<dd>
										<a >绿茶</a>
									</dd>
									<dd>
										<a>红茶</a>
									</dd>
									<dd>
										<a >乌龙茶</a>
									</dd>
									<dd>
										<a>白茶</a>
									</dd>
						</dl>
						<dl class="last">
							<dt>
								<a>亿家卡</a>
							</dt>
									<dd>
										<a >亿家卡</a>
									</dd>
									<dd>
										<a>    </a>
									</dd>
						</dl>
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
						<strong>￥：<s:property value="model.shopPrice" />元/份</strong>
							参 考 价：
							<del>￥<s:property value="model.marketPrice" />元/份</del>
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