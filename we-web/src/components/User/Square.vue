<template>
  <div >
    <div class="container">
      <transition name="fade">
        <aside class="left-wrapper">
          <div class="left-wrapper-fixed">
            <div class="menu-wrapper">
              <img src="../../assets/square_logo.png" height="200" width="200"/>
              <p class="tips">放飞心灵...</p>
              <p class="tips">...快乐自我</p>
            </div>
            <div class="menu_left">

              <ul>
                <p class="tips_ul">让心灵沐浴阳光，让快乐充溢胸膛。</p>
                <li class="leftNav" v-for="li_item in links" @click="changeMenu(li_item.name)" >
                  <a :class="{menuActive:menuIndex===li_item.name}" v-on:click="$goRoute(li_item.route) ">{{li_item.text}}</a>
                </li>
              </ul>
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
        name: "Square",
      data () {
        return {
          //当前选中菜单
          // active      :'Home',
          menuIndex: 'Article',
          links: [
            {
              text: '心理科普',
              name: 'Article',
              route: '/Article'
            },
            {
              text: '婚恋情感',
              name: 'Article',
              route: '/Article'
            },
            {
              text: '家庭关系',
              name: 'Article',
              route: '/Article'
            },
            {
              text: '个人社交',
              name: 'Article',
              route: '/Article'
            },
            {
              text: '热点',
              name: 'Article',
              route: '/Article'
            },
            {
              text: '心理健康',
              name: 'Article',
              route: '/Article'
            },
            {
              text: '性心理',
              name: 'Article',
              route: '/Article'
            },
            {
              text: '职场技能',
              name: 'Article',
              route: '/Article'
            },
            {
              text: '成长学习',
              name: 'Article',
              route: '/Article'
            }
          ]
        }
      },
      mounted () {
        // 触发监听窗口滚动事件
        window.addEventListener('scroll', this.isScroll);
      },
      methods:{
        // 监听窗口滚动
        isScroll(){
          var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
          if(scrollTop === 0) {
            //滚动条为0
            this.navShadow = false;
          }else {
            //滚动条不为0
            this.navShadow = true;
          }
        },
        // //切换左边菜单栏显示状态
        // asideStatus(){
        //   this.menuStatus = !this.menuStatus;
        //   let self = this;
        //   if(this.menuStatus) {
        //     let interval = setInterval(function () {
        //       self.navPadding +=1;
        //       if(self.navPadding >= 240)
        //       {
        //         self.navPadding = 240;
        //         clearInterval(interval);
        //       }
        //     },0.5)
        //   }else {
        //     let interval = setInterval(function () {
        //       self.navPadding -= 1;
        //       if(self.navPadding <= 0)
        //       {
        //         self.navPadding = 0;
        //         clearInterval(interval);
        //       }
        //     },1)
        //   }
        // },
        // 路由跳转
        location(url){
          this.$router.push({path:url});
        },

        // //单击菜单修改菜单样式
        // changeMenu(active,url){
        //   this.active = active;
        //   this.location(url)
        // },
        changeMenu: function(item){
          // 模型改变，视图会自动更新
          this.menuIndex = item;
        },
      },
    }
</script>

<style scoped>

  *{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }
  html{
    border: #f6f6f6;
  }
  .container{
    display: flex;
    min-height: 100vh;
    width: 600px;
  }
  .left-wrapper{
    flex:  0 0 300px;
    width: 240px;
    box-shadow: 0 2px 25px 0 rgba(0,0,0,0.16), 0 2px 10px 0 rgba(0,0,0,0.12);
    margin-left: 100px;
  }
  .left-wrapper-fixed{
    position: fixed;
    width: 300px;
    height: 100%;
    z-index: 2;
  }
  .main-wrapper{
    flex: 1;
    background: #f6f6f6;
    padding: 0 15px;
    margin-right: 100px;
  }
  .menu-wrapper{
    padding: 80px;
    height: 200px;
    /*background-image: url();*/
    background-position: center;
  }
  .menu-wrapper img{
    margin-top: 40px;
    width: 100px;
    height: 100px;
    border: 2px solid #fff;
    border-radius: 50%;
    overflow: hidden;
    box-shadow: 0 2px 5px 0 rgba(0,0,0,0.16), 0 2px 10px 0 rgba(0,0,0,0.12);
  }
  .tips{
    padding-top: 4px;
    font-size: 14px;
    line-height: 24px;
    color: #42b983;
  }
  .tips_ul{
    padding-top: 4px;
    font-size: 14px;
    line-height: 24px;
    color: #909399;
    margin-bottom: 30px;
  }
  .menu_left{
    margin-top: 20px;
    padding: 20px 0;
  }

  .leftNav{
    display: inline-block;
    margin-right: 10px;
    margin-top: 20px;
  }

  ul{
    margin-left: 10px;
    margin-top: 100px;
  }

  ul li a:hover{
    cursor: pointer;
    color: blue;
    font-size: medium;
  }
  /*.menu_left li{*/
  /*  display: flex;*/
  /*  padding: 0 20px;*/
  /*  height: 44px;*/
  /*  line-height: 44px;*/
  /*  cursor: pointer;*/
  /*}*/

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

  .menuActive {
    color: mediumseagreen;
    /*font-size: medium;*/
  }
</style>
