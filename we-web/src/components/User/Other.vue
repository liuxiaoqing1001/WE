<template>
  <div class="slide-img clear" id="slide-img">
    <!--用tran这个class控制ul是否含有过渡效果，样式已经写好-->
    <ul :style="{'width':(listWidth*list.length)+'px','transform':'translate3d(-'+(listWidth*nowIndex)+'px,0,0)'}">
      <!--遍历出来的图片-->
      <li v-for="(li,index) in list" :style="{'width':listWidth+'px'}">
        <a href="javascript:;">
          <img :src="list[nowIndex]" class="slider-img"/>
        </a>
      </li>
    </ul>
    <div class="slide-option">
      <span v-for="(li,index) in list" :class="{'active':index===nowIndex}"></span>
    </div>
    <div class="slide-arrow">
      <div class="arrow-left" @click.stop="switchDo('reduce')"></div>
      <div class="arrow-right" @click.stop="switchDo"></div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "Other",
      data(){
        return {
          nowIndex: 0,
          listWidth: '1000',
          list: [
            'https://i1.mifile.cn/a4/xmad_15535933141925_ulkYv.jpg',
            'https://i1.mifile.cn/a4/xmad_15532384207972_iJXSx.jpg',
            'https://i1.mifile.cn/a4/xmad_15517939170939_oiXCK.jpg',
            '../../assets/1.jpeg', '../../assets/1.png', '../../assets/2.jpeg', '../../assets/square_logo.png'
          ],
          timer:null
        }
      },
      methods: {
        //滑动操作
        switchDo(reduce){
          clearInterval(this.timer);
          //根据reduce判断this.nowIndex的增加或者减少！
          if(reduce==='reduce'){
            //如果是第一张，就返回最后一张
            if(this.nowIndex===0){
              this.nowIndex=this.list.length-1;
            }
            else{
              this.nowIndex--;
            }
          }
          else{
            //如果是最后一张，就返回第一张
            if(this.nowIndex===this.list.length-1){
              this.nowIndex=0;
            }
            else{
              this.nowIndex++;
            }
          }
          let _this=this;
          this.timer=setInterval(function () {
            _this.switchDo();
          },4000)

        },
      },
      mounted(){
        let _this=this;
        this.timer=setInterval(function () {
          _this.switchDo();
        },4000)
      }
    }
</script>

<style scoped>
  .slide-img {
    width: 1000px;
    height: 500px;
    overflow: hidden;
    position: relative;
    margin: 20px auto;
  }

  ul {
    transition: all .5s ease;
  }

  li {
    float: left;
  }

  .slide-arrow div {
    width: 50px;
    height: 100px;
    position: absolute;
    margin: auto;
    top: 0;
    bottom: 0;
    background: url("http://i1.bvimg.com/1949/4d860a3067fab23b.jpg") no-repeat;
  }

  .arrow-right {
    transform: rotate(180deg);
    right: 0;
  }

  .arrow-left {
    left: 0;
  }
  .slide-option{
    position: absolute;
    bottom: 10px;
    width: 100%;
    left: 0;
    text-align: center;
  }
  .slide-option span{
    display: inline-block;
    width: 14px;
    height: 14px;
    border-radius: 100%;
    background: #ccc;
    margin: 0 10px;
  }
  .slide-option .active{
    background: #09f;
  }
</style>
