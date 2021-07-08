import org.json.*;
import org.json.simple.parser.*;
import java.io.*;


public class GameRecord {
    JSONArray RecordFile = new JSONArray();
    public void loadRecord(){
        try{
            JSONParser parser = new JSONParser();
            Object file = parser.parse(new FileReader("./Record.json"));
            JSONArray RecordFile = (JSONArray) file;
        }catch(IOException ie){
            
        }catch(ParseException e){
            
        }
    }

    public void saveResult(JSONObject result){
        RecordFile.put(result);
    }

    public String getRecord(){
        for(int i = 0; i < RecordFile.length(); i++){
            JSONObject jobj = RecordFile.getJSONObject(i);
            JSONArray jary = jobj.getJSONArray("Record");
            
        }
        
        
        
        return "";
    }

    public void saveRecord(){
        try {
            FileWriter file = new FileWriter("./Record.json");
            file.write(RecordFile.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error - 파일저장 에러");
        }
    }
}
