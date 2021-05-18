<template>
  <div>
    <el-container class="userMainContainer">
      <!-- 头部 -->
      <el-header class="navHeader">
        <div>
          <img src="../assets/logo/we_logo_1.png" alt="">
          <span class="logo">WE</span>
        </div>
        <ul>
          <li class="topNav" v-for="item in links"  @click="makeActive(item.name)" >
            <a :class="{active:activeIndex===item.name}" v-on:click="$goRoute(item.route) ">{{item.text}}</a>
          </li>
        </ul>
        <el-input placeholder="请输入内容" style="width: 300px">
<!--          <el-button slot="append" icon="el-icon-search" ></el-button>-->
        </el-input>
        <span style="float:right;padding-top:10px;margin-right:1%">
          <el-dropdown trigger="click">
            <span class="el-dropdown-link">
              {{user}}
              <i class="el-icon-caret-bottom el-icon--right" style="color: blue"></i>
            </span>
            <el-dropdown-menu v-if="loginState===1" slot="dropdown">
              <el-dropdown-item @click.native="personalCenter">个人中心</el-dropdown-item>
              <el-dropdown-item @click.native="myMsg">我的消息</el-dropdown-item>
              <el-dropdown-item @click.native="modifyPwd">修改密码</el-dropdown-item>
              <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
            <el-dropdown-menu v-else slot="dropdown">
              <el-dropdown-item @click.native="login_register">登录/注册</el-dropdown-item>
              <el-dropdown-item @click.native="admin_center">管理员中心</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </span>
      </el-header>
      <!-- 主体 -->
      <el-container class="mainContainer" >
        <el-main>
          <!--路由占位符-->
          <router-view></router-view>
        </el-main>
      </el-container>

      <el-dialog title="登录/注册" :visible.sync="editDialogVisible" class="dialog_box" @close="editDialogClosed">
        <el-form ref="loginFormRef" :model="loginForm" :rules="rules" class="login-box">
            <el-form-item class="option" prop="userId">
              <el-input type="text" v-model="loginForm.userId" placeholder="请输入用户名或手机号">
                <i slot="prefix" class="el-input__icon el-icon-user"></i>
              </el-input>
            </el-form-item>
            <br>
            <el-form-item class="option" prop="password">
              <el-input type="password" v-model="loginForm.password" placeholder="请输入密码">
                <i slot="prefix" class="el-input__icon el-icon-lock"></i>
              </el-input>
            </el-form-item>
            <el-form-item class="option">
              <a @click="ModifyPwd_login" class="forgot" style="color: blue">忘记密码？</a>
              <a @click="Register_login" class="register" style="color: blue">尚无账号？去注册>></a>
            </el-form-item>
            <el-button class="loginButton" @click="submit_login">登 录</el-button>
        </el-form>
      </el-dialog>

    </el-container>
  </div>
</template>

<script>
    export default {
      inject:['reload'],
      name: 'UserMain',
      data () {
        return {
          user:window.sessionStorage.getItem("token"),
          activeIndex: 'Home',
          links: [],
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
          editDialogVisible: false,
          loginState:0,

        }
      },

      created() {
        this.initLi_links();
        console.log(window.sessionStorage.getItem("token"));
        if(window.sessionStorage.getItem("token")===null){
          this.user='游客';
          this.loginState=0;
        }else{
          this.loginState=1;
        }
      },

      // 点击菜单使用的函数
      methods: {
        initLi_links(){
          this.$http.get("/module/getAll").then(response => {
            if (response.data.errorCode===0){
              this.links = response.data.data;
            }else {
              this.$message.error(response.data.msg);
            }
          });
        },
        makeActive: function(item){
          // 模型改变，视图会自动更新
          this.activeIndex = item;
        },
        logout() {
          // 清空token
          window.sessionStorage.clear();
          // // 跳转到登录页
          // this.$router.push('/');
          this.reload();
        },
        personalCenter(){
          this.$router.push("/PersonalCenter");
        },
        myMsg(){
          this.$router.push("/MyMsg");
        },
        modifyPwd(){
          this.$router.push('/ModifyPwd');
        },
        login_register(){
          this.editDialogVisible=true;
        },
        admin_center(){
          this.$router.push("/Login");
        },
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
                  this.editDialogVisible=false;
                  this.reload();
                }else {
                  this.$message.error(response.data.msg);
                }
              });
            }
          });
        },
        ModifyPwd_login(){
          this.$router.push("/ForgetPwd");
        },
        Register_login(){
          this.$router.push("/Register");
        },
        editDialogClosed() {
          // 表单内容重置为空
          this.$refs.editFormRef.resetFields(); // 通过ref引用调用resetFields方法
          this.loginState=0;
        },
      }
    }
</script>

<style scoped>

  .userMainContainer {
    min-height: 910px;
    height: 100%;
    width: 100%;
    overflow: hidden;
  }

  .mainContainer{

  }

  .navHeader{
    height: 56px;
    background: beige;
    color: #fff;
    line-height: 56px;
    position: fixed;
    /*top: 0;*/
    z-index: 1;
    width: 100%;
    left: 0;
    margin-top: 10px;
  }

  .el-header {
    /*color: mediumseagreen;*/
    display: flex;
    justify-content: space-between;
    padding-left: 0;
    align-items: center;
    font-size: 20px;
  }

  div {
    display: flex;
    align-items: center;
  }

  img{
    height: 60px;
    width: 60px;
    margin-left: 20px;
  }

  span {
    margin-left: 10px;
  }

  .logo{
    /*margin-left: 30px;*/
    color: mediumseagreen;
    font-size: x-large;
  }

  .topNav{
    display: inline-block;
    margin-right: 10px;
  }


  /*a:hover可用于设置当鼠标悬停在超链接之上时超链接的样式*/
  ul li a:hover{
    cursor: pointer;
    color: blue;
    font-size: medium;
    /*background-color: #138bec;*/
    /*border: 1px solid #138bec;*/
  }

  ul li a{
    display:inline-block;
    /*padding: 18px 30px;*/
    /*color: mediumseagreen;*/
    /*font-weight:bold;*/
    color: black;
    font-size:16px;
    text-decoration:none !important;
    line-height:1;
    text-transform: uppercase;
    background-color:transparent;
    margin-right: 20px;

    -webkit-transition:background-color 0.25s;
    -moz-transition:background-color 0.25s;
    transition:background-color 0.25s;
  }

  ul li a:first-child{
    border-radius:2px 0 0 2px;
  }

  ul li a:last-child{
    border-radius:0 2px 2px 0;
    margin-right: 10px;
  }

  .active {
    color: mediumseagreen;
    font-size: medium;
  }

  .el-dropdown-link{
    color: mediumseagreen;
    font-size: 18px;
  }

  .dialog_box{
    width: 50%;
    margin-left: 25%;
    margin-bottom: 10%
  }

  .forgot{
    float: left;
    /*margin-left: 10px;*/
    margin-right: 50px;
  }

  .register{
    float: right;
    /*margin-right: 10px;*/
  }

  .loginButton{
    /*width: 100%;*/
    background-color: blue;
    color: white;
    font-size: 16px;
    margin-bottom: 20px;
  }

  .option{
    width: 100%;
    margin-top: 10px;
    margin-left: 20%;
  }

  .option a:hover{
    cursor: pointer;
  }

</style>
