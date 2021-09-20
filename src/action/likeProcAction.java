package action;

import service.BoardMgr;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class likeProcAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int num = Integer.parseInt(request.getParameter("num"));
        int cPage = Integer.parseInt(request.getParameter("cPage"));
        BoardMgr bMgr = new BoardMgr();
        bMgr.upLike(num);
        response.sendRedirect("read.bo?cPage=" + cPage + "&num=" + num);
    }
}
