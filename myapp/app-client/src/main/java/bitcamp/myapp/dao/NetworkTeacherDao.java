package bitcamp.myapp.dao;

import com.google.gson.Gson;
import bitcamp.myapp.vo.Teacher;

public class NetworkTeacherDao implements TeacherDao {

  DaoStub daoStub;

  public NetworkTeacherDao(DaoStub daoStub) {
    this.daoStub = daoStub;
  }

  @Override
  public void insert(Teacher t) {
    daoStub.fetch("board", "insert", t);
  }

  @Override
  public Teacher[] findAll() {
    return new Gson().fromJson(daoStub.fetch("teacher", "findAll"), Teacher[].class);
  }

  @Override
  public Teacher findByNo(int no) {
    return new Gson().fromJson(daoStub.fetch("teacher", "findByNo", no), Teacher.class);
  }

  @Override
  public void update(Teacher t) {
    daoStub.fetch("board", "update", t);
  }

  @Override
  public boolean delete(Teacher t) {
    daoStub.fetch("teacher", "delete", t);
    return true;
  }



}







