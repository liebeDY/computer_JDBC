package event;

import java.util.Scanner;

public class DBMain {

	public static void main(String[] args) {

		DB d = new DB();
		Scanner sc = new Scanner(System.in);
		
		boolean flag = true;
		int i = 0;
		
		while (flag) {
			
			System.out.println("1.등록 2.명단확인 3.종료");
			i = sc.nextInt();
			
			if (i == 1) {
				d.enroll();
			} else if (i == 2) {
				d.print();
			} else if (i == 3) {
				flag = false;
			}
		}
	}
}
