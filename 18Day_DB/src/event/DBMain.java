package event;

import java.util.Scanner;

public class DBMain {

	public static void main(String[] args) {

		DB d = new DB();
		Scanner sc = new Scanner(System.in);
		
		boolean flag = true;
		int i = 0;
		
		while (flag) {
			
			System.out.println("1.등록 2.전체출력 3.검색 4.수정 5.삭제 6.종료");
			
			i = sc.nextInt();
			
			switch (i) {
			
			case 1:
				d.enroll();
				break;
			
			case 2:
				d.print();
				break;
				
			case 3:
				int j;
				System.out.println("검색할 번호를 입력하세요 :");
				j = sc.nextInt();
				d.search(j);
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
			
			default:
				System.out.println("잘못입력");	
					
			}
		}
	}

}
