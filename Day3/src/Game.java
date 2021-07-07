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
		     System.out.println("�¾ҽ��ϴ�!");
		 }
		 else{
		     System.out.println("Ʋ�Ƚ��ϴ�!");
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
				 
				 if(lengthOfInput.length != 1){ // 1���� �޴��� üũ

			          System.out.println("1���� ���ڸ� �Է��ϼ���.");
			          sc.reset();
			          System.out.print(i+"������ : "+num1 + " + " + num2 + " = ");
			          continue;
			        }
				 else if(input<0){
					 System.out.println("����� �Է��ϼ���.");
			         sc.reset();
			         System.out.print(i+"������ : "+num1 + " + " + num2 + " = ");
			         continue;
				 }
				 else if(input >=0){
					 
				 }
				 else{
					 System.out.println("������ �Է��ϼ���.");				
			         sc.reset();
			         System.out.print(i+"������ : "+num1 + " + " + num2 + " = ");
			         continue;
				 }
				 }
			 
			 catch(InputMismatchException ime){

				 //System.out.println(ime.getLocalizedMessage());
				 sc = new Scanner(System.in);
	             System.out.println("\n������ �Է°����մϴ�.\n�ٽ� �Է����ּ���.\n");
				 System.out.print(i+"������ : "+num1 + " + " + num2 + " = ");
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
		 
		 System.out.println("���������Դϴ�.\n2�ڸ� ������ ���� �����ּ���.\n");
		 
		 for(int i=1;i<11;i++)
		 {
			 num1 = rd.nextInt(89)+10;
			 num2 = rd.nextInt(89)+10;
			 System.out.print(i+"������ : "+num1 + " + " + num2 + " = ");
			 String Q = Integer.toString(num1)+" + "+Integer.toString(num2) + " = ?";//+ Integer.toString(num1+num2)
			 input = isInt(i,num1,num2);
			 String RA = Integer.toString(num1+num2);
			 String A = Integer.toString(input);
			 count=checkanswer(num1,num2,input,count);
			 save(i,Q,RA,A);
			 
		 }
		 System.out.println("\n���� : "+ count +"/10");
		 score = count;
		 time = new Date(System.currentTimeMillis());
		 save_json(j);
	}
	
	protected static void save(int i, String Q,String RA, String A){
		JSONObject inner = new JSONObject();
		inner.put("����", Q);
		inner.put("����", RA);
		inner.put("������� ��", A);
		
		String num = Integer.toString(i)+"��";
		
//		String a = outer.toJSONString();
//		a=a.replace("{","").replace("}","").replace("\"","");
//		System.out.println(a);
		//�̻ڰ� ���.
		
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
