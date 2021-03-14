<template>
  <div>
    <div class="main">
      <div class="tips">
        判断是否是咨询师
        否：
        加入条件
        上传证件
        等待审核
        是：
        聊天室（咨询中心）
        文章
        自愿者个人中心
      </div>

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
    color: white;
    /*position: relative;*/
  }

</style>
