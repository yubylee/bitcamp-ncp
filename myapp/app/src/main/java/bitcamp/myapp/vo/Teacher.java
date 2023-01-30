package bitcamp.myapp.vo;

import java.util.Objects;

public class Teacher extends Member {
  private String email;
  private int degree;
  private String school;
  private String major;
  private int wage;







  @Override
  public String toString() {
    return "Teacher [email=" + email + ", degree=" + degree + ", school=" + school + ", major="
        + major + ", wage=" + wage + "]";
  }



  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + Objects.hash(degree, email, major, school, wage);
    return result;
  }



  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    Teacher other = (Teacher) obj;
    return degree == other.degree && Objects.equals(email, other.email)
        && Objects.equals(major, other.major) && Objects.equals(school, other.school)
        && wage == other.wage;
  }



  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public int getDegree() {
    return degree;
  }
  public void setDegree(int degree) {
    this.degree = degree;
  }
  public String getSchool() {
    return school;
  }
  public void setSchool(String school) {
    this.school = school;
  }
  public String getMajor() {
    return major;
  }
  public void setMajor(String major) {
    this.major = major;
  }
  public int getWage() {
    return wage;
  }
  public void setWage(int wage) {
    this.wage = wage;
  }

}
