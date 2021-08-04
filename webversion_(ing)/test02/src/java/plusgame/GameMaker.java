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
        
        questionsDAO questionsdao = new questionsDAO();
        int qCode = questionsdao.recentqCode()+1;
        Random rd = new Random();
        int num1, num2;
        
        for(int i = 1 ; i <= numofquestions ; i++){
            num1 = rd.nextInt(89)+10;
            num2 = rd.nextInt(89)+10;

            String question = num1 + " + "+ num2 + " = ";
            int rightAnswer = num1 + num2;
            questionsdao.setquestions(qCode, i, question, rightAnswer);
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
        if(level.equals("상")){gameMaker = new HighMaker();}
        else if(level.equals("중")){gameMaker = new MiddleMaker();}
        else if(level.equals("하")){gameMaker = new LowMaker();}
        else{System.out.println("오류발생\nGameMaker class 42line");}
        gameMaker.makeGame(numofquestions);
    }
}