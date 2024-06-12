<template>
  <div>
    <h1>{{ vueNm }}</h1>

    <div class="AddWrap">
      <form>
        <table class="tbAdd">
          <colgroup>
            <col width="15%" />
            <col width="*" />
          </colgroup>
          <tr>
            <th>이름</th>
            <td><input type="text" v-model="userNm" ref="userNm" /></td>
          </tr>
          <tr>
            <th>부서</th>
            <td><input type="text" v-model="deptNm" ref="deptNm" /></td>
          </tr>
          <tr>
            <th>직위</th>
            <td><input type="text" v-model="jbpsNm" ref="jbpsNm" /></td>
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
      <a href="javascript:;" v-on:click="fnSave" class="btnAdd btn">저장</a>
    </div>
  </div>
</template>ㅇ

<script>
export default {
  name: "Write",
  data(){ //변수생성
    return {
      userNm:'',
      deptNm:'',
      jbpsNm:'',
      userSn:'', // userSn을 전역 변수로 정의
      vueNm:'',
      form: ''
    }
  },
  mounted() {
    // userSn을 초기화
    this.userSn = this.$route.params.userSn; //URL 파라미터에서 userSn을 가져옴
    if(this.userSn !== undefined){
      this.vueNm = '게시글 수정'
    }else{
      this.vueNm = '게시글 등록'
    }
    this.fnDetail();
  },
  methods: {
    fnList(){
      this.$router.push({ path: '/board/list', query: this.form });
    },
    fnDetail(){
    //  const userSn = this.$route.params.userSn; //URL 파라미터에서 userSn을 가져옴

      if(this.userSn !== undefined){
        // 템플릿 리터럴을 사용하여 URL을 구성 (백틱 사용 > 문자열로 인식)
        this.$axios.get(this.$serverUrl+`/board/detail/${this.userSn}`)
            .then(res => {
              // Vue 컴포넌트의 데이터 설정
              this.userInfo = res.data;
              this.userNm = res.data.userNm;
              this.deptNm = res.data.deptNm;
              this.jbpsNm = res.data.jbpsNm;

            })
            .catch(err => {
              console.log(err);
            });
      }

    },
    fnSave(){

      if(!this.userNm){
        alert("이름을 입력해주세요.");
        this.$refs.userNm.focus();
        return;
      } else if(!this.deptNm){
        alert("부서를 입력해주세요.");
        this.$refs.deptNm.focus();
        return;
      } else if(!this.jbpsNm){
        alert("직위를 입력해주세요.");
        this.$refs.jbpsNm.focus();
        return;
      }


      if(this.userSn !== undefined){ //수정
        this.form = {
          userNm: this.userNm,
          deptNm: this.deptNm,
          jbpsNm: this.jbpsNm,
          userSn: this.userSn
        }

        this.$axios.put(this.$serverUrl+'/board/write', this.form)
            .then((res)=>{
              console.log('수정 res.data ', res.data);
              console.log('수정 res ', res);

              if(res.data.success){
                alert("수정되었습니다.");
                this.fnList();
              }else {
                alert("수정이 실패되었습니다.");
              }
            }).catch((err)=>{
          console.log(err);
        })


      } else { //신규저장
        this.form = {
          userNm: this.userNm,
          deptNm: this.deptNm,
          jbpsNm: this.jbpsNm
        }

        this.$axios.post(this.$serverUrl+'/board/write', this.form)
            .then((res)=>{
              console.log('신규저장 res.data ', res.data);
              console.log('신규저장 res ', res);

              if(res.data.success){
                alert("등록되었습니다.");
                this.fnList();
              }else {
                alert("등록이 실패되었습니다.");
              }
            }).catch((err)=>{
          console.log(err);
        })
      }






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
