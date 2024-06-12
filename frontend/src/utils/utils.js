import { useCommonStore } from '@/stores/common';



export const showLoading = (data) => {
  const common = useCommonStore();
  common.showLoading = data;
};

// Catches error connection or any other error (checks if error.response exists)
export const handleError = (error) => {
  const common = useCommonStore();
  let errMsg = '';
  // Resets errors in store
  common.error = '';
  // Checks if unauthorized
  errMsg = error.response ? error.response.data.message : '잘못된 호출입니다.';
  showLoading(false);
  common.error = errMsg;
  common.showError = true;
  // }
  if (error.response === undefined) return;
  if (error.response.status === 401) {
    setTimeout(() => {
      sessionStorage.removeItem('tokenValue');
      sessionStorage.removeItem('tokenExpirations');
      sessionStorage.removeItem('userInfo');

      this.$router.push({ path: '/login' });
    }, 1000);
  } else if (error.response.status === 403 || error.response.status === 404 || error.response.status === 405) {
    setTimeout(() => {
      this.$router.push({ path: '/login' });
    }, 1000);
  }

};
