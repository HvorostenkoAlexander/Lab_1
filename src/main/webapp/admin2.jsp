<%--
  Created by IntelliJ IDEA.
  User: antas
  Date: 02.09.2016
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@page pageEncoding="UTF-8"%>
<jsp:useBean id="admin" class="model.Administrator.Administrator"></jsp:useBean>
<html>
<head>
    <title>Админка</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="resources/js/bootstrap.min.js"></script>
</head>
<body>
<table class="table table-striped table-hover" style="width:70px; float:left;margin-right: 50px;" border="1">
    <tr>
        <td>Пользователь</td>
    </tr>
    <c:forEach items="${users}" var="element" varStatus="ind">
        <tr onclick="document.getElementById('${ind.index}').submit();">
            <td style="cursor: pointer">
                <form id="${ind.index}" action="admin2.jsp">
                    <input type="hidden" name="id" value="${element.id}">
                </form>
                <a>${element.login}</a></td>
        </tr>
    </c:forEach>

</table>
<table class="table table-striped table-hover" style="width:500px" border="1" id="card">
</table>
<form action="admin4.jsp" method="post">
    <input type="text" name="type" style="float: left; margin-right: 5px; width: 97px">
    <input type="text" name="name" style="float: left; margin-right: 5px; width: 123px">
    <input type="text" name="bill" style="float: left; margin-right: 5px; width: 137px">
    <input type="hidden" name="idUser" value="${param.id}" >
    <input type="submit" value="Добавить">
</form>
<script>
        var table = document.getElementById("card");
        var values ='';
        <c:forEach items="${userCard}" var="ele">
        <c:if test="${ele.idUser == param.id}">
        <c:forEach items="${products}" var="element">
        <c:if test="${ele.idCard == element.id}">
        values += '<tr>';
        values += '<td>';
        values += '${element.typeCard}';
        values += '</td>';
        values += '<td>';
        values += '${element.name}';
        values += '</td>';
        values += '<td>';
        values += '${element.accaontCard}';
        values += '</td>';
        <c:if test="${element.statusCard}">
        values += '<td>';
        values += '<c:out value="Разблокирована"/>';
        values += '</td>';
        </c:if>
        <c:if test="${not element.statusCard}">
        values += '<td>';
        values += '<c:out value="Заблокирована"/>';
        values += '<form id="${ele.idCard}" action="admin3.jsp">';
        values += '<input type="hidden" name="idCard" value="${ele.idCard}">';
        values += '<button type="submit" class="btn btn-primary">Разблокировать</button>';
        values += '</form>';
        values += '</td>';
        </c:if>
        values += '</tr>';
        </c:if>
        </c:forEach>
        </c:if>
        </c:forEach>;
        var header = '<tr>'+
                '<td>Тип карты</td>'+
                '<td>Имя карты</td>'+
                '<td>Сумма на счете</td>'+
                '<td>Статус</td>' +
                '</tr>';
        table.innerHTML = header+values;
</script>
</body>
</html>

