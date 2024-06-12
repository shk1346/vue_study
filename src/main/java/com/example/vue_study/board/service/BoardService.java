package com.example.vue_study.board.service;

import com.example.vue_study.board.info.BoardInfo;
import com.example.vue_study.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    BoardMapper boardMapper;

    public List<BoardInfo> selectUserList(Map<String, Object> param) {
        return boardMapper.selectUserList(param);
    }
    public int selectUserListCnt(Map<String, Object> param) {
        return boardMapper.selectUserListCnt(param);
    }

    public BoardInfo selectUserInfo(int userSn){
        return boardMapper.selectUserInfo(userSn);
    }

    public void insert (BoardInfo info){
        boardMapper.insert(info);
    }

    public void update(BoardInfo info){
        boardMapper.update(info);
    }

    public void delete(BoardInfo info){
        boardMapper.delete(info);
    }
}
