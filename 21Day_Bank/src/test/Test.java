package test;

import java.util.Scanner;

import service.AccountDao;
import vo.AccountVO;


public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String accountNumber;
		String accountName;
		int balance;
		
		boolean flag = true;
		int money = 0;
		int menu = 0;
		AccountVO a = null;
		AccountDao service = new AccountDao();
		
		while (flag) {
			
			System.out.println("1.등록 2.검색 3.입금 4.출금 5.계좌합계 6.계좌평균 7.계좌검색 8.종료");
			menu = sc.nextInt();
			
			switch (menu) {
			
			// 1.등록
			case 1:
				System.out.println("계좌번호를 입력하세요 : ");
				accountNumber = sc.next();
				System.out.println("계좌이름을 입력하세요 : ");
				accountName = sc.next();
				System.out.println("금액을 입력하세요 : ");
				balance = sc.nextInt();
				
				service.insertAccount(new AccountVO(accountNumber, accountName, balance));
				break;

			// 2.검색
			case 2:
				System.out.println("검색할 계좌번호를 입력하세요 :");
				accountNumber = sc.next();
				a = service.findAccount(accountNumber);
				System.out.println(a);
				break;
				
			// 3.입금
			case 3:
				System.out.println("입금할 계좌번호를 입력하세요 : ");
				accountNumber = sc.next();
				System.out.println("입금할 금액을 입력하세요 : ");
				money = sc.nextInt();
				
				int result = service.depositMoney(accountNumber, money);
				System.out.println(result + " 를 입금했습니다.");
				break;
				
			// 4.출금
			case 4:
				System.out.println("출금할 계좌번호를 입력하세요 : ");
				accountNumber = sc.next();
				System.out.println("출금할 금액을 입력하세요 : ");
				money = sc.nextInt();
				
				result = service.withDrawMoney(accountNumber, money);
				System.out.println(result + " 를 입금했습니다.");
				break;
				
			// 5.계좌합계
			case 5:
				System.out.println("합계를 구할 계좌이름을 입력하세요 :");
				accountName = sc.next();
				result = service.sumAccount(accountName);
				System.out.println("계좌의 합계는 " + result);
				break;
				
			// 6.계좌평균
			case 6:
				System.out.println("평균을 구할 계좌이름을 입력하세요 :");
				accountName = sc.next();
				result = service.avgAccount(accountName);
				System.out.println("계좌의 평균은 " + result);
				break;	
				
			// 7.계좌검색
			case 7:
				System.out.println("검색할 계좌번호를 입력하세요 :");
				accountNumber = sc.next();
				a = service.findAccount(accountNumber);
				System.out.println(a);
				break;
				
				
			case 8:
				flag = false;
				break;
			
			default:
				System.out.println("잘못 입력");
			
			}
		}
	}
}
