<template>
  <div>
<!--    dataForm：是数据绑定对象，loginFormRef：是引用名称，rules：是验证规则-->
    <el-form ref="loginFormRef" :model="loginForm" :rules="rules" class="login-box">
      <!--头像区域-->
      <div class="avatar_box">
        <img src="../assets/logo/we_logo.png" alt />
      </div>
<!--      <img src="../assets/we_logo.png" height="100" width="100"/>-->
<!--      <h3 class="login-title">欢迎登录</h3>-->
<!--      prop="username" 相当于html中的定义表单的name-->
      <div class="form_box">
        <el-form-item class="option" prop="userId">
          <!--        v-model="dataForm.username" 绑定数据-->
          <el-input type="text" v-model="loginForm.userId" placeholder="请输入用户名或手机号">
<!--            prefix首部、suffix尾部-->
            <i slot="prefix" class="el-input__icon el-icon-user"></i>
          </el-input>
        </el-form-item>
        <el-form-item class="option" prop="password">
          <el-input type="password" v-model="loginForm.password" placeholder="请输入密码">
            <i slot="prefix" class="el-input__icon el-icon-lock"></i>
          </el-input>
        </el-form-item>
        <el-form-item class="option">
          <a @click="ModifyPwd" class="forgot" style="color: blue">忘记密码？</a>
          <a @click="Register" class="register" style="color: blue">尚无账号？去注册>></a>
        </el-form-item>
        <!--      @click="submit_login" 定义表单提交事件-->
        <el-button class="loginButton" @click="submit_login">登 录</el-button>
      </div>
    </el-form>

    <el-dialog title="温馨提示" :visible.sync="dialogVisible" width="30%">
      <span>请输入正确的账号和密码</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>

</template>

<script>
  export default {
    name: "Login",
    data() {
      return {
        loginForm: {
          userId: '',
          password: ''
        },
        // 表单验证，需要在 el-form-item 元素中增加 prop 属性
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
          password: [
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
          ]
        },
        // 对话框显示和隐藏
        dialogVisible: false
      }
    },
    methods: {
      submit_login () {
        // validate 预校验
        this.$refs.loginFormRef.validate(async valid => {
          if (!valid){
            this.$message.error('请填写信息！！！');
          }else {
            this.$http.get("/user/login",{
              params:{
                phone:this.loginForm.userId,
                password:this.loginForm.password
              }
            }).then(response => {
              if (response.data.errorCode===0){
                // this.$message.success(response.data.msg);
                // 将登录成功之后的用户id保存到客户端的sessionStorage中
                if(response.data.data.name===null){
                  window.sessionStorage.setItem('token',response.data.data.phone);
                  window.sessionStorage.setItem('name',"null");
                }else {
                  window.sessionStorage.setItem('token',response.data.data.name);
                  window.sessionStorage.setItem('name',response.data.data.name);
                }
                window.sessionStorage.setItem('id',response.data.data.id);
                if (response.data.data.role===0){
                  this.$router.push("/AdminMain");
                }else {
                  this.$router.push("/UserMain");
                }
              }else {
                this.$message.error(response.data.msg);
              }
            });
          }
        });
      },
      ModifyPwd(){
        this.$router.push("/ForgetPwd");
      },
      Register(){
        this.$router.push("/Register");
      }
    }
  }
</script>

<style scoped>
  .avatar_box {
    padding: 10px;
    width: 100px;
    height: 100px;
    border: 1px solid #eee;
    border-radius: 50%;
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background: #fff;
  }

  .form_box{
    margin-top: 100px;
  }

  img {
    width: 100%;
    height: 100%;
    border-radius: 50%;
    /*background: #eee;*/
  }

  .login-box {
    border: 1px solid #DCDFE6;
    width: 350px;
    margin: 130px auto;
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
    margin-top: 45px;
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

  a:hover{
    cursor: pointer;
  }
</style>
