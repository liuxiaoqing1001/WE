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
<!--      <el-form-item label="出生日期" prop="birth">-->
<!--        <el-date-picker v-model="VForm.birth" type="datetime"-->
<!--                        format="yyyy年MM月dd日" value-format="yyyy年MM月dd日" style="float: left">-->
<!--        </el-date-picker>-->
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
        <a v-if="!isCanShowCertificate" @click="showCertificate" class="showFile">在线查看</a>
        <el-upload
          class="special"
          v-model="VForm.certificate"
          action="http://127.0.0.1:8618/file/upload"
          :show-file-list="true"
          :on-success="uploadFileHandler_certificate"
          :on-error="uploadFileErrorHandler"
          :on-progress="uploadFileOnProgressHandler">
          <el-button size="small" type="primary">选择文件</el-button>
          <span style="color: indianred;font-size: 10px">仅支持 .pdf / .png / .jpeg 文件上传</span>
        </el-upload>
      </el-form-item>
      <el-form-item label="学历学位证" prop="diploma">
        <a v-if="!isCanShowDiploma" @click="showDiploma" class="showFile">在线查看</a>
        <el-upload
          class="special"
          v-model="VForm.diploma"
          action="http://127.0.0.1:8618/file/upload"
          :show-file-list="true"
          :on-success="uploadFileHandler_diploma"
          :on-error="uploadFileErrorHandler"
          :on-progress="uploadFileOnProgressHandler">
          <el-button size="small" type="primary">选择文件</el-button>
          <span style="color: indianred;font-size: 10px">仅支持 .pdf / .png / .jpeg 文件上传</span>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button @click="comeBack()" class="option">取消</el-button>
        <el-button type="primary" @click="submitForm('ruleForm')" class="option">修改</el-button>
      </el-form-item>
    </el-form>

    <el-dialog title="执业资格证书" :visible.sync="isShowFileCertificate" width="60%" @close="showDialogClosed">
      <!--        <pdf ref="pdf" :src="src" v-for="i in numPages" :key="i" :page="i"></pdf>-->
      <div v-if="fileType===1">
        <img :src="src" />
      </div>
      <div v-else>
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
      </div>
    </el-dialog>

    <el-dialog title="学历学位证书" :visible.sync="isShowFileDiploma" width="60%" @close="showDialogClosed">
      <div v-if="fileType===1">
        <img :src="src" />
      </div>
      <div v-else>
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
      </div>
    </el-dialog>

  </div>

</template>

<script>
    import pdf from 'vue-pdf'

    export default {
      components:{
        pdf
      },
      name: "VCenter",
      data() {
        return {
          fileType:0,
          src:"",
          currentPage: 0, // pdf文件页码
          pageCount: 0, // pdf文件总页数
          isShowFileCertificate:false,
          isShowFileDiploma:false,
          isCanShowCertificate:false,
          isCanShowDiploma:false,
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
            // birth: '',
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
        // 有时PDF文件地址会出现跨域的情况,这里最好处理一下　　　　
        this.src = pdf.createLoadingTask(this.src);
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

        // 监听 修改用户信息对话框的关闭事件
        showDialogClosed() {
          // 表单内容重置为空
          this.$refs.editFormRef.resetFields(); // 通过ref引用调用resetFields方法
          this.src="";
        },
        showCertificate(){
          console.log("--------"+this.VForm.certificate)
          this.getFileByFileName(this.VForm.certificate);
          // this.src="http://storage.xuetangx.com/public_assets/xuetangx/PDF/PlayerAPI_v1.0.6.pdf";
          this.isShowFileCertificate=true;
        },
        showDiploma(){
          // this.src="/static/redis.pdf";
          console.log("////////////"+this.VForm.diploma)
          this.getFileByFileName(this.VForm.diploma);
          this.isShowFileDiploma=true;
        },
        getFileByFileName(fileName){
          var type = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length);
          if(type==="pdf"){
            this.fileType=0;
          }else{
            this.fileType=1;
          }
          this.$http.get("/file/getFileByFileName",{
            params:{
              file:fileName
            },
            responseType:"arraybuffer"
          }).then(response => {
            console.log(response)
            if (response.status===200){
              var binaryData = [];
              binaryData.push(response.data);
              this.src = window.URL.createObjectURL(new Blob(binaryData, {
                type: "application/pdf"
              }));
            }else {
              this.$message.error("预览失败");
            }
          });
        },
        // 改变PDF页码,val传过来区分上一页下一页的值,0上一页,1下一页
        changePdfPage (val) {
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
                this.isCanShowCertificate=true;
              }else {
                this.filePack.diploma=res.data;
                this.isCanShowDiploma=true;
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

  .showFile{
    color:blue;
    /*float: left;*/
    /*margin-left: 15px;*/
  }
  a:hover{
    cursor: pointer;
  }

  .option{
    font-weight: bold;
    margin-top: 30px;
    margin-right: 60px;
    width: 100px;
  }
</style>
