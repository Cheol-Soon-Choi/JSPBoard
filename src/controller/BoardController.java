package controller;

import action.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.do")
public class BoardController extends HttpServlet {

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String RequestURI = request.getRequestURI(); //JSPBoard/파일명
        String contextPath = request.getContextPath(); //JSPBoard
        String command = RequestURI.substring(contextPath.length());

        Action action = null;

        switch (command) {
            case "/board/member.do":
                action = new memberAction();
                break;
            case "/board/memberProc.do":
                action = new memberProcAction();
                break;
            case "/board/idCheck.do":
                action = new idCheckAction();
                break;
            case "/board/nickCheck.do":
                action = new nickCheckAction();
                break;
            case "/board/zipSearch.do":
                action = new zipSearchAction();
                break;
            case "/board/login.do":
                action = new loginAction();
                break;
            case "/board/logout.do":
                action = new logoutProcAction();
                break;
            case "/board/memberUpdate.do":
                action = new memberUpdateAction();
                break;
            case "/board/loginProc.do":
                action = new loginProcAction();
                break;
            case"/board/memberUpdateProc.do":
                action = new memberUpdateProcAction();
                break;
            case"/board/likeProc.do":
                action = new likeProcAction();
                break;
            case"/board/main.do":
                action = new mainAction();
                break;
            case"/board/prevnextProc.do":
                action = new prevnextProcAction();
                break;
            case"/board/downLoad.do":
                action = new downLoadAction();
                break;
        }
        try {
            action.execute(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doProcess(request, response);
    }
}
