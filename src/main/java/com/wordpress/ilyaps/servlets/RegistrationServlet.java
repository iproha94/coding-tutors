package com.wordpress.ilyaps.servlets;

import com.wordpress.ilyaps.dao.MemberDAO;
import com.wordpress.ilyaps.models.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ilyap on 25.12.2015.
 */
@WebServlet(name = "RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        if (!verificationParametersInRequest(request)) {
            pw.println(ServletHelper.BAD_FORM);
            pw.println(ServletHelper.getHtmlRedirect("/registration.jsp"));
            return;
        }

        Member member = new Member();

        member.setFirstname(request.getParameter("firstname"));
        member.setSurname(request.getParameter("surname"));
        member.setEmail(request.getParameter("email"));
        member.setHashPassword(request.getParameter("password").hashCode());
        member.setUniversityShortName(request.getParameter("university"));

        if (!MemberDAO.insert(member)) {
            pw.println(ServletHelper.ERROR);
            pw.println(ServletHelper.getHtmlRedirect("/registration.jsp"));
            return;
        }

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

        if (request.getParameter("password").length() < 4) {
            return false;
        }

        return true;
    }
}
