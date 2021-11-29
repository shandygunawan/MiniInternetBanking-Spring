<%--
  Created by IntelliJ IDEA.
  User: shand
  Date: 2021-11-23
  Time: 10:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <jsp:include page="components/scriptstyle.jsp" />

    <title>Homepage</title>
</head>
<body>
    <div class="wrapper">
        <jsp:include page="components/sidebar.jsp" />

        <div id="content" class="p-3" style="width: 100%">

            <h1>Hello World!</h1>
        </div>
    </div>
</body>
</html>
