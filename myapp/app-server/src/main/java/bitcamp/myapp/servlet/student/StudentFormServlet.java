package bitcamp.myapp.servlet.student;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import bitcamp.myapp.dao.StudentDao;
import bitcamp.util.BitcampSqlSessionFactory;
import bitcamp.util.DaoGenerator;

@WebServlet("/student/form")
public class StudentFormServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private StudentDao studentDao;

  public StudentFormServlet() {
    try {
      InputStream mybatisConfigInputStream = Resources.getResourceAsStream(
          "bitcamp/myapp/config/mybatis-config.xml");
      SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
      BitcampSqlSessionFactory sqlSessionFactory = new BitcampSqlSessionFactory(
          builder.build(mybatisConfigInputStream));
      studentDao = new DaoGenerator(sqlSessionFactory).getObject(StudentDao.class);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>비트캠프 - NCP 1기</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강사관리</h1>");
    out.println("<form action='insert' method='post'>");
    out.println("<table border='1'>");

    out.println("<tr>");
    out.println("  <th>이름</th>");
    out.println("  <td><input type='text' name='name'></td>");
    out.println("</tr>");

    out.println("<tr>");
    out.println("  <th>이메일</th>");
    out.println("  <td><input type='text' name='email'></td>");
    out.println("</tr>");

    out.println("<tr>");
    out.println("  <th>전화</th>");
    out.println("  <td><input type='text' name='tel'></td>");
    out.println("</tr>");

    out.println("<tr>");
    out.println("  <th>우편번호</th>");
    out.println("  <td><input type='text' name='pst_no'></td>");
    out.println("</tr>");

    out.println("<tr>");
    out.println("  <th>기본주소</th>");
    out.println("  <td><input type='text' name='bas_addr'></td>");
    out.println("</tr>");

    out.println("<tr>");
    out.println("  <th>상세주소</th>");
    out.println("  <td><input type='text' name='det_addr'></td>");
    out.println("</tr>");

    out.println("<tr>");
    out.println("  <th>워크</th>");
    out.println("  <td><input type='text' name='work'></td>");
    out.println("</tr>");

    out.println("<tr>");
    out.println("  <th>성별</th>");
    out.println("  <td><input type='text' name='gender''></td>");
    out.println("</tr>");

    out.println("<tr>");
    out.println("  <th>레벨</th>");
    out.println("  <td><input type='text' name='level'></td>\n");
    out.println("</tr>");

    out.println("<tr>");
    out.println("  <th>암호</th>");
    out.println("  <td><input type='password' name='pwd'></td>");
    out.println("</tr>");

    out.println("<div>");
    out.println("  <button>등록</button>");
    out.println("  <button id='btn-cancel' type='button'>취소</button>");
    out.println("</div>");
    out.println("</form>");

    out.println("<script>");
    out.println("document.querySelector('#btn-cancel').onclick = function() {");
    out.println("  location.href = 'list';");
    out.println("}");
    out.println("</script>");
    out.println("</body>");
    out.println("</html>");

  }
}
