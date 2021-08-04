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


public class questionsDAO {
    
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String MARIADB_URL = "jdbc:mariadb://localhost:3306/plusgamedb";
    static final String MARIADB_ID = "hwnim";
    static final String MARIADB_PW = "nam5324";

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public questionsDAO() {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(MARIADB_URL, MARIADB_ID, MARIADB_PW);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
    
    public boolean nextquestion(int qCode,int qNumber){
        String sql_query = "SELECT * FROM questions WHERE qCode = ? AND qNumber = ?";
        try {
            ps = con.prepareStatement(sql_query);
            ps.setInt(1, qCode);
            ps.setInt(2, qNumber);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public int recentqCode(){
        String sql_query = "SELECT qCode FROM questions ORDER BY qCode DESC LIMIT 1";
        try{
            ps = con.prepareStatement(sql_query);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("qCode");
            }
            return 1;   //1번 문제인 경우.
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;  //DB오류.
    }
    
    public String getquestion(int qCode, int qNumber){
        String sql_query = "SELECT question FROM questions WHERE qCode = ? AND qNumber = ?";
        try {
            ps = con.prepareStatement(sql_query);
            ps.setInt(1, qCode);
            ps.setInt(2, qNumber);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int setquestions(int qCode, int qNumber, String question, int rightAnswer){
        String sql_query = "INSERT INTO questions VALUES (?,?,?,?)";
        try{
            ps = con.prepareStatement(sql_query);
            ps.setInt(1, qCode);
            ps.setInt(2, qNumber);
            ps.setString(3, question);
            ps.setInt(4, rightAnswer);
            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;  //DB오류.
    }
    
}
