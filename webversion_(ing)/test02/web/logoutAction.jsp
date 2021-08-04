<%-- 
    Document   : loginAction
    Created on : 2021. 7. 29, 오전 11:00:31
    Author     : hwnim
--%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plus Game</title>
    </head>
    <body>
        <%
            session.invalidate();
        %>
        <script>location.href='login.jsp';</script>
    </body>
</html>
