package plusgame;

import java.sql.SQLException;
import java.util.Scanner;
import org.json.JSONArray;

/*************************************
* GamePlayer class (= 게임 실행 부분.)
* GameMaker에서 생성된 게임을 실제 play 가능하게 제작.
* play하면서 생긴 데이터(문제, 사용자 답, 정답, 플레이 시간, 난이도, 문항 수)를
* JSONObject로 저장, 반환.
*************************************/

public class GamePlayer {
    private Scanner ScreenInput = new Scanner(System.in);
    private DAO dao = new DAO();
    
    static private String level;
    static private int numofquest;
    static private String userId;
    
    public void setGame(String userId, String level, int numofquest){
        this.userId = userId;
        this.level = level;
        this.numofquest = numofquest;
    }
    
    public void play() throws ClassNotFoundException, SQLException{
        int input;
        int count = 0;
        int qCode = dao.getInt_DB("SELECT MAX(qCode) FROM questions", "qCode");
        long startTime = System.currentTimeMillis();
        
        JSONArray userAnswer = new JSONArray();
        
        for(int i = 1 ; i <= numofquest ; i++){
            String question = dao.getString_DB("SELECT question FROM questions WHERE qCode=" + qCode + " and qNumber=" + i, "question");
            int rightAnswer = dao.getInt_DB("SELECT rightAnswer FROM questions WHERE qCode=" + qCode + " and qNumber=" + i, "rightAnswer");
            while(true){
                System.out.print(i + "번 문제 : " + question);
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
                        userAnswer.put(input);
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

            if(checkanswer(input,rightAnswer)){
                count++;
            }
        }
        long endTime = System.currentTimeMillis();      //플레이 시간 계산.
        String time = Integer.toString((int)(((endTime - startTime) / 1000))) + ".";
        time += Integer.toString((int)((((endTime - startTime) % 1000) / 10)));
        
        double score = ((count * 1.0) / (numofquest * 1.0)) * 100;
        System.out.println("고생하셨습니다.\n점수는 " + (int)score + "점 입니다.");
        System.out.println("==========================================================");
        
        
        dao.insert_GameRecordDB(userId, qCode, (int)score, time, level, numofquest, userAnswer.toString());
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