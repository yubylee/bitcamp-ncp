package bitcamp.myapp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.TeacherDao;
import bitcamp.myapp.service.TeacherService;
import bitcamp.myapp.vo.Teacher;

@Service
public class DefaultTeacherService implements TeacherService {

  @Autowired private MemberDao memberDao;
  @Autowired private TeacherDao teacherDao;

  @Override
  public void add(Teacher teacher) {
    memberDao.insert(teacher);
    teacherDao.insert(teacher);
  }

  @Override
  public List<Teacher> list() {
    return teacherDao.findAll();
  }

  @Override
  public Teacher get(int no) {
    return teacherDao.findByNo(no);
  }

  @Override
  public Teacher get(String email, String password) {
    Map<String,Object> paramMap = new HashMap<>();
    paramMap.put("email", email);
    paramMap.put("password", password);

    return teacherDao.findByEmailAndPassword(paramMap);
  }

  @Override
  public void update(Teacher teacher) {
    if (memberDao.update(teacher) == 1 &&
        teacherDao.update(teacher) == 1) {
    } else {
      throw new RuntimeException("강사가 존재하지 않습니다.");
    }
  }

  @Override
  public void delete(int no) {
    if (teacherDao.delete(no) == 1 &&
        memberDao.delete(no) == 1) {
    } else {
      throw new RuntimeException("강사가 존재하지 않습니다.");
    }
  }
}





