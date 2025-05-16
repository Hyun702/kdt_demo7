package com.example.bbsdemo.web;


import com.example.bbsdemo.domain.BoardSVC.BoardSVC;
import com.example.bbsdemo.domain.entity.Board;
import com.example.bbsdemo.web.form.Board.DetailForm;
import com.example.bbsdemo.web.form.Board.SaveForm;
import com.example.bbsdemo.web.form.Board.UpdateForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

  @Autowired
  private final BoardSVC boardSVC;

  // 게시판 목록
  @GetMapping
  public String list(Model model){
    List<Board> list = boardSVC.boardAll();
    model.addAttribute("list",list);
    return "board/list";    //    GET http://localhost:9080/boards

  }

  // 게시글 조회 (단건)
  @GetMapping("/{id}")
  public String detail(
      @PathVariable("id") Long id,
      Model model
      ){
    log.info("id={}",id);

    Optional<Board> optionalBoard = boardSVC.findById(id);
    Board findedBoard = optionalBoard.orElseThrow();

    DetailForm detailForm = new DetailForm();
    detailForm.setBoardId(findedBoard.getBoardId());
    detailForm.setTitle(findedBoard.getTitle());
    detailForm.setWriter(findedBoard.getWriter());
    detailForm.setContent(findedBoard.getContent());
    detailForm.setCreatedDate(findedBoard.getCreatedDate());

    model.addAttribute("detailForm",detailForm);

    return "Board/detailBoard";
  }

  //게시글 등록 화면
  @GetMapping("/add")
  public String addForm(Model model){
    model.addAttribute("saveForm",new SaveForm());
    return "Board/add";
  }

  //게시글 등록 처리
  @PostMapping("/add")
  public String addBoard(
      @Valid @ModelAttribute SaveForm saveForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes
      ){
    if(bindingResult.hasErrors()){
      log.info("bindingResult = {}",bindingResult);
      return "Board/add";
    }

    Board board = new Board();
    board.setTitle(saveForm.getTitle());
    board.setWriter(saveForm.getWriter());
    board.setContent(saveForm.getContent());

    long bid = boardSVC.save(board);
    redirectAttributes.addAttribute("id",bid);
    return "redirect:/boards/{id}";

  }

  // 게시글 수정 화면
  @GetMapping("/{id}/edit")      // GET http://localhost:9080/id/edit
  public String updateForm(
      @PathVariable("id") Long id,
      Model model
  ){
    log.info("id={}",id);

    Optional<Board> optionalBoard = boardSVC.findById(id);
    Board findedBoard = optionalBoard.orElseThrow();

    UpdateForm updateForm = new UpdateForm();
    updateForm.setBoardId(findedBoard.getBoardId());
    updateForm.setTitle(findedBoard.getTitle());
    updateForm.setContent(findedBoard.getContent());
    updateForm.setModifiedDate(findedBoard.getModifiedDate());

    model.addAttribute("updateForm",updateForm);
    return "Board/updateBoard";

  }

  //게시글 수정 처리
  @PostMapping("/{id}/edit")        // POST http://localhost:9080/id/edit
  public String updateBoard(
      @PathVariable("id") Long boardId,
      @Valid @ModelAttribute("updateForm") UpdateForm updateForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes,
      Model model
  ){
    if(bindingResult.hasErrors()){
      log.info("bindingResult = {}",bindingResult);
      return "Board/updateBoard";
    }

    Board board = new Board();
    board.setBoardId(updateForm.getBoardId());
    board.setTitle(updateForm.getTitle());
    board.setContent(updateForm.getContent());
    board.setModifiedDate(LocalDateTime.now());

    long bid = boardSVC.updateById(boardId, board);
    redirectAttributes.addAttribute("id",boardId);

    return "redirect:/boards/{id}";      // 302 GET redirectUrl-> http://localhost/boards/id

  }

  //게시글 삭제 처리(단건)
  @GetMapping("/{id}/del")
  public String deleteBoard(
      @PathVariable("id") Long id
  ){

    int rows = boardSVC.DeleteById(id);
    log.info("게시글 {}-건이 삭제됨", rows);

    return "redirect:/boards";
  }

  //게시글 삭제 처리(여러건)
  @PostMapping("/del")
  public String deleteBoards(@RequestParam("boardIds") List<Long> boardIds){

    log.info("BoardIds = {}", boardIds);

    int rows = boardSVC.DeleteByIds(boardIds);

    log.info("게시글 {}-건이 삭제됨", rows);

    return "redirect:/boards";
  }


}
