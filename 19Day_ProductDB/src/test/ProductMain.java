package test;

import java.util.ArrayList;
import java.util.Scanner;

import service.ServiceImpl;
import vo.Product;

public class ProductMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		int menu;
		ServiceImpl service = new ServiceImpl();
		Product p = null;
		
		ArrayList<Product> v = null;
		
		while (flag) {
			System.out.println("1.��ǰ��� 2.��ǰ�˻� 3.��ü�˻� 4.���� 5.���� 6.����");
			
			menu = sc.nextInt();
			
			switch(menu) {
			
			case 1:
				service.addProduct();
				
				break;

			// �˻�
			case 2:
				p = service.getProduct();
				System.out.println(p);
				break;
				
			// ��ü���
			case 3:
				v = service.getProducts();
				for (int i = 0; i < v.size(); i++) {
					System.out.println(v.get(i));
				}
				break;
			
			// ����
			case 4:
				service.editProduct();
				break;
				
			// ����
			case 5:
				service.delProdcut();
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
