package plusgame;

import java.sql.SQLException;
import java.util.Random;

/*************************************
* 난이도 : 중
* 일의 자리의 합이 10 미만으로 설정되어
* 십의 자리 수 계산에 영향을 미치지 않도록 제작.
*************************************/

public class MiddleMaker extends GameMaker {
    public void makeGame(int numofquestions) throws ClassNotFoundException, SQLException{
        Random rd = new Random();
        questionsDAO questionsdao = new questionsDAO();
        
        int num1, num2;
        int tens1, tens2;
        int units1, units2;
        int qCode = questionsdao.recentqCode() + 1;
        
        for(int i = 1 ; i <= numofquestions ; i++){
            
            tens1 = rd.nextInt(8) + 1;  //난수들은 십의자리와 일의자리를 따로 계산해 더함.
            tens2 = rd.nextInt(8) + 1;
            while(true){
                units1 = rd.nextInt(8) + 1;
                units2 = rd.nextInt(8) + 1;
                
                if(units1 + units2 >= 10)continue;  //일의 자리의 합이 10이상일 때, 반복.
                else break;
            }
            
            num1 = (tens1 * 10) + units1;
            num2 = (tens2 * 10) + units2;

            String question = num1 + " + "+ num2 + " = ";
            int rightAnswer = num1 + num2;
            questionsdao.setquestions(qCode, i, question, rightAnswer);

        }
    }
    
}
