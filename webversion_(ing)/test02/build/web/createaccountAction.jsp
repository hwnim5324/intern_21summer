<%-- 
    Document   : loginAction
    Created on : 2021. 7. 29, 오전 11:00:31
    Author     : hwnim
--%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="plusgame.userDAO" %>
<%@page import="plusgame.optionDAO" %>
<%@page import="java.io.PrintWriter" %>
<%request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" class="plusgame.user" scope="page" />
<jsp:setProperty name="user" property="userId" />
<jsp:setProperty name="user" property="userPw" />
<jsp:setProperty name="user" property="userName" />
<jsp:setProperty name="user" property="checkuserPw" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plus Game</title>
    </head>
    <body>
        <%
            userDAO userdao = new userDAO();
            if(user.getUserId() == null){
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("alert('아이디를 입력해주세요.')");
                script.println("history.back()");
                script.println("</script>");
            }else if(user.getUserPw() == null){
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("alert('비밀번호를 입력해주세요.')");
                script.println("history.back()");
                script.println("</script>");
            }else if(user.getUserName() == null){
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("alert('이름을 입력해주세요.')");
                script.println("history.back()");
                script.println("</script>");
            }else if(userdao.checkpw(user.getUserPw(), user.getCheckuserPw()) == -1){
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.')");
                script.println("history.back()");
                script.println("</script>");
            }else{
                int result = userdao.createaccount(user);
                if(result==-1){
                    PrintWriter script = response.getWriter();
                    script.println("<script>");
                    script.println("alert('이미 존재하는 아이디입니다.')");
                    script.println("history.back()");
                    script.println("</script>");
                }else{
                    PrintWriter script = response.getWriter();
                    optionDAO optiondao = new optionDAO();
                    optiondao.newoption(user.getUserId());
                    script.println("<script>");
                    script.println("alert('회원가입 완료.\n환영합니다.')");
                    script.println("location.href='login.jsp'");
                    script.println("</script>");
                }
            }
        %>
    </body>
</html>
