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
import bitcamp.myapp.vo.Student;
import bitcamp.util.BitcampSqlSessionFactory;
import bitcamp.util.DaoGenerator;

@WebServlet("/student/view")
public class StudentViewServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private StudentDao studentDao;

  private static String getLevelText(int level) {
    switch (level) {
      case 0: return "비전공자";
      case 1: return "준전공자";
      default: return "전공자";
    }
  }

  public StudentViewServlet() {
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

    int studentNo = Integer.parseInt(request.getParameter("no"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>비트캠프 - NCP 1기</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>학생관리</h1>");

    Student s = this.studentDao.findByNo(studentNo);

    if (s == null) {
      out.println("<p>해당 번호의 게시글 없습니다.</p>");

    } else {

      out.println("<form id='student-form' action='update' method='post'>");

      out.println("<table border='1'>");

      out.println("<tr>");
      out.println("  <th>번호</th>");
      out.printf("  <td><input type='text' name='no' value='%d' readonly></td>\n",  s.getNo());
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>이름</th>");
      out.printf("  <td><input type='text' name='name' value='%s'></td>\n",  s.getName());
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>이메일</th>");
      out.printf("  <td><input type='text' name='email' value='%s'></td>\n",  s.getEmail());
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>전화</th>");
      out.printf("  <td><input type='text' name='tel' value='%s'></td>\n",  s.getTel());
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>등록일</th>");
      out.printf("  <td>%s</td>\n",  s.getCreatedDate());
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>우편번호</th>");
      out.printf("  <td><input type='text' name='pst_no' value='%s'></td>\n",  s.getPostNo());
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>기본주소</th>");
      out.printf("  <td><input type='text' name='bas_addr' value='%s'></td>\n",  s.getBasicAddress());
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>상세주소</th>");
      out.printf("  <td><input type='text' name='det_addr' value='%s'></td>\n",  s.getDetailAddress());
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>워크</th>");
      out.printf("  <td><input type='text' name='work' value='%s'></td>\n",  s.isWorking() ? "예" : "아니오");
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>성별</th>");
      out.printf("  <td><input type='text' name='gender' value='%s'></td>\n",  s.getGender() == 'M' ? "남자" : "여자");
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>레벨</th>");
      out.printf("  <td><input type='text' name='level' value='%s'></td>\n",  getLevelText(s.getLevel()));
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>암호</th>");
      out.println("  <td><input type='password' name='pwd'></td>");
      out.println("</tr>");

      out.println("</table>");
    }


    out.println("<div>");
    out.println("  <button id='btn-list' type='button'>목록</button>");
    out.println("  <button>변경</button>");
    out.println("  <button id='btn-delete' type='button'>삭제</button>");
    out.println("</div>");

    out.println("</form>");

    out.println("<script>");
    out.println("document.querySelector('#btn-list').onclick = function() {");
    out.println("  location.href = 'list';");
    out.println("}");
    out.println("document.querySelector('#btn-delete').onclick = function() {");
    out.println("  var form = document.querySelector('#student-form');");
    out.println("  form.action = 'delete';");
    out.println("  form.submit();");
    out.println("}");
    out.println("</script>");

    out.println("</body>");
    out.println("</html>");

  }

}
