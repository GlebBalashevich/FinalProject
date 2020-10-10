<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.contentpage"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title><fmt:message key="title.login"/></title>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200;0,300;0,400;0,600;0,700;0,800;0,900;1,200;1,300;1,400;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Login-Form-Dark.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Pretty-Registration-Form.css">
</head>

<body id="page-top" style="background-color: rgb(56,55,57);">
<c:import url="${pageContext.request.contextPath}/jsp/fragment/header.jsp"/>
<header class="masthead">
    <div class="intro-body">
    <div class="row register-form" id="login_form">
    <div class="col-md-8 offset-md-2">
        <form action="process_controller" method="post" class="shadow-lg custom-form" style="font-family: Nunito">
            <c:if test="${not empty successfulAuthorization && !successfulAuthorization}">
                <label class="alert-danger"><fmt:message key="login.not_authorized"/></label>
            </c:if>
            <c:if test="${not empty successfulActivation && successfulActivation}">
                <label class="alert-success"><fmt:message key="login.activated_user"/></label>
            </c:if>
            <c:if test="${not empty successfulActivation && !successfulActivation}">
                <label class="alert-warning"><fmt:message key="login.not_activated_user"/></label>
            </c:if>
            <div class="form-row form-group">
                <div class="col-sm-4 label-column">
                    <label class="col-form-label"><fmt:message key="login.email"/></label></div>
                <div class="col-sm-4 input-column">
                    <input class="form-control" name="email" type="email" autofocus required
                           minlength="7" maxlength="255" title="<fmt:message key="input.email"/>">
                </div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-4 label-column">
                    <label class="col-form-label"><fmt:message key="login.password"/></label></div>
                <div class="col-sm-4 input-column">
                    <input class="form-control" name="password" type="password" required
                           pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{8,}" minlength="8" maxlength="255"
                           title="<fmt:message key="input.password"/>">
                </div>
            </div>
            <input type="hidden" name="command" value="log_in_user">
            <button class="submit-button" type="submit" id="butt">
                <fmt:message key="login.log_in"/></button>
        </form>
        <a class="shadow-sm" href="process_controller?command=move_register_page"><fmt:message key="login.no_account"/></a></div>
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