package com.example.vue_study.board.mapper;

import com.example.vue_study.board.info.BoardInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardInfo> selectUserList();
    BoardInfo selectUserInfo(@Param("userSn") int userSn);
    void insert (BoardInfo info);
    void update (BoardInfo info);

}
