<%-- 
    Document   : login
    Created on : 2021. 7. 29, 오전 9:50:36
    Author     : hwnim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="main.css">
    <link rel="stylesheet" type="text/css" href="./semantic/semantic.min.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    <script src="./semantic/semantic.min.js"></script>

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
    <div class="ui middle aligned center aligned grid">
        <div class="column">
            <h2>
                <div class="content">
                    <p class="logo">Plus Game</p>
                </div>
            </h2>
            <form class="ui large form" method="post" action="loginAction.jsp">
                <div class="field">
                    <div class="ui left icon input">
                        <i class="user outline icon"></i>
                        <input type="text" name="userId" placeholder="ID">
                    </div>
                </div>
                <div class="field">
                    <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="password" name="userPw" placeholder="Password">
                    </div>
                </div>
                <input type="submit" class="blue ui button" style="width: 100%;" value="Login">
                <span style="float: right; margin-top: 10px;"><a href="createaccount.jsp">Create Account</a></span>
            </form>
        </div>
    </div>



</body>

</html>