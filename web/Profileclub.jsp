
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/header.jsp"%>
<!DOCTYPE html>

<html>
    <link rel="stylesheet" href="css/profileUser-style.css">

    <body>


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
                                <img class="img-account-profile rounded-circle mb-2"
                                     src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="">
                                <!-- Profile picture help block-->
                                <div class="small font-italic text-muted mb-4">JPG or PNG no larger than 5 MB</div>
                                <!-- Profile picture upload button-->
                                <button class="btn btn-primary" type="button">
                                    <label class="fileImage"><input type="file" />Upload New Image</label>
                                    <input type="file" class="form-control-file" id="fileImage">
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-8">
                        <!-- Account details card-->
                        <div class="card mb-4">
                            <div class="card-header">${club.clubName}'s Details</div>


                            <div class="card-body">
                                <form action="clubprofile" id="editForm" method="post">
                                    <input type="hidden" name="clubId" value="${club.clubId}">
                                    <!-- Form Group (username)-->
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputUsername">Club's Name </label>
                                        <input class="form-control" id="inputUsername" type="text"
                                               placeholder="${club.clubName}" name="clubName">
                                    </div>
                                    <!-- Form Row-->
                                    <div class="mb-3">
                                        <!-- Form Group (Name)-->
                                        <div class="form-group">
                                            <label for="description">Desciption</label>
                                            <textarea class="form-control form-control-descript" name="clubDescription"
                                                      id="description" rows="7" type="text"
                                                      placeholder="${club.clubDescription}"></textarea>
                                        </div>
                                    </div>
                                    <!-- Form Group (email address)-->
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputEmailAddress">Email address </label>
                                        <input class="form-control" id="inputEmailAddress" type="email"
                                               placeholder="${club.clubEmail}" name="clubEmail">
                                    </div>
                                    <!-- Form Row-->
                                    <div class="row gx-3 mb-3">
                                        <!-- Form Group (phone number)-->
                                        <div class="form-group col-md-5">
                                            <label for="category" class="small mb-1">Category</label>
                                            <select class="form-control" name="categoryId" id="category" type="text">
                                                <option value="1">Academic</option>
                                                <option value="2">Music & Art</option>
                                                <option value="3">Sport</option>
                                                <option value="4">Social & Media</option>
                                                <option value="5">Community</option>
                                            </select>
                                        </div>

                                    </div>
                                    <!-- Save changes and delete club buttons-->
                                    <button id="saveChangesBtn" class="btn btn-primary" type="submit" name="action" value="update">Save changes</button>
                                    <button  class="btn btn-danger" type="submit" name="action" value="delete" onclick="return confirm('Are you sure to delete?')" >Delete club</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <%@ include file="/includes/footer2.jsp" %>

<!--    

    </body>

</html>



