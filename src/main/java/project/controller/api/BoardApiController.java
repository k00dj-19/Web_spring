package project.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.servlet.http.HttpSession;

import project.config.auth.PrincipalDetail;
import project.dto.ResponseDto;
import project.model.Board;
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
}
