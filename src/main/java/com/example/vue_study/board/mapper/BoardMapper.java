package com.example.vue_study.board.mapper;

import com.example.vue_study.board.info.BoardInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    List<BoardInfo> selectUserList(@Param("param") Map<String, Object> param);
    int selectUserListCnt(@Param("param") Map<String, Object> param);
    BoardInfo selectUserInfo(@Param("userSn") int userSn);
    void insert (BoardInfo info);
    void update (BoardInfo info);
    int delete(BoardInfo info);


}
