package day03;

import java.util.ArrayList;
import java.util.Scanner;

public class AttendanceSystem {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		AttendanceManager a = new AttendanceManager();
		while(true) {
			System.out.println("1. 등록\n2. 목록\n3. 출석체크\n4. 출석현황\n5. 종료");
			System.out.print("선택 : ");
			int choice = input.nextInt();
			input.nextLine();
			switch(choice) {
			case 1:
				System.out.print("등록 학생 이름 : ");
				String name = input.nextLine();
				a.addStudent(name);
				break;
			case 2:
				System.out.println("학생 목록 : ");
				a.studentList();
				break;
			case 3:
				System.out.println("출석 학생 이름 : ");
				name = input.nextLine();
				System.out.println("등교 시간 : ");
				int hour = input.nextInt();
				int minute = input.nextInt();
				a.presentCheck(name, hour,minute);
				break;
			case 4:
				a.presentStatus();
				break;
			case 5:
				System.out.println("종료");
				System.exit(0);
				break;
			}
		}
	}

}
class Student {
	private String name;
	private boolean isPresent;
	private String status;
	
	public Student(String name) {
		this.name = name;
		isPresent = false;
		status = "결석";
	}
	public String getName() {
		return name;
	}
	public boolean isPresent() {
		return isPresent;
	}
	public String getStatue() {
		return status;
	}
	public void markPresent() {
		status = "출석";
		this.isPresent = true;
	}
	public void markLate() {
		status = "지각";
		this.isPresent = true;
	}
	public void markAbsent() {
		this.isPresent = false;
	}
}
class AttendanceManager {
	private ArrayList<Student> students;
	public AttendanceManager() {
		students = new ArrayList<>();
	}
	public void addStudent(String name) {
		for(Student student : students) {
			if(student.getName().equals(name)) {
				System.out.println("이미 등록된 학생입니다.");
				return;
			}
		}
		students.add(new Student(name));
		System.out.println(name + "이 등록되었습니다.");
	}
	public void studentList() {
		int i = 1;
		for(Student student : students) {
			System.out.println(i + ". " + student.getName());
			i++;
		}
		System.out.println();
	}
	public void presentCheck(String name, int hour, int minute) {
		boolean found = false; 
	    for (Student student : students) {
	        if (student.getName().equals(name)) {
	            if (student.isPresent()) {
	                System.out.println("이미 출석체크가 되었습니다.");
	                found = true;
	            } else {
	                if (hour > 8 || (hour == 8 && minute >= 10)) {
	                    student.markLate();
	                    System.out.println(name + " 지각 처리되었습니다.");
	                } else {
	                    student.markPresent();
	                    System.out.println(name + " 출석 처리되었습니다.");
	                }
	                found = true;
	            }
	        }
	    }
	    if(!found) {
	    	System.out.println("이런 학생은 없습니다.");
	    }
	}
	public void presentStatus() {
		int i = 1;
		for(Student student : students) {
//			System.out.println(i + ". " + student.getName() + " " + (student.isPresent() ? "출석" : "결석"));
			System.out.println(i + ". " + student.getName() + " " + student.getStatue());
			i++;
		}
		System.out.println();
		System.out.println();
	}
}