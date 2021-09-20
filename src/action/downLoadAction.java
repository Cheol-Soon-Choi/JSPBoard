package action;

import service.BoardMgr;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class downLoadAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        BoardMgr bMgr = new BoardMgr();
        bMgr.downLoad(request, response);
    }
}
