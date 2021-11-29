<%--
  Created by IntelliJ IDEA.
  User: shand
  Date: 2021-11-25
  Time: 9:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <jsp:include page="components/scriptstyle.jsp" />
    <title>Credit Card - Transactions</title>
</head>
<body>
    <div class="wrapper">
        <jsp:include page="components/sidebar.jsp" />

        <div id="content" class="p-3" style="width: 100%">

            <div class="container-fluid">

                <div class="row my-3">
                    <div class="col-12">
                        <h1><fmt:message key="credit.transaction.title" /></h1>
                    </div>
                </div>

                <div class="row my-3">
                    <div class="col-2">
                        <label for="select-account">
                            <fmt:message key="credit.transaction.chooseAccount" />
                        </label>
                    </div>
                    <div class="col-6">
                        <select id="select-account">
                            <c:forEach items="${user_accounts}" var="account">
                                <option value="${account.accountNumber}">${account.accountNumber}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="row my-3">
                    <div class="col-12">
                        <table id="table-transaction" class="table table-striped">
                            <thead>
                                <tr>
                                    <th><fmt:message key="credit.transaction.transactionId" /></th>
                                    <th><fmt:message key="credit.transaction.merchant" /></th>
                                    <th><fmt:message key="credit.transaction.date" /></th>
                                    <th><fmt:message key="credit.transaction.amount" /></th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${credit_transaction}" var="trans">
                                <tr>
                                    <td>${trans.id}</td>
                                    <td>${trans.merchant}</td>
                                    <td>${trans.date}</td>
                                    <td><fmt:formatNumber value="${trans.amount}" type="currency" currencySymbol="Rp " minFractionDigits="0" /></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>

            <br/><br/>

        </div>
    </div>
</body>

<script>

    var getUrlParameter = function getUrlParameter(sParam) {
        var sPageURL = window.location.search.substring(1),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;

        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');

            if (sParameterName[0] === sParam) {
                return typeof sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
            }
        }
        return false;
    };

    $(document).ready(function () {
        $("#table-transaction").DataTable();

        let accountNumber = getUrlParameter("accountNumber");

        if(accountNumber === false || accountNumber == null || accountNumber === "") {
            $("#select-account").val($("#select-account option:first").val());
        } else {
            $("#select-account").val($('#select-account option[value="' + accountNumber + '"]').val());
        }
    });

    $("#select-account").change(function () {
        var selectedOption = $('#select-account').val();
        if (selectedOption != ''){
            window.location.replace('/MiniInternetBanking/creditCardTransaction?accountNumber=' + selectedOption);
        }
    });
</script>

</html>
