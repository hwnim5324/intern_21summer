package plusgame;

import java.sql.SQLException;
import java.util.Random;

/*************************************
* GameMaker class (= 게임 생성 부분.)
* 난이도 : 상(default)
* super class로 sub class를 객체로 사용해
* 상황에 맞는 난이도로 게임 생성.
*************************************/

public class GameMaker {
    public void makeGame(int numofquestions) throws ClassNotFoundException, SQLException{
        
        DAO dao = new DAO();
        int qCode = dao.getInt_DB("SELECT MAX(qCode) FROM questions", "qCode") + 1;
        Random rd = new Random();
        int num1, num2;
        
        for(int i = 1 ; i <= numofquestions ; i++){
            num1 = rd.nextInt(89)+10;
            num2 = rd.nextInt(89)+10;

            String question = num1 + " + "+ num2 + " = ";
            int rightAnswer = num1 + num2;
            dao.insert_QuestionsDB(qCode, i, question, rightAnswer);
        }
    }
    
    static private GameMaker gameMaker;
    
    protected GameMaker(){}
    
    public static GameMaker getInstance(){
        if(gameMaker == null){
            gameMaker = new GameMaker();
        }
        return gameMaker;
    }

    public void makeOptionalGame(String level, int numofquestions) throws ClassNotFoundException, ClassNotFoundException, SQLException{
        if(level == "상"){gameMaker = new HighMaker();}
        else if(level == "중"){gameMaker = new MiddleMaker();}
        else if(level == "하"){gameMaker = new LowMaker();}
        else{System.out.println("오류발생\nGameMaker class 30line");}
        gameMaker.makeGame(numofquestions);
    }
}