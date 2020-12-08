package test;

import java.util.Scanner;

import service.EventDao;
import vo.Event;

public class DBMain {

	public static void main(String[] args) {

		EventDao d = new EventDao();
		Scanner sc = new Scanner(System.in);
		
		boolean flag = true;
		
		int num;
		String  email;
		
		int i = 0;
		
		while (flag) {
			
			System.out.println("1.��� 2.���Ȯ�� 3.�˻� 4.���� 5.���� 6.����");
			i = sc.nextInt();
			
			switch (i) {
			
			case 1:
				d.enroll();
				break;
			case 2:
				System.out.println(d.print());
				break;
				
			case 3:
				System.out.println("�˻��� ��ȣ�� �Է��ϼ��� : ");
				num = sc.nextInt();
				Event p = d.search(num);
				System.out.println(p);
				break;
				
			case 4:
				System.out.println("������ ��ȣ�� �Է��ϼ��� : ");
				num = sc.nextInt();
				System.out.println("Email�� �Է��ϼ��� : ");
				email = sc.next();
				Event v = new Event(num, email);
				d.updateEvent(v);
				break;
				
			case 5:
				System.out.println("������ ��ȣ�� �Է��ϼ��� : ");
				num = sc.nextInt();
				d.delete(num);
				break;
				
			case 6:
				flag = false;
				break;
				
            default:                                                                                                                                                                                     			default :
				System.out.println("�߸� �Է�");
			}
		}
	}
}
