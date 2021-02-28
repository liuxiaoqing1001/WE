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
import ModifyPwd from "../components/Other/ModifyPwd";
import PersonalCenter from "../components/Other/PersonalCenter";
import Register from "../components/Other/Register";
import Counseling from "../components/User/Counseling";
import Square from "../components/User/Square";
import TreeHole from "../components/User/TreeHole";
import VolunteerZone from "../components/User/VolunteerZone";
import Home from "../components/User/Home";

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
      redirect: "/Home",
      children:[
        {
          name: Home,
          path: '/Home',
          component: Home
        },
        {
          name: TreeHole,
          path: '/TreeHole',
          component: TreeHole
        },
        {
          name: Square,
          path: '/Square',
          component: Square
        },
        {
          name: Counseling,
          path: '/Counseling',
          component: Counseling
        },
        {
          name: VolunteerZone,
          path: '/VolunteerZone',
          component: VolunteerZone
        },
        {
          // 修改密码
          path: '/ModifyPwd',
          name: 'ModifyPwd',
          component: ModifyPwd
        },
        {
          path: '/PersonalCenter',
          name: 'PersonalCenter',
          component: PersonalCenter
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
          name: 'User',
          path: '/User',
          component: User
        },
        {
          name: 'Volunteer',
          path: '/Volunteer',
          component: Volunteer
        },
        {
          name: 'Admin',
          path: '/Admin',
          component: Admin
        },
        {
          name: 'Model',
          path: '/Model',
          component: Model
        },
        {
          name: 'Data',
          path: '/Data',
          component: Data
        },
        {
          name: 'Setting',
          path: '/Setting',
          component: Setting
        }
      ]
    },
    {
      // 注册
      path: '/Register',
      name: 'Register',
      component: Register
    }
  ]
})
