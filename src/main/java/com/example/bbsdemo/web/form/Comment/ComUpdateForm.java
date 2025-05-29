package com.example.bbsdemo.web.form.Comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class ComUpdateForm {

  private Long CommentId;

  @NotBlank(message = "내용 입력은 필수입니다.")
  private String content;

  private LocalDateTime updateDate;
}
