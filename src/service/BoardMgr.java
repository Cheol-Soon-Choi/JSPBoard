package service;

import bean.BoardBean;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class BoardMgr {

    private DBConnectionMgr pool;
    private static final String SAVEFOLDER = "C:/Users/CCS/IdeaProjects/JSPBoard/web/upload";
    private static final String ENCTYPE = "UTF-8";
    private static final int MAXSIZE = 5 * 1024 * 1024;

    public BoardMgr() {
        try {
            pool = DBConnectionMgr.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //게시판 리스트
    public ArrayList<BoardBean> getBoard(String keyField, String keyWord, int start, int end) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        ArrayList<BoardBean> blist = new ArrayList<>();
        try {
            con = pool.getConnection();
            if (keyWord.equals("") || keyWord == null) {
                sql = "select * from board where deldate is null order by pos desc, `group` limit ?,?";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, start);
                pstmt.setInt(2, end);
            } else {
                sql = "select * from  board where deldate is null and " + keyField + " like ?";
                sql += "order by pos desc, `group` limit ? , ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, "%" + keyWord + "%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, end);
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BoardBean boardBean = new BoardBean();
                boardBean.setNum(rs.getInt("num"));
                boardBean.setNick_name(rs.getString("nick_name"));
                boardBean.setName(rs.getString("name"));
                boardBean.setSubject(rs.getString("subject"));
                boardBean.setContents(rs.getString("contents"));
                boardBean.setCategory(rs.getString("category"));
                boardBean.setGroup(rs.getInt("group"));
                boardBean.setPos(rs.getInt("pos"));
                boardBean.setLevel(rs.getInt("level"));
                boardBean.setRegdate(rs.getString("regdate"));
                boardBean.setCount(rs.getInt("count"));
                boardBean.setLike(rs.getInt("like"));
                blist.add(boardBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        return blist;
    }

    //게시판 수 (24시간 이내 등록된 글)
    public int getTodayCount() {
        Connection con = null;
        ResultSet rs = null;
        String sql = null;
        Statement stmt = null;
        int todaycount = 0;
        try {
            con = pool.getConnection();
            stmt = con.createStatement();
            sql = "select count(*) from board where deldate is null and regdate between DATE_ADD(now(), INTERVAL -1 day) and now()";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                todaycount = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, stmt, rs);
        }
        return todaycount;
    }

    //게시판 수(토탈||검색)
    public int getCount(String keyField, String keyWord) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        int count = 0;
        try {
            con = pool.getConnection();
            if (keyWord.equals("") || keyWord == null) {
                sql = "select count(regdate) from board where deldate is null";
                pstmt = con.prepareStatement(sql);
            } else {
                sql = "select count(regdate) from board where deldate is null and " + keyField + " like ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, "%" + keyWord + "%");
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        return count;
    }

    //게시글 등록
    public void insertBoard(HttpServletRequest req) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        MultipartRequest multi = null;
        try {
            con = pool.getConnection();

            //파일 폴더 생성
            File file = new File(SAVEFOLDER);
            if (!file.exists()) {
                file.mkdirs();
            }
            multi = new MultipartRequest(req, SAVEFOLDER, MAXSIZE, ENCTYPE,
                    new DefaultFileRenamePolicy());
            //파일 이름, 사이즈 구하기
            int filesize = 0;
            String filename = null;
            if (multi.getFilesystemName("filename") != null) {
                filename = multi.getFilesystemName("filename");
                filesize = (int) multi.getFile("filename").length();
            }

            //이메일로 이름, 닉네임 구하기
            String nick_name = null;
            String name = null;
            sql = "select * from member where email=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, multi.getParameter("email"));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                nick_name = rs.getString("nick_name");
                name = rs.getString("name");
            }

            //pos 구하기
            int pos = 0;
            sql = "select max(pos) from board";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                pos = rs.getInt(1) + 1;
            }

            //게시물 db올리기
            sql = "insert board(email, nick_name, name, subject, contents, category, `group`, pos, level, regdate, pass, count, `like`, file_name, file_size)";
            sql += "value(?, ?, ?, ?, ?, ?, 0, ?, 0, now(), ?, 0, 0, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, multi.getParameter("email"));
            pstmt.setString(2, nick_name);
            pstmt.setString(3, name);
            pstmt.setString(4, multi.getParameter("subject"));
            pstmt.setString(5, multi.getParameter("contents"));
            pstmt.setString(6, multi.getParameter("category"));
            pstmt.setInt(7, pos);
            pstmt.setString(8, multi.getParameter("pass"));
            pstmt.setString(9, filename);
            pstmt.setInt(10, filesize);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
    }

    //게시판 읽기
    public BoardBean readBoard(int num) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        BoardBean b = new BoardBean();
        try {
            con = pool.getConnection();
            sql = "select * from board where num =?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                b.setNum(rs.getInt("num"));
                b.setNick_name(rs.getString("nick_name"));
                b.setSubject(rs.getString("subject"));
                b.setContents(rs.getString("contents"));
                b.setCategory(rs.getString("category"));
                b.setRegdate(rs.getString("regdate"));
                b.setEdtdate(rs.getString("edtdate"));
                b.setPass(rs.getInt("pass"));
                b.setCount(rs.getInt("count"));
                b.setLike(rs.getInt("like"));
                b.setFile_name(rs.getString("file_name"));
                b.setFile_size(rs.getInt("file_size"));
                b.setPos(rs.getInt("pos"));
                b.setGroup(rs.getInt("group"));
                b.setLevel(rs.getInt("level"));
                b.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        return b;
    }

    //조회수 증가
    public void upCount(int num) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;
        try {
            con = pool.getConnection();
            sql = "update board set count=count+1 where num=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, num);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt);
        }
    }

    //좋아요 +1
    public void upLike(int num) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;
        try {
            con = pool.getConnection();
            sql = "update board set `like`=`like`+1 where num=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, num);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt);
        }
    }

    //좋아요 숫자 확인
    public int getLike(int num) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        int like = 0;
        try {
            con = pool.getConnection();
            sql = "select `like` from board where num=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                like = rs.getInt("like");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt);
        }
        return like;
    }

    //좋아요 db 확인
    public HashMap<String, Object> checkLike(int num, String email) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;

        HashMap<String, Object> data = new HashMap<>();
        boolean check = false;
        boolean flag = false;
        try {
            con = pool.getConnection();
            sql = "select flag from likes where num=? and email =?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, num);
            pstmt.setString(2, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                check = true;
                flag = rs.getBoolean("flag");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        data.put("check", check);
        data.put("flag", flag);
        return data;
    }

    //좋아요 db 등록
    public void insertLike(int num, String email) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;
        try {
            con = pool.getConnection();
            sql = "insert likes(email, num, flag) values (?, ?, 1)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setInt(2, num);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt);
        }
    }

    //좋아요 -1
    public void downLike(int num) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;
        try {
            con = pool.getConnection();
            sql = "update board set `like`=`like`-1 where num=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, num);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt);
        }
    }

    //좋아요 db 수정
    public void editLike(int num, String email, boolean flag) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;
        try {
            con = pool.getConnection();
            sql = "update likes set flag = if(? = false, true, false) where num=? and email=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setBoolean(1, flag);
            pstmt.setInt(2, num);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt);
        }
    }

    //이전글 이동
    public int prevBoard(int num) {
        Connection con = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        int prevnum = 0;
        try {
            con = pool.getConnection();
            sql = "select * from board where deldate is null ORDER BY pos desc, `group`";
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            while (rs.next()) { // 현재 글번호에서 커서 중지
                if (rs.getInt("num") == num) {
                    break;
                }
            }
            if (rs.previous()) { // 커서를 이전행으로 이동
                prevnum = rs.getInt("num");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, stmt, rs);
        }
        return prevnum;
    }

    //다음글 이동
    public int nextBoard(int num) {
        Connection con = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        int nextnum = 0;
        try {
            con = pool.getConnection();
            sql = "select * from board where deldate is null ORDER BY pos desc, `group`";
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getInt("num") == num) {
                    break;
                }
            }
            if (rs.next()) { // 커서를 다음행으로 이동
                nextnum = rs.getInt("num");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, stmt, rs);
        }
        return nextnum;
    }


    //게시물 삭제
    public void delBoard(int num) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;
        try {
            con = pool.getConnection();
            sql = "update board set deldate=now() where num=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, num);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt);
        }
    }

    //게시물 수정
    public void editBoard(BoardBean bean) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;
        String subject = bean.getSubject();
        String contents = bean.getContents();
        String category = bean.getCategory();
        int num = bean.getNum();
        try {
            con = pool.getConnection();
            sql = "UPDATE board set subject=?, contents=?, category=?, edtdate=now() where num=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, subject);
            pstmt.setString(2, contents);
            pstmt.setString(3, category);
            pstmt.setInt(4, num);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt);
        }
    }

    //게시물 답변
    public void replyBoard(BoardBean bean) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        try {
            con = pool.getConnection();

            //이메일로 이름, 닉네임 구하기
            String nick_name = null;
            String name = null;
            sql = "select * from member where email=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bean.getEmail());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                nick_name = rs.getString("nick_name");
                name = rs.getString("name");
            }

            //중간에 넣을 자리 만들기
            sql = "update board set `group` = `group`+1 where pos=? and `group`>?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, bean.getPos());
            pstmt.setInt(2, bean.getGroup());
            pstmt.executeUpdate();

            //db등록(만든자리에 게시물 등록)
            sql = "insert board (email, nick_name, name, subject, contents, category, pos, level,`group`, regdate, pass, count, `like`)";
            sql += "value(?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, 0, 0)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bean.getEmail());
            pstmt.setString(2, nick_name);
            pstmt.setString(3, name);
            pstmt.setString(4, bean.getSubject());
            pstmt.setString(5, bean.getContents());
            pstmt.setString(6, bean.getCategory());
            pstmt.setInt(7, bean.getPos());
            pstmt.setInt(8, bean.getLevel() + 1);
            pstmt.setInt(9, bean.getGroup() + 1);
            pstmt.setInt(10, bean.getPass());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
    }

    //파일 다운로드
    public void downLoad(HttpServletRequest req, HttpServletResponse res) {
        try {
            String filename = req.getParameter("filename");
            String Path = SAVEFOLDER + File.separator + filename;
            String enPath = new String(Path.getBytes("8859_1"), "ksc5601");
            File file = new File(enPath); // 파일 객체 생성

            //헤더 설정
            res.setHeader("Accept-Ranges", "bytes"); // 다운로드 중 끊기면 중간부터 다시
            String client = req.getHeader("User-Agent"); // 브라우저 확인 + 한글 파일명
            if (client.indexOf("MSIE") != -1 || client.indexOf("Trident") != -1) {
                res.setContentType("application/smnet;charset=UTF-8");
                res.setHeader("Content-Disposition", "filename=" + filename + ";");
            } else {
                res.setContentType("application/smnet;charset=UTF-8");
                res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8") + ";");
            }

            if (file.isFile()) {
                BufferedInputStream fin = new BufferedInputStream(
                        new FileInputStream(file));
                BufferedOutputStream outs = new BufferedOutputStream(
                        res.getOutputStream());
                byte[] b = new byte[(int) file.length()]; // 파일 크기
                int read = 0;
                while ((read = fin.read(b)) != -1) { // 파일 크기(b) 만큼씩 반복해서 읽음, 다 읽으면 -1
                    outs.write(b, 0, read); // 출력스트림에 반복해서 저장함
                }
                outs.close();
                fin.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //테스트
    public void insertTest() {
        Connection con = null;
        String sql = null;
        PreparedStatement pstmt = null;
        try {
            con = pool.getConnection();
            sql = "insert board (pos, `group`, email, nick_name, name, subject, contents, category, level, regdate, pass, count, `like`) " +
                    "values (1, 0, 'a', 'aa', 'aaa', '테스트123', '냉무', '일반', 0, now(), 1, 1, 100)";
            pstmt = con.prepareStatement(sql);
            for (int i = 0; i < 100; i++) {
                pstmt.executeUpdate();
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt);
        }
    }

    public static void main(String[] args) {
        BoardMgr b = new BoardMgr();
        b.insertTest();
    }
}



