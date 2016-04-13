<%@ page import="com.wordpress.ilyaps.dao.WantToHelpDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.wordpress.ilyaps.dao.TaskDAO" %>
<%@ page import="com.wordpress.ilyaps.models.*" %>
<%--
  Created by IntelliJ IDEA.
  User: ilyap
  Date: 21.01.2016
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>list my tasks</title>
    <style>
        table {
            width: 100%; /* Ширина таблицы */
            border-collapse: collapse; /*  Убираем двойные линии между ячейками */
        }
        td, th {
            padding: 4px; /* Поля в ячейках */
            border: 1px solid #000080; /* Граница между ячейками */
        }
        th {
            background: #000080; /* Цвет фона строки заголовка */
            color: #ffe; /* Цвет текста */
            text-align: left; /* Выравнивание по левому краю */
            font-family: Arial, Helvetica, sans-serif; /* Выбор гарнитуры */
            font-size: 0.9em; /* Размер текста */
        }
    </style>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
<%@include file='top.jsp' %>

<%
    Member member = (Member) request.getSession().getAttribute("member");
    List<Task> tasks = TaskDAO.findTasksByMemberNeed(member);
%>
<div class="container">
    <h2 class="form-heading">List my tasks (<%= tasks.size() %>)</h2>
    <% for (Task task : tasks) {%>
    <div class="panel <% out.print(task.isOpen() ? "panel-success" : "panel-danger"); %>" >

        <div class="panel-heading">
                <%= task.getTitle() + " | " + task.getCategory().getName()%>
        </div>

        <div class="panel-body">
                <%= task.getText() %>
        </div>

        <table class="table">
            <% List<WantToHelp> helps =  WantToHelpDAO.findByTaskId(task); %>

            <% if (helps.size() > 0) { %>
                <tr><th>поблагодарить</th><th>email</th><th>соответствие</th><th>note</th></tr>
            <% } %>

            <% for (WantToHelp help : helps) { %>
            <tr>
                <form action="like-helper" method="post">
                    <td width="5%">
                        <% if (!help.isLike()) { %>
                            <button class="btn btn-primary" type="submit">like</button>
                        <% } %>
                    </td>

                    <td width="15%"><%= help.getMemberHelper().getEmail() %></td>
                    <td width="5%"><%= help.getLevelOfCompliance() %></td>
                    <td><%= help.getNote() %></td>

                    <input type="hidden"  name = "email" value = "<%= help.getMemberHelper().getEmail() %>">
                    <input type="hidden"  name = "help" value = "<%= help.getWantToHelpId() %>">
                </form>
            </tr>
            <%}%>
        </table>

        <div class="panel-footer">
            <h5> Дата публикации: <strong> <%= task.getDateTimeField()%> </strong> </h5>
            <hr>
            <% if (task.isOpen()) { %>
                <form action="close-task" method="post">
                    <button class="btn btn-danger" type="submit">Закрыть</button>
                    <input type="hidden"  name = "task-id" value = "<%= task.getTaskId() %>">
                </form>
            <%}%>
        </div>

    </div>
    <%}%>
</div>
</body>
</html>
