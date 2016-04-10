package com.wordpress.ilyaps.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ilyaps on 10.04.16.
 */
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        pw.println();

        String searchText = request.getParameter("text");

        if (searchText == null || searchText.length() < 3) {
            pw.println(ServletHelper.BAD_FORM);
            pw.println(ServletHelper.getHtmlRedirect("/"));
        } else {
            pw.println(ServletHelper.WAIT);
            request.getSession().setAttribute("search_text", searchText);
            pw.println(ServletHelper.getHtmlRedirect("/list-search-tasks.jsp"));
        }
    }

}
