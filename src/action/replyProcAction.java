package action;

import bean.BoardBean;
import service.BoardMgr;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class replyProcAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        BoardBean rebean = new BoardBean();
        String reemail = (String) session.getAttribute("idKey");
        String recategory = request.getParameter("category");
        String resubject = request.getParameter("subject");
        String recontents = request.getParameter("contents");
        int repos = Integer.parseInt(request.getParameter("pos"));
        int relevel = Integer.parseInt(request.getParameter("level"));
        int regroup = Integer.parseInt(request.getParameter("group"));
        int repass = Integer.parseInt(request.getParameter("inpwd"));
        String cPage = request.getParameter("cPage");

        rebean.setEmail(reemail);
        rebean.setCategory(recategory);
        rebean.setSubject(resubject);
        rebean.setContents(recontents);
        rebean.setPos(repos);
        rebean.setLevel(relevel);
        rebean.setGroup(regroup);
        rebean.setPass(repass);

        BoardMgr bMgr = new BoardMgr();
        bMgr.replyBoard(rebean);
        response.sendRedirect("main.do?cPage=" + cPage);
    }
}
