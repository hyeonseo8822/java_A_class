package day02;

import java.util.HashMap;
import java.util.Scanner;

public class HMDEx {

	public static void main(String[] args) {
		HashMap<String,String> dic = new HashMap<>();
		Scanner input = new Scanner(System.in);
		
		dic.put("hy2on_.s20", "현서");
		dic.put("jun_xse36", "준성");
		
		while(true) {
			System.out.print("검색할 단어 입력 : ");
			String word = input.nextLine();
			if(word.equals("종료")) {
				System.out.println("종료");
				break;
			}
			String kor = dic.get(word);
			if(kor == null) {
				System.out.println(word + "는 없는 단어 입니다");
			}
			else {
				System.out.println(kor);
			}
		}
	}

}
