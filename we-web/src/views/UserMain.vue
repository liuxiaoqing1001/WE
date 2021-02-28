<template>
  <div>
    <el-container class="home-container">
      <!-- 头部 -->
      <el-header>
        <div>
          <img src="../assets/we_logo_1.png" alt="">
          <span class="logo">WE</span>
        </div>
        <ul>
          <li class="topNav" v-for="item in links"  @click="makeActive(item.name)" >
            <a :class="{active:activeIndex===item.name}" v-on:click="$goRoute(item.route) ">{{item.text}}</a>
          </li>
        </ul>
        <el-input placeholder="请输入内容" style="width: 300px">
<!--          <el-button slot="append" icon="el-icon-search" ></el-button>-->
        </el-input>
        <span style="float:right;padding-top:10px;margin-right:1%">
          <el-dropdown trigger="click">
            <span class="el-dropdown-link">
              user
              <i class="el-icon-caret-bottom el-icon--right" style="color: blue"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="personalCenter">个人中心</el-dropdown-item>
              <el-dropdown-item @click.native="modifyPwd">修改密码</el-dropdown-item>
              <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </span>
      </el-header>
      <!-- 主体 -->
      <el-container>
        <el-main>
          <!--路由占位符-->
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
    export default {
      name: 'UserMain',
      data () {
        return {
          activeIndex: 'Home',
          links: [
            {
              text: '首页',
              name: 'Home',
              route: '/Home'
            },
            {
              text: '广场',
              name: 'Square',
              route: '/Square'
            },
            {
              text: '树洞',
              name: 'TreeHole',
              route: '/TreeHole'
            },
            {
              text: '咨询中心',
              name: 'Counseling',
              route: '/Counseling'
            },
            {
              text: '自愿者专区',
              name: 'VolunteerZone',
              route: '/VolunteerZone'
            }
          ]
        }
      },

      // 点击菜单使用的函数
      methods: {
        makeActive: function(item){
          // 模型改变，视图会自动更新
          this.activeIndex = item;
        },
        logout() {
          // // 清空token
          // window.sessionStorage.clear('token');
          // 跳转到登录页
          this.$router.push('/');
        },
        personalCenter(){
          this.$router.push("/PersonalCenter");
        },
        modifyPwd(){
          this.$router.push('/ModifyPwd');
        }
      }
    }
</script>

<style scoped>

  .home-container {
    min-height: 910px;
    height: 100%;
  }

  .el-header {
    /*color: mediumseagreen;*/
    display: flex;
    justify-content: space-between;
    padding-left: 0;
    align-items: center;
    font-size: 20px;
  }

  div {
    display: flex;
    align-items: center;
  }

  img{
    height: 60px;
    width: 60px;
    margin-left: 20px;
  }

  span {
    margin-left: 10px;
  }

  .logo{
    /*margin-left: 30px;*/
    color: mediumseagreen;
    font-size: x-large;
  }

  .topNav{
    display: inline-block;
    margin-right: 10px;
  }

  /*a:hover可用于设置当鼠标悬停在超链接之上时超链接的样式*/
  ul li a:hover{
    cursor: pointer;
    color: blue;
    font-size: medium;
    /*background-color: #138bec;*/
    /*border: 1px solid #138bec;*/
  }

  ul li a{
    display:inline-block;
    /*padding: 18px 30px;*/
    /*color: mediumseagreen;*/
    /*font-weight:bold;*/
    color: black;
    font-size:16px;
    text-decoration:none !important;
    line-height:1;
    text-transform: uppercase;
    background-color:transparent;
    margin-right: 20px;

    -webkit-transition:background-color 0.25s;
    -moz-transition:background-color 0.25s;
    transition:background-color 0.25s;
  }

  ul li a:first-child{
    border-radius:2px 0 0 2px;
  }

  ul li a:last-child{
    border-radius:0 2px 2px 0;
    margin-right: 10px;
  }

  .active {
    color: mediumseagreen;
    font-size: medium;
  }

  .el-dropdown-link{
    color: mediumseagreen;
    font-size: 18px;
  }

</style>
