import org.json.*;
import java.util.Random;

/*************************************
* GameMaker class (= 게임 생성 부분.)
* 난이도 : 상(default)
* super class로 sub class를 객체로 사용해
* 상황에 맞는 난이도로 게임 생성.
*************************************/

public class GameMaker {
    public JSONArray makeGame(int numofquestions){
        JSONArray questions = new JSONArray();
        Random rd = new Random();
        int num1, num2;
        
        for(int i = 1 ; i <= numofquestions ; i++){
            JSONObject inner = new JSONObject();
            num1 = rd.nextInt(89)+10;
            num2 = rd.nextInt(89)+10;
            
            inner.put("Quest", num1 + " + "+ num2 + " = ");
            inner.put("RightAnswer", num1 + num2);
            inner.put("Num",i);
            
            questions.put(inner);
        }
        return questions;
    }

    public JSONArray makeOptionalGame(int level, int numofquestions){
        GameMaker gameMaker = new GameMaker();
        if(level == 1){gameMaker = new HighMaker();}
        else if(level == 2){gameMaker = new MiddleMaker();}
        else if(level == 3){gameMaker = new LowMaker();}
        else{System.out.println("오류발생\nGameMaker class 30line");}
        return gameMaker.makeGame(numofquestions);
    }
}