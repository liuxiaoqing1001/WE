<template>
<!--  默认个人中心，右侧为个人基本信息，点击名称进入个人中心-->
<!--  两个按钮：我的文章，我的病人-->
<!--  我的文章：发布、管理（修改删除）、评论信息-->
<!--  咨询中心：病人列表，点击可看病人信息和病例-->
<!--  当病人开启咨询服务，匹配到你，弹出框，是否接受咨询，是：跳转聊天室（新页面）（一对一咨询）-->

  <div >
    <div class="container">
      <div class="main-wrapper">
        <router-view></router-view>
      </div>
      <transition name="fade">
        <aside class="right-wrapper">
          <div class="right-wrapper-fixed">
            <div class="menu-title">
<!--              姓名的名字作为头像-->
              <img src="../../assets/logo/we_logo.png" @click="intoC()" height="200" width="200"/><br>
              <a class="userName" @click="intoC()">{{userName}}</a>
              <p class="level">{{level}}</p>
            </div>
            <div class="menu_medium">
              <button @click="toArticle()" type="button" class="btn_article">我的文章</button>
              <button @click="toConsulting()" type="button" class="btn_consulting">咨询中心</button>
            </div>
            <div class="menu_bottom">
              <a class="comeBack" @click="comeBack()">返回用户平台</a>
              <a class="comeBack" @click="quit()">退出登录</a>
            </div>
          </div>
        </aside>
      </transition>
    </div>
  </div>
</template>

<script>
    export default {
      name: "VolunteerCenter",
      data () {
        return {
          router: '/VCenter',
          userName: 'user',
          level: '心理治疗师'
        }
      },
      methods:{
        toArticle:function(){
          this.$router.push("/VArticle");
          this.$message.success('文章');
        },
        toConsulting:function () {
          this.$router.push("/VConsulting");
          this.$message.success('咨询');
        },
        intoC:function () {
          this.$router.push("/VCenter");
          this.$message.success('个人中心');
        },
        comeBack:function () {
          this.$router.push("/Home");
          this.$message.success('返回');
        },
        quit:function () {
          this.$router.push("/");
          this.$message.success('已注销');
        }
      }
    }
</script>

<style scoped>
  .container{
    display: flex;
    min-height: 92vh;
    margin-top: 50px;
  }
  .main-wrapper{
    flex:  0 1 55%;
    margin-left: 200px;
    box-shadow: 0 2px 25px 0 rgba(0,0,0,0.16), 0 2px 10px 0 rgba(0,0,0,0.12);
    margin-right: 100px;
  }
  .right-wrapper{
    flex:  0 1 18%;
    width: 240px;
    height: 600px;
    box-shadow: 0 2px 25px 0 rgba(0,0,0,0.16), 0 2px 10px 0 rgba(0,0,0,0.12);
    margin-right: 200px;
    text-align: center;
  }
  .menu-title{
    margin-bottom: 50px;
  }
  .menu-title img{
    margin-top: 60px;
    margin-bottom: 20px;
    width: 100px;
    height: 100px;
    border: 2px solid #fff;
    border-radius: 50%;
    overflow: hidden;
    box-shadow: 0 2px 5px 0 rgba(0,0,0,0.16), 0 2px 10px 0 rgba(0,0,0,0.12);
  }
  a,img:hover{
    cursor: pointer;
  }
  .level{
    padding-top: 4px;
    font-size: 14px;
    line-height: 24px;
    color: #42b983;
  }
  .comeBack{
    font-size: 15px;
    color: #3f51b5;
    margin-left: 15px;
    margin-right: 15px;
  }
  .menu_bottom{
    margin-top: 50%;
  }
  .btn_article,.btn_consulting{
    display: inline-block;
    overflow: hidden;
    position: relative;
    border-radius: 10px;
    height: 40px;
    line-height: 36px;
    min-width: 90px;
    transition-duration: .3s;
    transition-timing-function: cubic-bezier(.23,1,.32,1);
    -webkit-transform: translateZ(0);
    transform: translateZ(0);
    text-decoration: none;
    text-transform: uppercase;
    border: none;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    /*background-color: #fff;*/
    /*color: rgba(0,0,0,.87);*/
    background-color: blue;
    color: white;
    -webkit-box-flex: 0;
    -webkit-flex-shrink: 0;
    -ms-flex: 0 0 auto;
    -ms-flex-negative: 0;
    flex-shrink: 0;
    /*margin: 0;*/
    margin-left: 15px;
    margin-right: 15px;
    margin-bottom: 15px;
    outline: 0;
    padding: 0;
    cursor: pointer;
    box-shadow: 0 1px 6px rgba(0,0,0,.117647), 0 1px 4px rgba(0,0,0,.117647);
  }
</style>
