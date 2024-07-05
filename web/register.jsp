<%-- 
    Document   : register
    Created on : 8 Mar 2024, 23:50:12
    Author     : minhdq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="model.*"%>
<%@include file="/includes/header.jsp"%>
<!DOCTYPE html>
<html>
    <div class="breadcrumb-wrapper">
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <div class="text-center">
                        <h1 class="lg-title">REGISTER</h1>
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

                            <form id="contact-form" class="contact-form" action="register" method="post">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label class="lg-form" for="name">Username</label>
                                                <input class="form-control form-control-name" name="username" 
                                                       type="text" required placeholder="Enter username">
                                                <h5 style="color:red">${requestScope.errorUserMail}</h5>
                                                <h5 style="color:red">${requestScope.errorUsername}</h5>    
                                                <h5 style="color:red">${requestScope.errorUsernameLength}</h5>    

                                            </div>

                                            <div class="form-group col-md-6">
                                                <label class="lg-form" for="name">Full Name (Optional)</label>
                                                <input class="form-control form-control-name" name="full-name" 
                                                       type="text" placeholder="Enter full name">
                                            </div>
                                        </div>


                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="gender" class="lg-form">Gender</label>
                                                <select class="dropdown-item dropdown-choice" name="gender" id="gender">
                                                    <option value="Male" ${user.gender == 'M' ? 'selected' : ''}>Male</option>
                                                    <option value="Female" ${user.gender == 'F' ? 'selected' : ''}>Female</option>
                                                    <option value="Others" ${user.gender == 'O' ? 'selected' : ''}>Others</option>
                                                </select>
                                            </div>

                                            <div class="form-group col-md-6">
                                                <label class="lg-form" for="name">Date of Birth</label>
                                                <input class="form-control form-control-name" name="dob" 
                                                       type="text" required placeholder="dd/MM/yyyy">
                                                <h5 style="color:red">${requestScope.errorDate}</h5>
                                            </div>
                                        </div>


                                        <div class="form-group">
                                            <label class="lg-form" for="pass">Password</label>

                                            <input class="form-control form-control-email" name="password"
                                                   type="password" required placeholder="Enter password">
                                            <h5 style="color:red">${requestScope.errorConfirmPass}</h5>    
                                            <h5 style="color:red">${requestScope.errorPass}</h5>  
                                            <h5 style="color:red">${requestScope.errorPassLength}</h5>  
                                        </div>
                                        <div class="form-group">
                                            <label class="lg-form" for="pass">Confirm Password</label>

                                            <input class="form-control form-control-email" name="confirm-pass"
                                                   type="password" required placeholder="Confirm your password">
                                            <h5 style="color:red">${requestScope.errorConfirmPass}</h5>    

                                        </div>

                                        <div class="form-group">
                                            <label class="lg-form" for="email">Email</label>

                                            <input class="form-control form-control-email" name="email"
                                                   type="email" required placeholder="Enter email">
                                            <h5 style="color:red">${requestScope.errorUserMail}</h5>

                                        </div>



                                        <button class="btn btn-primary solid blank mt-5" type="submit">Register</button>
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
                        <h4>Already logged in?</h4>
                        <a class="btn btn-primary btn-login" href="login">Login</a>
                    </div>
                </div>

                <div class="row no-gutters" id="instafeed">

                </div>
            </div>
        </div>
<%@ include file="/includes/footer2.jsp" %>

</html>

