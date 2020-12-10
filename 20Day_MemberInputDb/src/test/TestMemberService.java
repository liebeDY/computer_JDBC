package test;

import java.util.ArrayList;
import java.util.Scanner;

import service.MemberService;
import vo.MemberVO;

public class TestMemberService {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		String id;
		String name;
		String tel;
		String addr;
		
		boolean flag = true;
		
		int menu = 0;
		
		MemberService service = new MemberService();
		
		while (flag) {
			System.out.println("1.��� 2.�˻� 3.���� 4.���� 5.��ü��� 6.����");
			
			menu = sc.nextInt();
			
			switch(menu) {
			
			// ���
			case 1:
				System.out.println("���̵� �Է� : ");
				id = sc.next(); 
				System.out.println("�̸� �Է� :");
				name = sc.next();
				System.out.println("��ȣ �Է� :");
				tel = sc.next();
				System.out.println("�ּ� �Է� :");
				addr = sc.next();
				
				service.addMemberVO(new MemberVO(id, name, tel, addr));
				break;
				
			// �˻�
			case 2:
				System.out.println("�˻��� ���̵� �Է��ϼ��� : ");
				id = sc.next();
				MemberVO m = service.findMemberVO(id);
				System.out.println(m);		
				
				break;
				
			// ����
			case 3:
				System.out.println("������ ���̵� �Է��ϼ��� : ");
				id = sc.next();
				System.out.println("������ �̸��� �Է��ϼ��� : ");
				name = sc.next();
				System.out.println("������ ��ȭ��ȣ�� �Է��ϼ��� : ");
				tel = sc.next();
				System.out.println("������ �ּҸ� �Է��ϼ��� : ");
				addr = sc.next();
				
				MemberVO m2 = new MemberVO(id,name, tel, addr);
				service.updateMemberVO(m2);
				break;
				
			// ����
			case 4:
				System.out.println("������ ���̵� �Է��ϼ��� :");
				id = sc.next();
				service.deleteMemberVO(id);
				break;
				
			// ��ü���
			case 5:
				ArrayList<MemberVO> list = null;
				list = service.printAll();
				
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
				}
//				service.printAll();
				break;
				
			// ����
			case 6:
				flag = false;
				break;
				
			default:
				System.out.println("�߸��Է�");
			
			}
		}
	}
}
