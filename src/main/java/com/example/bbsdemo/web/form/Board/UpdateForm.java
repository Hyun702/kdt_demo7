package com.example.bbsdemo.web.form.Board;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateForm {

  private Long boardId;

  @NotBlank(message = "제목 입력은 필수입니다.")
  private String title;

  @NotBlank(message = "내용 입력은 필수입니다.")
  private String content;

  private LocalDateTime modifiedDate;

}
