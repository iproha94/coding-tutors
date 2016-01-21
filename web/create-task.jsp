<%--
  Created by IntelliJ IDEA.
  User: ilyap
  Date: 21.01.2016
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>create task</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
<%@include file='top.jsp' %>
<div class="container">
    <form action="create-task" method="post" class="form-signup">
        <h2 class="form-signup-heading">Fill out the task form</h2>


        <br>
        <div class="input-group input-group-lg">
            <span class="input-group-addon">title</span>
            <input type="text" class="form-control" aria-describedby="sizing-addon1" name="title" >
        </div>

        <br>
        <div class="input-group input-group-lg">
            <span class="input-group-addon">text</span>
            <input type="text" class="form-control" aria-describedby="sizing-addon1" name="text" >
        </div>

        <br>
        <button class="btn btn-large btn-primary" type="submit">Create task</button>
    </form>
</div>

</body>
</html>
