package bitcamp.myapp;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int A = scanner.nextInt();
    int B = scanner.nextInt();
    int C = scanner.nextInt();
    scanner.close();
    int max;
    if (A>=B && A>=B) {
      max = A;
    } else if(B>=A && B>=C) {
      max = B;
    } else {
      max = C;
    }


    if(A == B && A == C) {
      System.out.print(10000 + (A * 1000));
    } else if (A != B && A != C && B != C) {
      System.out.print(max);
    } else if()


  }
}