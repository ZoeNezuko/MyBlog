module.exports = {
  devServer: {
    proxy: {
      "/api": {
        target: "http://localhost:8666",
        changeOrigin: true,
        pathRewite: {
          "^api": ""
        }
      }
    }
  }
};
