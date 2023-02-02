package bitcamp.myapp.dao;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import bitcamp.myapp.vo.Student;

public class NetworkStudentDao implements StudentDao {

  List<Student> list;
  int lastNo;
  DataInputStream in;
  DataOutputStream out;

  public NetworkStudentDao(DataInputStream in, DataOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public void insert(Student s) {
    fetch("board", "insert", s);
  }

  @Override
  public Student[] findAll() {
    return new Gson().fromJson(fetch("student", "findAll"), Student[].class);
  }

  @Override
  public Student findByNo(int no) {
    return new Gson().fromJson(fetch("student", "findByNo", no), Student.class);
  }

  @Override
  public void update(Student s) {
    fetch("student", "update", s);
  }

  @Override
  public boolean delete(Student s) {
    fetch("student", "delete", s);
    return true;
  }

  public void save(String filename) {
    try (FileWriter out = new FileWriter(filename)) {

      out.write(new Gson().toJson(list));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void load(String filename) {
    if (list.size() > 0) { // 중복 로딩 방지!
      return;
    }

    try (BufferedReader in = new BufferedReader(new FileReader(filename))) {

      list = new Gson().fromJson(in, new TypeToken<List<Student>>() {});

      if (list.size() > 0) {
        lastNo = list.get(list.size() - 1).getNo();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String fetch(String dataName, String action, Object... data) throws DaoException {
    try {
      out.writeUTF(dataName);
      out.writeUTF(action);

      if (data.length > 0) {
        out.writeUTF(new Gson().toJson(data[0]));
      }

      // 응답
      String status = in.readUTF();
      if (status.equals("400")) {
        throw new DaoException("클라이언트 요청 오류!");
      } else if (status.equals("500")) {
        throw new DaoException("서버 실행 오류!");
      }
      return in.readUTF();

    } catch (DaoException e) {
      throw e;
    } catch (Exception e) {
      throw new DaoException("오류 발생!", e);
    }
  }

}







