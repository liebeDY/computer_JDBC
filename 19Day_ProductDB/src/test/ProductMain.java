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
			System.out.println("1.제품등록 2.제품검색 3.전체검색 4.수정 5.삭제 6.종료");
			
			menu = sc.nextInt();
			
			switch(menu) {
			
			case 1:
				service.addProduct();
				
				break;

			// 검색
			case 2:
				p = service.getProduct();
				System.out.println(p);
				break;
				
			// 전체출력
			case 3:
				v = service.getProducts();
				for (int i = 0; i < v.size(); i++) {
					System.out.println(v.get(i));
				}
				break;
			
			// 수정
			case 4:
				service.editProduct();
				break;
				
			// 삭제
			case 5:
				service.delProdcut();
				break;
				
			case 6:
				flag = false;
				break;
				
			default:
				System.out.println("잘못 입력");
			}
		}
	}
}
