<%-- 
    Document   : header
    Created on : 7 Mar 2024, 21:34:24
    Author     : minhdq
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

    <html lang="en">

        <head>
            <meta charset="utf-8">
            <title>FPTU Clubs</title>
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

            <!-- theme meta -->
            <meta name="theme-name" content="revolve" />

            <!--Favicon-->
            <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">

            <!-- THEME CSS
            ================================================== -->
            <!-- Bootstrap -->
            <link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.min.css">
            <!-- Themify -->
            <link rel="stylesheet" href="plugins/themify/css/themify-icons.css">
            <link rel="stylesheet" href="plugins/slick-carousel/slick-theme.css">
            <link rel="stylesheet" href="plugins/slick-carousel/slick.css">
            
            <!-- Slick Carousel -->
            <link rel="stylesheet" href="plugins/owl-carousel/owl.carousel.min.css">
            <link rel="stylesheet" href="plugins/owl-carousel/owl.theme.default.min.css">
            <link rel="stylesheet" href="plugins/magnific-popup/magnific-popup.css">
            <!-- manin stylesheet -->
            <link rel="stylesheet" href="css/style.css">

        </head>

        <body>

            <header class="header-top bg-grey justify-content-center">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-lg-2 col-md-4 text-center d-none d-lg-block">
                            <a class="navbar-brand " href="index">
                                <img src="images/logo.png" alt="" class="img-fluid">
                            </a>
                        </div>

                        <div class="col-lg-8 col-md-12">
                            <nav class="navbar navbar-expand-lg navigation-2 navigation">
                                <a class="navbar-brand text-uppercase d-lg-none" href="#">
                                    <img src="images/logo.png" alt="" class="img-fluid" style="max-width: 10%;">
                                </a>
                                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-collapse"
                                        aria-controls="navbar-collapse" aria-expanded="false" aria-label="Toggle navigation">
                                    <span class="ti-menu"></span>
                                </button>

                                <div class="collapse navbar-collapse" id="navbar-collapse">
                                    <ul id="menu" class="menu navbar-nav mx-auto">
                                        <li class="nav-item"><a href="index" class="nav-link">Home</a></li>


                                                <li class="nav-item dropdown">
                                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown3" role="button" data-toggle="dropdown"
                                               aria-haspopup="true" aria-expanded="false">
                                                Categories
                                            </a>
                                            <div class="dropdown-menu" aria-labelledby="navbarDropdown3">
                                                <a class="dropdown-item" href="category?categoryID=1">Academic</a>
                                                <a class="dropdown-item" href="category?categoryID=2">Music & Art</a>
                                                <a class="dropdown-item" href="category?categoryID=3">Sport</a>
                                                <a class="dropdown-item" href="category?categoryID=4">Social & Media</a>
                                                <a class="dropdown-item" href="category?categoryID=5">Community</a>
                                            </div>
                                        </li>
                                        
                                        <li class="nav-item dropdown">
                                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown"
                                               aria-haspopup="true" aria-expanded="false">
                                                Your Club
                                            </a>
                                            <div class="dropdown-menu" aria-labelledby="navbarDropdown2">
                                                <a class="dropdown-item" href="create_club">Create Clubs</a>
                                                <a class="dropdown-item" href="your_club">Your Clubs</a>
                                            </div>
                                        </li>
                                        
                                            <li class="nav-item dropdown">
                                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown"
                                               aria-haspopup="true" aria-expanded="false">
                                                Profile
                                            </a>
                                            <div class="dropdown-menu" aria-labelledby="navbarDropdown2">
                                                <a class="dropdown-item" href="userprofile">User Profile</a>
                                                <a class="dropdown-item" href="clubprofile">Club Profile</a>
                                            </div>
                                        </li>
                                        
                                     

                                    </ul>


                                </div>
                            </nav>
                        </div>

                        <c:set var="user" value="${sessionScope.account}" />
                        <%--<c:set var="isLoggedIn" value="${not empty user}" />--%>

                        <c:choose>
                            <c:when test="${user != null}">
                                <a class="btn-white btn btn-primary m-auto" href="logout">Logout</a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn-white btn btn-primary m-auto" href="login">Login / Register</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </header>
        </body>

    </html>
