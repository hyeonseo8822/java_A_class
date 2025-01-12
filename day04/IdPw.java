package day04;

import java.util.HashMap;
import java.util.Scanner;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class IdPw {
	static Scanner input = new Scanner(System.in);
	private static HashMap<String, Information> login = new HashMap<>();
	public static void main(String[] args) {
		login.put("root",new Information("root","1234","eee","1111"));
		while (true) {
			System.out.println("1. 관리자 / 2. 회원 / 3. 종료");
			System.out.print("선택: ");
			int choice = input.nextInt();
			switch (choice) {
			case 1:
				if (isCorrect()) {
					System.out.println("관리자 로그인 성공!");
					while (true) {
						System.out.println("1. 수정 / 2. 목록 / 3. 삭제 / 4. 검색 / 5. 종료");
						System.out.print("선택: ");
						choice = input.nextInt();
						if(choice == 5) {
							System.out.println("프로그램을 종료합니다.");
							break;
						}
						switch (choice) {
						case 1:
							modifyInfo();
							break;
						case 2:
							System.out.println("회원 목록:");
							for (String id : login.keySet()) {
								System.out.println(login.get(id).toString());
							}
							break;
						case 3:
							System.out.print("삭제할 회원 ID: ");
							String deleteId = input.next();
							if (idExists(deleteId)) {
								login.remove(deleteId);
								System.out.println("회원 삭제 완료!");
							}
							break;
						case 4: 
							System.out.println("아이디 입력 : ");
							String Id = input.next();
							if(idExists(Id)) {
								System.out.println(login.get(Id).toString());
							}
							break;
						default:
							System.out.println("잘못된 선택입니다.");
						}
					}
				}
				break;
			case 2:
				while(true) {
					System.out.println("1. 회원가입하기 / 2. 로그인하기 / 3. 종료");
					System.out.print("선택: ");
					choice = input.nextInt();
					if(choice == 3) {
						System.out.println("프로그램을 종료합니다.");
						break;
					}
					switch (choice) {
					case 1:
						signUp();
						break;
					case 2:
						if (isCorrect() ) {
							System.out.println("로그인 성공!");
							while(true) {
								System.out.println("1. 숫자맞추기 게임/ 2. 주사위 게임 / 3. 종료");
								System.out.print("선택 : ");
								choice = input.nextInt();
								if(choice == 3) {
									break;
								}
								switch(choice) {
								case 1:
									new GuessingGame();
									break;
								case 2:
									new Dice();
									break;
								}
							}
						}
						break;
					default:
						System.out.println("잘못된 선택입니다.");
					}
				}
			}
		}
	}
	public static void signUp() {
		System.out.print("아이디: ");
		String id = input.next();

		if(isDuplicate(id)) {
			return;
		}

		System.out.print("비밀번호: ");
		String pw = input.next();

		System.out.print("이메일: ");
		String email = input.next();

		System.out.print("전화번호: ");
		String tel = input.next();

		login.put(id, new Information(id, pw, email, tel));
		System.out.println("회원가입이 완료되었습니다!");
	}
	static public boolean isCorrect() {
		System.out.print("아이디를 입력하세요: ");
		String id = input.next();

		System.out.print("비밀번호를 입력하세요: ");
		String pw = input.next();
		
		
		if (idExists(id)) {
			if (login.get(id).getPw().equals(pw)) {
				return true;
			} else {
				System.out.println("비밀번호가 틀렸습니다.");
				return false;
			}
		}
		return false;
	}
	public static void modifyInfo() {
		System.out.println("수정할 항목을 선택하세요:");
		System.out.println("1. 아이디 변경");
		System.out.println("2. 비밀번호 변경");
		System.out.println("3. 이메일 변경");
		System.out.println("4. 전화번호 변경");
		System.out.print("선택: ");
		int choice = input.nextInt();

		System.out.print("변경할 아이디 : ");
		String id = input.next();
		if(!idExists(id)) {
			return;
		}

		Information info = login.get(id);

		switch (choice) {
		case 1:
			System.out.print("아이디를 입력하세요: ");
			String Id = input.next();
			info.setId(Id);
			login.put(Id, info);
			login.remove(id);
			System.out.println("아이디가 변경되었습니다.");
			break;
		case 2:
			System.out.print("비밀번호를 입력하세요: ");
			String pw = input.next();
			info.setPw(pw);
			System.out.println("비밀번호가 변경되었습니다.");
			break;
		case 3:
			System.out.print("이메일을 입력하세요: ");
			String email = input.next();
			info.setEmail(email);
			System.out.println("이메일이 변경되었습니다.");
			break;
		case 4:
			System.out.print("전화번호를 입력하세요: ");
			String tel = input.next();
			info.setTel(tel);
			System.out.println("전화번호가 변경되었습니다.");
			break;
		default:
			System.out.println("잘못된 선택입니다.");
		}
	}
	public static boolean idExists(String id) {
	    if (login.containsKey(id)) {
	        return true;
	    } else {
	        System.out.println("해당 아이디가 존재하지 않습니다.");
	        return false;
	    }
	}
	public static boolean isDuplicate(String id) {
	    if (login.containsKey(id)) {
	        System.out.println("이미 존재하는 아이디입니다.");
	        return true;
	    }
	    return false;
	}
}

class Information {
	private String id;
	private String pw;
	private String email;
	private String tel;
	LocalTime now = LocalTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");        
	private String time = now.format(formatter);
	public Information(String id, String pw, String email, String tel) {
		this.id = id;
		this.pw = pw;
		this.email = email;
		this.tel = tel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	public String toString() {
		return "아이디 : " + id + ", 비밀번호: " + pw + ", 이메일: " + email + ", 전화번호: " + tel + ", 가입시간 : " + time;
	}
}
