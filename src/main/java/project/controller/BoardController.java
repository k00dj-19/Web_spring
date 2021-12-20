package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import project.config.auth.PrincipalDetail;
import project.service.BoardService;

@Controller
public class BoardController {
  
  @Autowired
  private BoardService boardService;
  
  // 컨트롤러에서 세션을 어떻게 찾는지?
  //@AuthenticationPrincipal PrincipalDetail principal
  @GetMapping({"","/"})
  public String index(Model model) {
    model.addAttribute("boards", boardService.글목록());
    return "index";
  }
  
  @GetMapping("/board/{id}")
  public String findById(@PathVariable int id, Model model){
    model.addAttribute("board", boardService.글상세보기(id));
    return "board/detail";
  }
  
  // USER 권한이 필요
  @GetMapping("/board/saveForm")
  public String saveForm() {
    return "board/saveForm";
  }
}
