<template>
<!--  修改和展示信息-->
  <div class="VForm">
    <h3>咨询师个人信息</h3>
    <el-form :model="VForm" :rules="rules" ref="ruleForm" label-width="100px">
      <el-form-item label="用户名" prop="userName">
        <span style="float: left;margin-left: 10px">{{VForm.userName}}</span>
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="VForm.name"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="sender">
        <el-select v-model="VForm.sender" placeholder="请选择性别" class="special">
          <el-option v-for="item in optionSender" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="出生年月" prop="birth">
        <el-date-picker v-model="VForm.birth" type="date" placeholder="选择日期" class="special"></el-date-picker>
      </el-form-item>
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
        <el-upload
          class="special xj-upload clearfix"
          ref="upload"
          action="/FileUpload/upload2"
          :on-change="getFiles"
          :before-upload="beforeUpload"
          :before-remove="beforeRemove"
          :auto-upload="false"
          multiple
          :limit="1"
          :on-exceed="handleExceed"
          name="提交文件"
          accept="doc,docx,xls,xlsx"
          :on-success="fileSuccessUpload"
          :on-error="fileError">
          <el-button size="small" type="primary">选择文件</el-button>
        </el-upload>
        <!--      <el-input v-model="VForm.certificate"></el-input>-->
      </el-form-item>
      <el-form-item label="学历学位证" prop="diploma">
        <el-upload
          class="special xj-upload clearfix"
          ref="upload"
          action="/FileUpload/upload2"
          :on-change="getFiles"
          :before-upload="beforeUpload"
          :before-remove="beforeRemove"
          :auto-upload="false"
          multiple
          :limit="1"
          :on-exceed="handleExceed"
          name="提交文件"
          accept="doc,docx,xls,xlsx"
          :on-success="fileSuccessUpload"
          :on-error="fileError">
          <el-button size="small" type="primary">选择文件</el-button>
        </el-upload>
        <!--      <el-input v-model="VForm.diploma"></el-input>-->
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
          VForm: {
            userName:'xxx',
            name: '',
            sender: '',
            birth: '',
            comeFrom: '',
            phoneNum: '',
            identity: '',
            certificate: '',
            diploma: '',
          },
          rules: {
            userName: [
              {
                required: true,
                message: '请输入您的姓名',
                trigger: 'blur'
              }
            ],
            name: [
              {
                required: true,
                message: '请输入您的姓名',
                trigger: 'blur'
              }
            ],
            sender: [
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
            ],
            certificate: [
              {
                required: true,
                message: '请输入您的执业资格证',
                trigger: 'blur'
              }
            ],
            diploma: [
              {
                required: true,
                message: '请输入您的学历学位证书',
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

      methods: {
        submitForm(formName) {
          this.$refs[formName].validate(valid => {
            if (valid) {
              this.$message({
                type: 'success',
                message: '提交成功'
              });
              // this.activeName: 'first',
            } else {
              console.log('提交失败');
              return false;
            }
          });
        },

        comeBack(){

        },

        handleExceed(files, fileList) {
          this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
        },
        beforeRemove(file, fileList) {
          return this.$confirm(`确定移除 ${ file.name }？`);
        },
        uploadFile(){
          this.loading = true;
          this.$refs.upload.submit();
        },
        fileSuccessUpload(response,file,fileList){
          this.VForm['提交文件'] = response.split('?')[1].split('&')[1].split('=')[1];
          this.$refs.uploadattachment.submit();
        },
        fileSuccessUploadattachment(response,file,fileList){
          this.VForm['提交内容'] = response.split('?')[1].split('&')[1].split('=')[1];
          this.uploadForm();
        },
        uploadForm(){
          const self = this;
          this.$refs['VForm'].validate((valid)=>{
            if(valid){
              self.VForm['提交时间']=Vue.filter('formatdate')(new Date(),'yyyy-MM-dd hh:mm:ss');
              self.$axios({
                method:'post',
                url:self.uploadUrl,
                data:self.VForm,
                headers:self.headers
              })
              .then(function (res) {
                self.$message.success('上传成功');
                self.loading=false;
              })
            }
          })
        }

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
