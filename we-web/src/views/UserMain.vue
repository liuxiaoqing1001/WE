<template>
  <div>
    <el-container class="userMainContainer">
      <!-- 头部 -->
      <el-header class="navHeader">
        <div>
          <img src="../assets/logo/we_logo_1.png" alt="">
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
              {{user}}
              <i class="el-icon-caret-bottom el-icon--right" style="color: blue"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="personalCenter">个人中心</el-dropdown-item>
              <el-dropdown-item @click.native="myMsg">我的消息</el-dropdown-item>
              <el-dropdown-item @click.native="modifyPwd">修改密码</el-dropdown-item>
              <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </span>
      </el-header>
      <!-- 主体 -->
      <el-container class="mainContainer" >
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
          user:window.sessionStorage.getItem("token"),
          activeIndex: 'Home',
          links: []
        }
      },

      created() {
        this.initLi_links();
      },

      // 点击菜单使用的函数
      methods: {
        initLi_links(){
          this.$http.get("/module/getAll").then(response => {
            if (response.data.errorCode===0){
              this.links = response.data.data;
            }else {
              this.$message.error(response.data.msg);
            }
          });
        },
        makeActive: function(item){
          // 模型改变，视图会自动更新
          this.activeIndex = item;
        },
        logout() {
          // 清空token
          window.sessionStorage.clear();
          // 跳转到登录页
          this.$router.push('/');
        },
        personalCenter(){
          this.$router.push("/PersonalCenter");
        },
        myMsg(){
          this.$router.push("/MyMsg");
        },
        modifyPwd(){
          this.$router.push('/ModifyPwd');
        }
      }
    }
</script>

<style scoped>

  .userMainContainer {
    min-height: 910px;
    height: 100%;
    width: 100%;
    overflow: hidden;
  }

  .mainContainer{

  }

  .navHeader{
    height: 56px;
    background: beige;
    color: #fff;
    line-height: 56px;
    position: fixed;
    /*top: 0;*/
    z-index: 1;
    width: 100%;
    left: 0;
    margin-top: 10px;
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
