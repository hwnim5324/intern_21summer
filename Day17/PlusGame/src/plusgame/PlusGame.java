package plusgame;

import java.sql.SQLException;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

/************************************
 * PlusGame class (= 전체 프로그램 매니징 부분.)
 * 각 class에 연결하여 값을 받아 전달하고, 초기화면 UI를 출력.
*************************************/
public class PlusGame {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        GameManager plusGame = new GameManager();
        plusGame.play();
    }
}

class GameManager {  // 게임 전반을 운영하는 클래스

    private GameMaker gameMaker = new GameMaker();       // 문제를 출제하는 클래스
    private GamePlayer gamePlayer = new GamePlayer();    // 문제를 풀고 채점하는 클래스
    private GameRecord gameRecord = new GameRecord();    // 결과를 저장하고 불러오는 클래스
    private GameOption gameOption = new GameOption();    // 게임 설정을 저장하고 불러오는 클래스
    private GameLogIn gameLogin = new GameLogIn();       // 게임 계정에 대한 관리를 하는 클래스
    private Scanner screenInput = new Scanner(System.in);

    private static final int COMMAND_STARTGAME = 1;
    private static final int COMMAND_VIEWRESULT = 2;
    private static final int COMMAND_OPTION = 3;
    private static final int COMMAND_EXIT = 4;
    
    private static final int UNDER_COMMAND_CHANGE_NUMOFQUESTIONS = 1;
    private static final int UNDER_COMMAND_CHANGE_LEVEL_HIGH = 2;
    private static final int UNDER_COMMAND_CHANGE_LEVEL_MIDDLE = 3;
    private static final int UNDER_COMMAND_CHANGE_LEVEL_LOW = 4;
    private static final int UNDER_COMMAND_EXIT = 5;

    public void play() throws ClassNotFoundException, SQLException {
        printMessage("덧셈게임에 오신 것을 환영합니다");
        
        gameLogin.loginTask();
        
        //gameRecord.loadRecord();  // 파일에서 저장된 점수 데이터를 읽어 옴
        gameOption.loadOption(gameLogin.getId());  // 파일에서 저장된 옵션 데이터를 읽어 옴

        while (true) {
            printMainUI();
            try {
                int command = screenInput.nextInt();

                if (command == COMMAND_STARTGAME) {
                    gamePlayer.setGame(gameLogin.getId(), gameOption.getLevel(), gameOption.getNumOfQuestions());
                    gameMaker.makeOptionalGame(gameOption.getLevel(), gameOption.getNumOfQuestions());
                    gamePlayer.play();
                    //gameRecord.saveResult(result);

                    printMessage("게임을 완료하였습니다.");

                } else if (command == COMMAND_VIEWRESULT) {
                    String resultString = gameRecord.getRecord();
                    printMessage(resultString);
                    System.out.println("[Enter]를 치세요.");
                    screenInput.nextLine();
                    screenInput.nextLine();

                } else if (command == COMMAND_OPTION) {
                    while (true) {
                        gameOption.printOptionUI();
                        try {
                            int under_command = screenInput.nextInt();
                            if (under_command == UNDER_COMMAND_CHANGE_NUMOFQUESTIONS) {
                                gameOption.printNumOfQuestionsUI();
                                try {
                                    int numofquest = screenInput.nextInt();
                                    gameOption.setNumOfQuestions(numofquest);
                                } catch (Exception e) {
                                    //e.printStackTrace();
                                }
                            } else if (under_command == UNDER_COMMAND_CHANGE_LEVEL_HIGH) {
                                gameOption.setLevel("상");
                            } else if (under_command == UNDER_COMMAND_CHANGE_LEVEL_MIDDLE) {
                                gameOption.setLevel("중");
                            } else if (under_command == UNDER_COMMAND_CHANGE_LEVEL_LOW) {
                                gameOption.setLevel("하");
                            } else if (under_command == UNDER_COMMAND_EXIT) {
                                break;
                            }
                        } catch (Exception e) {

                        }
                    }

                } else if (command == COMMAND_EXIT) {
                    printMessage("게임을 종료합니다.");
                    break;
                }

            } catch (Exception e) {
                e.printStackTrace();
                printMessage("Error - 입력값 에러");
                screenInput.nextLine();   //  예외가 발생하면 버퍼를 비움   screenInput = new Scanner(System.in);
            }
        }
    }

    private void printMainUI() throws ClassNotFoundException, SQLException {
        // 메인 UI 출력
        System.out.println("==========================================================");
        System.out.println("현재 게임을 Play한 사용자 수 : " + gameRecord.numofuser());
        System.out.println("현재 난이도 : " + gameOption.getLevel() + "  현재 문항 수 : " + gameOption.getNumOfQuestions());
        System.out.println("1. 게임시작 ");
        System.out.println("2. 점수보기 ");
        System.out.println("3. 환경설정 ");
        System.out.println("4. 종료 ");
        System.out.println("==========================================================");
        System.out.print("Command> ");
    }

    private void printMessage(String str) {
        System.out.println(str);
    }

}
