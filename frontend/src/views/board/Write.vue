<template>
  <div>
    <h1>게시판 등록</h1>

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
      <a href="javascript:;" v-on:click="fnSave" class="btnAdd btn">등록</a>
    </div>
  </div>
</template>

<script>
export default {
  name: "Write",
  data(){ //변수생성
    return {
      userNm:'',
      deptNm:'',
      jbpsNm:'',
      form: ''
    }
  },
  mounted() {
    //this.fnList(); > 왜 문제???
  },
  methods: {
    fnList(){
      this.$router.push({ path: '/board/list', query: this.form });
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

      this.form = {
        userNm: this.userNm,
        deptNm: this.deptNm,
        jbpsNm: this.jbpsNm
      }

      this.$axios.post(this.$serverUrl+'/board/write', this.form)
          .then((res)=>{
            console.log(res.data);
            console.log(res);

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
