package com.example.bbsdemo.web.form.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class JoinForm {
  @NotBlank(message = "이메일 입력이 필요합니다.")
  @Email(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$",message = "이메일 형식에 맞지 않습니다.")
  @Size(min=7,max=50,message = "이메일을 입력할수 있는 범위는 7~50자 까지 입니다.")
  private String email;         //  이메일(아이디)
  @NotBlank(message = "비밀번호 입력이 필요합니다.")
  private String passwd;        //  비밀번호
  @NotBlank(message = "비밀번호 확인도 입력해주세요")
  private String passwdChk;     //  비밀번호 확인
  @NotBlank(message = "닉네임은 공백일수 없습니다.")
  private String nickname;      //  닉네임


}
