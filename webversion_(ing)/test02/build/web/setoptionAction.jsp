<%-- 
    Document   : setoption
    Created on : 2021. 7. 29, 오전 10:45:04
    Author     : hwnim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="plusgame.optionDAO" %>
<%@page import="plusgame.option" %>
<%@page import="java.io.PrintWriter" %>
<jsp:useBean id="user" class="plusgame.option" scope="page" />
<jsp:setProperty name="user" property="userNumofquest" />
<jsp:setProperty name="user" property="userLevel" />
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="main.css">
        <link rel="stylesheet" type="text/css" href="semantic/semantic.min.css">
        <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
        <script src="http://code.jquery.com/jquery-1.6.js"></script>
        <script src="semantic/semantic.min.js"></script>

        <title>Plus Game</title>

        <style type="text/css">
            body>.grid {
                height: 100%;
            }

            .image {
                margin-top: -100px;
            }

            .column {
                max-width: 25%;
            }
        </style>
    </head>

    <body>

        <%
            String userId = null;
            if (session.getAttribute("userId") != null) {
                userId = (String) session.getAttribute("userId");
            } else {
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("location.href='login.jsp'");
                script.println("</script>");
            }

            optionDAO optiondao = new optionDAO();
            if (optiondao.getoption(userId) == null) {
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("alert('유효하지 않은 접근입니다.')");
                script.println("history.back()");
                script.println("</script>");
            } else {
                user.setUserId(userId);
                optiondao.setoption(user);
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("alert('환경설정 완료.')");
                script.println("location.href='mainmenu.jsp'");
                script.println("</script>");
            }
        %>

    </body>

</html>