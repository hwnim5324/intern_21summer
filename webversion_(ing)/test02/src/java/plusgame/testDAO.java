package plusgame;

import java.sql.*;
import java.util.ArrayList;

public class testDAO {
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String MARIADB_URL = "jdbc:mariadb://localhost:3306/plusgamedb";
    static final String MARIADB_ID = "hwnim";
    static final String MARIADB_PW = "nam5324";
    
    public void insert_GameRecordDB(String userId, int qCode, int score, String time, String userLevel, int userNumofquest, String userAnswer) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection con = DriverManager.getConnection(MARIADB_URL,MARIADB_ID,MARIADB_PW);
        PreparedStatement ps; 
        
        String sql_query = "INSERT INTO gamerecord VALUES (?,?,?,?,?,?,?)";
        ps = con.prepareStatement(sql_query);
        ps.setString(1, userId);
        ps.setInt(2, qCode);
        ps.setInt(3, score);
        ps.setString(4, time);
        ps.setString(5, userLevel);
        ps.setInt(6, userNumofquest);
        ps.setString(7, userAnswer);
        
        ps.executeUpdate();
        
        ps.close();
        con.close();
    }
    
    public void insert_OptionDB(String userId, String userLevel, String userNumofquest) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection con = DriverManager.getConnection(MARIADB_URL,MARIADB_ID,MARIADB_PW);
        PreparedStatement ps;
        
        String sql_query = "INSERT INTO option VALUES (?,?,?)";
        ps = con.prepareStatement(sql_query);
        ps.setString(1, userId);
        ps.setString(2, userLevel);
        ps.setString(3, userNumofquest);
        
        ps.executeUpdate();
        ps.close();
        con.close();
    }
    
    public void insert_QuestionsDB(int qCode, int qNumber, String question, int rightAnswer) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection con = DriverManager.getConnection(MARIADB_URL,MARIADB_ID,MARIADB_PW);
        PreparedStatement ps;
        
        String sql_query = "INSERT INTO questions VALUES (?,?,?,?)";
        ps = con.prepareStatement(sql_query);
        ps.setInt(1, qCode);
        ps.setInt(2, qNumber);
        ps.setString(3, question);
        ps.setInt(4, rightAnswer);
        
        ps.executeUpdate();
        ps.close();
        con.close();
    }
    
    public void insert_UserInfoDB(String userId, String userName, String userPw) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection con = DriverManager.getConnection(MARIADB_URL,MARIADB_ID,MARIADB_PW);
        PreparedStatement ps;
        
        String sql_query = "INSERT INTO userinfo VALUES (?,?,?)";
        ps = con.prepareStatement(sql_query);
        ps.setString(1, userId);
        ps.setString(2, userName);
        ps.setString(3, userPw);
        
        ps.executeUpdate();
        ps.close();
        con.close();
    }
    
    public String getString_DB(String sql_query, String columnName) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection con = DriverManager.getConnection(MARIADB_URL,MARIADB_ID,MARIADB_PW);
        Statement st = con.createStatement();
        
        //String sql_query = "SELECT * FROM " + tableName + "WHERE" + columnName + " = " + dataName;
        ResultSet rs = st.executeQuery(sql_query);
        
        if(rs.next()){
            String rvalue = rs.getString(columnName);
            
            rs.close();
            st.close();
            con.close();
        
            return rvalue;
        }
        
        rs.close();
        st.close();
        con.close();
        return "";
    }
    
    public int getInt_DB(String sql_query, String columnName) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection con = DriverManager.getConnection(MARIADB_URL,MARIADB_ID,MARIADB_PW);
        Statement st = con.createStatement();
        
        //String sql_query = "SELECT * FROM " + tableName + "WHERE" + columnName + " = " + dataName;
        ResultSet rs = st.executeQuery(sql_query);
        
        if(rs.next()){
            int rvalue = rs.getInt(columnName);
            rs.close();
            st.close();
            con.close();
        
            return rvalue;
        }
        
        rs.close();
        st.close();
        con.close();
        return 0;
    }

    public ArrayList getManyValueToInt_DB(String sql_query, String columnName) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection con = DriverManager.getConnection(MARIADB_URL,MARIADB_ID,MARIADB_PW);
        Statement st = con.createStatement();
        
        //String sql_query = "SELECT * FROM " + tableName + "WHERE" + columnName + " = " + dataName;
        ResultSet rs = st.executeQuery(sql_query);
        ArrayList <Integer> integerlist = new ArrayList<Integer>();
        
        while(rs.next()){
            integerlist.add(rs.getInt("qCode"));
            
        }
        rs.close();
        st.close();
        con.close();
        return integerlist;
    }
    
    public void updateLevel_OptionDB(String userId, String userLevel) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection con = DriverManager.getConnection(MARIADB_URL,MARIADB_ID,MARIADB_PW);
        PreparedStatement ps;
        
        String sql_query = "UPDATE option SET userLevel=? WHERE  userId=?";
        ps = con.prepareStatement(sql_query);
        ps.setString(1, userLevel);
        ps.setString(2, userId);
        
        int a = ps.executeUpdate();
        ps.close();
        con.close();
    }
        public void updateNumofquest_OptionDB(String userId, int userNumofquest) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection con = DriverManager.getConnection(MARIADB_URL,MARIADB_ID,MARIADB_PW);
        PreparedStatement ps;
        
        String sql_query = "UPDATE option SET userNumofquest=? WHERE  userId=?";
        ps = con.prepareStatement(sql_query);
        ps.setInt(1, userNumofquest);
        ps.setString(2, userId);
        
        int a = ps.executeUpdate();
        ps.close();
        con.close();
    }
    
}
