package event;

import java.util.Scanner;

public class DBMain {

	public static void main(String[] args) {

		DB d = new DB();
		Scanner sc = new Scanner(System.in);
		
		boolean flag = true;
		int i = 0;
		
		while (flag) {
			
			System.out.println("1.��� 2.���Ȯ�� 3.����");
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
