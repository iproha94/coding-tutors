<%@ page import="com.wordpress.ilyaps.models.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.wordpress.ilyaps.models.Category" %>
<%@ page import="com.wordpress.ilyaps.dao.CategoryDAO" %>
<style>
    .p {
        word-spacing: -7px;
    }

    .p10 {
        word-spacing: -3px;
    }

    .b {
        margin-bottom: 0;
    }
</style>

<nav class="navbar navbar-default navbar-static-top p b">

    <div class="navbar-header" >
        <a class="navbar-brand" href="/" >
            <span class="glyphicon glyphicon-education" aria-hidden="true">CodingTutors</span>
        </a>
    </div>

    <div class="container-fluid" >
        <div align="right" >
            <ul class="nav navbar-nav">
                <% if (request.getSession().getAttribute("authorization") != null) {%>
                <li>
                    <a href="edit-profile.jsp"><span class="glyphicon glyphicon-user" aria-hidden="true">
                    <% out.print(( (Member) request.getSession().getAttribute("member")).getFullname());%>
                    </span></a>
                </li>
                <li><a href="create-task.jsp"><span class="glyphicon glyphicon-new-window" > Создать задачу</span></a></li>
                <li><a href="add-book.jsp"><span class="glyphicon glyphicon-new-window" > Добавить книгу</span></a></li>
                <li><a href="list-my-tasks.jsp"><span class="glyphicon glyphicon-th-list" > Мои задачи</span></a></li>
                <li><a href="logout"><span class="glyphicon glyphicon-log-out " > Выход</span></a></li>

                <%
                } else {
                %>
                <li><a href="registration.jsp">Регистрация</a></li>
                <li><a href="authorization.jsp">Авторизация</a></li>
                <%}%>
            </ul>
        </div>

        <div class="p10">
            <% if (request.getSession().getAttribute("authorization") != null) {%>
            <ul class="nav navbar-nav">
                <li>
                    <form action="search" method="post" class="navbar-form navbar-left" role="search">
                        <div class="input-group input-group">
                            <div class="input-group-addon">Search</div>
                            <div class="form-group">
                                <input name="text" type="text" class="form-control" placeholder="Search task">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-default">Go</button>
                    </form>
                </li>
                <li>
                    <form action="category" method="post" class="navbar-form navbar-left">
                        <div class="input-group input-group">
                            <div class="input-group-addon">Category</div>
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
                        <button class="btn btn-default" type="submit" onclick="form.action='category';">Show tasks</button>
                        <button class="btn btn-default" type="submit" onclick="form.action='library';" >Show books</button>
                    </form>
                </li>
            </ul>
        </div>
        <%}%>
    </div>


</nav>