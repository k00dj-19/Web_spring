package project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import project.config.auth.PrincipalDetailService;

// bean 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것.
@Configuration // bean 등록(IoC관리)
@EnableWebSecurity  // 시큐리티 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled = true)  // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private PrincipalDetailService principalDetailService;
  
  @Bean // IoC가 가능해짐.
  public BCryptPasswordEncoder encodePWD() {
    return new BCryptPasswordEncoder();
  }
  
  // 시큐리티가 대신 로그인해줄때 password를 가로채기를 하는데
  // 해당 password가 뭘로 해쉬되어 회원가입이 되었는지 알아야
  // 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
  }
  
  // 해당 메소드가 부모 클래스에 있는 메서드를 Override 했다는 것을 명시적으로 선언
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두는 게 좋음)
      .authorizeRequests()  // request가 들어올때,
        .antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**")  // "/", "/auth/**", "/js/**", "/css/**", "/image/**" 경로로 들어오면,
        .permitAll()  // 누구나 허용.
        .anyRequest()  // 이게 아닌 다른 요청은,
        .authenticated() // 인증이 되어야함.
      .and()
        .formLogin()
        .loginPage("/auth/loginForm")
        .loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다.
        .defaultSuccessUrl("/");  // 정상적으로 요청이 완료되면 / 로 이동.
  }
}
