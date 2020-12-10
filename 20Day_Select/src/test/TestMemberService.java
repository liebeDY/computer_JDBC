package test;

import java.util.Scanner;

import service.MemberService;
import vo.Employee;
import vo.Member;
import vo.Professor;
import vo.Student;

public class TestMemberService {

	public static void main(String[] args) {
		
		MemberService service = new MemberService();
		Scanner sc = new Scanner(System.in);
		
		String id = "";
		String name = "";
		String tel = "";
		String addr = "";
		int type = 0;
		String etc = "";
		
		boolean flag = true;
		
		int menu = 0;
		int j = 0;
		
		Member m = null;
		
		String[] tag = {"", "school", "dept", "job" };
		
		while (flag) {
			System.out.println("1.등록 2.검색 3.수정 4.삭제 5.전체출력 6.종료");
			
			menu = sc.nextInt();
			
			switch(menu) {
			// 1.등록
			case 1:
				System.out.println("그룹선택\n 1.학생 2.교수 3.교직원");
				type = sc.nextInt();
				System.out.println("아이디를 입력하세요");
				id = sc.next();
				System.out.println("이름을 입력하세요");
				name = sc.next();
				System.out.println("전화번호를 입력하세요");
				tel = sc.next();
				System.out.println("주소를 입력하세요");
				addr = sc.next();
				System.out.println(tag[type] + " 을 입력하세요 : ");
				etc = sc.next();
				
				if (type == 1) {
					service.addMember(new Student(id, name, tel, addr, type, etc));
				} else if (type == 2) {
					service.addMember(new Professor(id, name, tel, addr, type, etc));
				} else if (type == 3) {
					service.addMember(new Employee(id, name, tel, addr, type, etc));
				} else {
					System.out.println("입력 오류");
				}
				break;
			// 검색
			case 2:
				System.out.println("검색할 아이디를 입력하세요 : ");
				id = sc.next();
				m = service.getMember(id);
				
				String str = "";
				
				if (m.getType() == 1) {
					str = ((Student) m).toString();
				} else if (m.getType() == 2) {
					str = ((Professor) m).toString();
				} else if (m.getType() == 3) {
					str = ((Employee) m).toString();
				}
				System.out.println(str);
				break;
				
				
			// 수정
			case 3:
				System.out.println("그룹선택\n 1.학생 2.교수 3.교직원");
				type = sc.nextInt();
				
				System.out.println("수정할 사람의 id를 입력하세요 : ");
				id = sc.next();
				
				System.out.println("수정할 전화번호를 입력하세요 : ");
				tel = sc.next();
				
				System.out.println("수정할 주소를 입력하세요 : ");
				addr = sc.next();
				
				
				m = service.getMember(id);
				
				System.out.println("수정할 " + tag[type] +" 를 입력하세요 : ");
				
				etc = sc.next();
				
				if (type == 1) {
					service.editMember(new Student(id, "", tel, addr, type, etc));
				} else if (type == 2) {
					service.editMember(new Professor(id, "", tel, addr, type, etc));
				} else if (type == 3) {
					service.editMember(new Employee(id, "", tel, addr, type, etc));
				}
				break;
				
			// 삭제
			case 4: 
				System.out.println("삭제할 id를 입력하세요 : ");
				id = sc.next();
				
				service.delMember(id);
				break;

			// 전체출력
			case 5:
				System.out.println("그룹선택\n 1.학생 2.교수 3.교직원");
				type = sc.nextInt();
				System.out.println(service.getMembers(type));
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
