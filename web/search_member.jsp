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
                     <c:set var="searchInput" value="${param.search}" />
                    <form class="input-group rounded" action="search_user?club_id=${clubId}" method="GET">
                        <input type="search" name="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
                        <button type="submit"  class="btn btn-primary btn-search ti-search" data-mdb-ripple-init></button>
                    </form>

                    <form class="comment-form mb-5 gray-bg p-5" id="comment-form" style="margin-top: 20px" action="add_member" method="post">
                        <input type="hidden" name="clubId" value="${clubId}">
                        <h3 class="mb-4 text-center">List Of Members</h3>
                        <div class="m-auto">


                            <c:if test="${not empty searchList}">
                                <ul>
                                    <c:forEach var="member" items="${searchList}" begin="${(page-1)*pagesize}" end="${page*pagesize-1}">
                                        <li class="result-item">
                                            <label>${member.username} - ${member.fullName} </label> 
                                            <input type="checkbox" name="selectedUsers" value="${member.username}">
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:if>

                            <div class="pagination mt-5 pt-4 justify-content-center">
                                <c:set var="currentPage" value="${param.page != null ? (param.page < 1 ? 1 : param.page) : 1}"/>

                                <c:set var="totalPages" value="${(list.size() + pagesize - 1) / pagesize}"/>
                                <ul class="list-inline">
                                    
                                    <li class="list-inline-item">
                                        <c:if test="${(currentPage > 1)}">
                                            <a href="search_member?club_id=${clubId}&search=${searchInput}&page=${currentPage - 1}">&laquo;</a> <!-- Previous page button -->
                                        </c:if> 
                                    </li>


                                    <c:forEach var="i" begin="1" end="${totalPages}">
                                        <c:if test="${i == 1 || (i >= (currentPage - 1) && i <= (currentPage + 1)) || (i % totalPages == 0)}">

                                            <li class="list-inline-item">
                                                <a href="search_member?club_id=${clubId}&search=${searchInput}&page=${i}" ${i == currentPage ? 'class="active"' : ''}>${i}</a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                    <li class="list-inline-item">
                                        <c:if test="${(currentPage < totalPages) &&(currentPage + 1 < totalPages)}">
                                            <a href="search_member?club_id=${clubId}&search=${searchInput}&page=${currentPage + 1}">&laquo;</a> <!-- Previous page button -->
                                        </c:if>
                                    </li>
                                </ul>
                            
                            </div>
                                
                            <c:if test="${empty searchList}">
                                <p>No members found.</p>
                            </c:if>
                        </div>
                                
                        <button type="submit" class="btn btn-primary">Add members</button>

                    </form>

                </div>

                <%@include file="/includes/club_right_bar.jsp"%>


            </div>
        </div>
    </section>

</html>
<%@include file="/includes/footer2.jsp"%>
