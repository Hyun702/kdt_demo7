package com.example.bbsdemo.domain.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "board_seq", sequenceName = "board_seq", allocationSize = 1)
  private Long boardId;                  // 아이디
  private String title;                 // 제목
  private String content;               // 내용
  private String writer;                // 작성자
  private LocalDateTime  createdDate;    // 작성날짜
  private LocalDateTime  modifiedDate;    // 수정날짜

}
