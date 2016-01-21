package com.wordpress.ilyaps.servlets;

import com.wordpress.ilyaps.dao.WantToHelpDAO;
import com.wordpress.ilyaps.models.WantToHelp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ilyap on 21.01.2016.
 */
public class WantToHelpServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        if (!verificationParametersInRequest(request)) {
            pw.println(ServletHelper.BAD_FORM);
            pw.println(ServletHelper.getHtmlRedirect("/list-tasks.jsp"));
            return;
        }

        WantToHelp wantToHelp = new WantToHelp();

        wantToHelp.setComment(request.getParameter("comment"));
        wantToHelp.setMemberEmail(request.getParameter("member-email"));
        wantToHelp.setTaskId(new Integer(request.getParameter("task-id")));

        if (!WantToHelpDAO.insert(wantToHelp)) {
            pw.println(ServletHelper.ERROR);
            pw.println(ServletHelper.getHtmlRedirect("/list-tasks.jsp"));
            return;
        }

        pw.println(ServletHelper.SUCCESSFUL);
        pw.println(ServletHelper.getHtmlRedirect("list-tasks.jsp"));
    }

    boolean verificationParametersInRequest(HttpServletRequest request) {
        if (request.getParameter("comment").length() < 4) {
            return false;
        }

        return true;
    }
}
