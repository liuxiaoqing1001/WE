<template>
<!--  修改和展示信息-->
  <div class="VForm">
    <h3>咨询师个人信息</h3>
    <el-form :model="VForm" :rules="rules" ref="ruleForm" label-width="100px">
      <el-form-item label="用户名" prop="name">
        <span style="float: left;margin-left: 10px">{{VForm.name}}</span>
      </el-form-item>
      <el-form-item label="姓名" prop="realName">
        <el-input v-model="VForm.realName"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-select v-model="VForm.gender" placeholder="请选择性别" class="special">
          <el-option v-for="item in optionSender" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
      </el-form-item>
<!--      <el-form-item label="出生年月" prop="birth">-->
<!--        <el-date-picker v-model="VForm.birth" type="date" placeholder="选择日期" class="special"></el-date-picker>-->
<!--      </el-form-item>-->
      <el-form-item label="籍贯" prop="comeFrom">
        <el-input v-model="VForm.comeFrom"></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="phoneNum">
        <el-input v-model="VForm.phoneNum"></el-input>
      </el-form-item>
      <el-form-item label="身份证号" prop="identity">
        <el-input v-model="VForm.identity"></el-input>
      </el-form-item>
      <el-form-item label="执业资格证" prop="certificate">
<!--        action	必选参数，上传的地址	string-->
<!--        multiple	是否支持多选文件	boolean-->
<!--        data	上传时附带的额外参数	object-->
<!--        accept	接受上传的文件类型（thumbnail-mode 模式下此参数无效）	string	—	—-->
<!--        on-change	文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用	function(file, fileList)	—	—-->
<!--        before-upload	文件上传前的回调，参数为上传的文件，若返回 false 或者返回 Promise 且被 reject，则停止上传。	function(file)	—	—-->
<!--        auto-upload	是否在选取文件后立即进行上传	boolean	—	true-->
<!--        file-list	上传的文件列表, 例如: [{name: 'food.jpg', url: 'https://xxx.cdn.com/xxx.jpg'}]	array	—	[]-->
<!--        limit	最大允许上传个数	number	—	—-->

<!--        未显示文件，可在线查看-->
        <el-upload
          class="special"
          v-model="VForm.certificate"
          action="http://127.0.0.1:8618/file/upload"
          :show-file-list="true"
          :on-success="uploadFileHandler_certificate"
          :on-error="uploadFileErrorHandler"
          :on-progress="uploadFileOnProgressHandler">
          <el-button size="small" type="primary">选择文件</el-button>
          <span style="color: indianred;font-size: 10px">仅支持.doc/.docx/.pdf/.png/.jpeg 文件上传</span>
        </el-upload>
      </el-form-item>
      <el-form-item label="学历学位证" prop="diploma">
        <el-upload
          class="special"
          v-model="VForm.diploma"
          action="http://127.0.0.1:8618/file/upload"
          :show-file-list="true"
          :on-success="uploadFileHandler_diploma"
          :on-error="uploadFileErrorHandler"
          :on-progress="uploadFileOnProgressHandler">
          <el-button size="small" type="primary">选择文件</el-button>
          <span style="color: indianred;font-size: 10px">仅支持.doc/.docx/.pdf/.png/.jpeg 文件上传</span>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button @click="comeBack()" class="option">取消</el-button>
        <el-button type="primary" @click="submitForm('ruleForm')" class="option">修改</el-button>
      </el-form-item>
    </el-form>
  </div>

</template>

<script>
    export default {
      name: "VCenter",
      data() {
        return {
          visible: false,
          loading: null,
          filePack:{
            certificate: '',
            diploma: ''
          },
          VForm: {
            name:'',
            realName: '',
            gender: '',
            birth: '',
            comeFrom: '',
            phoneNum: '',
            identity: '',
            certificate: '',
            diploma: '',
          },
          rules: {
            name: [
              {
                required: true,
                message: '请输入您的姓名',
                trigger: 'blur'
              }
            ],
            gender: [
              {
                required: true,
                message: '请输入您的性别',
                trigger: 'blur'
              }
            ],
            birth: [
              {
                required: true,
                message: '请输入您的出生年月',
                trigger: 'blur'
              }
            ],
            comeFrom: [
              {
                required: true,
                message: '请输入您的籍贯',
                trigger: 'blur'
              }
            ],
            phoneNum: [
              {
                required: true,
                message: '请输入您的手机号',
                trigger: 'blur'
              }
            ],
            identity: [
              {
                required: true,
                message: '请输入您的身份证号',
                trigger: 'blur'
              }
            ]
          },
          optionSender: [
            {
              value: '0',
              label: '女'
            },
            {
              value: '1',
              label: '男'
            }
          ],
          headers: {
            'Content-Type':'application/x-www-form-urlencoded'
          }
        }
      },
      created() {
        this.getMsg();
      },
      methods: {
        getMsg(){
          this.$http.get("/user/getVolunteerByName",{
            params:{
              name:window.sessionStorage.getItem("token"),
            }
          }).then(response => {
            if (response.data.errorCode===0){
              this.VForm = response.data.data;
            }else {
              this.$message.error(response.data.msg);
            }
          });
        },
        submitForm(formName) {
          this.VForm.certificate = this.filePack.certificate;
          this.VForm.diploma = this.filePack.diploma;
          this.$refs[formName].validate(valid => {
            if (valid) {
              this.$http.post("/user/updateVByName",{
                volunteer:this.VForm
              }).then(response => {
                if (response.data.errorCode===0){
                  this.$message.success(response.data.msg);
                }else {
                  this.$message.error(response.data.msg);
                }
              });
            } else {
              console.log('提交失败');
              return false;
            }
          });
        },

        comeBack(){
          this.$router.go(0);
        },

        uploadFileHandler_diploma(res){
          this.uploadFileHandler(res,"diploma");
        },
        uploadFileHandler_certificate(res){
          this.uploadFileHandler(res,"certificate");
        },
        uploadFileHandler(res,type){
          console.log(res);
          setTimeout(() => {
            this.loading.close();
            if (res.code === 201) {
              this.$message.error(res.msg)
            }else{
              if (type==="certificate"){
                this.filePack.certificate=res.data;
              }else {
                this.filePack.diploma=res.data;
              }
              this.$message.success(res.msg);
            }
          }, 1000);
        },
        uploadFileErrorHandler(res){
          this.$message.error("加载失败,请检查网络连接")
        },
        uploadFileOnProgressHandler(res){
          this.fullScreenLoading()
        },
        fullScreenLoading() {
          this.loading = this.$loading({
            lock: true,
            text: '文件加载中...',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          });
        },

      }
    }

</script>

<style scoped>
  .VForm{
    margin-top: 50px;
    margin-right: 150px;
    margin-left: 150px;
  }

  .special{
    float: left;
  }

  .option{
    font-weight: bold;
    margin-top: 30px;
    margin-right: 60px;
    width: 100px;
  }
</style>
