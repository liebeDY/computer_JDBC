package service;

import java.util.ArrayList;
import java.util.Scanner;

import vo.Product;

public class ServiceImpl implements Service {

	Scanner sc = new Scanner(System.in);
	Dao dao = new OracleDao(); // 객체 생성, 업캐스팅
	
	@Override
	public void addProduct() {
		System.out.println("제품이름:");
		String name = sc.next();
		System.out.println("제품가격:");
		int price = sc.nextInt();
		
		dao.insert(new Product(0, name, price));
	}

	@Override
	public Product getProduct() {
		System.out.println("검색할 제품번호:");
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
		System.out.println("수정할 제품번호:");
		int num = sc.nextInt();
		
		Product p = dao.select(num);
		
		if (p == null) {
			System.out.println("해당 제품이 없다.");
		} else {
			System.out.println(p);
			System.out.println("새 제품이름:");
			String name = sc.next();
			System.out.println("새 제품가격:");
			int price = sc.nextInt();
			
			p.setName(name);
			p.setPrice(price);
			dao.update(p.getNum(), p);
		}
		
	}

	@Override
	public void delProdcut() {
		System.out.println("삭제할 제품번호:");
		int num = sc.nextInt();
		dao.delete(num);
	}
	
	

}
