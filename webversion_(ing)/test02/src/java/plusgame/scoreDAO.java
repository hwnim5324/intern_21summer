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
import java.util.ArrayList;
import org.json.JSONArray;

public class scoreDAO {

    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String MARIADB_URL = "jdbc:mariadb://localhost:3306/plusgamedb";
    static final String MARIADB_ID = "hwnim";
    static final String MARIADB_PW = "nam5324";

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public scoreDAO() {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(MARIADB_URL, MARIADB_ID, MARIADB_PW);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<score> getRankList() {
        String sql_query = "SELECT * FROM gamerecord ORDER BY score DESC LIMIT 5";
        ArrayList<score> list = new ArrayList<score>();
        try {
            ps = con.prepareStatement(sql_query);
            rs = ps.executeQuery();
            while (rs.next()) {
                score record = new score();
                record.setUserId(rs.getString("userId"));
                record.setScore(rs.getInt("score"));
                record.setTime(rs.getString("time"));
                record.setqCode(rs.getInt("qCode"));
                list.add(record);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<score> getMyList(String userId) {
        String sql_query = "SELECT * FROM gamerecord WHERE userid=? ORDER BY score DESC LIMIT 10";
        ArrayList<score> list = new ArrayList<score>();
        try {
            ps = con.prepareStatement(sql_query);
            ps.setString(1,userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                score record = new score();
                record.setScore(rs.getInt("score"));
                record.setTime(rs.getString("time"));
                record.setqCode(rs.getInt("qCode"));
                list.add(record);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public score getdetail(int qCode){
        String sql_query = "SELECT * FROM gamerecord WHERE qCode = ?";
        try {
            ps = con.prepareStatement(sql_query);
            ps.setInt(1, qCode);
            rs = ps.executeQuery();
            while (rs.next()) {
                score record = new score();
                record.setUserId(rs.getString("userId"));
                record.setUserLevel(rs.getString("userLevel"));
                record.setUserNumofquest(rs.getInt("userNumofquest"));
                record.setScore(rs.getInt("score"));
                record.setTime(rs.getString("time"));
                record.setUserAnswer(rs.getString("userAnswer"));
                return record;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Integer> transformUserAnswer(int qCode){
        String sql_query = "SELECT userAnswer FROM gamerecord WHERE qCode = ?";
        try {
            JSONArray jarray;
            ps = con.prepareStatement(sql_query);
            ps.setInt(1, qCode);
            rs = ps.executeQuery();
            if (rs.next()) {
                jarray = new JSONArray(rs.getString("userAnswer"));
                ArrayList<Integer> ilist = new ArrayList();
                for(int i=0;i<jarray.length();i++){
                    ilist.add(jarray.getInt(i));
                }
                return ilist;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<questions> getquestions(int qCode){
        String sql_query = "SELECT * FROM questions WHERE qCode = ? ORDER BY qNumber ASC";
        ArrayList<questions> list = new ArrayList<questions>();
        try {
            ps = con.prepareStatement(sql_query);
            ps.setInt(1, qCode);
            rs = ps.executeQuery();
            while (rs.next()) {
                questions record = new questions();
                record.setqNumber(rs.getInt("qNumber"));
                record.setQuestions(rs.getString("question"));
                record.setRightAnswer(rs.getInt("rightAnswer"));
                list.add(record);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    

}
