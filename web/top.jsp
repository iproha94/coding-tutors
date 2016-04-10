<%@ page import="com.wordpress.ilyaps.models.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    .p {
        word-spacing: -7px;
    }

    .b {
        margin-bottom: 0px;
    }
</style>

<nav class="navbar navbar-default navbar-static-top p b">
    <div class="container-fluid" >

        <div class="navbar-header" >
            <a class="navbar-brand" href="/" >
                <span class="glyphicon glyphicon-education" aria-hidden="true">CodingTutors</span>
            </a>
        </div>

        <div align="right">
            <ul class="nav navbar-nav">
                <%
                    if (request.getSession().getAttribute("authorization") != null) {
                %>
                <form action="search" method="post" class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <input name="text" type="text" class="form-control" placeholder="Search task">
                    </div>
                    <button type="submit" class="btn btn-default">Go</button>
                </form>

                <li ><a href="edit-profile.jsp"><span class="glyphicon glyphicon-user" aria-hidden="true">
                <%
                        out.print(( (Member) request.getSession().getAttribute("member")).getFullname());
                %>
                </span></a></li>
                <li><a href="create-task.jsp"><span class="glyphicon glyphicon-new-window" > Создать задачу</span></a></li>
                <li><a href="add-book.jsp"><span class="glyphicon glyphicon-new-window" > Добавить книгу</span></a></li>
                <li><a href="list-my-tasks.jsp"><span class="glyphicon glyphicon-th-list" > Мои задачи</span></a></li>
                <li><a href="list-category.jsp"><span class="glyphicon glyphicon-list" > Категории</span></a></li>
                <li><a href="logout"><span class="glyphicon glyphicon-log-out " > Выход</span></a></li>

                <%
                } else {
                %>
                <li><a href="registration.jsp">Регистрация</a></li>
                <li><a href="authorization.jsp">Авторизация</a></li>
                <%
                    }
                %>
            </ul>
        </div>
    </div>
</nav>