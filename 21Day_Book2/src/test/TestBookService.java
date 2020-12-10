package test;

import java.util.Scanner;

import service.BookDAO;
import vo.BookVO;

public class TestBookService {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		String id;
		String title;
		String author;
		String publisher;
		int price;
		
		boolean flag = true;
		
		int menu = 0;
		BookVO b = null;
		BookDAO service = new BookDAO();
		
		while (flag) {
			
			System.out.println("1.등록 2.검색 3.수정 4.삭제 5.종료");
			menu = sc.nextInt();
			
			switch (menu) {
			
			// 1.등록
			case 1:
				System.out.println("id를 입력하세요 : ");
				id = sc.next();
				System.out.println("책이름을 입력하세요 : ");
				title = sc.next();
				System.out.println("저자를 입력하세요 : ");
				author = sc.next();
				System.out.println("출파사를 입력하세요 : ");
				publisher = sc.next();
				System.out.println("가격을 입력하세요 : ");
				price = sc.nextInt();
				
				service.bookinsert(new BookVO(id, title, author, publisher, price));
				break;

			// 2.검색
			case 2:
				System.out.println("검색할 id를 입력하세요 :");
				id = sc.next();
				b = service.bookselect(id);
				System.out.println(b);
				break;
				
			// 3.수정
			case 3:
				System.out.println("수정할 id를 입력하세요 : ");
				id = sc.next();
				System.out.println("수정할 책이름을 입력하세요 : ");
				title = sc.next();
				System.out.println("수정할 저자를 입력하세요 : ");
				author = sc.next();
				System.out.println("수정할 출판사를 입력하세요 : ");
				publisher = sc.next();
				System.out.println("수정할 가격을 입력하세요 : ");
				price = sc.nextInt();
				
				b = new BookVO(id, title, author, publisher, price);
				service.bookupdate(b);
				
				break;
				
			// 4.삭제
			case 4:
				System.out.println("삭제할 id를 입력하세요 : ");
				id = sc.next();
				service.bookdelete(id);
				break;
				
			// 5.종료
			case 5:
				flag = false;
				break;
			
			default:
				System.out.println("잘못 입력");
			
			}
		}
	}
}
