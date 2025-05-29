package com.example.bbsdemo.domain.Member;

import com.example.bbsdemo.domain.entity.Member;

import java.util.Optional;

public interface MemberDAO {

  /**
   * 회원 가입
   * @param member 회원정보
   * @return 가입후 정보
   */
  Member insertMember(Member member);


  /**
   * 회원 유무
   * @param email
   * @return
   */
  boolean isExist(String email);

  /**
   * 회원 조회
   * @param memberId
   * @return 회원정보
   */

  Optional<Member> findByMemeberId(Long memberId);

  /**
   * 회원 조회
   * @param email
   * @return 회원 정보
   */

  Optional<Member> findByEmail(String email);
}
