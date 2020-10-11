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
    <link rel="stylesheet" href="https://bootstraptema.ru/plugins/2018/irs/ion.rangeSlider.css" />
    <link rel="stylesheet" href="https://bootstraptema.ru/plugins/2018/irs/ion.rangeSlider.skin.css" />
</head>

<body id="page-top">
<c:import url="${pageContext.request.contextPath}/jsp/fragment/header.jsp"/>
<header class="masthead">
    <div class="intro-body">
    <div class="row register-form" id="carPage">
        <div class="col-4 offset-1">
            <form action="process_controller" method="post" class="shadow-none custom-form" id="carsFilter" style="font-family: Nunito">
                <div class="form-row form-group">
                    <div class="col-4 label-column">
                        <label class="col-form-label"><fmt:message key="cars.date_from"/></label></div>
                    <div class="col-7 input-column">
                        <input class="form-control" name="date_from" id="dateFrom" type="datetime-local"
                               value="${carParameters.get("date_from")}" required>
                    </div>
                </div>
                <div class="form-row form-group">
                    <div class="col-4 label-column">
                        <label class="col-form-label"><fmt:message key="cars.date_to"/></label></div>
                    <div class="col-7 input-column">
                        <input class="form-control" name="date_to" id="dateTo" type="datetime-local"
                               value="${carParameters.get("date_to")}" required>
                    </div>
                </div>
                <div class="form-row form-group">
                    <div class="col-4 label-column">
                        <label class="col-form-label"><fmt:message key="cars.type"/></label></div>
                    <div class="col-7 dropdown input-column">
                        <select class="form-control" name="car_type" id="car-select">
                            <option value=""><fmt:message key="cars.car_type.any"/></option>
                            <option value="SUV"><fmt:message key="cars.car_type.suv"/></option>
                            <option value="SEDAN"><fmt:message key="cars.car_type.sedan"/></option>
                            <option value="MINIVAN"><fmt:message key="cars.car_type.minivan"/></option>
                        </select>
                    </div>
                </div>
                <div class="form-row form-group">
                    <div class="col-4 label-column">
                        <label class="col-form-label"><fmt:message key="cars.price_range"/></label></div>
                    <div class="col-7 input-column">
                        <input type="text" name="price_range" value="${carParameters.get("price_range")}" id="range_03">
                    </div>
                </div>
                <div class="form-row form-group">
                    <div class="col-4 offset-4 input-column">
                        <button class="submit-button" type="submit" id="butt">
                            <fmt:message key="cars.filter"/></button>
                    </div>
                </div>
                <input type="hidden" name="command" value="filter_cars">
            </form>
        </div>
        <div class="col-7 shadow-sm">
            <div class="form-row form-group">
            <c:choose>
                <c:when test="${not empty carList}">
                <c:forEach var="carElement" items="${carList}">
                <div class="col-3">
                    <img src="${pageContext.request.contextPath}/img/cars/5030mercedes.png"/>
                </div>
                <div class="col-6">
                    <div class="row">
                        <div class="col">
                            <p>${carElement.model}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-3">
                            <p>${carElement.type.name()}</p>
                        </div>
                        <div class="col-3">
                            <p>${carElement.fuelConsumption}l/100km</p>
                        </div>
                        <div class="col-3">
                            <p>${carElement.fuelType.name()}l/100km</p>
                        </div>
                    </div>
                </div>
                <div class="col-3">
                    <div class="row">
                        <p>${carElement.rentCost}$ per day</p>
                    </div>
                    <div class="form-row form-group">
                    <div class="row input-column" >
                        <button class="submit-button" type="submit" id="buttOrd">
                            ORDER</button>
                    </div>
                    </div>
                </div>
                </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:if test="${empty noCars}">
                        <label class="col-form-label"><fmt:message key="cars.choose_params"/></label></div>
                    </c:if>
                    <c:if test="${not empty noCars && !noCars}">
                        <label class="col-form-label"><fmt:message key="cars.nothing_to_show"/></label></div>
                    </c:if>
                </c:otherwise>
            </c:choose>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://bootstraptema.ru/plugins/2018/irs/ion.rangeSlider.min.js"></script>
<script src="${pageContext.request.contextPath}/js/price_range.js"></script>
</body>
</html>
