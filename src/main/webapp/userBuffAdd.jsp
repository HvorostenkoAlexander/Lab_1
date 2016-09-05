
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 03.09.2016
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<jsp:useBean id="client" class="model.Client.Client"></jsp:useBean>
<html>
<head>
    <title>userBuff</title>
</head>
<body>


    ${client.FillAaccount(param.sumAdd,param.idCard)}
    <form id="form" action="BuffServlet" method="post"></form>
<script>
    document.getElementById('form').submit();
</script>

</body>
</html>
