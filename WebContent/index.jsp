<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function loadName(){
		var xmlHttp;
		if(window.XMLHttpRequest){
			xmlHttp=new XMLHttpRequest();
		}else{
			xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlHttp.onreadystatechange=function(){
			if(xmlHttp.readyState==4 && xmlHttp.status==200){
				document.getElementById("name").value=xmlHttp.responseText;
			}
		};
		// xmlHttp.open("get", "getAjaxName?name=jack&age=11", true);
		// xmlHttp.open("post", "getAjaxName?name=jack&age=11", true);
		// xmlHttp.send();
		
	    xmlHttp.open("post", "user_registPage", true);
	    xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	    xmlHttp.send("name="+document.getElementById("name").value+"&age="+document.getElementById("age").value);
	}
</script>
</head>
<body>
<input type="button" onclick="loadName()" value="Ajax获取数据"/>&nbsp;&nbsp;
<input type="text" id="name" name="name" />
<input type="text" id="age" name="age" />
</body>
</html>