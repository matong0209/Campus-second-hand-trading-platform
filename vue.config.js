module.exports = {
    devServer: {
      proxy: {
        // /api是后台数据接口的上下文路径
        '/api': {
         
            target: 'http://localhost:8084/',
            //允许跨域
            changeOrigin: true,
            pathRewrite: {
              '^/api': ''
            }
        }
      }
    },
    productionSourceMap: process.env.NODE_ENV === 'production' ? false : true
  }