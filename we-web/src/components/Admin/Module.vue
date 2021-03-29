<template>
  <div>
    <el-container>
      <el-header>
        <!--面包屑导航区域-->
        <el-breadcrumb >
          <el-breadcrumb-item :to="{ path: '/Module' }">模块管理</el-breadcrumb-item>
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
      <el-table :data="mList" style="width: 100%" border stripe>
        <el-table-column prop="mId" label="编号"></el-table-column>
        <el-table-column prop="mName" label="名称"></el-table-column>
        <el-table-column prop="mRemark" label="备注"></el-table-column>
        <el-table-column prop="mCreator" label="创建者"></el-table-column>
        <el-table-column prop="mState" label="状态"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            {{scope.row.id}}
          </template>
          <!--修改-->
          <el-button type="primary" size="mini" icon="el-icon-edit"></el-button>
          <!--删除-->
          <el-button type="danger" size="mini" icon="el-icon-delete"></el-button>
          <!--状态开关-->
          <el-button type="warning" size="mini" icon="el-icon-setting"></el-button>
        </el-table-column>
      </el-table>
    </el-container>

  </div>
</template>

<script>
  export default {
    name: "Model",
    data(){
      return{
        mList:[],//模块列表
        total:0,//模块总数
        //获取模块列表的参数对象
        queryInfo:{
          query:'',//查询参数
          pagenum:1,//当前页码
          pagesize:2//每页显示条数
        }
      }
    },
    // created() {
    //   this.getUserList();
    // },
    // methods:{
    //   async getUserList(){
    //     const { data:res } = await this.$http.get('users',{params:this.queryInfo})
    //     console.log(res);
    //     if (res.meta.status !==200){
    //       return this.$message.error(res.meta.msg);
    //     }
    //     this.userList = res.data.users;
    //     this.total = res.data.total;
    //   }
    // }
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
