//store.js (Vuex)

import { defineStore } from 'pinia';
import api from '@/service/ListService';

export const useListStore = defineStore('listParam', {
    state: () => ({
        contents: '',
    }),
    actions: {
        async getList(payload) {

            try {
                const res = await api.getList(payload);
                    console.log('res.data : ', res.data);
                    this.contents = res.data;

            } catch (err) {
             //   handleError(err);
            }
        }
    },
    getters: {
        getContent(state) {
            console.log('state.contents : ', state.contents);
            return state.contents;
        }

    }
});
