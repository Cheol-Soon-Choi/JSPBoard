package action;

import bean.MemberBean;
import service.MemberMgr;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class memberUpdateProcAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        MemberBean mbean = new MemberBean();
        mbean.setDtladdress(request.getParameter("address2"));
        mbean.setZipcode(request.getParameter("zipcode"));
        mbean.setAddress(request.getParameter("address"));
        mbean.setNick_name(request.getParameter("nick_name"));
        mbean.setName(request.getParameter("name"));
        mbean.setPwd(request.getParameter("pwd"));
        mbean.setEmail(request.getParameter("email"));

        MemberMgr mMgr = new MemberMgr();
        boolean rs = mMgr.updateMember(mbean);

        String msg;
        String url = null;
        if (rs) {
            msg = "수정이 완료되었습니다.";
            url = "main.do";
            request.setAttribute("alertType", 1);
        } else {
            msg = "수정에 실패하였습니다.";
            request.setAttribute("alertType", 2);
        }

        request.setAttribute("msg", msg);
        request.setAttribute("url", url);

        RequestDispatcher dispatcher = request.getRequestDispatcher("alert.jsp");
        dispatcher.forward(request, response);

    }
}
