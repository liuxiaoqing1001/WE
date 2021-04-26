<template>
  <div class="UForm">
<!--    <el-form :model="UForm" :rules="rules" ref="ruleForm" label-width="100px">-->
<!--      -->
<!--      <el-form-item label="用户名" prop="userName">-->
<!--        <span style="float: left;margin-left: 10px">{{UForm.userName}}</span>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="性别" prop="sender">-->
<!--        <el-select v-model="UForm.sender" placeholder="请选择性别" class="special">-->
<!--          <el-option v-for="item in optionSender" :key="item.value" :label="item.label" :value="item.value"/>-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="出生年月" prop="birth">-->
<!--        <el-date-picker v-model="UForm.birth" type="date" placeholder="选择日期" class="special"></el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="手机号" prop="phoneNum">-->
<!--        <el-input v-model="UForm.phoneNum"></el-input>-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button type="primary" @click="comeBack(ruleForm)" class="option">取消</el-button>-->
<!--        <el-button type="primary" @click="submitForm('ruleForm')" class="option">修  改</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->
    <el-form :model="UForm" :rules="rules" ref="ruleForm" label-width="100px">
      <h4>个人信息</h4>
      <el-form-item label="ID">
        <el-input v-model="UForm.id" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="用户名" prop="name">
        <el-input v-model="UForm.name"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="UForm.sex" style="float: left">
          <el-option v-for="item in sexes" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="UForm.email"></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input v-model="UForm.phone"></el-input>
      </el-form-item>
      <el-form-item label="出生日期">
        <el-date-picker v-model="UForm.birthday" type="datetime"
                        format="yyyy年MM月dd日" value-format="yyyy年MM月dd日" style="float: left">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="comeBack()" class="option">返回</el-button>
        <el-button type="primary" @click="submitForm('ruleForm')" class="option">修  改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    export default {
      name: "PersonalCenter",
      data(){
        // 验证邮箱的规则(验证规则 验证的值 回调函数)
        var checkEmail = (rule, value, callback) => {
          // 验证邮箱的正则表达式
          const regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
          if (regEmail.test(value)) {
            // 合法的邮箱
            return callback()
          }
          callback(new Error('请输入合法的邮箱'))
        };
        // 验证手机的规则
        var checkPhone = (rule, value, callback) => {
          const regMobile = /^1[0-9][0-9]{9}$/;
          if (regMobile.test(value)) {
            // 合法的手机
            return callback()
          }
          callback(new Error('请输入合法的手机号'))
        };
        return{
          //多条件查询是否性别下拉框
          sexes: [
            {
              value: '1',
              label: '男'
            },
            {
              value: '0',
              label: '女'
            }
          ],
          UForm: {
            id:window.sessionStorage.getItem("id"),
            name: '',
            sex:'',
            email: '',
            phone: '',
            birthday:''
          },
          rules: {
            name: [
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
            email: [
              {
                required: true,
                message: '请输入用户邮箱',
                trigger: 'blur'
              },
              {
                validator: checkEmail,
                trigger: 'blur'
              }
            ],
            phone: [
              {
                required: true,
                message: '请输入用户手机',
                trigger: 'blur'
              },
              {
                validator: checkPhone,
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
        this.getUser();
      },
      methods: {
        getUser(){
          this.$http.get("/user/getUserById",{
            params:{
              id:this.UForm.id
            }
          }).then(response => {
            if (response.data.errorCode===0){
              this.UForm = response.data.data;
            }else {
              this.$message.error(response.data.msg);
            }
          });
        },
        submitForm(formName) {
          this.$refs[formName].validate(valid => {
            if (!valid){
              this.$message.error('请填写信息！！！');
            }else {
              this.$http.post("/user/update",{
                user:this.UForm
              }).then(response => {
                if (response.data.errorCode===0){
                  this.$message.success(response.data.msg);
                  this.getUser();
                  console.log(response)
                  window.sessionStorage.setItem('token',response.data.data.name);
                  window.sessionStorage.setItem('name',response.data.data.name);
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
