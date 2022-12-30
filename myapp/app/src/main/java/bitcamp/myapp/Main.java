package bitcamp.myapp;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int  A, B;
    A = scanner.nextInt();
    B = scanner.nextInt();

    while(B > 0) {
      sum += B%10;
      B /= 10;
    }
    System.out.println(B);

  }
}