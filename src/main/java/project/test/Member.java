package project.test;

import lombok.Data;
//import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
@Data  // Data = Getter + Setter
@AllArgsConstructor  // 모든 필드를 사용하는 생성자 생성, 요즘엔 RequiredArgsConstructor을 많이 사용.
//@RequiredArgsConstructor  // 변수 앞에 final이 붙은 필드에 대한 생성자 생성
@NoArgsConstructor  // 빈 생성자
public class Member {
  private int id;
  private String username;
  private String password;
  private String email; 
  
  /*
  public Member(int id, String username, String password, String email) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
  }
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  */
}

