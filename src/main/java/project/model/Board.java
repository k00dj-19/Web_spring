package project.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;

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


  
  
  private int count;  // 조회수
  
  @ManyToOne(fetch = FetchType.EAGER)   // 연관관계. Many = Board, User = One. 한명의 User가 많은 Board 작성 가능. 기본 fatch 전략이 EAGER. select하면 바로바로 가져옴.
  @JoinColumn(name="userId")  // 필드 값이 userId로 만들어짐.
  private User user;  // 자바는 오브젝트를 저장할 수 있지만, DB는 오브젝트를 저장할 수 없다. 따라서 foreign key를 사용.
  
  @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy가 적혀있으면, 연관관계의 주인이 아니라는 뜻. -> 난 FK가 아니에요. DB에 column을 만들지 마세요.
  // One = Board, Many = reply. 기본 fatch 전략이 LAZY. 개수가 많으니까 필요하면 들고오고, 안필요하면 안들고 올게. 그러나 여기선 게시판 상세보기하면 댓글을 바로 가져와야 하니까 fatch전략을 EAGER로 변경.
  private List<Reply> reply;  // Board를 select할 때, join문을 통해 값을 얻기 위한 것.
  
  @CreationTimestamp
  private Timestamp createDate;
}
