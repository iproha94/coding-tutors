package com.wordpress.ilyaps.servlets;

import com.wordpress.ilyaps.dao.BookDAO;
import com.wordpress.ilyaps.dao.CategoryDAO;
import com.wordpress.ilyaps.models.Book;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddBookServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        if (!verificationParametersInRequest(request)) {
            pw.println(ServletHelper.BAD_FORM);
            pw.println(ServletHelper.getHtmlRedirect("/create-task.jsp"));
            return;
        }

        Book book = new Book();
        book.setAuthor(request.getParameter("author"));
        book.setTitle(request.getParameter("title"));
        book.setCategory(CategoryDAO.getMap().get(new Integer(request.getParameter("category"))));

        if (!BookDAO.insert(book)) {
            pw.println(ServletHelper.ERROR);
            pw.println(ServletHelper.getHtmlRedirect("/add-book.jsp"));
            return;
        }

        pw.println(ServletHelper.SUCCESSFUL);
        pw.println(ServletHelper.getHtmlRedirect("/"));
    }

    private boolean verificationParametersInRequest(HttpServletRequest request) {
        if (request.getParameter("title").length() < 4) {
            return false;
        }

        if (request.getParameter("author").length() < 4) {
            return false;
        }

        return true;
    }
}
