package com.example.bbsdemo.domain.BoardCommentSVC;

import com.example.bbsdemo.domain.entity.BoardComment;

import java.util.List;
import java.util.Optional;

public interface CommentSVC {

  // 댓글 작성

  Long save(BoardComment boardComment);

  // 댓글 목록

  List<BoardComment> Comments();

  // 댓글 번호로 조회

  Optional<BoardComment> findById(Long id);

  // 댓글 수정

  int UpdateById(Long id, BoardComment boardComment);

  // 댓글 삭제

  int DeleteById(Long id, BoardComment boardComment);

  // 게시글 삭제시 댓글 삭제

  int DeleteByBoardId(Long BoardId);

}
