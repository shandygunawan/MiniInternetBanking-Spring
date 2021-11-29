<%--
  Created by IntelliJ IDEA.
  User: shand
  Date: 2021-11-23
  Time: 12:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>

    <%-- JQuery & Bootstrap --%>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

    <title>Welcome to Mini Internet Banking!</title>
</head>
<body>
    <div class="d-flex justify-content-center p-4">

        <div class="card shadow p-3">
            <h5 class="card-title text-center">Welcome to Mini Internet Banking!</h5>
            <div class="card-body">
                <form:form method="post" action="/MiniInternetBanking/perform_login" modelAttribute="user">
                    <div class="mb-3 row">
                        <label for="input-user-username" class="col-sm-4 col-form-label">Username</label>
                        <div class="col-sm-8">
                            <form:input path="username" id="input-user-username" cssClass="form-control" />
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="input-user-password" class="col-sm-4 col-form-label">Password</label>
                        <div class="col-sm-8">
                            <form:password path="password" cssClass="form-control" id="input-user-password" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 d-flex flex-row-reverse">
                            <input type="submit" value="Login" class="btn btn-primary" />
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</body>
<script>
    $(document).ready(function() {
        $("#input-user-username").prop('required', true);
        $("#input-user-password").prop('required', true);
        $("#input-user-password").attr('minlength', "5");
    });
</script>
</html>
