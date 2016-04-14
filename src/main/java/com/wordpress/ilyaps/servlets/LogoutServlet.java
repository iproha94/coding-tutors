package com.wordpress.ilyaps.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        if (request.getSession().getAttribute("authorization") != null) {
            request.getSession().removeAttribute("authorization");
            pw.println(ServletHelper.SUCCESSFUL);
        } else {
            pw.println(ServletHelper.ERROR);
        }

        pw.println(ServletHelper.getHtmlRedirect("/"));
    }
}
