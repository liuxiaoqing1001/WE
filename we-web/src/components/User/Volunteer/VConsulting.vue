<template>
  <div>
    <h5>我的咨询者</h5>
    <el-container>
      <!--用户列表区域-->
      <!--    边框：border属性，设置为true即可启用-->
      <!--    隔行变色：stripe属性,创建带斑马纹的表格，默认为false，设置为true即为启用-->
      <el-table :data="userList" style="width: 100%" border stripe>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="mobile" label="电话"></el-table-column>
        <el-table-column prop="mg_state" label="状态"></el-table-column>
        <el-table-column label="详情">
          <el-button type="primary" size="mini" icon="el-icon-edit"></el-button>
          <el-button type="warning" size="mini" icon="el-icon-search"></el-button>
        </el-table-column>
      </el-table>
    </el-container>

  </div>
</template>

<script>
    export default {
        name: "VConsulting",
      data(){
        return{
          userList:[],//用户列表
          total:0,//用户总数
          //获取用户列表的参数对象
          queryInfo:{
            query:'',//查询参数
            pagenum:1,//当前页码
            pagesize:2//每页显示条数
          }
        }
      },
    }
</script>

<style scoped>
  .el-table{
    height: auto;
  }
</style>
