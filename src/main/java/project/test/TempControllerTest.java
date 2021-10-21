package project.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // 파일 리턴
public class TempControllerTest {
  
  //https://web-spring-ysnkx.run.goorm.io/blog/temp/home
  @GetMapping("/temp/home")
  public String tempHome() {
    System.out.println("tempHome()");
    // 파일리턴 기본경로 : src/main/resources/static
    // 따라서 기본경로 내의 home.html을 리턴하라는 의미.
    // 리턴명 : /home.html      그냥 home.html로 하면 ../statichome.html이 됨.
    return "/home.html";
  }
  
  // static 폴더는 정적 파일만 브라우저가 인식가능해서 jsp같은 동적파일은 읽을 수 없다. 따라서 경로를 바꾸어야 한다. src/main 내에 webapp/WEB-INF/views 폴더 생성. 그 안에 test.jsp 넣기.
  @GetMapping("/temp/jsp")
  public String tempJsp() {
    // prefix : /WEB-INF/views/
    // suffix : .jsp
    // 풀네임 : /WEB-INF/views/test.jsp
    return "test";
  }
}
