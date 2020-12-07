package prod;

import java.util.Scanner;

public class ProductMain {

	public static void main(String[] args) {

		Product p = new Product();
		
		Scanner sc = new Scanner(System.in);
		
		boolean flag = true;
		int i = 0;
		
		while (flag) {
			System.out.println("1.제품생산 2.제품출력 3.제품검색 4.주문 5.주문내역 6.종료");
			
			i = sc.nextInt();
			
			switch(i) {
			
			case 1:
				p.ProductInsert();
				break;
			
			case 2:
				p.ProductPrint();
				break;
			// 검색	
			case 3:
				System.out.println("검색할 제품 번호를 입력하세요 : " );
				int productId = sc.nextInt();
				p.Productsearch(productId);
				break;
			// 주문	
			case 4:
				p.order();
				break;
				
			case 5:
				p.print_order();
				break;
				
			case 6:
				flag = false;
				break;
				
			default :
				System.out.println("잘못 입력하셨습니다.");
				
			}
		}
	}
}
