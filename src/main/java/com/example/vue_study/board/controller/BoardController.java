package com.example.vue_study.board.controller;

import com.example.vue_study.board.info.BoardInfo;
import com.example.vue_study.board.info.BoardParam;
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

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@AllArgsConstructor
@RequestMapping("/board")
@CrossOrigin(origins = "http://localhost:8082") // 해당 출처 허용 ( CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.)
@RestController
public class BoardController {

    @Autowired
    BoardService boardService;

    /*
     * ver2
     * */
    @RequestMapping(path = "/list", method = POST)
    public Map<String,Object> getNoticeList(@RequestBody BoardParam boardParam) throws Exception {

        log.info("BoardParam ======== " + toString(boardParam));

        Map<String, Object> formData = new HashMap<>();
        formData.put("searchKey", boardParam.getSearchKey());
        formData.put("searchWord", boardParam.getSearchWord());
        formData.put("startIndex", boardParam.getNowPage() * boardParam.getRowCnt() - boardParam.getRowCnt());
        formData.put("rowCnt", boardParam.getRowCnt());

        List<BoardInfo> list = boardService.selectUserList(formData);
        int totalCnt = boardService.selectUserListCnt(formData);



        Map<String, Object> paging = new HashMap<>();
        paging.put("total", totalCnt); // 게시물 총 갯수
        paging.put("nowPage", boardParam.getNowPage()); // 현재 페이지
        paging.put("rowCnt", boardParam.getRowCnt()); // 페이지당 보여줄 게시물 수
        paging.put("pageSize", 3); // 섹션당 보여줄 페이지 수
        /*
        * Math.ceil함수 안에서 연산을 하면 정수값이 도출돼서 Math.ceil이 적용되지 않는다.
            따라서 double타입으로 강제타입변환을 하거나 1.0을 곱해서 소수점이 있는 실수로 만들어줘야 한다.
        * */
        paging.put("lastPage", (int) Math.ceil((double) totalCnt/(double)boardParam.getRowCnt())); // 마지막 페이지 번호

        Map<String, Object> rtnData = new HashMap<>();
        rtnData.put("list", list);
        rtnData.put("paging", paging);

        log.info("list ======== " + toString(list));

        return rtnData;
    }

    /*
    * ver1
    * */
    @GetMapping("/member")
    public Map<String,Object> Member(@RequestParam("searchKey") String searchKey, @RequestParam("searchWord") String searchWord
            , @RequestParam("nowPage") int nowPage, @RequestParam("rowCnt") int rowCnt){
        Map<String, Object> formData = new HashMap<>();
        formData.put("searchKey", searchKey);
        formData.put("searchWord", searchWord);
        formData.put("startIndex", nowPage * rowCnt - rowCnt);
        formData.put("rowCnt", rowCnt);

        List<BoardInfo> list = boardService.selectUserList(formData);
        int totalCnt = boardService.selectUserListCnt(formData);
    //    log.info("BoardController list ======== " + toString(list));

        Map<String, Object> paging = new HashMap<>();
        paging.put("total", totalCnt); // 게시물 총 갯수
        paging.put("nowPage", nowPage); // 현재 페이지
        paging.put("rowCnt", rowCnt); // 페이지당 보여줄 게시물 수
        paging.put("pageSize", 3); // 섹션당 보여줄 페이지 수
        paging.put("lastPage", (int) Math.ceil((double) totalCnt/(double)rowCnt)); // 마지막 페이지 번호


        Map<String, Object> rtnData = new HashMap<>();
        rtnData.put("list", list);
        rtnData.put("paging", paging);

        return rtnData;

    }

    @PostMapping("/write")
    public ResponseEntity<Map<String,Object>> write(@RequestBody BoardInfo info){

        Map<String,Object> response = new HashMap<>();

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

    @GetMapping("/detail/{userSn}")
    public BoardInfo detail(@PathVariable("userSn") int userSn){
        log.info("detail userSn======== " + userSn);

        BoardInfo userInfo = boardService.selectUserInfo(userSn);
        return userInfo;
    }

    @PutMapping("/write")
    public ResponseEntity<Map<String,Object>> update(@RequestBody BoardInfo info){
        log.info("update BoardInfo ======== " + toString(info));

        Map<String,Object> response = new HashMap<>();

        try {
            boardService.update(info);
            response.put("success", true);
            return ResponseEntity.ok(response);

        } catch (Exception e){
            response.put("success", false);
            response.put("message", "수정에 실패했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String,Object>> delete(@RequestBody BoardInfo info){
        log.info("update BoardInfo ======== " + toString(info));
        log.info("update BoardInfo ======== " + info.getUserSn());


        Map<String,Object> response = new HashMap<>();

        try {
            boardService.delete(info);
            response.put("success", true);
            return ResponseEntity.ok(response);

        } catch (Exception e){
            response.put("success", false);
            response.put("message", "수정에 실패했습니다.");
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
