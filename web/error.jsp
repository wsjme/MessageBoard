<%--
  Created by IntelliJ IDEA.
  User: wsj
  Date: 2017/7/5
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>[访问错误]</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
</head>
<body background="img/1.jpg" style="background-size:100% 100%;background-repeat:no-repeat;">
<script>
    //$.messager.alert('警告','警告消息');
    $.messager.confirm('访问错误！','您想要跳转到登录页面吗？',function(r){
        if (r){
            location.replace('${pageContext.request.contextPath}/login.jsp')
        }
    });
</script>
</body>
</html>
