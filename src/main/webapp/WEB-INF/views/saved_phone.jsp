<%--
  Created by IntelliJ IDEA.
  User: shand
  Date: 2021-11-25
  Time: 8:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <jsp:include page="components/scriptstyle.jsp" />

    <title>Save Phone Number</title>
</head>
<body>

<div class="wrapper">

    <jsp:include page="components/sidebar.jsp" />

    <div id="content" class="p-3" style="width: 100%">

        <jsp:include page="components/header.jsp" />

        <div class="container-fluid">
            <h3><fmt:message key="profile.savedPhone.title" /></h3>

            <form:form method="post" action="/MiniInternetBanking/user/savePhoneNumber" modelAttribute="savedPhone">
                <div class="row form-group my-3">
                    <div class="col-2">
                        <label for="select-provider"><fmt:message key="profile.savedPhone.provider" /></label>
                    </div>
                    <div class="col-6">
                        <form:select id="select-provider" path="provider" cssClass="form-select">
                            <option value="telkomsel" selected>Telkomsel</option>
                            <option value="xl">XL</option>
                            <option value="indosat">Indosat</option>
                        </form:select>
                    </div>
                </div>
                <div class="row form-group my-3">
                    <div class="col-2">
                        <label for="input-phonenumber"><fmt:message key="profile.savedPhone.phoneNumber" /></label>
                    </div>
                    <div class="col-6">
                        <form:input path="phoneNumber" cssClass="form-control" id="input-phonenumber" />
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
                    <table id="table-saved-phones" class="table table-striped">
                        <thead>
                        <tr>
                            <th><fmt:message key="profile.savedPhone.provider" /></th>
                            <th><fmt:message key="profile.savedPhone.phoneNumber" /></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${saved_phone_numbers}" var="phone">
                            <tr>
                                <td>${phone.provider}</td>
                                <td>${phone.phoneNumber}</td>
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
        $("#table-saved-phones").DataTable();

        $("#select-provider").prop("required", true);
        $("#input-phonenumber").prop("required", true);
        $("#input-phonenumber").attr("minlength", 8);

        $("#input-phonenumber").inputFilter(function (value) {
            return /^\d*$/.test(value);
        })
    });

</script>

</html>
