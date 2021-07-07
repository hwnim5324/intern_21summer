import java.util.Scanner;
import java.util.Date;
import java.util.Random;
import java.util.InputMismatchException;
import org.json.simple.*;
import java.io.FileWriter;
import java.io.IOException;


public class Game {
	static Scanner sc = new Scanner(System.in);
	static JSONObject score_file = new JSONObject();
	int score;
	Date time;
	
	private static int checkanswer(int num1, int num2, int input, int count) {
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
	
	protected int isInt(int i, int num1, int num2){
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

				 //System.out.println(ime.getLocalizedMessage());
				 sc = new Scanner(System.in);
	             System.out.println("\n정수만 입력가능합니다.\n다시 입력해주세요.\n");
				 System.out.print(i+"번문제 : "+num1 + " + " + num2 + " = ");
	             continue;
			 }
			 break;
		 }
		return input;
	}
	
	protected void playgame(int j){		
		Random rd = new Random();
		 int num1,num2,input;
		 int count=0;
		 
		 System.out.println("덧셈게임입니다.\n2자리 숫자의 합을 구해주세요.\n");
		 
		 for(int i=1;i<11;i++)
		 {
			 num1 = rd.nextInt(89)+10;
			 num2 = rd.nextInt(89)+10;
			 System.out.print(i+"번문제 : "+num1 + " + " + num2 + " = ");
			 String Q = Integer.toString(num1)+" + "+Integer.toString(num2) + " = ?";//+ Integer.toString(num1+num2)
			 input = isInt(i,num1,num2);
			 String RA = Integer.toString(num1+num2);
			 String A = Integer.toString(input);
			 count=checkanswer(num1,num2,input,count);
			 save(i,Q,RA,A);
			 
		 }
		 System.out.println("\n점수 : "+ count +"/10");
		 score = count;
		 time = new Date(System.currentTimeMillis());
		 save_json(j);
	}
	
	protected static void save(int i, String Q,String RA, String A){
		JSONObject inner = new JSONObject();
		inner.put("문제", Q);
		inner.put("정답", RA);
		inner.put("사용자의 답", A);
		
		String num = Integer.toString(i)+"번";
		
//		String a = outer.toJSONString();
//		a=a.replace("{","").replace("}","").replace("\"","");
//		System.out.println(a);
		//이쁘게 출력.
		
		score_file.put(num,inner);

	}
	
	protected static void save_json(int j){
		try {
			FileWriter file = new FileWriter("./file"+Integer.toString(j)+".json");
			file.write(score_file.toJSONString());
			file.flush();
			file.close();
			score_file.clear();
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
