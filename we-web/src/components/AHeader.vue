<template>

  <div>
    <el-container class="home-container">
      <!-- 头部 -->
      <el-header>
        <div>
          <img src="../assets/we_logo.png" alt="">
          <span>“我和你”心理健康公益平台</span>
        </div>
        <el-button type="info" @click="logout">退出</el-button>
      </el-header>
      <!-- 主体 -->
      <el-container>
        <!-- 侧边栏 -->
        <el-aside width="200px">Aside</el-aside>
        <!-- 右侧内容主体 -->
        <el-main>Main</el-main>
      </el-container>
    </el-container>
  </div>

</template>

<script>
    export default {
        name: "AHeader"
    }
</script>

<style scoped>
  .home-container {
    height: 100%;
  }
  .el-header {
    background: #373d41;
    color: #fff;
    display: flex;
    justify-content: space-between;
    padding-left: 0;
    align-items: center;
    font-size: 20px;
  }

  > div {
    display: flex;align-items: center;
  }

  span {
    margin-left: 15px;
  }

  .el-aside {
    background: #333744;
  }

  .el-main {
    background: #eaedf1;
  }
</style>
