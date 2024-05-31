package com.example.vue_study.board.controller;

import com.example.vue_study.board.info.BoardInfo;
import com.example.vue_study.board.service.BoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@RequestMapping("/board")
@CrossOrigin(origins = "http://localhost:8082") // 해당 출처 허용 ( CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.)
@RestController
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/list")
    public List<BoardInfo> getTest(){
        log.info("게시판 컨트롤러");

        List<BoardInfo> list = boardService.selectUserList();
        log.info("BoardController list ======== " + toString(list));

        return list;

    }

    @PostMapping("/write")
    public ResponseEntity<Map<String,Object>> write(@RequestBody BoardInfo info){

        log.info("BoardController BoardInfo ======== " + toString(info));

        Map<String,Object> response = new HashMap<>();

        BoardInfo newBoard = new BoardInfo();
        newBoard.setUserNm(info.getUserNm());
        newBoard.setDeptNm(info.getDeptNm());
        newBoard.setJbpsNm(info.getJbpsNm());

        info.setEmlAddr("shk@co.com");
        info.setUserPswdEvl("@1234");
        info.setMblTelSeCdEvl("010");
        info.setMblTelOnoEvl("2222");
        info.setMblTelSnEvl("3333");
        info.setMblTelSnEvl("3333");
        info.setRoleCd("manager");

        try {

            boardService.insert(info);
            response.put("success", true);
            return ResponseEntity.ok(response);

        } catch (Exception e){
            response.put("success", false);
            response.put("message", "등록에 실패했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }


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
