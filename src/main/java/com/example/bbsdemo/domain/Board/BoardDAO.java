package com.example.bbsdemo.domain.Board;

import com.example.bbsdemo.domain.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardDAO {

  // 게시물 등록
  Long save (Board board);

  // 게시판 목록
  List<Board> boardAll();

  // 게시글 조회
  Optional<Board> findById(Long id);

  // 게시글 수정
  int updateById(Long boardId, Board board);

  // 게시글 삭제
  int DeleteById(Long id);

  // 게시글 다중 삭제
  int DeleteByIds(List<Long> ids);

}
