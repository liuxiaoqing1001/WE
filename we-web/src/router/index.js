import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import Login from '../views/Login'
import UserMain from '../views/UserMain'
import AdminMain from '../views/AdminMain'
import Users from "../components/Users";

Vue.use(Router);

export default new Router({
  routes: [
    {
      // 登录页
      path: '/',        //访问路径
      name: 'Login',
      component: Login  //跳转页面
    },
    {
      // 用户首页
      path: '/UserMain',
      name: 'UserMain',
      component: UserMain
    },
    {
      // 管理首页
      path: '/AdminMain',
      name: 'AdminMain',
      component: AdminMain,
      redirect: '/Users',//重定向
      children:[//子路由
        {
          path: '/Users',
          component: Users
        }
        // ,
        // {
        //   path: '/HelloWorld',
        //   component: HelloWorld
        // }
      ]
    }
  ]
})
