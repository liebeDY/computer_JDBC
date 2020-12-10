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
			System.out.println("1.등록 2.전체출력 3.검색 4.수정 5.삭제 6.종료");
			i = sc.nextInt();
			
			switch (i) {
			
			case 1:
				System.out.println("제품 번호를 입력하세요 : ");
				p_id = sc.nextInt();
				System.out.println("제품 이름을 입력하세요 : ");
				name = sc.next();
				System.out.println("제품 수량을 입력하세요 : ");
				num = sc.nextInt();
				System.out.println("제조사를 입력하세요 : ");
				co = sc.next();
				System.out.println("제품 가격을 입력하세요 : ");
				price = sc.nextInt();
				
				Product p = new Product(p_id, name, num, co, price);
				pd.insert(p);
				break;
			
			// 전체 출력
			case 2:
				ArrayList<Product> p1 = null;
				for (int j = 0; j < pd.printAll().size(); j++) {
					p1 = pd.printAll();
					System.out.println(p1.get(j));
				}
				
				
//				System.out.println(pd.printAll());
				break;
				
			// 제품 검색	
			case 3:
				System.out.println("검색할 제품 번호를 입력하세요 : ");
				p_id = sc.nextInt();
				Product p2 = pd.selectProduct(p_id);
				System.out.println(p2);
				break;
			
			// 수정	
			case 4:
				System.out.println("제품 번호를 입력하세요 : ");
				p_id = sc.nextInt();
				System.out.println("수정할 제품 이름을 입력하세요 : ");
				name = sc.next();
				System.out.println("수정할 제품 수량을 입력하세요 : ");
				num = sc.nextInt();
				System.out.println("수정할 제조사를 입력하세요 : ");
				co = sc.next();
				System.out.println("수정할 제품 가격을 입력하세요 : ");
				price = sc.nextInt();
				
				Product m2 = new Product(p_id, name, num, co, price);
				pd.update(m2);
				break;
			
			// 삭제
			case 5:
				System.out.println("삭제할 제품 번호를 입력하세요 : ");
				p_id = sc.nextInt();
				pd.delete(p_id);
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
