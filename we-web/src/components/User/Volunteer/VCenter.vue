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
