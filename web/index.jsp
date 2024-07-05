<%-- 
    Document   : index
    Created on : 7 Mar 2024, 21:39:09
    Author     : minhdq
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.*, dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/header.jsp"%>

<!DOCTYPE html>
<html>

    <body>

        <div class="breadcrumb-wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="text-center">
                            <h1 class="lg-title">TOP CLUBS</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <section class="slider mt-4">
            <div class="container-fluid">
                <div class="row no-gutters">
                    <div class="col-lg-12 col-sm-12 col-md-12 slider-wrap">
                        <c:set var="list" value="${ClubDAO.getAll()}"/>
             
                        <c:forEach var="s" begin="0" end="5">

                            <div class="slider-item">
                                <div class="slider-item-content">
                                    <div class="post-thumb mb-4">
                                        <a href="club?club_id=${list[s].clubId}">
                                            <img src="${list[s].clubLogo}" alt="${list[s].clubId}'s Logo" class="img-fluid img-crop-slider">
                                        </a>
                                    </div>

                                    <div class="slider-post-content">
                                        <span class="cat-name text-color font-sm font-extra text-uppercase letter-spacing">${list[s].categoryName}</span>
                                        <h3 class="post-title mt-1"><a href="club?club_id=${list[s].clubId}">${list[s].clubName} </a></h3>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>                     



                    </div>
                </div>
            </div>
        </section>

        <section class="section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="row">
                            <c:set var="page" value="${param.page}"/>
                            <c:choose>
                                <c:when test="${list.size() <= 8}">
                                    <c:set var="pagesize" value="${list.size()}" />
                                </c:when>
                                <c:otherwise>
                                    <c:set var="pagesize" value="8" />
                                </c:otherwise>
                            </c:choose>
                            
                            <c:if test="${empty page}">

                                <c:set var="page" value="1"/>
                            </c:if>
                            <%--<c:set var="list" value="${ClubDAO.getAll()}"/>--%>
                            <%--<c:if test="${not empty Club}">--%>


                            <c:forEach var="s" begin="${(page-1)*pagesize}" end="${page*pagesize-1}">
                                <div class="col-lg-3 col-md-6">
                                    <article class="post-grid mb-5">
                                        <a class="post-thumb mb-4 d-block" href="club?club_id=${list[s].clubId}">
                                            <img src="${list[s].clubLogo}" alt="${list[s].clubId}'s Logo" class="img-fluid w-100">
                                        </a>
                                        <span class="cat-name text-color font-extra text-sm text-uppercase letter-spacing-1">${list[s].categoryName}</span>
                                        <h3 class="post-title mt-1">
                                            <a href="club?club_id=${list[s].clubId}">${list[s].clubName}</a>
                                        </h3>

                                        <span class="text-muted letter-spacing font-sm">${list[s].clubEmail}</span>

                                    </article>
                                </div>

                            </c:forEach>
                        </div>
                    </div>
                </div>

                <div class="m-auto">

                    <div class="pagination mt-5 pt-4 justify-content-center">
                        <c:set var="currentPage" value="${param.page != null ? param.page : 1}"/>

                        <c:set var="totalPages" value="${(list.size() + pagesize - 1) / pagesize}"/>
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
            </div>
        </div>
    </section>
</body>
<%@ include file="/includes/footer.jsp" %>
