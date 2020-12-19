<template>
  <el-form :model="dataForm" :rules="rules"  class="login-box">
    <img src="../assets/we_logo.png" height="100" width="100"/>
    <h3 class="login-title">欢迎登录</h3>
    <el-form-item label="账号" prop="username">
      <el-input type="text" v-model="dataForm.username" placeholder="请输入用户名或手机号"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input type="password" v-model="dataForm.password" placeholder="请输入密码"></el-input>
    </el-form-item>
    <el-form-item class="option">
      <a href="#" class="forgot">忘记密码？</a>
      <a href="#" class="register">尚无账号？去注册>></a>
    </el-form-item>
    <el-button class="loginButton" @click="submit1">登录</el-button>
  </el-form>

<!--  <el-dialog-->
<!--    title="温馨提示"-->
<!--    :visible.sync="dialogVisible"-->
<!--    width="30%"-->
<!--  >-->
<!--    <span>请输入账号和密码</span>-->
<!--    <span slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>-->
<!--      </span>-->
<!--  </el-dialog>-->
</template>

<script>
  export default {
    name: "Login",
    data() {
      return {
        dataForm: {username: '', password: ''},
        // 表单验证，需要在 el-form-item 元素中增加 prop 属性
        rules: {
          username: [{required: true, message: '账号不可为空', trigger: 'blur'}],
          password: [{required: true, message: '密码不可为空', trigger: 'blur'}]
        }
        // // 对话框显示和隐藏
        // dialogVisible: false
      }
    },
    methods: {
      submit1 () {
        // var params = new URLSearchParams();
        // params.append('name', this.dataForm.username);
        // params.append('password',this.dataForm.password);
        // this.axios.post('/user/login', params)
        //   .then((res)=>{
        //     if (res.data.code==0){
        //       // 使用 vue-router 路由到指定页面，该方式称之为编程式导航
              this.$router.push("/Main");
          //   }
          // })
          // .catch((res)=>{
          //   // this.dialogVisible = true;
          //   console.log(res.data.message);
          // });
      }
    }
  }
</script>

<style scoped>
  .login-box {
    border: 1px solid #DCDFE6;
    width: 350px;
    margin: 120px auto;
    padding: 35px 35px 15px 35px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    box-shadow: 0 0 25px #909399;
  }

  .login-title {
    text-align: center;
    margin: 0 auto 40px auto;
    color: #303133;
  }

  .option{
    width: 100%;
  }

  .forgot{
    float: left;
    margin-left: 10px;
  }

  .register{
    float: right;
    margin-right: 10px;
  }

  .loginButton{
    width: 100%;
    background-color: blue;
    color: white;
    font-size: 16px;
    margin-bottom: 20px;
  }
</style>
