package action;

import service.MemberMgr;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class idCheckAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        MemberMgr mMgr = new MemberMgr();
        boolean result = mMgr.idCheck(email);
        request.setAttribute("email", email);
        request.setAttribute("result", result);

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("idCheck.jsp");
        dispatcher.forward(request, response);
    }
}
