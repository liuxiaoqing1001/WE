<template>
  <div>
    <div class="main">
      <!--    <div class="ball-box">-->
      <!--      <div class="ball">-->
      <!--        <div class="line1"></div>-->
      <!--        <div class="line2"></div>-->
      <!--        <div class="line3"></div>-->
      <!--        <div class="line4"></div>-->
      <!--        <div class="line5"></div>-->
      <!--      </div>-->
      <!--    </div>-->
    </div>
  </div>


</template>

<script>
    export default {
        name: "VolunteerZone"
    }
</script>

<style scoped>
  .main{
    width: 100%;
    min-width: 1200px;
    height: 900px;
    background-image: url("../../assets/background/background.jpeg");
    background-repeat:no-repeat;
    background-size:100% 100%;
    -moz-background-size:100% 100%;
    overflow: hidden;
    filter: drop-shadow(0 0 10px white);
    position: relative;
  }
  /*.ball-box{*/
  /*  !*background-color: #000000;*!*/
  /*  width: 600px;*/
  /*  height: 600px;*/
  /*  position: absolute;*/
  /*  left: 40%;*/
  /*  top: 40%;*/
  /*  margin: -150px 0 0 -150px;*/
  /*  -webkit-perspective-origin: 50% 50%;*/
  /*  -moz-perspective-origin: 50% 50%;*/
  /*  -ms-perspective-origin: 50% 50%;*/
  /*  -o-perspective-origin: 50% 50%;*/
  /*  perspective-origin: 50% 50%;*/
  /*  -webkit-perspective: 3000px;*/
  /*  -moz-perspective: 3000px;*/
  /*  -ms-perspective: 3000px;*/
  /*  -o-perspective: 3000px;*/
  /*  perspective: 3000px;*/
  /*}*/
  /*@-webkit-keyframes rotate3d {*/
  /*  0%{*/
  /*    -webkit-transform: rotateZ(-30deg) rotateY(0deg);*/
  /*    -moz-transform: rotateZ(-30deg) rotateY(0deg);*/
  /*    -ms-transform: rotateZ(-30deg) rotateY(0deg);*/
  /*    -o-transform: rotateZ(-30deg) rotateY(0deg);*/
  /*    transform: rotateZ(-30deg) rotateY(0deg);*/
  /*  }*/
  /*  100%{*/
  /*    -webkit-transform: rotateZ(-30deg) rotateY(360deg);*/
  /*    -moz-transform: rotateZ(-30deg) rotateY(360deg);*/
  /*    -ms-transform: rotateZ(-30deg) rotateY(360deg);*/
  /*    -o-transform: rotateZ(-30deg) rotateY(360deg);*/
  /*    transform: rotateZ(-30deg) rotateY(360deg);*/
  /*  }*/
  /*}*/
  /*@-moz-keyframes rotate3d {*/
  /*  0%{*/
  /*    -webkit-transform: rotateZ(-30deg) rotateY(0deg);*/
  /*    -moz-transform: rotateZ(-30deg) rotateY(0deg);*/
  /*    -ms-transform: rotateZ(-30deg) rotateY(0deg);*/
  /*    -o-transform: rotateZ(-30deg) rotateY(0deg);*/
  /*    transform: rotateZ(-30deg) rotateY(0deg);*/
  /*  }*/
  /*  100%{*/
  /*    -webkit-transform: rotateZ(-30deg) rotateY(360deg);*/
  /*    -moz-transform: rotateZ(-30deg) rotateY(360deg);*/
  /*    -ms-transform: rotateZ(-30deg) rotateY(360deg);*/
  /*    -o-transform: rotateZ(-30deg) rotateY(360deg);*/
  /*    transform: rotateZ(-30deg) rotateY(360deg);*/
  /*  }*/
  /*}*/
  /*@-ms-keyframes rotate3d {*/
  /*  0%{*/
  /*    -webkit-transform: rotateZ(-30deg) rotateY(0deg);*/
  /*    -moz-transform: rotateZ(-30deg) rotateY(0deg);*/
  /*    -ms-transform: rotateZ(-30deg) rotateY(0deg);*/
  /*    -o-transform: rotateZ(-30deg) rotateY(0deg);*/
  /*    transform: rotateZ(-30deg) rotateY(0deg);*/
  /*  }*/
  /*  100%{*/
  /*    -webkit-transform: rotateZ(-30deg) rotateY(360deg);*/
  /*    -moz-transform: rotateZ(-30deg) rotateY(360deg);*/
  /*    -ms-transform: rotateZ(-30deg) rotateY(360deg);*/
  /*    -o-transform: rotateZ(-30deg) rotateY(360deg);*/
  /*    transform: rotateZ(-30deg) rotateY(360deg);*/
  /*  }*/
  /*}*/
  /*@-o-keyframes rotate3d {*/
  /*  0%{*/
  /*    -webkit-transform: rotateZ(-30deg) rotateY(0deg);*/
  /*    -moz-transform: rotateZ(-30deg) rotateY(0deg);*/
  /*    -ms-transform: rotateZ(-30deg) rotateY(0deg);*/
  /*    -o-transform: rotateZ(-30deg) rotateY(0deg);*/
  /*    transform: rotateZ(-30deg) rotateY(0deg);*/
  /*  }*/
  /*  100%{*/
  /*    -webkit-transform: rotateZ(-30deg) rotateY(360deg);*/
  /*    -moz-transform: rotateZ(-30deg) rotateY(360deg);*/
  /*    -ms-transform: rotateZ(-30deg) rotateY(360deg);*/
  /*    -o-transform: rotateZ(-30deg) rotateY(360deg);*/
  /*    transform: rotateZ(-30deg) rotateY(360deg);*/
  /*  }*/
  /*}*/
  /*@keyframes rotate3d {*/
  /*  0%{*/
  /*    -webkit-transform: rotateZ(-30deg) rotateY(0deg);*/
  /*    -moz-transform: rotateZ(-30deg) rotateY(0deg);*/
  /*    -ms-transform: rotateZ(-30deg) rotateY(0deg);*/
  /*    -o-transform: rotateZ(-30deg) rotateY(0deg);*/
  /*    transform: rotateZ(-30deg) rotateY(0deg);*/
  /*  }*/
  /*  100%{*/
  /*    -webkit-transform: rotateZ(-30deg) rotateY(360deg);*/
  /*    -moz-transform: rotateZ(-30deg) rotateY(360deg);*/
  /*    -ms-transform: rotateZ(-30deg) rotateY(360deg);*/
  /*    -o-transform: rotateZ(-30deg) rotateY(360deg);*/
  /*    transform: rotateZ(-30deg) rotateY(360deg);*/
  /*  }*/
  /*}*/
  /*.ball{*/
  /*  height: 100%;*/
  /*  -webkit-transform-style: preserve-3d;*/
  /*  -moz-transform-style: preserve-3d;*/
  /*  -ms-transform-style: preserve-3d;*/
  /*  transform-style: preserve-3d;*/
  /*  -webkit-animation: rotate3d 30s linear infinite;*/
  /*  -moz-animation: rotate3d 30s linear infinite;*/
  /*  -ms-animation: rotate3d 30s linear infinite;*/
  /*  -o-animation: rotate3d 30s linear infinite;*/
  /*  animation: rotate3d 30s linear infinite;*/
  /*}*/
  /*.ball:after{*/
  /*  display: block;*/
  /*  content: '';*/
  /*  width: 1px;*/
  /*  height: 500px;*/
  /*  !*background-color: #ff0;*!*/
  /*  position: absolute;*/
  /*  top: -100px;*/
  /*  left: 150px;*/
  /*}*/
  /*.ball > div{*/
  /*  border: 1px #ffffff solid;*/
  /*  position: absolute;*/
  /*  width: 100%;*/
  /*  height: 100%;*/
  /*  -webkit-border-radius: 50%;*/
  /*  -moz-border-radius: 50%;*/
  /*  border-radius: 50%;*/
  /*}*/
  /*.ball .line1{*/
  /*  -webkit-transform: rotateY(0deg);*/
  /*  -moz-transform: rotateY(0deg);*/
  /*  -ms-transform: rotateY(0deg);*/
  /*  -o-transform: rotateY(0deg);*/
  /*  transform: rotateY(0deg);*/
  /*}*/
  /*.ball .line2{*/
  /*  -webkit-transform: rotateY(36deg);*/
  /*  -moz-transform: rotateY(36deg);*/
  /*  -ms-transform: rotateY(36deg);*/
  /*  -o-transform: rotateY(36deg);*/
  /*  transform: rotateY(36deg);*/
  /*}*/
  /*.ball .line3{*/
  /*  -webkit-transform: rotateY(72deg);*/
  /*  -moz-transform: rotateY(72deg);*/
  /*  -ms-transform: rotateY(72deg);*/
  /*  -o-transform: rotateY(72deg);*/
  /*  transform: rotateY(72deg);*/
  /*}*/
  /*.ball .line4{*/
  /*  -webkit-transform: rotateY(108deg);*/
  /*  -moz-transform: rotateY(108deg);*/
  /*  -ms-transform: rotateY(108deg);*/
  /*  -o-transform: rotateY(108deg);*/
  /*  transform: rotateY(108deg);*/
  /*}*/
  /*.ball .line1{*/
  /*  -webkit-transform: rotateY(144deg);*/
  /*  -moz-transform: rotateY(144deg);*/
  /*  -ms-transform: rotateY(144deg);*/
  /*  -o-transform: rotateY(144deg);*/
  /*  transform: rotateY(144deg);*/
  /*}*/
</style>
