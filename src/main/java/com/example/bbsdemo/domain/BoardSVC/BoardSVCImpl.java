package com.example.bbsdemo.domain.BoardSVC;


import com.example.bbsdemo.domain.Board.BoardDAO;
import com.example.bbsdemo.domain.entity.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardSVCImpl implements BoardSVC {

  private final BoardDAO boardDAO;

  @Override
  public Long save(Board board) {
    return boardDAO.save(board);
  }

  @Override
  public List<Board> boardAll() {
    return boardDAO.boardAll();
  }

  @Override
  public Optional<Board> findById(Long id) {
    return boardDAO.findById(id);
  }

  @Override
  public int updateById(Long boardId, Board board) {
    return boardDAO.updateById(boardId, board);
  }

  @Override
  public int DeleteById(Long id) {
    return boardDAO.DeleteById(id);
  }

  @Override
  public int DeleteByIds(List<Long> ids) {
    return boardDAO.DeleteByIds(ids);
  }
}
