import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

public class MiddleMaker extends GameMaker {
    
    public JSONArray makeGame(int numofquestions){
        JSONArray questions = new JSONArray();
        Random rd = new Random();
        int num1, num2;
        int tens1, tens2;
        int units1, units2;
        
        for(int i = 1 ; i <= numofquestions ; i++){
            JSONObject inner = new JSONObject();
            
            tens1 = rd.nextInt(8) + 1;
            tens2 = rd.nextInt(8) + 1;
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
