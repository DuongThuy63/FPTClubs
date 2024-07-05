<%-- 
    Document   : club
    Created on : 12 Mar 2024, 10:45:45
    Author     : minhdq
--%>

<%@page import="model.*, dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/header.jsp"%>

<!DOCTYPE html>
<html>

    <div class="breadcrumb-wrapper">
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <div class="text-center">
                        <h2 class="lg-title">Club's Activites</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <c:set var="club" value="${ClubDAO.getById(param.club_id)[0]}" />
    <c:set var="articleList" value="${ArticleDAO.getListArticle(param.club_id)}" />


    <section class="section-padding">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-md-12 col-sm-12 col-xs-12">
                    <div class="row">
                        <c:set var="page" value="${param.page}"/>
                        <c:choose>
                            <c:when test="${articleList.size() <= 8}">
                                <c:set var="pagesize" value="${articleList.size()}" />
                            </c:when>
                            <c:otherwise>
                                <c:set var="pagesize" value="8" />
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${empty page}">

                            <c:set var="page" value="1"/>
                        </c:if>


                        <c:if test="${articleList.size() > 0}">
                            <c:forEach var="s" begin="${(page-1)*pagesize}" end="${page*pagesize-1}">

                                <div class="col-lg-6 col-md-6">
                                    <article class="post-grid mb-5">

                                        <div class="post-thumb mb-4">
                                            <img src="${articleList[s].image}" alt="${articleList[s].articleID}'s Image" class="img-fluid w-100">
                                        </div>
                                        <h3 class="post-title mt-1"><a href="article?article_id=${articleList[s].articleID}&club_id=${club.clubId}">${articleList[s].title}</a></h3>

                                        <span class=" text-muted  text-capitalize">${articleList[s].dateCreate}</span>

                                    </article>
                                </div>
                            </c:forEach>
                        </c:if>


                    </div>

                    <c:if test="${articleList.size() > 0}">
                        <div class="m-auto">

                            <div class="pagination mt-5 pt-4 justify-content-center">
                                <c:set var="currentPage" value="${param.page != null ? param.page : 1}"/>

                                <c:set var="totalPages" value="${(articleList.size() + pagesize - 1) / pagesize}"/>
                                <ul class="list-inline">
                                    <li class="list-inline-item">
                                        <c:if test="${(currentPage > 1)}">
                                            <a href="?page=${currentPage - 1 }">&laquo;</a> <!-- Previous page button -->
                                        </c:if> 
                                    </li>

                                    <c:forEach var="i" begin="1" end="${totalPages}">
                                        <c:if test="${(i >= (currentPage - 1) && i <= (currentPage + 1)) || (i % totalPages == 0)}">
                                            <li class="list-inline-item">
                                                <a href="?page=${i}" ${i == currentPage ? 'class="active"' : ''}>${i}</a>
                                            </li>
                                        </c:if>
                                    </c:forEach>

                                    <li class="list-inline-item">
                                        <c:if test="${(currentPage < totalPages) &&(currentPage + 1 < totalPages)}">
                                            <a href="?page=${currentPage + 1}">&raquo;</a> <!-- Next page button -->
                                        </c:if>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </c:if>
                </div>
                <%@include file="/includes/club_right_bar.jsp"%>
            </div>
        </div>

    </section>
</html>
<%@include file="/includes/footer2.jsp"%>
