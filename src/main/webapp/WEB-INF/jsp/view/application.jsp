<%-- 
    Document   : application
    Created on : May 6, 2021, 12:37:54 PM
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
    <div class="navigation" style="
         border-style: solid;
         box-sizing: border-box;
         left: 0;
         background-color: aquamarine;
         ">  
        <p><a href="<c:url value="/jobs" />">Browse jobs</a></p>
        <p><a href="<c:url value="/applications" />">Browse applications</a></p>

    </div>


    <div class="applicant" style="
         float: left;
         width: 300px;
         margin: 50px;
         text-align: left;
         border-style: solid;
         background-color: lightpink;"

         >
        <li>
            <label> Job Title: <c:out value="${application.title}"/>   </label>
            <p>_____________________________</p>

            <br>
            <label> Applicant Name: <c:out value="${application.firstName}"/>&nbsp<c:out value="${application.lastName}"/> </label>
            <p>_____________________________</p>

            <br>
            <label> Applicant Email: <c:out value="${application.email}"/> </label>
            <p>_____________________________</p>

            <br>
            <label> Applicant Phone Number: <c:out value="${application.phone}"/>
                <p>_____________________________</p>

                <br>
                <br>${application.attachmentName}&nbsp;
                <a href="
                   <c:url value="/applications">
                       <c:param name="action" value="download" />
                       <c:param name="id" value="${application.id}" />
                       <c:param name="attachment" value="${application.attachmentName}" />

                   </c:url>"
                   > Download</a>
                <p>_____________________________</p>

                <br><br>
                <label> Desired Salary: 
                             <fmt:setLocale value = "en_US"/>
         <fmt:formatNumber value = "${application.desiredSalary}" type = "currency"/> </label>
                <br>
                <p>_____________________________</p>

                <label> Applicant Applied On: <c:out value="${application.startDate}"/> </label>
                <br>
                </li>
                </div>

                <h2>Disqualify Candidate</h2>
                <form method="get" action="applications" enctype="multipart/form-data">
                    <input type="hidden" name="action" value="disqualify" />
                    <input type ="hidden" id="Id" name ="Id" value="${application.id}">
                    <input type="submit" value="Submit"/>
                </form>









                </html>
