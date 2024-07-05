<%-- 
    Document   : club_right_bar
    Created on : 17 Mar 2024, 18:45:06
    Author     : minhdq
--%>
<%@page import="model.*, dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="club" value="${ClubDAO.getById(param.club_id)[0]}" />
    <c:set var="members" value="${UserDAO.getAllMembersByClubId(param.club_id)}" />

    
    <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
        <div class="sidebar sidebar-right">
            <div class="sidebar-wrap mt-5 mt-lg-0">
                <div class="sidebar-widget about mb-5 text-center p-3">
                    <div class="about-author">
                        <img src="${club.clubLogo}" alt="${club.clubId}'s Logo" class="img-fluid">
                    </div>
                    <h4 class="mb-0 mt-4">${club.clubName}</h4>
                    <p>${club.categoryName}</p>
                    <p>${club.clubDescription}</p>
                    <p>${club.clubEmail}</p>



                </div>


                <div class="sidebar-widget subscribe mb-5">
                    <a href="create_post?club_id=${club.clubId}" class="btn btn-primary d-block mt-3">Create post</a>
                </div>



                <div class="sidebar-widget category mb-5">
                    <h4 class="text-center widget-title">Members</h4>
                    <ul class="list-unstyled">
                        <c:forEach var="member" items="${members}" begin="0" end="5">
                            <li>${member.username} - ${member.fullName}</li> 
                        </c:forEach>
          
                        <a class="btn btn-primary d-block mt-3"  href="member_list?club_id=${club.clubId}">More</a>
                    </ul>

                </div>
            </div>
        </div>
    </div>
</html>
