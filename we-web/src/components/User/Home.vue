<template>
  <div>
    <el-container class="home-container">
      <!-- 主体 -->
      <el-main>
        <div class="banner">
          <div class="item">
            <img :src="dataList[currentIndex]" @click="go(currentIndex)"/>
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
        <hr class="hr_style1">
        <div class="banner_theme">
          <div class="item_theme">
            <div class="DetailsContent">
              <div class="poster_dv">
                <swiper ref="mySwiper" :options="swiperOption" v-if="posterObj.length > 0">
                  <swiper-slide class="poster_item" v-for="(item, index) in posterObj" :key="index">
                    <img :src="item.imgUrl" alt="" @click="goArticle(index)"/>
                  </swiper-slide>
                </swiper>
                <!--以下看需要添加-->
                <!--      <div class="swiper-pagination"></div>-->
                <!-- 下一页 -->
                <div class="swiper-button-next"></div>
                <!-- 上一页 -->
                <div class="swiper-button-prev"></div>
              </div>
            </div>

          </div>
        </div>
        <hr class="hr_style2">
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
        <hr class="hr_style3">
        <div class="banner_theme">
          <div class="item_theme">
            <video id="myVideo" class="video-js" loop="loop">
              <source  style="margin: 10px 10% 10px 10%;"
                src="../../../static/video/ineed.mp4"
                type="video/mp4">
            </video>
            <p class="tip_video">
              <span class="span_need"  @click="intoChat()">I NEED TO GO >>>  </span><br><br>
              我需要食物，我需要一张床，我需要一所房子，我需要一辆车，我需要拥抱，我需要爱情，我需要一个家，我需要停下来，我...需要你。
              <br><br>
            </p>
          </div>
        </div>
        <hr class="hr_style4">
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
  import { Swiper, SwiperSlide } from "vue-awesome-swiper";
  import "swiper/dist/css/swiper.css";

  export default {
    components: {
      Swiper,
      SwiperSlide,
    },
    name: "Home",
    data() {
      let that = this;
      return{
        dataList:[
          'https://ossimg.xinli001.com/20210331/8b9bb5fa1668eabcb49fc99e06383c40.jpeg?x-oss-process=image/quality,Q_80',
          // 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F011e485783aa700000018c1be22d29.jpg&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1619969651&t=32b17ba6792e5657e8a0f14615b578e2',
          // 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsryhxl.com%2Fuploadsabcd%2F191024%2F1-1910242054134c.jpg&refer=http%3A%2F%2Fsryhxl.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1619969096&t=7fbfc060937fe015e29c27f998d0a3d5',
          'https://ossimg.xinli001.com/20210326/18012ce2485d8729be81f5c989c9c05d.jpeg?x-oss-process=image/quality,Q_80',
          'https://ossimg.xinli001.com/20210402/b5c744482c660101988e4020e5c0a8ee.jpeg?x-oss-process=image/quality,Q_80',
        ],
        // https://www.xinli001.com/info/100472191 成长
        // https://www.xinli001.com/info/100472544?source=pc-home 抑郁症
        //https://www.xinli001.com/info/100000260 遇见最美的你
        // https://www.xinli001.com/info/100472605  心理课
        // https://www.xinli001.com/info/100472532 关于自我
        currentIndex: 0,   //默认显示图片
        timer: null,    //定时器
        posterObj: [
          {imgUrl:"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.wenzhangba.com%2Fallimg%2F181121%2F1Q121204002-5094-0.png&refer=http%3A%2F%2Fimg.wenzhangba.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1623258612&t=667b55882aaf419de1cf25c997da41fb"},
          {imgUrl:"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.huicheimg.com%2Fedpic_360_360%2Fc7%2Fda%2F55%2Fc7da55532fc6c80b34ea4635224f1de0.jpg&refer=http%3A%2F%2Fup.huicheimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1623258655&t=bce36b9d953470704d4ab467185b88fe"},
          {imgUrl:"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201505%2F23%2F20150523190529_sVRz8.thumb.700_0.jpeg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1623258553&t=8abe4b081bfa9846d34c838e76db3a3b"},
          {imgUrl:"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F4837802862dfe5cbd5bdcbec93a01fa055e81f775f52-f3q13U_fw658&refer=http%3A%2F%2Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1623258172&t=1cb206f066306c63c29019a4f5b18081"},
          {imgUrl:"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202003%2F28%2F20200328221700_cguxd.thumb.400_0.jpg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1623258421&t=8788a95d3ec829b537d7c708b746840f"},
        ],
        swiperOption: {
          effect: "coverflow",
          coverflowEffect: {
            rotate:80,
            stretch: 10,
            depth: 60,
            modifier: 2,
            slideShadows : true
          },
          loop: true, // 无限循环
          observer: false, //修改swiper自己或子元素时，自动初始化swiper
          observeParents: false, //修改swiper的父元素时，自动初始化swiper
          slidesPerView: "auto", // 设置可视区一共显示几张图片,设置auto,设置auto为自适应;
          centeredSlides: true, // 设置为true时,带有active-slide类名的图片会居中
          loopAdditionalSlides: 0, //loop模式下会在slides前后复制若干个slide,，前后复制的个数不会大于原总个数。默认为0，前后各复制1个。0,1,2 --> 2,0,1,2,0
          //  页码
          pagination: {
            el: '.swiper-pagination',
            type: 'bullets',
            dynamicMainBullets: 2, //动态分页器的主指示点的数ss量
            clickable: true, //此参数设置为true时，点击分页器的指示点分页器会控制Swiper切换。
          },
          //  点击上一页 下一页
          navigation: {
            nextEl: '.swiper-button-next', //前进按钮的css选择器或HTML元素。
            prevEl: '.swiper-button-prev', //后退按钮的css选择器或HTML元素。
            hideOnClick: true, //点击slide时显示/隐藏按钮
            disabledClass: 'my-button-disabled', //前进后退按钮不可用时的类名。
            hiddenClass: 'my-button-hidden', //按钮隐藏时的Class
          },
          on: {
            slideChangeTransitionEnd: function() {
              // console.log('当前index+',this.realIndex)
              that.realIndex = this.realIndex;
            }
          },
        },
        realIndex: 0,
      }

    },
    //定时器
    mounted() {
      this.timer = setInterval(() => {
        this.gotoPage(this.nextIndex)
      }, 3000);

      this.initVideo();
    },
    methods: {
      initVideo() {
        //初始化视频方法
        let myPlayer = this.$video(myVideo, {
          //确定播放器是否具有用户可以与之交互的控件。没有控件，启动视频播放的唯一方法是使用autoplay属性或通过Player API。
          controls: true,
          //自动播放属性,muted:静音播放
          autoplay: "muted",
          //建议浏览器是否应在<video>加载元素后立即开始下载视频数据。
          preload: "auto",
          //设置视频播放器的显示宽度（以像素为单位）
          width: "800px",
          //设置视频播放器的显示高度（以像素为单位）
          height: "400px"
        });
      },
      go(index){
        var url = "";
        switch (index) {
          case 0:
            url="https://www.xinli001.com/info/100472532";
            break;
            case 1:
              url="https://www.xinli001.com/info/100472191";
              break;
              case 2:
                url = "https://www.xinli001.com/info/100472544?source=pc-home";
                break;
        }
        window.open(url,"_blank")
      },
      goArticle(index){
        this.$http.get("/article/getRandArticle").then(response => {
          if (response.data.errorCode===0){
            window.open("/#/Detail?id="+response.data.data,"_blank");
          }else {
            this.$message.error(response.data.msg);
          }
        });
      },
      gotoPage(index) {
        this.currentIndex = index;
      },
      intoTH:function () {
        this.$router.push("/TreeHole");
      },
      intoChat:function(){
        this.$router.push("/Consulting");
      },
      intoV:function () {
        this.$router.push("/VolunteerZone");
      },
      // 跳转到指定页面
      swiperSlideTo(index){
        // this.$refs.mySwiper.$swiper.slideTo(index, 0, true);
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
    background: beige;
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
    height: 800px;
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

  /*hr{*/
  /*  margin-top:60px;*/
  /*  !*border:2px dashed mediumseagreen;*!*/
  /*  !*height:30px;*!*/
  /*  text-align:center;*/
  /*  line-height:0px;*/
  /*  !*background:mediumseagreen;*!*/

  /*  !*BORDER-BOTTOM-STYLE: dotted;*!*/
  /*  !*BORDER-LEFT-STYLE: dotted;*!*/
  /*  !*BORDER-RIGHT-STYLE: dotted;*!*/
  /*  !*BORDER-TOP-STYLE: dotted;*!*/
  /*  color:mediumseagreen;*/
  /*  !*size:5px;*!*/
  /*}*/

  /*hr:after{*/
  /*  content:"这里加字";*/
  /*  font-weight:bold;*/
  /*  font-size:18px;*/
  /*  background:white;*/
  /*  z-index:2;*/
  /*}*/

  hr{
    margin-top:60px;
    text-align:center;
    line-height:20px;
    BORDER-BOTTOM-STYLE: dotted;
    BORDER-LEFT-STYLE: dotted;
    BORDER-RIGHT-STYLE: dotted;
    BORDER-TOP-STYLE: dotted;
    color: white;
    /*background:mediumseagreen;*/
  }

  hr:after{
    font-weight:bold;
    font-size:18px;
    color: mediumseagreen;
    /*background:mediumseagreen;*/
    z-index:2;
  }

  .hr_style1:after{
    content:"凡事不要想的太复杂，手握的太紧，东西会碎，手会疼";

  }

  .hr_style2:after{
    content:"每个人都有自己故事，只是演绎的方式不同";

  }

  .hr_style3:after{
    content:"人生无须太多的准备，上帝给了我们腿与脚，就是让我们不停地前行";

  }

  .hr_style4:after{
    content:"心中有光的人，终会冲破一切黑暗和荆棘";

  }

  .banner_theme{
    width: 100%;
    position: relative;
    margin-top: 60px;
  }
  .item_theme{
    width: 100%;
    max-height: 1960px;
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
  .tip_video{
    color: white;
    font-size: 18px;
    text-align: right;
    position: absolute;
    left: 60%;
    right: 10px;
    top: 40%;
  }
  .span_need{
    font-weight: bolder;
    font-size: 25px
  }
  .span_need:hover{
    cursor: pointer;
  }
  .tipV{
    color: white;
    float: left;
    font-size: 30px;
    text-indent: 2em;
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

  .DetailsContent {
    width: 100%;
    height: 100%;
  }
  .swiper-container-3d {
    perspective: 600px;
    height: 469px;
    padding-top: 18px;
  }
  .swiper-slide-prev {
    transform: translate3d(78px, 0px, 100px) rotateX(0deg) rotateY(65deg) scale(1) !important;
  }
  .swiper-slide-next {
    transform: translate3d(-78px, 0px, 100px) rotateX(0deg) rotateY(-65deg) scale(1) !important;
  }
  .poster_dv {
    width: 100%;
    height: 469px;
    position: relative;
  }
  .poster_item {
    width: 495px;
    height: 415px;
  }
  .poster_item img {
    width: 100%;
    height: 100%;
    display: block;
  }

  .swiper-pagination {
    padding: 0 12px 0 7px;
    height: 13px;
    background: rgba(0, 0, 0, 0.5);
    border-radius: 7px;
    position: absolute;
    bottom: 10px;
    left: 50%;
    transform: translateX(-50%);
    box-sizing: border-box;
    display: flex;
    align-items: center;
  }
  .swiper-pagination-bullet-active {
    background: #fff !important;
  }
  .swiper-pagination-bullet {
    width: 8px;
    height: 8px;
    opacity: 1;
    background: #d3b987;
    border-radius: 50%;
    display: block;
    margin-left: 5px;
  }
  .swiper-button-next {
    width: 32px;
    height: 269px;
    top: 0;
    right: 0;
    background: rgba(0, 0, 0, 0);
    color: rgba(0, 0, 0, 0);
  }
  .swiper-button-prev {
    width: 32px;
    height: 269px;
    top: 0;
    left: 0;
    background: rgba(0, 0, 0, 0);
    color: rgba(0, 0, 0, 0);
  }
</style>
