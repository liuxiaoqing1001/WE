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
            <el-button type="primary" icon="el-icon-plus" @click="addDialogVisible = true"></el-button>
          </el-col>
          <el-col :span="10">
            <!--搜索区域-->
            <el-input v-model="queryInfo.query" placeholder="请输入内容" clearable @clear="getUserList">
              <el-button slot="append" icon="el-icon-search" @click="getUser"></el-button>
            </el-input>
          </el-col>
        </el-row>
      </el-card>

      <!--添加用户的对话框-->
      <el-dialog title="添加用户" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
        <!--内容主体区域-->
        <el-form ref="addFormRef" :model="addForm" :rules="addFormRules" label-width="70px">
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="addForm.phone"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="addForm.password"></el-input>
          </el-form-item>
        </el-form>
        <!--底部按钮区域-->
        <span slot="footer" class="dialog-footer">
          <el-button @click="addDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addUser">确 定</el-button>
        </span>
      </el-dialog>

      <!--用户列表区域-->
      <!--    边框：border属性，设置为true即可启用-->
      <!--    隔行变色：stripe属性,创建带斑马纹的表格，默认为false，设置为true即为启用-->
      <el-table :data="userList" style="width: 100%" border stripe>
        <el-table-column prop="id" label="ID">
        </el-table-column>
        <el-table-column prop="name" label="用户名"></el-table-column>
        <el-table-column prop="phone" label="手机号"></el-table-column>
        <el-table-column prop="sex" label="性别"></el-table-column>
        <el-table-column prop="birthday" label="出生日期"></el-table-column>
        <el-table-column prop="email" label="邮箱"></el-table-column>
        <el-table-column prop="role" label="角色"></el-table-column>
        <el-table-column prop="regdate" label="创建时间"></el-table-column>
        <el-table-column label="操作">
          <!-- 作用域插槽 -->
          <template slot-scope="scope">
            <!--修改-->
            <!--          enterable属性：表示鼠标是否可进入到 tooltip 中，默认是true开的，需要把它绑定为false，鼠标移到上面提示就消失了-->
            <el-tooltip class="item" effect="dark" content="修改" placement="top" :enterable="false">
              <el-button type="primary" size="mini" icon="el-icon-edit" @click="showEditDialog(scope.row.id)"></el-button>
            </el-tooltip>
            <!--删除-->
            <el-tooltip class="item" effect="dark" content="删除" placement="top" :enterable="false">
              <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeUserById(scope.row.id)"></el-button>
            </el-tooltip>
            <!--          &lt;!&ndash;分配角色&ndash;&gt;-->
            <!--          <el-tooltip class="item" effect="dark" content="分配角色" placement="top" :enterable="false">-->
            <!--            <el-button type="warning" size="mini" icon="el-icon-setting"></el-button>-->
            <!--          </el-tooltip>-->
          </template>

        </el-table-column>
      </el-table>
      <!--分页区域-->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNum"
        :page-sizes="[1, 2, 5, 10]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>

      <!--修改用户信息的对话框-->
      <el-dialog title="修改用户" :visible.sync="editDialogVisible" width="50%"  @close="editDialogClosed">
        <!--内容主体区域-->
        <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="70px">
          <el-form-item label="ID">
            <el-input v-model="editForm.id" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="用户名" prop="name">
            <el-input v-model="editForm.name"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-select v-model="editForm.sex" style="float: left">
              <el-option v-for="item in sexes" :key="item.label" :label="item.label" :value="item.label">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="editForm.email"></el-input>
          </el-form-item>
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="editForm.phone"></el-input>
          </el-form-item>
          <el-form-item label="出生日期">
            <el-date-picker v-model="editForm.birthday" type="datetime"
                            format="yyyy年MM月dd日" value-format="yyyy年MM月dd日" style="float: left">
            </el-date-picker>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="editDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="editUserInfo">确 定</el-button>
        </span>
      </el-dialog>
    </el-container>

  </div>
</template>

<script>
  export default {
    name: "User",
    data(){
      // 验证邮箱的规则(验证规则 验证的值 回调函数)
      var checkEmail = (rule, value, callback) => {
        // 验证邮箱的正则表达式
        const regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
        if (regEmail.test(value)) {
          // 合法的邮箱
          return callback()
        }
        callback(new Error('请输入合法的邮箱'))
      };
      // 验证手机的规则
      var checkPhone = (rule, value, callback) => {
        const regMobile = /^1[0-9][0-9]{9}$/;
        if (regMobile.test(value)) {
          // 合法的手机
          return callback()
        }
        callback(new Error('请输入合法的手机号'))
      };
      return{
        userList:[],//用户列表
        total:0,//用户总数
        //获取用户列表的参数对象
        queryInfo:{
          query:'',//查询参数
          pageNum:1,//当前页码
          pageSize:5//每页显示条数
        },
        addDialogVisible: false, // 控制添加用户对话框是否显示
        editDialogVisible: false, // 控制修改用户信息对话框是否显示
        // 数据绑定对象:添加用户的表单数据
        addForm: {
          phone: '',
          password: ''
        },
        // 验证规则:添加用户表单的验证规则对象
        addFormRules: {
          phone: [
            {
              required: true,
              message: '请输入手机号',
              trigger: 'blur' //表示触发时机（blur失去焦点）
            },
            {
              // min: 3,
              // max: 11,
              // message: '长度在 3 到 11 个字符',
              validator: checkPhone,
              trigger: 'blur'
            }
          ],
          password: [
            {
              required: true,
              message: '请输入用户密码',
              trigger: 'blur'
            },
            {
              min: 6,
              max: 15,
              message: '长度在 6 到 15 个字符',
              trigger: 'blur'
            }
          ]
        },
        //多条件查询是否性别下拉框
        sexes: [
          {
            value: '1',
            label: '男'
          },
          {
            value: '0',
            label: '女'
          }
        ],
        // 修改用户信息的表单数据
        editForm: {
          name: '',
          sex:'',
          email: '',
          phone: '',
          birthday:''
        },
        // 修改用户信息表单的验证规则对象
        editFormRules: {
          name: [
            {
              required: true, //表示是否必填
              message: '账号不可为空',
              trigger: 'blur' //表示触发时机（blur失去焦点）
            },
            {
              min: 3,
              max: 11,
              message: '长度在 3 到 11 个字符',
              trigger: 'blur'
            }
          ],
          email: [
            {
              required: true,
              message: '请输入用户邮箱',
              trigger: 'blur'
            },
            {
              validator: checkEmail,
              trigger: 'blur'
            }
          ],
          phone: [
            {
              required: true,
              message: '请输入用户手机',
              trigger: 'blur'
            },
            {
              validator: checkPhone,
              trigger: 'blur'
            }
          ]
        }
      }
    },
    created() {
      this.getUserList();
    },
    methods:{
      // 监听 pageSize 改变的事件
      handleSizeChange(newSize) {
        // console.log(newSize)
        this.queryInfo.pageSize = newSize;
        // 重新发起请求用户列表
        this.getUserList();
      },
      // 监听 当前页码值 改变的事件
      handleCurrentChange(newPage) {
        // console.log(newPage)
        this.queryInfo.pageNum = newPage;
        // 重新发起请求用户列表
        this.getUserList();
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
      },
      getUser(){
        this.$http.get("/user/getUser/"+this.queryInfo.query).then(response => {
          if (response.data.errorCode===0){
            this.$message.success(response.data.msg);
            this.userList = response.data.data;
          }else {
            this.$message.error(response.data.msg);
          }
        });
      },
      // 监听 添加用户对话框的关闭事件
      addDialogClosed() {
        // 表单内容重置为空
        this.$refs.addFormRef.resetFields();
      },
      // 点击按钮 添加新用户
      addUser() {
        this.$refs.addFormRef.validate(async valid => {
          if (!valid){
            this.$message.error('请填写信息！！！');
          }else {
            this.$http.post("/user/register",{
              phone:this.addForm.phone,
              password:this.addForm.password
            }).then(response => {
              if (response.data.errorCode===0){
                this.$message.success(response.data.msg);
                // 隐藏添加用户的对话框
                this.addDialogVisible = false;
                // 重新发起请求用户列表
                this.getUserList();
              }else {
                this.$message.error(response.data.msg);
              }
            });
          }
        })
      },
      // 监听 修改用户信息对话框的关闭事件
      editDialogClosed() {
        // 表单内容重置为空
        this.$refs.editFormRef.resetFields() // 通过ref引用调用resetFields方法
      },
      // 监听 修改用户状态
      showEditDialog(id) {
        this.$http.get("/user/getUserById",{
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
      // 点击按钮 修改用户信息
      editUserInfo() {
        this.$refs.editFormRef.validate(valid => {
          if (!valid){
            this.$message.error('请填写信息！！！');
          }else {
            this.$http.post("/user/update",{
              user:this.editForm
            }).then(response => {
              if (response.data.errorCode===0){
                this.$message.success(response.data.msg);
                this.editDialogVisible = false;
                this.getUserList();
              }else {
                this.$message.error(response.data.msg);
              }
            });
          }
        })
      },
      // 根据ID删除对应的用户信息
      removeUserById(id) {
        // // 两种方式：1. async，await
        // const confirmResult = await this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        //   confirmButtonText: '确定',
        //   cancelButtonText: '取消',
        //   type: 'warning'
        // }).catch(err => err);
        // if (confirmResult === 'confirm') {
        //   return this.$message.success('已取消删除')
        // }

        //2.
        this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          typeId: 'warning'
        }).then(() => {
          this.$http.delete("/user/delete/"+id).then(response => {
            if (response.data.errorCode===0){
              this.$message.success(response.data.msg);
              this.getUserList();
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
