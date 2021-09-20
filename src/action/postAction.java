package action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class postAction implements Action{
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

            request.setAttribute("email", email);

            RequestDispatcher dispatcher = request.getRequestDispatcher("post.jsp");
            dispatcher.forward(request, response);

        }
    }
}
