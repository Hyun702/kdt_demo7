package com.example.bbsdemo.domain.BoardComment;

import com.example.bbsdemo.domain.entity.BoardComment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest

class CommentDAOImplTest {

  @Autowired
  CommentDAO commentDAO;

  @Test
  @DisplayName("댓글 저장 및 조회")
  void saveAndFindById() {

    BoardComment boardComment = new BoardComment();
    boardComment.setContent("테스트 댓글임");
    boardComment.setCommenter("테스팅");
    boardComment.setCreatedDate(LocalDateTime.now());

    Long commentId = commentDAO.save(boardComment);

    Optional<BoardComment> found = commentDAO.findById(commentId);
    BoardComment boardComment1 = found.orElseThrow();
    assertThat(boardComment1.getContent()).isEqualTo("테스트 댓글임");

  }

  @Test
  @DisplayName("댓글 전체 조회")
  void findAllComments() {

    List<BoardComment> comments = commentDAO.Comments();
    for (BoardComment comment : comments) {
      log.info("댓글 = {}",comment);
    }
  }

  @Test
  @DisplayName("댓글 수정")
  void updateComment() {
    Long commentId = 28L;

    BoardComment update = new BoardComment();
    update.setContent("수정된 댓글 내용입니다아.");
    update.setUpdatedDate(LocalDateTime.now());
    int result = commentDAO.UpdateById(commentId, update);

    Optional<BoardComment> found = commentDAO.findById(commentId);
    BoardComment modifyComment = found.orElseThrow();
    assertThat(result).isEqualTo(1);
    assertThat(modifyComment.getContent()).isEqualTo("수정된 댓글 내용입니다아.");
    log.info("댓글 수정 확인 = {}",result);

  }

  @Test
  @DisplayName("댓글 삭제")
  void deleteComment() {
    Long commentId = 26L;

    int rows = commentDAO.DeleteById(commentId);

    assertThat(rows).isEqualTo(1);

    log.info("삭제된 댓글 수 = {}",rows);

  }
}