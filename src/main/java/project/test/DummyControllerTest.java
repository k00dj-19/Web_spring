package project.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import project.model.User;
import project.repository.UserRepository;
import project.model.RoleType;

@RestController
public class DummyControllerTest {
  
  @Autowired  // 의존성 주입(DI)
  // @Autowired 안쓰면 DummyControllerTest를 @RestController가 메모리에 로드할 때 userRepository가 null이다. 써주면 같이 메모리에 로드해주어 사용하기만 하면됨.
  private UserRepository userRepository;
  
  // https://web-spring-ysnkx.run.goorm.io/blog/dummy/join (요청)
  // http의 body에 username, password, email을 요청하면 join의 파라미터에 변수명이 같을 경우 자동으로 매핑된다.
  @PostMapping("/dummy/join")
  //public String join(String username, String password, String email){  // id, role, createDate는 자동으로 생성되므로 제외
  public String join(User user) {  // 객체로도 받을 수 있다!
    System.out.println("id: " + user.getId());
    System.out.println("username: " + user.getUsername());
    System.out.println("password: " + user.getPassword());
    System.out.println("email: " + user.getEmail());
    System.out.println("role: " + user.getRole());
    System.out.println("createDate: " + user.getCreateDate());
    
    user.setRole(RoleType.USER);
    userRepository.save(user);  // DB에 가져온 객체 정보를 자동으로 저장
    return "회원가입이 완료되었습니다.";
  }
}
