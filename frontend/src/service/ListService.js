import axios from '@/utils/request';

export default {
  getList(payload) {
    console.log('payload : ', payload);
    try {
      return axios.post('/board/list', payload);
    }catch(err){
      if(axios.isAxiosError(err)){
        console.log(err);
      }

    }

  }

  /*getList(payload) {
    try {
      return this.$axios.get(this.$serverUrl+"/board/list", payload);
    }catch(err){
      if(axios.isAxiosError(err)){
        console.log(err);
      }

    }

  }*/
};
