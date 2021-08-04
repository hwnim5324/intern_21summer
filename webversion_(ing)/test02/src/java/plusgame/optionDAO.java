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

public class optionDAO {
    
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String MARIADB_URL = "jdbc:mariadb://localhost:3306/plusgamedb";
    static final String MARIADB_ID = "hwnim";
    static final String MARIADB_PW = "nam5324";

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public optionDAO() {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(MARIADB_URL, MARIADB_ID, MARIADB_PW);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public option getoption(String userId){
        String sql_query = "SELECT * FROM option WHERE userId = ?";
        try {
            ps = con.prepareStatement(sql_query);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                option user = new option();
                user.setUserId(userId);
                user.setUserLevel(rs.getString("userLevel"));
                user.setUserNumofquest(rs.getInt("userNumofquest"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int setoption(option user){
        String sql_query = "UPDATE option SET userNumofquest=?, userLevel=? WHERE  userId=?";
        try {
            ps = con.prepareStatement(sql_query);
            ps.setInt(1, user.getUserNumofquest());
            ps.setString(2, user.getUserLevel());
            ps.setString(3, user.getUserId());
            ps.executeUpdate();
            return 1;   //저장성공.
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;  //DB오류.
    }
    
    public int newoption(String userId){
        String sql_query = "INSERT INTO option VALUES (?,?,?)";
        try {
            ps = con.prepareStatement(sql_query);
            ps.setString(1, "상");
            ps.setString(2, userId);
            ps.setInt(3, 10);
            ps.executeUpdate();
            return 1;   //저장성공.
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;  //DB오류.
    }
    
    
}
