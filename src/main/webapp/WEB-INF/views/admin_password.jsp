<%--
  Created by IntelliJ IDEA.
  User: shand
  Date: 2021-11-24
  Time: 8:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<html>
<head>
    <jsp:include page="components/scriptstyle.jsp" />
    <title>Admin - Change Password</title>
</head>
<body>
    <div class="wrapper">
        <jsp:include page="components/sidebar.jsp" />

        <div id="content" class="p-3" style="width: 100%">
            <div class="container-fluid">
                <div class="row my-2">
                    <div class="col-12">
                        <h1><fmt:message key="admin.password.title" /></h1>
                    </div>
                </div>

                <form id="form-changepassword" method="post" action="/MiniInternetBanking/user/updatePassword">
                    <div class="row form-group my-3">
                        <div class="col-2">
                            <label for="input-user-oldpassword"><fmt:message key="admin.password.oldPassword" /></label>
                        </div>
                        <div class="col-6">
                            <input type="password" name="old_password" id="input-user-oldpassword" class="form-control" />
                        </div>
                    </div>

                    <div class="row form-group my-3">
                        <div class="col-2">
                            <label for="input-user-newpassword"><fmt:message key="admin.password.newPassword" /></label>
                        </div>
                        <div class="col-6">
                            <input type="password" name="new_password" id="input-user-newpassword" class="form-control" />
                        </div>
                    </div>

                    <div class="row form-group my-3">
                        <div class="col-2">
                            <label for="input-user-confirmpassword"><fmt:message key="admin.password.confirmPassword" /></label>
                        </div>
                        <div class="col-6 d-flex flex-column">
                            <input type="password" name="confirm_password" id="input-user-confirmpassword" class="form-control" />
                            <small id="input-user-confirmpassword-help" style="color: red;"></small>
                        </div>
                    </div>

                    <div class="row my-3">
                        <div class="col-12">
                            <input type="submit" value="<fmt:message key="button.text.submit" />" class="btn btn-primary" />
                        </div>
                    </div>

                </form>

            </div>
        </div>
    </div>
</body>

<script>
    $(document).ready(function () {
        $("#input-user-oldpassword").prop("required", true);
        $("#input-user-newpassword").prop("required", true);
        $("#input-user-confirmpassword").prop("required", true);

        $("#input-user-oldpassword").attr("minlength", "5");
        $("#input-user-newpassword").attr("minlength", "5");
        $("#input-user-confirmpassword").attr("minlength", "5");

        $("#input-user-confirmpassword-help").hide();

        $("#form-changepassword").submit(function() {
            return validation();
        });
    });

    function validation() {
        let newPassword = $("#input-user-newpassword").val();
        let confirmPassword = $("#input-user-confirmpassword").val();

        if (newPassword !== confirmPassword) {
            $("#input-user-confirmpassword-help").text('<fmt:message key="error.password.confirmFailed" />');
            $("#input-user-confirmpassword-help").show();
            return false;
        }

    }
</script>

</html>
