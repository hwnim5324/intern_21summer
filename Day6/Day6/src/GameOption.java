import org.json.*;
import java.io.*;
import java.util.*;

public class GameOption {
    
    private GameMaker gameMaker = new GameMaker();
    private HighMaker highMaker = new HighMaker();
    private MiddleMaker middleMaker = new MiddleMaker();
    private LowMaker lowMaker = new LowMaker();
    
    private static final String DEFAULT = "High";
    private static final String HIGH_LEVEL = "High";
    private static final String MIDDLE_LEVEL = "Middle";
    private static final String LOW_LEVEL = "Low";
    private static String option = DEFAULT;
    private static int numofquestions = 10;
    
    public void setOption(String option){
        if(option == null) return;
        this.option = option;
        printchange2();
    }
    public void setOption2(int numofquest){
        this.numofquestions = numofquest;
    }
    
    public String getOption(){
        return option;
    }
    
    public void setNumOfQuestions(int num){
        try{
            if(num == 10) return;
            this.numofquestions = num;
        }
        catch(Exception e){
            
        }
        printchange1();
    }
    
    public int getNumOfQuestions(){
        return numofquestions;
    }
    
    public JSONArray makeOptionGame(){
        if(option == HIGH_LEVEL) return highMaker.makeGame(numofquestions);
        else if(option == MIDDLE_LEVEL) return middleMaker.makeGame(numofquestions);
        else if(option == LOW_LEVEL) return lowMaker.makeGame(numofquestions);
        else{
            System.out.println("오류발생\n오류발생\n오류발생\n옵션설정오류발생!");
            return gameMaker.makeGame(numofquestions);
        }
    }
    
    public void printOptionUI(){
        System.out.println("==========================================================");
        System.out.println("1. 문항 수 조정 " );
        System.out.println("2. 난이도 상(High) " );
        System.out.println("3. 난이도 중(Middle) " );
        System.out.println("4. 난이도 하(Low) " );
        System.out.println("5. 뒤로가기 " );
        System.out.println("==========================================================");
        System.out.print("Command> ");
    }
    public void printNumOfQuestionsUI(){
        System.out.println("==========================================================");
        System.out.println("Play할 게임의 문항 수를 정해주세요" );
        System.out.println("==========================================================");
        System.out.print("Command> ");
    }
    public void printchange1(){
        System.out.println("==========================================================");
        System.out.println("설정이 변경되었습니다\n현재 문항 수 : " + numofquestions );
        System.out.println("==========================================================");
    }
    public void printchange2(){
        System.out.println("==========================================================");
        System.out.println("설정이 변경되었습니다\n현재 난이도 : " + option );
        System.out.println("==========================================================");
    }
    
}
