package project.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

import project.model.User;
import project.repository.UserRepository;
import project.model.RoleType;

import java.util.function.Supplier;

@RestController
public class DummyControllerTest {
  
  @Autowired  // 의존성 주입(DI)
  // @Autowired 안쓰면 DummyControllerTest를 @RestController가 메모리에 로드할 때 userRepository가 null이다. 써주면 같이 메모리에 로드해주어 사용하기만 하면됨.
  private UserRepository userRepository;
  
  // {id} 주소로 파라미터를 전달 받을 수 있음.
  // https://web-spring-ysnkx.run.goorm.io/blog/dummy/user/{id}
  @GetMapping("/dummy/user/{id}")
  public User detail(@PathVariable int id){
    // findByID는 return이 option이다. 잘못된 값을 찾았을 때 DB에서 못 찾아오면 user가 null이 되고, 이걸 리턴하면 프로그램에 문제가 생기기 때문.
    // Optional로 User객체를 감싸서 null인지 아닌지를 판단해서 return해!
    
    // User user = userRepository.findById(id).get();  // null이 리턴될리가 없을 때. 객체를 바로 전달.
    /* User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
      @Override
      public User get() {
        // TODO Auto-generated method stub
        return new User();
      }
    });  // null이면 빈 객체를 만들어서 전달해라
    */
    
    // 람다식
    // User user = userRepository.findById(id).orElseThrow(()->{
    //     return new IllegalArgumentException("Can't find this user. id: " + id);
    // });
    
    
    User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
      @Override
      public IllegalArgumentException get() {
        return new IllegalArgumentException("Can't find this user. id: " + id);
      }
    });
    // 요청 : 웹 브라우저
    // user 객체 = 자바 오브젝트
    // 변환 (웹브라우저가 이해할 수 있는 데이터) -> json
    // 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
    // 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
    // user 오브젝트를 json으로 변환해서 브라우저에게 던져줌. 그래서 json타입의 데이터로 보인다!!
    return user;
  }
  
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
