package test;

import java.util.Scanner;

import service.EventDao;
import vo.Event;

public class DBMain {

	public static void main(String[] args) {

		EventDao d = new EventDao();
		Scanner sc = new Scanner(System.in);
		
		boolean flag = true;
		
		int num;
		String  email;
		
		int i = 0;
		
		while (flag) {
			
			System.out.println("1.등록 2.명단확인 3.검색 4.수정 5.삭제 6.종료");
			i = sc.nextInt();
			
			switch (i) {
			
			case 1:
				d.enroll();
				break;
			case 2:
				System.out.println(d.print());
				break;
				
			case 3:
				System.out.println("검색할 번호를 입력하세요 : ");
				num = sc.nextInt();
				Event p = d.search(num);
				System.out.println(p);
				break;
				
			case 4:
				System.out.println("수정할 번호를 입력하세요 : ");
				num = sc.nextInt();
				System.out.println("Email을 입력하세요 : ");
				email = sc.next();
				Event v = new Event(num, email);
				d.updateEvent(v);
				break;
				
			case 5:
				System.out.println("삭제할 번호를 입력하세요 : ");
				num = sc.nextInt();
				d.delete(num);
				break;
				
			case 6:
				flag = false;
				break;
				
            default:                                                                                                                                                                                     			default :
				System.out.println("잘못 입력");
			}
		}
	}
}
