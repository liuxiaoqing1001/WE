import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import Login from '../views/Login'
import Main from '../views/UserMain'
import Header from '../components/AHeader'
import Footer from '../components/AFooter'

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
      path: '/',        //访问路径
      name: 'login',
      component: Login  //跳转页面
    },
    {
      // 首页
      path: '/Main',
      name: 'Main',
      component: Main
    }
  ]
})
