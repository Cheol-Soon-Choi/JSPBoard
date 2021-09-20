package action;

import bean.BoardBean;
import service.BoardMgr;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class deleteProcAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        int inpwd = Integer.parseInt(request.getParameter("inpwd"));
        int num = Integer.parseInt(request.getParameter("num"));
        String cPage = request.getParameter("cPage");

        BoardBean bean = (BoardBean) session.getAttribute("bean");
        int dbpwd = bean.getPass();

        BoardMgr bMgr = new BoardMgr();
        String msg;
        String url;
        if (dbpwd == inpwd) {
            bMgr.delBoard(num);
            msg = "삭제 완료!";
            url = "main.do?cPage=" + cPage;
            request.setAttribute("msg", msg);
            request.setAttribute("url", url);
            request.setAttribute("alertType", 3);

            RequestDispatcher dispatcher = request.getRequestDispatcher("alert.jsp");
            dispatcher.forward(request, response);
        } else {
            msg = "게시글 비밀번호가 일치하지 않습니다.";

            request.setAttribute("msg", msg);
            request.setAttribute("alertType", 2);

            RequestDispatcher dispatcher = request.getRequestDispatcher("alert.jsp");
            dispatcher.forward(request, response);
        }
    }
}
