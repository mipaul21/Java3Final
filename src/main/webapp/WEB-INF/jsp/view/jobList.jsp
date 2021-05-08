<%-- 
    Document   : jobList
    Created on : Apr 23, 2021, 8:32:58 PM
    Author     : Mitchell Paul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="styles/main.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>JSP Page</title>
    </head>
    <body>
        <div class="navigation" style="
             border-style: solid;
             box-sizing: border-box;
             left: 0;
             background-color: aquamarine;
             ">   
            <p><a href="<c:url value="/jobs" />">Browse jobs</a></p>
            <p><a href="<c:url value="/applications" />">Browse applications</a></p>
            <p><a href="<c:url value="/login" />">Login</a></p>
        </div>
        <div class="container">
            <h2>Available Jobs</h2>

            <div class="jobs">
                <c:forEach items="${jobs}" var="jobs" begin="${begin}" end="${end}">
                    <div class="job">




                        <li><a href="
                               <c:url value="/jobs">
                                   <c:param name="action" value="viewJob" />
                                   <c:param name="Id" value="${jobs.id}" />



                               </c:url>"><c:out escapeXml="true" value="${(jobs.title)}"/> | &nbsp;<c:out value="${jobs.city}"/>, <c:out value="${jobs.state}"/> | &nbsp;<c:out value="${jobs.department}" /></p>
                    </div>
                </c:forEach>
            </div>

            <div class="jobPagination">
                <c:forEach var="i" begin="1" end="${maxPages}">
                    <a <c:if test="${currentPage == i}">class="active"</c:if> href="<c:url value="/jobs"><c:param name="page" value="${i}" /></c:url>">${i}</a>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
