<%-- 
    Document   : member_list
    Created on : 15 Mar 2024, 22:29:36
    Author     : minhdq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/header.jsp"%>

<!DOCTYPE html>
<html>
    <section class="single-block-wrapper section-padding">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-md-12 col-sm-12 col-xs-12">

                    <c:set var="page" value="${param.page}"/>
                    <c:set var="pagesize" value="10"/> <!-- Thay đổi pagesize thành 10 -->
                    <c:if test="${empty page}">
                        <c:set var="page" value="1"/>
                    </c:if>
                    <c:set var="clubId" value="${param.club_id}" />

                    <c:set var="list" value="${UserDAO.getAllMembersByClubId(clubId)}"/>
                    <div>
                        <a type="button" class="btn btn-primary d-block mt-3 btn-login" href="add_member?club_id=${clubId}">Add member</a>
                    </div>


                    <form class="comment-form mb-5 gray-bg p-5" id="comment-form">
                        <h3 class="mb-4 text-center">List Of Members</h3>
                        <c:forEach var="member" items="${members}"  begin="${(page-1)*pagesize}" end="${page*pagesize-1}">
                            <li>${member.username} - ${member.fullName}</li> 
                            </c:forEach>

                        <div class="m-auto">

                            <div class="pagination mt-5 pt-4 justify-content-center">
                                <c:set var="currentPage" value="${param.page != null ? (param.page < 1 ? 1 : param.page) : 1}"/>

                                <c:set var="totalPages" value="${(list.size() + pagesize - 1) / pagesize}"/>
                                <ul class="list-inline">
                                    <li class="list-inline-item">
                                        <c:if test="${(currentPage > 1)}">
                                            <a href="member_list?club_id=${clubId}&page=${currentPage - 1}">&laquo;</a> <!-- Previous page button -->
                                        </c:if> 
                                    </li>


                                    <c:forEach var="i" begin="1" end="${totalPages}">
                                        <c:if test="${i == 1 || (i >= (currentPage - 1) && i <= (currentPage + 1)) || (i % totalPages == 0)}">

                                            <li class="list-inline-item">
                                                <a href="member_list?club_id=${clubId}&page=${i}" ${i == currentPage ? 'class="active"' : ''}>${i}</a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                    <li class="list-inline-item">
                                        <c:if test="${(currentPage < totalPages) &&(currentPage + 1 < totalPages)}">
                                            <a href="member_list?club_id=${clubId}&page=${currentPage + 1}">&laquo;</a> <!-- Previous page button -->
                                        </c:if>
                                    </li>

                                </ul>
                            </div>
                        </div>
                    </form>
                </div>

                <%@include file="/includes/club_right_bar.jsp"%>

            </div>
        </div>
    </section>
    <%@include file="/includes/footer2.jsp"%>

</html>
