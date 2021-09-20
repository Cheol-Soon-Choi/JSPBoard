package action;

import bean.BoardBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class editAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        BoardBean bean = (BoardBean) session.getAttribute("bean");
        int num = Integer.parseInt(request.getParameter("num"));
        String cPage = request.getParameter("cPage");
        String category = bean.getCategory();
        String contents = bean.getContents();
        String subject = bean.getSubject();

        request.setAttribute("num", num);
        request.setAttribute("cPage", cPage);
        request.setAttribute("category", category);
        request.setAttribute("contents", contents);
        request.setAttribute("subject", subject);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/board/edit.jsp?cPage="+cPage+"&num="+num);
        dispatcher.forward(request, response);
    }
}
