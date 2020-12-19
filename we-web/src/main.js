// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import VueRouter from 'vue-router'
import router from './router'

import axios from 'axios'
import VueAxios from 'vue-axios'

// 导入 ElementUI
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import App from './App'

// 安装路由
Vue.use(VueRouter);

Vue.use(VueAxios,axios);

// 安装 ElementUI
Vue.use(ElementUI);

Vue.config.productionTip = false;
// // 配置请求的根路径
// axios.defaults.baseURL = 'http://127.0.0.1:8617/api/private/v1/';
// Vue.prototype.$http = axios;

new Vue({
  el: '#app',
  // 启用路由
  router,
  // 启用 ElementUI
  // render: h => h(App)
  components: { App },
  template: '<App/>'

});

// import Vue from 'vue'
// import App from './App'
// import router from './router'
// // import BootstrapVue from 'bootstrap-vue'
// // import 'bootstrap/dist/css/bootstrap.css'
// // import 'bootstrap-vue/dist/css/bootstrap-vue.css'
//
// import ElementUI from 'element-ui'
// import 'element-ui/lib/theme-chalk/index.css'
//
// // Vue.use(BootstrapVue);
// Vue.use(ElementUI);
// Vue.config.productionTip = false;
//
// /* eslint-disable no-new */
// new Vue({
//   el: '#app',
//   router,
//   components: { App },
//   template: '<App/>'
// });
