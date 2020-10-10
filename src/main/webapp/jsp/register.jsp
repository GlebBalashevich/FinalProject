<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.contentpage"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title><fmt:message key="title.register"/></title>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200;0,300;0,400;0,600;0,700;0,800;0,900;1,200;1,300;1,400;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Login-Form-Dark.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Pretty-Registration-Form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body id="page-top" style="background-color: rgb(56,55,57);">
<c:import url="${pageContext.request.contextPath}/jsp/fragment/header.jsp"/>
<header class="masthead">
    <div class="intro-body">
    <div class="row register-form" id="register_form">
    <div class="col-md-8 offset-md-2">
        <form action="process_controller" method="post" class="shadow-lg custom-form" id="register" style="background-color: rgba(0,0,0,0.08); font-family: Nunito">
            <c:if test="${not empty registerParameters}">
                <label class="alert-danger"><fmt:message key="register.invalid_parameters"/></label>
            </c:if>
            <div class="form-row form-group" style="margin: 0px -5px 15px;">
                <div class="col-sm-4 label-column">
                    <label class="col-form-label"><fmt:message key="register.email"/></label></div>
                <div class="col-sm-4 input-column">
                    <input class="form-control" name="email" type="email"
                           autofocus required value="${registerParameters.get("email")}"
                           minlength="7" maxlength="255" title="<fmt:message key="input.email"/>">
                </div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-4 label-column">
                    <label class="col-form-label"><fmt:message key="register.password"/></label></div>
                <div class="col-sm-4 input-column">
                    <input class="form-control" name="password" type="password"
                           required pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{8,}" minlength="8" maxlength="255">
                </div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-4 label-column">
                    <label class="col-form-label"><fmt:message key="register.confirm_password"/></label></div>
                <div class="col-sm-4 input-column">
                    <input class="form-control" name="confirm_password" type="password"
                           required pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{8,}" minlength="8" maxlength="255">
                </div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-4 label-column">
                    <label class="col-form-label"><fmt:message key="register.first_name"/></label></div>
                <div class="col-sm-4 input-column">
                    <input class="form-control" name="first_name" type="text"
                           pattern="[a-zA-Zа-яА-Я-]{1,20}"
                           required value="${registerParameters.get("first_name")}">
                </div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-4 label-column"><label class="col-form-label label-column"><fmt:message key="register.second_name"/></label>
                </div>
                <div class="col-sm-4 input-column">
                    <input class="form-control" name="second_name" type="text"
                           pattern="[a-zA-Zа-яА-Я-]{1,20}"
                           required value="${registerParameters.get("second_name")}">
                </div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-4 label-column"><label class="col-form-label">
                    <fmt:message key="register.driver_license"/></label></div>
                <div class="col-sm-4 input-column">
                    <input class="form-control" name="driver_license" type="text"
                           pattern="([0-9]?[a-zA-Z]{2}\s?[0-9]{6})"
                           required value="${registerParameters.get("driver_license")}">
                </div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-4 label-column"><label class="col-form-label">
                    <fmt:message key="register.phone_number"/></label></div>
                <div class="col-sm-4 input-column">
                    <input class="form-control" name="phone_number" type="text"
                           pattern="^(\+)?([-_():\s]?\d[-_():\s]?){12}?$" value="${registerParameters.get("phone_number")}">
                </div>
            </div>
            <input type="hidden" name="command" value="register_client">
            <button class="btn btn-light submit-button" type="submit" id="butt">
                <fmt:message key="register.register"/></button>
        </form>
    </div>
    </div>
    </div>
</header>
<c:import url="${pageContext.request.contextPath}/jsp/fragment/footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath}/js/grayscale.js"></script>
</body>

</html>