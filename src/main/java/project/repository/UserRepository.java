package project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.model.User;

// DAO(Data Access Object) : 실질적으로 DB에 접근하는 객체.
// 자동으로 bean 등록이 된다. 따라서 @Repository 생략 가능.
public interface UserRepository extends JpaRepository<User, Integer>{  // 해당 JpaRepository는 User테이블이 관리한다. primary key는 integer다. JpaRepository를 상속했으므로 모든 함수 사용 가능.
  
  // SELECT * FROM user WHERE username = ?1;
  // Optional : null이 올 수 있는 값을 감싸는 Wrapper클래스. NullPointerException이 발생하지 않도록 도와줌.
  Optional<User> findByUsername(String username);
}
  

  // JPA Naming 쿼리 : 함수 명에 따라 자동으로 아래의 쿼리생성.
  // SELECT * FROM user WHERE username = ?1 AND password = ?2;
  // 물음표 각각에 지역변수들이 들어감. ?1 : username, ?2 : password
  // User findByUsernameAndPassword(String username, String password);
  
  
  // 위와 동일한 함수
  // @Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2;", nativeQuery = true)
  // User findByUsernameAndPassword(String username, String password);