package main;

import java.util.ArrayList;
import java.util.Scanner;

import board.Board;
import join.Member;
import product.Product;

public class Main {
	public static String loginId = " ";
	
	public static void func1(Scanner sc, join.Service service) {
		
		int menu = 0;
		String id = "", pwd = "";
		boolean flag = true, loginFlag = false;
		Member m = null;
		
		while (flag) {
			
			System.out.println("1.ȸ������ 2.���������� 3.�α��� 4.�α׾ƿ� 5.Ż�� 6.����");
			menu = sc.nextInt();
			
			switch (menu) {
			
			// 1.ȸ������
			case 1:
				m = new Member();
				System.out.println("id:");
				m.setId(sc.next());
				System.out.println("pwd:");
				m.setPwd(sc.next());
				System.out.println("name:");
				m.setName(sc.next());
				System.out.println("email:");
				m.setEmail(sc.next());
				
				service.addMember(m);
				break;
			
			// 2.����������
			case 2:
				if (loginId.equals("") || loginId == null) {
					System.out.println("�α��� ���� �ϼ���");
				} else {
					m = new Member();
					m.setId(loginId);
					System.out.println("���ο� ��й�ȣ�� �Է��ϼ��� : ");
					m.setPwd(sc.next());
					System.out.println("���ο� �̸����� �Է��ϼ��� :");
					m.setEmail(sc.next());
					
					service.editMemberInfo(m);
				}
				break;
				
			// 3.�α���
			case 3:
				System.out.println("id");
				id = sc.next();
				System.out.println("pwd");
				pwd = sc.next();
				
				loginFlag = service.login(id, pwd);
				
				if (loginFlag) {
					loginId = id;
					System.out.println(id + " �� �α��� ����");
				} else {
					System.out.println("�α��� ����");
				}
				break;
				
			// 4.�α׾ƿ�
			case 4:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("�α��� ���� �ϼ���.");
				} else {
					loginId = " ";
					System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
				}
				break;
				
			// 5.Ż��
			case 5:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("�α��� ���� �ϼ���.");
				} else {
					service.removeMember(loginId);
				}
				break;
				
			// 6.����
			case 6:
				flag = false;
				break;
			}
		}
	}
	
	// �Խ����� �α����� �Ǿ� �־�� �Ѵ�
	public static void func2(Scanner sc, board.Service service) {
		
		int menu = 0;
		int num = 0;
		boolean flag = true;
		Board b = null;
		while (flag) {
			
			System.out.println("1.�۾��� 2.�� ��ȣ�� �˻� 3.�� ���� 4.�� ���� 5.��ü �� ���� 6.����");
			menu = sc.nextInt();
			switch(menu) {
			
			// 1.�۾���
			case 1:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("�α��� ���� �ϼ���.");
				} else {
					b = new Board();
					b.setWriter(loginId);
					System.out.println("�� ��й�ȣ : ");
					b.setPwd(sc.next());
					System.out.println("���� :");
					b.setTitle(sc.next());
					System.out.println("���� :");
					b.setContent(sc.next());
					
					service.write(b);
				}	
				break;
				
			// 2.�� ��ȣ�� �˻�
			case 2:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("�α��� ���� �ϼ���.");
				} else {
					System.out.println("�˻��� �� ��ȣ�� �Է��ϼ��� : ");
					num = sc.nextInt();
					
					b = service.getArticle(num);
					
					if (b == null) {
						System.out.println("���� �� ��ȣ �Դϴ�.");
					} else {
						System.out.println(b);
					}
				}
				break;
			
			// 3.�� ����
			case 3:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("�α��� ���� �ϼ���.");
				} else {
					b = new Board();
					System.out.println("������ �� ��ȣ�� �Է��ϼ��� : ");
					num = sc.nextInt();
					b.setNum(num);
					System.out.println("������ �� ������ �Է��ϼ��� : ");
					b.setTitle(sc.next());
					System.out.println("������ ������ �Է��ϼ��� : ");
					b.setContent(sc.next());
					System.out.println("������ ��й�ȣ�� �Է��ϼ��� : ");
					b.setPwd(sc.next());
					
					service.editArticle(b);
				}
				break;
				
			// 4.�� ����
			case 4:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("�α��� ���� �ϼ���.");
				} else {
					System.out.println("������ �� ��ȣ�� �Է��ϼ��� : ");
					num = sc.nextInt();
					
					service.delArticle(num);
				}
				break;
			// 5.��ü���
			case 5:
				ArrayList<Board> list = (ArrayList<Board>) service.getArticles();
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
				}
				break;
				
			// 6.����
			case 6:
				flag = false;
				break;
			}
		}
	}

	// product
	public static void func3(Scanner sc, product.Service service) {
		
		int menu = 0;
		int num = 0;
		String name;
		boolean flag = true;
		Product p = null;
		
		while (flag) {
			System.out.println("1.��ǰ��� 2.��ǰ��ȣ�� �˻� 3.��ǰ������ �˻� 4.��ǰ���� 5.��ǰ��� 6.��ǰ���� 7.����");
			menu = sc.nextInt();
			
			switch(menu) {
			
			// 1.��ǰ���
			case 1:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("�α����� �ϼ���.");
				} else {
					p = new Product();
					
					System.out.println("��ǰ �̸��� �Է��ϼ��� : ");
					p.setName(sc.next());
					System.out.println("��ǰ ������ �Է��ϼ��� : ");
					p.setPrice(sc.nextInt());
					System.out.println("��ǰ ������ �Է��ϼ��� : ");
					p.setQuantity(sc.nextInt());
					p.setId(loginId);
					p.setNum(0);
					
					service.addProduct(p);
				}
				break;
				
			// 2.��ǰ��ȣ�� �˻�
			case 2:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("�α����� �ϼ���.");
				} else {
					System.out.println("�˻��� ��ȣ�� �Է��ϼ��� :");
					num = sc.nextInt();
					
					p = service.getByNum(num);
					
					if (p == null) {
						System.out.println("���� ��ȣ�Դϴ�.");
					} else {
						System.out.println(p);
					}
				}
				break;
				
			// 3.��ǰ������ �˻�
			case 3:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("�α����� �ϼ���.");
				} else {
					System.out.println("�˻��� ��ǰ���� �Է��ϼ��� : ");
					name = sc.next();
					
					p = service.getNyName(name);
					
					if (p == null) {
						System.out.println("���� ��ȣ�Դϴ�.");
					} else {
						System.out.println(p);
					}
				}
				break;
				
			// 4.��ǰ����
			case 4:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("�α����� �ϼ���.");
				} else {
					p = new Product();
					
					System.out.println("������ ��ǰ��ȣ�� �Է��ϼ��� :");
					p.setNum(sc.nextInt());
					System.out.println("������ ��ǰ���� �Է��ϼ��� :");
					p.setName(sc.next());
					System.out.println("������ ������ �Է��ϼ��� : ");
					p.setPrice(sc.nextInt());
					System.out.println("������ ������ �Է��ϼ��� : ");
					p.setQuantity(sc.nextInt());
					System.out.println("������ ���̵� �Է��ϼ��� : ");
					p.setId(sc.next());
					
					service.editProduct(p);
				}
			// 5.��ǰ���
			case 5:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("�α����� �ϼ���.");
				} else {
					ArrayList<Product> list = new ArrayList<Product>();
					
					list = (ArrayList<Product>) service.getAll();
					
					for (int i = 0; i < list.size(); i++) {
						System.out.println(list.get(i));
					}
					break;
				}
			// 6.��ǰ����
			case 6:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("�α����� �ϼ���.");
				} else {
					System.out.println("������ ��ǰ��ȣ�� �Է��ϼ��� :");
					num = sc.nextInt();
					
					service.delProduct(num);
					break;
				}
			// 7.����
			case 7:
				flag = false;
				break;
			}
		}
	}
	
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		join.Service joinService = new join.ServiceImpl();
		board.Service boardService = new board.ServiceImpl();
		product.Service productService = new product.ServiceImpl();
		
		int menu = 0;
		
		boolean flag = true;
		
		while (flag) {
			
			System.out.println("1.ȸ������ 2.�Խ��� 3.â�� 4.����");
			menu = sc.nextInt();
			
			switch (menu) {
			
			// 1.ȸ������ case
			case 1:
				func1(sc, joinService);
				break;
			// 2.�Խ��� case
			case 2:
				func2(sc, boardService);
				break;
				
			// 3.â�� case
			case 3:
				func3(sc, productService);
				break;
				
			// 4.����
			case 4: 
				flag = false;
				break;
			}
		}
	}
}
