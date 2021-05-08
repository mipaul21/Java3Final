<%-- 
    Document   : applicationListings
    Created on : May 6, 2021, 8:55:10 PM
    Author     : Mitchell Paul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="styles/main.css" rel="stylesheet">

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
        <p><a href="<c:url value="login?logout" />">Logout</a></p>

    </div>

        <c:forEach items="${applications}" var="applications" begin="${begin}" end="${end}">

            <c:if test="${applications.value.active == true}">

                <div class="applications">
                    <li><a href="
                           <c:url value="/applications">
                               <c:param name="action" value="viewApplication" />
                               <c:param name="Id" value="${applications.value.id}" />
                           </c:url>"><c:out value="${(applications.value.title)}"/>&nbsp;&nbsp; | <c:out value="${(applications.value.firstName)}"/>&nbsp;&nbsp;<c:out value="${applications.value.lastName}"/>, <c:out value="${applications.value.email}"/> </p>
                </div>
            </c:if>
        </c:forEach>





    </body>
</html>