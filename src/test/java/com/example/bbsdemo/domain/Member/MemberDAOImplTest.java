package com.example.bbsdemo.domain.Member;

import com.example.bbsdemo.domain.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
//@Transactional // 테스트 후 자동 롤백
class MemberDAOImplTest {

  @Autowired
  MemberDAO memberDAO;

  @Test
  @DisplayName("회원가입 후 회원 정보가 DB에 저장되는지 확인")
  void insertMemberTest() {
    // given
    Member member = new Member();
    member.setEmail("test@example.com");
    member.setPasswd("1234");
    member.setNickname("테스터");

    // when
    Member saved = memberDAO.insertMember(member);

    // then
    assertNotNull(saved.getMemberId());
    assertEquals("test@example.com", saved.getEmail());
    assertEquals("테스터", saved.getNickname());

    // 확인용 출력
    System.out.println("저장된 회원 ID: " + saved.getMemberId());
  }

  @Test
  @DisplayName("이메일로 회원 존재 여부 확인")
  void isExistTest() {
    // given
    Member member = new Member();
    member.setEmail("exist@example.com");
    member.setPasswd("1234");
    member.setNickname("존재자");
    memberDAO.insertMember(member);

    // when
    boolean exists = memberDAO.isExist("exist@example.com");

    // then
    assertTrue(exists);
  }

  @Test
  @DisplayName("회원 ID로 조회")
  void findByMemberIdTest() {
    // given
    Member member = new Member();
    member.setEmail("search@example.com");
    member.setPasswd("abcd");
    member.setNickname("찾기맨");
    Member saved = memberDAO.insertMember(member);

    // when
    Optional<Member> result = memberDAO.findByMemeberId(saved.getMemberId());

    // then
    assertTrue(result.isPresent());
    assertEquals("search@example.com", result.get().getEmail());

  }

  @Test
  @DisplayName("없는 이메일로 조회 시 Optional.empty 반환")
  void findByWrongEmail() {
    Optional<Member> result = memberDAO.findByEmail("notfound@example.com");
    assertFalse(result.isPresent());
  }
}
