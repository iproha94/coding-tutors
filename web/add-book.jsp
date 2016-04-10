<%@ page import="com.wordpress.ilyaps.dao.CategoryDAO" %>
<%@ page import="com.wordpress.ilyaps.models.Category" %><%--
  Created by IntelliJ IDEA.
  User: ilyap
  Date: 21.01.2016
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>add book</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
<%@include file='top.jsp' %>
<div class="container">
    <form action="add-book" method="post" class="form-signup">
        <h2 class="form-signup-heading">Fill out the book form</h2>

        <br>
        <div class="input-group input-group-lg">
            <span class="input-group-addon">title</span>
            <input type="text" class="form-control" aria-describedby="sizing-addon1" name="title" >
        </div>

        <br>
        <div class="input-group input-group-lg">
            <span class="input-group-addon">author</span>
            <input type="text" class="form-control" aria-describedby="sizing-addon1" name="author" >
        </div>

        <br>
        <div class="input-group input-group-lg">
            <span class="input-group-addon">Category</span>
            <select class="form-control" id="sel1"  name="category" >
                <%
                    for (Category category : CategoryDAO.findAll()) {
                %>
                <option value = '<% out.print(category.getId()); %>'>
                    <% out.print(category.getName()); %>
                </option>
                <%
                    }
                %>
            </select>
        </div>

        <br>
        <button class="btn btn-large btn-primary" type="submit">Add book</button>
    </form>
</div>

</body>
</html>
