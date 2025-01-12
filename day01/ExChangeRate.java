package day01;

import java.util.Scanner;

public class ExChangeRate {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int choice = 0;
		double exchangeRate = 0,money = 0;
		while(true) {
			try {
				System.out.print("1. 원화/2. 달라/ 아무키 종료 입력 : ");
				choice = input.nextInt();

				System.out.print("환율 입력 : ");
				exchangeRate = input.nextDouble();

				System.out.print("변환할 금액 입력 : ");
				money = input.nextDouble();
			}catch(Exception e) {
				System.out.println("에러 : " + e.getMessage());
				input.nextLine();
			}
			if(choice == 1) {
				System.out.printf("변환된 금액: %.2f 달러\n", money / exchangeRate);
			}
			else if(choice == 2) {
				System.out.printf("변환된 금액: %.2f 원\n", money * exchangeRate);
			}
			else {
				System.out.println("종료");
				break;
			}
			
		}
		input.close();
	}

}