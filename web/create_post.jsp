<%-- 
    Document   : createPost
    Created on : 13-Mar-2024, 18:54:09
    Author     : DuongPhan
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/header.jsp"%>

<html>
    <body>
        <section class="single-block-wrapper section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-md-12 col-sm-12 col-xs-12">

                        <form class="comment-form mb-5 gray-bg p-5" id="comment-form" action="create_post" method="post" enctype="multipart/form-data">
                            <h3 class="mb-4 text-center">Create POST</h3>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input class="form-control" type="text" name="title" id="title" placeholder="Title:">
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <textarea class="form-control mb-3" name="content" id="content" cols="30" rows="5"
                                              placeholder="Content"></textarea>
                                </div>

                                <div class="col-md-6">
                                    <label class="fileImage">Upload Image</label>
                                    <input type="file" name="fileImage" class="form-control-file" id="fileImage" accept="images/*">
                                </div>
                            </div>
                            <input type="hidden" name="articleId" value="${param.club_id}">

                            <input class="btn btn-primary" type="submit" name="submit-contact" id="submit_contact"
                                   value="Create" style="margin-top: 5%;">



                        </form>

                    </div>
                    <%@ include file="/includes/club_right_bar.jsp" %>
                </div>
            </div>
        </section>
    </body>

    <%@ include file="/includes/footer2.jsp" %>
</html>
