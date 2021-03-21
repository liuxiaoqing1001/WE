// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import VueRouter from 'vue-router'
import router from './router'

import App from './App'

import axios from 'axios'
// import VueAxios from 'vue-axios'

// 导入 ElementUI
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

// 星空粒子插件
import VueParticles from 'vue-particles'

import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

import Chat from 'jwchat'

Vue.use(Chat);

Vue.use(mavonEditor);

Vue.use(VueParticles);

// 安装路由
Vue.use(VueRouter);

// Vue.use(VueAxios,axios);

// 安装 ElementUI
Vue.use(ElementUI);

Vue.config.productionTip = false;
// 配置请求的根路径
axios.defaults.baseURL = 'http://127.0.0.1:8617/api/private/v1/';
Vue.prototype.$http = axios;

// 路由跳转
Vue.prototype.$goRoute = function (index) {
  this.$router.push(index)
};

new Vue({
  el: '#app',
  // 启用路由
  router,
  // 启用 ElementUI
  // render: h => h(App)
  components: { App },
  template: '<App/>'

});
