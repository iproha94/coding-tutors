package com.wordpress.ilyaps.servlets;

import com.wordpress.ilyaps.dao.BaseDAO;
import com.wordpress.ilyaps.models.Book;
import com.wordpress.ilyaps.models.LikeBook;
import com.wordpress.ilyaps.models.Member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LikeBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        int bookId = new Integer(request.getParameter("book-id"));
        Book book = (Book) BaseDAO.find(Book.class, bookId);
        book.incLikes();
        BaseDAO.update(book);

        Member member = (Member) request.getSession().getAttribute("member");
        BaseDAO.insert(new LikeBook(member, book));

        pw.println(ServletHelper.SUCCESSFUL);
        pw.println(ServletHelper.getHtmlRedirect("list-books.jsp"));
    }
}
