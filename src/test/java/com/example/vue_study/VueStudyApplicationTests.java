package com.example.vue_study;

import com.example.vue_study.board.info.BoardInfo;
import com.example.vue_study.board.service.BoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@Slf4j
@SpringBootTest
class VueStudyApplicationTests {
    @Autowired
    BoardService boardService;
    @Test
    void contextLoads() {
    //    List<BoardInfo> list = boardService.selectUserList();

    //    log.info("VueStudyApplicationTests list ======== " + toString(list));
    }

    public static String toString(Object obj) {
        if (obj == null) {
            return null;
        }

        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JsonUtil.toString() failed.", e);
        }
    }

}
