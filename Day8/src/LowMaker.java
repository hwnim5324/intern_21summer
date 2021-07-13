import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

/*************************************
* 난이도 : 하
* 각 자리의 합이 10 미만으로 설정되어
* 다른 자리 수 계산에 영향을 미치지 않도록 제작.
*************************************/

public class LowMaker extends GameMaker {
        public JSONArray makeGame(int numofquestions){
        JSONArray questions = new JSONArray();
        Random rd = new Random();
        int num1, num2;
        int tens1, tens2;
        int units1, units2;
        
        for(int i = 1 ; i <= numofquestions ; i++){
            JSONObject inner = new JSONObject();

            while(true){
                tens1 = rd.nextInt(8) + 1;
                tens2 = rd.nextInt(8) + 1;
                
                if(tens1 + tens2 >= 10)continue;
                else break;
            }
            
            while(true){
                units1 = rd.nextInt(8) + 1;
                units2 = rd.nextInt(8) + 1;
                
                if(units1 + units2 >= 10)continue;
                else break;
            }
            
            num1 = (tens1 * 10) + units1;
            num2 = (tens2 * 10) + units2;
            
            inner.put("Quest", num1 + " + "+ num2 + " = ");
            inner.put("RightAnswer", num1 + num2);
            inner.put("Num",i);
            
            questions.put(inner);
        }
        return questions;
    }
}
