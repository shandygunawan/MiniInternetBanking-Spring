<%--
  Created by IntelliJ IDEA.
  User: shand
  Date: 2021-11-23
  Time: 4:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/sidebars.css" />
</head>

<body>
    <div class="flex-shrink-0 p-3 bg-white" style="width: 280px;">
        <a href="/MiniInternetBanking" class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
            <svg class="bi me-2" width="30" height="24"><use xlink:href="#bootstrap"/></svg>
            <span class="fs-5 fw-semibold">Mini Internet Banking</span>
        </a>
        <ul class="list-unstyled ps-0">
            <li class="mb-1">
                <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="false">
                    <fmt:message key="sidebar.purchase" />
                </button>
                <div class="collapse" id="home-collapse">
                    <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                        <li><a href="/MiniInternetBanking/purchaseVoucher" class="link-dark rounded">
                            <fmt:message key="sidebar.purchase.topupVoucher" />
                        </a></li>
                    </ul>
                </div>
            </li>
            <li class="mb-1">
                <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#dashboard-collapse" aria-expanded="false">
                    <fmt:message key="sidebar.payment" />
                </button>
                <div class="collapse" id="dashboard-collapse">
                    <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                        <li><a href="/MiniInternetBanking/paymentTelephone" class="link-dark rounded">
                            <fmt:message key="sidebar.payment.telephone" />
                        </a></li>
                    </ul>
                </div>
            </li>
            <li class="mb-1">
                <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="false">
                    <fmt:message key="sidebar.creditcard" />
                </button>
                <div class="collapse" id="orders-collapse">
                    <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                        <li><a href="/MiniInternetBanking/creditCardTransaction" class="link-dark rounded"><fmt:message key="sidebar.creditcard.transaction" /></a></li>
                        <li><a href="/MiniInternetBanking/creditCardBill" class="link-dark rounded"><fmt:message key="sidebar.creditcard.bill" /></a></li>
                    </ul>
                </div>
            </li>
            <li class="border-top my-3"></li>
            <li class="mb-1">
                <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#account-collapse" aria-expanded="false">
                    <fmt:message key="sidebar.administration" />
                </button>
                <div class="collapse" id="account-collapse">
                    <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                        <li><a href="/MiniInternetBanking/adminLanguage" class="link-dark rounded"><fmt:message key="sidebar.administration.changeLanguage" /></a></li>
                        <li><a href="/MiniInternetBanking/adminPassword" class="link-dark rounded"><fmt:message key="sidebar.administration.changePassword" /></a></li>
                    </ul>
                </div>
            </li>
            <li class="mb-1">
                <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#profile-collapse" aria-expanded="false">
                    <fmt:message key="sidebar.profile" />
                </button>
                <div class="collapse" id="profile-collapse">
                    <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                        <li><a href="/MiniInternetBanking/savedPhone" class="link-dark rounded"><fmt:message key="sidebar.profile.savedPhoneNumber" /></a></li>
                        <li><a href="/MiniInternetBanking/userAccount" class="link-dark rounded"><fmt:message key="sidebar.profile.userAccount" /></a></li>
                    </ul>
                </div>
            </li>
        </ul>
    </div>

    <script src="${pageContext.request.contextPath}/resources/assets/js/sidebars.js"></script>
</body>
