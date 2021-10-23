package project.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
  private int id;
  
  @Column(nullable = false, length = 100)
  private String title;
  
  @Lob  // 대용량 데이터일때 사용
  private String content;  // 섬머노트 라이브러리 사용. <html>태그가 섞여서 디자인 됨. 데이터 용량이 커짐.
  
  @ColumnDefault("0")
  private int count;  // 조회수
  
  @ManyToOne   // 연관관계. Many = Board, User = One. 한명의 User가 많은 Board 작성 가능.
  @JoinColumn(name="userId")  // 필드 값이 userId로 만들어짐.
  private User user;  // 자바는 오브젝트를 저장할 수 있지만, DB는 오브젝트를 저장할 수 없다. 따라서 foreign key를 사용.
  
  
  @CreationTimestamp
  private Timestamp createDate;
}
