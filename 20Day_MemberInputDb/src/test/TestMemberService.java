package test;

import java.util.ArrayList;
import java.util.Scanner;

import service.MemberService;
import vo.MemberVO;

public class TestMemberService {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		String id;
		String name;
		String tel;
		String addr;
		
		boolean flag = true;
		
		int menu = 0;
		
		MemberService service = new MemberService();
		
		while (flag) {
			System.out.println("1.등록 2.검색 3.수정 4.삭제 5.전체출력 6.종료");
			
			menu = sc.nextInt();
			
			switch(menu) {
			
			// 등록
			case 1:
				System.out.println("아이디 입력 : ");
				id = sc.next(); 
				System.out.println("이름 입력 :");
				name = sc.next();
				System.out.println("번호 입력 :");
				tel = sc.next();
				System.out.println("주소 입력 :");
				addr = sc.next();
				
				service.addMemberVO(new MemberVO(id, name, tel, addr));
				break;
				
			// 검색
			case 2:
				System.out.println("검색할 아이디를 입력하세요 : ");
				id = sc.next();
				MemberVO m = service.findMemberVO(id);
				System.out.println(m);		
				
				break;
				
			// 수정
			case 3:
				System.out.println("수정할 아이디를 입력하세요 : ");
				id = sc.next();
				System.out.println("수정할 이름을 입력하세요 : ");
				name = sc.next();
				System.out.println("수정할 전화번호를 입력하세요 : ");
				tel = sc.next();
				System.out.println("수정할 주소를 입력하세요 : ");
				addr = sc.next();
				
				MemberVO m2 = new MemberVO(id,name, tel, addr);
				service.updateMemberVO(m2);
				break;
				
			// 삭제
			case 4:
				System.out.println("삭제할 아이디를 입력하세요 :");
				id = sc.next();
				service.deleteMemberVO(id);
				break;
				
			// 전체출력
			case 5:
				ArrayList<MemberVO> list = null;
				list = service.printAll();
				
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
				}
//				service.printAll();
				break;
				
			// 종료
			case 6:
				flag = false;
				break;
				
			default:
				System.out.println("잘못입력");
			
			}
		}
	}
}
