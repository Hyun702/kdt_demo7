package com.example.bbsdemo.domain.Member;

import com.example.bbsdemo.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor

public class MemberDAOImpl implements MemberDAO {

  private final NamedParameterJdbcTemplate template;

  /**
   * 회원 가입
   * @param member 회원정보
   * @return 가입후 정보
   */
  @Override
  public Member insertMember(Member member) {
    // sql 작성
    StringBuffer sql = new StringBuffer();
    sql.append(" insert into member (member_id,email,passwd,nickname) ");
    sql.append(" values(member_member_id_seq.nextval, :email, :passwd, :nickname) ");

    // sql과의 매핑 및 실행
    SqlParameterSource param = new BeanPropertySqlParameterSource(member);

    KeyHolder keyHolder = new GeneratedKeyHolder();
    int rows = template.update(sql.toString(), param, keyHolder, new String[]{"member_id"});

    long memberId = ((Number) keyHolder.getKeys().get("member_id")).longValue();

    return findByMemeberId(memberId).get();
  }

  /**
   * 회원 존재 여부 확인
   * @param email
   * @return 회원이 존재하는지 여부(이메일)
   */
  @Override
  public boolean isExist(String email) {
    //sql 작성
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT count(*) ");
    sql.append(" FROM MEMBER ");
    sql.append(" WHERE email = :email ");


    SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
    Integer cnt = template.queryForObject(sql.toString(), param, Integer.class);


    return (cnt == 1) ? true : false;
  }

  /**
   *
   * @param memberId
   * @return
   */
  @Override
  public Optional<Member> findByMemeberId(Long memberId) {
    StringBuffer sql = new StringBuffer();
    sql.append(" select member_id, ");
    sql.append("        email, ");
    sql.append("        passwd, ");
    sql.append("        nickname, ");
    sql.append("        created_date, ");
    sql.append("        update_date ");
    sql.append("  from  member ");
    sql.append(" where  member_id = :member_id ");

    Map<String, Long> param = Map.of("member_id", memberId);
    try {
      Member member = template.queryForObject(
          sql.toString(), param, BeanPropertyRowMapper.newInstance(Member.class));

      return Optional.of(member);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Member> findByEmail(String email) {
    StringBuffer sql = new StringBuffer();
    sql.append("select  member_id, ");
    sql.append("        email, ");
    sql.append("        passwd, ");
    sql.append("        nickname, ");
    sql.append("        created_date, ");
    sql.append("        update_date ");
    sql.append("  from  member ");
    sql.append(" where  email = :email ");

    Map<String, String> param = Map.of("email", email);
    try {
      Member member = template.queryForObject(
          sql.toString(), param, BeanPropertyRowMapper.newInstance(Member.class));

      return Optional.of(member);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

}
