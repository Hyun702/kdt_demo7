package com.example.bbsdemo.domain.Post.dao;

import com.example.bbsdemo.domain.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostDAO {

  // 게시물 등록
  Long save (Post post);

  // 게시판 목록
  List<Post> PostAll();

  // 게시글 조회
  Optional<Post> findById(Long id);
}
