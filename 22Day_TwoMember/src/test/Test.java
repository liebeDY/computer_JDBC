package test;

import java.util.Scanner;

import service.BookService;
import service.CustomerService;
import vo.BookVO;
import vo.Customer;

public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		boolean flag1 = true;
		boolean flag2 = true;
		int menu2 = 0;
		String id;
		String title;
		String author;
		String publisher;
		int price;
		
		String name;
		String addr;
		String tel;
		
		Customer b = null;
		
		CustomerService ser = new CustomerService();
		BookService service = new BookService();

		while (flag1) {
			System.out.println("1.�� 2.å");
			int menu1 = sc.nextInt();
			switch (menu1) {
			// �� case
			case 1:
				while (flag2) {
					System.out.println("1.��� 2.�˻� 3.��ü��� 4.����, 5.���� 6.����");
					menu2 = sc.nextInt();
					
					switch (menu2) {
					// �� ���
					case 1:
						System.out.println("�̸��� �Է��ϼ��� : ");
						name = sc.next();
						System.out.println("�ּҸ� �Է��ϼ��� : ");
						addr = sc.next();
						System.out.println("��ȭ��ȣ�� �Է��ϼ��� : ");
						tel = sc.next();
						
						b = new Customer(name, addr, tel);
						ser.insert(b);
						break;
						
					// �� �˻�
					case 2:
						System.out.println("�˻��� �̸��� �Է��ϼ��� : ");
						name = sc.next();
						
						b = ser.findCustomer(name);
						System.out.println(b);
						break;
						
					// ��ü���
					case 3:
						ser.printAll();
						break;
						
					// �� ����
					case 4:
						System.out.println("������ �̸��� �Է��ϼ��� : ");
						name = sc.next();
						ser.delete(name);
						break;
						
					// �� ����	
					case 5:
						System.out.println("������ �̸��� �Է��ϼ��� : ");
						name = sc.next();
						System.out.println("������ �ּҸ� �Է��ϼ��� : ");
						addr = sc.next();
						System.out.println("������ ��ȣ�� �Է��ϼ��� : ");
						tel = sc.next();
						
						b = new Customer(name, addr, tel);
						ser.update(b);
						break;
						
					// �޴� ����
					case 6:
						flag2 = false;
						break;
					}
				}
				break;
			
			// å case
			case 2:
				while (flag2) {
					System.out.println("1.å��� 2.�˻� 3.���� 4.���� 5.��ü��� 6.����");
					menu2 = sc.nextInt();
					
					BookVO b1 = null;
					switch (menu2) {
					
					// å ���
					case 1:
						System.out.println("å id�� �Է��ϼ��� : ");
						id = sc.next();
						System.out.println("���ڸ� �Է��ϼ��� : ");
						author = sc.next();
						System.out.println("������ �Է��ϼ��� : ");
						title = sc.next();
						System.out.println("���ǻ縦 �Է��ϼ��� : ");
						publisher = sc.next();
						System.out.println("������ �Է��ϼ��� : ");
						price = sc.nextInt();
						
						b1 = new BookVO(id, author, title, publisher, price);
						
						service.insert(b1);
						break;
					
					// å �˻�
					case 2:
						System.out.println("�˻��� å id�� �Է��ϼ��� :");
						id = sc.next();
						
						b1 = service.findbook(id);
						System.out.println(b1);
						break;

					// å ����
					case 3:
						System.out.println("������ å id�� �Է��ϼ��� : ");
						id = sc.next();
						System.out.println("������ ���ڸ� �Է��ϼ��� : ");
						author = sc.next();
						System.out.println("������ ������ �Է��ϼ��� : ");
						title = sc.next();
						System.out.println("������ ���ǻ縦 �Է��ϼ��� : ");
						publisher = sc.next();
						System.out.println("������ ������ �Է��ϼ��� : ");
						price = sc.nextInt();
						
						b1 = new BookVO(id, title, author, publisher, price);
						
						service.updatebook(b1);
						break;

					// å ����
					case 4:
						System.out.println("������ åid�� �Է��ϼ��� : ");
						id = sc.next();
						
						service.deletebook(id);
						break;

					// ��ü ���
					case 5:
						service.printAll();
						break;
						
						
					case 6:
						flag = false;
						break;

					}
				}
				break;
			}
		}
	}
}
