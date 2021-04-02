import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/Login'
import UserMain from '../views/UserMain'
import AdminMain from '../views/AdminMain'
import User from "../components/Admin/User";
import Admin from "../components/Admin/Admin";
import Volunteer from "../components/Admin/Volunteer";
import Module from "../components/Admin/Module";
import ArticleManager from "../components/Admin/ArticleManager";
import Data from "../components/Admin/Data";
import Setting from "../components/Admin/Setting";
import ModifyPwd from "../components/Other/ModifyPwd";
import ForgetPwd from "../components/Other/ForgetPwd";
import PersonalCenter from "../components/Other/PersonalCenter";
import Register from "../components/Other/Register";
import Consulting from "../components/User/Consulting";
import Square from "../components/User/Square";
import Article from "../components/User/Article";
import TreeHole from "../components/User/TreeHole";
import VolunteerZone from "../components/User/VolunteerZone";
import VolunteerCenter from "../components/User/VolunteerCenter";
import Other from "../components/Other/Other";
import Home from "../components/User/Home";
import Detail from "../components/User/Detail";
import MentalTest from "../components/User/MentalTest";
import ChatRoom from "../components/User/ChatRoom";
import VCenter from "../components/User/Volunteer/VCenter";
import VArticle from "../components/User/Volunteer/VArticle";
import VConsulting from "../components/User/Volunteer/VConsulting";
import VWrite from "../components/User/Volunteer/VWrite";

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
      // 忘记密码
      path: '/ForgetPwd',
      name: 'ForgetPwd',
      component: ForgetPwd
    },
    {
      // 修改密码
      path: '/ModifyPwd',
      name: 'ModifyPwd',
      component: ModifyPwd
    },
    {
      name: ChatRoom,
      path: "/ChatRoom",
      component: ChatRoom
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
          component: TreeHole,
        },
        {
          name: Square,
          path: '/Square',
          component: Square,
          redirect: "/Article",
          children:[
            {
              name: Article,
              path: '/Article',
              component: Article
            },
            {
              name: Detail,
              path: '/Detail',
              component: Detail
            }
          ]
        },
        {
          name: Consulting,
          path: '/Consulting',
          component: Consulting
        },
        {
          name: VolunteerZone,
          path: '/VolunteerZone',
          component: VolunteerZone
        },
        {
          name: Other,
          path: '/Other',
          component: Other
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
        },
        {
          name: MentalTest,
          path: '/MentalTest',
          component: MentalTest
        },

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
          name: 'Module',
          path: '/Module',
          component: Module
        },
        {
          name: 'ArticleManager',
          path: '/ArticleManager',
          component: ArticleManager
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
    },
    {
      name: VolunteerCenter,
      path: '/VolunteerCenter',
      component: VolunteerCenter,
      redirect: '/VCenter',//重定向
      children:[//子路由
        {
          name: 'VCenter',
          path: '/VCenter',
          component: VCenter
        },
        {
          name: 'VArticle',
          path: '/VArticle',
          component: VArticle
        },
        {
          name: 'VConsulting',
          path: '/VConsulting',
          component: VConsulting
        },
        {
          name: 'VWrite',
          path: '/VWrite',
          component: VWrite
        }
      ]
    }
  ]
})
