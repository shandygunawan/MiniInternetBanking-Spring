<%--
  Created by IntelliJ IDEA.
  User: shand
  Date: 2021-11-27
  Time: 1:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <jsp:include page="components/scriptstyle.jsp" />

    <title>User Accounts</title>
</head>
<body>

<div class="wrapper">

    <jsp:include page="components/sidebar.jsp" />

    <div id="content" class="p-3" style="width: 100%">

        <jsp:include page="components/header.jsp" />

        <div class="container-fluid">
            <h3><fmt:message key="profile.userAccount.title" /></h3>

            <form:form method="post" action="/MiniInternetBanking/user/createUserAccount" modelAttribute="userAccount">
                <div class="row form-group my-3">
                    <div class="col-2">
                        <label for="input-accountnumber"><fmt:message key="profile.userAccount.accountNumber" /></label>
                    </div>
                    <div class="col-6">
                        <form:input path="accountNumber" cssClass="form-control" id="input-accountnumber" />
                    </div>
                </div>
                <div class="row form-group my-3">
                    <div class="col-2">
                        <label for="input-balance"><fmt:message key="profile.userAccount.balance" /></label>
                    </div>
                    <div class="col-6">
                        <form:input path="balance" cssClass="form-control" id="input-balance" />
                    </div>
                </div>
                <div class="row form-group my-3">
                    <div class="col-2">
                        <input type="submit" value="<fmt:message key="button.text.save" />" class="btn btn-primary" />
                    </div>
                </div>
            </form:form>

            <br/><br/>

            <div class="row">
                <div class="col-12">
                    <table id="table-user-accounts" class="table table-striped">
                        <thead>
                        <tr>
                            <th><fmt:message key="profile.userAccount.accountNumber" /></th>
                            <th><fmt:message key="profile.userAccount.balance" /></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${user_accounts}" var="account">
                            <tr>
                                <td>${account.accountNumber}</td>
                                <td><fmt:formatNumber value="${account.balance}" type="currency" currencySymbol="Rp " minFractionDigits="0" /></td>
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

    $(document).ready(function () {
        $("#table-user-accounts").DataTable();

        $("#input-accountnumber").prop("required", true);
        $("#input-amount").prop("required", true);
        $("#input-accountnumber").attr("minlength", 8);
        $("#input-accountnumber").attr("maxlength", 12);

        $("#input-accountnumber, #input-amount").inputFilter(function (value) {
            return /^\d*$/.test(value);
        })
    });
</script>

</html>
