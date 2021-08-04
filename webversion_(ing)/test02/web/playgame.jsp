<%-- 
    Document   : playgame
    Created on : 2021. 7. 29, 오전 10:07:36
    Author     : hwnim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="plusgame.questionsDAO"%>
<%@page import="plusgame.questions"%>
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

            int qNumber = 1;
            if (request.getParameter("qNumber") != null) {
                qNumber = Integer.parseInt(request.getParameter("qNumber"));
            }
            else{
                long startTime = System.currentTimeMillis();
                session.setAttribute("startTime",startTime);
            }
            questionsDAO questionsdao = new questionsDAO();
            int qCode = questionsdao.recentqCode();
        %>

        <div class="ui middle aligned center aligned grid">
            <div class="column">
                <h2>
                    <div class="content">
                        <p class="logo">Plus Game</p>
                    </div>
                </h2>
                <form class="ui large form" method="post">

                    <div style=" margin-top: 20px; margin-bottom: 50px;">
                        <span style="font-size:1.2cm; margin-right: 0.4cm;"><%=questionsdao.getquestion(qCode, qNumber)%></span>
                        <div class="ui input">
                            <input type="text" id="userAnswer" name ="userAnswer" style="height: 1.4cm; width: 3.5cm;" placeholder="답" autofocus>
                        </div>
                    </div>
                    <%
                        if (questionsdao.nextquestion(qCode, qNumber + 1)) {
                    %>
                    <a href="playgame.jsp?qNumber=<%=qNumber + 1%>">
                        <div class="ui blue button" onclick="OnSubmit();" style="width: 100%; margin-bottom: 20px;">
                            <p>다음 문제</p>
                        </div>
                    </a>
                    <%
                    } else {
                    %>
                    <a href="completegame.jsp">
                        <div class="ui blue button" onclick="saveqNumber();" style="width: 100%; margin-bottom: 20px;">
                            <p>완료</p>
                        </div>
                    </a>
                    <%
                        }
                    %>
                </form>
            </div>
        </div>

        <script>
            function OnSubmit() {
                var userAnswer = document.querySelector('#userAnswer');
                window.sessionStorage['<%=qNumber%>'] = userAnswer.value;
            }

            $("form").on("submit", function (event) {
                event.preventDefault();
            });

            $(document).ready(function () {
                $("#userAnswer").keypress(function (e) {
                    if (e.which == 13) {
                        OnSubmit();
                        <%
                            if (questionsdao.nextquestion(qCode, qNumber + 1)) {
                                %>location.href="playgame.jsp?qNumber=<%=qNumber + 1%>"<%
                            }
                            else{
                                %>window.sessionStorage['qNumber'] = <%=qNumber%>;
                                location.href="completegame.jsp"<%
                            }
                        %>
                        
                    }
                });
            });

            function saveqNumber(){
                window.sessionStorage['qNumber']=<%=qNumber%>;
            }

        </script>



    </body>

</html>