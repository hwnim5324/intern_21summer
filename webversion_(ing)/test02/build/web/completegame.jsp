<%-- 
    Document   : completegame
    Created on : 2021. 7. 29, 오전 10:09:43
    Author     : hwnim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="plusgame.GameMaker"%>
<%@page import="plusgame.optionDAO" %>
<%@page import="plusgame.option" %>
<%@page import="plusgame.questionsDAO" %>
<%@page import="plusgame.questions" %>
<%@page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="main.css">
    <link rel="stylesheet" type="text/css" href="semantic/semantic.min.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
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


            long startTime, endTime;
            endTime = System.currentTimeMillis();
            startTime = (long)session.getAttribute("startTime");
            String time = Integer.toString((int)(((endTime - startTime) / 1000))) + ".";
            time += Integer.toString((int)((((endTime - startTime) % 1000) / 10)));
                
            questionsDAO questionsdao = new questionsDAO();
            int qCode = questionsdao.recentqCode();
        %>
        
        <script>
            var qNumber = (int)sessionStorage.getItem("qNumber");
            
        </script>
    

    
    <div class="ui middle aligned center aligned grid">
        <div class="column">
            <h2>
                <div class="content">
                    <p class="logo">Plus Game</p>
                </div>
            </h2>
            <form class="ui large form">
                <h1 style="font-size:1.2cm; margin-top: 20px;">NAME : <%= %></h1>
                <h1 style="font-size:1.2cm;">SCORE : <%= %></h1>
                <h1 style="font-size:1.2cm;">TIME : <%=time %></h1>
                <a href="mainmenu.jsp">
                    <div class="ui blue button" style="width: 100%; margin-top: 30px; margin-bottom: 20px;">
                        <p>완료</p>
                    </div>
                </a>
            </form>
        </div>
    </div>



</body>

</html>