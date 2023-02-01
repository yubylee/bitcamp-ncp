package com.eomcs.net;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class PreClient {
  public static void main(String[] args) throws Exception{
    Scanner keyScan = new Scanner(System.in);
    System.out.println("클라실행");
    Socket socket = new Socket("192.168.0.15", 8888);
    System.out.println("서버에 연결됨");

    PrintStream out = new PrintStream(socket.getOutputStream());
    Scanner in = new Scanner(socket.getInputStream());

    while(true) {
      System.out.println("입력> ");
      String message = keyScan.nextLine();
      out.println(message);

      if (message.equals("quit")) {
        break;
      }

      String response = in.nextLine();
      System.out.printf("응답: %s\n", response);
    }

    in.close();
    out.close();
    socket.close();

    System.out.println("클라종료");
    keyScan.close();
  }
}
