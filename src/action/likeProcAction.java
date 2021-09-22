package action;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import service.BoardMgr;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class likeProcAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {
        String body = request.getReader().lines().collect(Collectors.joining());
        System.out.println(body);

        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(body);
        JSONObject data = (JSONObject) obj;
        int num = Integer.parseInt((String) data.get("num"));

        BoardMgr bMgr = new BoardMgr();
        bMgr.upLike(num);
        int rsnum = 0;
        rsnum = bMgr.getLike(num);

        JSONObject jobj = new JSONObject();
        jobj.put("num", rsnum);
        String k = jobj.toJSONString();
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(k);
    }
}
