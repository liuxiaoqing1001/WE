<template>
  <div class="UForm">
    <el-form :model="UForm" :rules="rules" ref="ruleForm" label-width="100px">
      <h4>个人信息</h4>
      <el-form-item label="用户名" prop="userName">
        <span style="float: left;margin-left: 10px">{{UForm.userName}}</span>
      </el-form-item>
      <el-form-item label="性别" prop="sender">
        <el-select v-model="UForm.sender" placeholder="请选择性别" class="special">
          <el-option v-for="item in optionSender" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="出生年月" prop="birth">
        <el-date-picker v-model="UForm.birth" type="date" placeholder="选择日期" class="special"></el-date-picker>
      </el-form-item>
      <el-form-item label="手机号" prop="phoneNum">
        <el-input v-model="UForm.phoneNum"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="comeBack(ruleForm)" class="option">取消</el-button>
        <el-button type="primary" @click="submitForm('ruleForm')" class="option">修  改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    export default {
        name: "PersonalCenter",
      data(){
        return{
          UForm: {
            userName:'xxx',
            sender: '',
            birth: '',
            phoneNum: '',
          },
          rules: {
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
            phoneNum: [
              {
                required: true,
                message: '请输入您的手机号',
                trigger: 'blur'
              }
            ],
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
        comeBack(formName){
          this.$refs[formName].resetFields();
        }
      }

    }
</script>

<style scoped>
  .UForm{
    position: absolute;
    top: 10%;
    left: 40%;
  }

  .special{
    float: left;
  }

  h4{
    margin-left: 40px;
  }

  .option{
    font-weight: bold;
    margin-top: 30px;
    margin-right: 30px;
  }
</style>
