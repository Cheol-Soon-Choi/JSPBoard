package action;

import service.BoardMgr;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class prevnextProcAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int num = Integer.parseInt(request.getParameter("num"));
        String movingP = request.getParameter("movingP");
        String cPage = request.getParameter("cPage");
        int rownum = 0;
        String msg;
        String url;

        BoardMgr bMgr = new BoardMgr();
        if (movingP.equals("이전글")) { // 이전글
            rownum = bMgr.prevBoard(num);
            if (rownum != 0) {
                response.sendRedirect("read.bo?cPage=" + cPage + "&num=" + rownum);
            } else {
                msg = "첫 글입니다.";
                url = "read.bo?cPage=" + cPage + "&num=" + num;
                request.setAttribute("msg", msg);
                request.setAttribute("url", url);
                request.setAttribute("alertType", 1);
                RequestDispatcher dispatcher = request.getRequestDispatcher("alert.jsp");
                dispatcher.forward(request, response);
            }
        } else { // 다음글
            rownum = bMgr.nextBoard(num);
            if (rownum != 0) {
                response.sendRedirect("read.bo?cPage=" + cPage + "&num=" + rownum);
            } else {
                msg = "마지막 글입니다.";
                url = "read.bo?cPage=" + cPage + "&num=" + num;
                request.setAttribute("msg", msg);
                request.setAttribute("url", url);
                request.setAttribute("alertType", 1);
                RequestDispatcher dispatcher = request.getRequestDispatcher("alert.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}
