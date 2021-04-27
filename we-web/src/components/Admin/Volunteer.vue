<template>
  <div>
    <el-container>
      <el-header>
        <!--面包屑导航区域-->
        <el-breadcrumb >
          <el-breadcrumb-item :to="{ path: '/User' }">用户管理</el-breadcrumb-item>
          <el-breadcrumb-item>自愿者</el-breadcrumb-item>
        </el-breadcrumb>
      </el-header>

      <!--卡片视图区域-->
      <el-card>
        <el-row :gutter="20">
          <el-col :span="1.5">
            <el-button type="primary" @click="getV">审核通过</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="primary" @click="getNotV">审核中</el-button>
          </el-col>
          <el-col :span="10">
            <!--搜索区域-->
            <el-input v-model="queryInfo.query" placeholder="请输入内容" clearable @clear="getUserList">
              <el-button slot="append" icon="el-icon-search" @click="getVolunteer"></el-button>
            </el-input>
          </el-col>
        </el-row>
      </el-card>
      <!--自愿者列表区域-->
      <!--    边框：border属性，设置为true即可启用-->
      <!--    隔行变色：stripe属性,创建带斑马纹的表格，默认为false，设置为true即为启用-->
      <el-table :data="userList" style="width: 100%" border stripe>
        <el-table-column prop="id" label="ID"></el-table-column>
        <el-table-column prop="name" label="用户名"></el-table-column>
        <el-table-column prop="realName" label="真实姓名"></el-table-column>
        <el-table-column prop="phoneNum" label="手机号"></el-table-column>
<!--        <el-table-column prop="gender" label="性别"></el-table-column>-->
<!--        <el-table-column prop="birth" label="出生日期"></el-table-column>-->
<!--        <el-table-column prop="comeFrom" label="邮箱"></el-table-column>-->
<!--        <el-table-column prop="identity" label="身份证号"></el-table-column>-->
<!--        <el-table-column prop="role" label="角色"></el-table-column>-->
        <el-table-column prop="state" label="状态"></el-table-column>
        <el-table-column prop="certificate" label="执业资格证">
<!--          <template slot-scope="scope">-->
<!--            <el-button-->
<!--              size="mini"-->
<!--              type="text"-->
<!--              icon="el-icon-download"-->
<!--              @click="downFile(scope.row.certificate)"-->
<!--              v-hasPermi="['hospital:treatment:download']">-->
<!--              下载执业资格证-->
<!--            </el-button>-->
<!--          </template>-->
        </el-table-column>
        <el-table-column prop="diploma" label="学历学位证">
<!--          <template slot-scope="scope">-->
<!--            <el-button-->
<!--              size="mini"-->
<!--              type="text"-->
<!--              icon="el-icon-download"-->
<!--              @click="downFile(scope.row.diploma)"-->
<!--              v-hasPermi="['hospital:treatment:download']">-->
<!--              下载执业资格证-->
<!--            </el-button>-->
<!--          </template>-->
        </el-table-column>
<!--        <el-table-column prop="entryTime" label="申请时间"></el-table-column>-->
        <el-table-column label="操作">
          <template slot-scope="scope">
            <!--详情-->
            <el-tooltip class="item" effect="dark" content="详情" placement="top" :enterable="false">
              <el-button type="primary" size="mini" @click="showEditDialog(scope.row.name)">详情</el-button>
            </el-tooltip>
            <!--审核通过-->
            <el-tooltip class="item" effect="dark" content="审核通过" placement="top" :enterable="false">
              <el-button type="danger" size="mini" @click="changeUserRoleById(scope.row.name)">审核通过</el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <!--分页区域-->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNum"
        :page-sizes="[5, 10,15]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
      <!--修改用户信息的对话框-->
      <el-dialog title="自愿者详情" :visible.sync="showDialogVisible" width="50%" @close="showDialogClosed">
        <!--内容主体区域-->
        <el-form :model="VForm" ref="VFormRef" label-width="70px">
          <el-form-item label="ID" class="inputDeep">
            <el-input v-model="VForm.id" readonly></el-input>
          </el-form-item>
          <el-form-item label="用户名" prop="name" class="inputDeep">
            <el-input v-model="VForm.name" readonly></el-input>
          </el-form-item>
          <el-form-item label="真实姓名" prop="name" class="inputDeep">
            <el-input v-model="VForm.realName" readonly></el-input>
          </el-form-item>
          <el-form-item label="身份证号" prop="identity" class="inputDeep">
            <el-input v-model="VForm.identity" readonly></el-input>
          </el-form-item>
          <el-form-item label="性别" class="inputDeep">
            <el-input v-model="VForm.gender" readonly></el-input>
          </el-form-item>
          <el-form-item label="籍贯" prop="comeFrom" class="inputDeep">
            <el-input v-model="VForm.comeFrom" readonly></el-input>
          </el-form-item>
          <el-form-item label="手机号" prop="phoneNum" class="inputDeep">
            <el-input v-model="VForm.phoneNum" readonly></el-input>
          </el-form-item>
          <el-form-item label="出生日期" class="inputDeep">
            <el-date-picker v-model="VForm.birth" type="datetime"
                            format="yyyy年MM月dd日" value-format="yyyy年MM月dd日" style="float: left" readonly>
            </el-date-picker>
          </el-form-item>
          <el-form-item label="申请时间" prop="entryTime" class="inputDeep">
            <el-input v-model="VForm.entryTime" readonly></el-input>
          </el-form-item>
          <el-form-item label="执业资格证书" prop="certificate" class="inputDeep">
            <el-input v-model="VForm.certificate" readonly></el-input>
            <a @click="showCertificate" class="showFile">在线查看</a>
          </el-form-item>
          <el-form-item label="学历学位证书" prop="diploma" class="inputDeep">
            <el-input v-model="VForm.diploma" readonly></el-input>
            <a @click="showDiploma" class="showFile">在线查看</a>
          </el-form-item>
        </el-form>
      </el-dialog>

      <el-dialog title="执业资格证书" :visible.sync="isShowFileCertificate" width="60%" @close="showDialogClosed">
<!--        <pdf ref="pdf" :src="src" v-for="i in numPages" :key="i" :page="i"></pdf>-->
        <p class="arrow">
          <span @click="changePdfPage(0)" class="turn" :class="{grey:currentPage===1}">上一页</span>
          << {{currentPage}} / {{pageCount}} >>
          <span @click="changePdfPage(1)" class="turn" :class="{grey:currentPage===pageCount}">下一页</span>
        </p>
        <pdf
          :src="src"
          :page="currentPage"
          @num-pages="pageCount=$event"
          @page-loaded="currentPage=$event"
          @loaded="loadPdfHandler">
        </pdf>

      </el-dialog>

      <el-dialog title="学历学位证书" :visible.sync="isShowFileDiploma" width="60%" @close="showDialogClosed">
        <p class="arrow">
          <span @click="changePdfPage(0)" class="turn" :class="{grey:currentPage===1}">上一页</span>
          << {{currentPage}} / {{pageCount}} >>
          <span @click="changePdfPage(1)" class="turn" :class="{grey:currentPage===pageCount}">下一页</span>
        </p>
        <pdf
          :src="src"
          :page="currentPage"
          @num-pages="pageCount=$event"
          @page-loaded="currentPage=$event"
          @loaded="loadPdfHandler">
        </pdf>
<!--        <pdf ref="pdf" :src="src"></pdf>-->
      </el-dialog>

    </el-container>
  </div>
</template>

<script>
  import pdf from 'vue-pdf'

  export default {
    components:{
      pdf
    },
    name: "Volunteer",
    data(){
      return{
        src:"",
        currentPage: 0, // pdf文件页码
        pageCount: 0, // pdf文件总页数
        isShowFileCertificate:false,
        isShowFileDiploma:false,
        userList:[],//自愿者列表
        total:0,//自愿者总数
        //获取自愿者列表的参数对象
        queryInfo:{
          query:'',//查询参数
          pageNum:1,//当前页码
          pageSize:5//每页显示条数
        },
        showDialogVisible: false, // 控制修改用户信息对话框是否显示
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
        VForm: {
          id:'',
          name:'',
          realName: '',
          gender: '',
          birth: '',
          comeFrom: '',
          phoneNum: '',
          identity: '',
          state:'',
          entryTime:'',
          certificate: '',
          diploma: '',
        },
      }
    },
    created() {
      this.getUserList();
      // 有时PDF文件地址会出现跨域的情况,这里最好处理一下　　　　
      this.src = pdf.createLoadingTask(this.src);
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
        this.$http.get("/user/getAllVolunteer").then(response => {
          if (response.data.errorCode===0){
            this.userList = response.data.data;
            this.total = response.data.data.length;
          }else {
            this.$message.error(response.data.msg);
          }
        });
      },
      getV(){
        this.$http.get("/user/getV").then(response => {
          if (response.data.errorCode===0){
            this.$message.success(response.data.msg);
            this.userList = response.data.data;
          }else {
            this.$message.error(response.data.msg);
          }
        });
      },
      getNotV(){
        this.$http.get("/user/getNotV").then(response => {
          if (response.data.errorCode===0){
            this.$message.success(response.data.msg);
            this.userList = response.data.data;
          }else {
            this.$message.error(response.data.msg);
          }
        });
      },
      getVolunteer(){
        this.$http.get("/user/getVolunteer/"+this.queryInfo.query).then(response => {
          if (response.data.errorCode===0){
            this.$message.success(response.data.msg);
            this.userList = response.data.data;
          }else {
            this.$message.error(response.data.msg);
          }
        });
      },
      // 监听 修改用户信息对话框的关闭事件
      showDialogClosed() {
        // 表单内容重置为空
        this.$refs.editFormRef.resetFields(); // 通过ref引用调用resetFields方法
      },
      showEditDialog(name) {
        //查看详情
        this.$http.get("/user/getVolunteerByName",{
          params:{
            name:name
          }
        }).then(response => {
          if (response.data.errorCode===0){
            this.showDialogVisible = true;
            this.VForm = response.data.data;
            if (response.data.data.gender==="0"){
              this.VForm.gender="女";
            }else{
              this.VForm.gender="男";
            }
          }else {
            this.$message.error(response.data.msg);
          }
        });
      },
      changeUserRoleById(name){
        this.$confirm('此操作将通过该用户提交的自愿者咨询师申请, 是否继续?', '提示', {
          confirmButtonText: '审核通过',
          cancelButtonText: '取消',
          typeId: 'warning'
        }).then(() => {
          // 修改状态
          this.$http.post("/user/updateVSateByName/",{
            name:name,
            state:"通过"
          }).then(response => {
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
            message: '已取消审核'
          })
        })
      },
      showCertificate(){
        this.src="http://storage.xuetangx.com/public_assets/xuetangx/PDF/PlayerAPI_v1.0.6.pdf";
        this.isShowFileCertificate=true;
      },
      showDiploma(){
        // this.src="/home/liu/liu_source/softwareCode/ideaCode/WE/we-idea/target/classes/static/file/181827a2fedc461da9d3357486a7934f.pdf"
        // this.src="http://storage.xuetangx.com/public_assets/xuetangx/PDF/PlayerAPI_v1.0.6.pdf";
        this.src="/static/redis.pdf";
        this.isShowFileDiploma=true;
      },

      // 改变PDF页码,val传过来区分上一页下一页的值,0上一页,1下一页
      changePdfPage (val) {
        // console.log(val)
        if (val === 0 && this.currentPage > 1) {
          this.currentPage--;
        }
        if (val === 1 && this.currentPage < this.pageCount) {
          this.currentPage++;
        }
      },

      // pdf加载时
      loadPdfHandler (e) {
        this.currentPage = 1; // 加载的时候先加载第一页
      },

      downFile(file){
        console.log(file)
        // this.$http.post("/file/down",{
        //   file:file
        // }).then(response => {
        //   if (response.data.errorCode===0){
        //     this.$message.success(response.data.msg);
        //   }else {
        //     this.$message.error(response.data.msg);
        //   }
        // });
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
  .showFile{
    color:blue;
    float: left;
    margin-left: 15px;
  }
  a:hover{
    cursor: pointer;
  }

  .turn{
    color: blue;
    margin-left: 10px;
    margin-right: 10px;
  }
  span:hover{
    cursor: pointer;
  }
</style>
