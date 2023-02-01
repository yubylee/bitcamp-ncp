package com.eomcs.net;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class PreServer {
  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);
    System.out.println("서버 실행 중...");

    ServerSocket serverSocket = new ServerSocket(8888);

    Socket socket = serverSocket.accept();
    System.out.println("클라이언트와 연결됨!");

    Scanner in = new Scanner(socket.getInputStream());
    PrintStream out = new PrintStream(socket.getOutputStream());

    while(true) {
      // 클라이언트가 보낸 문자열 한 줄 읽을 때까지 리턴하지 않는다.
      String message = in.nextLine();
      System.out.println(message);
      if(message.equals("quit")) {
        break;
      }

      //      String[] messageArray = message.split("");
      //      for (String s : messageArray) {
      //        out.println(s);
      //      }

      String[] arr = message.split(" ");

      String result = null;
      int one = Integer.parseInt(arr[0]);
      int two = Integer.parseInt(arr[2]);
      String center = arr[1];
      switch (center) {
        case "+":
          result = Integer.toString(one + two);
          break;
        case "-":
          result = Integer.toString(one - two);
          break;
        case "*":
          result = Integer.toString(one * two);
          break;
        case "/":
          result = Integer.toString(one / two);
          break;
        default:
          result = "error";
      }
      out.println(result);





      //      char[] arr = new char[message.length()];
      //
      //      for (int i = 0; i < arr.length; i++) {
      //        arr[i] = message.charAt(i);
      //      }
      //      String result = null;
      //      int one = arr[0];
      //      String sachic = String.valueOf(arr[1]);
      //      int two = arr[2];
      //      switch (sachic) {
      //        case "+":
      //          result = Integer.toString(one + two);
      //          break;
      //        case "-":
      //          result = Integer.toString(one - two);
      //          break;
      //        case "*":
      //          result = Integer.toString(one * two);
      //          break;
      //        case "/":
      //          result = Integer.toString(one / two);
      //          break;
      //        default:
      //          result = "error";
      //      }
      //      out.println(result);
      //      out.println(arr[0]);
      //      out.println(arr[1]);
      //      out.println(arr[2]);
    }
    socket.close();
    serverSocket.close();
    System.out.println("서버 종료!");
    keyScan.close();
  }
}

