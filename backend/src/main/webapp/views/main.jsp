<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Professor'sTea starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="/static/js/jquery.js" type="text/javascript"></script>
    <script src="/static/js/prefixfree.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="/static/css/button.css" />
    <link rel="stylesheet" type="text/css" href="/static/css/demo.css" />
    <script type="text/javascript">
        $(function(){
        	$(".bt").css("opacity","1");       	        	
        });
    </script>

  </head>
  
  <body>
    <div class="container1">
        <br><br><br>
        <header>
            <h1>教授的下午茶 <span>管理后台</span></h1>
			<br>
        </header>
        <div id="box">
            <div id="welcome" style="font-style:italic;color:#404A54">欢迎您，${User }</div>
            <div class="bt">
                <div class="button" onclick="Link(0)"></div>
                <div class="button1" onclick="Link(1)"></div>
                <div class="button2" onclick="Link(2)"></div>
                <div class="button3" onclick="Link(3)"></div>
                <div class="button4" onclick="Link(4)"></div>
                <div style="clear:both"> </div> 
            </div>
        </div>
    </div>
    <script>
    	function Link(num){
    		switch(num){
    			case 0:location.href="/backend/video/listVideos";
    				break;
   				case 1:location.href="/backend/product/listTeas";
   					break;
				case 2:location.href="/backend/welfare/listWelfare";
					break;
				case 3:location.href="/backend/product/listOrders";
   					break;
				case 4:location.href="/backend/delivery/list";
					break;
				default:location.href="/backend/choose/main";
    		}
    		
    	}
    </script>               
  </body>
</html>
