package com.example.bbsdemo.domain.Post.dao;

import com.example.bbsdemo.domain.entity.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest


class PostDAOImplTest {

  private static final Logger log = LoggerFactory.getLogger(PostDAOImplTest.class);
  @Autowired
  PostDAO postDAO;


  @Test
  @DisplayName("게시물 등록")
  void save() {
    Post post = new Post();
    post.setTitle("토지");
    post.setText("박경리 작가의 책");
    post.setCreater("홍길동1");
    post.setCreate_date(LocalDateTime.now());
    post.setUpdate_date(LocalDateTime.now());

    Long pid = postDAO.save(post);
    log.info("게시번호={}", pid);

  }

  @Test
  @DisplayName("게시판 목록")
  void PostAll(){

    List<Post> list = postDAO.PostAll();
//    log.info("게시물 목록 = {}", list);
    for (Post post : list) {
      log.info("게시물 목록 = {}", post);
    }

  }
}