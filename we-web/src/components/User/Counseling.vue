<template>
  <div class="content">
    <div class="top"><img src="../../assets/1.jpeg"></div>
    <div class="bottom"><img src="../../assets/square_logo.png"></div>
    <div class="left"><img src="../../assets/we_logo.png"></div>
    <div class="right"><img src="../../assets/we_logo_1.png"></div>
    <div class="front"><img src="../../assets/we_logo_black.png"></div>
    <div class="after"><img src="../../assets/iconfont/icon-pwd.png"></div>
  </div>
</template>

<script>
    export default {
        name: "Counseling"
    }
</script>

<style scoped>
  .content{
    width:200px;
    height:200px;
    transform-style:preserve-3d;
    transform: rotateX(-30deg) rotateY(-80deg);
    position:relative;
    margin:200px auto;
    animation: rotate linear 20s infinite;
  }
  @-webkit-keyframes rotate {
    from {
      transform: rotateX(0deg) rotateY(0deg);
    }
    to {
      transform: rotateX(360deg) rotateY(360deg);
    }
  }
  .content div{
    width:200px;
    height:200px;
    position: absolute;
    opacity:.8;
  }
  .content>div>img{
    width:200px;
    height:200px;
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
