<template>
  <div class="app-container">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="edit-box">
      <el-form-item class="option" label="文章标题" prop="title">
        <el-input v-model="ruleForm.title" class="title"></el-input>
      </el-form-item>
      <el-form-item label="文章类别">
        <el-select v-model="ruleForm.type" style="float: left">
          <el-option v-for="item in types" :key="item.name" :label="item.name" :value="item.name">
          </el-option>
        </el-select>
        <el-button type="primary" @click="submitForm('ruleForm')" class="send">发布</el-button>
        <el-button @click="comeBack()" class="comeBack">取消</el-button>
      </el-form-item>
    </el-form>
    <mavon-editor v-model="article.content" class="editor" />
  </div>
</template>

<script>
    export default {
      name: "VWrite",
      data() {
        return {
          article: {
            content: ""
          },
          ruleForm: {
            title: '',
            type:''
          },
          rules: {
            title: [
              {
                required: true,
                message: '请输入标题',
                trigger: 'blur'
              }
            ],
          },
          types:[]
        };
      },
      created() {
        this.getType();
      },
      methods: {
        getType(){
          this.$http.get("/type/getAll").then(response => {
            if (response.data.errorCode===0){
              this.types = response.data.data;
            }else {
              this.$message.error(response.data.msg);
            }
          });
        },
        submitForm(formName) {
          this.$refs[formName].validate(valid => {
            if (valid) {
              this.$http.post("/article/add",{
                sender:window.sessionStorage.getItem('id'),
                title:this.ruleForm.title,
                content:this.article.content,
                type:this.ruleForm.type
              }).then(response => {
                if (response.data.errorCode===0){
                  this.$message.success(response.data.msg);
                  this.$router.push("/VArticle");
                }else {
                  this.$message.error(response.data.msg);
                }
              });
            } else {
              this.$message.error('标题和内容不能为空');
              return false;
            }
          });
        },
        comeBack() {
          this.$router.go(-1);
        }
      },
    }
</script>

<style scoped>
  .edit-box{
    border: 1px solid #DCDFE6;
    width: 100%;
  }

  .option{
    margin-top: 20px;
    margin-bottom: 30px;
    margin-right: 50px;
  }

  .editor {
    height: 100%;
    min-height: 750px;
  }

  .title{
    float: left;
    width: 90%;
  }

  .comeBack,.send{
    float: right;
    margin-right: 20px;
  }

  .comeBack{
    margin-right: 15px;
  }
</style>
