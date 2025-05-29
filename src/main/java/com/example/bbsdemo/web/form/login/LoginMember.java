package com.example.bbsdemo.web.form.login;

import lombok.AllArgsConstructor;
import lombok.Getter;



@Getter
@AllArgsConstructor

public class LoginMember {

  private Long memberId;    // 확인용 멤버 아이디
  private String email;
  private String nickname;

}
