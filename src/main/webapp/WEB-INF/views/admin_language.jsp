<%--
  Created by IntelliJ IDEA.
  User: shand
  Date: 2021-11-24
  Time: 11:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<html>
<head>
    <jsp:include page="components/scriptstyle.jsp" />
    <title>Admin - Change Language</title>
</head>
<body>
    <div class="wrapper">
        <jsp:include page="components/sidebar.jsp" />

        <div id="content" class="p-3" style="width: 100%">
            <div class="container-fluid">
                <div class="row my-2">
                    <div class="col-12">
                        <h1><fmt:message key="admin.language.title" /></h1>
                    </div>
                </div>
                <div class="row my-3">
                    <div class="col-8">
                        <select id="locales" class="form-select form-select-lg" aria-label=".form-select-lg example">
                            <option value="" selected disabled><fmt:message key="admin.language.select.title" /></option>
                            <option value="en"><fmt:message key="admin.language.select.english" /></option>
                            <option value="id"><fmt:message key="admin.language.select.indonesia" /></option>
                        </select>
                    </div>
                    <div class="col-4">
                        <button type="button" class="btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#confirmModal">
                            <fmt:message key="button.text.change" />
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%-- Modal --%>
    <div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="confirmModalLabel">
                        <fmt:message key="admin.language.select.title" /> : <span id="span-modal-selected-language"></span>
                    </h5>
                </div>
                <div class="modal-body">
                    <fmt:message key="modal.text.areYouSure" />
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">
                        <fmt:message key="button.text.no" />
                    </button>
                    <button type="button" class="btn btn-primary" id="btn-modal-yes">
                        <fmt:message key="button.text.yes" />
                    </button>
                </div>
            </div>
        </div>
    </div>

</body>
<script>
    $(document).ready(function() {

        $("#locales").change(function () {
            var selectedOption = $('#locales').val();
            if(selectedOption === "en") {
                $("#span-modal-selected-language").text('<fmt:message key="admin.language.select.english" />');
            } else if(selectedOption === "id") {
                $("#span-modal-selected-language").text('<fmt:message key="admin.language.select.indonesia" />');
            }
        })

        $("#btn-modal-yes").click(function () {
            var selectedOption = $('#locales').val();
            if (selectedOption != ''){
                window.location.replace('adminLanguage?lang=' + selectedOption);
            }
        });
    });
</script>
</html>
