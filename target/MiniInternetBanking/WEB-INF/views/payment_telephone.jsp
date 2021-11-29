<%--
  Created by IntelliJ IDEA.
  User: shand
  Date: 2021-11-24
  Time: 1:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <jsp:include page="components/scriptstyle.jsp" />

    <%--  JQuery UI For AutoComplete  --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/jquery-ui.css" />
    <script src="${pageContext.request.contextPath}/resources/assets/js/jquery-ui.js"></script>

    <title>Payment - Telephone</title>
</head>
<body>
    <div class="wrapper">
        <jsp:include page="components/sidebar.jsp" />

        <div id="content" class="p-3" style="width: 100%">

            <jsp:include page="components/header.jsp" />

            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                        <h3><fmt:message key="payment.telephone.title" /></h3>
                    </div>
                </div>

                <form:form method="post" action="/MiniInternetBanking/payment/telephone" modelAttribute="payment_telephone">
                    <div class="row form-group my-2">
                        <div class="col-2">
                            <label for="input-payment-phonenumber"><fmt:message key="payment.telephone.phoneNumber" /></label>
                        </div>
                        <div class="col-6">
                            <form:input path="phoneNumber" cssClass="form-control" id="input-payment-phonenumber" />
                        </div>
                    </div>
                    <div class="row form-group my-2">
                        <div class="col-2">
                            <label for="input-payment-amount"><fmt:message key="payment.telephone.amount" /></label>
                        </div>
                        <div class="col-6">
                            <form:input path="amount" cssClass="form-control" id="input-payment-amount" />
                        </div>
                    </div>
                    <div class="row form-group my-3">
                        <div class="col-2">
                            <label for="select-account"><fmt:message key="payment.telephone.chooseAccount" /></label>
                        </div>
                        <div class="col-6">
                            <select id="select-account" name="accountNumber" class="form-select">
                                <c:forEach items="${user_accounts}" var="account">
                                    <option value="${account.accountNumber}">
                                            ${account.accountNumber}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row form-group my-2">
                        <div class="col-12">
                            <input type="submit" value="<fmt:message key="button.text.submit" />" class="btn btn-primary" />
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</body>

<script>
    var savedPhoneNumbers = JSON.parse('${saved_phone_numbers}');

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

    $(document).ready(function () {
        $("#input-payment-phonenumber").prop("required", true);
        $("#input-payment-amount").prop("required", true);
        $("#input-payment-phonenumber").attr("minlength", "8");

        $("#input-payment-phonenumber, #input-payment-amount").inputFilter(function (value) {
            return /^\d*$/.test(value);
        })

        var autocompleteOptions = [];

        for(let i = 0; i < savedPhoneNumbers.length; i++) {
            autocompleteOptions.push(savedPhoneNumbers[i].phoneNumber);
        }

        $("#input-payment-phonenumber").autocomplete({
            source: autocompleteOptions,
        });

        $("#select-account").val($("#select-account option:first").val());
    })
</script>

</html>
