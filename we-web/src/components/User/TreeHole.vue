<template>
  <div>
    <div class="main">
      <div class="stars">
        <div class="star" v-for="(item,index) in starsCount" :key="index" ref="star"></div>
      </div>
      <div class="content">
        <div class="top"><img src="../../assets/we_logo.png"></div>
        <div class="bottom"><img src="../../assets/square_logo.png"></div>
        <div class="left"><img src="../../assets/we_logo.png"></div>
        <div class="right"><img src="../../assets/we_logo_1.png"></div>
        <div class="front"><img src="../../assets/we_logo_black.png"></div>
        <div class="after"><img src="../../assets/we_logo_1.png"></div>
      </div>
      <div class="option">
        告诉星星，也许会得到你想要的，放心，没人知道你是谁
      </div>
    </div>

  </div>
</template>

<script>
  // import ball from "TreeHole";
    export default {
        name: "TreeHole",
      // components:{ball},
      data() {
        return {
          starsCount:2000,//数量
          distance:200,//间距
        }
      },
      mounted() {
        let starArr=this.$refs.star;
        starArr.forEach(item=>{
          var speed = 0.2+(Math.random()*1);
          var distance = this.distance+(Math.random()*300);
          item.style.transformOrigin=`0 0 ${distance}px`;
          item.style.transform=`translate3d(0,0,-${distance}px) rotateY(${(Math.random()*360)}deg) rotateX(${(Math.random()*-50)}deg) scale(${speed},${speed})`;
        })
      },
    }
</script>

<style scoped>
  .main {
    width: 100%;
    min-width: 1200px;
    height: 900px;
    /*background-color: #fff;*/
    background: radial-gradient(ellipse at bottom, #090a0f 0%, #000000 100%);
    overflow: hidden;
    filter: drop-shadow(0 0 10px white);
    position: relative;
  }
  .option{
    color: white;
    font-size: 3px;
  }
  ::after{
     content: '。';
     position: absolute;
     left: 50%;
     transform: translateX(-50%);
     bottom: -28px;
     width:2px;
     height:2px;
     background: blue;
     border-radius:50%;
    color: snow;
    font-size: 1px;
  }
  .stars {
    transform: perspective(500px);
    transform-style: preserve-3d;
    position: absolute;
    perspective-origin: 50% 100%;
    left: 50%;
    animation: rotate 800s infinite linear;
    bottom: -256px;
  }
  .star{
    width: 2px;
    height: 2px;
    background: snow;
    position: absolute;
    top: 0;
    left: 50px;
    backface-visibility: hidden;
  }
  /*@-webkit-keyframes rotate {*/
  /*  from {*/
  /*    transform: rotateX(0deg) rotateY(0deg);*/
  /*  }*/
  /*  to {*/
  /*    transform: rotateX(360deg) rotateY(360deg);*/
  /*  }*/
  /*}*/
  @keyframes rotate {
    from{
      transform: perspective(400px) rotateZ(0deg) rotateX(0deg) rotateY(0deg);
    }
    to{
      transform: perspective(400px) rotateZ(360deg) rotateX(360deg) rotateY(360deg);
    }
  }
  .content{
    width:200px;
    height:200px;
    transform-style:preserve-3d;
    transform: rotateX(-30deg) rotateY(-80deg);
    position:relative;
    margin:200px auto;
    animation: rotate linear 90s infinite;
  }

  .content div{
    width:200px;
    height:200px;
    position: absolute;
    opacity:.8;
  }
  .content>div>img{
    width:100px;
    height:100px;
  }
  .content .top{
    transform: rotateX(90deg) translateZ(100px);
  }
  .content .left{
    transform: rotateY(-90deg) translateZ(100px);
  }
  .content .bottom{
    transform: rotateX(-90deg) translateZ(100px);
  }
  .content .right{
    transform: rotateY(90deg) translateZ(100px);
  }
  .content .front{
    transform: rotateY(0deg) translateZ(100px);
  }
  .content .after{
    transform: translateZ(-100px) rotateY(180deg);
  }
</style>
