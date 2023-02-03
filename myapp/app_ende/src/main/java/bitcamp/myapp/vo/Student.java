package bitcamp.myapp.vo;

import java.util.Objects;

// 회원 데이터를 담을 메모리를 설계한다.
public class Student extends Member {
  private String postNo;
  private String basicAddress;
  private String detailAddress;
  private boolean working;
  private  char gender;
  private byte level;



  @Override
  public String toString() {
    return "Student [postNo=" + postNo + ", basicAddress=" + basicAddress + ", detailAddress="
        + detailAddress + ", working=" + working + ", gender=" + gender + ", level=" + level + "]";
  }



  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result =
        prime * result + Objects.hash(basicAddress, detailAddress, gender, level, postNo, working);
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
    Student other = (Student) obj;
    return Objects.equals(basicAddress, other.basicAddress)
        && Objects.equals(detailAddress, other.detailAddress) && gender == other.gender
        && level == other.level && Objects.equals(postNo, other.postNo) && working == other.working;
  }



  public String getPostNo() {
    return postNo;
  }
  public void setPostNo(String postNo) {
    this.postNo = postNo;
  }
  public String getBasicAddress() {
    return basicAddress;
  }
  public void setBasicAddress(String basicAddress) {
    this.basicAddress = basicAddress;
  }
  public String getDetailAddress() {
    return detailAddress;
  }
  public void setDetailAddress(String detailAddress) {
    this.detailAddress = detailAddress;
  }
  public boolean isWorking() {
    return working;
  }
  public void setWorking(boolean working) {
    this.working = working;
  }
  public char getGender() {
    return gender;
  }
  public void setGender(char gender) {
    this.gender = gender;
  }
  public byte getLevel() {
    return level;
  }
  public void setLevel(byte level) {
    this.level = level;
  }
}