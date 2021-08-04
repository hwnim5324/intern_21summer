<%-- 
    Document   : showscore
    Created on : 2021. 7. 29, 오전 10:09:01
    Author     : hwnim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="plusgame.score"%>
<%@page import="plusgame.questions"%>
<%@page import="plusgame.scoreDAO"%>
<%@page import="plusgame.questionsDAO"%>
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
        }
        else{
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("location.href='login.jsp'");
            script.println("</script>");
        }
        int qCode=0;
        if(request.getParameter("qCode")!=null){
            qCode=Integer.parseInt(request.getParameter("qCode"));
        }
        if(qCode==0){
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('유효하지 않은 주소입니다.')");
            script.println("location.href='showscore.jsp'");
            script.println("</script>");
        }
        score score = new scoreDAO().getdetail(qCode);
    %>
    
    <div class="ui middle aligned center aligned grid">
        <div class="column">
            <h2>
                <div class="content">
                    <p class="logo">Plus Game</p>
                </div>
            </h2>
            <form class="ui large form">

                <table class="ui selectable called table" style="margin-top: 0.7cm;">
                    <thead>
                        <tr>
                            <th class="center aligned">번호</th>
                            <th class="center aligned">문제</th>
                            <th class="center aligned">입력</th>
                            <th class="center aligned">정답</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <%
                                questionsDAO questionsdao = new questionsDAO();
                                ArrayList<questions> list = questionsdao.getquestions(qCode);
                                ArrayList<Integer> userAnswer = questionsdao.transformUserAnswer(qCode);
                                for(int i=0;i<list.size();i++){
                            %>
                            <tr>
                                <td class="center aligned"><%= list.get(i).getqNumber() %></td>
                                <td class="center aligned"><%= list.get(i).getQuestions() %></td>
                                <td class="center aligned"><%= userAnswer.get(i) %></td>
                                <td class="center aligned"><%= list.get(i).getRightAnswer() %></td>
                            </tr>
                            <%
                                }
                            %>
                        
                    </tbody>
                </table>


                <a href="showscore.jsp">
                    <div class="ui blue button" style="width: 100%; margin-top: 30px; margin-bottom: 20px;">
                        <p>돌아가기</p>
                    </div>
                </a>
            </form>
        </div>
    </div>



</body>

</html>