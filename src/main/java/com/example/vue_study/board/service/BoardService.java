package com.example.vue_study.board.service;

import com.example.vue_study.board.info.BoardInfo;
import com.example.vue_study.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    BoardMapper boardMapper;

    public List<BoardInfo> selectUserList() {
        return boardMapper.selectUserList();
    }

    public void insert (BoardInfo info){
        log.info("BoardService BoardInfo ======== " + info);
        boardMapper.insert(info);
    }
}
