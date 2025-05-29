package com.example.bbsdemo.web;

import com.example.bbsdemo.domain.BoardCommentSVC.CommentSVC;
import com.example.bbsdemo.domain.entity.BoardComment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor

public class CommentController {

  @Autowired
  private final CommentSVC commentSVC;

  //
  @GetMapping
  public String addComment(
      @ModelAttribute BoardComment boardComment){

    Long saved = commentSVC.save(boardComment);
    log.info("저장된 댓글 수 = {}",saved);

    return "redirect:/board/" + boardComment.getCommentId();

  }
}
