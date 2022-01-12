package project.controller.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.servlet.http.HttpSession;

import project.config.auth.PrincipalDetail;
import project.dto.ReplySaveRequestDto;
import project.dto.ResponseDto;
import project.model.Board;
import project.model.Reply;
import project.service.BoardService;

@RestController
public class BoardApiController {

  @Autowired
  private BoardService boardService;
  
  @PostMapping("/api/board")
  public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
    boardService.글쓰기(board,principal.getUser());
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
  }
  
  @DeleteMapping("/api/board/{id}")
  public ResponseDto<Integer> deleteById(@PathVariable int id){
    boardService.글삭제하기(id);
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
  }
  
  @PutMapping("/api/board/{id}")
  public ResponseDto<Integer> update(@PathVariable int id,@RequestBody Board board){
    boardService.글수정하기(id,board);
    return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    
  }
  
  // 데이터를 받을 때 컨트롤러에서 dto를 만들어서 받는 게 좋다.
  // dto는 데이터를 한번에 받아서 영속화 시켜서 집어넣는다.
  @PostMapping("/api/board/{boardId}/reply")
  public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto){
    boardService.댓글쓰기(replySaveRequestDto);
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
  }
}
