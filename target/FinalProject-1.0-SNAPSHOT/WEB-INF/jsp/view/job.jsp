<%-- 
    Document   : Job
    Created on : May 6, 2021, 11:42:36 AM
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
        <p><a href="<c:url value="/login" />">Login</a></p>
    </div>


    <body>
        <div class="jobDescription" style="
             float: left;
             width: 300px;
             margin: 50px;
             text-align: left;
             border-style: solid;
             background-color: lightpink;"
             >



            <label> Job ID: <c:out value="${job.id}"/> </label>
            <p>_____________________________</p>

            <br>
            <label>Location:  <c:out value="${job.city}"/>,  <c:out value="${job.state}"/>  </label>
            <p>_____________________________</p>

            <br>

            <c:if test="${job.fullTime == true}">
                <label>Employment: Full-time</label>   
                <p>_____________________________</p>

            </c:if>
            <c:if test="${job.fullTime == false}"> 
                <label>Employment: Part-time</label>
                <p>_____________________________</p>


            </c:if>   


            <br>
            <label>Department: <c:out value="${job.department}"/> </label>
            <p>_____________________________</p>
            <label>Experience Required: <c:out value="${job.experience}"/> </label>
            <br>
            <p>_____________________________</p>
            <c:if test="${job.wageCategory == 'Salary'}"> 
                <label>Salary: 
                    <c:out value="${job.salary}"/> 
                </c:if>
                <c:if test="${job.wageCategory == 'Hourly'}"> 
                    <label>Hourly: $</label>
                    <c:out value="${job.salary}"/>
                </c:if>


            </label>
        </div>
        <div class="jobAppForm" style="
             float: right;
             width: 400px;
             text-align: left;
             margin-left: 200px;
             margin-right: 400px;
             border-style: solid;
             box-sizing: border-box;
             background-color: lightpink;
             ">

            <h2>Apply for <c:out value="${job.title}"/></h2>
            <form method="POST" action="applications" enctype="multipart/form-data">
                <input type="hidden" name="action" value="submitApplication" />
                <input type ="hidden" name ="jobId" value="${job.id}">
                <input type ="hidden" name ="jobTitle" value="${job.title}"><br><br>
                <label for="firstName">First Name</label><br>
                <input type="text" name="firstName" id="firstName" required value="${application.firstName}"/><br>
                <p class="error"><c:out value="${application.firstNameError}"></c:out></p>
                    <br>
                    <label for="lastName">Last Name</label><br>
                    <input type="text" name="lastName" id="lastName" value="${application.lastName}" required/><br>
                <p class="error"><c:out value="${application.lastNameError}"></c:out></p>
                    <br>
                    <label for="email">Email Address:</label><br>
                    <input type="text" name="email" id="email" value="${application.email}" required/><br>
                <p class="error"><c:out value="${application.emailError}"></c:out></p>
                    <br>
                    <label for="phoneNumber">Phone Number:</label><br>
                    <input type="text" name="phoneNumber" id="phoneNumber" value="${application.phone}" required/><br>   
                <p class="error"><c:out value="${application.phoneError}"></c:out></p>
                    <br>
                    <label for="desiredSalary">Desired Salary</label><br>
                    <input type="number" step="0.01" min="0" name="desiredSalary" id="desiredSalary" value="${application.desiredSalary}" required/><br>
                <p class="error"><c:out value="${application.salaryError}"></c:out></p>
                    <br>
                    <label for="startDate">Earliest Start Date</label><br>
                    <input type="date" id="startDate" name="startDate" min="2018-06-07T00:00" value="${application.startDate}">    
                <br>
                <p class="error"><c:out value="${application.startDateError}"></c:out></p>
                <br>
                <label for="resume">Resume Upload</label><br>
                <input type="file" name="resume" id="resume" /><br><br>
                <input type="submit" value="Submit"/>
            </form>

        </div>



    </body>
</html>
