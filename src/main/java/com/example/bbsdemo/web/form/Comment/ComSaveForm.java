package com.example.bbsdemo.web.form.Comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ComSaveForm {

  @NotBlank(message = "작성자 입력은 필수입니다.")
  private String Commenter;

  @NotBlank(message = "내용 입력은 필수입니다.")
  private String content;

}
