import org.json.*;
import java.io.*;
import java.util.*;

/*************************************
* GameRecord class (= 역대 게임 정보를 저장, 호출하는 부분.)
* 프로그램 시작 시, 종료 시, 게임 실행 시 실행.
*************************************/

public class GameRecord {

    private JSONArray RecordFile;

    public void loadRecord() {  //프로그램 시작시, 지난 게임정보를 read.
        try {
            String file_read = "";
            String fileName = "./Record.json";
            StringBuffer readBuffer = new StringBuffer();       //버퍼리더를 사용한 파일 전체 읽기.
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line = "";
                while ((line = reader.readLine()) != null) {
                    readBuffer.append(line);
                }
                file_read = readBuffer.toString();
            } catch (Exception e) {
                //e.printStackTrace();
            }
            RecordFile = new JSONArray(file_read);
        } catch (Exception ie) {
            RecordFile = new JSONArray();
        }
    }

    public void saveResult(JSONObject result) { //GamePlayer의 결과값을 받아, 게임 한 판의 결과를 RecordFile에 저장.
        RecordFile.put(result);
    }

    public String getRecord() { //지난 게임 정보를 불러와 사용자가 보기 쉬운 형태로 반환.
        int score, input, answer, numofquest, level;
        String time, quest;
        ArrayList<Rank> rankList = new ArrayList<Rank>();

        String result = "";
        String showRecord = "";

        for (int i = 0; i < RecordFile.length(); i++) { //JSONObject형태 게임 데이터 가공.
            JSONObject jobj = RecordFile.getJSONObject(i);
            JSONArray jary = jobj.getJSONArray("Record");
            score = jobj.getInt("Score");
            time = jobj.getString("Time");
            level = jobj.getInt("Level");
            numofquest = jobj.getInt("NumOfQuest");

            String str_level;
            if (level == 1) {
                str_level = "상";
            } else if (level == 2) {
                str_level = "중";
            } else if (level == 3) {
                str_level = "하";
            } else {
                System.out.println("오류발생\nGameRecord class 62 line");
                str_level = "저장오류";
            }
            //여기부터 점수 상위 3개 항목 계산 및 지난 게임 결과 문자열 저장.
            rankList.add(new Rank(score, numofquest, time, str_level));

            result += "==========================================================\n";
            result += i + 1 + "번째 Play Record입니다.\n";
            result += "점수 : " + score + "\n난이도 : " + str_level + "  문항 수 : " + numofquest + "\n시간 : " + time + "초\n\n";

            for (int j = 0; j < numofquest; j++) {
                JSONObject jo = jary.getJSONObject(j);

                quest = jo.getString("Quest");
                input = jo.getInt("Input");
                answer = jo.getInt("Answer");

                result += j + 1 + "번 문제 : " + quest + input + "\n정답 : " + answer + "\n";
            }
            result += "==========================================================\n";
        }

        Collections.sort(rankList);
        String rank = "==========================================================\n";
        rank += "TOP3 Score\n";
        
        for (int i = 1 ; i <= 3 ; i++){
            Rank printrank = rankList.get(i - 1);
            rank += i + "등 >> " + "점수 : " + printrank.getscore() + "점   시간 : " + printrank.gettime() + "초\n       난이도 : " + printrank.getlevel() + "   문항 수 : " + printrank.getnumofquest() + "\n";
        }

        showRecord += rank;
        showRecord += result;
        return showRecord;
    }

    public void saveRecord() {  //게임 종료시 갖고있던 게임 데이터 파일로 저장.
        try {
            FileWriter file = new FileWriter("./Record.json");
            file.write(RecordFile.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error - 파일저장 에러(GameRecord)");
        }
    }

    public int NumOfUser() {    //역대 플레이한 인원 수 반환.
        return RecordFile.length();
    }
}

class Rank implements Comparable<Rank>{     //순위 top3를 계산하기 위한 class.
    private int score, numofquest;
    private String time, level;
    
    public Rank(int score, int numofquest, String time, String level){
        this.score = score;
        this.numofquest = numofquest;
        this.time = time;
        this.level = level;
    }
    public int getscore(){
        return this.score;
    }
    public int getnumofquest(){
        return this.numofquest;
    }
    public String gettime(){
        return this.time;
    }
    public String getlevel(){
        return this.level;
    }

    @Override
    public int compareTo(Rank o) {
        if(this.score > o.getscore()){
            return -1;
        } else if(this.score < o.getscore()){
            return 1;
        }
        return 0;
    }
}