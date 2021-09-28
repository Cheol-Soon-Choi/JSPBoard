package action;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import service.BoardMgr;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

public class likeProcAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {
        String body = request.getReader().lines().collect(Collectors.joining());

        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(body);
        JSONObject data = (JSONObject) obj;
        int num = Integer.parseInt((String) data.get("num"));
        String email = (String) data.get("email");
        BoardMgr bMgr = new BoardMgr();

        //like db 확인
        HashMap<String, Object> rs = bMgr.checkLike(num, email);
        boolean check = (boolean) rs.get("check");
        boolean flag = (boolean) rs.get("flag");

        if(!check){ // like db에 data 없음
            bMgr.upLike(num); // 좋아요+1
            bMgr.insertLike(num, email); // like db 추가
        }
        else { // like db에 data 있음.
            if(!flag){ // 이전 클릭으로 좋아요 해제한 상태 => 좋아요+1 해줘야 함
                bMgr.upLike(num);
            }else { // 이전 클릭으로 좋아요 누른 상태 => 좋아요-1 해줘야 함
                bMgr.downLike(num);
            }
            bMgr.editLike(num, email, flag);
        }

        //좋아요 갯수 전달
        int rsnum = 0;
        rsnum = bMgr.getLike(num);
        JSONObject jobj = new JSONObject();
        jobj.put("num", rsnum);
        jobj.put("flag", flag);
        String k = jobj.toJSONString();
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(k);
    }
}