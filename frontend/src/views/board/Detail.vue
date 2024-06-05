<template>
  <div>
    <h1>게시글 상세</h1>

    <div class="AddWrap">
      <form>
        <table class="tbAdd">
          <colgroup>
            <col width="15%" />
            <col width="*" />
          </colgroup>
<!--          <p>{{ userInfo }}</p>
          <p>{{ userNm }}</p>-->
          <tr>
            <th>회원번호</th>
            <td>{{ $route.params.userSn }}</td> <!--    url에 입력한 params 중 id(userSn) 값      -->
          </tr>
          <tr>
            <th>이름</th>
            <td>{{ userNm }}</td>
          </tr>
          <tr>
            <th>부서</th>
            <td>{{ deptNm }}</td>
          </tr>
          <tr>
            <th>직위</th>
            <td>{{ jbpsNm }}</td>
          </tr>
<!--          <tr>
            <th>내용</th>
            <td><textarea v-model="cont" ref="cont"></textarea></td>
          </tr>-->
        </table>
      </form>
    </div>

    <div class="btnWrap">
<!--      <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnList">목록</button>
      <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnSave">등록</button>-->

      <a href="javascript:;" @click="fnList" class="btn">목록</a>
      <a href="javascript:;" v-on:click="fnWrite" class="btnAdd btn">수정</a>
    </div>
  </div>
</template>

<script>
export default {
  name: "Detail",
  data(){ //변수생성
    return {
      userInfo:null,
      userNm:'',
      deptNm:'',
      jbpsNm:'',
      userSn:'' // userSn을 전역 변수로 정의
    }
  },
  mounted() {
    // userSn을 초기화
    this.userSn = this.$route.params.userSn; //URL 파라미터에서 userSn을 가져옴
    this.fnDetail();
  },
  methods: {
    fnList(){
      this.$router.push({ path: '/board/list', query: this.form });
    },
    fnDetail(){
      // 템플릿 리터럴을 사용하여 URL을 구성 (백틱 사용)
      this.$axios.get(this.$serverUrl+`/board/detail/${this.userSn}`)
          .then(res => {
            console.log('res ? :', res)

            this.userInfo = res.data;
            this.userNm = res.data.userNm;
            this.deptNm = res.data.deptNm;
            this.jbpsNm = res.data.jbpsNm;

          })
          .catch(err => {
            console.log(err);
          });

    },
    fnWrite(){
      this.$router.push({ path: `/board/write/${this.userSn}`, query: this.form });
    }
  }
}


</script>

<style scoped>
.tbAdd{border-top:1px solid #888;}
.tbAdd th, .tbAdd td{border-bottom:1px solid #eee; padding:5px 0;}
.tbAdd td{padding:10px 10px; box-sizing:border-box;}
.tbAdd td input{width:100%; min-height:30px; box-sizing:border-box; padding:0 10px;}
.tbAdd td textarea{width:100%; min-height:300px; padding:10px; box-sizing:border-box;}
.btnWrap{text-align:center; margin:20px 0 0 0;}
.btnWrap a{margin:0 10px;}
.btnAdd {background:#43b984}
.btnDelete{background:#f00;}
</style>
