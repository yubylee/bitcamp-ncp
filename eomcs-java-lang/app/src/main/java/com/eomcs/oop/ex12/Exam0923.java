// List 와 forEach() - forEach() 사용 후
package com.eomcs.oop.ex12;

import java.util.ArrayList;

public class Exam0923 {

  public static void main(String[] args) {
    ArrayList<String> names = new ArrayList<>();
    names.add("홍길동");
    names.add("임꺽정");
    names.add("유관순");
    names.add("김구");
    names.add("안중근");

    names.forEach(System.out::println);

    //    names.forEach(new Consumer<String>() {
    //      public void accept(String t) {
    //        System.out.println(t);
    //      };
    //    });
  }
}