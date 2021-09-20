package action;

import bean.BoardBean;
import service.BoardMgr;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class editProcAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        BoardBean bean = (BoardBean) session.getAttribute("bean");
        String cPage = request.getParameter("cPage");

        int num = Integer.parseInt(request.getParameter("num"));
        int inpwd = Integer.parseInt(request.getParameter("inpwd"));
        int dbpwd = bean.getPass();
        String category = request.getParameter("category");
        String contents = request.getParameter("contents");
        String subject = request.getParameter("subject");

        BoardBean editBean = new BoardBean();
        editBean.setCategory(category);
        editBean.setContents(contents);
        editBean.setSubject(subject);
        editBean.setNum(num);

        BoardMgr bMgr = new BoardMgr();
        String msg;
        String url;
        if (inpwd != dbpwd) {
            msg = "게시글 비밀번호가 일치하지 않습니다.";

            request.setAttribute("msg", msg);
            request.setAttribute("alertType", 2);

            RequestDispatcher dispatcher = request.getRequestDispatcher("alert.jsp");
            dispatcher.forward(request, response);

        } else {
            bMgr.editBoard(editBean);
            msg = "수정이 완료되었습니다.";
            url = "read.bo?cPage=" + cPage + "&num=" + num;

            request.setAttribute("msg", msg);
            request.setAttribute("url", url);
            request.setAttribute("alertType", 1);

            RequestDispatcher dispatcher = request.getRequestDispatcher("alert.jsp");
            dispatcher.forward(request, response);
        }
    }
}
