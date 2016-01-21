<%@ page import="com.wordpress.ilyaps.dao.TaskDAO" %>
<%@ page import="com.wordpress.ilyaps.models.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list my tasks</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
<%@include file='top.jsp' %>
<% Member member = (Member) request.getSession().getAttribute("member"); %>

    <div class="container">
        <h2 class="form-heading">List all tasks</h2>
        <% for (Task task : TaskDAO.findAllOpen()) {%>
            <div class="panel panel-success">

                <div class="panel-heading">
                    <%= task.getTitle() %>
                </div>

                <div class="panel-body">
                    <%= task.getText() %>
                </div>

                <div class="panel-footer">
                    <form action="want-to-help" method="post">
                        <div class="row input-group">
                            <span class="input-group-btn">
                                <button class="btn btn-success" type="button">Помочь!</button>
                            </span>
                            <input type="text" class="form-control" placeholder="comment">
                            <input type="hidden"  name = "task-id" value = "<%= task.getTaskId() %>">
                            <input type="hidden"  name = "member-email" value = "<%= member.getEmail() %>">
                        </div>
                    </form>
                </div>
            </div>
        <%}%>
    </div>
</body>
</html>