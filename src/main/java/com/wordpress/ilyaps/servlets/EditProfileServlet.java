package com.wordpress.ilyaps.servlets;

import com.wordpress.ilyaps.dao.MemberDAO;
import com.wordpress.ilyaps.models.Member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ilyap on 19.01.2016.
 */
public class EditProfileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");


        if (!verificationParametersInRequest(request)) {
            pw.println(ServletHelper.BAD_FORM);
            pw.println(ServletHelper.getHtmlRedirect("/edit-profile.jsp"));
            return;
        }

        Member member = new Member();
        Member oldMember = (Member) request.getSession().getAttribute("member");

        member.setFirstname(request.getParameter("firstname"));
        member.setSurname(request.getParameter("surname"));
        member.setEmail(request.getParameter("email"));
        member.setHashPassword(oldMember.getHashPassword());
        member.setUniversityShortName(request.getParameter("university"));

        if (!MemberDAO.update(member)) {
            pw.println(ServletHelper.ERROR);
            pw.println(ServletHelper.getHtmlRedirect("/edit-profile.jsp"));
            return;
        }

        request.getSession().setAttribute("member", member);
        pw.println(ServletHelper.SUCCESSFUL);
        pw.println(ServletHelper.getHtmlRedirect("/"));
    }

    boolean verificationParametersInRequest(HttpServletRequest request) {
        if (request.getParameter("firstname").length() < 4) {
            return false;
        }

        if (request.getParameter("surname").length() < 4) {
            return false;
        }

        if (request.getParameter("email").length() < 4) {
            return false;
        }

        return true;
    }

}
