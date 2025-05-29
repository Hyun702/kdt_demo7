package com.example.bbsdemo.domain.BoardComment;


import com.example.bbsdemo.domain.entity.BoardComment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CommentDAOImpl implements CommentDAO{

  private final NamedParameterJdbcTemplate template;

  private RowMapper<BoardComment> boardCommentRowMapperAuto(){
    return BeanPropertyRowMapper.newInstance(BoardComment.class);
  }

  private RowMapper<BoardComment> boardCommentRowMapper(){
    return (rs, rowNum) -> {

      BoardComment boardComment = new BoardComment();
      boardComment.setCommentId(rs.getLong("comment_id"));
      boardComment.setContent(rs.getString("content"));
      boardComment.setCommenter(rs.getString("commenter"));

      return boardComment;

    };

  }

  /**
   * 댓글 작성
   * @param boardComment
   * @return 댓글 번호
   */
  @Override
  public Long save(BoardComment boardComment) {
    StringBuffer sql = new StringBuffer();
    sql.append(" INSERT INTO COMMENTS (COMMENT_ID, content, commenter, created_date, updated_date) ");
    sql.append(" VALUES (comments_comment_id_seq.nextval, :content, :commenter, sysdate, sysdate) ");

    SqlParameterSource param = new BeanPropertySqlParameterSource(boardComment);

    KeyHolder keyHolder = new GeneratedKeyHolder();

    int rows = template.update(sql.toString(), param, keyHolder, new String[]{"comment_id"});
    Number commentId = (Number) keyHolder.getKeys().get("comment_id");
    long comid = commentId.longValue();

    return comid;
  }


  /**
   * 댓글 목록
   * @return 댓글 목록
   */
  @Override
  public List<BoardComment> Comments() {
    StringBuffer sql = new StringBuffer();
    sql.append("  SELECT comment_id, content, commenter, created_date, updated_date ");
    sql.append("    FROM comments ");
    sql.append(" ORDER BY comment_id desc ");

    List<BoardComment> list = template.query(sql.toString(), boardCommentRowMapperAuto());

    return list;
  }

  /**
   * 댓글 번호로 조회
   * @param id
   * @return
   */
  @Override
  public Optional<BoardComment> findById(Long id) {
    StringBuffer sql = new StringBuffer();
    sql.append("  SELECT   comment_id, content, commenter, created_date, updated_date ");
    sql.append("    FROM   comments ");
    sql.append("   WHERE   comment_id = :id ");

    SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);

    try{
      BoardComment boardComment = template.queryForObject(sql.toString(), param, boardCommentRowMapperAuto());
      return Optional.of(boardComment);
    }catch(EmptyResultDataAccessException e){
      return Optional.empty();
    }


  }

  /**
   * 댓글 수정
   * @param id
   * @param boardComment
   * @return 수정된 건 수
   */
  @Override
  public int UpdateById(Long id, BoardComment boardComment) {
    StringBuffer sql = new StringBuffer();
    sql.append(" UPDATE  COMMENTS ");
    sql.append("    SET  content = :content, updated_date = sysdate ");
    sql.append("  WHERE  comment_id = :comment_id ");

    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("content", boardComment.getContent())
        .addValue("comment_id", id);

    int rows = template.update(sql.toString(), param);
    return rows;
  }

  /**
   * 댓글 삭제
   * @param id
   * @return 삭제 된 건수
   */
  @Override
  public int DeleteById(Long id) {
    String sql = " DELETE FROM COMMENTS WHERE comment_id = :comment_id ";

    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("comment_id", id);

    int rows = template.update(sql, param);

    return rows;
  }

  /**
   * 게시글 삭제시 댓글 삭제
   * @param BoardId
   * @return
   */
  @Override
  public int DeleteByBoardId(Long BoardId) {
    String sql =" DELETE FROM COMMENTS WHERE Board_id ";

    Map<String, Long> param = Map.of("Board_id",BoardId);
    int rows = template.update(sql, param);

    return rows;
  }


}
