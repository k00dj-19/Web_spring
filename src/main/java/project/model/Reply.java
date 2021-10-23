package project.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Reply {
  @Id  // Primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
  private int id; // 시퀀스, auto_increment
  
  @Column(nullable = false, length = 200)
  private String content;
  
  // 어느 게시글의 댓글인지 연관관계 필요.
  @ManyToOne  // 여러개의 댓글이 하나의 게시글에 있을 수 있다.
  @JoinColumn(name="boardId")
  private Board board;
  
  // 댓글을 누가 적었는지
  @ManyToOne  // 여러개의 답변을 한 유저가 쓸 수 있다.
  @JoinColumn(name="userId")
  private User user;
  
  @CreationTimestamp
  private Timestamp createDate;
}
