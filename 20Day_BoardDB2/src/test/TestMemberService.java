package test;

import java.util.Scanner;

import service.BoardService;
import vo.Board;

public class TestMemberService {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		String id;
		String title;
		String contents;
		
		boolean flag = true;
		
		int menu = 0;
		
		BoardService service = new BoardService();
		
		while (flag) {
			System.out.println("1.��� 2.�˻� 3.���� 4.���� 5.��ü��� 6.����");
			
			menu = sc.nextInt();
			
			switch(menu) {
			
			// ���
			case 1:
				System.out.println("���̵� �Է��ϼ��� : ");
				id = sc.next();
				System.out.println("������ �Է��ϼ��� : ");
				title = sc.nextLine(); // �տ��� �Է��� ���͸� �����ֱ� ���ؼ�
				System.out.println("������ �Է��ϼ��� : ");
				contents = sc.nextLine();
				
				service.insert(new Board(id, title, contents));
				break;
			
			// �˻�
			case 2:
				System.out.println("�˻��� ���̵� �Է��ϼ��� : ");
				id = sc.next();
				Board b = service.find(id);
				System.out.println(b);
				break;
				
			// ����
			case 3:
				System.out.println("������ ���̵� �Է��ϼ��� : ");
				id = sc.next();
				System.out.println("������ ������ �Է��ϼ��� : ");
				title = sc.next();
				System.out.println("������ ������ �Է��ϼ��� : ");
				contents = sc.next();
				
				Board b1 = new Board(id, title, contents);
				service.update(b1);
				break;
				
			// ����
			case 4:
				System.out.println("������ ���̵� �Է��ϼ��� : ");
				id = sc.next();
				service.delete(id);
				break;
				
			// ��ü���
			case 5:
				service.printAll();
				break;
				
			// ����
			case 6:
				flag = false;
				break;
				
			default :
				System.out.println("�߸� �Է�");
			
			}
		}
	}
}
