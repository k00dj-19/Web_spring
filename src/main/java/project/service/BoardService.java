package project.service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import project.model.Board;
import project.model.User;
import project.repository.BoardRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class BoardService {
 
  @Autowired
  private BoardRepository boardRepository;
  
  @Transactional
  public void 글쓰기(Board board, User user) { // title, content
    board.setCount(0);
    board.setUser(user);
    boardRepository.save(board);
  }
  
  public Page<Board> 글목록(Pageable pageable){
    return boardRepository.findAll(pageable);
  }
  
  public Board 글상세보기(int id){
    return boardRepository.findById(id)
      .orElseThrow(()->{
        return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
      });
  }
}