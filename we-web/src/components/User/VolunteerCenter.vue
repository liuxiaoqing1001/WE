<template>
<!--  默认个人中心，右侧为个人基本信息，点击名称进入个人中心-->
<!--  两个按钮：我的文章，我的病人-->
<!--  我的文章：发布、管理（修改删除）、评论信息-->
<!--  咨询中心：病人列表，点击可看病人信息和病例-->
<!--  当病人开启咨询服务，匹配到你，弹出框，是否接受咨询，是：跳转聊天室（新页面）（一对一咨询）-->

  <div >
    <div class="container">
      <transition name="fade">
        <aside class="left-wrapper">
          <div class="left-wrapper-fixed">
            <div class="menu-title">
<!--              姓名的名字作为头像-->
              <img src="../../assets/logo/square_logo.png" height="200" width="200"/>
              <p class="userName">刘小青</p>
              <p class="level">心理治疗师</p>
            </div>
            <div class="menu_medium">

            </div>
          </div>
        </aside>
      </transition>
      <div class="main-wrapper">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
      name: "VolunteerCenter",
      data () {
        return {
          menuIndex: 'VArticle',
        }
      },
      methods:{
        changeMenu: function(item){
          // 模型改变，视图会自动更新
          this.menuIndex = item;
        },
      }
    }
</script>

<style scoped>
  .container{
    display: flex;
    height: 650px;
    width: 600px;
    float: right;
    margin-top: 50px;
    /*margin-left: 100px;*/
  }
  .left-wrapper{
    flex:  0 0 300px;
    width: 240px;
    box-shadow: 0 2px 25px 0 rgba(0,0,0,0.16), 0 2px 10px 0 rgba(0,0,0,0.12);
    /*margin-right: 100px;*/
  }
  .left-wrapper-fixed{
    position: fixed;
    width: 300px;
    height: 650px;
    z-index: 2;
  }
  .menu-title{
    padding: 80px;
    height: 200px;
    /*background-image: url();*/
    background-position: center;
  }
  .menu-title img{
    margin-top: 40px;
    width: 100px;
    height: 100px;
    border: 2px solid #fff;
    border-radius: 50%;
    overflow: hidden;
    box-shadow: 0 2px 5px 0 rgba(0,0,0,0.16), 0 2px 10px 0 rgba(0,0,0,0.12);
  }
  .level{
    padding-top: 4px;
    font-size: 14px;
    line-height: 24px;
    color: #42b983;
  }
  .menu_medium{
    margin-top: 20px;
    padding: 20px 0;
  }

</style>
