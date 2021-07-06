import java.util.InputMismatchException;
import java.util.Scanner;

public class Play{
	static Scanner sc = new Scanner(System.in);
	
	protected int isInt(){
		int input;
		while(true)
		 {
			 try{
				 input = sc.nextInt();
				 //System.out.println("input = "+input);
				 String[] lengthOfInput = sc.nextLine().split(" ");
				 
				 if(lengthOfInput.length != 1){ // 1개만 받는지 체크

			          System.out.println("1~3 중 하나만 입력하세요.");
			          System.out.println("원하시는 메뉴의 번호를 입력하세요.");
					  System.out.println("1.점수확인\n2.게임\n3.종료\n");
			          sc.reset();
			          continue;
			        }
				 else if(input<1||input>3){
					 System.out.println("1~3 중 하나만 입력하세요.");
					 System.out.println("원하시는 메뉴의 번호를 입력하세요.");
					 System.out.println("1.점수확인\n2.게임\n3.종료\n");
			         sc.reset();
			         continue;
				 }
				 }
			 
			 catch(InputMismatchException ime){

				 //System.out.println(ime.getLocalizedMessage());
				 sc = new Scanner(System.in);
	             System.out.println("1~3 중 하나만 입력하세요.\n");
	             System.out.println("원하시는 메뉴의 번호를 입력하세요.");
	 			 System.out.println("1.점수확인\n2.게임\n3.종료\n");
	             continue;
			 }
			 break;
		 }
		return input;
	}
	
	
	public void start(){
		int select;
		int j=1;
		game g = new game();
		Score s = new Score();
		while(true){
			System.out.println("원하시는 메뉴의 번호를 입력하세요.");
			System.out.println("1.점수확인\n2.게임\n3.종료\n");
			select = isInt();
			if(select == 1){
				s.showscore(j);
			}
			else if(select ==2){
				g.playgame(j++);
				s.getscore(g.score);
				s.gettime(g.time);
			}
			else{
				break;
			}
		}
		System.out.println("종료되었습니다.");
	}
}
