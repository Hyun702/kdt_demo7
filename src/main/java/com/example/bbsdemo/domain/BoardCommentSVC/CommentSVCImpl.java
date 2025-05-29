package com.example.bbsdemo.domain.BoardCommentSVC;

import com.example.bbsdemo.domain.BoardComment.CommentDAO;
import com.example.bbsdemo.domain.entity.BoardComment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor

public class CommentSVCImpl implements CommentSVC{

  private final CommentDAO commentDAO;


  @Override
  public Long save(BoardComment boardComment) {
    return commentDAO.save(boardComment);
  }

  @Override
  public List<BoardComment> Comments() {
    return commentDAO.Comments();
  }

  @Override
  public Optional<BoardComment> findById(Long id) {
    return Optional.empty();
  }

  @Override
  public int UpdateById(Long id, BoardComment boardComment) {
    return 0;
  }

  @Override
  public int DeleteById(Long id, BoardComment boardComment) {
    return 0;
  }

  @Override
  public int DeleteByBoardId(Long BoardId) {
    return 0;
  }
}
