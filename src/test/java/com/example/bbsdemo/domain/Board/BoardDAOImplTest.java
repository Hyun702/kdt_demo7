package com.example.bbsdemo.domain.Board;

import com.example.bbsdemo.domain.entity.Board;
import com.example.bbsdemo.web.form.Board.DetailForm;
import com.example.bbsdemo.web.form.Board.UpdateForm;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

  @Slf4j
  @SpringBootTest

  class BoardDAOImplTest {

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
    void findAll() {
      List<Board> boards = boardDao.boardAll();
      for (Board board : boards) {
        log.info("게시물= {}", board);
      }
    }

    @Test
    @DisplayName("게시글 조회")
    void findById() {
      Long boardId = 7L;
      Optional<Board> optionalBoard = boardDao.findById(boardId);
      Board findByboard = optionalBoard.orElseThrow();
      log.info("findByboard = {}", findByboard);
    }

    @Test
    @DisplayName("게시글 수정")
    void updateById() {
      Long boardId = 13L;
      Board board = new Board();
      board.setTitle("홍길동의 행적");
      board.setContent("그는 나타났다 사라졌다");
      int rows = boardDao.updateById(boardId, board);
      log.info("수정된 게시글 수 = {}", rows);

      Optional<Board> optionalBoard = boardDao.findById(boardId);
      Board modifiedBoard = optionalBoard.orElseThrow();

      Assertions.assertThat(modifiedBoard.getTitle()).isEqualTo("홍길동의 행적");
      Assertions.assertThat(modifiedBoard.getContent()).isEqualTo("그는 나타났다 사라졌다");


    }

    @Test
    @DisplayName("게시글 삭제")
    void deleteByid() {
      Long boardId = 21L;
      int rows = boardDao.DeleteById(boardId);
      log.info("삭제된 게시글 수 = {}", rows);
      Assertions.assertThat(rows).isEqualTo(1);

    }

    @Test
    @DisplayName("게시글 삭제")
    void deleteByids() {
      List<Long> ids = List.of(25L, 23L);
      int rows = boardDao.DeleteByIds(ids);
      log.info("삭제된 게시글 수 = {}", rows);
      Assertions.assertThat(rows).isEqualTo(2);

    }

    @Test
    @DisplayName("ID 값 주입 후 확인")
    void checkSetValues() {
      DetailForm detailForm = new DetailForm();
      detailForm.setBoardId(100L);
      UpdateForm updateForm = new UpdateForm();
      updateForm.setBoardId(200L);
      Board board = new Board();
      board.setBoardId(300L);

      log.info("detailForm.boardId = {}", detailForm.getBoardId()); // 100
      log.info("updateForm.boardId = {}", updateForm.getBoardId()); // 200
      log.info("board.boardId = {}", board.getBoardId());           // 300
    }

    @Test
    @DisplayName("아 욕나오네")
    void save_shouldReturnGeneratedBoardId() {
      // given
      Board board = new Board();
      board.setTitle("테스트 제목");
      board.setWriter("테스터");
      board.setContent("테스트 내용입니다.");

      // when
      Long generatedId = boardDao.save(board);

      // then
      System.out.println("생성된 board_id = " + generatedId);
      assertThat(generatedId).isNotNull();
      assertThat(generatedId).isGreaterThan(0L);
    }
    @Test
    @DisplayName("디테일 폼에 값이 잘 들어오는지 확인")
    void findById_shouldReturnBoardWithCorrectValues() {
      // given
      Board board = new Board();
      board.setTitle("디테일 제목");
      board.setWriter("작성자A");
      board.setContent("디테일 내용");

      Long savedId = boardDao.save(board);

      // when
      Optional<Board> foundBoard = boardDao.findById(savedId);

      // then
      assertThat(foundBoard).isPresent();
      Board result = foundBoard.get();
      assertThat(result.getBoardId()).isEqualTo(savedId);
      assertThat(result.getTitle()).isEqualTo("디테일 제목");
      assertThat(result.getWriter()).isEqualTo("작성자A");
      assertThat(result.getContent()).isEqualTo("디테일 내용");
    }
  }



