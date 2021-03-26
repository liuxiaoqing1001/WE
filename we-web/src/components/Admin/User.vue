<template>
  <div>
    <el-container>
      <el-header>
        <!--面包屑导航区域-->
        <el-breadcrumb >
          <el-breadcrumb-item :to="{ path: '/AdminMain' }">用户管理</el-breadcrumb-item>
          <el-breadcrumb-item>普通用户</el-breadcrumb-item>
        </el-breadcrumb>
      </el-header>

      <!--卡片视图区域-->
      <el-card>
        <el-row :gutter="20">
          <el-col :span="2">
            <el-button type="primary" icon="el-icon-plus"></el-button>
          </el-col>
          <el-col :span="10">
            <!--搜索区域-->
            <el-input placeholder="请输入内容">
              <el-button slot="append" icon="el-icon-search"></el-button>
            </el-input>
          </el-col>
        </el-row>
      </el-card>
      <!--用户列表区域-->
      <!--    边框：border属性，设置为true即可启用-->
      <!--    隔行变色：stripe属性,创建带斑马纹的表格，默认为false，设置为true即为启用-->
      <el-table :data="userList" style="width: 100%" border stripe>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="phone" label="电话"></el-table-column>
        <el-table-column prop="sex" label="性别"></el-table-column>
        <el-table-column prop="birthday" label="出生日期"></el-table-column>
        <el-table-column prop="email" label="邮箱"></el-table-column>
        <el-table-column prop="role" label="角色"></el-table-column>
        <el-table-column prop="regdate" label="创建时间"></el-table-column>
        <el-table-column label="操作">
<!--          <template slot-scope="scope">-->
<!--            {{scope.row.id}}-->
<!--          </template>-->
          <!--修改-->
<!--          enterable属性：表示鼠标是否可进入到 tooltip 中，默认是true开的，需要把它绑定为false，鼠标移到上面提示就消失了-->
          <el-tooltip class="item" effect="dark" content="修改" placement="top" :enterable="false">
            <el-button type="primary" size="mini" icon="el-icon-edit"></el-button>
          </el-tooltip>
          <!--删除-->
          <el-tooltip class="item" effect="dark" content="删除" placement="top" :enterable="false">
            <el-button type="danger" size="mini" icon="el-icon-delete"></el-button>
          </el-tooltip>
          <!--分配角色-->
          <el-tooltip class="item" effect="dark" content="分配角色" placement="top" :enterable="false">
            <el-button type="warning" size="mini" icon="el-icon-setting"></el-button>
          </el-tooltip>
        </el-table-column>
      </el-table>
      <!--分页区域-->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pagenum"
        :page-sizes="[1, 2, 5, 10]"
        :page-size="queryInfo.pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </el-container>

  </div>
</template>

<script>
  export default {
    name: "User",
    data(){
      return{
        userList:[],//用户列表
        total:0,//用户总数
        //获取用户列表的参数对象
        queryInfo:{
          query:'',//查询参数
          pagenum:1,//当前页码
          pagesize:5//每页显示条数
        }
      }
    },
    created() {
      this.getUserList();
    },
    methods:{
      // 监听 pageSize 改变的事件
      handleSizeChange(newSize) {
        console.log(newSize)
      },
      // 监听 当前页码值 改变的事件
      handleCurrentChange(newPage) {
        console.log(newPage)
      },
      getUserList(){
        this.$http.get("/user/getAllUser").then(response => {
          if (response.data.errorCode===0){
            this.userList = response.data.data;
            this.total = response.data.data.length;
          }else {
            this.$message.error(response.data.msg);
          }
        });
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
