<template>
  <div id="main">
    <div class="loop">
      <span class="logo">
        <img src="../assets/we_logo.png" height="50" width="50"/>
        <span>“我和你”心理健康公益平台</span>
      </span>
<!--      <span class="nav-head">-->
<!--        -->
<!--      </span>-->
      <span class="search">
        <el-input type="text" placeholder="请输入搜索关键字"></el-input>
      </span>
      <span class="el-icon-user user">
        <el-button class="loginOR">登录/注册</el-button>
      </span>
      <div>
        <img src="../assets/1.jpeg" height="450" width="90%"/>
      </div>
    </div>
    <div class="content">
      <nav v-bind:class="active" v-on:click.prevent>
        <!-- 当菜单上的链接被点击时，我们调用了 makeActive 方法, 该方法在 Vue 实例中创建。 -->
        <a href="#" class="home" v-on:click="makeActive('home')">Home</a>
        <a href="#" class="projects" v-on:click="makeActive('projects')">Projects</a>
        <a href="#" class="services" v-on:click="makeActive('services')">Services</a>
        <a href="#" class="contact" v-on:click="makeActive('contact')">Contact</a>
      </nav>
      <!-- 激活的菜单样式为  active 类 -->
      <!-- 为了阻止链接在点击时跳转，我们使用了 "prevent" 修饰符 (preventDefault 的简称)。 -->

      <!-- 以下 "active" 变量会根据当前选中的值来自动变换 -->
      <p>您选择了 <b>{{active}} 菜单</b></p>
    </div>


  </div>
</template>

<script>
    export default {
      name: 'Main',
      data () {
        return {
          active: 'home'
        }
      },
      // 点击菜单使用的函数
      methods: {
        makeActive: function(item){
          // switch (item) {
          //
          // }
          // 模型改变，视图会自动更新
          this.active = item;
        }
      }
    }
</script>

<style scoped>
  *{
    margin:0;
    padding:0;
  }

  body{
    font:15px/1.3 'Open Sans', sans-serif;
    color: #5e5b64;
    text-align:center;
  }

  .loop{
    height: 500px;
  }

  .logo{
    float: left;
    margin-left: 100px;
    font-size: 16px;
    margin-bottom: 10px;
  }

  .search{
    float: left;
    width: 350px;
    margin: 20px auto auto 200px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    box-shadow: 0 0 5px #909399;
  }

  .user{
    float: right;
    margin-right: 150px;
    font-size: 17px;
    margin-top: 35px;
  }

  .loginOR{
    margin-left: 7px;
  }

  /*.content{*/
  /*  margin-top: 20px;*/
  /*}*/

  a, a:visited {
    outline:none;
    color: black;
  }

  a:hover{
    text-decoration:none;
  }

  section, footer, header, aside, nav{
    display: block;
  }

  .content{
    margin-top: 40px;
  }

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

    -webkit-transition:background-color 0.25s;
    -moz-transition:background-color 0.25s;
    transition:background-color 0.25s;
  }

  nav a:first-child{
    border-radius:2px 0 0 2px;
  }

  nav a:last-child{
    border-radius:0 2px 2px 0;
  }

  nav.home .home,
  nav.projects .projects,
  nav.services .services,
  nav.contact .contact{
    color: mediumseagreen;
    /*background-color: mediumseagreen;*/
  }

  p{
    font-size:22px;
    font-weight:bold;
    color:#7d9098;
  }

  p b{
    color:#ffffff;
    display:inline-block;
    padding:5px 10px;
    background-color: mediumseagreen;
    border-radius:2px;
    text-transform:uppercase;
    font-size:18px;
  }
</style>
