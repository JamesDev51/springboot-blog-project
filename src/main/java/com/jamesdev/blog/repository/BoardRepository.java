package com.jamesdev.blog.repository;

import com.jamesdev.blog.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
    Page<Board> findByTitleContainingOrContentContaining(Pageable pageable,String title, String content);
}
