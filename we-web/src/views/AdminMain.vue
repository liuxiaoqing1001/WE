<template>

  <div>
    <el-container class="home-container">
      <!-- 头部 -->
      <el-header>
        <div>
          <img src="../assets/we_logo_black.png" alt="">
          <span>“我和你”心理健康公益平台</span>
        </div>
        <el-button type="info" @click="logout">退出</el-button>
      </el-header>
      <!-- 主体 -->
      <el-container>
        <!-- 侧边栏 -->
        <el-aside :width="isCollapse ? '64px' : '200px'">
          <div class="toggle-button" @click="toggleCollapse">...</div>

<!--          @open="handleOpen"-->
<!--          @close="handleClose"-->
<!--            545c64-->
<!--          unique-opened属性：  表示是否只保持一个子菜单的展开  默认为false-->
<!--          collapse属性： 表示是否水平折叠收起菜单（仅在 mode 为 vertical 时可用）  默认为false-->
<!--          collapse-transition属性： 表示是否开启折叠动画   默认是true开启状态-->
<!--          router属性： 表示是否使用 vue-router 的模式，启用该模式会在激活导航时以 index 作为 path 进行路由跳转   默认为false-->
          <el-menu
            default-active="1-1"
            class="el-menu-vertical-demo"

            background-color="#333744"
            text-color="#fff"
            active-text-color="#ffd04b"
            :unique-opened="true"
            :collapse="isCollapse"
            :collapse-transition="false"
            :router="true">
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-user"></i>
                <span>用户管理</span>
              </template>
              <el-menu-item-group>
                <template slot="title">普通用户管理</template>
                <el-menu-item index="1-1">用户列表</el-menu-item>
                <el-menu-item index="1-2">添加用户</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="自愿者管理">
                <el-menu-item index="1-3">自愿者审核</el-menu-item>
              </el-menu-item-group>
              <el-submenu index="1-4">
                <template slot="title">管理员设置</template>
                <el-menu-item index="1-4-1">管理员列表</el-menu-item>
              </el-submenu>
            </el-submenu>
            <el-menu-item index="2">
              <i class="el-icon-menu"></i>
              <span slot="title">模块管理</span>
            </el-menu-item>
            <el-menu-item index="3" >
<!--              disabled-->
              <i class="el-icon-document"></i>
              <span slot="title">数据统计</span>
            </el-menu-item>
            <el-menu-item index="4">
              <i class="el-icon-setting"></i>
              <span slot="title">系统设置</span>
            </el-menu-item>
          </el-menu>

<!--          <el-menu background-color="#333744" text-color="#fff" active-text-color="#ffd04b">-->
<!--            &lt;!&ndash; 一级菜单 &ndash;&gt;-->
<!--            <el-submenu index="1">-->
<!--              &lt;!&ndash; 一级菜单模板区域 &ndash;&gt;-->
<!--              <template slot="title">-->
<!--                <i class="el-icon-location"></i>-->
<!--                <span>导航一</span>-->
<!--              </template>-->
<!--              &lt;!&ndash; 二级菜单&ndash;&gt;-->
<!--              <el-menu-item index="1-1">-->
<!--                <template slot="title">-->
<!--                  <i class="el-icon-location"></i>-->
<!--                  <span>选项1</span>-->
<!--                </template>-->
<!--              </el-menu-item>-->
<!--            </el-submenu>-->
<!--          </el-menu>-->
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
        isCollapse: false
      }
    },
    methods: {
      logout() {
        // // 清空token
        // window.sessionStorage.clear('token');
        // 跳转到登录页
        this.$router.push('/')
      },
      // 点击切换左侧菜单的折叠与展开
      toggleCollapse() {
        this.isCollapse = !this.isCollapse
      }
      // async getMenuList() {
      //   const { data: res } = await this.$http.get('menus')
      //   console.log(res)
      //   if (res.meta.status !== 200) return this.$message.error(res.meta.msg)
      //   this.menuList = res.data
      // }
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
