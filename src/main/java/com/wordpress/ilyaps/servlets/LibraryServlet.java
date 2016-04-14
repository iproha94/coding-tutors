package com.wordpress.ilyaps.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LibraryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        request.getSession().setAttribute("category",  request.getParameter("category"));

        pw.println(ServletHelper.WAIT);
        pw.println(ServletHelper.getHtmlRedirect("/list-books.jsp"));
    }
}
