package action;

import bean.ZipcodeBean;
import service.MemberMgr;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class zipSearchAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String search = request.getParameter("search");
        String area3 = null;
        ArrayList<ZipcodeBean> zlist = null;

        MemberMgr mMgr = new MemberMgr();
        if (search.equals("y")) {
            area3 = request.getParameter("area3");
            zlist = mMgr.zipcodeRead(area3);
        }
        request.setAttribute("search", search);
        request.setAttribute("area3", area3);
        request.setAttribute("zlist", zlist);

        RequestDispatcher dispatcher = request.getRequestDispatcher("zipSearch.jsp");
        dispatcher.forward(request, response);
    }
}
