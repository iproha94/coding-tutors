package com.wordpress.ilyaps.servlets;

import com.wordpress.ilyaps.dao.MemberDAO;
import com.wordpress.ilyaps.models.Member;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ilyap on 24.12.2015.
 */
@WebServlet(name = "AuthorizationServlet")
public class AuthorizationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        if (!verificationParametersInRequest(request)) {
            pw.println(ServletHelper.BAD_FORM);
            pw.println(ServletHelper.getHtmlRedirect("/authorization.jsp"));
            return;
        }

        String email = request.getParameter("email");
        int hashPassword = request.getParameter("password").hashCode();

        Member member = MemberDAO.find(email, hashPassword);
        if (member == null) {
            pw.println(ServletHelper.ERROR);
            pw.println(ServletHelper.getHtmlRedirect("/authorization.jsp"));
            return;
        }

        request.getSession().setAttribute("authorization", true);
        request.getSession().setAttribute("member", member);

        pw.println(ServletHelper.SUCCESSFUL);
        pw.println(ServletHelper.getHtmlRedirect("/"));
    }

    boolean verificationParametersInRequest(HttpServletRequest request) {
        if (request.getParameter("email").length() < 4) {
            return false;
        }

        if (request.getParameter("password").length() < 4) {
            return false;
        }

        return true;
    }

}
