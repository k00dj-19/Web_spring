package project.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import project.model.User;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면,
// UserDetails 타입의 오브젝트를 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
@Getter
public class PrincipalDetail implements UserDetails{
  private User user;  // 콤포지션 : 객체를 품고 있는 형태
  
  public PrincipalDetail(User user) {
    this.user = user;
  }
  
  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }
  
  // 계정이 만료되지 않았는 지 리턴한다. (true: 만료안됨)
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }
  
  // 계정이 잠겼는지 확인. (true : 안 잠긴거)
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }
  
  // 비밀번호가 만료되지 않았는 지 리턴한다. (true: 만료안됨)
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  
  // 계정이 활성화(사용가능)인 지 리턴 (true : 활성화)
  @Override
  public boolean isEnabled() {
    return true;
  }
  
  // 계정이 갖고있는 권한 목록을 리턴한다. (권한이 여러개 있을 수 있어서 루프를 돌아야 하는데 우리는 한개만)
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> collectors = new ArrayList<>();
    /*
    자바는 파라미터로 함수를 넣을 수 없다! 어차피 GrantedAuthority에 함수가 getAuthority 뿐이므로, 람다식을 사용하자.
    collectors.add(new GrantedAuthority() {
      
      @Override
      public String getAuthority() {
        // role 받을 때 앞에 ROLE_ 을 꼭 넣어줘야 인식한다.
        return "ROLE_"+user.getRole();  // ROLE_USER
      }
    });
    */
    collectors.add(()->{return "ROLE_"+user.getRole();});
    return collectors;
  }
}
