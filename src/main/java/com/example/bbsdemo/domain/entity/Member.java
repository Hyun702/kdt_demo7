package com.example.bbsdemo.domain.entity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {
  private Long memberId;          //  MEMBER_ID	NUMBER
  private String email;           //  EMAIL	VARCHAR2(100 BYTE)
  private String passwd;          //  PASSWD	VARCHAR2(12 BYTE)
  private String tel;             //  TEL	VARCHAR2(13 BYTE)
  private String nickname;        //  NICKNAME	VARCHAR2(30 BYTE)
  private String gender;          //  GENDER	VARCHAR2(6 BYTE)
  private LocalDateTime cdate;    //  CDATE	TIMESTAMP(6)
  private LocalDateTime udate;    //  UDATE	TIMESTAMP(6)

}
