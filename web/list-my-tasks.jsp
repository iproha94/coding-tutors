<%@ page import="com.wordpress.ilyaps.models.Member" %>
<%@ page import="com.wordpress.ilyaps.models.Task" %>
<%@ page import="com.wordpress.ilyaps.models.WantToHelp" %>
<%@ page import="com.wordpress.ilyaps.dao.WantToHelpDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.wordpress.ilyaps.dao.TaskDAO" %><%--
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

<% Member member = (Member) request.getSession().getAttribute("member"); %>
<div class="container">
    <h2 class="form-heading">List my tasks (<%= TaskDAO.countByMember(member)%>)</h2>
    <% for (Task task : member.getListMyTasks()) {%>
    <div class="panel <% out.print(task.isOpen() ? "panel-success" : "panel-danger"); %>" >

        <div class="panel-heading">
                <%= task.getTitle() %>
        </div>

        <div class="panel-body">
                <%= task.getText() %>
        </div>

        <table class="table">
            <% List<WantToHelp> helps = task.getListWantToHelps(); %>

            <% if (helps.size() > 0) { %>
                <tr><th>email</th><th>соответствие</th><th>note</th></tr>
            <% } %>

            <% for (WantToHelp help : helps) { %>
            <tr>
                <td width="15%"><%= help.getMemberHelper().getEmail() %></td>
                <td width="5%"><%= help.getLevelOfCompliance() %></td>
                <td><%= help.getNote() %></td>
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
