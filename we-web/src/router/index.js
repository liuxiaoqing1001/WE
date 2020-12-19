import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import Login from '../views/Login'
import Main from '../views/Main'
import Header from '../components/Header'
import Footer from '../components/Footer'

Vue.use(Router);

export default new Router({
  routes: [
    // {
    //   path: '/',
    //   name: 'HelloWorld',
    //   component: HelloWorld
    // },
    {
      // 登录页
      path: '/',
      name: 'login',
      component: Login
    },
    {
      // 首页
      path: '/Main',
      name: 'Main',
      component: Main
    }
  ]
})
