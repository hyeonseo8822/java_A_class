package day02;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListEx {

    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<String>();
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            System.out.print("이름을 입력해주세요: ");
            String s = input.nextLine();
            a.add(s);
        }
        String longName = "";
        for (int i = 0; i < a.size(); i++) {
            String name = a.get(i);
            if (name.length() > longName.length()) {
                longName = name;
            }
        }
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
        System.out.println("\n가장 긴 이름: " + longName);
    }
}
