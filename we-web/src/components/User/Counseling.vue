<template>
  <div class="body">
    <div class="text">
      <h1 v-on:click="$goRoute('/MentalTest')">{{testText}}</h1>
    </div>
  </div>

</template>

<script>
    export default {
      name: "Counseling",
      data () {
        return {
          nowIndex:0,
          testText:'欢迎来到WE咨询中心'
        }
      },
      mounted(){
        let textContent=this;
        let timer = setInterval(function(){
          textContent.nowIndex++;
          switch (textContent.nowIndex) {
            case 1:
              textContent.testText = '为了更好地为您服务';
              break;
            case 2:
              textContent.testText = '您将进行一些小小的心理测试';
              break;
            case 3:
              textContent.testText = '请点击屏幕前往测试区>>>';
              break;
          }
          if (textContent.nowIndex > 3) {
            setTimeout(() => {
              clearInterval(timer);
            }, 2000)
          }
        }, 2000)
      }
    }
</script>

<style scoped>
  .body{
    background: darkgray;
    width: 100%;
    height: 100%;
  }

  h1 {
    color: white;
    text-transform: uppercase;
    margin-top: 100px;
    text-align: center;
    font-size: 5rem;
    line-height: 1;
    animation: letterSpacing 1s 7 alternate ease-in-out;
    display: block;
    letter-spacing: .5rem;
  }

  @keyframes letterSpacing {
    0% {
      letter-spacing: -72px;
      filter: blur(20px);
    }

    40% {
      filter: blur(6px);
    }

    80% {
      letter-spacing: 8px;
      filter: blur(0);
    }
  }
</style>
