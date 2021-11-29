<%--
  Created by IntelliJ IDEA.
  User: shand
  Date: 2021-11-24
  Time: 4:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <jsp:include page="components/scriptstyle.jsp" />

    <%--  JQuery UI For AutoComplete  --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/jquery-ui.css" />
    <script src="${pageContext.request.contextPath}/resources/assets/js/jquery-ui.js"></script>

    <title>Purchase - Top Up Voucher</title>
</head>
<body>
<div class="wrapper">

    <jsp:include page="components/sidebar.jsp" />

    <div id="content" class="p-3" style="width: 100%">

        <jsp:include page="components/header.jsp" />

        <div class="container-fluid">
            <div class="row my-2">
                <div class="col-12">
                    <h3><fmt:message key="purchase.voucher.title" /></h3>
                </div>
            </div>

            <form method="post" action="/MiniInternetBanking/purchase/voucher">
                <div class="row form-group my-3">
                    <div class="col-2">
                        <label for="select-provider"><fmt:message key="purchase.voucher.provider" /></label>
                    </div>
                    <div class="col-6">
                        <select id="select-provider" name="provider" class="form-select">
                            <option value="telkomsel" selected>Telkomsel</option>
                            <option value="xl">XL</option>
                            <option value="indosat">Indosat</option>
                        </select>
                    </div>
                </div>
                <div class="row form-group my-3">
                    <div class="col-2">
                        <label for="input-purchase-phonenumber"><fmt:message key="purchase.voucher.phoneNumber" /></label>
                    </div>
                    <div class="col-6">
                        <input type="text" name="phone_number" class="form-control" id="input-purchase-phonenumber" />
                    </div>
                </div>

                <div class="row form-group my-3">
                    <div class="col-2">
                        <label for="input-purchase-token"><fmt:message key="purchase.voucher.token" /></label>
                    </div>
                    <div class="col-6">
                        <input type="text" name="token" class="form-control" id="input-purchase-token" readonly />
                    </div>
                </div>

                <div class="row form-group my-3">
                    <div class="col-2">
                        <label for="input-purchase-apply2"><fmt:message key="purchase.voucher.apply2" /></label>
                    </div>
                    <div class="col-6">
                        <input type="text" name="apply2" class="form-control" id="input-purchase-apply2" />
                    </div>
                </div>

                <div class="row form-group my-3">
                    <div class="col-2">
                        <label for="select-account"><fmt:message key="purchase.voucher.chooseAccount" /></label>
                    </div>
                    <div class="col-6">
                        <select id="select-account" name="account_number" class="form-select">
                            <c:forEach items="${user_accounts}" var="account">
                                <option value="${account.accountNumber}">
                                        ${account.accountNumber}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="row form-group my-3">
                    <div class="col-2">
                        <input type="submit" value="<fmt:message key="button.text.submit" />" class="btn btn-primary" />
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>

<script>
    var savedPhoneNumbers = JSON.parse('${saved_phone_numbers}');

    // Functions

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


    $(document).ready(function() {
        $("#select-provider").prop("required", true);
        $("#input-purchase-phonenumber").prop("required", true);
        $("#input-purchase-token").prop("required", true);
        $("#input-purchase-apply2").prop("required", true);
        $("#input-purchase-phonenumber").attr("minlength", "8");

        var autocompleteOptions = [];

        for(let i = 0; i < savedPhoneNumbers.length; i++) {
           autocompleteOptions.push(savedPhoneNumbers[i].phoneNumber);
        }

        $("#input-purchase-phonenumber").autocomplete({
            source: autocompleteOptions,
        });

        $("#input-purchase-phonenumber").inputFilter(function (value) {
            return /^\d*$/.test(value);
        });

        $("#select-account").val($("#select-account option:first").val());
    });

    $("#input-purchase-phonenumber").change(function() {
        let random_number = Math.floor(10 + Math.random() * 90);
        let phone_number = $(this).val();
        let token = random_number + phone_number.substr(phone_number.length - 6);
        $("#input-purchase-token").val(token);
    });
</script>

</html>
