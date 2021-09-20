package controller;

import action.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.bo")
public class CRUDController extends HttpServlet {

    public void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String RequestURI = request.getRequestURI();  //JSPBoard/파일명
        String contextPath = request.getContextPath();  //JSPBoard
        String command = RequestURI.substring(contextPath.length());

        Action action = null;

        switch (command) {
            case "/board/read.bo":
                action = new readAction();
                break;
            case "/board/reply.bo":
                action = new replyAction();
                break;
            case "/board/replyProc.bo":
                action = new replyProcAction();
                break;
            case "/board/edit.bo":
                action = new editAction();
                break;
            case "/board/editProc.bo":
                action = new editProcAction();
                break;
            case "/board/delete.bo":
                action = new deleteAction();
                break;
            case "/board/deleteProc.bo":
                action = new deleteProcAction();
                break;
            case "/board/post.bo":
                action = new postAction();
                break;
            case "/board/postProc.bo":
                action = new postProcAction();
                break;
        }

        try {
            action.execute(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }
}
