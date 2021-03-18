<template>
  <div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="register-box">
      <h3>修改密码</h3>
      <div class="form_box">
        <el-form-item class="option" label="密码" prop="pass">
          <el-input type="password" v-model="ruleForm.pass" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item class="option" label="确认密码" prop="checkPass">
          <el-input type="password" v-model="ruleForm.checkPass" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item class="option">
          <el-button @click="resetForm('ruleForm')">重置</el-button>
          <el-button type="primary" @click="submitForm('ruleForm')" style="margin-right: 50px">修改</el-button>
        </el-form-item>
      </div>
    </el-form>
  </div>
</template>

<script>
    export default {
        name: "ModifyPwd",
      data() {
        var validatePass = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('输入密码不可为空'));
          } else if (value !== this.ruleForm.pass) {
            callback(new Error('密码不一致!'));
          } else {
            callback();
          }
        };
        return {
          ruleForm: {
            pass: '',
            checkPass: ''
          },
          rules: {
            pass: [
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
            checkPass: [
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
              this.$message({
                type: 'success',
                message: '注册成功'
              });
            } else {
              this.$message.error('注册失败');
              return false;
            }
          });
        },

        resetForm(formName) {
          this.$refs[formName].resetFields();
        }
      }
    }
</script>

<style scoped>
  .register-box{
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
