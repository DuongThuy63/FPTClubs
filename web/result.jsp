<%-- 
    Document   : result
    Created on : 9 Mar 2024, 17:45:51
    Author     : minhdq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/header.jsp"%>

<!DOCTYPE html>
<html>

    <section class="pt-5 padding-bottom">
        <div class="container">
            <div class="breadcrumb-wrapper">
                <div class="col-lg-12 text-center">
                    <h5 class="message">${message}</h5>
                    <div>
                        <a class="btn btn-primary btn-login btn-back" href="index">Back to home</a>
                        <!--<button class="btn btn-primary btn-login btn-back" onclick="goBack()">Go Back</button>-->
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
