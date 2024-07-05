<%-- 
    Document   : register
    Created on : 8 Mar 2024, 23:50:12
    Author     : minhdq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="model.*"%>
<%@include file="/includes/header.jsp"%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="css/profileUser-style.css">
 
    <section class="pt-5 padding-bottom">

        <div class="container">
            <div style="margin-top: 2%; margin-right: 17%;">


                <hr class="mt-0 mb-4">
                <div class="row">
                    <div class="col-xl-4">
                        <!-- Profile picture card-->
                        <div class="card mb-4 mb-xl-0">
                            <div class="card-header">Profile Picture</div>
                            <div class="card-body text-center">
                                <!-- Profile picture image-->
                                <img class="img-account-profile rounded-circle mb-2" src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="">
                                <!-- Profile picture help block-->
                                <div class="small font-italic text-muted mb-4">JPG or PNG no larger than 5 MB</div>
                                <!-- Profile picture upload button-->
                                <button class="btn btn-primary" type="button">
                                    <label class="fileImage"><input type="file"/>Upload New Image</label>
                                    <input type="file" class="form-control-file" id="fileImage">
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-8">
                        <!-- Account details card-->
                        <div class="card mb-4">
                            <div class="card-header">${account.username}'s Details</div>
                            <div class="card-body">
                                <form action="userprofile" method="post">
                                    <input type="hidden" name="username" value="${account.username}">


                                    <!-- Form Row-->
                                    <div class="mb-3">
                                        <!-- Form Group (Name)-->
                                        <label class="small mb-1" for="inputFullName">Full name</label>
                                        <input class="form-control" name="fullName" id="inputFullName" type="text" placeholder="${account.fullName}">

                                    </div>  
                                    <div class="mb-3">
                                        <!-- Form Group (Pass)-->
                                        <label class="small mb-1" for="inputPassword">Password</label>
                                        <input class="form-control" name="password" id="inputPassword" type="password" placeholder="Enter your new password">
                                        <h5 style="color:red">${requestScope.errorPass}</h5>  
                                        <h5 style="color:red">${requestScope.errorPassLength}</h5>  
                                    </div>  
                                    <!-- Form Group (email address)-->
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputPhone">Phone number</label>
                                        <input class="form-control" name="phone" id="inputPhone" type="tel" placeholder="${account.phone}">

                                        <label class="small mb-1" for="inputEmailAddress">Email address (Required)</label>
                                        <input class="form-control" name="email"  id="inputEmailAddress" type="email" placeholder="${account.email}">
                                    </div>
                                    <!-- Form Row-->
                                    <div class="row gx-3 mb-3">
                                        <!-- Form Group (phone number)-->
                                        <div class="form-group col-md-5">
                                            <label for="gender" class="small mb-1">Gender</label>
                                            <select class="form-control" name="gender" id="genders">
                                                <option value="Male" ${user.gender == 'M' ? 'selected' : ''}>Male</option>
                                                <option value="Female" ${user.gender == 'F' ? 'selected' : ''}>Female</option>
                                                <option value="Others" ${user.gender == 'O' ? 'selected' : ''}>Others</option>
                                            </select>
                                        </div>
                                        <!-- Form Group (birthday)-->
                                        <div class="col-md-5">
                                            <label class="small mb-1" for="inputBirthday">Birthday</label>
                                            <input class="form-control" name="dob"  id="inputBirthday" type="text" placeholder="${account.dob}">

                                        </div>
                                    </div>
                                    <!-- Save changes button-->
                                    <button class="btn btn-primary" type="submit">Save changes</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>     
        </div>            

    </section>

    <%@ include file="/includes/footer2.jsp" %>

</html>

