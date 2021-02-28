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
          <a href="#" class="sort" v-on:click="makeActive('sort')">分类</a>
          <a href="#" class="treeHole" v-on:click="makeActive('treeHole')">树洞</a>
          <a href="#" class="chatRoom" v-on:click="makeActive('chatRoom')">咨询中心</a>
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
<!--          &lt;!&ndash;路由占位符&ndash;&gt;-->
<!--          <p>{{active}}</p>-->
          <div :is='myComponent'/>
<!--          <router-view></router-view>-->
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import chatRoom from "../components/User/ChatRoom";
  import sort from "../components/User/Sort";
  import treeHole from "../components/User/TreeHole";
    export default {
      name: 'UserMain',
      components:{
        sort,
        treeHole
      },
      data () {
        return {
          active: 'home',
          myComponent: 'treeHole'
        }
      },

      // 点击菜单使用的函数
      methods: {
        makeActive: function(item){
          // switch (item) {
          //   case 'home':
          //
          //     // this.active = this.$router.push('/ModifyPwd');
          //     break;
          //   case 'sort':
          //     break;
          //   case 'treeHole':
          //     break;
          //   case 'chatRoom':
          //     break;
          // }
          // 模型改变，视图会自动更新
          this.active = item;
          this.myComponent = item;

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
  nav.sort .sort,
  nav.treeHole .treeHole,
  nav.chatRoom .chatRoom{
    color: mediumseagreen;
    font-size: medium;
  }

  .el-dropdown-link{
    color: mediumseagreen;
    font-size: 18px;
  }

</style>
