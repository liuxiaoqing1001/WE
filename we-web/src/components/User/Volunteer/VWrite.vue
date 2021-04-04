<template>
  <div class="app-container">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="edit-box">
      <el-form-item class="option" label="文章标题" prop="name">
        <el-input v-model="ruleForm.title" class="title"></el-input>
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
            title: ''
          },
          rules: {
            name: [
              {
                required: true,
                message: '请输入标题',
                trigger: 'blur'
              },
              {
                min: 3,
                max: 10,
                message: '长度在 3 到 10 个字符',
                trigger: 'blur'
              }
            ],
          }
        };
      },
      methods: {
        submitForm(formName) {
          this.$refs[formName].validate(valid => {
            if (valid) {
              this.$message({
                typeId: 'success',
                message: '发布成功'
              });
            } else {
              this.$message.error('发布失败');
              return false;
            }
          });
        },
        comeBack() {
          this.$router.push('/VArticle');
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
    width: 65%;
  }

  .comeBack,.send{
    float: right;
  }

  .comeBack{
    margin-right: 15px;
  }
</style>
