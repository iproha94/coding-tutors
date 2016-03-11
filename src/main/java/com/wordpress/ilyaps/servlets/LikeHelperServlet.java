package com.wordpress.ilyaps.servlets;

import com.wordpress.ilyaps.dao.BaseDAO;
import com.wordpress.ilyaps.dao.MemberDAO;
import com.wordpress.ilyaps.dao.TaskDAO;
import com.wordpress.ilyaps.models.Member;
import com.wordpress.ilyaps.models.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ilyaps on 11.03.16.
 */
public class LikeHelperServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        String emailHelper = request.getParameter("email");
        Member member = (Member) BaseDAO.find(Member.class, emailHelper);
        member.incLikes();
        MemberDAO.update(member);

        pw.println(ServletHelper.SUCCESSFUL);
        pw.println(ServletHelper.getHtmlRedirect("list-my-tasks.jsp"));
    }


}
