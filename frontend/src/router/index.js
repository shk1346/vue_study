import { createWebHistory, createRouter } from 'vue-router'
//경로가 아닌 라이브러리 이름이 들어가면 라이브러리에서 가지고 오겠다.
import Home from '/src/views/board/Home.vue'
import List from '/src/views/board/List.vue'
import Write from '/src/views/board/Write.vue'

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
    }

];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export {router}
