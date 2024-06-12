import axios from 'axios';
import { showLoading } from '@/utils/utils.js';

const instance = axios.create({
  baseURL: 'http://localhost:8081' //???
});

instance.interceptors.request.use(
  (config) => {
    let token = sessionStorage.getItem('tokenValue');
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    showLoading(true);
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

axios.interceptors.response.use(
  (response) => {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // - 200번대 응답은 여기서 처리
    // Do something with response data - 응답받은 데이터 처리
    showLoading(false);
    return response;
  }, (error) => {
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    // - 200번대 외의 응답은 여기서 처리
    // Do something with response error - 응답 에러 처리
    return Promise.reject(error);
  });

export default instance;
