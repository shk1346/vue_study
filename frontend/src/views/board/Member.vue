<template>
  This is MemberList
  <div>
    <select v-model="search_key">
      <option value="">- 선택 -</option>
      <option value="userNm">이름</option>
      <option value="deptNm">부서</option>
      <option value="jbpsNm">직위</option>
    </select>
    <input type="text" v-model="search_word" @keyup.enter="fnGetList()">
    <button @click="fnGetList()">검색</button>
  </div>
  <div class="board-list">
    <div class="common-buttons">
      <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnWrite">등록</button>
    </div>
    <table class="w3-table-all">
      <thead>
      <tr>
        <th>No</th>
        <th>이름</th>
        <th>부서</th>
        <th>직위</th>
        <th>등록일시</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(row, idx) in list" :key="idx">
        <td>{{ row.userSn }}</td>
        <td>
          <router-link v-bind:to="`/board/detail/${row.userSn}`">
            {{ row.userNm }}
          </router-link>
        </td>
        <td>{{ row.deptNm }}</td>
        <td>{{ row.jbpsNm }}</td>
        <td>{{ $dayjs(row.regDt).format("YYYY-MM-DD")}}</td>
        <td><a href="javascript:;" v-on:click="fnDelete(row.userSn)" class="btn">삭제</a></td>
      </tr>
      </tbody>
    </table>
  </div>
  <div>
    <!--
        (v-bind): 로 props를 자식 컴포넌트로 보내기
        v-on: 으로 자식 컴포넌트로부터 이벤트 받기
    -->
    <Paging
        :paging="paging"
        v-on:prevPage="prevPage"
        v-on:nextPage="nextPage"
        v-on:firstPage="firstPage"
        v-on:lastPage="lastPage"
        v-on:changeNowPage="changeNowPage"/>
  </div>
</template>

<script>
import Paging from '/src/components/Paging.vue'
export default {
  name: "List",
  components : {
    Paging
  },
  data(){ //변수생성
    return {
      /*list:{},
      search_key: this.$route.query.searchKey ? this.$route.query.searchKey : '',
      search_word: this.$route.query.searchWord ? this.$route.query.searchWord : '',
      paging: {
        total: 0, //게시물 총 갯수
        nowPage: 1, //현재 페이지
        rowCnt: 5, //페이지당 보여줄 게시물 수
        pageSize: 5, //섹션당 보여줄 페이지 수
*/
      list: [],
      search_key: this.$route.query.searchKey || '',
      search_word: this.$route.query.searchWord || '',
      paging: {
        total: 0, // 게시물 총 갯수
        nowPage: 1, // 현재 페이지
        rowCnt: 3, // 페이지당 보여줄 게시물 수
        pageSize: 3, // 섹션당 보여줄 페이지 수
        lastPage: 0 // 마지막 페이지
      }

    }
  },
  mounted() {
    this.fnGetList();
  },
  methods: { //함수들
    fnGetList(){
      this.$axios.get(this.$serverUrl+"/board/member",{
        params: { //params 속성을 사용하면 Axios가 자동으로 객체를 쿼리 문자열로 변환하고 URL에 추가
          searchKey: this.search_key,
          searchWord: this.search_word,
          nowPage: this.paging.nowPage,
          rowCnt: this.paging.rowCnt


        }
      }).then((res)=>{
        this.list = res.data.list;
        this.paging = res.data.paging;
        this.paging.count = res.data.length;
        this.paging.lastPage = res.data.paging.lastPage;
        localStorage.setItem('total', res.data.length);

        //  console.log("res : " + JSON.stringify(res));
        //  console.log("data length : " + res.data.length);
        console.log("res.data.paging : " + JSON.stringify(res.data.paging));
      }).catch((err)=>{
        if (err.message.indexOf('Network Error') > -1) {
          alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
        }
      })
    },

    fnWrite(){
      this.$router.push({path:'/board/write',query:this.body});
    },
    fnDelete(userSn){
      this.$axios.delete(this.$serverUrl+"/board/delete",{
        data: { userSn: userSn } // data 속성을 객체로 감싸야 함
      }).then((res) => {
        if(res.data.success){
          alert("삭제되었습니다.");
          this.fnGetList();
        }else{
          alert(res.data.message);
        }
      }).catch((err) => {
        console.log(err);
      })
    },
    // 페이징
    prevPage(){
      if(this.paging.nowPage > 1){
        this.paging.nowPage -= 1;
        this.fnGetList(this.paging.nowPage);
      }else if(this.paging.nowPage == 1){
        alert('첫번째 페이지입니다.');
      }
    },

    nextPage(){
      console.log('paging.lastPage : ', this.paging.lastPage)
      console.log('nowPage : ', this.paging.nowPage)
      if(this.paging.lastPage > this.paging.nowPage){
        this.paging.nowPage += 1;
        this.fnGetList(this.paging.nowPage);
      }else if(this.paging.lastPage == this.paging.nowPage){
        alert('마지막 페이지입니다.');
      }
    },

    firstPage(){
      if(this.paging.nowPage != 1){
        this.paging.nowPage = 1;
        this.fnGetList(this.paging.nowPage);
      }else if(this.paging.nowPage == 1){
        alert('첫번째 페이지입니다.');
      }
    },

    lastPage(){
      if(this.paging.nowPage != this.paging.lastPage){
        this.paging.nowPage = this.paging.lastPage;
        this.fnGetList(this.paging.nowPage);
      }else if(this.paging.lastPage == this.paging.nowPage){
        alert('마지막 페이지입니다.');
      }
    },

    // 자식 컴포넌트로부터 받아온 매개 변수(page) 사용하기
    changeNowPage(page){
      console.log("선택한 page : ", page)
      if(page !== this.paging.nowPage){
        this.paging.nowPage = page;
        this.fnGetList(this.paging.nowPage);
      }
    }
  }

}
</script>

<style scoped>

</style>
