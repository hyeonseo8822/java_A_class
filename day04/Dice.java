package day04;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Dice {
	Dice() {
		Scanner input = new Scanner(System.in);
        HashMap<String, Integer> users = new HashMap<>();
        int answer = 0;
        while (answer != 3) {
            int user1Sum = 0;
            int user2Sum = 0;
            System.out.println("1. 게임하기\n2. 기록보기\n3. 종료하기");
            System.out.print("입력: ");
            try {
                answer = input.nextInt();
                input.nextLine();

                switch (answer) {
                    case 1:
                        System.out.print("첫 번째 사용자 이름: ");
                        String username1 = input.nextLine();
                        System.out.print("두 번째 사용자 이름: ");
                        String username2 = input.nextLine();

                        users.putIfAbsent(username1, 0);
                        users.putIfAbsent(username2, 0);

                        int win1 = users.get(username1);
                        int win2 = users.get(username2);

                        for (int i = 0; i < 2; i++) {
                            int user1Dice = (int) (Math.random() * 6) + 1;
                            int user2Dice = (int) (Math.random() * 6) + 1;
                            
                            user1Sum += user1Dice;
                            user2Sum += user2Dice;
                            
                            System.out.println("\n" + username1 + "의 주사위: " + user1Dice);
                            System.out.println(username2 + "의 주사위: " + user2Dice);
                        }
                        if(user2Sum > 10 && user1Sum > 10) {
                        	System.out.println("다시 합니다.\n\n");
                        	continue;
                        }
                        else if(user2Sum > 10) {
                        	System.out.println(username2 +"이 10을 넘어 패배\n\n");
                        	win1++;
                        }
                        else if(user1Sum > 10){
                        	System.out.println(username1 +"이 10을 넘어 패배\n\n");
                        	win2++;
                        }
                        else if (user1Sum > user2Sum) {
                            System.out.println(username1 + " 승리!\n\n");
                            win1 += 1;
                        } else if (user1Sum < user2Sum) {
                            System.out.println(username2 + " 승리!\n\n");
                            win2 += 1;
                        } else {
                            System.out.println("무승부!\n\n");
                        }

                        users.put(username1, win1);
                        users.put(username2, win2);

                        break;

                    case 2:
                        System.out.println("\n=== 사용자 기록 ===");
                        for (String user : users.keySet()) {
                            System.out.println(user + ": " + users.get(user) + "승");
                        }
                        System.out.println();
                        break;

                    case 3:
                        System.out.println("게임을 종료합니다.");
                        break;

                    default:
                        System.out.println("잘못된 입력");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력");
                input.nextLine();
            }
        }
	}

    public static void main(String[] args) {
        new Dice();
    }
}
