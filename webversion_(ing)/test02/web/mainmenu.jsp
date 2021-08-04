<%-- 
    Document   : mainmenu
    Created on : 2021. 7. 29, 오전 10:01:15
    Author     : hwnim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="plusgame.option" %>
<%@page import="plusgame.optionDAO" %>
<!DOCTYPE html>
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
        String userId=null;
        if(session.getAttribute("userId")!=null){
            userId=(String)session.getAttribute("userId");
        }
        else{
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("location.href='login.jsp'");
            script.println("</script>");
        }
        optionDAO optiondao = new optionDAO();
        option useroption = optiondao.getoption(userId);
    %>
    
    
    <div class="ui middle aligned center aligned grid">
        <div class="column">
            <h2>
                <div class="content">
                    <p class="logo">Plus Game</p>
                </div>
            </h2>
            <form class="ui large form">
                
                <span class="level">난이도 : <%= useroption.getUserLevel()%> &ensp;&ensp;&ensp;&ensp;&ensp;문항수 : <%= useroption.getUserNumofquest()%></span>

                <a href="playgame.jsp">
                    <div class="ui inverted blue button" style="width: 100%; margin-top: 20px; margin-bottom: 20px;">
                        <p>게임 시작</p>
                    </div>
                </a>
                <a href="showscore.jsp">
                    <div class="ui inverted blue button" style="width: 100%; margin-bottom: 20px;">
                        <p>점수 보기</p>
                    </div>
                </a>
                <a href="setoption.jsp">
                    <div class="ui inverted blue button" style="width: 100%; margin-bottom: 20px;">
                        <p>환경 설정</p>
                    </div>
                </a>
                <a href="logoutAction.jsp">
                    <div class="ui inverted blue button" style="width: 100%; margin-bottom: 20px;">
                        <p>로그아웃</p>
                    </div>
                </a>
            </form>
        </div>
    </div>



</body>

</html>