package com.example.bbsdemo.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Post {

  private Long postid;        // 아이디
  private String title;   // 제목
  private String text;    // 내용
  private String creater; // 작성자
  private LocalDateTime  create_date;    // 작성날짜
  private LocalDateTime  update_date;    // 수정날짜

}
