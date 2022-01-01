package project.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder  // 빌더 패턴
// ORM : Java(다른언어) Object를 테이블로 매핑해주는 기술
@Entity // User 클래스 내의 필드로 MySQL 테이블을 자동으로 생성
//@DynamicInsert  // insert할 때 null인 필드를 제외함
public class User {
  
  @Id // Primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
  private int id; // 시퀀스, auto_increment
  
  @Column(nullable = false, length = 100, unique = true)
  private String username; // 아이디
  
  @Column(nullable = false, length = 100) // 123456 => 해쉬를 통해 암호화 하므로 최대 길이를 100으로 넉넉하게 잡는다.
  private String password;
  
  @Column(nullable = false, length = 50)
  private String email;
  
  // @ColumnDefault("user")
  //DB는 RoleType이라는 게 없기 때문에 아래의 어노테이션을 붙여줘서 string임을 알려줘야한다.
  @Enumerated(EnumType.STRING)
  private RoleType role; // Enum을 쓰는 게 좋다. // ADMIN, USER
  
  @CreationTimestamp // 시간이 자동으로 입력됨.
  private Timestamp createDate;
}
