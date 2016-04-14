package com.wordpress.ilyaps.servlets;

import com.wordpress.ilyaps.dao.BaseDAO;
import com.wordpress.ilyaps.dao.TaskDAO;
import com.wordpress.ilyaps.models.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CloseTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        int taskId = new Integer(request.getParameter("task-id"));
        Task task = (Task) BaseDAO.find(Task.class, taskId);
        task.setOpen(false);
        TaskDAO.update(task);

        pw.println(ServletHelper.SUCCESSFUL);
        pw.println(ServletHelper.getHtmlRedirect("list-my-tasks.jsp"));
    }


}
