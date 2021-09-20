package service;

import bean.MemberBean;
import bean.ZipcodeBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberMgr {

    private DBConnectionMgr pool;

    public MemberMgr() {
        try {
            pool = DBConnectionMgr.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //회원가입
    public boolean insertMember(MemberBean bean) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        boolean flag = false;
        try {
            con = pool.getConnection();
            sql = "insert member(email, pwd, name, nick_name, address, dtladdress, zipcode, regdate)"
                    + "values(?, ?, ?, ?, ?, ?, ?, now())";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bean.getEmail());
            pstmt.setString(2, bean.getPwd());
            pstmt.setString(3, bean.getName());
            pstmt.setString(4, bean.getNick_name());
            pstmt.setString(5, bean.getAddress());
            pstmt.setString(6, bean.getDtladdress());
            pstmt.setString(7, bean.getZipcode());
            if (pstmt.executeUpdate() == 1) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        return flag;
    }

    //로그인
    public int login(String email, String pwd) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        int flag = -1;
        String dbpwd = "";
        try {
            con = pool.getConnection();
            sql = "select pwd from member where email=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();

            if (rs.next()) { // 아이디 o
                dbpwd = rs.getString("pwd");
                if (pwd.equals(dbpwd)) {
                    flag = 1; //패스워드 확인
                } else {
                    flag = 0; //패스워드 틀림
                }
            } else {
                flag = -1; // 아이디x
            }
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        return flag;
    }

    //회원정보가져오기
    public MemberBean getMember(String email) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        MemberBean bean = null;
        try {
            con = pool.getConnection();
            sql = "select * from member where email = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                bean = new MemberBean();
                bean.setEmail(rs.getString("email"));
                bean.setPwd(rs.getString("pwd"));
                bean.setAddress(rs.getString("address"));
                bean.setDtladdress(rs.getString("dtladdress"));
                bean.setName(rs.getString("name"));
                bean.setNick_name(rs.getString("nick_name"));
                bean.setZipcode(rs.getString("zipcode"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        return bean;
    }

    //id 중복확인
    public boolean idCheck(String email) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        boolean flag = false;
        try {
            con = pool.getConnection();
            sql = "select * from member where email=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            flag = rs.next(); // id 있으면 true, 없으면 false

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        return flag;
    }

    //별명 중복확인
    public boolean nickCheck(String nick) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        boolean flag = false;
        try {
            con = pool.getConnection();
            sql = "select * from member where nick_name=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nick);
            rs = pstmt.executeQuery();
            flag = rs.next(); // 별명 있으면 true, 없으면 false

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        return flag;
    }

    //회원정보수정하기
    public boolean updateMember(MemberBean m) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        boolean flag = false;
        try {
            con = pool.getConnection();
            sql = "UPDATE member SET pwd=?, name=?, address=?, dtladdress=?, zipcode=? where email=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, m.getPwd());
            pstmt.setString(2, m.getName());
            pstmt.setString(3, m.getAddress());
            pstmt.setString(4, m.getDtladdress());
            pstmt.setString(5, m.getZipcode());
            pstmt.setString(6, m.getEmail());
            int count = pstmt.executeUpdate();
            if (count == 1) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        return flag;
    }

    //zip코드 가져오기
    public ArrayList<ZipcodeBean> zipcodeRead(String area3) {
        Connection con = null;
        String sql = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<ZipcodeBean> zlist = new ArrayList<>();
        try {
            con = pool.getConnection();
            sql = "select * from zipcode where area3 like ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + area3 + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ZipcodeBean zBean = new ZipcodeBean();
                zBean.setZipcode(rs.getString(1));
                zBean.setArea1(rs.getString(2));
                zBean.setArea2(rs.getString(3));
                zBean.setArea3(rs.getString(4));
                zlist.add(zBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        return zlist;
    }
}
