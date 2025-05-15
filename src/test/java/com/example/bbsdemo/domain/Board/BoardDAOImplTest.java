package com.example.bbsdemo.domain.Board;

import com.example.bbsdemo.domain.entity.Board;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

  @Slf4j
  @SpringBootTest

  class BoardDAOImplTest  {

    @Autowired
    BoardDAO boardDao;


    @Test
    @DisplayName("게시물 작성")
    void save() {
      Board board = new Board();
      board.setTitle("제목5");
      board.setContent("내용을 입력합니다아.");
      board.setWriter("홍길동");
      board.setCreatedDate(LocalDateTime.now());
      board.setModifiedDate(LocalDateTime.now());

      Long bid = boardDao.save(board);
      log.info("게시물 번호 = {}", bid);

    }

    @Test
    @DisplayName("게시판 조회")
    void findAll(){
      List<Board> boards = boardDao.boardAll();
      for (Board board : boards) {
        log.info("게시물= {}",board);
      }
    }

    @Test
    @DisplayName("게시글 조회")
    void findById(){
      Long boardId = 7L;
      Optional<Board> optionalBoard = boardDao.findById(boardId);
      Board findByboard = optionalBoard.orElseThrow();
      log.info("optionalBoard = {}",optionalBoard);
    }

    @Test
    @DisplayName("게시글 수정")
    void updateById(){
      Long boardId = 25L;
      Board board = new Board();
      board.setTitle("홍길동의 행적");
      board.setContent("그는 나타났다 사라졌다");
      int rows = boardDao.updateById(boardId, board);
      log.info("수정된 게시글 수 = {}", rows);


    }

    @Test
    @DisplayName("게시글 삭제")
    void deleteByid(){
      Long boardId = 21L;
      int rows = boardDao.DeleteById(boardId);
      log.info("삭제된 게시글 수 = {}", rows);

    }

    @Test
    @DisplayName("게시글 삭제")
    void deleteByids(){
      List<Long> ids = List.of(25L,23L);
      int rows = boardDao.DeleteByIds(ids);
      log.info("삭제된 게시글 수 = {}", rows);

    }

}