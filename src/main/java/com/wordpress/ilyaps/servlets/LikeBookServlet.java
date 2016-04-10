package com.wordpress.ilyaps.servlets;

import com.wordpress.ilyaps.dao.BaseDAO;
import com.wordpress.ilyaps.dao.MemberDAO;
import com.wordpress.ilyaps.models.Book;
import com.wordpress.ilyaps.models.Member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ilyaps on 10.04.16.
 */
public class LikeBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        int bookId = new Integer(request.getParameter("book-id"));
        Book book = (Book) BaseDAO.find(Book.class, bookId);
        book.incLikes();
        BaseDAO.update(book);

        pw.println(ServletHelper.SUCCESSFUL);
        pw.println(ServletHelper.getHtmlRedirect("list-books.jsp"));
    }
}
