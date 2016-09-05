<%--
  Created by IntelliJ IDEA.
  User: antas
  Date: 30.08.2016
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Список карт</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="resources/js/bootstrap.min.js"></script>
</head>
<body>

<c:if test="${ empty param.id}" >
    <form >

        <div class="row" style="width:600px; float:right; margin-right: 100px; margin-top: 30px;" border="1" >

            <div class="col-xs-6">
                <p class="form-control-static">Количество денег на карте (BYN)</p>
                <input type="text" class="form-control input-lg" placeholder="Баланс ">
            </div>



            <br />
            <br />




        </div>
        <br />
    </form>
    <%--<div class="row" style="width:600px; float:right; margin-right: 100px; margin-top: 30px;" border="1" >--%>
        <%--<form name="AddForm">--%>

            <%--<input   type="text"   class="form-control input-lg" placeholder="Введите сумму" >--%>



            <%--<input type="submit" class="btn btn-default navbar-btn" value="Пополнить счет">--%>
        <%--</form>--%>
        <%--<br />--%>
        <%--<form name="DelForm">--%>
            <%--<input type="text"   class="form-control input-lg" placeholder="Введите сумму" >--%>


            <%--<input type="submit" class="btn btn-default navbar-btn" value="Оплатить">--%>

        <%--</form>--%>
    <%--</div>--%>
</c:if>
<c:forEach items="${products}" var="element" varStatus="st">

    <c:if test="${element.id== param.id}" >
        <form >

            <div class="row" style="width:600px; float:right; margin-right: 100px; margin-top: 30px;" border="1" >

                <div class="col-xs-6">
                    <p class="form-control-static">Количество денег на карте (BYN)</p>
                    <input type="text"  name="balance" class="form-control input-lg" placeholder= ${element.accaontCard}>
                </div>




                <br />
                <br />






                <%--<button type="button" class="btn btn-default navbar-btn">Пополнить счет</button>--%>
                <%--<button type="button" class="btn btn-default navbar-btn">Оплатить</button>--%>


            </div>
            <br />
        </form>
     <div class="row" style="width:600px; float:right; margin-right: 100px; margin-top: 30px;" border="1" >
        <form name="sumAddForm" action="userBuffAdd.jsp" method="post">

            <input   type="text"  name="sumAdd" class="form-control input-lg" placeholder="Введите сумму" >

            <input type="hidden" name="idCard" value="${param.id}" >

            <input name="add" type="submit" class="btn btn-default navbar-btn" value="Пополнить счет">
        </form>
        <br />
        <form name="sumDelForm" action="userBuffDel.jsp" method="post">
            <input   type="text"  name="sumDel" class="form-control input-lg" placeholder="Введите сумму" >

            <input type="hidden" name="idCard" value="${param.id}" >
            <input name="del" type="submit" class="btn btn-default navbar-btn" value="Оплатить">

        </form>
    </div>
    </c:if>
</c:forEach>



<table class="table table-striped table-hover"  style="width:500px;  margin-left: 10px;" border="1">
    <tr>

        <td>Тип карты</td>
        <td>Имя карты</td>
        <td>Сумма на счете</td>
        <td>Статус</td>
    </tr>

    <c:forEach items="${products}" var="element" varStatus="st">
        <tr onclick="document.getElementById('${st.index}').submit();">
            <td style="cursor: pointer">
                <form id="${st.index}" action="users.jsp">
                    <input type="hidden" name="id" value="${element.id}">
                </form>
            ${products[st.index].typeCard}
            </td>
            <td>${products[st.index].name}</td>
            <td>${products[st.index].accaontCard}</td>


            <c:if test="${products[st.index].statusCard}">
                <td>
                   <c:out value="Разблокирована"/>

                  <form id="${st.index}" action="userBuffUnlock.jsp">
                       <input type="hidden" name="idCard" value="${element.id}">
                   <button type="submit" class="btn btn-primary">Заблокировать</button>
                   </form>

               </td>
            </c:if>

            <c:if test="${not products[st.index].statusCard}">
                <td><c:out value="Заблокирована"/></td>
            </c:if>
        </tr>
    </c:forEach>

</table>
</body>
</html>
