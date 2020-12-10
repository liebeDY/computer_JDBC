package service;

import java.util.ArrayList;
import java.util.Scanner;

import vo.Product;

public class ServiceImpl implements Service {

	Scanner sc = new Scanner(System.in);
	Dao dao = new OracleDao(); // ��ü ����, ��ĳ����
	
	@Override
	public void addProduct() {
		System.out.println("��ǰ�̸�:");
		String name = sc.next();
		System.out.println("��ǰ����:");
		int price = sc.nextInt();
		
		dao.insert(new Product(0, name, price));
	}

	@Override
	public Product getProduct() {
		System.out.println("�˻��� ��ǰ��ȣ:");
		int num = sc.nextInt();
		Product p = dao.select(num);
		
		return p;
	}

	@Override
	public ArrayList<Product> getProducts() {

		return dao.selectAll();
	}

	@Override
	public void editProduct() {
		System.out.println("������ ��ǰ��ȣ:");
		int num = sc.nextInt();
		
		Product p = dao.select(num);
		
		if (p == null) {
			System.out.println("�ش� ��ǰ�� ����.");
		} else {
			System.out.println(p);
			System.out.println("�� ��ǰ�̸�:");
			String name = sc.next();
			System.out.println("�� ��ǰ����:");
			int price = sc.nextInt();
			
			p.setName(name);
			p.setPrice(price);
			dao.update(p.getNum(), p);
		}
		
	}

	@Override
	public void delProdcut() {
		System.out.println("������ ��ǰ��ȣ:");
		int num = sc.nextInt();
		dao.delete(num);
	}
	
	

}
