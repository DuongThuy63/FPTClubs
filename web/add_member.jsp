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



                    <form class="input-group rounded" action="search_user" method="GET">
                        <input type="search" name="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
                        <input type="hidden" name="club_id" value="${clubId}" />
                        <button type="submit"  class="btn btn-primary btn-search ti-search" data-mdb-ripple-init></button>
                    </form>

                    <form class="comment-form mb-5 gray-bg p-5" id="comment-form" style="margin-top: 20px" action="add_member">
                        <h3 class="mb-4 text-center">List Of Members</h3>
                        <div class="m-auto">

                        </div>


                    </form>

                </div>


                <%@include file="/includes/club_right_bar.jsp"%>

            </div>
        </div>
    </section>

</html>
<%@include file="/includes/footer2.jsp"%>
