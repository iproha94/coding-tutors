package com.wordpress.ilyaps.servlets;

import com.wordpress.ilyaps.Logic.Compliance;
import com.wordpress.ilyaps.dao.MemberDAO;
import com.wordpress.ilyaps.dao.TaskDAO;
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

/**
 * Created by ilyap on 21.01.2016.
 */
public class WantToHelpServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        if (!verificationParametersInRequest(request)) {
            pw.println(ServletHelper.BAD_FORM);
            pw.println(ServletHelper.getHtmlRedirect("/list-tasks.jsp"));
            return;
        }

        WantToHelp wantToHelp = new WantToHelp();

        wantToHelp.setNote(request.getParameter("note"));

        int taskId = new Integer(request.getParameter("task-id"));
        Task task =TaskDAO.find(taskId);
        wantToHelp.setTask(task);

        String memberHelperEmail = request.getParameter("member-email");
        Member helper = MemberDAO.find(memberHelperEmail);
        wantToHelp.setMemberHelper(helper);

        Member need = task.getMemberNeed();

        wantToHelp.setLevelOfCompliance(Compliance.getComplianceMembers(need, helper));

        if (!WantToHelpDAO.insert(wantToHelp)) {
            pw.println(ServletHelper.ERROR);
            pw.println(ServletHelper.getHtmlRedirect("/list-tasks.jsp"));
            return;
        }

        task.incCountWantToHelp();
        TaskDAO.update(task);

        pw.println(ServletHelper.SUCCESSFUL);
        pw.println(ServletHelper.getHtmlRedirect("list-tasks.jsp"));
    }

    boolean verificationParametersInRequest(HttpServletRequest request) {
        return request.getParameter("note").length() >= 4;

    }
}
