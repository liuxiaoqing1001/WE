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
            <el-button type="primary" icon="el-icon-plus" @click="addDialogVisible = true"></el-button>
          </el-col>
          <el-col :span="10">
            <!--搜索区域-->
            <el-input v-model="queryInfo.query" placeholder="请输入内容" clearable @clear="getModuleList">
              <el-button slot="append" icon="el-icon-search" @click="getModule"></el-button>
            </el-input>
          </el-col>
        </el-row>
      </el-card>

      <!--添加模块的对话框-->
      <el-dialog title="添加模块" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
        <!--内容主体区域-->
        <el-form ref="addFormRef" :model="addForm" :rules="addFormRules" label-width="70px">
          <el-form-item label="名字" prop="name">
            <el-input v-model="addForm.name" placeholder="例：Home"></el-input>
          </el-form-item>
          <el-form-item label="路径" prop="route">
            <el-input v-model="addForm.route" placeholder="例：/Home"></el-input>
          </el-form-item>
          <el-form-item label="显示名" prop="text">
            <el-input v-model="addForm.text" placeholder="例：首页"></el-input>
          </el-form-item>
        </el-form>
        <!--底部按钮区域-->
        <span slot="footer" class="dialog-footer">
          <el-button @click="addDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addModule">确 定</el-button>
        </span>
      </el-dialog>

      <el-table :data="mList" style="width: 100%" border stripe>
        <el-table-column prop="id" label="ID"></el-table-column>
        <el-table-column prop="name" label="名字"></el-table-column>
        <el-table-column prop="route" label="路径"></el-table-column>
        <el-table-column prop="text" label="显示名"></el-table-column>
        <el-table-column prop="createDate" label="创建时间"></el-table-column>
        <el-table-column prop="state" label="状态">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.state" @change="moduleStateChanged(scope.row)"></el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <!--修改-->
            <el-tooltip class="item" effect="dark" content="修改" placement="top" :enterable="false">
              <el-button type="primary" size="mini" icon="el-icon-edit" @click="showEditDialog(scope.row.id)"></el-button>
            </el-tooltip>
            <!--删除-->
            <el-tooltip class="item" effect="dark" content="删除" placement="top" :enterable="false">
              <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeModuleById(scope.row.id)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNum"
        :page-sizes="[5, 10,15]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>

      <!--修改模块信息的对话框-->
      <el-dialog title="修改模块" :visible.sync="editDialogVisible" width="50%"  @close="editDialogClosed">
        <!--内容主体区域-->
        <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="70px">
          <el-form-item label="ID">
            <el-input v-model="editForm.id" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="名字" prop="name">
            <el-input v-model="editForm.name"></el-input>
          </el-form-item>
          <el-form-item label="路径" prop="route">
            <el-input v-model="editForm.route"></el-input>
          </el-form-item>
          <el-form-item label="显示名" prop="text">
            <el-input v-model="editForm.text"></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-select v-model="editForm.state" style="float: left">
              <el-option v-for="item in states" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="editDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="editModuleInfo">确 定</el-button>
        </span>
      </el-dialog>

    </el-container>

  </div>
</template>

<script>
  export default {
    name: "Module",
    data(){
      return{
        mList:[],//模块列表
        total:0,//模块总数
        //获取模块列表的参数对象
        queryInfo:{
          query:'',//查询参数
          pageNum:1,//当前页码
          pageSize:5//每页显示条数
        },
        addDialogVisible: false,
        editDialogVisible: false,
        stateValue:'0',
        states: [
          {
            value: '0',
            label: '0 关闭'
          },
          {
            value: '1',
            label: '1 开启'
          }
        ],
        addForm: {
          name: '',
          route: '',
          text: '',
        },
        // 验证规则:添加模块表单的验证规则对象
        addFormRules: {
          name: [
            {
              required: true,
              message: '请输入名字',
              trigger: 'blur'
            }
          ],
          text: [
            {
              required: true,
              message: '请输入显示名',
              trigger: 'blur'
            }
          ]
        },
        editForm: {
          name: '',
          route:'',
          text: '',
          state:''
        },
        // 修改模块信息表单的验证规则对象
        editFormRules: {
          name: [
            {
              required: true, //表示是否必填
              message: '名字不可为空',
              trigger: 'blur' //表示触发时机（blur失去焦点）
            }
          ],
          text: [
            {
              required: true,
              message: '请输入显示名',
              trigger: 'blur'
            }
          ]
        }
      }
    },
    created() {
      this.getModuleList();
    },
    methods:{
      // 监听 pageSize 改变的事件
      handleSizeChange(newSize) {
        // console.log(newSize)
        this.queryInfo.pageSize = newSize;
        // 重新发起请求模块列表
        this.getModuleList();
      },
      // 监听 当前页码值 改变的事件
      handleCurrentChange(newPage) {
        // console.log(newPage)
        this.queryInfo.pageNum = newPage;
        // 重新发起请求模块列表
        this.getModuleList();
      },
      // 监听 当前状态值 改变事件
      moduleStateChanged(moduleInfo) {
        if (moduleInfo.state===true){
          this.stateValue="1";
        }
        console.log(moduleInfo.state);
        this.$http.post("/module/updateState/",{
          id:moduleInfo.id,
          state:this.stateValue
        }).then(response => {
          if (response.data.errorCode===0){
            this.$message.success(response.data.msg);
          }else {
            moduleInfo.state=!moduleInfo.state;
            this.$message.error(response.data.msg);
          }
        });
      },
      // 监听 修改模块状态
      showEditDialog(id) {
        this.$http.get("/module/getModuleById",{
          params:{
            id:id
          }
        }).then(response => {
          if (response.data.errorCode===0){
            this.editDialogVisible = true;
            this.editForm = response.data.data;
          }else {
            this.$message.error(response.data.msg);
          }
        });
      },
      // 监听 修改模块信息对话框的关闭事件
      editDialogClosed() {
        // 表单内容重置为空
        this.$refs.editFormRef.resetFields() // 通过ref引用调用resetFields方法
      },
      // 监听 添加模块对话框的关闭事件
      addDialogClosed() {
        // 表单内容重置为空
        this.$refs.addFormRef.resetFields();
      },
      addModule() {
        this.$refs.addFormRef.validate(async valid => {
          if (!valid){
            this.$message.error('请填写信息！！！');
          }else {
            this.$http.post("/module/add",{
              module:this.addForm
            }).then(response => {
              if (response.data.errorCode===0){
                this.$message.success(response.data.msg);
                this.addDialogVisible = false;
                this.getModuleList();
              }else {
                this.$message.error(response.data.msg);
              }
            });
          }
        })
      },
      getModuleList(){
        this.$http.get("/module/getAll").then(response => {
          if (response.data.errorCode===0){
            this.mList = response.data.data;
            this.total = response.data.data.length;
            // for (let i=0;i<this.total;i++){
            //   console.log(this.mList[i].state)
            //   if(this.mList[i].state===0){
            //     this.mList[i].state=0
            //   }else {
            //     this.mList[i].state=1
            //   }
            // }
          }else {
            this.$message.error(response.data.msg);
          }
        });
      },
      getModule(){
        this.$http.get("/module/getModule/"+this.queryInfo.query).then(response => {
          if (response.data.errorCode===0){
            this.$message.success(response.data.msg);
            this.mList = response.data.data;
          }else {
            this.$message.error(response.data.msg);
          }
        });
      },
      editModuleInfo() {
        this.$refs.editFormRef.validate(valid => {
          if (!valid){
            this.$message.error('请填写信息！！！');
          }else {
            this.$http.post("/module/update",{
              module:this.editForm
            }).then(response => {
              if (response.data.errorCode===0){
                this.$message.success(response.data.msg);
                this.editDialogVisible = false;
                this.getModuleList();
              }else {
                this.$message.error(response.data.msg);
              }
            });
          }
        })
      },
      removeModuleById(id) {
        this.$confirm('此操作将永久删除该模块, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          typeId: 'warning'
        }).then(() => {
          this.$http.delete("/module/delete/"+id).then(response => {
            if (response.data.errorCode===0){
              this.$message.success(response.data.msg);
              this.getModuleList();
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
</style>
