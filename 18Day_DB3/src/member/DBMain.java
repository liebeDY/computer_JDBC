package member;

import java.util.Scanner;

public class DBMain {

	public static void main(String[] args) {

		DB d = new DB();
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		int i = 0;
		
		while (flag) {
			System.out.println("1.등록 2.전체회원 3.검색 4.수정 5.삭제 6.종료");
			
			i = sc.nextInt();
			
			switch (i) {
			
			case 1:
				d.MemberInsert();
				break;
				
			case 2:
				d.MemberPrint();
				break;
				
			case 3:
				System.out.println("검색할 이름을 입력하세요 : ");
				String name = sc.next();
				d.MemberSearch(name);
				break;
				
			case 4:
				d.update();
				break;
				
			case 5:
				d.delete();
				break;
				
			case 6:
				flag = false;
				break;
			
			default :
				System.out.println("잘못 입력");
			}
		}
	}
}
