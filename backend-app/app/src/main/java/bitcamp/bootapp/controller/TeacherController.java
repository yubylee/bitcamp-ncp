package bitcamp.bootapp.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import bitcamp.bootapp.dao.TeacherDao;
import bitcamp.bootapp.vo.Teacher;

@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
@RestController
public class TeacherController {

  TeacherDao teacherDao = new TeacherDao();

  @PostMapping("/teachers")
  public Object addTeacher(
      String name,
      String tel,
      String mail,
      String education,
      String university,
      String major,
      int fee)
  {


    Teacher t = new Teacher();
    t.setName(name);
    t.setTel(tel);
    t.setMail(mail);
    t.setEducation(education);
    t.setUniversity(university);
    t.setMajor(major);
    t.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    t.setFee(fee);

    this.teacherDao.insert(t);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");

    return contentMap;
  }


  @GetMapping("/teachers")
  public Object getTeachers() {

    Teacher[] teachers = this.teacherDao.findAll();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", teachers);

    return contentMap;
  }


  @GetMapping("/teachers/{teacherNo}")
  public Object getTeacher(@PathVariable int teacherNo) {

    Teacher t = this.teacherDao.findByNo(teacherNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();

    if (t == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "강사가 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("data", t);
    }

    return contentMap;
  }

  @PutMapping("/teachers/{no}")
  public Object updateTeacher(
      //@Pathvariable int memberNo, // Teacher
      Teacher teacher) {

    Map<String,Object> contentMap = new HashMap<>();

    Teacher old = this.teacherDao.findByNo(teacher.getNo());
    if (old == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "강사가 없습니다.");
      return contentMap;
    }

    teacher.setCreatedDate(old.getCreatedDate());

    this.teacherDao.update(teacher);

    contentMap.put("status", "success");

    return contentMap;
  }

  @DeleteMapping("/teachers/{teacherNo}")
  public Object deleteTeacher(
      // 낱개로 받을 때는 @PathVariable 애노테이션을 생략하면 안된다.
      @PathVariable int teacherNo) {

    Teacher t = this.teacherDao.findByNo(teacherNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();

    if (t == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "회원이 없습니다.");

    } else {
      this.teacherDao.delete(t);
      contentMap.put("status", "success");
    }

    return contentMap;
  }
}
