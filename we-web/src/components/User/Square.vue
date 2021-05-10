<template>
  <div >
    <div class="container">
      <transition name="fade">
        <aside class="left-wrapper">
          <div class="left-wrapper-fixed">
            <div class="menu-title">
              <img src="../../assets/logo/square_logo.png" height="200" width="200"/>
              <p class="tips">放飞心灵...</p>
              <p class="tips">...快乐自我</p>
            </div>
            <div class="menu_medium">
              <ul>
                <p class="tips_ul">让心灵沐浴阳光，让快乐充溢胸膛。</p>
                <li class="leftNav" v-for="li_item in li_links" @click="changeMenu(li_item.id)" >
                  <a :class="{active:menuIndex===li_item.id}" >{{li_item.name}}</a>
                </li>
              </ul>
            </div>
            <div class="menu-bottom">
              liuXiaoQing © 2020-2021<br/>
              Sign by <a href="">vue</a>  and <a href="">springBoot</a>
            </div>
          </div>
        </aside>
      </transition>
      <div class="main-wrapper">
<!--        <router-view  v-if="isRouterAlive"></router-view>-->
<!--        <router-view></router-view>-->

        <div class="msg">
          <div class="index-wrapper">
            <ul>
              <li class="blog-wrapper" v-for="item in articleList" :key="item.id" v-on:click="toDetail(item.id)">
                <p class="blog-sender">{{item.senderName}}</p>
                <p class="blog-sendDate">{{item.sendDate}}</p>
                <h2 class="blog-title">{{item.title}}</h2>
                <div class="blog-tag">
                  <ul>
                    <li>{{item.type}}</li>
                  </ul>
                </div>
              </li>
            </ul>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
    export default {
      name: "Square",
      // provide (){
      //   return {
      //     reload:this.reload
      //   }
      // },
      data () {
        return {
          // isRouterAlive:true,
          //当前选中菜单
          menuIndex: '1',
          li_links: [],
          articleList:[]
        }
      },
      mounted () {
        // 触发监听窗口滚动事件
        window.addEventListener('scroll', this.isScroll);
      },
      created() {
        this.initLi_links();
        // this.toSwitch(1);
        this.getArticleList(this.menuIndex);
      },
      methods:{
        // reload (){
        //   this.isRouterAlive = false;
        //   this.$nextTick(function(){
        //     this.isRouterAlive = true;
        //   })
        // },
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
        initLi_links(){
          this.$http.get("/type/getAll").then(response => {
            if (response.data.errorCode===0){
              this.li_links = response.data.data;
            }else {
              this.$message.error(response.data.msg);
            }
          });
        },
        changeMenu: function(item){
          // 模型改变，视图会自动更新
          this.menuIndex = item;
          this.getArticleList(this.menuIndex);
          // this.toSwitch(item);
        },
        // toSwitch(id){
        //   window.sessionStorage.setItem('tid',id);
        //   this.$router.push('/Article');
        //   // this.$router.go('/Article');
        // },

        toDetail(id){
          // this.$router.push({
          //   path: 'Detail',
          //   query: {
          //     id:id
          //   }
          // })
          window.open("/#/Detail?id="+id,"_blank");
        },
        getArticleList(id){
          this.$http.get("/article/getArticleByType",{
            params:{
              id:id
            }
          }).then(response => {
            if (response.data.errorCode===0){
              this.articleList = response.data.data;
            }else {
              this.$message.error(response.data.msg);
            }
          });
        }
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
  /*flex属性默认是0 1 auto */
  /* flex-grow为0，则存在剩余空间也不放大*/
  /* flex-shrink为1，则空间不足该项目缩小*/
  /* flex-basis为auto，则该项目本来的大小*/
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
    width: 1100px;
    margin-right: 100px;
    /*margin-top: 60px;*/
    /*width: 800px;*/
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
  .menu_medium{
    margin-top: 20px;
    padding: 20px 0;
  }
  .menu-bottom{
    font-size: smaller;
    color: #909399;
    margin-top: 200px;
    /*bottom:0px;*/
    /*margin:0px;*/
    /*float:left;*/
    /*width:100%;*/
  }

  .menu-bottom a{
    color: blue;
  }

  .leftNav{
    display: inline-block;
    margin-right: 10px;
    margin-top: 20px;
  }

  .menu_medium ul{
    margin-left: 10px;
    margin-top: 100px;
  }

  .menu_medium ul li a:hover{
    cursor: pointer;
    color: blue;
    font-size: medium;
  }

  .menu_medium ul li a{
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

  .menu_medium ul li a:first-child{
    border-radius:2px 0 0 2px;
  }

  .menu_medium ul li a:last-child{
    border-radius:0 2px 2px 0;
    margin-right: 10px;
  }

  .active {
    color: mediumseagreen;
    font-size: medium;
  }

  .msg{
    width: 1100px;
    margin-right: 100px;
    margin-top: 88px;
  }
  .explain{
    margin-top: 100px;
  }
  .index-wrapper{
    max-width: 960px;
    margin: 30px auto 40px;
  }
  .blog-wrapper{
    margin-bottom: 30px;
    padding: 12px 12px 0;
    background: #fff;
    border-radius: 3px;
    text-align: left;
    list-style: none;
    box-shadow: 0 1px 2px rgba(151,151,151,0.58);
  }
  .blog-sender,.blog-sendDate{
    line-height: 24px;
    margin: 0 0 0px;
    font-size: 13px;
    font-weight: bold;
    color: #727272;
    overflow: hidden;
  }
  .blog-title{
    margin-bottom: 10px;
    font-size: 24px;
    line-height: 32px;
    color: #3f51b5;
    border-bottom: 1px solid #ddd;
    padding-bottom: 15px;
  }
  .blog-content{
    word-break: break-all;
    padding-bottom: 20px;
    line-height: 1.8;
  }
  .blog-more{
    display: inline-block;
    padding: 0 6px;
    font-weight: 500;
    color: #3f51b5 !important;
    border: none !important;
    border-radius: 3px;
  }
  .blog-tag{
    position: relative;
    margin-left: 80%;
    /*padding-top: 8px;*/
    /*margin: 0 -12px;*/
    /*float: right;*/
    /*padding-bottom: 10px;*/
    /*border-top: 1px solid #ddd;*/
  }

  .blog-tag li{
    display: inline-block;
    margin: 0 8px 8px 0;
    border-radius: 2px;
    background: mediumseagreen;
    padding: 0 16px;
    line-height: 28px;
    color: white;
  }
  li:hover{
    cursor: pointer;
  }
</style>
