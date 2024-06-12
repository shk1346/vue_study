package com.example.vue_study.board.info;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardParam {

    private Integer rowCnt;
    private Integer nowPage;
    private String searchKey;
    private String searchWord;

}

