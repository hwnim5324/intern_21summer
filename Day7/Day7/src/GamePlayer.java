import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Date;
import java.text.SimpleDateFormat;

public class GamePlayer {
    
    private Scanner ScreenInput = new Scanner(System.in);
    JSONObject convert = null;
    static private int level;
    static private int numofquest;
    
    public void setGame(int level, int numofquest){
        this.level = level;
        this.numofquest = numofquest;
    }
    
    public void play(JSONArray questions){
        JSONObject result = new JSONObject();
        JSONObject obj = new JSONObject();
        JSONArray jarray = new JSONArray();
        int input;
        int count = 0;
        
        long startTime = System.currentTimeMillis();
        for(int i = 1 ; i <= numofquest ; i++){
            JSONObject s_obj = new JSONObject();
            obj = questions.getJSONObject(i - 1);
            String str = obj.getString("Quest");
            int A = obj.getInt("RightAnswer");
            while(true){
                System.out.print(i + "번 문제 : " + str);
                try{
                    input = ScreenInput.nextInt();
                
                    String[] lengthOfInput = ScreenInput.nextLine().split(" ");
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
                    ScreenInput.nextLine();
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
        long endTime = System.currentTimeMillis();      //플레이 시간 계산.
        String time = Integer.toString((int)(((endTime - startTime) / 1000))) + ".";
        time += Integer.toString((int)((((endTime - startTime) % 1000) / 10)));
        
        double score = ((count * 1.0) / (numofquest * 1.0)) * 100;
        System.out.println("고생하셨습니다.\n점수는 " + (int)score + "점 입니다.");
        System.out.println("==========================================================");
        result.put("Record",jarray);
        result.put("Time", time);
        result.put("Score",(int)score);
        result.put("Level",level);
        result.put("NumOfQuest", numofquest);
        convert = result;
    }
    

    public JSONObject getResult(){
        return convert;
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