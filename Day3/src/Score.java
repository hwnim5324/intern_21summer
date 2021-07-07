import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Collections;



public class Score{
	
	static JSONObject scorelist = new JSONObject();
	static int number=1;
	int score;
	String time;
	static Scanner sc = new Scanner(System.in);
	LinkedList<Integer> li1 = new LinkedList<Integer>();
	LinkedList<Integer> li2 = new LinkedList<Integer>();
	protected void showscore(int i){
		
		if(i==1){
			System.out.println("���� �÷��� ������ �����ϴ�.\n�÷��� �� Ȯ�����ּ���.\n");
			
			return;
		}
		makelist(i);
		
		String txt = scorelist.toJSONString();
		System.out.println(txt.replace("{","").replace("}","").replace("\"",""));
		//System.out.println(scorelist);
		//System.out.println(a);
		
		System.out.println("���� �÷��� �����Դϴ�.\n�ڼ��� Ȯ���Ϸ��� ��ȣ�� �Է����ּ���.");
		System.out.println("�ʱ�ȭ������ ���ư����� 0�� �Է����ּ���.\n");
		
		
		
		int input = isInt();
		if(input == 0)
		{
			return;
		}
		showdetail(input);
	}
	
	protected void showdetail(int j){
		int num = comparenum(j);
		JSONParser parser = new JSONParser();
		try {
			Object file = parser.parse(new FileReader("./file"+Integer.toString(num)+".json"));//�ּ�.
	        JSONObject jobj = (JSONObject) file;
			for(int i =1;i<11;i++) {
				//Integer.toString(i)+"��"
				System.out.println(jobj.get(Integer.toString(i)+"��"));
			}
			System.out.println();
		}
		catch (ParseException e) { 
			e.printStackTrace(); 
			}
		catch (IOException ie) {
			ie.printStackTrace();
		}
	}
	
	protected int comparenum(int k) {	//�����ʿ�.
		Collections.reverse(li2);
		int n = li2.get(k-1);
		int index = li1.indexOf(n);
		return index;
	}
	
	protected void getscore(int score){
		this.score = score;
	}
	protected void gettime(Date time){
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.time  = transFormat.format(time);
	}
	
	protected void makelist(int a){
		//�ڷᱸ���� ����. score.json����.
		//"key",[score,time]���� ����.
		JSONObject inner = new JSONObject();
		inner.put("score",Integer.toString(score));
		inner.put("time", time);
		scorelist.put(Integer.toString(number++),inner);	//json���� �ƴ�. �����ʿ�.	
		li1.add(score);
		li2.add(score);
		
	}
	
	protected int isInt(){
		int input;
		while(true)
		 {
			 try{
				 input = sc.nextInt();
				 String[] lengthOfInput = sc.nextLine().split(" ");
				 
				 if(lengthOfInput.length != 1){ // 1���� �޴��� üũ

			          System.out.println("1���� ���ڸ� �Է��ϼ���.");
			          continue;
			        }
				 else if(input<0){
					 System.out.println("0�̸��� ���� �Է��� �� �����ϴ�.");
			         sc.reset();
			         continue;
				 }
				 }
			 
			 catch(InputMismatchException ime){
				 sc = new Scanner(System.in);
	             System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
	             continue;
			 }
			 break;
		 }
		return input;
	}
}

