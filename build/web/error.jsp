<%-- 
    Document   : error
    Created on : 9 Mar 2024, 21:26:41
    Author     : minhdq
--%>

<%@page  isErrorPage="true" contentType="text/html" pageEncoding="utf-8" %>
<%@include file="/includes/header.jsp"%>

<!DOCTYPE html>
<html>

    <section class="pt-5 padding-bottom">
        <div class="container">
            <div class="breadcrumb-wrapper">
                <div class="col-lg-12 text-center">
                    <c:if test="${not empty exception}">
                        <h5 class="message">${exception.message}</h5>
                    </c:if>

                    <h5 class="message">${message}</h5>

                    <div>
                        <button class="btn btn-primary btn-login btn-back" onclick="goBack()">Go Back</button>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script>
        function goBack() {
            window.history.back(); // This function navigates back in the browser's history
        }
    </script>

</html>

<%@include file="/includes/footer2.jsp"%>
