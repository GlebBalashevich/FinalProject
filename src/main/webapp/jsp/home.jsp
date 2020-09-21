<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
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
</head>

<body id="page-top">
<nav class="navbar navbar-light navbar-expand-md navbar navbar-expand-lg fixed-top" id="mainNav">
    <div class="container"><a class="navbar-brand shadow-none js-scroll-trigger" href="process_controller?command=move_home_page">Car|Book</a><button data-toggle="collapse" class="navbar-toggler navbar-toggler-right" data-target="#navbarResponsive" type="button" aria-controls="navbarResponsive" aria-expanded="false"
                                                                                                              aria-label="Toggle navigation" value="Menu"><i class="fa fa-bars"></i></button>
        <div class="collapse navbar-collapse text-success" id="navbarResponsive">
            <ul class="nav navbar-nav ml-auto">
                <li class="nav-item nav-link js-scroll-trigger" role="presentation"><a class="nav-link js-scroll-trigger" href="process_controller?command=move_home_page">home</a></li>
                <li class="nav-item nav-link js-scroll-trigger" role="presentation"><a class="nav-link js-scroll-trigger" href="#cars">cars</a></li>
                <li class="nav-item nav-link js-scroll-trigger" role="presentation"><a class="nav-link js-scroll-trigger" href="#download">download</a></li>
                <li class="nav-item nav-link js-scroll-trigger" role="presentation"><a class="nav-link js-scroll-trigger" href="#default">default</a></li>
                <c:choose>
                    <c:when test="${sessionScope.user_role==null}">
                        <li class="nav-item nav-link js-scroll-trigger" role="presentation"><a class="nav-link active js-scroll-trigger" href="process_controller?command=move_login_page">Log in</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item nav-link js-scroll-trigger" role="presentation"><a class="nav-link active js-scroll-trigger" href="process_controller?command=log_out_user">Log out</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
<header class="masthead">
    <div class="shadow intro-body">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 mx-auto">
                    <h1 class="brand-heading"><strong>You rent more than a car.</strong></h1>
                    <p class="intro-text">We ready to help you 24/7. <br>Only new cars, only excellent service</p><a class="btn btn-link btn-circle" role="button" href="#carchoose"><i class="fa fa-angle-double-down animated"></i></a></div>
            </div>
        </div>
    </div>
</header>
<section id="carchoose" class="content-section text-center">
    <div class="container">
        <h1><strong>Let us find the perfect car for you</strong></h1>
        <div class="row register-form">
            <div class="col-md-8 offset-md-2">
                <form class="shadow-lg custom-form">
                    <div class="form-row form-group">
                        <div class="col-sm-4 label-column"><label class="col-form-label">DATE FROM</label></div>
                        <div class="col-sm-6 input-column"><input class="form-control" type="date"></div>
                    </div>
                    <div class="form-row form-group">
                        <div class="col-sm-4 label-column"><label class="col-form-label">DATE TO</label></div>
                        <div class="col-sm-6 input-column"><input class="form-control" type="date"></div>
                    </div>
                    <div class="form-row form-group">
                        <div class="col-sm-4 label-column"><label class="col-form-label" style="font-family: Cabin, sans-serif;">CAR TYPE</label></div>
                        <div class="col-sm-4 input-column">
                            <div class="dropdown"><button class="btn btn-light dropdown-toggle" data-toggle="dropdown" aria-expanded="false" type="button">Dropdown </button>
                                <div class="dropdown-menu" role="menu"><a class="dropdown-item" role="presentation" href="#">First Item</a><a class="dropdown-item" role="presentation" href="#">Second Item</a><a class="dropdown-item" role="presentation" href="#">Third Item</a></div>
                            </div>
                        </div>
                    </div><button class="btn btn-light submit-button" type="button">SEARCH A CAR</button></form>
            </div>
        </div>
    </div>
</section>
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
