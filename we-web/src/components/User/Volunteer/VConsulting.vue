<template>
  <div>
    <h5>我的咨询者</h5>
    <el-container>
      <!--用户列表区域-->
      <!--    边框：border属性，设置为true即可启用-->
      <!--    隔行变色：stripe属性,创建带斑马纹的表格，默认为false，设置为true即为启用-->
      <el-table :data="userList" style="width: 100%" border stripe>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="phone" label="电话"></el-table-column>
        <el-table-column prop="state" label="状态"></el-table-column>
        <el-table-column label="详情">

          <template slot-scope="scope">
            <el-tooltip class="item" effect="dark" content="修改" placement="top" :enterable="false">
              <el-button type="primary" circle size="small" icon="el-icon-edit" @click="edit(scope.row.id)"></el-button>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="查看" placement="top" :enterable="false">
              <el-button type="info" circle size="small" icon="el-icon-view" @click="showMore(scope.row.id)"></el-button>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="联系" placement="top" :enterable="false">
              <el-button type="success" circle size="small" class="el-icon-chat-line-round" @click="toChat(scope.row.id)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <!--修改用户信息的对话框-->
      <el-dialog title="修改用户状态" :visible.sync="editDialogVisible" width="50%"  @close="editDialogClosed">
        <!--内容主体区域-->
        <el-form :model="userForm" ref="editFormRef" label-width="70px">
          <el-form-item label="状态">
            <el-select v-model="userForm.state" style="float: left">
              <el-option v-for="item in states" :key="item.label" :label="item.label" :value="item.label">
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
<!--          <el-button @click="editDialogVisible = false">取 消</el-button>-->
          <el-button type="primary" @click="ediInfo">确 定</el-button>
        </span>
      </el-dialog>

      <el-dialog title="详情" :visible.sync="showDialogVisible" width="50%" @close="editDialogClosed">
        <!--内容主体区域-->
        <el-form :model="userForm" ref="editFormRef" label-width="70px">
          <el-form-item label="ID" class="inputDeep">
            <el-input v-model="userForm.id" readonly></el-input>
          </el-form-item>
          <el-form-item label="用户名" class="inputDeep">
            <el-input v-model="userForm.name" readonly></el-input>
          </el-form-item>
          <el-form-item label="手机号" class="inputDeep">
            <el-input v-model="userForm.phone" readonly></el-input>
          </el-form-item>
          <el-form-item label="状态" class="inputDeep">
            <el-input v-model="userForm.state" readonly></el-input>
          </el-form-item>
          <el-form-item label="基础测试" class="inputDeep">
            <el-input type="textarea" v-model="userForm.testStr" readonly></el-input>
          </el-form-item>
        </el-form>
      </el-dialog>
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
            pageNum:1,//当前页码
            pageSize:10//每页显示条数
          },
          editDialogVisible: false,
          showDialogVisible:false,
          userForm: {
            name: '',
            phone: '',
            state:'',
            testStr:''
          },
          states: [
            {
              value: '0',
              label: '无'
            },
            {
              value: '1',
              label: '轻微'
            },
            {
              value: '2',
              label: '一般'
            },
            {
              value: '3',
              label: '严重'
            }
          ],
        }
      },
      created() {
        this.getConsultantsById();
      },
      methods:{
        handleSizeChange(newSize) {
          // console.log(newSize)
          this.queryInfo.pageSize = newSize;
          // 重新发起请求用户列表
          this.getConsultantsById();
        },
        // 监听 当前页码值 改变的事件
        handleCurrentChange(newPage) {
          // console.log(newPage)
          this.queryInfo.pageNum = newPage;
          // 重新发起请求用户列表
          this.getConsultantsById();
        },
        editDialogClosed() {
          // 表单内容重置为空
          this.$refs.editFormRef.resetFields(); // 通过ref引用调用resetFields方法
          this.userForm ={};
        },
        showDialogVisible(){
          this.$refs.editFormRef.resetFields() // 通过ref引用调用resetFields方法
        },
        getConsultantsById(){
          this.$http.get("/user/getConsultantsByVId/"+window.sessionStorage.getItem("id")).then(response => {
            if (response.data.errorCode===0){
              this.userList = response.data.data;
            }else {
              this.$message.error(response.data.msg);
            }
          });
        },
        edit(id) {
          this.getById(id);
          this.editDialogVisible = true;
        },
        getById(id){
          this.$http.get("/user/getByCId",{
            params:{
              id:id
            }
          }).then(response => {
            if (response.data.errorCode===0){
              this.userForm = response.data.data;

              // this.$http.get("/test/getByCId",{
              //   params:{
              //     id:id
              //   }
              // }).then(res => {
              //   if (res.data.errorCode===0){
              //     var testStr ="";
              //     for (var i=0;i<res.data.data.length;i++){
              //       testStr += (i+1)+": "+res.data.data[i].title+"\n"+"\t回答："+res.data.data[i].answer+"\n";
              //     }
              //     this.userForm = response.data.data;
              //     this.userForm.testStr = testStr;
              //   }else {
              //     this.$message.error(res.data.msg);
              //   }
              // });

            }else {
              this.$message.error(response.data.msg);
            }
          });
        },
        ediInfo() {
          this.$http.post("/user/updateStateById",{
            state:this.userForm.state,
            id:this.userForm.id
          }).then(response => {
            if (response.data.errorCode===0){
              this.$message.success(response.data.msg);
              this.editDialogVisible = false;
              this.getConsultantsById();
            }else {
              this.$message.error(response.data.msg);
            }
          });
        },
        showMore(id){
          this.getById(id);
          this.showDialogVisible = true;
        },
        toChat(id){
          window.sessionStorage.setItem("cid",id);
          // this.$router.push("/ChatRoom");
          window.open("/#/ChatRoom","_blank");
        }
      }
    }
</script>

<style scoped>
  .el-table{
    height: auto;
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
