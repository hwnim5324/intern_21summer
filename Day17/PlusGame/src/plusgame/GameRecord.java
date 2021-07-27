package plusgame;

import org.json.*;
import java.io.*;
import java.sql.SQLException;
import java.util.*;

/*************************************
* GameRecord class (= 역대 게임 정보를 저장, 호출하는 부분.)
* 프로그램 시작 시, 종료 시, 게임 실행 시 실행.
*************************************/

public class GameRecord {
    
    private DAO dao = new DAO();

    public String getRecord() throws ClassNotFoundException, SQLException { //지난 게임 정보를 불러와 사용자가 보기 쉬운 형태로 반환.
        ArrayList <Integer> integerlist = new ArrayList<Integer>();
        integerlist = dao.getManyValueToInt_DB("SELECT qCode FROM gamerecord ORDER BY score ASC LIMIT 10", "qCode");
        int qCode, score, time, userNumofquest;
        String userId, userLevel, userAnswer, rightAnswer, question, result = "";
        
        for(int i = 0; i < 10; i++){
            qCode = integerlist.get(i);
            
            score = dao.getInt_DB("SELECT score FROM gamerecord WHERE qCode=" + qCode, "score");
            time = dao.getInt_DB("SELECT time FROM gamerecord WHERE qCode=" + qCode, "time");
            userNumofquest = dao.getInt_DB("SELECT userNumofquest FROM gamerecord WHERE qCode=" + qCode, "userNumofquest");
            userId = dao.getString_DB("SELECT userId FROM gamerecord WHERE qCode=" + qCode, "userId");
            userLevel = dao.getString_DB("SELECT userLevel FROM gamerecord WHERE qCode=" + qCode, "userLevel");
            String jarray_str = dao.getString_DB("SELECT userAnswer FROM gamerecord WHERE qCode=" + qCode, "userAnswer");
            JSONArray jarray = new JSONArray(jarray_str);
            
            result += "==========================================================\n";
            result += i + 1 + "등의 Play Record입니다.\n";
            result += "아이디 : " + userId + "\n";
            result += "점수 : " + score + "\n난이도 : " + userLevel + "  문항 수 : " + userNumofquest + "\n시간 : " + time + "초\n\n";
            for (int j = 0; j < userNumofquest; j++) {
                question = dao.getString_DB("SELECT question FROM questions WHERE qCode = " + qCode, "question");
                rightAnswer = dao.getString_DB("SELECT rightAnswer FROM questions WHERE qCode = " + qCode, "rightAnswer");
                userAnswer =jarray.getString(j); //변경해야함.
                result += j + 1 + "번 문제 : " + question + userAnswer + "\n정답 : " + rightAnswer + "\n";
            }
            result += "==========================================================\n";
        }
        return result;
    }
        
        
    public int numofuser() throws ClassNotFoundException, ClassNotFoundException, SQLException{
        return dao.getInt_DB("SELECT COUNT(DISTINCT qCode) AS numofuser FROM gamerecord", "numofuser");
    }
        
        
        
        
        
        
        
        
        
//        int score, input, answer, numofquest, level;
//        String time, quest;
//        ArrayList<Rank> rankList = new ArrayList<Rank>();
//
//        String result = "";
//        String showRecord = "";
//
//        for (int i = 0; i < RecordFile.length(); i++) { //JSONObject형태 게임 데이터 가공.
//            JSONObject jobj = RecordFile.getJSONObject(i);
//            JSONArray jary = jobj.getJSONArray("Record");
//            score = jobj.getInt("Score");
//            time = jobj.getString("Time");
//            level = jobj.getInt("Level");
//            numofquest = jobj.getInt("NumOfQuest");
//
//            String str_level;
//            if (level == 1) {
//                str_level = "상";
//            } else if (level == 2) {
//                str_level = "중";
//            } else if (level == 3) {
//                str_level = "하";
//            } else {
//                System.out.println("오류발생\nGameRecord class 62 line");
//                str_level = "저장오류";
//            }
//            //여기부터 점수 상위 3개 항목 계산 및 지난 게임 결과 문자열 저장.
//            rankList.add(new Rank(score, numofquest, time, str_level));
//
//            result += "==========================================================\n";
//            result += i + 1 + "번째 Play Record입니다.\n";
//            result += "점수 : " + score + "\n난이도 : " + str_level + "  문항 수 : " + numofquest + "\n시간 : " + time + "초\n\n";
//
//            for (int j = 0; j < numofquest; j++) {
//                JSONObject jo = jary.getJSONObject(j);
//
//                quest = jo.getString("Quest");
//                input = jo.getInt("Input");
//                answer = jo.getInt("Answer");
//
//                result += j + 1 + "번 문제 : " + quest + input + "\n정답 : " + answer + "\n";
//            }
//            result += "==========================================================\n";
//        }
//
//        Collections.sort(rankList);
//        String rank = "==========================================================\n";
//        rank += "TOP3 Score\n";
//        
//        //정렬을 db로 하는 법!
//        //dao.getString_DB("SELECT userId, score, time, userLevel, userNumofquest FROM gamerecord ORDER BY score ASC LIMIT 3","");    //정렬 어케함;;
//        
//        for (int i = 1 ; i <= 3 ; i++){
//            Rank printrank = rankList.get(i - 1);
//            rank += i + "등 >> " + "점수 : " + printrank.getscore() + "점   시간 : " + printrank.gettime() + "초\n       난이도 : " + printrank.getlevel() + "   문항 수 : " + printrank.getnumofquest() + "\n";
//        }
//
//        showRecord += rank;
//        showRecord += result;
//        return showRecord;
//    }
}


