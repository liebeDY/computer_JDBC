package member;

import java.util.Scanner;

public class DBMain {

	public static void main(String[] args) {

		DB d = new DB();
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		int i = 0;
		
		while (flag) {
			System.out.println("1.��� 2.��üȸ�� 3.�˻� 4.���� 5.���� 6.����");
			
			i = sc.nextInt();
			
			switch (i) {
			
			case 1:
				d.MemberInsert();
				break;
				
			case 2:
				
				break;
				
			case 3:
				
				break;
				
			case 4:
				
				break;
				
			case 5:
				
				break;
				
			case 6:
				flag = false;
				break;
			
			default :
				System.out.println("�߸� �Է�");
			}
		}
	}
}
