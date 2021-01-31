<template>
  <div>
    <!--面包屑导航区域-->
    <el-breadcrumb >
<!--      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>-->
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>普通用户</el-breadcrumb-item>
    </el-breadcrumb>
    <!--卡片视图区域-->
    <el-card>
      <el-row :gutter="20">
        <el-col :span="17">
          <!--搜索区域-->
          <el-input placeholder="请输入内容">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary">添加用户</el-button>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
    export default {
        name: "User"
    }
</script>

<style scoped>
  html, body, #app {
    height: 100%;
    margin: 0;
    padding: 0;
  }
  el-breadcrumb{
    margin-bottom: 15px;
    font-size: 12px;
  }
  el-card{
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1) !important;
  }
</style>
