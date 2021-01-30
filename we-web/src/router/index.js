import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import Login from '../views/Login'
import UserMain from '../views/UserMain'
import AdminMain from '../views/AdminMain'
import User from "../components/User";
import Admin from "../components/Admin";
import Volunteer from "../components/Volunteer";

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
      redirect: '/User',//重定向
      children:[//子路由
        {
          path: '/User',
          component: User
        },
        {
          path: '/Volunteer',
          component: Volunteer
        },
        {
          path: '/Admin',
          component: Admin
        }
      ]
    }
  ]
})
