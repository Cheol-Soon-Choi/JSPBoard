package action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class logoutProcAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        session.invalidate();

        String msg = "로그아웃 되었습니다.";
        String url = "main.do";

        request.setAttribute("alertType", 1);
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);

        RequestDispatcher dispatcher = request.getRequestDispatcher("alert.jsp");
        dispatcher.forward(request, response);
    }
}
