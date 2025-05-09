package com.example.bbsdemo.domain.Post.dao;

import com.example.bbsdemo.domain.entity.Post;
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
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Repository

class PostDAOImpl implements PostDAO {

    final private NamedParameterJdbcTemplate template;


    RowMapper<Post> postRowMapper(){

      return (rs, rowNum) -> {
        Post post = new Post();
        post.setPostid(rs.getLong("post_id"));
        post.setTitle(rs.getString("title"));
        post.setText(rs.getString("text"));
        post.setCreater(rs.getString("creater"));
        post.setCreate_date(rs.getTimestamp("create_date").toLocalDateTime());
        post.setUpdate_date(rs.getTimestamp("update_date").toLocalDateTime());
        return post;

      };

    }

  /**
   * 게시물 등록
   * @param post
   * @return 아이디
   */

  @Override
  public Long save(Post post) {
    StringBuffer sql = new StringBuffer();
    sql.append("INSERT INTO POST(post_id,title,text,creater,create_date,update_date)" );
    sql.append(" VALUES (post_seq.nextval,:title,:text,:creater,:create_date,:update_date) ");

    SqlParameterSource param = new BeanPropertySqlParameterSource(post);

    KeyHolder keyHolder = new GeneratedKeyHolder();
    int rows = template.update(sql.toString(), param, keyHolder, new String[]{"post_id"});
    log.info("rows = {}",rows);

    Number pidNumber = (Number)keyHolder.getKeys().get("post_id");
    long pid = pidNumber.longValue();
    return pid;

  }

  /**
   * 게시판 목록
   * @return 게시판 목록
   */
  @Override
  public List<Post> PostAll() {
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT post_id, title, text, creater, create_date, update_date ");
    sql.append("   FROM post ");
    sql.append(" ORDER BY post_id desc ");

    List<Post> list = template.query(sql.toString(), postRowMapper());

    return list;
  }

  @Override
  public Optional<Post> findById(Long id) {
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT post_id, title, text, creater, create_date, update_date");
    sql.append("FROM POST");
    sql.append("WHERE POST_ID = :id");

    SqlParameterSource param = new MapSqlParameterSource("id",id);

    Post post = null;

    try{
      post = template.queryForObject(sql.toString(), param, BeanPropertyRowMapper.newInstance(Post.class));
    }catch (EmptyResultDataAccessException e){
      return Optional.empty();
    }

    return Optional.of(post);


  }
}
