package com.example.bbsdemo.domain.BoardSVC;

import com.example.bbsdemo.domain.Board.BoardDAO;
import com.example.bbsdemo.domain.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardSVC {


  Long save(Board board);

  List<Board> boardAll();

  Optional<Board> findById(Long id);

  int updateById(Long boardId, Board board);

  int DeleteById(Long id);

  int DeleteByIds(List<Long> ids);
}
