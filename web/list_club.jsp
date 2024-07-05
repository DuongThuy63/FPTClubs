<%-- 
    Document   : list_club
    Created on : Mar 16, 2024, 11:28:15 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <body>

        <div>
            <table class="table table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>Logo</th>
                        <th>Club Name</th>
                        <th>Description</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="club" items="${cList}">
                    <tr>
                        <td><img src="${club.clubLogo}" class="rounded" style="width: 50px;height: 50px"></td>
                        <td>${club.clubName}</td>
                        <td>${club.clubDescription}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
<%@include file="/includes/footer2.jsp"%>
