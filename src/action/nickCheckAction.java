package action;

import service.MemberMgr;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class nickCheckAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String nick = request.getParameter("nick_name");
        MemberMgr mMgr = new MemberMgr();
        boolean result = mMgr.nickCheck(nick);

        request.setAttribute("nick", nick);
        request.setAttribute("result", result);

        RequestDispatcher dispatcher = request.getRequestDispatcher("nickCheck.jsp");
        dispatcher.forward(request, response);

    }
}
