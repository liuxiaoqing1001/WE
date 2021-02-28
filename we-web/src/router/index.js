import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/Login'
import UserMain from '../views/UserMain'
import AdminMain from '../views/AdminMain'
import User from "../components/Admin/User";
import Admin from "../components/Admin/Admin";
import Volunteer from "../components/Admin/Volunteer";
import Model from "../components/Admin/Model";
import Data from "../components/Admin/Data";
import Setting from "../components/Admin/Setting";
import ModifyPwd from "../views/ModifyPwd";
import Register from "../views/Register";
import ChatRoom from "../components/User/ChatRoom";
import Sort from "../components/User/Sort";
import TreeHole from "../components/User/TreeHole";

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
      component: UserMain,
      redirect: "/TreeHole",
      children:[
        {
          name: TreeHole,
          path: '/TreeHole',
          component: TreeHole
        },
        {
          name: Sort,
          path: '/Sort',
          component: Sort
        },
        {
          name: ChatRoom,
          path: '/ChatRoom',
          component: ChatRoom
        }
      ]
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
        },
        {
          path: '/Model',
          component: Model
        },
        {
          path: '/Data',
          component: Data
        },
        {
          path: '/Setting',
          component: Setting
        }
      ]
    },
    {
      // 修改密码
      path: '/ModifyPwd',
      name: 'ModifyPwd',
      component: ModifyPwd
    },
    {
      // 注册
      path: '/Register',
      name: 'Register',
      component: Register
    }
  ]
})
