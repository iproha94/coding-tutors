<%@ page import="com.wordpress.ilyaps.models.Category" %>
<%@ page import="com.wordpress.ilyaps.dao.CategoryDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>task category</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
    <%@include file='top.jsp' %>

    <div class="container">
        <form action="category" method="post" class="form-signup">
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
            <button class="btn btn-large btn-primary" type="submit" onclick="form.action='category';">Show tasks</button>
            <button class="btn btn-large btn-primary" type="submit" onclick="form.action='library';" >Show books</button>
        </form>
    </div>

</body>
</html>