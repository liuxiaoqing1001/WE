<template>
<!--  一进来先判断是否是咨询师-->
<!--  否：-->
<!--  加入条件-->
<!--  上传证件-->
<!--  等待审核-->
<!--  是：整个页面切换至-->
<!--  聊天室（咨询中心）-->
<!--  文章-->
<!--  自愿者个人中心-->
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
              <span class="vFormName" style="float: left;margin-left: 10px">{{VForm.name}}</span>
            </el-form-item>
            <el-form-item label="真实姓名" prop="realName">
              <el-input v-model="VForm.realName"></el-input>
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
              <!--      :before-upload  文件上传前的回调-->
              <!--      :accept 接收的文件类型-->
              <el-upload class="special" ref="upload" action="/System/fileUpload.mvc" :on-change="getFiles"
                         :before-upload="fileUploadSuffix" multiple :accept="doc,docx,xls,xlsx" :data="addFilesDate" :file-list="fileList">
                <el-button size="small" type="primary">选择文件</el-button>
              </el-upload>
              <!--      <el-input v-model="VForm.certificate"></el-input>-->
            </el-form-item>
            <el-form-item label="学历学位证" prop="diploma">
              <el-upload class="special" ref="upload" action="/System/fileUpload.mvc" :on-change="getFiles"
                         :before-upload="fileUploadSuffix" multiple :accept="doc,docx,xls,xlsx" :data="addFilesDate" :file-list="fileList">
                <el-button size="small" type="primary">选择文件</el-button>
              </el-upload>
              <!--      <el-input v-model="VForm.diploma"></el-input>-->
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
          applyV: false,
          VForm: {
            name:'',
            realName: '',
            sender: '',
            birth: '',
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
        }
      },
      created() {
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
            this.$message.error(response.data.msg);
          }
        });

      },
      methods:{
        into:function () {
          this.applyV=true;
          if(window.sessionStorage.getItem("name")==="null"){
            this.VForm.name = "未设置用户名，请先前往个人中心设置用户名，否则申请无效";
            // vFormName设置颜色
          }else {
            this.VForm.name = window.sessionStorage.getItem("name");
          }
        },
        del:function () {
          this.applyV=false;
        },

        submitForm(formName) {

          //提交申请

          this.$refs[formName].validate(valid => {
            if (valid) {
              this.$message({
                typeId: 'success',
                message: '提交成功'
              });
              // this.activeName: 'first',
            } else {
              console.log('提交失败');
              return false;
            }
          });

        },

        fileUploadSuffix : function (fileList, suffix) {
          let blooean = null
          for (let i in fileList) {
            let item = fileList[i] // 某一条文件信息
            let fileName = item.name.lastIndexOf('.') // 取到文件名开始到最后一个点的长度
            let fileNameLength = item.name.length // 取到文件名长度
            let hz = item.name.substring(fileName + 1, fileNameLength) // 获取上传文件的后缀名
            // 判断文件名后缀是否合法
            if (suffix.indexOf(hz) === -1) { // 不合法上传文件
              // 删除上传的文件列表中的不合法文件类型
              fileList.splice(i--, 1) // 删除列表中的数据（删除后文件调整）
              // 弹窗显示判断
              blooean = true
            }
          }
          return blooean // 返回参数
        }
      }
    }
</script>

<style scoped>
  .main{
    width: 100%;
    min-width: 1200px;
    height: 900px;
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
