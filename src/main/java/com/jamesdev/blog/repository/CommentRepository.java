package com.jamesdev.blog.repository;

import com.jamesdev.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    //nativeQuery 로  댓글을 바로 save할 수 있는 메소드를 만듬
    @Modifying
    @Query(value="INSERT INTO comment (userId, boardId, content, createdDate) VALUES(?1, ?2, ?3, now())",nativeQuery=true)
    int commentSave(long userId, long boardId, String content);
}
