package main;

import java.util.ArrayList;
import java.util.Scanner;

import board.Board;
import join.Member;
import product.Product;

public class Main {
	public static String loginId = " ";
	
	public static void func1(Scanner sc, join.Service service) {
		
		int menu = 0;
		String id = "", pwd = "";
		boolean flag = true, loginFlag = false;
		Member m = null;
		
		while (flag) {
			
			System.out.println("1.회원가입 2.내정보수정 3.로그인 4.로그아웃 5.탈퇴 6.종료");
			menu = sc.nextInt();
			
			switch (menu) {
			
			// 1.회원가입
			case 1:
				m = new Member();
				System.out.println("id:");
				m.setId(sc.next());
				System.out.println("pwd:");
				m.setPwd(sc.next());
				System.out.println("name:");
				m.setName(sc.next());
				System.out.println("email:");
				m.setEmail(sc.next());
				
				service.addMember(m);
				break;
			
			// 2.내정보수정
			case 2:
				if (loginId.equals("") || loginId == null) {
					System.out.println("로그인 먼저 하세요");
				} else {
					m = new Member();
					m.setId(loginId);
					System.out.println("새로운 비밀번호를 입력하세요 : ");
					m.setPwd(sc.next());
					System.out.println("새로운 이메일을 입력하세요 :");
					m.setEmail(sc.next());
					
					service.editMemberInfo(m);
				}
				break;
				
			// 3.로그인
			case 3:
				System.out.println("id");
				id = sc.next();
				System.out.println("pwd");
				pwd = sc.next();
				
				loginFlag = service.login(id, pwd);
				
				if (loginFlag) {
					loginId = id;
					System.out.println(id + " 님 로그인 성공");
				} else {
					System.out.println("로그인 실패");
				}
				break;
				
			// 4.로그아웃
			case 4:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("로그인 먼저 하세요.");
				} else {
					loginId = " ";
					System.out.println("로그아웃 되었습니다.");
				}
				break;
				
			// 5.탈퇴
			case 5:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("로그인 먼저 하세요.");
				} else {
					service.removeMember(loginId);
				}
				break;
				
			// 6.종료
			case 6:
				flag = false;
				break;
			}
		}
	}
	
	// 게시판은 로그인이 되어 있어야 한다
	public static void func2(Scanner sc, board.Service service) {
		
		int menu = 0;
		int num = 0;
		boolean flag = true;
		Board b = null;
		while (flag) {
			
			System.out.println("1.글쓰기 2.글 번호로 검색 3.글 수정 4.글 삭제 5.전체 글 보기 6.종료");
			menu = sc.nextInt();
			switch(menu) {
			
			// 1.글쓰기
			case 1:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("로그인 먼저 하세요.");
				} else {
					b = new Board();
					b.setWriter(loginId);
					System.out.println("글 비밀번호 : ");
					b.setPwd(sc.next());
					System.out.println("제목 :");
					b.setTitle(sc.next());
					System.out.println("내용 :");
					b.setContent(sc.next());
					
					service.write(b);
				}	
				break;
				
			// 2.글 번호로 검색
			case 2:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("로그인 먼저 하세요.");
				} else {
					System.out.println("검색할 글 번호를 입력하세요 : ");
					num = sc.nextInt();
					
					b = service.getArticle(num);
					
					if (b == null) {
						System.out.println("없는 글 번호 입니다.");
					} else {
						System.out.println(b);
					}
				}
				break;
			
			// 3.글 수정
			case 3:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("로그인 먼저 하세요.");
				} else {
					b = new Board();
					System.out.println("수정할 글 번호를 입력하세요 : ");
					num = sc.nextInt();
					b.setNum(num);
					System.out.println("수정할 글 제목을 입력하세요 : ");
					b.setTitle(sc.next());
					System.out.println("수정할 내용을 입력하세요 : ");
					b.setContent(sc.next());
					System.out.println("수정할 비밀번호를 입력하세요 : ");
					b.setPwd(sc.next());
					
					service.editArticle(b);
				}
				break;
				
			// 4.글 삭제
			case 4:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("로그인 먼저 하세요.");
				} else {
					System.out.println("삭제할 글 번호를 입력하세요 : ");
					num = sc.nextInt();
					
					service.delArticle(num);
				}
				break;
			// 5.전체출력
			case 5:
				ArrayList<Board> list = (ArrayList<Board>) service.getArticles();
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
				}
				break;
				
			// 6.종료
			case 6:
				flag = false;
				break;
			}
		}
	}

	// product
	public static void func3(Scanner sc, product.Service service) {
		
		int menu = 0;
		int num = 0;
		String name;
		boolean flag = true;
		Product p = null;
		
		while (flag) {
			System.out.println("1.제품등록 2.제품번호로 검색 3.제품명으로 검색 4.제품수정 5.제품목록 6.제품삭제 7.종료");
			menu = sc.nextInt();
			
			switch(menu) {
			
			// 1.제품등록
			case 1:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("로그인을 하세요.");
				} else {
					p = new Product();
					
					System.out.println("제품 이름을 입력하세요 : ");
					p.setName(sc.next());
					System.out.println("제품 가격을 입력하세요 : ");
					p.setPrice(sc.nextInt());
					System.out.println("제품 수량을 입력하세요 : ");
					p.setQuantity(sc.nextInt());
					p.setId(loginId);
					p.setNum(0);
					
					service.addProduct(p);
				}
				break;
				
			// 2.제품번호로 검색
			case 2:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("로그인을 하세요.");
				} else {
					System.out.println("검색할 번호를 입력하세요 :");
					num = sc.nextInt();
					
					p = service.getByNum(num);
					
					if (p == null) {
						System.out.println("없는 번호입니다.");
					} else {
						System.out.println(p);
					}
				}
				break;
				
			// 3.제품명으로 검색
			case 3:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("로그인을 하세요.");
				} else {
					System.out.println("검색할 제품명을 입력하세요 : ");
					name = sc.next();
					
					p = service.getNyName(name);
					
					if (p == null) {
						System.out.println("없는 번호입니다.");
					} else {
						System.out.println(p);
					}
				}
				break;
				
			// 4.제품수정
			case 4:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("로그인을 하세요.");
				} else {
					p = new Product();
					
					System.out.println("수정할 제품번호를 입력하세요 :");
					p.setNum(sc.nextInt());
					System.out.println("수정할 제품명을 입력하세요 :");
					p.setName(sc.next());
					System.out.println("수정할 가격을 입력하세요 : ");
					p.setPrice(sc.nextInt());
					System.out.println("수정할 수량을 입력하세요 : ");
					p.setQuantity(sc.nextInt());
					System.out.println("수정할 아이디를 입력하세요 : ");
					p.setId(sc.next());
					
					service.editProduct(p);
				}
			// 5.제품목록
			case 5:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("로그인을 하세요.");
				} else {
					ArrayList<Product> list = new ArrayList<Product>();
					
					list = (ArrayList<Product>) service.getAll();
					
					for (int i = 0; i < list.size(); i++) {
						System.out.println(list.get(i));
					}
					break;
				}
			// 6.제품삭제
			case 6:
				if (loginId.equals(" ") || loginId == null) {
					System.out.println("로그인을 하세요.");
				} else {
					System.out.println("삭제할 제품번호를 입력하세요 :");
					num = sc.nextInt();
					
					service.delProduct(num);
					break;
				}
			// 7.종료
			case 7:
				flag = false;
				break;
			}
		}
	}
	
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		join.Service joinService = new join.ServiceImpl();
		board.Service boardService = new board.ServiceImpl();
		product.Service productService = new product.ServiceImpl();
		
		int menu = 0;
		
		boolean flag = true;
		
		while (flag) {
			
			System.out.println("1.회원관리 2.게시판 3.창고 4.종료");
			menu = sc.nextInt();
			
			switch (menu) {
			
			// 1.회원관리 case
			case 1:
				func1(sc, joinService);
				break;
			// 2.게시판 case
			case 2:
				func2(sc, boardService);
				break;
				
			// 3.창고 case
			case 3:
				func3(sc, productService);
				break;
				
			// 4.종료
			case 4: 
				flag = false;
				break;
			}
		}
	}
}
