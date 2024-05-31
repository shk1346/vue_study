<template>
  This is boardList
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
          <router-link v-bind:to="'/board/write/'+row.userSn">
            {{ row.userNm }}
          </router-link>
        </td>
<!--        <td>{{ row.userNm }}</td>-->
        <td>{{ row.deptNm }}</td>
        <td>{{ row.jbpsNm }}</td>
        <td>{{ $dayjs(row.regDt).format("YYYY-MM-DD")}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "List",
  data(){
    return {
      list:{}
    }
  },
  mounted() {
    this.fnGetList();
  },
  methods: { //함수들
    fnGetList(){
      this.$axios.get(this.$serverUrl+"/board/list",{
      }).then((res)=>{
        this.list = res.data
      //  console.log("list" + JSON.stringify(res.data));
      }).catch((err)=>{
        if (err.message.indexOf('Network Error') > -1) {
          alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
        }
      })
    },
    fnWrite(){
      this.$router.push({path:'/board/write',query:this.body});
    }
  },
  components : {

  }
}
</script>

<style scoped>

</style>
