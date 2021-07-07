import java.util.InputMismatchException;
import java.util.Scanner;

public class Play {
static Scanner sc = new Scanner(System.in);
static int j=1;
	protected int isInt(){
		int input;
		while(true)
		 {
			 try{
				 input = sc.nextInt();
				 //System.out.println("input = "+input);
				 String[] lengthOfInput = sc.nextLine().split(" ");
				 
				 if(lengthOfInput.length != 1){ // 1���� �޴��� üũ

			          System.out.println("1~3 �� �ϳ��� �Է��ϼ���.");
			          System.out.println("���Ͻô� �޴��� ��ȣ�� �Է��ϼ���.");
					  System.out.println("1.����Ȯ��\n2.����\n3.����\n");
			          sc.reset();
			          continue;
			        }
				 else if(input<1||input>3){
					 System.out.println("1~3 �� �ϳ��� �Է��ϼ���.");
					 System.out.println("���Ͻô� �޴��� ��ȣ�� �Է��ϼ���.");
					 System.out.println("1.����Ȯ��\n2.����\n3.����\n");
			         sc.reset();
			         continue;
				 }
				 }
			 
			 catch(InputMismatchException ime){

				 //System.out.println(ime.getLocalizedMessage());
				 sc = new Scanner(System.in);
	             System.out.println("1~3 �� �ϳ��� �Է��ϼ���.\n");
	             System.out.println("���Ͻô� �޴��� ��ȣ�� �Է��ϼ���.");
	 			 System.out.println("1.����Ȯ��\n2.����\n3.����\n");
	             continue;
			 }
			 break;
		 }
		return input;
	}
	
	
	public void start(){
		int select;
		Game g = new Game();
		Score s = new Score();
		while(true){
			System.out.println("���Ͻô� �޴��� ��ȣ�� �Է��ϼ���.");
			System.out.println("1.����Ȯ��\n2.����\n3.����\n");
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
		System.out.println("����Ǿ����ϴ�.");
	}
}
