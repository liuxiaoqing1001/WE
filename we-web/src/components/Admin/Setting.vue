<template>
  <div>
    <el-container>
      <el-header>
        <!--面包屑导航区域-->
        <el-breadcrumb >
          <el-breadcrumb-item :to="{ path: '/Setting' }">系统设置</el-breadcrumb-item>
          <el-breadcrumb-item>关于平台</el-breadcrumb-item>
        </el-breadcrumb>
      </el-header>

    </el-container>

  </div>
</template>

<script>
  export default {
    name: "Setting",
    data(){
      return{
      }
    }
  }
</script>

<style scoped>
  .el-breadcrumb{
    /*margin-bottom: 20px;*/
    margin-top: 15px;
    font-size: 13px;
    height: auto;
  }
  .el-card{
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1) !important;
    height: auto;
  }
  .el-table{
    height: auto;
  }
</style>
