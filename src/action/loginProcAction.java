package action;

import bean.MemberBean;
import service.MemberMgr;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class loginProcAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");

        MemberMgr mMgr = new MemberMgr();
        int result = mMgr.login(email, pwd);
        MemberBean mBean = mMgr.getMember(email);
        String msg;
        String url = "";
        HttpSession session = request.getSession();

        if (result == 1) {
            msg = mBean.getNick_name() + "님 로그인에 성공하였습니다.";
            url = "main.do";
            session.setAttribute("idKey", email);
            session.setAttribute("nick", mBean.getNick_name());
            request.setAttribute("alertType", 1);
        } else if (result == 0) {
            msg = "비밀번호를 다시한번 확인해주세요.";
            request.setAttribute("alertType", 2);
        } else {
            msg = "아이디를 다시한번 확인해주세요.";
            request.setAttribute("alertType", 2);
        }

        request.setAttribute("msg", msg);
        request.setAttribute("url", url);

        RequestDispatcher dispatcher = request.getRequestDispatcher("alert.jsp");
        dispatcher.forward(request, response);

    }
}
