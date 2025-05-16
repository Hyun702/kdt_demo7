package com.example.bbsdemo.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {
  private Long boardId;                  // 아이디
  private String title;                 // 제목
  private String content;               // 내용
  private String writer;                // 작성자
  private LocalDateTime  createdDate;    // 작성날짜
  private LocalDateTime  modifiedDate;    // 수정날짜
}
