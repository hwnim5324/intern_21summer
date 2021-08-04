/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plusgame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author hwnim
 */
public class userDAO {

    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String MARIADB_URL = "jdbc:mariadb://localhost:3306/plusgamedb";
    static final String MARIADB_ID = "hwnim";
    static final String MARIADB_PW = "nam5324";

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public userDAO() {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(MARIADB_URL, MARIADB_ID, MARIADB_PW);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int login(String userId, String userPw) {
        String sql_query = "SELECT userPw FROM userinfo WHERE userID = ?";
        try {
            ps = con.prepareStatement(sql_query);
            ps.setString(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getString(1).equals(userPw)) {
                    return 1;   //로그인 성공.
                } else {
                    return 0;   //비밀번호 불일치.
                }
            }
            return -1;  //아이디 없음.
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -2; //DB오류.
    }

    public int checkpw(String userPw, String checkuserPw){
        if(userPw.equals(checkuserPw)){
            return 1;   //비밀번호 확인 일치.
        }
        return -1;  //비밀번호 확인 불일치.
    }
    
    public int createaccount(user user) {
        String sql_query = "INSERT INTO userinfo VALUES (?,?,?)";
        try {
            ps = con.prepareStatement(sql_query);
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getUserPw());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;  //DB오류.
    }
}
