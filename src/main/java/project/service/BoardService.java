package project.service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import project.dto.ReplySaveRequestDto;
import project.model.Board;
import project.model.Reply;
import project.model.User;
import project.repository.BoardRepository;
import project.repository.ReplyRepository;
import project.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class BoardService {
 
  @Autowired
  private BoardRepository boardRepository;
  
  @Autowired
  private ReplyRepository replyRepository;
  
  @Autowired
  private UserRepository userRepository;
  
  @Transactional
  public void 글쓰기(Board board, User user) { // title, content
    board.setCount(0);
    board.setUser(user);
    boardRepository.save(board);
  }
  
  @Transactional(readOnly = true)
  public Page<Board> 글목록(Pageable pageable){
    return boardRepository.findAll(pageable);
  }
  
  @Transactional(readOnly = true)  
  public Board 글상세보기(int id){
    return boardRepository.findById(id)
      .orElseThrow(()->{
        return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
      });
  }
  
  @Transactional
  public void 글삭제하기(int id){
    boardRepository.deleteById(id);
  }
  
  @Transactional
  public void 글수정하기(int id, Board requestBoard){
    Board board = boardRepository.findById(id)
      .orElseThrow(()->{
        return new IllegalArgumentException("글 수정 실패 : 아이디를 찾을 수 없습니다.");
      });// 영속화 완료
    board.setTitle(requestBoard.getTitle());
    board.setContent(requestBoard.getContent());
    // 해당 함수 종료시(Service가 종료될 때) 트랜잭션이 종료됨. 이때 더티체킹이 일어나 DB에 자동업데이트가 됨.
  }
  
	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {
		int result = replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
		System.out.println("BoardService : "+result);
	}
  
  @Transactional
  public void 댓글삭제(int replyId) {
    replyRepository.deleteById(replyId);
  }
}
