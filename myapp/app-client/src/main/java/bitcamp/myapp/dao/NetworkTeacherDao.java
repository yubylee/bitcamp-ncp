package bitcamp.myapp.dao;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import bitcamp.myapp.vo.Teacher;

public class NetworkTeacherDao implements TeacherDao {

  List<Teacher> list;
  int lastNo;
  DataInputStream in;
  DataOutputStream out;

  public NetworkTeacherDao(DataInputStream in, DataOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public void insert(Teacher t) {
    fetch("board", "insert", t);
  }

  @Override
  public Teacher[] findAll() {
    return new Gson().fromJson(fetch("teacher", "findAll"), Teacher[].class);
  }

  @Override
  public Teacher findByNo(int no) {
    return new Gson().fromJson(fetch("teacher", "findByNo", no), Teacher.class);
  }

  @Override
  public void update(Teacher t) {
    fetch("board", "update", t);
  }

  @Override
  public boolean delete(Teacher t) {
    fetch("teacher", "delete", t);
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

      list = new Gson().fromJson(in, new TypeToken<List<Teacher>>() {});

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







