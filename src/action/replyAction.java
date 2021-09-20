package action;

import bean.BoardBean;
import service.BoardMgr;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class replyAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //원글에서 넘어온 데이터
        HttpSession session = request.getSession();
        BoardBean bean = (BoardBean) session.getAttribute("bean");
        int num = Integer.parseInt(request.getParameter("num"));
        String cPage = request.getParameter("cPage");
        String category = bean.getCategory();
        String contents = bean.getContents();
        String subject = bean.getSubject();
        int pos = bean.getPos();
        int group = bean.getGroup();
        int level = bean.getLevel();

        request.setAttribute("num", num);
        request.setAttribute("cPage", cPage);
        request.setAttribute("category", category);
        request.setAttribute("contents", contents);
        request.setAttribute("subject", subject);
        request.setAttribute("pos", pos);
        request.setAttribute("group", group);
        request.setAttribute("level", level);

        RequestDispatcher dispatcher = request.getRequestDispatcher("reply.jsp?cPage="+cPage+"&num="+num);
        dispatcher.forward(request, response);

    }
}
