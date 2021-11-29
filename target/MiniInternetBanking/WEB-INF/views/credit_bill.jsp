<%--
  Created by IntelliJ IDEA.
  User: shand
  Date: 2021-11-25
  Time: 9:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <jsp:include page="components/scriptstyle.jsp" />
    <title>Credit Bill</title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="components/sidebar.jsp" />

    <div id="content" class="p-3" style="width: 100%">

        <jsp:include page="components/header.jsp" />

        <div class="container-fluid">
            <div class="row my-2">
                <div class="col-12">
                    <h1><fmt:message key="credit.bill.title" /></h1>
                </div>
            </div>

            <div class="row my-3">
                <div class="col-2">
                    <label for="select-account">
                        <fmt:message key="credit.bill.chooseAccount" />
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

            <div class="row my-2">
                <div class="col-12">
                    <div class="card p-2 shadow">
                        <div class="card-text">
                            <div class="row mb-2">
                                <div class="col-2"> <fmt:message key="credit.bill.info.period" /> </div>
                                <div class="col-2">: ${current_month} </div>
                            </div>
                            <div class="row mb-2">
                                <div class="col-2"> <fmt:message key="credit.bill.info.accountNumber" /> </div>
                                <div class="col-2">: ${account_number} </div>
                            </div>
                            <div class="row mb-2">
                                <div class="col-2"> <fmt:message key="credit.bill.info.totalNetBill" /> </div>
                                <div class="col-2">: <fmt:formatNumber value="${net_bill_amount}" type="currency" currencySymbol="Rp " minFractionDigits="0" />  </div>
                            </div>
                            <div class="row mb-2">
                                <div class="col-2"> <fmt:message key="credit.bill.info.minimumPayment" /> </div>
                                <div class="col-2">: <fmt:formatNumber value="10000" type="currency" currencySymbol="Rp " minFractionDigits="0" /> </div>
                            </div>
                            <div class="row mb-2">
                                <div class="col-2"> <fmt:message key="credit.bill.info.dueDate" /> </div>
                                <div class="col-2">: ${due_date} </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <br/><br/>

            <div class="row my-2">
                <div class="col-12">
                    <h3><fmt:message key="credit.bill.title.billCurrentMonth" /></h3>
                </div>
            </div>

            <div class="row my-2">
                <div class="col-12">
                    <table id="table-bill" class="table table-striped">
                        <thead>
                            <tr>
                                <th><fmt:message key="credit.bill.table.billId" /></th>
                                <th><fmt:message key="credit.bill.table.merchant" /></th>
                                <th><fmt:message key="credit.bill.table.date" /></th>
                                <th><fmt:message key="credit.bill.table.amount" /></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${credit_bill}" var="bill">
                                <tr>
                                    <td>${bill.id}</td>
                                    <td>${bill.merchant}</td>
                                    <td>${bill.date}</td>
                                    <td><fmt:formatNumber value="${bill.amount}" type="currency" currencySymbol="Rp " minFractionDigits="0" /></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <br/><br/>

            <form id="form-pay" method="post" action="/MiniInternetBanking/creditCard/payBill">
                <div class="row my-2 form-group">
                    <div class="col-1">
                        <label for="input-bill-amount"><fmt:message key="credit.bill.pay" /></label>
                    </div>
                    <div class="col-3 d-flex flex-column">
                        <input type="text" name="amount" class="form-control" id="input-bill-amount" />
                        <small id="input-bill-amount-help" style="color: red;"></small>
                    </div>
                    <div class="col-2">
                        <input type="submit" value="<fmt:message key="credit.bill.pay" />" class="btn btn-primary" />
                    </div>
                </div>
                <input type="hidden" name="account_number" value="${account_number}" />
            </form>

            <br/><br/>

            <div class="row my-2">
                <div class="col-12">
                    <h3><fmt:message key="credit.bill.title.paymentCurrentMonth" /></h3>
                </div>
            </div>

            <div class="row my-2">
                <div class="col-12">
                    <table id="table-payment" class="table table-striped">
                        <thead>
                        <tr>
                            <th><fmt:message key="credit.bill.table.paymentId" /></th>
                            <th><fmt:message key="credit.bill.table.date" /></th>
                            <th><fmt:message key="credit.bill.table.amount" /></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${credit_payment}" var="payment">
                            <tr>
                                <td>${payment.id}</td>
                                <td>${payment.date}</td>
                                <td><fmt:formatNumber value="${payment.amount}" type="currency" currencySymbol="Rp " minFractionDigits="0" /></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>

    </div>
</div>
</body>

<script>

    // Restricts input for the set of matched elements to the given inputFilter function.
    (function($) {
        $.fn.inputFilter = function(inputFilter) {
            return this.on("input keydown keyup mousedown mouseup select contextmenu drop", function() {
                if (inputFilter(this.value)) {
                    this.oldValue = this.value;
                    this.oldSelectionStart = this.selectionStart;
                    this.oldSelectionEnd = this.selectionEnd;
                } else if (this.hasOwnProperty("oldValue")) {
                    this.value = this.oldValue;
                    this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
                } else {
                    this.value = "";
                }
            });
        };
    }(jQuery));

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
        $("#table-bill, #table-payment").DataTable();

        $("#input-bill-amount").inputFilter(function (value) {
            return /^\d*$/.test(value);
        })

        $("#input-bill-amount-help").hide();

        $("#form-pay").submit(function() {
            return validation();
        });

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
            window.location.replace('/MiniInternetBanking/creditCardBill?accountNumber=' + selectedOption);
        }
    });


    function validation() {
        let amount = document.getElementById("input-bill-amount").value;

        if(!amount) {
            $("#input-bill-amount-help").text('<fmt:message key="error.amount.empty" />')
            $("#input-bill-amount-help").show();
            return false;
        }

        if(parseInt(amount) < 10000) {
            $("#input-bill-amount-help").text('<fmt:message key="error.amount.lessThan10K" />')
            $("#input-bill-amount-help").show();
            return false;
        }
    }

</script>

</html>
