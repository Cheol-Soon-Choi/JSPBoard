package action;

import bean.BoardBean;
import bean.PagingBean;
import service.BoardMgr;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class mainAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        BoardMgr bMgr = new BoardMgr();
        int newCount = bMgr.getTodayCount();

        int cPage = 1;
        String keyField = "";
        String keyWord = "";

        if (request.getParameter("keyWord") != null) {
            keyField = request.getParameter("keyField");
            keyWord = request.getParameter("keyWord");
        }
        if (request.getParameter("cPage") != null) {
            cPage = Integer.parseInt(request.getParameter("cPage"));
        }

        PagingBean pb = new PagingBean();
        int totalRecord = bMgr.getCount(keyField, keyWord);
        pb.setcPage(cPage);
        pb.setTotalRecord(totalRecord);

        ArrayList<BoardBean> blist = null;
        blist = bMgr.getBoard(keyField, keyWord, pb.getStart(), pb.getEnd());

        request.setAttribute("keyWord", keyWord);
        request.setAttribute("keyField", keyField);
        request.setAttribute("newCount", newCount);
        request.setAttribute("blist", blist);
        request.setAttribute("cPage", cPage);
        request.setAttribute("pb", pb);

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("main.jsp");
        dispatcher.forward(request, response);
    }
}
