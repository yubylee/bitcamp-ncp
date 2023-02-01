
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class CalcServer{

  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);
    System.out.println("서버실행중...");
    ServerSocket serverSocket = new ServerSocket(8888);
    Socket socket = serverSocket.accept();
    System.out.println("클라이언트와 연결");

    Scanner in = new Scanner(socket.getInputStream());
    PrintStream out = new PrintStream(socket.getOutputStream());



    String message = in.nextLine();
    for (int i =0 ; i < message.length(); i++) {
      message.charAt(i);
    }
    out.println(message);



    socket.close();
    serverSocket.close();
    System.out.println("서버종료");
    keyScan.close();
  }
}