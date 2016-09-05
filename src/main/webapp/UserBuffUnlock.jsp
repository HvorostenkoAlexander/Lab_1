<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.09.2016
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@page pageEncoding="UTF-8"%>
<jsp:useBean id="client" class="model.Client.Client"></jsp:useBean>
<fmt:requestEncoding value="UTF-8" />
<html>
<head>
    <title>userBuffUnlock</title>
</head>
<body>
${client. BlockAaccount(param.idCard)}
<form id="form" action="BuffServlet" method="post"></form>
<script>
    document.getElementById('form').submit();
</script>
</body>
</html>
