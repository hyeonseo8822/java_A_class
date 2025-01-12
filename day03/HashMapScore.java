package day03;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HashMapScore {

	public static void main(String[] args) {
		HashMap<String , Integer> javaScore = new HashMap<>();
		javaScore.put("현서", 98);
		javaScore.put("준성", 50);	

		System.out.println("HashMap의 요소의 개수" + javaScore.size());
		Set<String> keys = javaScore.keySet();
		Iterator<String> it = keys.iterator();
		
		while(it.hasNext()) {
			String name = it.next();
			int score = javaScore.get(name);
			System.out.println(name + " : " + score);
		}
	}
}
