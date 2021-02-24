<template>
  <div>
    <el-container class="home-container">
      <!-- 头部 -->
      <el-header>
        <div>
          <img src="../assets/we_logo_1.png" alt="">
          <span class="logo">WE</span>
        </div>
        <nav v-bind:class="active" v-on:click.prevent>
          <!-- 当菜单上的链接被点击时，我们调用了 makeActive 方法, 该方法在 Vue 实例中创建。 -->
          <a href="#" class="home" v-on:click="makeActive('home')">首页</a>
          <a href="#" class="projects" v-on:click="makeActive('projects')">分类</a>
          <a href="#" class="services" v-on:click="makeActive('services')">树洞</a>
          <a href="#" class="contact" v-on:click="makeActive('contact')">自愿者专区</a>
        </nav>

<!--        <el-input placeholder="请输入内容" style="width: 300px">-->
<!--          <el-button slot="append" icon="el-icon-search" ></el-button>-->
<!--        </el-input>-->
        <span style="float:right;padding-top:10px;margin-right:1%">
          <el-dropdown trigger="click">
            <span class="el-dropdown-link">
              user
              <i class="el-icon-caret-bottom el-icon--right" style="color: blue"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="modifyPwd">修改密码</el-dropdown-item>
              <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </span>
        <!--        <el-button type="info" @click="logout">退出</el-button>-->
      </el-header>
      <!-- 主体 -->
      <el-container>
        <el-main>
          <!--路由占位符-->
          <p>{{active}}</p>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
<!--  <div id="main">-->
<!--    <div class="loop">-->
<!--      <span class="logo">-->
<!--        <img src="../assets/we_logo.png" height="50" width="50"/>-->
<!--        <span>“我和你”心理健康公益平台</span>-->
<!--      </span>-->
<!--      <span class="el-icon-user user">-->
<!--        <el-button class="loginOR">登录</el-button>-->
<!--        /<el-button class="loginOR">注册</el-button>-->
<!--      </span>-->
<!--    </div>-->
<!--    <div class="content">-->
<!--      <nav v-bind:class="active" v-on:click.prevent>-->
<!--        &lt;!&ndash; 当菜单上的链接被点击时，我们调用了 makeActive 方法, 该方法在 Vue 实例中创建。 &ndash;&gt;-->
<!--        <a href="#" class="home" v-on:click="makeActive('home')">Home</a>-->
<!--        <a href="#" class="projects" v-on:click="makeActive('projects')">Projects</a>-->
<!--        <a href="#" class="services" v-on:click="makeActive('services')">Services</a>-->
<!--        <a href="#" class="contact" v-on:click="makeActive('contact')">Contact</a>-->
<!--      </nav>-->
<!--      &lt;!&ndash; 激活的菜单样式为  active 类 &ndash;&gt;-->
<!--      &lt;!&ndash; 为了阻止链接在点击时跳转，我们使用了 "prevent" 修饰符 (preventDefault 的简称)。 &ndash;&gt;-->

<!--      &lt;!&ndash; 以下 "active" 变量会根据当前选中的值来自动变换 &ndash;&gt;-->
<!--      <p>您选择了 <b>{{active}} 菜单</b></p>-->
<!--    </div>-->
<!--  </div>-->
</template>

<script>
    export default {
      name: 'UserMain',
      data () {
        return {
          active: 'home'
        }
      },
      // 点击菜单使用的函数
      methods: {
        makeActive: function(item){
          // switch (item) {
          //   case 'home':
          //     this.active = this.$router.push('/ModifyPwd');
          //     break;
          //   case 'projects':
          //     break;
          //
          // }
          // 模型改变，视图会自动更新
          this.active = item;
        },
        logout() {
          // // 清空token
          // window.sessionStorage.clear('token');
          // 跳转到登录页
          this.$router.push('/');
        },
        modifyPwd(){
          // this.$router.push('/modifyPwd');
          this.$router.push("/AdminMain");
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
    color: mediumseagreen;
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
    font-size: x-large;
  }



  /**{*/
  /*  margin:0;*/
  /*  padding:0;*/
  /*}*/

  /*body{*/
  /*  font:15px/1.3 'Open Sans', sans-serif;*/
  /*  color: #5e5b64;*/
  /*  text-align:center;*/
  /*}*/

  /*.logo{*/
  /*  float: left;*/
  /*  margin-left: 100px;*/
  /*  font-size: 16px;*/
  /*  margin-bottom: 10px;*/
  /*}*/

  /*.search{*/
  /*  float: left;*/
  /*  width: 350px;*/
  /*  margin: 20px auto auto 200px;*/
  /*  border-radius: 5px;*/
  /*  -webkit-border-radius: 5px;*/
  /*  -moz-border-radius: 5px;*/
  /*  box-shadow: 0 0 5px #909399;*/
  /*}*/

  /*.user{*/
  /*  float: right;*/
  /*  margin-right: 150px;*/
  /*  font-size: 17px;*/
  /*  margin-top: 35px;*/
  /*}*/

  /*.loginOR{*/
  /*  margin-left: 7px;*/
  /*}*/

  /*!*.content{*!*/
  /*!*  margin-top: 20px;*!*/
  /*!*}*!*/

  /*a, a:visited {*/
  /*  outline:none;*/
  /*  color: black;*/
  /*}*/

  /*a:hover{*/
  /*  text-decoration:none;*/
  /*}*/

  /*section, footer, header, aside, nav{*/
  /*  display: block;*/
  /*}*/

  /*.content{*/
  /*  margin-top: 40px;*/
  /*}*/

  nav{
    /*display:inline-block;*/
    /*margin:0 auto;*/
    /*background-color: silver;*/
    /*box-shadow:0 1px 1px #ccc;*/
    border-radius:2px;
  }

  nav a{
    display:inline-block;
    /*padding: 18px 30px;*/
    color:#000;
    /*font-weight:bold;*/
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

  nav a:first-child{
    border-radius:2px 0 0 2px;
  }

  nav a:last-child{
    border-radius:0 2px 2px 0;
    margin-right: 100px;
  }

  nav.home .home,
  nav.projects .projects,
  nav.services .services,
  nav.contact .contact{
    color: mediumseagreen;
    font-size: medium;
  }

  .el-dropdown-link{
    color: mediumseagreen;
    font-size: 18px;
  }

  /*p b{*/
  /*  color:#ffffff;*/
  /*  display:inline-block;*/
  /*  padding:5px 10px;*/
  /*  background-color: mediumseagreen;*/
  /*  border-radius:2px;*/
  /*  text-transform:uppercase;*/
  /*  font-size:18px;*/
  /*}*/
</style>
