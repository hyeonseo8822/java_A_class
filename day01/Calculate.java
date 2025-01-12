package day01;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculate {

	public static void main(String[] args) {
		double n1,n2;
		String op;
		Scanner input = new Scanner(System.in);
		System.out.println("종료 0");
		while(true) {
			try {
				System.out.print("두 수 입력 : ");
				n1 = input.nextDouble();
				if(n1 == 0) {
					break;
				}
				n2 = input.nextDouble();
				input.nextLine();
				System.out.print("연산자 입력 : ");
				op = input.nextLine();
			
				if(op.equals("+")) {
					System.out.println(n1 + " + " + n2 + " = " + (n1 + n2));
				}
				else if(op.equals("-")) {
					System.out.println(n1 + " - " + n2 + " = " + (n1 - n2));
				}
				else if(op.equals("/")) {
					if(n2 == 0) {
						System.out.println("0으로 나눌 수 없음");
						continue;
					}
					System.out.println(n1 + " / " + n2 + " = " + (n1 / n2));
				}
				else if(op.equals("*")) {
					System.out.println(n1 + " * " + n2 + " = " + (n1 * n2));
				}
				else {
					System.out.println("다시 입력");
					continue;
				}
			}catch(InputMismatchException e) {
				System.out.println("숫자가 아닙니다.");
				input.nextLine();
			}
		}
		input.close();
	}

}
