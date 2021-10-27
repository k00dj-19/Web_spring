package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.model.User;

// DAO(Data Access Object) : 실질적으로 DB에 접근하는 객체.
// 자동으로 bean 등록이 된다. 따라서 @Repository 생략 가능.
public interface UserRepository extends JpaRepository<User, Integer>{  // 해당 JpaRepository는 User테이블이 관리한다. primary key는 integer다. JpaRepository를 상속했으므로 모든 함수 사용 가능.
}
