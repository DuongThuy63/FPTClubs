<%-- 
    Document   : createClub
    Created on : 11 Mar 2024, 19:25:04
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
                        <h1 class="lg-title">CREATE CLUB</h1>
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

                            <form id="contact-form" class="contact-form" action="create_club" method="post">
                                <div class="row">
                                    <div class="col-md-12">

                                        <div class="form-group">
                                            <label class="lg-form" for="name">Club's Name</label>
                                            <input class="form-control form-control-name" name="name" 
                                                   type="text" required placeholder="Enter name">
                                            <h5 style="color:red">${requestScope.errorNameLength}</h5>
                                            <h5 style="color:red">${requestScope.errorName}</h5>    


                                        </div>

                                        <div class="form-group">
                                            <label class="lg-form" for="pass">Club's Description</label>

                                            <textarea class="form-control form-control-message" name="description"
                                                      id="message" rows="5" required></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label for="category" class="lg-form">Club's Category</label>
                                            <select class="dropdown-item dropdown-choice" name="category" id="category">
                                                <option value="1">Academic</option>
                                                <option value="2">Music & Art</option>
                                                <option value="3">Sport</option>
                                                <option value="4">Social & Media</option>
                                                <option value="5">Community</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="lg-form" for="email">Contact Email</label>

                                            <input class="form-control form-control-email" name="email"
                                                   type="email" placeholder="Enter email">
                                        </div>

<!--                                        <div class="form-group">
                                            <label class="lg-form">Upload Club's Logo</label>
                                            <input type="file" name="fileImage" class="form-control-file" id="fileImage" accept="images/*">
                                        </div>-->
                                        <button class="btn btn-primary solid blank mt-5" type="submit">Create</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>



    <%@ include file="/includes/footer2.jsp" %>
</html>

