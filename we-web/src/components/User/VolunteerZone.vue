<template>
  <div>
    <div class="main">
      <div v-if="!applyV" class="frame">
        <h2>咨询师入驻标准</h2>
        <div class="standard">
          <h5>1. 前提条件</h5>
          申请成为平台入驻自愿者咨询师需要同时符合以下条件：<br>
          (1) 拥有法律要求的执业资格。
          需持有国家二级心理咨询师、国家三级心理咨询师，注册系统咨询师，注册系统督导师，婚姻家庭咨询师，心理治疗师资质或港澳台及海外法律认可的执业资格等。<br>
          (2) 全日制大专及以上学历（1999年12月31日以前取得相应学历学位证书）。<br>
          (3) 一定的咨询经验是专业水平的保障。<br>
          <h5>2. 申请方式</h5>
          满足以上条件的申请者，点击<a class="intoA" @click="into()">网上入驻通道</a>进入，
          按要求提供材料进行审核，即可进入入驻考察环节，成为平台新晋咨询师，
          新晋咨询师入驻平台半年后，提交个案报告后要可参加转正面试,面试通过即可转为平台正式入驻咨询师。<br>
          <h5>3. 网上入驻提供材料</h5>
          学历证明（必须）、 法律要求的执业资格证（必须）、 相关论文和书籍（非必须）
        </div>
      </div>

      <div v-else class="frameAV">
        <el-button @click="del()" type="danger" size="mini" icon="" style="font-size: medium;float: right">X</el-button>
        <h2>咨询师入驻申请</h2>
        <div class="VForm">
          <el-form :model="VForm" :rules="rules" ref="ruleForm" label-width="100px">
            <el-form-item label="用户名" prop="name">
              <span class="vFormName" :style="{'color':vFormNameColor}">{{VForm.name}}</span>
            </el-form-item>
            <el-form-item label="真实姓名" prop="realName">
              <el-input v-model="VForm.realName"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
              <el-select v-model="VForm.gender" placeholder="请选择性别" class="special">
                <el-option v-for="item in optionGender" :key="item.value" :label="item.label" :value="item.value"/>
              </el-select>
            </el-form-item>
<!--            <el-form-item label="出生日期" prop="birth">-->
<!--              <el-date-picker v-model="VForm.birth" type="datetime"-->
<!--                              format="yyyy年MM月dd日" value-format="yyyy年MM月dd日" style="float: left">-->
<!--              </el-date-picker>-->
<!--            </el-form-item>-->
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
<!--              拖拽框上传-->
<!--              <el-upload-->
<!--                class="special"-->
<!--                drag-->
<!--                action="http://127.0.0.1:8618/file/upload"-->
<!--                :show-file-list="true"-->
<!--                :on-success="uploadFileHandler"-->
<!--                :on-error="uploadFileErrorHandler"-->
<!--                :on-progress="uploadFileOnProgressHandler">-->
<!--                <i class="el-icon-upload"></i>-->
<!--                <div class="el-upload__text">-->
<!--                  将文件拖到此处，或<em>点击上传</em>-->
<!--                  <br>-->
<!--                  <span style="color: mediumseagreen;font-size: 10px">仅支持.doc/.docx/.pdf/.png/.jpeg 文件上传</span>-->
<!--                </div>-->
<!--              </el-upload>-->

<!--              按钮上传-->
              <el-upload
                class="special"
                v-model="VForm.certificate"
                action="http://127.0.0.1:8618/file/upload"
                :limit="1"
                :show-file-list="true"
                :on-success="uploadFileHandler_certificate"
                :on-remove="handleRemove_certificate"
                :on-error="uploadFileErrorHandler"
                :on-progress="uploadFileOnProgressHandler">
                <el-button size="small" type="primary">选择文件</el-button>
                <span style="color: indianred;font-size: 10px">仅支持 .pdf / .png / .jpeg 文件上传</span>
              </el-upload>
            </el-form-item>
            <el-form-item label="学历学位证" prop="diploma">
              <el-upload
                class="special"
                v-model="VForm.diploma"
                action="http://127.0.0.1:8618/file/upload"
                :limit="1"
                :show-file-list="true"
                :on-success="uploadFileHandler_diploma"
                :on-remove="handleRemove_diploma"
                :on-error="uploadFileErrorHandler"
                :on-progress="uploadFileOnProgressHandler">
                <el-button size="small" type="primary">选择文件</el-button>
                <span style="color: indianred;font-size: 10px">仅支持 .pdf / .png / .jpeg 文件上传</span>
              </el-upload>
            </el-form-item>
            <el-form-item>
<!--              <el-button @click="comeBack()" class="option">取消</el-button>-->
              <el-button type="primary" @click="submitForm('ruleForm')" class="option">提  交</el-button>
            </el-form-item>
          </el-form>
        </div>

      </div>
    </div>
  </div>


</template>

<script>
    export default {
      name: "VolunteerZone",
      data(){
        return{
          visible: false,
          loading: null,
          applyV: false,
          vFormNameColor:'',
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
            // userName: [
            //   {
            //     required: true,
            //     message: '请输入您的姓名',
            //     trigger: 'blur'
            //   }
            // ],
            realName: [
              {
                required: true,
                message: '请输入您的真实姓名',
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
            ],
            // certificate: [
            //   {
            //     required: true,
            //     message: '请输入您的执业资格证',
            //     trigger: 'blur'
            //   }
            // ],
            // diploma: [
            //   {
            //     required: true,
            //     message: '请输入您的学历学位证书',
            //     trigger: 'blur'
            //   }
            // ]
          },
          optionGender: [
            {
              value: '0',
              label: '女'
            },
            {
              value: '1',
              label: '男'
            }
          ],
        }
      },
      created() {
        this.judgeRole();
      },
      methods:{
        judgeRole(){
          this.$http.get("/user/getRoleById",{
            params:{
              id:window.sessionStorage.getItem("id"),
            }
          }).then(response => {
            if (response.data.errorCode===0){
              if(response.data.data==="2"){
                this.$router.push("/VolunteerCenter");
              }
            }else {
              // this.$message.error(response.data.msg);
            }
          });
        },
        into:function () {
          if(window.sessionStorage.getItem("id")===null){
            this.$message.error("未登录");
          }else {
            this.applyV = true;
            if (window.sessionStorage.getItem("name") === "null") {
              this.VForm.name = "未设置用户名，请先前往个人中心设置用户名，否则申请无效";
              this.vFormNameColor = "#ff0000";
            } else {
              // this.VForm.name = window.sessionStorage.getItem("name");
              // 根据name从用户表中获取sender/birth/phoneNum
              this.$http.get("/user/getUserByName", {
                params: {
                  name: window.sessionStorage.getItem("name"),
                }
              }).then(response => {
                if (response.data.errorCode === 0) {
                  this.VForm = response.data.data;
                } else {
                  this.$message.error(response.data.msg);
                }
              });
            }
          }
        },
        del:function () {
          this.applyV=false;
        },

        //未将文件路径存入数据库
        submitForm(formName) {
          this.VForm.certificate = this.filePack.certificate;
          this.VForm.diploma = this.filePack.diploma;
          //提交申请
          this.$refs[formName].validate(valid => {
            if (valid) {
              this.$http.post("/user/addVRequest",{
                volunteer:this.VForm
              }).then(response =>{
                if (response.data.errorCode===0){
                  this.$message.success(response.data.msg);
                  this.del();
                }else {
                  this.$message.error(response.data.msg);
                }
              });
            }
          });
        },
        handleRemove_diploma(){
          this.handleRemove("diploma");
        },
        handleRemove_certificate(){
          this.handleRemove("certificate");
        },
        handleRemove(type){
          var fileName="";
          if(type==="certificate"){
            fileName = this.filePack.certificate;
          }else {
            fileName = this.filePack.diploma;
          }
          this.$http.delete("/file/delFile",{
            params:{
              file:fileName
            }
          }).then(response=>{
            if (response.data.errorCode===0){
              this.$message.success(response.data.msg);
              if(type==="certificate"){
                this.filePack.certificate="";
              }else {
                this.filePack.diploma="";
              }
            }else {
              this.$message.error(response.data.msg);
            }
          });
        },
        uploadFileHandler_diploma(res){
          this.uploadFileHandler(res,"diploma");
        },
        uploadFileHandler_certificate(res){
          this.uploadFileHandler(res,"certificate");
        },
        uploadFileHandler(res,type){
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
        }
      }
    }
</script>

<style scoped>
  .main{
    width: 100%;
    /*min-width: 1200px;*/
    height: 100%;
    min-height: 910px;
    background-image: url("../../assets/background/background.jpeg");
    background-repeat:no-repeat;
    background-size:100% 100%;
    -moz-background-size:100% 100%;
    overflow: hidden;
    filter: drop-shadow(0 0 10px white);
    /*color: white;*/
    /*position: relative;*/
  }

  .frame{
    background:rgba(312,500,500,0.5);
    width: 1200px;
    height: 600px;
    border-radius: 10px;
    position: absolute;
    left: 15%;
    top: 20%;
  }

  .frameAV{
    background: white;
    width: 1500px;
    height: 850px;
    /*border-radius: 10px;*/
    position: absolute;
    left: 8%;
    top: 5%;
    text-align: center;
  }

  .standard{
    /*color: white;*/
    /*font-size: 15px;*/
    text-align: left;
    margin-left: 20px;
    margin-right: 20px;
    position: absolute;
    left: 3%;
    top: 15%;
  }

  .intoA{
    color: blue;
  }

  a:hover{
    cursor: pointer;
  }

  .VForm{
    margin-right: 150px;
    margin-left: 150px;
  }

  .vFormName{
    float: left;
    margin-left: 10px
  }

  .special{
    float: left;
  }

  .option{
    font-size: 16px;
    font-weight: bold;
    margin-top: 30px;
    margin-right: 60px;
    width: 100px;
    background-color: blue;
  }
</style>
