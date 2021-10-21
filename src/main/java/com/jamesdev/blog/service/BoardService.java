package com.jamesdev.blog.service;

import com.jamesdev.blog.model.Board;
import com.jamesdev.blog.model.User;
import com.jamesdev.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public Page<Board> getPosts(Pageable pageable){return boardRepository.findAll(pageable);}

    @Transactional
    public void writePost(Board board, User user){
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }
    @Transactional
    public Board findPostById(long id){
        return boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("찾는 보드는 없소");
        });
    }
}
