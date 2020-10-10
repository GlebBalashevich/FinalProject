<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.contentpage"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title><fmt:message key="title.cars"/></title>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200;0,300;0,400;0,600;0,700;0,800;0,900;1,200;1,300;1,400;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Login-Form-Dark.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Pretty-Registration-Form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body id="page-top">
<c:import url="${pageContext.request.contextPath}/jsp/fragment/header.jsp"/>
<header class="masthead">
    <div class="intro-body">
    <div class="row register-form">
        <div class="col-md-4 offset-1">
            <form action="process_controller" method="post" class="shadow-none custom-form" id="carsFilter" style="font-family: Nunito">
                <div class="form-row form-group">
                    <div class="col-sm-3 label-column">
                        <label class="col-form-label"><fmt:message key="cars.date_from"/></label></div>
                    <div class="col-sm-6 input-column">
                        <input class="form-control" id="dateFrom" type="datetime-local" required>
                    </div>
                </div>
                <div class="form-row form-group">
                    <div class="col-sm-3 label-column">
                        <label class="col-form-label"><fmt:message key="cars.date_to"/></label></div>
                    <div class="col-sm-6 input-column">
                        <input class="form-control" id="dateTo" type="datetime-local" required>
                    </div>
                </div>
                <div class="form-row form-group">
                    <div class="col-sm-6 offset-3 dropdown input-column">
                        <select class="form-control" name="car-select" id="car-select">
                            <option value=""><fmt:message key="cars.type"/></option>
                            <option value="img/vehicle1.jpg">VW Golf VII</option>
                            <option value="img/vehicle2.jpg">Audi A1 S-LINE</option>
                            <option value="img/vehicle3.jpg">Toyota Camry</option>
                            <option value="img/vehicle4.jpg">BMW 320 ModernLine</option>
                            <option value="img/vehicle5.jpg">Mercedes-Benz GLK</option>
                            <option value="img/vehicle6.jpg">VW Passat CC</option>
                        </select>
                    </div>
                </div>
                <div class="form-row form-group">
                    <div class="col-sm-4 offset-4 input-column">
                        <button class="submit-button" type="submit" id="butt">
                            <fmt:message key="cars.filter"/></button>
                    </div>
                </div>
                <input type="hidden" name="command" value="filter_cars">
            </form>
        </div>
        <div class="col-md-7 shadow-sm">
            <div class="form-row form-group">
                <div class="col-sm-3">
                    <img src="${pageContext.request.contextPath}/img/cars/5030mercedes.png"/>
                </div>
                <div class="col-sm-6">
                    <div class="row">
                        <div class="col">
                            <p>Mercedes GLS 2.0Turbo</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-3">
                            <p>SEDAN</p>
                        </div>
                        <div class="col-sm-3">
                            <p>5l/100km</p>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="row">
                        <p>100 $ per day</p>
                    </div>
                    <div class="form-row form-group">
                    <div class="row input-column" >
                        <button class="submit-button" type="submit" id="buttOrd">
                            ORDER</button>
                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</header>
<c:import url="${pageContext.request.contextPath}/jsp/fragment/footer.jsp"/>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath}/js/grayscale.js"></script>
<script src="${pageContext.request.contextPath}/js/moment.js"></script>
<script src="${pageContext.request.contextPath}/js/datetime.js"></script>
</body>
</html>
