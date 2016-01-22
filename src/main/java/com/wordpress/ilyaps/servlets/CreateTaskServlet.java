package com.wordpress.ilyaps.servlets;

import com.wordpress.ilyaps.dao.TaskDAO;
import com.wordpress.ilyaps.models.Member;
import com.wordpress.ilyaps.models.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by ilyap on 21.01.2016.
 */
public class CreateTaskServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        if (!verificationParametersInRequest(request)) {
            pw.println(ServletHelper.BAD_FORM);
            pw.println(ServletHelper.getHtmlRedirect("/create-task.jsp"));
            return;
        }

        Task task = new Task();
        Member member = (Member) request.getSession().getAttribute("member");

        task.setMemberNeed(member);
        task.setText(request.getParameter("text"));
        task.setTitle(request.getParameter("title"));
        task.setDateTimeField(new Timestamp(new Date().getTime()));

        if (!TaskDAO.insert(task)) {
            pw.println(ServletHelper.ERROR);
            pw.println(ServletHelper.getHtmlRedirect("/create-task.jsp"));
            return;
        }

        pw.println(ServletHelper.SUCCESSFUL);
        pw.println(ServletHelper.getHtmlRedirect("/list-my-tasks.jsp"));
    }

    boolean verificationParametersInRequest(HttpServletRequest request) {
        if (request.getParameter("title").length() < 4) {
            return false;
        }

        if (request.getParameter("text").length() < 4) {
            return false;
        }

        return true;
    }

}
