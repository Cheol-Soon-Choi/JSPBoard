package action;

import bean.BoardBean;
import service.BoardMgr;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class readAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("idKey");
        String msg;
        String url;
        if (email == null || email.equals("")) {
            msg = "로그인 바랍니다.";
            url = "login.do";
            request.setAttribute("msg", msg);
            request.setAttribute("url", url);
            request.setAttribute("alertType", 1);

            RequestDispatcher dispatcher = request.getRequestDispatcher("alert.jsp");
            dispatcher.forward(request, response);
        } else {
            int num = Integer.parseInt(request.getParameter("num"));

            String cPage = request.getParameter("cPage");
            String keyField = request.getParameter("keyField");
            String keyWord = request.getParameter("keyWord");

            request.setAttribute("num", num);
            request.setAttribute("cPage", cPage);
            request.setAttribute("keyField", keyField);
            request.setAttribute("keyWord", keyWord);
            request.setAttribute("email", email);

            BoardMgr bMgr = new BoardMgr();
            BoardBean bean = bMgr.readBoard(num);
            session.setAttribute("bean", bean);
            bMgr.upCount(num);

            // 좋아요 상태 전달
            HashMap<String, Object> rs = bMgr.checkLike(num, email);
            boolean check = (boolean) rs.get("check");
            boolean flag = (boolean) rs.get("flag");
            request.setAttribute("check", check);
            request.setAttribute("flag", flag);

            RequestDispatcher dispatcher = request.getRequestDispatcher("read.jsp");
            dispatcher.forward(request, response);
        }
    }
}
