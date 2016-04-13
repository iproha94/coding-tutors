<%@ page import="com.wordpress.ilyaps.dao.TaskDAO" %>
<%@ page import="com.wordpress.ilyaps.models.Task" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="com.wordpress.ilyaps.dao.WantToHelpDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.wordpress.ilyaps.models.WantToHelp" %>
<%@ page import="java.util.Set" %>
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
<%
    Member member = (Member) request.getSession().getAttribute("member");
    int start = request.getParameter("start") != null ? new Integer(request.getParameter("start")) : 0;
    String searchText = (String) request.getSession().getAttribute("search_text");
    Set<Task> tasks = TaskDAO.search(searchText, start, 10);
%>

    <div class="container">
        <h2 class="form-heading">List found tasks by "<%= searchText %>"</h2>
        <%
            for (Task task : tasks) { %>
            <div class="panel panel-warning">

                <div class="panel-heading">
                    <%= "#" + task.getTaskId() + " | " + task.getTitle() + " | " + task.getCategory().getName() %>
                </div>

                <div class="panel-body">
                    <%= task.getText() %>
                </div>

                <form action="want-to-help" method="post">
                    <div class=" input-group panel-body">
                        <% String comment = WantToHelpDAO.getNoteForTaskByEmail(task, member); %>
                        <%if (task.isOpen() && comment == null) { %>
                        <span class="input-group-btn">
                            <button class="btn btn-success " type="submit">Помочь!</button>
                        </span>
                        <input type="text" class="form-control" name = "note" placeholder="note">
                        <% } else if (task.isOpen()){ %>
                        <span class="input-group-addon">
                            Ваш комментарий
                        </span>
                        <input type="text" class="form-control" value="<%= comment %>" readonly>
                        <% } %>
                    </div>

                    <input type="hidden"  name = "task-id" value = "<%= task.getTaskId() %>">
                    <input type="hidden"  name = "member-email" value = "<%= member.getEmail() %>">
                </form>


                <div class="panel-footer">
                    <% if (task.isOpen()) { %>

                        <h5> Хотят помочь: <strong> <%= task.getCountWantToHelp()%> </strong> </h5>
                        <hr>
                        <%
                            long duration = new Date().getTime() - task.getDateTimeField().getTime();
                            long diffdays =  TimeUnit.MILLISECONDS.toDays(duration);
                        %>
                        <h5> Дата публикации: <strong> <%= diffdays%> days ago</strong> </h5>
                    <% } else { %>
                            Задача закрыта
                    <% } %>

                    <table class="table"><hr>
                    <% List<WantToHelp> helps =  WantToHelpDAO.findByTaskId(task);
                    if (helps.size() > 0) { %>
                        <tr><th>email</th><th>соответствие</th></tr>
                        <% for (WantToHelp help : helps) { %>
                            <tr>
                                <td width="15%"><%= help.getMemberHelper().getEmail() %></td>
                                <td width="5%"><%= help.getLevelOfCompliance() %></td>
                            </tr>
                        <% }
                    } %>
                        </table>
                </div>
            </div>
        <%}%>
    </div>
</body>
</html>