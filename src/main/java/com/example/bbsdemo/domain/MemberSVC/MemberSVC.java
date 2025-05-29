package com.example.bbsdemo.domain.MemberSVC;

import com.example.bbsdemo.domain.entity.Member;

import java.util.Optional;

public interface MemberSVC {

  // 회원 가임
  Member insertMember(Member member);

  // 회원 존재 유무 확인(이메일)
  boolean isExist(String email);


  // 회원 조회(멤버 아이디)
  Optional<Member> findByMemeberId(Long memberId);
  // 회원 조회(이메일)
  Optional<Member> findByEmail(String email);

}


