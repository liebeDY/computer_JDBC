package test;

import java.util.Scanner;

import service.MemberService;
import vo.Employee;
import vo.Member;
import vo.Professor;
import vo.Student;

public class TestMemberService {

	public static void main(String[] args) {
		
		MemberService service = new MemberService();
		Scanner sc = new Scanner(System.in);
		
		String id = "";
		String name = "";
		String tel = "";
		String addr = "";
		int type = 0;
		String etc = "";
		
		boolean flag = true;
		
		int menu = 0;
		int j = 0;
		
		Member m = null;
		
		String[] tag = {"", "school", "dept", "job" };
		
		while (flag) {
			System.out.println("1.��� 2.�˻� 3.���� 4.���� 5.��ü��� 6.����");
			
			menu = sc.nextInt();
			
			switch(menu) {
			// 1.���
			case 1:
				System.out.println("�׷켱��\n 1.�л� 2.���� 3.������");
				type = sc.nextInt();
				System.out.println("���̵� �Է��ϼ���");
				id = sc.next();
				System.out.println("�̸��� �Է��ϼ���");
				name = sc.next();
				System.out.println("��ȭ��ȣ�� �Է��ϼ���");
				tel = sc.next();
				System.out.println("�ּҸ� �Է��ϼ���");
				addr = sc.next();
				System.out.println(tag[type] + " �� �Է��ϼ��� : ");
				etc = sc.next();
				
				if (type == 1) {
					service.addMember(new Student(id, name, tel, addr, type, etc));
				} else if (type == 2) {
					service.addMember(new Professor(id, name, tel, addr, type, etc));
				} else if (type == 3) {
					service.addMember(new Employee(id, name, tel, addr, type, etc));
				} else {
					System.out.println("�Է� ����");
				}
				break;
			// �˻�
			case 2:
				System.out.println("�˻��� ���̵� �Է��ϼ��� : ");
				id = sc.next();
				m = service.getMember(id);
				
				String str = "";
				
				if (m.getType() == 1) {
					str = ((Student) m).toString();
				} else if (m.getType() == 2) {
					str = ((Professor) m).toString();
				} else if (m.getType() == 3) {
					str = ((Employee) m).toString();
				}
				System.out.println(str);
				break;
				
				
			// ����
			case 3:
				System.out.println("�׷켱��\n 1.�л� 2.���� 3.������");
				type = sc.nextInt();
				
				System.out.println("������ ����� id�� �Է��ϼ��� : ");
				id = sc.next();
				
				System.out.println("������ ��ȭ��ȣ�� �Է��ϼ��� : ");
				tel = sc.next();
				
				System.out.println("������ �ּҸ� �Է��ϼ��� : ");
				addr = sc.next();
				
				
				m = service.getMember(id);
				
				System.out.println("������ " + tag[type] +" �� �Է��ϼ��� : ");
				
				etc = sc.next();
				
				if (type == 1) {
					service.editMember(new Student(id, "", tel, addr, type, etc));
				} else if (type == 2) {
					service.editMember(new Professor(id, "", tel, addr, type, etc));
				} else if (type == 3) {
					service.editMember(new Employee(id, "", tel, addr, type, etc));
				}
				break;
				
			// ����
			case 4: 
				System.out.println("������ id�� �Է��ϼ��� : ");
				id = sc.next();
				
				service.delMember(id);
				break;

			// ��ü���
			case 5:
				System.out.println("�׷켱��\n 1.�л� 2.���� 3.������");
				type = sc.nextInt();
				System.out.println(service.getMembers(type));
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
