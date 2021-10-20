package project.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import project.test.Member;
// 사용자가 요청 -> 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
  
  private static final String TAG = "HttpControllerTest : ";
  
  @GetMapping("/http/lombok")
  public String lombokTest() {
    Member m = new Member(1, "kdj", "1234", "kdj@naver.com");
    System.out.println(TAG+"getter : "+m.getId());
    m.setId(5000);
    System.out.println(TAG+"getter : "+m.getId());
    return "lombok test 완료";
  }
  
  
  
  // 인터넷 브라우저 요청은 무조건 get 요청밖에 할 수 없다. 따라서 postman사용해서 put,post,delete해보기
  //https://web-spring-ysnkx.run.goorm.io/http/get (select)
  @GetMapping("/http/get")
  //public String getTest(@RequestParam int id, @RequestParam String username) {  // RequestParam을 사용하여 하나씩 받는 방법
  public String getTest(Member m) {  // 생성자를 사용하여 한꺼번에 받는 방법
    return "get 요청: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
  }
  
  //https://web-spring-ysnkx.run.goorm.io/http/post (insert)
  /*
  @PostMapping("/http/post") // raw : text/plain
  public String postTest(@RequestBody String text) {
    return "post 요청: " + text;
  }
  */
  
  /* json
  {
	"id" : 1,
	"username" : "kdj",
	"password" : 123456,
	"email" : "kdj@naver.com"
  }
  */
  @PostMapping("/http/post") //raw : application/json
  public String postTest(@RequestBody Member m) {  // MessageConverter (스프링부트)
    return "post 요청: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
  }
  
  //https://web-spring-ysnkx.run.goorm.io/http/put (update)
  @PutMapping("/http/put")
  public String putTest(@RequestBody Member m) {
    return "put 요청: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
  }
  
  //https://web-spring-ysnkx.run.goorm.io/http/delete (delete)
  @DeleteMapping("/http/delete")
  public String deleteTest() {
    return "delete 요청";
  }
}
