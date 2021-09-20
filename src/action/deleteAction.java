package action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class deleteAction implements Action{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int num = Integer.parseInt(request.getParameter("num"));
        String cPage = request.getParameter("cPage");

        request.setAttribute("num", num);
        request.setAttribute("cPage", cPage);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/board/delete.jsp?cPage="+cPage+"&num="+num);
        dispatcher.forward(request, response);
    }
}
