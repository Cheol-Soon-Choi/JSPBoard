package action;

import bean.MemberBean;
import service.MemberMgr;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class memberUpdateAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("idKey");

        MemberMgr mMgr = new MemberMgr();
        MemberBean mbean = mMgr.getMember(email);

        request.setAttribute("mbean", mbean);

        RequestDispatcher dispatcher = request.getRequestDispatcher("memberUpdate.jsp");
        dispatcher.forward(request, response);
    }
}
