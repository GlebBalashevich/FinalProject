<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.contentpage"/>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<nav class="navbar navbar-light navbar-expand-md navbar navbar-expand-lg fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand shadow-none js-scroll-trigger" id="brandLogo"
                              href="process_controller?command=move_home_page"><fmt:message key="title.brand_name"/></a>
        <button data-toggle="collapse" class="navbar-toggler navbar-toggler-right" data-target="#navbarResponsive"
                type="button" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"
                value="Menu"><i class="fa fa-bars"></i></button>
        <div class="collapse navbar-collapse text-success" id="navbarResponsive">
            <ul class="nav navbar-nav ml-auto">
                <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                    <a class="nav-link js-scroll-trigger" href="process_controller?command=move_home_page">
                        <fmt:message key="label.home"/>
                    </a>
                </li>
                <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                    <a class="nav-link js-scroll-trigger" href="process_controller?command=move_cars_page">
                        <fmt:message key="label.cars"/>
                    </a>
                </li>
                <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                    <a class="nav-link js-scroll-trigger" href="process_controller?command=move_user_office_page">
                        <fmt:message key="label.office"/>
                    </a>
                </li>
                <c:choose>
                    <c:when test="${empty sessionScope.user.role}">
                        <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                            <a class="nav-link js-scroll-trigger"
                               href="process_controller?command=move_login_page">
                                <fmt:message key="label.login"/>
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                            <a class="nav-link js-scroll-trigger" href="process_controller?command=log_out_user">
                                <fmt:message key="label.logout"/>
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
        <div class="col" id="lang_switch" style="max-width: 60px">
            <div class="row">
                <div class="col text-right"><a href="process_controller?command=switch_locale&locale=ru">
                    <fmt:message key="title.locale_ru"/></a></div>
            </div>
            <div class="row">
                <div class="col text-right"><a href="process_controller?command=switch_locale&locale=en">
                    <fmt:message key="title.locale_en"/></a></div>
            </div>
        </div>
    </div>
</nav>