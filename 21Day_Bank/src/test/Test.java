package test;

import java.util.Scanner;

import service.AccountDao;
import vo.AccountVO;


public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String accountNumber;
		String accountName;
		int balance;
		
		boolean flag = true;
		int money = 0;
		int menu = 0;
		AccountVO a = null;
		AccountDao service = new AccountDao();
		
		while (flag) {
			
			System.out.println("1.��� 2.�˻� 3.�Ա� 4.��� 5.�����հ� 6.������� 7.���°˻� 8.����");
			menu = sc.nextInt();
			
			switch (menu) {
			
			// 1.���
			case 1:
				System.out.println("���¹�ȣ�� �Է��ϼ��� : ");
				accountNumber = sc.next();
				System.out.println("�����̸��� �Է��ϼ��� : ");
				accountName = sc.next();
				System.out.println("�ݾ��� �Է��ϼ��� : ");
				balance = sc.nextInt();
				
				service.insertAccount(new AccountVO(accountNumber, accountName, balance));
				break;

			// 2.�˻�
			case 2:
				System.out.println("�˻��� ���¹�ȣ�� �Է��ϼ��� :");
				accountNumber = sc.next();
				a = service.findAccount(accountNumber);
				System.out.println(a);
				break;
				
			// 3.�Ա�
			case 3:
				System.out.println("�Ա��� ���¹�ȣ�� �Է��ϼ��� : ");
				accountNumber = sc.next();
				System.out.println("�Ա��� �ݾ��� �Է��ϼ��� : ");
				money = sc.nextInt();
				
				int result = service.depositMoney(accountNumber, money);
				System.out.println(result + " �� �Ա��߽��ϴ�.");
				break;
				
			// 4.���
			case 4:
				System.out.println("����� ���¹�ȣ�� �Է��ϼ��� : ");
				accountNumber = sc.next();
				System.out.println("����� �ݾ��� �Է��ϼ��� : ");
				money = sc.nextInt();
				
				result = service.withDrawMoney(accountNumber, money);
				System.out.println(result + " �� �Ա��߽��ϴ�.");
				break;
				
			// 5.�����հ�
			case 5:
				System.out.println("�հ踦 ���� �����̸��� �Է��ϼ��� :");
				accountName = sc.next();
				result = service.sumAccount(accountName);
				System.out.println("������ �հ�� " + result);
				break;
				
			// 6.�������
			case 6:
				System.out.println("����� ���� �����̸��� �Է��ϼ��� :");
				accountName = sc.next();
				result = service.avgAccount(accountName);
				System.out.println("������ ����� " + result);
				break;	
				
			// 7.���°˻�
			case 7:
				System.out.println("�˻��� ���¹�ȣ�� �Է��ϼ��� :");
				accountNumber = sc.next();
				a = service.findAccount(accountNumber);
				System.out.println(a);
				break;
				
				
			case 8:
				flag = false;
				break;
			
			default:
				System.out.println("�߸� �Է�");
			
			}
		}
	}
}
