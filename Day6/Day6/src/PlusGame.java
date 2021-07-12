import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;


public class PlusGame {

    public static void main(String[] args) {
        GameManager plusGame = new GameManager();
        plusGame.play();
    }
    // D2coding 
}

class GameManager {  // 게임 전반을 운영하는 클래스

    //private GameMaker gameMaker = new GameMaker();       // 문제를 출제하는 클래스
    private GamePlayer gamePlayer = new GamePlayer();    // 문제를 풀고 채점하는 클래스
    private GameRecord gameRecord = new GameRecord();    // 결과를 저장하고 불러오는 클래스
    private Scanner screenInput = new Scanner(System.in);
    private GameOption gameOption = new GameOption();

    private static final int COMMAND_STARTGAME = 1;
    private static final int COMMAND_VIEWRESULT = 2;
    private static final int COMMAND_OPTION = 3;
    private static final int COMMAND_EXIT = 4;


    public void play() {
        printMessage("덧셈게임에 오신 것을 환영합니다");
        gameRecord.loadRecord();  // 파일에서 저장된 점수 데이터를 읽어 옴
        gameOption.setOption(gameRecord.getOption_level());
        gameOption.setOption2(gameRecord.getOption_numofquest());

        while(true){
            printMainUI();
            gamePlayer.setGame(gameOption.getOption(),gameOption.getNumOfQuestions());
            try {
                int command = screenInput.nextInt();

                if(command == COMMAND_STARTGAME){
                    JSONArray questions = gameOption.makeOptionGame();
                    gamePlayer.play(questions);

                    JSONObject result = gamePlayer.getResult();
                    gameRecord.saveResult(result);

                    printMessage("게임을 완료하였습니다.");



                }else if(command == COMMAND_VIEWRESULT){
                    String resultString = gameRecord.getRecord();
                    printMessage(resultString);
                    System.out.println("[Enter]를 치세요." );
                    screenInput.nextLine();
                    screenInput.nextLine();

                }else if(command == COMMAND_OPTION){
                    while(true){
                        gameOption.printOptionUI();
                    try{
                        int under_command = screenInput.nextInt();
                        if(under_command == 1){
                            gameOption.printNumOfQuestionsUI();
                            try{
                                int numofquest = screenInput.nextInt();
                                gameOption.setNumOfQuestions(numofquest);
                            }
                            catch(Exception e){
                                
                            }
                        }
                        else if(under_command == 2) gameOption.setOption("High");
                        else if(under_command == 3) gameOption.setOption("Middle");
                        else if(under_command == 4) gameOption.setOption("Low");
                        else if(under_command == 5) break;
                    }
                    catch(Exception e){
                        
                    }
                    }
                    
                }else if(command == COMMAND_EXIT){
                    printMessage("게임을 종료합니다.");
                    gameRecord.saveRecord();
                    break;
                }

            } catch (Exception e) {
                e.printStackTrace();
                printMessage("Error - 입력값 에러");
                screenInput.nextLine();   //  예외가 발생하면 버퍼를 비움   screenInput = new Scanner(System.in);
            }
        }
    }

    private void printMainUI(){
        // 메인 UI 출력
        System.out.println("==========================================================");
        System.out.println("현재 게임을 Play한 사용자 수 : " + gameRecord.NumOfUser());
        System.out.println("현재 난이도 : " + gameOption.getOption() + "  현재 문항 수 : " + gameOption.getNumOfQuestions());
        System.out.println("1. 게임시작 " );
        System.out.println("2. 점수보기 " );
        System.out.println("3. 환경설정 " );
        System.out.println("4. 종료 " );
        System.out.println("==========================================================");
        System.out.print("Command> ");
    }

    private void printMessage(String str){
        System.out.println(str);
    }

}