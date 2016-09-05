<%--
  Created by IntelliJ IDEA.
  User: antas
  Date: 02.09.2016
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<jsp:useBean id="admin" class="model.Administrator.Administrator"></jsp:useBean>
<html>
<body>
${admin.UnlockCard(param.idCard)}
<form id="form" action="BuffServlet" method="post"></form>
<script>
    document.getElementById('form').submit();
</script>
</body>
</html>