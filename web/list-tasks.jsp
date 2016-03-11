<%@ page import="com.wordpress.ilyaps.dao.TaskDAO" %>
<%@ page import="com.wordpress.ilyaps.models.Task" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="com.wordpress.ilyaps.dao.BaseDAO" %>
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
        <h2 class="form-heading">List all tasks (<%=BaseDAO.count(Task.class)%>)</h2>
        <% int start = request.getParameter("start") != null ? new Integer(request.getParameter("start")) : 0; %>
        <nav>
            <ul class="pager">
                <li class="previous"><a href="list-tasks.jsp?start=<%=start - 10%>"><span aria-hidden="true">&larr;</span> Older</a></li>
                <li class="next"><a href="list-tasks.jsp?start=<%=start + 10%>">Newer <span aria-hidden="true">&rarr;</span></a></li>
            </ul>
        </nav>

        <% for (Task task : TaskDAO.findAllOpen(start, 10)) {
            if (!task.getMemberNeed().getEmail().equals(member.getEmail())) {%>
                <div class="panel panel-success">

                    <div class="panel-heading">
                        <%= "#" + task.getTaskId() + " | " + task.getTitle() %>
                    </div>

                    <div class="panel-body">
                        <%= task.getText() %>
                    </div>

                    <form action="want-to-help" method="post">
                        <div class=" input-group panel-body">
                            <% String comment = member.getMyNoteForTask(task); %>
                            <% if (comment == null) { %>
                            <span class="input-group-btn">
                                <button class="btn btn-success " type="submit">Помочь!</button>
                            </span>
                            <input type="text" class="form-control" name = "note" placeholder="note">
                            <% } else { %>
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
                        <h5> Хотят помочь: <strong> <%= task.getCountWantToHelp()%> </strong> </h5>
                        <hr>
                        <%
                            long duration = new Date().getTime() - task.getDateTimeField().getTime();
                            long diffdays =  TimeUnit.MILLISECONDS.toDays(duration);
                        %>
                        <h5> Дата публикации: <strong> <%= diffdays%> days ago</strong> </h5>
                    </div>
                </div>
            <%}
        }%>
    </div>
</body>
</html>