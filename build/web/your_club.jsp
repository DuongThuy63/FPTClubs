<%-- 
    Document   : your_club
    Created on : 13 Mar 2024, 23:38:08
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
                        <h1 class="lg-title padding-bottom">Your Clubs</h1>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <section >
        <c:set var="list" value="${clubList}"/>

        <div class="container-fluid">
            <div class="row">
                <c:forEach var="s" begin="0" end="${list.size() - 1}">
                <div class="col-lg-3 col-md-6">
                    <div class="slider-item-content">
                        <div class="post-thumb mb-4">
                            <a href="club?club_id=${list[s].clubId}">
                                <img src="${list[s].clubLogo}" alt="${list[s].clubId}'s Logo" class="img-fluid img-crop-slider">
                            </a>
                        </div>

                        <div class="slider-post-content">
                            <span class="cat-name text-color font-sm font-extra text-uppercase letter-spacing">${list[s].categoryName}</span>
                            <h3 class="post-title mt-1">
                                <a href="club?club_id=${list[s].clubId}">${list[s].clubName}</a></a>
                            </h3>
                            <span class="text-muted text-capitalize">${list[s].clubEmail}</span>
                        </div>
                    </div>
                </div>
            </c:forEach>
            </div>
        </div>
    </section>
</html>
<%@include file="/includes/footer2.jsp"%>
