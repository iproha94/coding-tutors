<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>authoriation</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
    <%@include file='top.jsp' %>

    <div class="container">
        <form action="authorization" method="post" class="form-signin">
            <h2 class="form-signin-heading">Please sign in</h2>
            <br>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">email</span>
                <input type="text" class="form-control" aria-describedby="sizing-addon1" name = "email">
            </div>
            <br>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">password</span>
                <input type="password" class="form-control" aria-describedby="sizing-addon1" name = "password">
            </div>
            <br>
            <button class="btn btn-large btn-primary" type="submit">Sign in</button>
        </form>
    </div>
</body>
</html>