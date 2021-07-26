package plusgame;

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
    
    static private GameMaker gameMaker;
    
    protected GameMaker(){}
    
    public static GameMaker getInstance(){
        if(gameMaker == null){
            gameMaker = new GameMaker();
        }
        return gameMaker;
    }

    public JSONArray makeOptionalGame(String level, int numofquestions){
        if(level == "상"){gameMaker = new HighMaker();}
        else if(level == "중"){gameMaker = new MiddleMaker();}
        else if(level == "하"){gameMaker = new LowMaker();}
        else{System.out.println("오류발생\nGameMaker class 30line");}
        return gameMaker.makeGame(numofquestions);
    }
}