package com.example.bbsdemo.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardComment {

  private Long CommentId;     //댓글 아이디
  private String Content;
  private String Commenter;
  private LocalDateTime CreatedDate;
  private LocalDateTime UpdatedDate;


}
