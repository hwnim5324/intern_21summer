import org.json.*;
import java.io.*;

/*************************************
* GameOption class (= 최근 게임 설정을 저장, 호출하는 부분.)
* 프로그램 실행 시, 환경설정 변경 시 실행.
*************************************/

public class GameOption {

    private static final int DEFAULT = 1;
    private static final int HIGH_LEVEL = 1;
    private static final int MIDDLE_LEVEL = 2;
    private static final int LOW_LEVEL = 3;

    private static int numofquestions = 10;
    public static int level = DEFAULT;
    public static String str_level;
    public static JSONObject optionJson;

    public void loadOption() {
        String fileName = "./Option.json";
        try {
            StringBuffer readBuffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = "";
            while ((line = reader.readLine()) != null) {
                readBuffer.append(line);
            }
            String file_read = readBuffer.toString();
            optionJson = new JSONObject(file_read);
            numofquestions = optionJson.getInt("NumOfQuest");
            level = optionJson.getInt("Level");
        } catch (IOException | JSONException e) {
            optionJson = new JSONObject();
        }
        setStrLevel();
    }
    
    public void saveOption(){
        try {
            if(optionJson != null){
                optionJson.remove("NumOfQuest");
                optionJson.remove("Level");
            }
            
            optionJson.put("NumOfQuest", numofquestions);
            optionJson.put("Level", level);
            
            FileWriter file = new FileWriter("./Option.json");
            file.write(optionJson.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error - 파일저장 에러(GameOption)");
        }
    }

    public int getLevel() {
        return level;
    }
    
    public int getNumOfQuestions() {
        return numofquestions;
    }
    
    public String getStrLevel(){
        return str_level;
    }

    public void setNumOfQuestions(int num) {
        this.numofquestions = num;
        printChangeNumOfQuest();
    }
    
    public void setLevel(int level) {
        this.level = level;
        setStrLevel();
        printChangeLevel();
    }
    
    public void setStrLevel(){
        if(level == HIGH_LEVEL) str_level = "상";
        else if(level == MIDDLE_LEVEL) str_level = "중";
        else if(level == LOW_LEVEL ) str_level = "하";
        else {System.out.println("오류발생\nGameOption class 86line");}
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

    public void printChangeNumOfQuest() {
        saveOption();
        System.out.println("==========================================================");
        System.out.println("설정이 변경되었습니다\n현재 문항 수 : " + numofquestions);
        System.out.println("==========================================================");
    }

    public void printChangeLevel() {
        saveOption();
        System.out.println("==========================================================");
        System.out.println("설정이 변경되었습니다\n현재 난이도 : " + str_level);
        System.out.println("==========================================================");
    }

}
