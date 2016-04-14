package com.wordpress.ilyaps.servlets;

import com.wordpress.ilyaps.dao.BaseDAO;
import com.wordpress.ilyaps.models.Member;
import com.wordpress.ilyaps.models.WantToHelp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LikeWantToHelpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        String helpId = request.getParameter("help");
        WantToHelp help = (WantToHelp) BaseDAO.find(WantToHelp.class, new Integer(helpId));

        if (!help.isLike()) {
            help.setLike();
            BaseDAO.update(help);

            String emailHelper = request.getParameter("email");
            Member member = (Member) BaseDAO.find(Member.class, emailHelper);
            member.incLikes();
            BaseDAO.update(member);
        }

        pw.println(ServletHelper.SUCCESSFUL);
        pw.println(ServletHelper.getHtmlRedirect("list-my-tasks.jsp"));
    }
}
