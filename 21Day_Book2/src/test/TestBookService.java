package test;

import java.util.Scanner;

import service.BookDAO;
import vo.BookVO;

public class TestBookService {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		String id;
		String title;
		String author;
		String publisher;
		int price;
		
		boolean flag = true;
		
		int menu = 0;
		BookVO b = null;
		BookDAO service = new BookDAO();
		
		while (flag) {
			
			System.out.println("1.��� 2.�˻� 3.���� 4.���� 5.����");
			menu = sc.nextInt();
			
			switch (menu) {
			
			// 1.���
			case 1:
				System.out.println("id�� �Է��ϼ��� : ");
				id = sc.next();
				System.out.println("å�̸��� �Է��ϼ��� : ");
				title = sc.next();
				System.out.println("���ڸ� �Է��ϼ��� : ");
				author = sc.next();
				System.out.println("���Ļ縦 �Է��ϼ��� : ");
				publisher = sc.next();
				System.out.println("������ �Է��ϼ��� : ");
				price = sc.nextInt();
				
				service.bookinsert(new BookVO(id, title, author, publisher, price));
				break;

			// 2.�˻�
			case 2:
				System.out.println("�˻��� id�� �Է��ϼ��� :");
				id = sc.next();
				b = service.bookselect(id);
				System.out.println(b);
				break;
				
			// 3.����
			case 3:
				System.out.println("������ id�� �Է��ϼ��� : ");
				id = sc.next();
				System.out.println("������ å�̸��� �Է��ϼ��� : ");
				title = sc.next();
				System.out.println("������ ���ڸ� �Է��ϼ��� : ");
				author = sc.next();
				System.out.println("������ ���ǻ縦 �Է��ϼ��� : ");
				publisher = sc.next();
				System.out.println("������ ������ �Է��ϼ��� : ");
				price = sc.nextInt();
				
				b = new BookVO(id, title, author, publisher, price);
				service.bookupdate(b);
				
				break;
				
			// 4.����
			case 4:
				System.out.println("������ id�� �Է��ϼ��� : ");
				id = sc.next();
				service.bookdelete(id);
				break;
				
			// 5.����
			case 5:
				flag = false;
				break;
			
			default:
				System.out.println("�߸� �Է�");
			
			}
		}
	}
}
