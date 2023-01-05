package bitcamp.myapp;

import java.sql.Date;

public class MemberHandler {

  static final int SIZE = 100;
  static int count = 0;

  // 레퍼런스 배열 준비
  static Member[] members = new Member[SIZE];

  static void inputMembers() {

    Member m = new Member();
    m.no = Prompt.inputInt("번호? ");
    m.name = Prompt.inputString("이름? ");
    m.tel = Prompt.inputString("전화? ");
    m.postNo = Prompt.inputString("우편번호? ");
    m.basicAddress = Prompt.inputString("주소1? ");
    m.detailAddress = Prompt.inputString("주소2? ");
    m.working = Prompt.inputInt("0. 미취업\n1. 재직중\n재직자? ") == 1;
    m.gender = Prompt.inputInt("0. 남자\n1. 여자\n성별? ") == 0 ? 'M' : 'W';
    m.level = (byte) Prompt.inputInt("0. 비전공자\n1. 준전공자\n2. 전공자\n전공? ");
    m.createdDate = new Date(System.currentTimeMillis()).toString();

    // 지금 금방 만든 객체에 사용자가 입력한 값을 저장한 후
    // 그 객체의 주소를 잃어버리지 않게 레퍼런스 배열에 보관해 둔다.
    members[count++] = m;

  }



  static void printMembers() {
    for (int i = 0; i < count; i++) {
      Member m = members[i];
      System.out.printf("번호: %d\n", m.no);
      System.out.printf("이름: %s\n", m.name);
      System.out.printf("전화: %s\n", m.tel);
      System.out.printf("우편번호: %s\n", m.postNo);
      System.out.printf("주소1: %s\n", m.basicAddress);
      System.out.printf("주소2: %s\n", m.detailAddress);
      System.out.printf("재직자: %s\n", m.working ? "예" : "아니오");
      System.out.printf("성별: %s\n", m.gender == 'M' ? "남자" : "여자");

      String levelTitle;
      switch (m.level) {
        case 0: levelTitle = "비전공자"; break;
        case 1: levelTitle = "준전공자"; break;
        default: levelTitle = "전공자";
      }
      System.out.printf("전공: %s\n", levelTitle);

      System.out.printf("가입일: %s\n", m.createdDate);

      System.out.println("---------------------------------------");
    }
  }



  static void service() {
    while (true) {
      System.out.println("[회원관리]");
      System.out.println("1. 등록");
      System.out.println("2. 목록");
      System.out.println("3. 조회");
      System.out.println("4. 변경");
      System.out.println("5. 삭제");
      System.out.println("0. 이전");
      int menuNo2 = Prompt.inputInt("메뉴> ");
  
      if (menuNo2 == 0) {
        break;
      } else if (menuNo2 == 1) {
        inputMembers();
      } else if (menuNo2 == 2) {
        printMembers();
      } else if (menuNo2 >= 3 && menuNo2 <= 5){
        System.out.println("작업실행!");
      } else {
        System.out.println("잘못된 번호");
      }
  
    }
  }



  static void goMainMenu() {
    while (true) {
      System.out.println("1. 회원관리");
      System.out.println("9. 종료");
      int menuNo = Prompt.inputInt("메뉴> ");
  
      if (menuNo == 1) {
        service();
      } else if (menuNo == 9) {
        System.out.println("안녕~~");
        break;
      } else {
        System.out.println("잘못된 번호");
      }
    }
  }
}
