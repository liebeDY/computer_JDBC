package test;

import java.util.ArrayList;
import java.util.Scanner;

import service.ProductDao;
import vo.Product;

public class TestProductService {

	public static void main(String[] args) {

		ProductDao pd = new ProductDao();
		
		Scanner sc = new Scanner(System.in);
		
		int p_id;
		String name;
		int num;
		String co;
		int price;
		
		boolean flag = true;
		int i = 0;
		
		while (flag) {
			System.out.println("1.��� 2.��ü��� 3.�˻� 4.���� 5.���� 6.����");
			i = sc.nextInt();
			
			switch (i) {
			
			case 1:
				System.out.println("��ǰ ��ȣ�� �Է��ϼ��� : ");
				p_id = sc.nextInt();
				System.out.println("��ǰ �̸��� �Է��ϼ��� : ");
				name = sc.next();
				System.out.println("��ǰ ������ �Է��ϼ��� : ");
				num = sc.nextInt();
				System.out.println("�����縦 �Է��ϼ��� : ");
				co = sc.next();
				System.out.println("��ǰ ������ �Է��ϼ��� : ");
				price = sc.nextInt();
				
				Product p = new Product(p_id, name, num, co, price);
				pd.insert(p);
				break;
			
			// ��ü ���
			case 2:
				ArrayList<Product> p1 = null;
				for (int j = 0; j < pd.printAll().size(); j++) {
					p1 = pd.printAll();
					System.out.println(p1.get(j));
				}
				
				
//				System.out.println(pd.printAll());
				break;
				
			// ��ǰ �˻�	
			case 3:
				System.out.println("�˻��� ��ǰ ��ȣ�� �Է��ϼ��� : ");
				p_id = sc.nextInt();
				Product p2 = pd.selectProduct(p_id);
				System.out.println(p2);
				break;
			
			// ����	
			case 4:
				System.out.println("��ǰ ��ȣ�� �Է��ϼ��� : ");
				p_id = sc.nextInt();
				System.out.println("������ ��ǰ �̸��� �Է��ϼ��� : ");
				name = sc.next();
				System.out.println("������ ��ǰ ������ �Է��ϼ��� : ");
				num = sc.nextInt();
				System.out.println("������ �����縦 �Է��ϼ��� : ");
				co = sc.next();
				System.out.println("������ ��ǰ ������ �Է��ϼ��� : ");
				price = sc.nextInt();
				
				Product m2 = new Product(p_id, name, num, co, price);
				pd.update(m2);
				break;
			
			// ����
			case 5:
				System.out.println("������ ��ǰ ��ȣ�� �Է��ϼ��� : ");
				p_id = sc.nextInt();
				pd.delete(p_id);
				break;
				
			case 6:
				flag = false;
				break;
			
			default:
				System.out.println("�߸� �Է�");
			
			}	
		}
	}
}
