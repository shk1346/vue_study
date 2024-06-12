import { defineStore } from 'pinia';
export const useCommonStore = defineStore('common', {
    state: () => ({
        showloading: false,
        error: '',
        showError: false
    }),
    getters: {
        getShowLoading(state) {
            return state.showloading;
        },
        getError(state) {
            return state.error;
        },
        getShowError(state) {
            return state.showError;
        }
    }
});
