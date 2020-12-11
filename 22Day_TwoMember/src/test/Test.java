package test;

import java.util.Scanner;

import service.BookService;
import service.CustomerService;
import vo.BookVO;
import vo.Customer;

public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		boolean flag1 = true;
		boolean flag2 = true;
		int menu2 = 0;
		String id;
		String title;
		String author;
		String publisher;
		int price;
		
		String name;
		String addr;
		String tel;
		
		Customer b = null;
		
		CustomerService ser = new CustomerService();
		BookService service = new BookService();

		while (flag1) {
			System.out.println("1.고객 2.책");
			int menu1 = sc.nextInt();
			switch (menu1) {
			// 고객 case
			case 1:
				while (flag2) {
					System.out.println("1.등록 2.검색 3.전체출력 4.삭제, 5.수정 6.종료");
					menu2 = sc.nextInt();
					
					switch (menu2) {
					// 고객 등록
					case 1:
						System.out.println("이름을 입력하세요 : ");
						name = sc.next();
						System.out.println("주소를 입력하세요 : ");
						addr = sc.next();
						System.out.println("전화번호를 입력하세요 : ");
						tel = sc.next();
						
						b = new Customer(name, addr, tel);
						ser.insert(b);
						break;
						
					// 고객 검색
					case 2:
						System.out.println("검색할 이름을 입력하세요 : ");
						name = sc.next();
						
						b = ser.findCustomer(name);
						System.out.println(b);
						break;
						
					// 전체출력
					case 3:
						ser.printAll();
						break;
						
					// 고객 삭제
					case 4:
						System.out.println("삭제할 이름을 입력하세요 : ");
						name = sc.next();
						ser.delete(name);
						break;
						
					// 고객 수정	
					case 5:
						System.out.println("수정할 이름을 입력하세요 : ");
						name = sc.next();
						System.out.println("수정할 주소를 입력하세요 : ");
						addr = sc.next();
						System.out.println("수정할 번호를 입력하세요 : ");
						tel = sc.next();
						
						b = new Customer(name, addr, tel);
						ser.update(b);
						break;
						
					// 메뉴 종료
					case 6:
						flag2 = false;
						break;
					}
				}
				break;
			
			// 책 case
			case 2:
				while (flag2) {
					System.out.println("1.책등록 2.검색 3.수정 4.삭제 5.전체출력 6.종료");
					menu2 = sc.nextInt();
					
					BookVO b1 = null;
					switch (menu2) {
					
					// 책 등록
					case 1:
						System.out.println("책 id를 입력하세요 : ");
						id = sc.next();
						System.out.println("저자를 입력하세요 : ");
						author = sc.next();
						System.out.println("제목을 입력하세요 : ");
						title = sc.next();
						System.out.println("출판사를 입력하세요 : ");
						publisher = sc.next();
						System.out.println("가격을 입력하세요 : ");
						price = sc.nextInt();
						
						b1 = new BookVO(id, author, title, publisher, price);
						
						service.insert(b1);
						break;
					
					// 책 검색
					case 2:
						System.out.println("검색할 책 id를 입력하세요 :");
						id = sc.next();
						
						b1 = service.findbook(id);
						System.out.println(b1);
						break;

					// 책 수정
					case 3:
						System.out.println("수정할 책 id를 입력하세요 : ");
						id = sc.next();
						System.out.println("수정할 저자를 입력하세요 : ");
						author = sc.next();
						System.out.println("수정할 제목을 입력하세요 : ");
						title = sc.next();
						System.out.println("수정할 출판사를 입력하세요 : ");
						publisher = sc.next();
						System.out.println("수정할 가격을 입력하세요 : ");
						price = sc.nextInt();
						
						b1 = new BookVO(id, title, author, publisher, price);
						
						service.updatebook(b1);
						break;

					// 책 삭제
					case 4:
						System.out.println("삭제할 책id를 입력하세요 : ");
						id = sc.next();
						
						service.deletebook(id);
						break;

					// 전체 출력
					case 5:
						service.printAll();
						break;
						
						
					case 6:
						flag = false;
						break;

					}
				}
				break;
			}
		}
	}
}
