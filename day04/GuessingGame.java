package day04;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GuessingGame {
	GuessingGame() {
		Scanner input = new Scanner(System.in);
        HashMap<String, Integer> users = new HashMap<>();
        int choice = 0;

        System.out.println("========== 숫자 맞추기 게임 =========");

        while (true) {
            try {
                System.out.println("1. 게임하기\n2. 기록보기\n3. 종료하기");
                System.out.print("선택: ");
                choice = input.nextInt();
                input.nextLine();
                
                if(choice == 3) {
                    System.out.println("게임을 종료합니다.");
                	break;
                }
                switch (choice) {
                    case 1:
                        System.out.print("사용자 이름을 입력하세요: ");
                        String username = input.nextLine();

                        users.put(username, 0);

                        System.out.println("숫자의 범위 입력 : ");
                        int start = input.nextInt();
                        int end = input.nextInt();
                        input.nextLine();

                        if (start > end) {
                            int tmp = start;
                            start = end;
                            end = tmp;
                        }

                        int randNum = (int) (Math.random() * (end - start + 1)) + start;
                        System.out.println(start + " ~ " + end + " 범위");

                        int attempt = 0;
                        int user;

                        while (true) {
                            System.out.print("숫자 입력: ");
                            user = input.nextInt();
                            attempt++;

                            if (user == randNum) {
                                System.out.println("정답입니다!");
                                break;
                            } else if (user > randNum) {
                                System.out.println(user + "보다 작습니다.");
                            } else {
                                System.out.println(user + "보다 큽니다.");
                            }
                        }

                        users.put(username, attempt);
                        System.out.println(username + " 게임 시도 횟수: " + attempt);
                        break;

                    case 2:
                        System.out.println("=== 기록 보기 ===");
                        for (String user1 : users.keySet()) {
                            System.out.println(user1 + ": 총 " + users.get(user1) + "회 시도");
                        }
                        System.out.println();
                        break;
                    default:
                        System.out.println("다시 입력");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력");
                input.nextLine();
            }
        }
	}

    public static void main(String[] args) {
        new GuessingGame();
    }
}
