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
              <li v-for="(item,index) in dataList" @click="gotoPage(index)" >
                <span class="indexDot" :class="{'current':currentIndex === index}"/>
              </li>
            </ul>
          </div>
          <div class="page1" v-if="this.dataList.length > 1">
            <img src="../../assets/iconfont/left.png" @click="gotoPage(prevIndex)"/>
          </div>
          <div class="page2" v-if="this.dataList.length > 1">
            <img src="../../assets/iconfont/right.png" @click="gotoPage(nextIndex)"/>
          </div>
        </div>
        <div class="banner_theme">
          <div class="item_theme">
            <img src="../../assets/image/1.jpeg" height="640" width="1024"/>
          </div>
        </div>
        <div class="banner_theme">
          <div class="item_theme">
            <img src="https://img.zcool.cn/community/010d1d57620dde0000012e7e2aa1e7.jpg@3000w_1l_0o_100sh.jpg"
                 height="100%" width="100%" />
            <div class="tip">
              置身于这个宇宙中，也许星星懂，将它视作树洞，倾诉心声，放心，没人知道你是谁
            </div>
            <a class="intoTH" @click="intoTH()">>>>前往树洞</a>
          </div>
        </div>
        <div class="banner_theme">
          <div class="item_theme">
            <img src="../../assets/image/1.jpeg" height="640" width="1024"/>
          </div>
        </div>
        <div class="banner_theme">
          <div class="item_theme">
            <img src="../../assets/background/background.jpeg" height="700" width="80%" style="float: right"/>
            <div class="tipV">
              诚邀您加入平台自愿者咨询师
            </div>
            <a class="intoV" @click="intoV()">>>>进入专区</a>
          </div>
        </div>
      </el-main>
      <el-footer>
        liuXiaoQing © 2020-2021
        Sign by <a href="">vue</a>  and <a href="">springBoot</a>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
  export default {
    name: "Home",
    data() {
      return{
        dataList:[
          'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3744317613,3330554315&fm=15&gp=0.jpg',
          'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F011e485783aa700000018c1be22d29.jpg&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1619969651&t=32b17ba6792e5657e8a0f14615b578e2',
          'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsryhxl.com%2Fuploadsabcd%2F191024%2F1-1910242054134c.jpg&refer=http%3A%2F%2Fsryhxl.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1619969096&t=7fbfc060937fe015e29c27f998d0a3d5',
          'https://ossimg.xinli001.com/20191028/69c90fc8584f568365521ee029cdbade.png?x-oss-process=image/quality,Q_80'
        ],
        // http://www.nxwcmedia.com/?p=29644
        currentIndex: 0,   //默认显示图片
        timer: null    //定时器
      }

    },
    //定时器
    mounted() {
      this.timer = setInterval(() => {
        this.gotoPage(this.nextIndex)
      }, 3000);
    },
    methods: {
      gotoPage(index) {
        this.currentIndex = index;
      },
      intoTH:function () {
        this.$router.push("/TreeHole");
      },
      intoV:function () {
        this.$router.push("/VolunteerZone");
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
    }
  }
</script>

<style scoped>
  .home-container {
    min-height: 910px;
    height: 100%;
  }
  .el-footer {
    background-color: silver;
    color: white;
    text-align: center;
    line-height: 60px;
    margin-top: 50px;
  }

  .el-footer a{
    color: blue;
  }

  * {
    margin: 0;
    padding: 0;
  }
  ul li {
    list-style: none;
    /*float: left;*/
    width: 30px;
    height: 40px;
    line-height: 40px;
    text-align: center;
    cursor: pointer;
    color: rgba(255,255,255,.8);
    font-size: 14px;
    margin-left: auto;
    display: inline;
    margin-right: auto;
  }
  .banner {
    width: 100%;
    position: relative;
  }
  .banner img {
    width: 100%;
    height: 700px;
    display: block;
  }
  .banner .page {
    /*background: rgba(0,0,0,.5);*/
    position: absolute;
    text-align: center;
    right: 0;
    bottom: 0;
    width: 100%;
  }
  .banner .page1{
    position: absolute;
    text-align: center;
    font-size: 100px;
    color: silver;
    left: 50px;
    bottom: 50%;
    width: 60px;
  }
  .banner .page2{
    position: absolute;
    text-align: center;
    font-size: 100px;
    color: silver;
    right: 50px;
    bottom: 50%;
    width: 60px;
  }
  .page1 img,.page2 img{
    height: 100px;
  }
  .banner .page ul {
    /*transform:rotate(-180deg);*/
  }
  .indexDot{
    display: inline-block;
    width: 14px;
    height: 14px;
    border-radius: 100%;
    background: #ccc;
    margin: 0 10px;
  }
  .current {
    background: #ff6700;
  }

  .banner_theme{
    width: 100%;
    position: relative;
    margin-top: 130px;
  }
  .item_theme{
    width: 100%;
    max-height: 960px;
    background-color: black;
    overflow: hidden;
  }
  .tip{
    color: white;
    font-size: 18px;
    text-align: left;
    position: absolute;
    left: 20%;
    top: 40%;
  }
  .tipV{
    color: white;
    float: left;
    font-size: 30px;
    position: absolute;
    left: 8%;
    top: 18%;
    writing-mode: vertical-lr;
  }
  .intoTH{
    color: gold;
    font-size: 25px;
    font-weight: bold;
    position: absolute;
    left: 60%;
    top: 70%;
  }
  .intoV{
    color: white;
    font-size: 25px;
    font-weight: bold;
    position: absolute;
    left: 5%;
    bottom: 8%;
  }
  a:hover{
    cursor: pointer;
  }
</style>
