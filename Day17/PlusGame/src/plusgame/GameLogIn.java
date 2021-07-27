package plusgame;

import java.sql.SQLException;
import java.util.Scanner;

public class GameLogIn {

    private DAO dao = new DAO();  //DB와 연동하여 정보를 저장, 불러오는 클래스.
    Scanner sc = new Scanner(System.in);

    private static String id = "";

    public void createAccount() throws ClassNotFoundException, SQLException {
        String userId = "";
        String userName = "";
        String userPw = "";
        while(true){
            printCreateAccountIDUI();
            userId = sc.next();
            if(checkId(userId)){
                break;
            }
        }
        printCreateAccountNameUI();
        userName = sc.next();
        printCreateAccountPWUI();
        userPw = sc.next();
        dao.insert_UserInfoDB(userId, userName, userPw);
        dao.insert_OptionDB(userId, "상", "10");
        printCreateAccountSuccessUI();
    }

    public boolean checkId(String userId) throws ClassNotFoundException, SQLException {
        String id = "";

        id = dao.getString_DB("SELECT userId FROM userinfo WHERE userid='" + userId + "'","userId");
        if (id.equals(userId)) {
            System.out.println("이미 존재하는 아이디입니다.");
            return false;
        } else {
            System.out.println("사용가능한 아이디입니다.");
            return true;
        }
    }

    public boolean login() throws ClassNotFoundException, SQLException {
        String userId = "";
        String userPw = "";
        while (true) {
            try {
                printIDUI();
                userId = sc.next();
                printPWUI();
                userPw = sc.next();

                String id = dao.getString_DB("SELECT userId FROM userinfo WHERE userid='" + userId + "'","userId");
                String pw = dao.getString_DB("SELECT userPw FROM userinfo WHERE userid='" + userId + "'","userPw");               
                
                if (id.equals(userId) && pw.equals(userPw)) {
                    this.id = userId;
                    printLogInSuccessUI();
                    return true;
                } else {
                    printLogInErrorUI();
                    return false;
                }
            } catch (Exception e) {
                sc.nextLine();
            }
        }
    }

    public void loginTask() {
        int account_command = 0;
        while (true) {
            try {
                printAccountUI();
                account_command = sc.nextInt();
                if (account_command == 1) {
                    if(login()){
                        return;
                    }
                } else if (account_command == 2) {
                    createAccount();
                }
            } catch (Exception e) {
                sc.nextLine();
            }

        }

    }

    public String getId() {
        return this.id;
    }

    public void printAccountUI() {
        System.out.println("==========================================================");
        System.out.println("1. 로그인 ");
        System.out.println("2. 계정생성 ");
        System.out.println("==========================================================");
        System.out.print("Command> ");
    }

    public void printCreateAccountIDUI() {
        System.out.println("==========================================================");
        System.out.print("아이디 : ");
    }

    public void printCreateAccountNameUI() {
        System.out.print("이름 : ");
    }

    public void printCreateAccountPWUI() {
        System.out.print("비밀번호 : ");
    }
    
    public void printCreateAccountSuccessUI() {
        System.out.println("==========================================================");
        System.out.println("계정생성 완료!");
        System.out.println("==========================================================");
    }

    public void printIDUI() {
        System.out.println("==========================================================");
        System.out.print("아이디 : ");
    }

    public void printPWUI() {
        System.out.print("비밀번호 : ");
    }

    public void printLogInErrorUI() {
        System.out.println("==========================================================");
        System.out.println("없는 계정이거나\n잘못된 아이디 혹은 비밀번호입니다.\n다시 입력해주세요.");
        System.out.println("==========================================================");
    }

    public void printLogInSuccessUI() {
        System.out.println("==========================================================");
        System.out.println("로그인 완료!\n" + this.id + "님 환영합니다.");
        System.out.println("==========================================================");
    }

}
