/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plusgame;

import org.json.JSONArray;

public class score {
    private int score;
    private String time;
    private String userLevel;
    private int userNumofquest;
    private JSONArray userAnswer;
    private String userId;
    private int qCode;

    public int getqCode() {
        return qCode;
    }

    public void setqCode(int qCode) {
        this.qCode = qCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTime() {
        return time + "초";
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public int getUserNumofquest() {
        return userNumofquest;
    }

    public void setUserNumofquest(int userNumofquest) {
        this.userNumofquest = userNumofquest;
    }

    public JSONArray getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = new JSONArray(userAnswer);
    }
    
    
}
