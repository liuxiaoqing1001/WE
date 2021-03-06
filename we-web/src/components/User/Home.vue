<template>
  <div>
    <el-container class="home-container">
      <!-- 主体 -->
      <el-main>
        <div class="banner">
          <div class="item">
            <img :src="dataList[currentIndex]">
          </div>
          <div class="page" v-if="this.dataList.length > 1">
            <ul>
              <li @click="gotoPage(prevIndex)">&lt;</li>
              <li v-for="(item,index) in dataList" @click="gotoPage(index)" :class="{'current':currentIndex === index}">{{index+1}}</li>
              <li @click="gotoPage(nextIndex)">&gt;</li>
            </ul>
          </div>
        </div>
        <div class="banner_theme">
          <div class="item_theme">
            <img src="../../assets/1.jpeg" height="640" width="1024"/>
          </div>
        </div>
        <div class="banner_theme">
          <div class="item_theme">
            <img src="../../assets/1.jpeg" height="640" width="1024"/>
          </div>
        </div>
        <div class="banner_theme">
          <div class="item_theme">
            <img src="../../assets/1.jpeg" height="640" width="1024"/>
          </div>
        </div>
        <div class="banner_theme">
          <div class="item_theme">
            <img src="../../assets/1.jpeg" height="640" width="1024"/>
          </div>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
    export default {
      name: "Home",
      data() {
        return{
          dataList:[
            'https://i1.mifile.cn/a4/xmad_15535933141925_ulkYv.jpg',
            'https://i1.mifile.cn/a4/xmad_15532384207972_iJXSx.jpg',
            'https://i1.mifile.cn/a4/xmad_15517939170939_oiXCK.jpg'
          ],
          currentIndex: 0,   //默认显示图片
          timer: null    //定时器
        }

      },
      methods: {
        gotoPage(index) {
          this.currentIndex = index;
        }
      },
      computed: {
        //上一张
        prevIndex() {
          if(this.currentIndex === 0) {
            return this.dataList.length - 1;
          }else{
            return this.currentIndex - 1;
          }
        },
        //下一张
        nextIndex() {
          if(this.currentIndex === this.dataList.length - 1) {
            return 0;
          }else {
            return this.currentIndex + 1;
          }
        },
        //定时器
        runInv() {
          this.timer = setInterval(() => {
            this.gotoPage(this.nextIndex)
          }, 1000);
        }
      }
    }
</script>

<style scoped>
  .home-container {
    min-height: 910px;
    height: 100%;
  }

  /*.el-header {*/
  /*  !*color: mediumseagreen;*!*/
  /*  display: flex;*/
  /*  justify-content: space-between;*/
  /*  padding-left: 0;*/
  /*  align-items: center;*/
  /*  font-size: 20px;*/
  /*  margin-top: 50px;*/
  /*}*/

  * {
    margin: 0;
    padding: 0;
  }
  ul li {
    list-style: none;
    float: left;
    width: 30px;
    height: 40px;
    line-height: 40px;
    text-align: center;
    cursor: pointer;
    color: rgba(255,255,255,.8);
    font-size: 14px;
  }
  .banner {
    width: 100%;
    /*margin: 0 auto;*/
    position: relative;
    /*margin-top: 60px;*/
  }
  .banner img {
    width: 100%;
    display: block;
  }
  .banner .page {
    background: rgba(0,0,0,.5);
    position: absolute;
    text-align: center;
    right: 0;
    bottom: 0;
    width: 100%;
  }
  .banner .page ul {
    float: right;
  }
  .current {
    color: #ff6700;
  }
  .banner_theme{
    width: 100%;
    position: relative;
    margin-top: 130px;
  }
  .item_theme{
    width: 100%;
    max-height: 960px;
    background-color: #4a5064;
  }
</style>
