<%-- 
    Document   : login
    Created on : 8 Mar 2024, 19:49:54
    Author     : minhdq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/header.jsp"%>
<!DOCTYPE html>
<html>
    <div class="breadcrumb-wrapper">
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <div class="text-center">
                        <h1 class="lg-title">LOGIN</h1>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <section class="pt-5 padding-bottom">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

                    <div class="row justify-content-center">
                        <div class="col-lg-8">


                            <form id="contact-form" class="contact-form" action="login" method="post">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="lg-form" for="name">Username </label>
                                            <input class="form-control form-control-name" name="username" 
                                                   type="text" required placeholder="Enter username">
                                            <h5 style="color:red">${requestScope.error}</h5>
                                        </div>

                                        <div class="form-group">
                                            <label class="lg-form" for="pass">Password</label>

                                            <input class="form-control form-control-email" name="password"
                                                   type="password" required placeholder="Enter password">
                                            <h5 style="color:red">${requestScope.error}</h5>
                                        </div>
                                        <button class="btn btn-primary solid blank mt-5" type="submit">Login</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    
        <div class="not-yet-section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h4>Not logged in yet?</h4>
                        <a class="btn btn-primary btn-login" href="register">Register</a>
                    </div>
                </div>

            </div>
        </div>
    <%@ include file="/includes/footer2.jsp" %>

</html>
