<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="model.*, dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/includes/header.jsp"%>
<jsp:useBean id="random" class="java.util.Random" scope="application" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="club" value="${ClubDAO.getById(param.club_id)[0]}" />
        <c:set var="article_post" value="${ArticleDAO.getPostById(param.article_id)}" />
        <c:set var="article_list" value="${ArticleDAO.getAllPosts()}" />
        <section class="single-block-wrapper section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-md-12 col-sm-12 col-xs-12">
                        <div class="single-post">
                            <div class="post-header mb-5 text-center">
                                <div class="meta-cat">
                                    <!--                                <a class="post-category font-extra text-color text-uppercase font-sm letter-spacing-1"
                                                                        href="#">Health ,</a>
                                                                    <a class="post-category font-extra text-color text-uppercase font-sm letter-spacing-1"
                                                                        href="#">lifestyle</a>-->
                                </div>
                                <h2 class="post-title mt-2">
                                    ${article_post.title} 
                                </h2>

                                <div class="post-meta">
                                    <span class="text-uppercase font-sm letter-spacing-1 mr-3">${article_post.username}</span>
                                    <span class="text-uppercase font-sm letter-spacing-1">${article_post.dateCreate}</span>
                                </div>

                            </div>
                            <div class="post-body">
                                <div class="entry-content">
                                    ${article_post.content}
                                    <div class="row">
                                        <div class="col-lg-12 col-md-6" style="padding-top: 50px">
                                            <img src="${article_post.image}"
                                                 class="img-fluid mr-4 w-100">
                                        </div>

                                    </div>
                                </div>

                                <div
                                    class="tags-share-box center-box d-flex text-center justify-content-between border-top border-bottom py-3">


                                    <div class="post-share">
                                        <span class="count-number-like">2</span>
                                        <a class="penci-post-like single-like-button"><i class="ti-heart"></i></a>
                                    </div>

                                    <div class="list-posts-share">
                                        <a target="_blank" rel="nofollow" href="#"><i class="ti-facebook"></i></a>
                                        <a target="_blank" rel="nofollow" href="#"><i class="ti-twitter"></i></a>
                                        <a target="_blank" rel="nofollow" href="#"><i class="ti-pinterest"></i></a>
                                        <a target="_blank" rel="nofollow" href="#"><i class="ti-linkedin"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="related-posts-block mt-5">
                            <h3 class="news-title mb-4 text-center">
                                You May Also Like
                            </h3>
                            <div class="row">
                                <c:if test="${article_list.size() > 0}">
                                    <c:forEach var="index" begin="0" end="2">
                                        <c:set var="randomIndex" value="${random.nextInt(article_list.size())}" />
                                        <div class="col-lg-4 col-md-4 col-sm-6">
                                            <div class="post-block-wrapper mb-4 mb-lg-0">
                                                <a href="article?article_id=${article_list[randomIndex].articleID}&club_id=${club.clubId}">
                                                    <img class="img-fluid" src="${article_list[randomIndex].image}" alt="post-thumbnail" />
                                                </a>
                                                <div class="post-content mt-3">
                                                    <h5>
                                                        <a href="article?article_id=${article_list[randomIndex].articleID}&club_id=${club.clubId}">
                                                            ${article_list[randomIndex].title}
                                                        </a>
                                                    </h5>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>

                        <div class="comment-area my-5">
                            <h3 class="mb-4 text-center">Comment</h3>
                            <h3 class="mb-4 text">${countAllComment} comments </h3>

                            <c:forEach items="${listAllComment}" var="r">
                                <div class="comment-area-box media"  >
                                    <c:forEach items="${listAllAcount}" var="a">
                                        <c:if test="${r.username == a.username }">
                                            <img alt="" src="${a.avatar}" class="avatar avatar-100 photo" width="100"
                                                 height="100">
                                        </c:if>
                                        <div class="media-body ml-4">
                                            <h4 class="mb-0">${a.username} </h4>
                                            <span class="date-comm font-sm text-capitalize text-color"><i
                                                    class="ti-time mr-2"></i>${r.date_Create}</span>

                                            <div class="comment-content mt-3">
                                                <p>${r.content}</p>
                                            </div>
                                            <!--                                <div class="comment-meta mt-4 mt-lg-0 mt-md-0">
                                                                                <a href="#" class="text-underline ">Reply</a>
                                                                            </div>-->


                                        </div>
                                    </c:forEach>



                                </div>
                            </c:forEach>


                            <!--                        <div class="comment-area-box media mt-5">
                                                        <img alt="" src="images/blog-user-3.jpg" class="mt-2 img-fluid float-left mr-3">
                            
                                                        <div class="media-body ml-4">
                                                            <h4 class="mb-0 ">John Doe </h4>
                                                            <span class="date-comm font-sm text-capitalize text-color"><i
                                                                    class="ti-time mr-2"></i>June 7, 2019 </span>
                            
                                                            <div class="comment-content mt-3">
                                                                <p>Some consultants are employed indirectly by the client via a consultancy staffing
                                                                    company. </p>
                                                            </div>
                                                            <div class="comment-meta mt-4 mt-lg-0 mt-md-0">
                                                                <a href="#" class="text-underline">Reply</a>
                                                            </div>
                                                        </div>
                                                    </div>
                            -->

                            <form class="comment-form mb-5 gray-bg p-5" id="comment-form">
                                <h3 class="mb-4 text-center">Leave a comment</h3>
                                <div class="row">
                                    <div class="col-lg-12">
                                        <textarea class="form-control mb-3" name="comment" id="comment" cols="30" rows="5"
                                                  placeholder="Comment"></textarea>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input class="form-control" type="text" name="name" id="name" placeholder="Name:">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input class="form-control" type="text" name="mail" id="mail" placeholder="Email:">
                                        </div>
                                    </div>
                                </div>

                                <button type="button" class="btn btn-primary" onclick="addReview(${detail.id})">Add a comment</button>
                            </form>

                        </div>
                    </div>      
                    <%@include file="/includes/club_right_bar.jsp"%>

                </div>
            </div>
        </section>

    </body>

    <%@ include file="/includes/footer2.jsp" %>

</html>
