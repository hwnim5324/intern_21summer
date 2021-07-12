import org.json.*;
import java.util.Random;

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
}