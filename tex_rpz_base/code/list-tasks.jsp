<%
int catId = new Integer((String) request.getSession().getAttribute("category"));
Category category = (Category) BaseDAO.find(Category.class, catId);

List<Task> tasks = TaskDAO.findOpenByCategory(category, start, 10);
            
for (Task task : tasks) {
    if (!task.getMemberNeed().getEmail().equals(member.getEmail())) {%>
        <div class="panel panel-success">

            <div class="panel-heading">
                <%= "#" + task.getTaskId() + " | " + task.getTitle() + " | " + task.getCategory().getName() %>
            </div>

            <div class="panel-body">
                <%= task.getText() %>
            </div>

            <form action="want-to-help" method="post">
                <div class=" input-group panel-body">
                    <% String comment = WantToHelpDAO.getNoteForTaskByEmail(task, member); %>
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
