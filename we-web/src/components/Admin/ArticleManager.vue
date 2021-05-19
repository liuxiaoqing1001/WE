<template>
  <div>
    <el-container>
      <el-header>
        <!--面包屑导航区域-->
        <el-breadcrumb >
          <el-breadcrumb-item :to="{ path: '/ArticleManager' }">文章管理</el-breadcrumb-item>
        </el-breadcrumb>
      </el-header>

      <!--卡片视图区域-->
      <el-card>
        <el-row :gutter="20">
          <el-col :span="10">
            <!--搜索区域-->
            <el-input v-model="queryInfo.query" placeholder="请输入内容" clearable @clear="getArticleList">
              <el-button slot="append" icon="el-icon-search" @click="getArticle"></el-button>
            </el-input>
          </el-col>
        </el-row>
      </el-card>

      <el-table :data="mList" style="width: 100%" border stripe>
        <el-table-column prop="id" label="ID"></el-table-column>
        <el-table-column prop="sender" label="发送者"></el-table-column>
        <el-table-column prop="type" label="类型"></el-table-column>
        <el-table-column prop="title" label="标题"></el-table-column>
        <el-table-column prop="sendDate" label="发送时间"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <!--详情-->
            <el-tooltip class="item" effect="dark" content="详情" placement="top" :enterable="false">
              <el-button type="primary" round size="mini" icon="el-icon-view" @click="showDialog(scope.row.id)"></el-button>
            </el-tooltip>
            <!--删除-->
            <el-tooltip class="item" effect="dark" content="删除" placement="top" :enterable="false">
              <el-button type="danger" round size="mini" icon="el-icon-delete" @click="removeArticleById(scope.row.id)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNum"
        :page-sizes="[10, 20, 50]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>

      <!--文章信息的对话框-->
      <el-dialog title="文章" :visible.sync="editDialogVisible" width="50%"  @close="editDialogClosed">
        <!--内容主体区域 :rules="editFormRules"-->
        <el-form :model="viewForm" ref="editFormRef" label-width="70px">
<!--          <el-form-item>-->
<!--            <el-image v-model="viewForm.imgUrl" :href="viewForm.imgUrl"></el-image>-->
<!--          </el-form-item>-->
          <el-form-item label="发送者:" prop="sender" class="inputDeep">
            <el-input v-model="viewForm.sender" readonly></el-input>
          </el-form-item>
          <el-form-item label="类型:" prop="type" class="inputDeep">
            <el-input v-model="viewForm.type" readonly></el-input>
          </el-form-item>
          <el-form-item label="标题:" prop="type" class="inputDeep">
            <el-input v-model="viewForm.title" readonly></el-input>
          </el-form-item>
          <el-form-item label="内容:" prop="content" class="inputDeep">
            <el-input type="textarea" v-model="viewForm.content" readonly></el-input>
          </el-form-item>
        </el-form>
      </el-dialog>

    </el-container>

  </div>
</template>

<script>
  export default {
    name: "ArticleManager",
    data(){
      return{
        mList:[],//文章列表
        total:0,//文章总数
        //获取文章列表的参数对象
        queryInfo:{
          query:'',//查询参数
          pageNum:1,//当前页码
          pageSize:10//每页显示条数
        },
        editDialogVisible: false,
        viewForm: {
          imgUrl:'',
          sender: '',
          title:'',
          content:'',
          type: '',
          sendDate:''
        },

      }
    },
    created() {
      this.getArticleList();
    },
    methods:{
      // 监听 pageSize 改变的事件
      handleSizeChange(newSize) {
        // console.log(newSize)
        this.queryInfo.pageSize = newSize;
        // 重新发起请求文章列表
        this.getArticleList();
      },
      // 监听 当前页码值 改变的事件
      handleCurrentChange(newPage) {
        // console.log(newPage)
        this.queryInfo.pageNum = newPage;
        // 重新发起请求文章列表
        this.getArticleList();
      },
      // 监听 文章状态
      showDialog(id) {
        this.$http.get("/article/getArticleById",{
          params:{
            id:id
          }
        }).then(response => {
          if (response.data.errorCode===0){
            this.editDialogVisible = true;
            this.viewForm = response.data.data;
          }else {
            this.$message.error(response.data.msg);
          }
        });
      },
      // 监听 文章信息对话框的关闭事件
      editDialogClosed() {
        // 表单内容重置为空
        this.$refs.editFormRef.resetFields() // 通过ref引用调用resetFields方法
      },
      getArticleList(){
        this.$http.get("/article/getAll").then(response => {
          if (response.data.errorCode===0){
            this.mList = response.data.data;
            this.total = response.data.data.length;
          }else {
            this.$message.error(response.data.msg);
          }
        });
      },
      getArticle(){
        this.$http.get("/article/getArticle/"+this.queryInfo.query).then(response => {
          if (response.data.errorCode===0){
            this.$message.success(response.data.msg);
            this.mList = response.data.data;
          }else {
            this.$message.error(response.data.msg);
          }
        });
      },
      removeArticleById(id) {
        this.$confirm('此操作将永久删除该文章, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          typeId: 'warning'
        }).then(() => {
          this.$http.delete("/article/delete/"+id).then(response => {
            if (response.data.errorCode===0){
              this.$message.success(response.data.msg);
              this.getArticleList();
            }else {
              this.$message.error(response.data.msg);
            }
          });
        }).catch(() => {
          this.$message({
            typeId: 'info',
            message: '已取消删除'
          })
        })
      }
    }
  }
</script>

<style scoped>
  .el-breadcrumb{
    /*margin-bottom: 20px;*/
    margin-top: 15px;
    margin-bottom: 15px;
    font-size: 13px;
    height: auto;
  }
  .el-card{
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1) !important;
    height: auto;
  }
  .el-table{
    height: auto;
    margin-top: 15px;
    font-size: 12px;
  }
  .el-pagination{
    margin-top: 15px;
  }
  .inputDeep>>>.el-input__inner {
    border: 0;
  }
  .inputDeep>>>.el-textarea__inner {
    border: 0;
    height: 400px;
    /*resize: none;*/
  }

</style>
