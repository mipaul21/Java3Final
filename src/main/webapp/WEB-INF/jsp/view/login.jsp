<%-- 
    Document   : login
    Created on : May 6, 2021, 12:26:28 PM
    Author     : Mitchell Paul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Application</title>
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
            <p><a href="<c:url value="/login" />">Login</a></p>
        </div>


        <h2>Login</h2>
        <%
            if ((Boolean) request.getAttribute("loginFailed")) {
        %>
        <p style="font-weight: bold;">The username or password you entered are not correct. Please try again.</p>
        <%
        } else {
        %>
        <p>You must log in to access the application site.</p>
        <%
            }
        %>



        <form method="POST" action="<c:url value="/login" />">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" /><br><br>
            <label for="password">Password</label>
            <input type="password" name="password" id="password" /><br><br>
            <input type="submit" value="Log In" />
        </form>
    </body>
</html>
