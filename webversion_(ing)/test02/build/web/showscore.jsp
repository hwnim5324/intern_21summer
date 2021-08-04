<%-- 
    Document   : showscore
    Created on : 2021. 7. 29, 오전 10:43:28
    Author     : hwnim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="plusgame.scoreDAO"%>
<%@page import="plusgame.score"%>
<%@page import="java.util.ArrayList"%>

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
            }else{
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("location.href='login.jsp'");
                script.println("</script>");
            }
        %>

        <div class="ui middle aligned center aligned grid">
            <div class="column">
                <h2>
                    <div class="content">
                        <p class="logo">Plus Game</p>
                    </div>
                </h2>
                <form class="ui large form" method="post" action="detailscore.jsp">

                    <table class="ui selectable called table" style="margin-top: 0.7cm;">
                        <thead>
                            <tr>
                                <th class="center aligned">Rank</th>
                                <th class="center aligned">ID</th>
                                <th class="center aligned">Score</th>
                                <th class="center aligned">Time</th>
                            </tr>
                        </thead>
                        <tbody>

                            <%
                                scoreDAO scoredao = new scoreDAO();
                                ArrayList<score> list = scoredao.getRankList();
                                for(int i=0;i<list.size();i++){
                            %>
                            <tr onclick="location.href = 'detailscore.jsp?qCode=<%= list.get(i).getqCode() %>'" style="cursor:pointer">
                                <td class="center aligned"><%= i + 1 %></td>
                                <td class="center aligned"><%= list.get(i).getUserId() %></td>
                                <td class="center aligned"><%= list.get(i).getScore() %></td>
                                <td class="center aligned"><%= list.get(i).getTime() %></td>
                            </tr>
                            <%
                                }
                            %>
                            </tbody>
                    </table>
                            
                            
                            <table class="ui selectable called table" style="margin-top: 0.7cm;">
                        <thead>
                            <tr>
                                <th class="center aligned">Rank</th>
                                <th class="center aligned">Score</th>
                                <th class="center aligned">Time</th>
                            </tr>
                        </thead>
                        <tbody>
                            
                            <%
                                ArrayList<score> mylist = scoredao.getMyList(userId);
                                for(int i=0;i<mylist.size();i++){
                            %>
                            <tr onclick="location.href = 'detailscore.jsp?qCode=<%= mylist.get(i).getqCode() %>'" style="cursor:pointer">
                                <td class="center aligned"><%= i + 1 %></td>
                                <td class="center aligned"><%= mylist.get(i).getScore() %></td>
                                <td class="center aligned"><%= mylist.get(i).getTime() %></td>
                            </tr>
                            <%
                                }
                            %>

                        </tbody>
                    </table>


                    <a href="mainmenu.jsp">
                        <div class="ui blue button" style="width: 100%; margin-top: 30px; margin-bottom: 20px;">
                            <p>돌아가기</p>
                        </div>
                    </a>
                </form>
            </div>
        </div>



    </body>

</html>