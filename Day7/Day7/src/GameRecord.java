
import org.json.*;
import java.io.*;
import java.util.*;

public class GameRecord {

    private JSONArray RecordFile;

    public void loadRecord() {
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

    public void saveResult(JSONObject result) {
        RecordFile.put(result);
        //System.out.println(RecordFile);
    }

    public String getRecord() {  //변수 리네이밍 필요.
        int score, input, answer, numofquest, level;
        String time, quest;
        ArrayList<Rank> rankList = new ArrayList<Rank>();

        String result = "";
        String showRecord = "";

        for (int i = 0; i < RecordFile.length(); i++) {
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
                System.out.println("에러\n\n\n\n\n\n\n\n\n\n\n에러");
                str_level = "저장오류";
            }
            
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

    public void saveRecord() {
        try {
            FileWriter file = new FileWriter("./Record.json");
            file.write(RecordFile.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error - 파일저장 에러");
        }
    }

    public int NumOfUser() {
        return RecordFile.length();

    }
}

//class JsonComparator implements Comparator<JSONObject> {
//    public int compare(JSONObject a,JSONObject b){
//		if(a.getInt("Score")>b.getInt("Score")) return 1;
//		if(a.getInt("Score")<b.getInt("Score")) return -1;
//		return 0;
//	}
//} 주석은 ctrl + shift + c

class Rank implements Comparable<Rank>{
    int score, numofquest;
    String time, level;
    
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