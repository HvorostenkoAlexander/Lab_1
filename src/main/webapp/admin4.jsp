<%--
  Created by IntelliJ IDEA.
  User: antas
  Date: 02.09.2016
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@page pageEncoding="UTF-8"%>
<jsp:useBean id="admin" class="model.Administrator.Administrator"></jsp:useBean>
<fmt:requestEncoding value="UTF-8" />
<html>
<head>
    <title>Админка</title>
</head>
<body>
${admin.AddCard(param.idUser,param.name,param.bill,true,param.type)}
<form id="form" action="BuffServlet" method="post"></form>
<script>
    document.getElementById('form').submit();
</script>
</body>
</html>
