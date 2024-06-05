//라우터 설정

import { createWebHistory, createRouter } from 'vue-router'
//경로가 아닌 라이브러리 이름이 들어가면 라이브러리에서 가지고 오겠다.
import Home from '/src/views/board/Home.vue'
import List from '/src/views/board/List.vue'
import Write from '/src/views/board/Write.vue'
import Detail from '/src/views/board/Detail.vue'

const routes = [
    {
        path: "/",
        name: "Home",
        component: Home,
    },
    {
        path:"/board/list",
        component: List,
    },
    {
        path:"/board/write",
        component: Write,
    },
    {
        path:"/board/write/:userSn",
        component: Write,
    },
    {
        path: '/board/detail/:userSn',
        component: Detail
    }

];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export {router}
