package project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

// bean 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것.
@Configuration // bean 등록(IoC관리)
@EnableWebSecurity  // 시큐리티 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled = true)  // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  // 해당 메소드가 부모 클래스에 있는 메서드를 Override 했다는 것을 명시적으로 선언
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()  // request가 들어올때,
        .antMatchers("/auth/**")  // /auth/** 경로로 들어오면,
        .permitAll()  // 누구나 허용.
        .anyRequest()  // 이게 아닌 다른 요청은,
        .authenticated() // 인증이 되어야함.
      .and()
        .formLogin()
        .loginPage("/auth/loginForm");
  }
}
