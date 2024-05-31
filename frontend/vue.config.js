const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: { //개발환경에서의 서버
    port:8082, //vue.js의 포트번호
    proxy: {
      '/api':{
        // '/api'로 들어오면 포트 8081(스프링서버)로 보낸다.
        target: 'http://localhost:8081',
        changeOrigin: true //cross origin 허용
      }
    }
  }
})
