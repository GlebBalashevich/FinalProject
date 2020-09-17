<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Home - Car|Book</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Cabin:700">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Login-Form-Dark.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Pretty-Registration-Form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/untitled.css">
</head>

<body id="page-top" style="background-color: rgb(56,55,57);">
<nav class="navbar navbar-light navbar-expand-md navbar navbar-expand-lg fixed-top" id="mainNav" style="padding: 0px;">
    <div class="container"><a class="navbar-brand shadow-none js-scroll-trigger"
                              href="process_controller?command=move_home_page" style="font-size: 59px;">Car|Book</a>
        <button data-toggle="collapse" class="navbar-toggler navbar-toggler-right" data-target="#navbarResponsive"
                type="button" aria-controls="navbarResponsive"
                aria-expanded="false" aria-label="Toggle navigation" value="Menu"><i class="fa fa-bars"></i></button>
        <div class="collapse navbar-collapse text-success" id="navbarResponsive" style="font-size: 19px;">
            <ul class="nav navbar-nav ml-auto">
                <li class="nav-item nav-link js-scroll-trigger" role="presentation"><a
                        class="nav-link js-scroll-trigger" href="process_controller?command=move_home_page">home</a>
                </li>
                <li class="nav-item nav-link js-scroll-trigger" role="presentation"><a
                        class="nav-link js-scroll-trigger" href="#cars">cars</a></li>
                <li class="nav-item nav-link js-scroll-trigger" role="presentation"><a
                        class="nav-link js-scroll-trigger" href="#download">download</a></li>
                <li class="nav-item nav-link js-scroll-trigger" role="presentation"><a
                        class="nav-link js-scroll-trigger" href="#default">default</a></li>
            </ul>
        </div>
    </div>
</nav>
<header class="masthead" style="background-image: url(/img/audi-a5-chernyj.png);">
    <div class="intro-body">
    <div class="row register-form" id="register_form">
    <div class="col-md-8 offset-md-2">
        <form action="process_controller" method="post" class="shadow-lg custom-form" id="register" style="background-color: rgba(0,0,0,0.08);">
            <div class="form-row form-group" style="margin: 0px -5px 15px;">
                <div class="col-sm-3 label-column"><label class="col-form-label">LOGIN</label></div>
                <div class="col-sm-6 input-column">
                    <input class="form-control" name="login" type="text"
                           autofocus required value="${registerParameters.get("login")}">
                </div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><label class="col-form-label">PASSWORD</label></div>
                <div class="col-sm-6 input-column">
                    <input class="form-control" name="password" type="password"
                           required>
                </div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><label class="col-form-label">CONFIRM PASSWORD</label></div>
                <div class="col-sm-6 input-column">
                    <input class="form-control" name="confirm_password" type="password"
                           required>
                </div>
            </div>
            <div class="form-row form-group">
                <div class="col label-column"><label class="col-form-label">FIRST NAME</label></div>
                <div class="col input-column">
                    <input class="form-control" name="first_name" type="text"
                           required value="${registerParameters.get("first_name")}">
                </div>
                <div class="col label-column"><label class="col-form-label label-column">SECOND NAME</label>
                </div>
                <div class="col input-column">
                    <input class="form-control" name="second_name" type="text"
                           required value="${registerParameters.get("second_name")}">
                </div>
            </div>
            <div class="form-row form-group">
                <div class="col label-column"><label class="col-form-label">DRIVER LICENSE</label></div>
                <div class="col input-column">
                    <input class="form-control" name="driver_license" type="text"
                           required value="${registerParameters.get("driver_license")}">
                </div>
                <div class="col label-column"><label class="col-form-label label-column">EMAIL</label></div>
                <div class="col input-column">
                    <input class="form-control" name="email" type="email"
                           required value="${registerParameters.get("email")}">
                </div>
            </div>
            <div class="form-row form-group">
                <div class="col label-column"><label class="col-form-label">PHONE NUMBER</label></div>
                <div class="col input-column">
                    <input class="form-control" name="phone_number" type="tel"
                           pattern="^\d{12}" value="${registerParameters.get("phone_number")}">
                </div>
                <div class="col label-column"><label class="col-form-label"></label></div>
                <div class="col"><label class="col-form-label"></label></div>
            </div>
            <input type="hidden" name="command" value="register_client">
            <button class="btn btn-light submit-button" type="submit">REGISTER</button>
        </form>
    </div>
    </div>
    </div>
</header>
<footer style="background-image: url(/img/footer.png);">
    <div class="container text-center">
        <p>Copyright ©&nbsp;Car|Book 2020</p>
    </div>
</footer>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath}/js/grayscale.js"></script>
</body>

</html>