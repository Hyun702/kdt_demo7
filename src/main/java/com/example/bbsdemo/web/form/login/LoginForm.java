package com.example.bbsdemo.web.form.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginForm {

  @NotBlank(message = "이메일 입력은 필수입니다.")
  private String email;
  @NotBlank(message = "비밀번호 입력은 필수입니다.")
  private String passwd;
}
