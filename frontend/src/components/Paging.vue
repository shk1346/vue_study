<template>
  <div class="mt-5">
    <div class="flex justify-center px-4 py-4 overflow-x-auto rounded-md">
      <div class="flex mr-4 rounded">
        <button
            @click="firstPage()"
            class="px-3 py-2 ml-0 leading-tight bg-white text-indigo-700 border border-r-0 border-gray-200 rounded-l hover:bg-indigo-500 hover:text-white">
          처음으로
        </button>

        <button
            @click="prevPage()"
            class="px-3 py-2 leading-tight bg-white text-indigo-700  border border-r-0 border-gray-200 hover:bg-indigo-500 hover:text-white">
          이전
        </button>

        <!--페이지 세팅을 for문으로 돌리고, 현재 페이지 색상 변경, 클릭 시 페이지 이동-->
        <button
            v-for="page in setPages" :key="page"
            :class="[page === this.paging.nowPage ? 'selected' : '']"
            class="px-3 py-2 leading-tight bg-white text-indigo-700  border border-r-0 border-gray-200 hover:bg-indigo-500 hover:text-white"
            @click="changeNowPage(page)">
          {{ page }}
        </button>

        <button
            @click="nextPage()"
            class="px-3 py-2 leading-tight bg-white text-indigo-700  border border-r-0 border-gray-200 hover:bg-indigo-500 hover:text-white">
          다음
        </button>

        <button
            @click="lastPage()"
            class="px-3 py-2 leading-tight bg-white text-indigo-700 border border-gray-200 rounded-r hover:bg-indigo-500 hover:text-white">
          끝으로
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Pagination',

  // 부모 컴포넌트에서 받아오는 props 데이터
  props:['paging'],

  computed: {
    //시작 페이지 계산
    startPage: function () {
      // 부모 컴포넌트에서 받아오는 props 데이터
      console.log('this.paging : ', this.paging)

      let value = (Math.floor((this.paging.nowPage-1) / this.paging.pageSize) * this.paging.pageSize) + 1
      return value
    },
    //총 페이지 수를 계산
    totalPage: function () {
      var count = this.paging.rowCnt;
      return Math.floor(this.paging.total / count) + ((this.paging.total % count) == 0 ? 0 : 1)
    },
    // 화면에 표시할 페이지 숫자 배열
    setPages() {

      let pages = [];
      let endPage = this.startPage + this.paging.pageSize - 1;
      console.log('startPage : ', this.startPage)
      console.log('endPage : ', endPage)

      for(let num = this.startPage; num <= endPage; num++){
        pages.push(num);
      }
      console.log('pages : ', pages)

      return pages;
    },
  },

  // 부모 컴포넌트로 이벤트 전달
  methods:{
    prevPage(){
      this.$emit("prevPage");
    },
    nextPage(){
      this.$emit("nextPage");
    },
    firstPage(){
      this.$emit("firstPage");
    },
    lastPage(){
      this.$emit("lastPage");
    },

    /* emit
    *   자식 컴포넌트에서 부모 컴포넌트로 데이터를 전달 또는 트리거의 목적으로 이벤트를 내보낼 수 있다.
    * 숫자를 눌렀을 때 작동하는 페이지 이동
      이벤트 + 매개변수(page) 전달! > List.vue
    * */
    changeNowPage(page){
      this.$emit("changeNowPage", page);
    }
  },

};
</script>

<style>
button.selected{
  background-color: #4338CA;
  color: white;
}

</style>
