<template>
  <div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="edit-box">
      <h3>修改密码</h3>
      <div class="form_box">
        <el-form-item class="option" label="账号" prop="userId">
          <el-input type="text" v-model="ruleForm.userId" placeholder="请输入账号或手机号"></el-input>
        </el-form-item>
        <el-form-item class="option" label="密码" prop="pwd">
          <el-input type="password" v-model="ruleForm.pwd" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item class="option" label="确认密码" prop="checkPwd">
          <el-input type="password" v-model="ruleForm.checkPwd" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item class="option">
          <el-button @click="comeBack()">返回</el-button>
          <el-button type="primary" @click="submitForm('ruleForm')" style="margin-right: 50px">修改</el-button>
        </el-form-item>
      </div>
    </el-form>
  </div>
</template>

<script>
    export default {
      name: "ForgetPwd",
      data() {
        var validatePass = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('输入密码不可为空'));
          } else if (value !== this.ruleForm.pwd) {
            callback(new Error('密码不一致!'));
          } else {
            callback();
          }
        };
        return {
          ruleForm: {
            userId:'',
            pwd: '',
            checkPwd: ''
          },
          rules: {
            userId: [
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
            pwd: [
              {
                required: true,
                message: '密码不可为空',
                trigger: 'blur'
              },
              {
                min: 6,
                max: 15,
                message: '长度在 6 到 15 个字符',
                trigger: 'blur'
              }
            ],
            checkPwd: [
              {
                required: true,
                validator: validatePass,
                trigger: 'blur'
              },
              {
                min: 6,
                max: 15,
                message: '长度在 6 到 15 个字符',
                trigger: 'blur'
              }
            ]
          }
        }
      },

      methods: {
        submitForm(formName) {
          this.$refs[formName].validate(valid => {
            if (valid) {
              this.$http.post("/user/modifyPwd",{
                phone:this.ruleForm.userId,
                password:this.ruleForm.pwd
              }).then(response => {
                if (response.data.errorCode===0){
                  this.$message.success(response.data.msg);
                  this.$router.push("/");
                }else {
                  this.$message.error(response.data.msg);
                }
              });
            }
          });
        },
        comeBack() {
          this.$router.go(-1);
        }
      }
    }
</script>

<style scoped>
  .edit-box{
    border: 1px solid #DCDFE6;
    width: 350px;
    margin: 130px auto;
    padding: 35px 35px 15px 35px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    box-shadow: 0 0 25px #909399;
  }

  .form_box{
    margin-top: 20px;
    /*text-align: center;*/
  }

  .option{
    margin-top: 20px;
    margin-bottom: 30px;
  }
</style>
