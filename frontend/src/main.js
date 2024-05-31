/* 가장 상위 인스턴스 */

import { createApp } from 'vue'
import App from './App.vue'
import BootstrapVue3 from 'bootstrap-vue-3'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css'
import {router} from './router'

// 다른 컴포넌트에서는 import 없이 this.$axios로 사용가능
//javascript 어플리케이션에서 서버 통신을 하기 위한 HTTP 통신 라이브러리
import axios from 'axios'
import dayjs from 'dayjs'
import './assets/common.css'

const app = createApp(App)
app.config.globalProperties.$axios = axios; //전역변수로 설정 컴포넌트에서 this.$axios 호출할 수 있게 설정
app.config.globalProperties.$serverUrl = '//localhost:8081' //api server
app.config.globalProperties.$dayjs = dayjs;
app.use(BootstrapVue3)
app.use(router).mount('#app')

