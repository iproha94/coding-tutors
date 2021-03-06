package com.wordpress.ilyaps.servlets;

import com.wordpress.ilyaps.logic.Compliance;
import com.wordpress.ilyaps.dao.BaseDAO;
import com.wordpress.ilyaps.dao.WantToHelpDAO;
import com.wordpress.ilyaps.models.Member;
import com.wordpress.ilyaps.models.Task;
import com.wordpress.ilyaps.models.WantToHelp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WantToHelpServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        if (!verificationParametersInRequest(request)) {
            pw.println(ServletHelper.BAD_FORM);
            pw.println(ServletHelper.getHtmlRedirect("/list-tasks.jsp"));
            return;
        }

        WantToHelp wantToHelp = new WantToHelp();

        wantToHelp.setNote(request.getParameter("note"));

        int taskId = new Integer(request.getParameter("task-id"));
        Task task =(Task) BaseDAO.find(Task.class, taskId);

        String memberHelperEmail = request.getParameter("member-email");
        Member helper = (Member) BaseDAO.find(Member.class, memberHelperEmail);
        wantToHelp.setMemberHelper(helper);

        Member need = task.getMemberNeed();

        wantToHelp.setLevelOfCompliance(Compliance.getComplianceMembers(need, helper) + helper.getLikes());


        WantToHelpDAO.addWantToHelp(task, wantToHelp);

        pw.println(ServletHelper.SUCCESSFUL);
        pw.println(ServletHelper.getHtmlRedirect("list-tasks.jsp"));
    }

    private boolean verificationParametersInRequest(HttpServletRequest request) {
        return request.getParameter("note").length() >= 4;

    }
}
