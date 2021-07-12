import org.json.*;
import java.io.*;
import java.util.*;

public class GameRecord {

    private JSONArray RecordFile;
    private String option_level;
    private int option_numofquest;

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
        try{
            JSONObject jobj = RecordFile.getJSONObject(RecordFile.length()-1);
        option_level = jobj.getString("Level");
        option_numofquest = jobj.getInt("NumOfQuest");
        }
        catch(Exception e){
            
        }
        
    }

    public void saveResult(JSONObject result) {
        RecordFile.put(result);     
        //System.out.println(RecordFile);
    }

    public String getRecord() {  //변수 리네이밍 필요.
        int score, input, answer, numofquest;
        String time, quest, level;
        ArrayList<Integer> list = new ArrayList<Integer>();

        String result = "";
        String Show = "";
        
        JSONArray jsonArr = new JSONArray();
        JSONArray sortedJsonArray = new JSONArray();

        for (int i = 0; i < RecordFile.length(); i++) {
            JSONObject jobj = RecordFile.getJSONObject(i);
            JSONArray jary = jobj.getJSONArray("Record");
            score = jobj.getInt("Score");
            time = jobj.getString("Time");
            level = jobj.getString("Level");
            numofquest = jobj.getInt("NumOfQuest");
            
            JSONObject rankObj = new JSONObject();
            rankObj.put("Score", Integer.toString(score));
            rankObj.put("Time", time);
            jsonArr.put(rankObj);

            list.add(score);

            result += "==========================================================\n";
            result += i + 1 + "번째 Play Record입니다.\n";
            result += "점수 : " + score + "\n난이도 : " + level + "  문항 수 : " + numofquest + "\n시간 : " + time + "\n\n";

            for (int j = 0; j < numofquest; j++) {
                JSONObject jo = jary.getJSONObject(j);

                quest = jo.getString("Quest");
                input = jo.getInt("Input");
                answer = jo.getInt("Answer");

                result += j + 1 + "번 문제 : " + quest + input + "\n정답 : " + answer + "\n";
            }
            result += "==========================================================\n";
        }

        String rank = "==========================================================\n";
        rank += "TOP3 Score\n";

        for (int j = 0; j < list.size(); j++) {
            Collections.sort(list);
            Collections.reverse(list);
            rank += j + 1 + "등 : " + list.get(j) + "점\n";
            if (j == 2) {
                break;
            }
        }

        //JSONArray 객체 정렬
        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        for (int i = 0; i < jsonArr.length(); i++) {
            jsonValues.add(jsonArr.getJSONObject(i));
        }
        Collections.sort(jsonValues, new Comparator<JSONObject>() {
            //You can change "Name" with "ID" if you want to sort by ID
            private static final String KEY_NAME = "Score";

            @Override
            public int compare(JSONObject a, JSONObject b) {
                String valA = new String();
                String valB = new String();

                try {
                    valA = (String) a.getString(KEY_NAME);
                    valB = (String) b.getString(KEY_NAME);
                } catch (JSONException e) {
                    //do something
                    e.printStackTrace();
                }

                return valA.compareTo(valB);
                //if you want to change the sort order, simply use the following:
                //return -valA.compareTo(valB);
            }
        });

        for (int i = 0; i < jsonArr.length(); i++) {
            sortedJsonArray.put(jsonValues.get(i));
        }
        System.out.println(sortedJsonArray);
        //JSONArray 객체 정렬
        
        Show += rank;
        Show += result;
        return Show;
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
    public String getOption_level(){
        //recordfile에서 마지막 인덱스 object의 option value를 찾아서 반환
        return option_level;
    }
    public int getOption_numofquest(){
        return option_numofquest;
    }
}