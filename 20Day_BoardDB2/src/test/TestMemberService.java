package test;

import java.util.Scanner;

import service.BoardService;
import vo.Board;

public class TestMemberService {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		String id;
		String title;
		String contents;
		
		boolean flag = true;
		
		int menu = 0;
		
		BoardService service = new BoardService();
		
		while (flag) {
			System.out.println("1.등록 2.검색 3.수정 4.삭제 5.전체출력 6.종료");
			
			menu = sc.nextInt();
			
			switch(menu) {
			
			// 등록
			case 1:
				System.out.println("아이디를 입력하세요 : ");
				id = sc.next();
				System.out.println("제목을 입력하세요 : ");
				title = sc.nextLine(); // 앞에서 입력한 엔터를 지워주기 위해서
				System.out.println("내용을 입력하세요 : ");
				contents = sc.nextLine();
				
				service.insert(new Board(id, title, contents));
				break;
			
			// 검색
			case 2:
				System.out.println("검색할 아이디를 입력하세요 : ");
				id = sc.next();
				Board b = service.find(id);
				System.out.println(b);
				break;
				
			// 수정
			case 3:
				System.out.println("수정할 아이디를 입력하세요 : ");
				id = sc.next();
				System.out.println("수정할 제목을 입력하세요 : ");
				title = sc.next();
				System.out.println("수정할 내용을 입력하세요 : ");
				contents = sc.next();
				
				Board b1 = new Board(id, title, contents);
				service.update(b1);
				break;
				
			// 삭제
			case 4:
				System.out.println("삭제할 아이디를 입력하세요 : ");
				id = sc.next();
				service.delete(id);
				break;
				
			// 전체출력
			case 5:
				service.printAll();
				break;
				
			// 종료
			case 6:
				flag = false;
				break;
				
			default :
				System.out.println("잘못 입력");
			
			}
		}
	}
}
