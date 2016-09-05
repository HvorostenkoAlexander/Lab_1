<%--
  Created by IntelliJ IDEA.
  User: antas
  Date: 30.08.2016
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
<head>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="resources/js/bootstrap.min.js"></script>
</head>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-lg-4">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        Авторизация на сайте</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-13 login-box">
                            <form role="form" action="/login" method="post" id="form">
                                <div class="input-group">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                    <input type="text" class="form-control" name="login" placeholder="Имя пользователя" required autofocus />
                                </div>
                                <div class="input-group">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                    <input type="password" class="form-control" name="password" placeholder="Ваш пароль" required />
                                </div>

                <div class="panel-footer">
                    <div class="row">
                        <div class="col-xs-6 col-sm-6 col-md-6">
                            <div class="checkbox">
                                <label for="form">
                                    <input type="checkbox" value="Remember" name="remember">
                                    Запомнить меня
                                </label>
                            </div>
                        </div>
                        <div class="">
                            <button type="submit" class="btn btn-labeled btn-success">
                                <span class="btn-label"><i class="glyphicon glyphicon-ok"></i></span>Войти</button>
                            <!--<button type="button" class="btn btn-labeled btn-danger">
                                <span class="btn-label"><i class="glyphicon glyphicon-remove"></i></span>Выход</button>-->
                        </div>
                    </div>
                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div></body>
</html>

