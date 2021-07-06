import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Score{
	int score;
	String time;
	
	protected void showscore(int i){
		
		if(i==1){
			System.out.println("지난 플레이 내역이 없습니다.\n플레이 후 확인해주세요.");
			
			return;
		}
		
		System.out.println("지난 플레이 내역입니다.\n자세히 확인하려는 번호를 입력해주세요.");
		System.out.println("초기화면으로 돌아가려면 0을 입력해주세요.\n");
		//출력.
		
		//출력 끝나면 입력받는데, 입력은 isInt사용. 입력에 따라서 showdetail()실행.
		//isInt의 인터페이스화 혹은 클래스 단독생성 고려.
	}
	
	protected void showdetail(int j){
		//저장된 파일번호와 j의 값을 비교해서
		//원하는 파일 출력.
		//이를 위해서 linked list로 비교 배열 저장 필요.
	}
	
	protected void getscore(int score){
		this.score = score;
	}
	protected void gettime(Date time){
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.time  = transFormat.format(time);
	}
	
	protected void makelist(){
		//자료구조적 정리. score.json생성.
		//"key",[score,time]으로 저장.
	}
}
