import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;
public class day1 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Random rd = new Random();
		 Scanner sc = new Scanner(System.in);
		 int num1,num2,input;
		 int count=0;
		 
		 System.out.println("덧셈게임입니다.\n2자리 숫자의 합을 구해주세요.\n");
		 
		 for(int i=1;i<11;i++)
		 {
			 num1 = rd.nextInt(89)+10;
			 num2 = rd.nextInt(89)+10;
			 System.out.print(i+"번문제 : "+num1 + " + " + num2 + " = ");
			 input = isInt(i,sc,num1,num2);
			 count=checkanswer(num1,num2,input,count);
		 }
		 System.out.println("\n점수 : "+ count +"/10");
	}
	
	
	public static int checkanswer(int num1, int num2, int input, int count) {
		if(input==num1+num2)
		 {
			 count++;
		     System.out.println("맞았습니다!");
		 }
		 else{
		     System.out.println("틀렸습니다!");
		 }
		return count;
	}
	
	public static int isInt(int i,Scanner sc, int num1, int num2){
		int input;
		while(true)
		 {
			 try{
				 input = sc.nextInt();
				 //System.out.println("input = "+input);
				 String[] lengthOfInput = sc.nextLine().split(" ");
				 
				 if(lengthOfInput.length != 1){ // 1개만 받는지 체크

			          System.out.println("1개의 숫자만 입력하세요.");
			          sc.reset();
			          System.out.print(i+"번문제 : "+num1 + " + " + num2 + " = ");
			          continue;
			        }
				 else if(input<0){
					 System.out.println("양수를 입력하세요.");
			         sc.reset();
			         System.out.print(i+"번문제 : "+num1 + " + " + num2 + " = ");
			         continue;
				 }
				 else if(input >=0){
					 
				 }
				 else{
					 System.out.println("정수를 입력하세요.");				
			         sc.reset();
			         System.out.print(i+"번문제 : "+num1 + " + " + num2 + " = ");
			         continue;
				 }
				 }
			 
			 catch(InputMismatchException ime){

				 System.out.println(ime.getLocalizedMessage());
				 sc = new Scanner(System.in);
	             System.out.println("\n정수만 입력가능합니다.\n다시 입력해주세요.\n");
				 System.out.print(i+"번문제 : "+num1 + " + " + num2 + " = ");
	             continue;
			 }
			 break;
		 }
		return input;
	}
}


