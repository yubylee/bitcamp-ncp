package bitcamp.myapp.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.dao.TeacherDao;
import bitcamp.myapp.vo.Teacher;

public class TeacherDaoImpl implements TeacherDao {

  Connection con;

  public TeacherDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Teacher t) {
    try (Statement stmt = con.createStatement()) {

      String sql = String.format(
          "insert into app_teacher("
              + " member_id, "
              + " degree, "
              + " school, "
              + " major, "
              + " wage)"
              + " values('%s',%d,'%s','%s',%d)",
              t.getNo(),
              t.getDegree(),
              t.getSchool(),
              t.getMajor(),
              t.getWage());

      stmt.executeUpdate(sql);

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public List<Teacher> findAll() {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select m.member_id, "
                + " m.name,"
                + " m.email,"
                + " m.tel,"
                + " t.degree,"
                + " t.major,"
                + " t.wage"
                + " from app_teacher t"
                + " inner join app_member m on t.member_id = m.member_id"
                + " order by "
                + " m.name asc")) {

      ArrayList<Teacher> list = new ArrayList<>();
      while (rs.next()) {
        Teacher t = new Teacher();
        t.setNo(rs.getInt("member_id"));
        t.setName(rs.getString("name"));
        t.setEmail(rs.getString("email"));
        t.setTel(rs.getString("tel"));
        t.setDegree(rs.getInt("degree"));
        t.setMajor(rs.getString("major"));
        t.setWage(rs.getInt("wage"));

        list.add(t);
      }

      return list;

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public Teacher findByNo(int no) {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select "
            + " m.member_id,"
            + " m.name,"
            + " m.email,"
            + " m.tel,"
            + " m.created_date,"
            + " t.degree,"
            + " t.school,"
            + " t.major,"
            + " t.wage"
            + " from app_teacher t"
            + " inner join app_member m on t.member_id = m.member_id"
            + " where t.member_id=" + no)) {

      if (rs.next()) {
        Teacher t = new Teacher();
        t.setNo(rs.getInt("member_id"));
        t.setName(rs.getString("name"));
        t.setEmail(rs.getString("email"));
        t.setTel(rs.getString("tel"));
        t.setCreatedDate(rs.getDate("created_date"));
        t.setEmail(rs.getString("email"));
        t.setDegree(rs.getInt("degree"));
        t.setSchool(rs.getString("school"));
        t.setMajor(rs.getString("major"));
        t.setWage(rs.getInt("wage"));
        return t;
      }

      return null;

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public int update(Teacher t) {
    try (Statement stmt = con.createStatement()) {

      String sql = String.format(
          "update app_teacher set "
              + " degree=%d,"
              + " school='%s',"
              + " major='%s',"
              + " wage=%d "
              + " where member_id=%d",
              t.getDegree(),
              t.getSchool(),
              t.getMajor(),
              t.getWage(),
              t.getNo());

      return stmt.executeUpdate(sql);

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (Statement stmt = con.createStatement()) {

      String sql = String.format("delete from app_teacher"
          + " where member_id=%d", no);
      return stmt.executeUpdate(sql);

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }
}























