import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Date;
import java.text.SimpleDateFormat;

public class GamePlayer {
    JSONObject result = new JSONObject();
    private Scanner screenInput = new Scanner(System.in);
    
    public void play(JSONArray questions){
        JSONObject obj = new JSONObject();
        JSONArray jarray = new JSONArray();
        String memory;
        int input;
        int count = 0;
        
        for(int i = 1 ; i < 11 ; i++){
            JSONObject s_obj = new JSONObject();
            obj = questions.getJSONObject(i - 1);
            String str = obj.getString("Quest");
            int num = obj.getInt("Num");
            int A = obj.getInt("RightAnswer");
            
            while(true){
                System.out.print(i + "번 문제 : " + str);
                try{
                    input = screenInput.nextInt();
                
                    String[] lengthOfInput = screenInput.nextLine().split(" ");
                    if(lengthOfInput.length != 1){
                        System.out.println("==========================================================");
                        System.out.println("Error - 입력값 에러\n내용 : 2개 이상의 수 입력.");
                        System.out.println("==========================================================");
                        continue;
                    }else if(input<=0){
                        System.out.println("==========================================================");
                        System.out.println("Error - 입력값 에러\n내용 : 0이하의 수 입력.");
                        System.out.println("==========================================================");
                        continue;
                    }else{
                        break;
                    }    
                }
                catch(Exception e){
                    System.out.println("==========================================================");
                    System.out.println("Error - 입력값 에러\n내용 : 잘못된 자료형.");
                    System.out.println("==========================================================");
                    screenInput.nextLine();
                    continue;
                }
            }
            if(checkanswer(input,A)){
                count++;
            }
            s_obj.put("Quest", str);
            s_obj.put("Input", input);
            s_obj.put("Answer", A);
            jarray.put(s_obj);
        }
        
        System.out.println("고생하셨습니다.\n점수는 " + count + "점 입니다.");
        System.out.println("==========================================================");
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat form = new SimpleDateFormat ( "HH : mm : ss");
        String time = form.format(date);
        result.put("Record",jarray);
        result.put("Time", time);
        result.put("Score",count);
    }
    

    public JSONObject getResult(){
        System.out.println(result);
        return result;
    }
    
    private boolean checkanswer(int input, int answer) {
		if(input == answer)
		 {
                    System.out.println("==========================================================");
		    System.out.println("맞았습니다!");
                    System.out.println("==========================================================");
                    return true;
		 }
		 else{
                    System.out.println("==========================================================");
		    System.out.println("틀렸습니다!\n정답은 "+answer+"입니다.");
                    System.out.println("==========================================================");
                    return false;
		 }
	}
    
}