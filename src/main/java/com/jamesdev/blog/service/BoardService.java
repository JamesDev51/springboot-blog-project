package com.jamesdev.blog.service;

import com.jamesdev.blog.dto.CommentWriteRequestDto;
import com.jamesdev.blog.dto.PostRequestDto;
import com.jamesdev.blog.model.Board;
import com.jamesdev.blog.model.User;
import com.jamesdev.blog.repository.BoardRepository;
import com.jamesdev.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public Page<Board> getPosts(Pageable pageable){return boardRepository.findAll(pageable);}

    @Transactional
    public void writePost(PostRequestDto postRequestDto, User user){
        Board requestedBoard = postRequestDto.toEntity();
        requestedBoard.setCount(0);
        requestedBoard.setUser(user);
        boardRepository.save(requestedBoard);
    }
    @Transactional
    public Board findPostById(long id){
        return boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("찾는 보드는 없소");
        });
    }
    @Transactional
    public void editPost(long id, PostRequestDto postRequestDto){
        Board board = boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("찾는 보드가 업수다");
        });
        board.setTitle(postRequestDto.getTitle());
        board.setContent(postRequestDto.getContent());
    }
    @Transactional
    public void deletePost(long id){
        boardRepository.deleteById(id);
    }

    @Transactional
    public void writeComment(CommentWriteRequestDto commentWriteRequestDto){
        int ret=commentRepository.commentSave(commentWriteRequestDto.getUserId(), commentWriteRequestDto.getBoardId(), commentWriteRequestDto.getContent());
    }
    public void deleteComment(long id){
        commentRepository.deleteById(id);
    }
}
