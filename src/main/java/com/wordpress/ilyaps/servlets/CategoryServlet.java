package com.wordpress.ilyaps.servlets;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CategoryServlet extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(CategoryServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        LOGGER.info(request.getParameter("category"));
        request.getSession().setAttribute("category",  request.getParameter("category"));

        pw.println(ServletHelper.WAIT);
        pw.println(ServletHelper.getHtmlRedirect("/list-tasks.jsp"));
    }
}
