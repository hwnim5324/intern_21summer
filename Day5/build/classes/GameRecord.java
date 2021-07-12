import org.json.*;
import org.json.simple.parser.*;
import java.io.*;
import java.util.Scanner;


public class GameRecord {
    static JSONArray RecordFile = null;
    public void loadRecord(){
        try{
            String file_read = "";
            FileReader file = new FileReader("./Record.json");
            Scanner sc = new Scanner(file);
            
            while(sc.hasNextLine()){
                file_read += sc.nextLine();
            }
            
            RecordFile = new JSONArray(file_read);
            file.close();
            
        }catch(IOException ie){
            RecordFile = new JSONArray();
        }
    }

    public void saveResult(JSONObject result){
        System.out.println(result);
        RecordFile.put(result);
    }

    public String getRecord(){
        int Score, Input, Answer;
        String Time;
        String Quest;
        
        String All = "";
        
        for(int i = 0; i < RecordFile.length(); i++){
            JSONObject jobj = RecordFile.getJSONObject(i);
            JSONArray jary = jobj.getJSONArray("Record");
            Score = jobj.getInt("Score");
            Time = jobj.getString("Time");
            
            All += "==========================================================\n";
            All += i + 1 + "번째 Play Record입니다.\n";
            All += "점수 : " + Score + "\n시간 : " + Time + "\n\n"; //무슨 시간인지 말 생각해야함.
            
            for(int j = 0; j < 10 ; j++){
                JSONObject jo = jary.getJSONObject(j);
            
                Quest = jo.getString("Quest");
                Input = jo.getInt("Input");
                Answer = jo.getInt("Answer");
                
                All += j + 1 + "번 문제 : " + Quest + Input + "\n정답 : " + Answer + "\n";
            }
            All += "==========================================================\n";
        }
        
        
        
        return All;
    }

    public void saveRecord(){
        try {
            FileWriter file = new FileWriter("./Record.json");
            file.write(RecordFile.toString());  //
            //RecordFile.toJSONObject(RecordFile);  //에러 수정.
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error - 파일저장 에러");
        }
    }
}
