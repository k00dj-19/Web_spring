package project.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import project.model.Board;
import project.model.Reply;
import project.repository.BoardRepository;
import project.repository.ReplyRepository;

@RestController
public class ReplyControllerTest {
  
  @Autowired
  private BoardRepository boardRepository;
  
  @Autowired
  private ReplyRepository replyRepository;
  
  // 무한 참조 예시. Board의 replys가 Reply를 들고 있고, Reply는 Board를 또 들고 있어서 계속 참조한다.
  @GetMapping("/test/board/{id}")
  public Board getBoard(@PathVariable int id) {
    // jackson 라이브러리 (오브젝트를 json으로 리턴) => 모델의 getter를 호출
    return boardRepository.findById(id).get();
  }
  
  @GetMapping("/test/reply")
  public List<Reply> getReply() {
    return replyRepository.findAll();
  }
}
