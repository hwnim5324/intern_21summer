<%-- 
    Document   : loginAction
    Created on : 2021. 7. 29, 오전 11:00:31
    Author     : hwnim
--%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="plusgame.userDAO" %>
<%@page import="java.io.PrintWriter" %>
<%request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" class="plusgame.user" scope="page" />
<jsp:setProperty name="user" property="userId" />
<jsp:setProperty name="user" property="userPw" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plus Game</title>
    </head>
    <body>
        <%
            userDAO userdao = new userDAO();
            int result = userdao.login(user.getUserId(), user.getUserPw());
            if(result==1){
                session.setAttribute("userId",user.getUserId());
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("location.href='mainmenu.jsp'");
                script.println("</script>");
            }else if(result==0){
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("alert('비밀번호를 잘못 입력하셨습니다.')");
                script.println("history.back()");
                script.println("</script>");
            }else if(result==-1){
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("alert('존재하지 않는 아이디입니다.')");
                script.println("history.back()");
                script.println("</script>");
            }else if(result==-2){
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("alert('데이터베이스 오류가 발생하였습니다.')");
                script.println("history.back()");
                script.println("</script>");
            }
        %>
    </body>
</html>
