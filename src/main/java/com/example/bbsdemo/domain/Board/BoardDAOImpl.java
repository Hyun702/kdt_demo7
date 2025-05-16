package com.example.bbsdemo.domain.Board;


import com.example.bbsdemo.domain.entity.Board;
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

@Slf4j
@Repository
@RequiredArgsConstructor

public class BoardDAOImpl implements BoardDAO {

  private final NamedParameterJdbcTemplate template;

  RowMapper<Board> rowMapperAuto(){
    return BeanPropertyRowMapper.newInstance(Board.class);
  }

  RowMapper<Board> rowMapper() {
    return (rs, rowNum) -> {
      Board board = new Board();
      board.setBoardId(rs.getLong("board_id"));
      board.setTitle(rs.getString("title"));
      board.setContent(rs.getString("content"));
      board.setWriter(rs.getString("writer"));


      return board;

    };
  }

  /**
   * 게시물 등록
   * @param board
   * @return 등록된 게시물 아이디
   */
  @Override
  public Long save(Board board) {
    StringBuffer sql = new StringBuffer();
    sql.append("INSERT INTO board (board_id, title, writer, content, created_date, modified_date)");
    sql.append("VALUES (board_seq.nextval, :title, :writer, :content, sysdate, sysdate)");

    SqlParameterSource param = new BeanPropertySqlParameterSource(board);

    KeyHolder keyHolder = new GeneratedKeyHolder();

    int rows = template.update(sql.toString(), param, keyHolder, new String[]{"board_id"});
    Number bidNumber = (Number)keyHolder.getKeys().get("board_id");
    long bid = bidNumber.longValue();

    return bid;
  }

  /**
   * 게시물 목록
   * @return 게시물 목록
   */
  @Override
  public List<Board> boardAll() {
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT * ");
    sql.append(" FROM board ");
    sql.append(" ORDER BY board_id DESC ");

    List<Board> list = template.query(sql.toString(), rowMapperAuto());

    return list;
  }


  /**
   * 게시글 개별 조회
   * @param id
   * @return 게시글 개별 조회
   */
  @Override
  public Optional<Board> findById(Long id) {
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT  title, writer, content, created_date ");
    sql.append(" FROM    board ");
    sql.append(" WHERE   board_id = :id ");

    SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);

    try{
      Board board = template.queryForObject(sql.toString(), param, rowMapperAuto());
      return Optional.of(board);
    }catch(EmptyResultDataAccessException e){
      return Optional.empty();
    }


  }

  /**
   * 게시글 수정
   * @param boardId
   * @param board
   * @return 수정된 글의 갯수
   */
  @Override
  public int updateById(Long boardId, Board board) {
    StringBuffer sql = new StringBuffer();
    sql.append(" UPDATE BOARD ");
    sql.append(" SET   title = :title, content = :content, modified_date = sysdate ");
    sql.append(" WHERE board_id = :board_id ");

    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("title", board.getTitle())
        .addValue("content", board.getContent())
        .addValue("board_id",boardId);

    int rows = template.update(sql.toString(), param);

    return rows;

  }

  /**
   * 게시글 삭제
   * @param id
   * @return 삭제된 글의 갯수
   */
  @Override
  public int DeleteById(Long id) {
    StringBuffer sql = new StringBuffer();
    sql.append("DELETE  ");
    sql.append("FROM BOARD ");
    sql.append("WHERE board_id = :id ");

    SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);

    int rows = template.update(sql.toString(), param);

    return rows;
  }

  /**
   * 게시글 다중 삭제
   * @param ids
   * @return 삭제된 글의 갯수
   */
  @Override
  public int DeleteByIds(List<Long> ids) {
    StringBuffer sql = new StringBuffer();
    sql.append(" DELETE  ");
    sql.append(" FROM BOARD ");
    sql.append(" WHERE board_id IN(:ids) ");

    Map<String, List<Long>> param = Map.of("ids",ids);
    int rows = template.update(sql.toString(), param);

    return rows;
  }
}
