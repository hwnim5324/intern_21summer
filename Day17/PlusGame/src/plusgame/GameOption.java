package plusgame;

import org.json.*;
import java.io.*;
import java.sql.SQLException;

/**
 * ***********************************
 * GameOption class (= 최근 게임 설정을 저장, 호출하는 부분.) 프로그램 실행 시, 환경설정 변경 시 실행.
************************************
 */
public class GameOption {
    
    private DAO dao = new DAO();
    String userId = "";

    private static final int DEFAULT = 1;
    private static final String HIGH_LEVEL = "상";
    private static final String MIDDLE_LEVEL = "중";
    private static final String LOW_LEVEL = "하";

    private static int numofquestions = 10;
    public static String level = HIGH_LEVEL;
    public static JSONObject optionJson;

    public void loadOption(String userId) throws ClassNotFoundException, SQLException {
        this.userId = userId;
        this.level = dao.getString_DB("SELECT userLevel FROM option WHERE userid="+"'"+ this.userId +"'", "userLevel");
        this.numofquestions = Integer.parseInt(dao.getString_DB("SELECT userNumofquest FROM option WHERE userid="+"'"+ this.userId +"'", "userNumofquest"));
    }

    public void saveOption() throws ClassNotFoundException, SQLException {
        
        dao.update_OptionDB(this.userId, this.level, String.valueOf(this.numofquestions));
    }

    public String getLevel() {
        return level;
    }

    public int getNumOfQuestions() {
        return numofquestions;
    }

    public void setNumOfQuestions(int num) throws ClassNotFoundException, SQLException, SQLException {
        this.numofquestions = num;
        printChangeNumOfQuest();
    }

    public void setLevel(String level) throws ClassNotFoundException, ClassNotFoundException, SQLException {
        this.level = level;
        printChangeLevel();
    }


    public void printOptionUI() {
        System.out.println("==========================================================");
        System.out.println("1. 문항 수 조정 ");
        System.out.println("2. 난이도 상(High) ");
        System.out.println("3. 난이도 중(Middle) ");
        System.out.println("4. 난이도 하(Low) ");
        System.out.println("5. 뒤로가기 ");
        System.out.println("==========================================================");
        System.out.print("Command> ");
    }

    public void printNumOfQuestionsUI() {
        System.out.println("==========================================================");
        System.out.println("Play할 게임의 문항 수를 정해주세요");
        System.out.println("==========================================================");
        System.out.print("Command> ");
    }

    public void printChangeNumOfQuest() throws ClassNotFoundException, SQLException {
        saveOption();
        System.out.println("==========================================================");
        System.out.println("설정이 변경되었습니다\n현재 문항 수 : " + numofquestions);
        System.out.println("==========================================================");
    }

    public void printChangeLevel() throws ClassNotFoundException, SQLException {
        saveOption();
        System.out.println("==========================================================");
        System.out.println("설정이 변경되었습니다\n현재 난이도 : " + level);
        System.out.println("==========================================================");
    }

}
