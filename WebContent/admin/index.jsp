<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<head>
<title>网上商城管理中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/css/general.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {
   background-image: url("${pageContext.request.contextPath }/image/wallhaven-542768.jpg");
   background-size: cover;
}
</style>
</head>
<body>
<center><s:actionerror/></center>
<form method="post" action="${pageContext.request.contextPath }/adminUser_login.action" target="_parent" name='theForm' onsubmit="return validate()">
  <table cellspacing="0" cellpadding="0" style="margin-top: 100px" align="center">
  <tr>
    <td><img src="images/login.png" width="178" height="256" border="0" alt="SHOP" /></td>
    <td style="padding-left: 50px">
      <table>
      <tr>
        <td>管理员姓名：</td>
        <td><input type="text" name="username" /></td>
      </tr>
      <tr>
        <td>管理员密码：</td>
        <td><input type="password" name="password" /></td>
      </tr>
      <tr>
      	<td>&nbsp;</td>
      	<td><input type="submit"  value="进入后台管理系统" class="button"/></td>
      </tr>
      </table>
    </td>
  </tr>
  </table>
  <input type="hidden" name="act" value="signin" />
</form>

</body>