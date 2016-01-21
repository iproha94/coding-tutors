<%@ page import="com.wordpress.ilyaps.models.University" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>registration</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
    <%@include file='top.jsp' %>

    <div class="container">
        <form action="registration" method="post" class="form-signup">
            <h2 class="form-signup-heading">Please sign up</h2>

            <br>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">firstname</span>
                <input type="text" class="form-control" aria-describedby="sizing-addon1" name="firstname" >
            </div>

            <br>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">surname</span>
                <input type="text" class="form-control" aria-describedby="sizing-addon1" name="surname" >
            </div>

            <br>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">email</span>
                <input type="text" class="form-control" aria-describedby="sizing-addon1" name="email" >
            </div>

            <br>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">password</span>
                <input type="password" class="form-control" aria-describedby="sizing-addon1" name="password" >
            </div>

            <br>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">University</span>
                <select class="form-control" id="sel1"  name="university" >
                    <%
                        for (String university : University.getSet()) {
                    %>
                    <option value = '<% out.print(university); %>'>
                        <% out.print(University.getMap().get(university)); %>
                    </option>
                    <%
                        }
                    %>
                </select>
            </div>



            <br>
            <button class="btn btn-large btn-primary" type="submit">Sign up</button>
        </form>
    </div>
</body>
</html>