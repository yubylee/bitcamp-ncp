package com.eomcs.oop.ex02.test;

//# 관련된 기능(메서드)을 묶어 분류하기
//1) 분류 전
//2) 메서드를 클래스로 묶어 분류하기
//3) 클래스 변수 도입
//4) 인스턴스 변수 도입
//5) 인스턴스 메서드 활용
//6) 패키지 멤버 클래스로 분리
//7) 클래스를 역할에 따라 패키지로 분류하기
//
public class CalculatorTest {
  static class Calculator {

    int result = 0;

    static void plus(Calculator obj, int a) {
      obj.result += a;
    }

    static void minus(Calculator obj, int a) {
      obj.result -= a;
    }

    static void multiple(Calculator obj, int a) {
      obj.result *= a;
    }

    static void divide(Calculator obj, int a) {
      obj.result /= a;
    }
  }
  public static void main(String[] args) {

    Calculator c1 = new Calculator();

    Calculator.plus(c1,2);
    Calculator.plus(c1,3);
    Calculator.minus(c1,1);
    Calculator.multiple(c1,7);
    Calculator.divide(c1,3);

    System.out.printf("result = %d\n", c1.result);
  }


}