<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.util.List" %>
<%@ page import="com.wordpress.ilyaps.models.*" %>
<%@ page import="com.wordpress.ilyaps.dao.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list books</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
<%@include file='top.jsp' %>
<%
    Member member = (Member) request.getSession().getAttribute("member");
    int catId = new Integer((String) request.getSession().getAttribute("category"));
    Category category = (Category) BaseDAO.find(Category.class, catId);

    List<Book> books = BookDAO.findByCategory(category);
%>

    <div class="container">
        <h2 class="form-heading">List books for   <%= category.getName() %> </h2>
        <%
            for (Book book : books) {
        %>
        <div class="panel panel-success">

            <div class="panel-heading">
                <%= book.getAuthor() %>
            </div>

            <div class="panel-body">
                <%=  book.getTitle() %>
            </div>

            <div class="panel-footer">
                Likes: <%= book.getLikes() %>

                <% if (!LikeBookDAO.exist(new LikeBook(member, book))) { %>
                <form action="like-book" method="post">
                    <button class="btn btn-primary" type="submit">like</button>
                    <input type="hidden"  name = "book-id" value = "<%= book.getBookId() %>">
                </form>
                <% } %>
            </div>
        </div>

        <%}%>
    </div>
</body>
</html>