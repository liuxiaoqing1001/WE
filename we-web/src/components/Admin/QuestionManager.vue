<template>
  <div>
    <el-container>
      <el-header>
        <!--面包屑导航区域-->
        <el-breadcrumb >
          <el-breadcrumb-item :to="{ path: '/QuestionManager' }">基础题库</el-breadcrumb-item>
        </el-breadcrumb>
      </el-header>

      <!--卡片视图区域-->
      <el-card>
        <el-row :gutter="20">
          <el-col :span="2">
            <el-button type="primary" icon="el-icon-plus" @click="addDialogVisible = true"></el-button>
          </el-col>
<!--          <el-col :span="10">-->
<!--            &lt;!&ndash;搜索区域&ndash;&gt;-->
<!--            <el-input v-model="queryInfo.query" placeholder="请输入内容" clearable @clear="getQuestionList">-->
<!--              <el-button slot="append" icon="el-icon-search" @click="getQuestion"></el-button>-->
<!--            </el-input>-->
<!--          </el-col>-->
        </el-row>
      </el-card>

      <!--添加模块的对话框-->
      <el-dialog title="添加" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
        <!--内容主体区域-->
        <el-form ref="addFormRef" :model="addForm" :rules="addFormRules" label-width="70px">
          <el-form-item label="题目" prop="title">
            <el-input v-model="addForm.title" placeholder="例：平均每天的睡眠时间"></el-input>
          </el-form-item>
          <el-form-item label="选项" prop="item">
            <el-input v-model="addForm.item" placeholder="例（分号隔开）：小于4小时;4-6小时;7-8小时;大于8小时"></el-input>
          </el-form-item>
<!--          <el-form-item label="答案" prop="answer">-->
<!--            <el-input v-model="addForm.answer" placeholder="例：A"></el-input>-->
<!--          </el-form-item>-->
        </el-form>
        <!--底部按钮区域-->
        <span slot="footer" class="dialog-footer">
          <el-button @click="addDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addQuestion">确 定</el-button>
        </span>
      </el-dialog>

      <el-table :data="qList" style="width: 100%" border stripe>
        <el-table-column prop="qid" label="ID"></el-table-column>
        <el-table-column prop="title" label="题目"></el-table-column>
        <el-table-column prop="date" label="创建时间"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <!--详情-->
            <el-tooltip class="item" effect="dark" content="详情" placement="top" :enterable="false">
              <el-button type="primary" round size="mini" icon="el-icon-view" @click="showDialog(scope.row.qid)"></el-button>
            </el-tooltip>
            <!--删除-->
            <el-tooltip class="item" effect="dark" content="删除" placement="top" :enterable="false">
              <el-button type="danger" round size="mini" icon="el-icon-delete" @click="removeQuestionById(scope.row.qid)"></el-button>
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

      <!--基础题库信息的对话框-->
      <el-dialog title="详情" :visible.sync="editDialogVisible" width="50%"  @close="editDialogClosed">
        <!--内容主体区域 :rules="editFormRules"-->
        <el-form :model="viewForm" ref="editFormRef" label-width="70px">
          <el-form-item label="ID:" prop="qid" class="inputDeep">
            <el-input v-model="viewForm.qid" readonly></el-input>
          </el-form-item>
          <el-form-item label="题目:" prop="title" class="inputDeep">
            <el-input v-model="viewForm.title" readonly></el-input>
          </el-form-item>
          <el-form-item label="选项:" prop="item" class="inputDeep">
            <el-input v-model="viewForm.item" readonly></el-input>
          </el-form-item>
        </el-form>
      </el-dialog>

    </el-container>

  </div>
</template>

<script>
  export default {
    name: "QuestionManager",
    data(){
      return{
        qList:[],//基础题库列表
        total:0,//基础题库总数
        //获取基础题库列表的参数对象
        queryInfo:{
          query:'',//查询参数
          pageNum:1,//当前页码
          pageSize:10//每页显示条数
        },
        addDialogVisible: false,
        editDialogVisible: false,
        addForm: {
          title: '',
          item: '',
          answer: '',
        },
        addFormRules: {
          title: [
            {
              required: true,
              message: '请输入题目',
              trigger: 'blur'
            }
          ],
          item: [
            {
              required: true,
              message: '请输入选项，分号隔开',
              trigger: 'blur'
            }
          ]
        },
        viewForm: {
          qid:'',
          title:'',
          item:'',
          answer: '',
          date:''
        },

      }
    },
    created() {
      this.getQuestionList();
    },
    methods:{
      // 监听 pageSize 改变的事件
      handleSizeChange(newSize) {
        // console.log(newSize)
        this.queryInfo.pageSize = newSize;
        // 重新发起请求基础题库列表
        this.getQuestionList();
      },
      // 监听 当前页码值 改变的事件
      handleCurrentChange(newPage) {
        // console.log(newPage)
        this.queryInfo.pageNum = newPage;
        // 重新发起请求基础题库列表
        this.getQuestionList();
      },
      // 监听 基础题库状态
      showDialog(id) {
        this.$http.get("/test/getQuestionByQid",{
          params:{
            qid:id
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
      // 监听 基础题库信息对话框的关闭事件
      editDialogClosed() {
        // 表单内容重置为空
        this.$refs.editFormRef.resetFields() // 通过ref引用调用resetFields方法
      },
      addDialogClosed() {
        // 表单内容重置为空
        this.$refs.addFormRef.resetFields();
      },
      addQuestion() {
        this.$refs.addFormRef.validate(async valid => {
          if (!valid){
            this.$message.error('请填写信息！！！');
          }else {
            this.$http.post("/test/addQuestion",{
              question:this.addForm
            }).then(response => {
              if (response.data.errorCode===0){
                this.$message.success(response.data.msg);
                this.addDialogVisible = false;
                this.getQuestionList();
              }else {
                this.$message.error(response.data.msg);
              }
            });
          }
        })
      },
      getQuestionList(){
        this.$http.get("/test/getAllQuestion").then(response => {
          if (response.data.errorCode===0){
            this.qList = response.data.data;
            this.total = response.data.data.length;
          }else {
            this.$message.error(response.data.msg);
          }
        });
      },
      // getQuestion(){
      //   this.$http.get("/test/getQuestion/"+this.queryInfo.query).then(response => {
      //     if (response.data.errorCode===0){
      //       this.$message.success(response.data.msg);
      //       this.qList = response.data.data;
      //     }else {
      //       this.$message.error(response.data.msg);
      //     }
      //   });
      // },
      removeQuestionById(id) {
        this.$confirm('此操作将永久删除该题, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          typeId: 'warning'
        }).then(() => {
          this.$http.delete("/test/deleteQuestion/"+id).then(response => {
            if (response.data.errorCode===0){
              this.$message.success(response.data.msg);
              this.getQuestionList();
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
