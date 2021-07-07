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
			System.out.println("지난 플레이 내역이 없습니다.\n플레이 후 확인해주세요.\n");
			
			return;
		}
		makelist(i);
		
		String txt = scorelist.toJSONString();
		System.out.println(txt.replace("{","").replace("}","").replace("\"",""));
		//System.out.println(scorelist);
		//System.out.println(a);
		
		System.out.println("지난 플레이 내역입니다.\n자세히 확인하려는 번호를 입력해주세요.");
		System.out.println("초기화면으로 돌아가려면 0을 입력해주세요.\n");
		
		
		
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
			Object file = parser.parse(new FileReader("./file"+Integer.toString(num)+".json"));//주소.
	        JSONObject jobj = (JSONObject) file;
			for(int i =1;i<11;i++) {
				//Integer.toString(i)+"번"
				System.out.println(jobj.get(Integer.toString(i)+"번"));
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
	
	protected int comparenum(int k) {	//수정필요.
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
		//자료구조적 정리. score.json생성.
		//"key",[score,time]으로 저장.
		JSONObject inner = new JSONObject();
		inner.put("score",Integer.toString(score));
		inner.put("time", time);
		scorelist.put(Integer.toString(number++),inner);	//json형식 아님. 수정필요.	
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
				 
				 if(lengthOfInput.length != 1){ // 1개만 받는지 체크

			          System.out.println("1개의 숫자만 입력하세요.");
			          continue;
			        }
				 else if(input<0){
					 System.out.println("0미만의 수는 입력할 수 없습니다.");
			         sc.reset();
			         continue;
				 }
				 }
			 
			 catch(InputMismatchException ime){
				 sc = new Scanner(System.in);
	             System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
	             continue;
			 }
			 break;
		 }
		return input;
	}
}

