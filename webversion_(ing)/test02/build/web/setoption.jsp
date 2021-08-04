<%-- 
    Document   : setoption
    Created on : 2021. 7. 29, 오전 10:45:04
    Author     : hwnim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="plusgame.optionDAO" %>
<%@page import="plusgame.option" %>
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
                option user = optiondao.getoption(userId);
            }
        %>


        <div class="ui middle aligned center aligned grid">
            <div class="column">
                <h2>
                    <div class="content">
                        <p class="logo">Plus Game</p>
                    </div>
                </h2>
                <form class="ui large form" method="post" action="setoptionAction.jsp">
                    <div>
                        <span style="font-size:0.8cm; margin-right: 4.5cm;">문항수</span>
                        <div class="ui input">
                            <input type="text" style="height: 1.2cm; width: 3.5cm; margin-top: 20px; margin-bottom: 50px;" name="userNumofquest" placeholder="입력">
                        </div>
                    </div>
                    <div>
                        <span style="font-size:0.8cm; margin-right: 2cm;">난이도</span>
                        <div class="ui buttons">
                            <label class="ui blue button inverted" id="high" style="height: 1.2cm; width: 2cm; margin-top: 20px; margin-bottom: 50px;">
                                <input type="radio" name="userLevel"id="high" value="상" id="high" style="visibilty:hidden; display:none;">상
                            </label>
                            <label class="ui blue button inverted" id="mid" style="height: 1.2cm; width: 2cm; margin-top: 20px; margin-bottom: 50px;">
                                <input type="radio" name="userLevel"id="mid" value="중" id="high" style="visibilty:hidden; display:none;">중
                            </label>
                            <label class="ui blue button inverted" id="low" style="height: 1.2cm; width: 2cm; margin-top: 20px; margin-bottom: 50px;">
                                <input type="radio" name="userLevel"id="low" value="하" id="high" style="visibilty:hidden; display:none;">하
                            </label>
                        </div>
                    </div>
                    <input type="submit" class="ui blue button" style="width: 100%;" value="저장">
                </form>
            </div>
        </div>



        <script>

            $('input[name="userLevel"]').change(function () {
                if ($('input[id="high"]').prop('checked')) {
                    if($('label[id="high"]').hasClass('inverted')){
                        $('label[id="high"]').toggleClass('inverted');
                    }
                } else {
                    if(!$('label[id="high"]').hasClass('inverted')){
                        $('label[id="high"]').toggleClass('inverted');
                    }
                }
                if ($('input[id="mid"]').prop('checked')) {
                    if($('label[id="mid"]').hasClass('inverted')){
                        $('label[id="mid"]').toggleClass('inverted');
                    }
                } else {
                    if(!$('label[id="mid"]').hasClass('inverted')){
                        $('label[id="mid"]').toggleClass('inverted');
                    }
                }
                if ($('input[id="low"]').prop('checked')) {
                    if($('label[id="low"]').hasClass('inverted')){
                        $('label[id="low"]').toggleClass('inverted');
                    }
                } else {
                    if(!$('label[id="low"]').hasClass('inverted')){
                        $('label[id="low"]').toggleClass('inverted');
                    }
                }
            });


        </script>
    </body>

</html>