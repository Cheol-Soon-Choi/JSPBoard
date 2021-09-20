package action;

import bean.MemberBean;
import service.MemberMgr;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class memberProcAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberBean bean = new MemberBean();
        bean.setEmail(request.getParameter("email"));
        bean.setName(request.getParameter("name"));
        bean.setPwd(request.getParameter("pwd"));
        bean.setNick_name(request.getParameter("nick_name"));
        bean.setAddress(request.getParameter("address"));
        bean.setZipcode(request.getParameter("zipcode"));
        bean.setDtladdress(request.getParameter("address2"));

        MemberMgr mMgr = new MemberMgr();
        boolean result = mMgr.insertMember(bean);

        response.setCharacterEncoding("UTF-8");

        String msg;
        String url;
        if (result) {
            msg = "회원가입에 성공했습니다!";
            url = "login.do";
            request.setAttribute("msg", msg);
            request.setAttribute("url", url);
            request.setAttribute("alertType", 1);

        } else {
            msg = "회원가입에 실패했습니다.";
            request.setAttribute("msg", msg);
            request.setAttribute("alertType", 2);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("alert.jsp");
        dispatcher.forward(request, response);
    }
}
