<%@ page import="com.wordpress.ilyaps.models.Member" %>
<%@ page import="com.wordpress.ilyaps.models.University" %>
<%@ page import="com.wordpress.ilyaps.dao.UniversityDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit profile</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
<%@include file='top.jsp' %>
    <div class="container">
        <form action="edit-profile" method="post" class="form-signup">
            <h2 class="form-signup-heading">Edit your profile</h2>
            <% Member member = (Member) request.getSession().getAttribute("member"); %>
            <br>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">firstname</span>
                <input type="text" class="form-control" aria-describedby="sizing-addon1" name="firstname"  value = "<% out.print(member.getFirstname()); %>">
            </div>

            <br>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">surname</span>
                <input type="text" class="form-control" aria-describedby="sizing-addon1" name="surname" value = "<% out.print(member.getSurname()); %>">
            </div>

            <br>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">email</span>
                <input type="text" class="form-control" aria-describedby="sizing-addon1" name="email" readonly value = "<% out.print(member.getEmail()); %>">
            </div>

            <br>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">likes</span>
                <input type="text" class="form-control" aria-describedby="sizing-addon1" name="likes" readonly value = "<% out.print(member.getLikes()); %>">
            </div>

            <br>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">University</span>
                <select class="form-control" id="sel1"  name="university" >
                    <%
                        for (String university : UniversityDAO.getSet()) {
                    %>
                    <option <%
                        if (member.getUniversityShortName().equals(university)) {
                            out.print("selected");
                        }
                    %> value = '<% out.print(university); %>'>
                        <% out.print(UniversityDAO.getMap().get(university)); %>
                    </option>
                    <%
                        }
                    %>
                </select>
            </div>

            <br>
            <button class="btn btn-large btn-primary" type="submit">Save</button>
        </form>
    </div>

</body>
</html>