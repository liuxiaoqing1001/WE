<template>

  <div>
    <el-container class="home-container">
      <!-- 头部 -->
      <el-header>
        <div>
          <img src="../assets/logo/we_logo_1.png" alt="">
          <span>“我和你”心理健康公益平台<span style="font-size: 10px">·管理员后台·</span></span>
        </div>
<!--        <span style="float:right;padding:5px;margin-left:2%;width:20%">-->
<!--          <el-input placeholder="请输入关键字" v-model="searchCriteria" @click="handleIconClick"></el-input>-->
<!--        </span>-->
        <span style="float:right;padding-top:10px;margin-right:1%">
          <el-dropdown trigger="click">
            <span class="el-dropdown-link" style="color:white;font-size: 18px">
              admin
              <i class="el-icon-caret-bottom el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="modifyPwd">修改密码</el-dropdown-item>
              <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </span>
      </el-header>
      <!-- 主体 -->
      <el-container>
        <!-- 侧边栏 -->
        <el-aside :width="isCollapse ? '64px' : '180px'">
          <div class="toggle-button" @click="toggleCollapse">...</div>

<!--          @open="handleOpen"-->
<!--          @close="handleClose"-->
<!--          unique-opened属性：  表示是否只保持一个子菜单的展开  默认为false-->
<!--          collapse属性： 表示是否水平折叠收起菜单（仅在 mode 为 vertical 时可用）  默认为false-->
<!--          collapse-transition属性： 表示是否开启折叠动画   默认是true开启状态-->
<!--          router属性： 表示是否使用 vue-router 的模式，启用该模式会在激活导航时以 index 作为 path 进行路由跳转   默认为false-->
<!--          default-active：绑定$route.path的值,刷新页面不改变当前导航路径-->
          <el-menu
            default-active="1-1"
            background-color="#333744"
            text-color="#fff"
            active-text-color="#ffd04b"
            :unique-opened="true"
            :collapse="isCollapse"
            :collapse-transition="false"
            :router="true"
            :default-active="$route.path">
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-user"></i>
                <span>用户管理</span>
              </template>
              <el-menu-item index="/User" >
                <template slot="title">
                  <span class="secondNav">普通用户</span>
                </template>
              </el-menu-item>
              <el-menu-item index="/Volunteer" >
                <template slot="title">
                  <span class="secondNav">自愿者</span>
                </template>
              </el-menu-item>
              <el-menu-item index="/Admin" >
                <template slot="title">
                  <span class="secondNav">管理员设置</span>
                </template>
              </el-menu-item>
            </el-submenu>
            <el-menu-item index="/Model" >
              <i class="el-icon-menu"></i>
              <span slot="title">模块管理</span>
            </el-menu-item>
            <el-menu-item index="/Data" >
              <i class="el-icon-document"></i>
              <span slot="title">数据统计</span>
            </el-menu-item>
            <el-submenu index="4">
              <template slot="title">
                <i class="el-icon-setting"></i>
                <span>系统设置</span>
              </template>
              <el-menu-item index="/Setting" >
                <template slot="title">
                  <span class="secondNav">xxx</span>
                </template>
              </el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <!-- 右侧内容主体 -->
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
    name: "AdminMain",
    data(){
      return{
        // 菜单是否折叠
        isCollapse: false,
        searchCriteria: '',
        activePath:''
      }
    },
    methods: {
      logout() {
        // 清空token
        window.sessionStorage.clear();
        // 跳转到登录页
        this.$router.push('/');
      },
      modifyPwd(){
        this.$router.push('/modifyPwd');
      },
      // 点击切换左侧菜单的折叠与展开
      toggleCollapse() {
        this.isCollapse = !this.isCollapse
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
    background: #373d41;
    color: #fff;
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

  .secondNav{
    float: right;
    margin-right: 3px;
  }

  .toggle-button {
    /*float: right;*/
    background: #4a5064;
    color:#fff;
    font-size: 18px;
    font-weight: bold;
    line-height:24px;
    text-align: right;
    text-indent: 0.5em;
    letter-spacing: 0.5em;
    cursor: pointer;
  }

  .el-aside {
    background: #333744;
  }

  .el-main {
    background: #eaedf1;
  }
</style>
