package com.example.vue_study.board.info;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


    @Getter
    @Setter
    public class BoardInfo {

        private int userSn;                 // 회원일련번호
        private int custSn;                 // 고객일련번호
        private String emlAddr;             // 이메일주소
        private String emlAddrHvl;          // 이메일주소해쉬값
        private String userPswdEvl;          // 회원비밀번호암호화값
        private String userNm;              // 회원명
        private String mblTelSeCdEvl;       // 휴대전화구분코드암호화값
        private String mblTelOnoEvl;         // 휴대전화국번호암호화값
        private String mblTelSnEvl;          // 휴대전화일련번호암호화값
        private String telRgnCdEvl;          // 전화지역코드암호화값
        private String telOnoEvl;            // 전화국번호암호화값
        private String telSnEvl;             // 전화일련번호암호화값
        private String deptNm;              // 부서명
        private String jbpsNm;              // 직위명
        private String roleCd;              // 역할코드 1: 관리자 2: 그룹회원
        private String userSttsCd;          // 회원상태코드 1:정상 2: 탈퇴
        private int updtMngrUserSn;         // 갱신운영자일련번호
        private int updtCustUserSn;         // 갱신회원일련번호
        private String joinYmd;             // 가입일자
        private Date regDt;                 // 등록일시
        private Date updtDt;                // 갱신일시

        //목록
        private int no;                     // 행번호

        //추가
        private String roleNm;              // 역할이름
        private String chgPwVal;            // 비밀번호 변경 유효성
    }

