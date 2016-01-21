<%@ page import="com.wordpress.ilyaps.models.Member" %>
<%@ page import="com.wordpress.ilyaps.models.Task" %>
<%@ page import="com.wordpress.ilyaps.models.WantToHelp" %>
<%@ page import="com.wordpress.ilyaps.dao.WantToHelpDAO" %><%--
  Created by IntelliJ IDEA.
  User: ilyap
  Date: 21.01.2016
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list my tasks</title>

    <style>
        body {
            margin: 0; /* Убираем отступы */
        }
        .task {
            margin: 0% 5%; /* Отступы вокруг */
        }
    </style>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
<%@include file='top.jsp' %>

<% Member member = (Member) request.getSession().getAttribute("member"); %>
<div class="task container">
    <h2 class="form-heading">List my tasks</h2>
    <% for (Task task : member.getListMyTasks()) {%>
    <div class="panel <% out.print(task.isOpen() ? "panel-success" : "panel-danger"); %>" >

        <div class="panel-heading">
                <%= task.getTitle() %>
        </div>

        <div class="panel-body">
                <%= task.getText() %>
        </div>

        <div class="panel-footer">
            <form action="close-task" method="post">
                <span class="btn">
                    <button class="btn btn-danger" type="submit">Закрыть</button>
                </span>
                <input type="hidden"  name = "task-id" value = "<%= task.getTaskId() %>">
            </form>
            <% for (WantToHelp help : WantToHelpDAO.findByTaskId(task.getTaskId())) { %>
            <div class="input-group input-group-lg">
                <span class="input-group-addon" id="sizing-addon1"><%= help.getMemberEmail() %></span>
                <input type="text" class="form-control" value="<%= help.getNote()%>" readonly>
            </div>
            <br>
            <%}%>
        </div>

    </div>
    <%}%>
</div>
</body>
</html>
