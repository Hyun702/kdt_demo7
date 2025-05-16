package com.example.bbsdemo.web.form.Board;

import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class SaveForm {


  @NotBlank(message = "제목 입력은 필수입니다.")
  private String title ;

  @NotBlank(message = "작성자 입력은 필수입니다.")
  private String writer;

  @NotBlank(message = "내용 입력은 필수입니다.")
  private String content;

}
