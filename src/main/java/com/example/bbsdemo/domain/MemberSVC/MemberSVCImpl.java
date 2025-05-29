package com.example.bbsdemo.domain.MemberSVC;

import com.example.bbsdemo.domain.Member.MemberDAO;
import com.example.bbsdemo.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberSVCImpl implements MemberSVC {

  private final MemberDAO memberDAO;

  @Override
  public Member insertMember(Member member) {
    return memberDAO.insertMember(member);
  }

  @Override
  public boolean isExist(String email) {
    return memberDAO.isExist(email);
  }

  @Override
  public Optional<Member> findByMemeberId(Long memberId) {
    return memberDAO.findByMemeberId(memberId);
  }

  @Override
  public Optional<Member> findByEmail(String email) {
    return memberDAO.findByEmail(email);
  }
}
