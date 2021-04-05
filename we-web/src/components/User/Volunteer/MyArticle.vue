<template>
  <div v-if="!isEdit" class="detail">
    <div>
      <i class="back el-icon-back" @click="back()"></i>
      <el-dropdown trigger="click" class="edit">
        <a class="el-dropdown-link">更多</a>
        <el-dropdown-menu slot="dropdown" class="edit">
          <el-dropdown-item @click.native="edit">修改</el-dropdown-item>
          <el-dropdown-item @click.native="del(list.id)">删除</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <div id="content">
      <h3>{{list.title}}</h3>
      <h6>{{list.sender}}</h6>
      <div>
        <span style="font-size:15px;margin-right: 30px">{{list.sendDate}}</span>
        <span style="font-size:18px">
          <i class="el-icon-star-off" style="margin-right: 10px">{{praiseNum}}</i>
          <i class="el-icon-edit">{{commentNum}}</i>
        </span>
      </div>
      <!-- 用v-html解析后台传来的HTML代码 -->
      <div v-html="list.content"></div>
    </div>
    <div class="commentArea">
      <span v-if="!isComment" class="noComment">暂无评论</span>
      <ul v-else class="list-group">
        <li class="list-group-item" v-for="cItem in cList" :key="cItem.id">
          <span class="badge">{{ cItem.sender }}</span>
          {{ cItem.content }}
        </li>
      </ul>
    </div>
  </div>

  <div v-else class="app-container">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="edit-box">
      <el-form-item class="option" label="文章标题" prop="title">
        <el-input v-model="ruleForm.title" class="title"></el-input>
      </el-form-item>
      <el-form-item label="文章类别">
        <el-select v-model="ruleForm.type" style="float: left">
          <el-option v-for="item in types" :key="item.name" :label="item.name" :value="item.name">
          </el-option>
        </el-select>
        <el-button type="primary" @click="submitForm('ruleForm')" class="send">修改</el-button>
        <el-button @click="comeBack()" class="comeBack">返回</el-button>
      </el-form-item>
    </el-form>
    <mavon-editor v-model="article.content" class="editor" />
  </div>
</template>

<script>
    export default {
      name: "MyArticle",
      data(){
        return{
          list:[],
          cList:[],
          isEdit: false,
          isComment:false,
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
          types:[],
          praiseNum:0,
          commentNum:0
        }
      },
      created(){
        // 获取动态路由传值
        var aid=this.$route.query.id;
        // 调用requestData()方法请求数据
        this.requestData(aid);
        this.getType();
        this.initPAC(aid);
      },
      methods:{
        getType(){
          this.$http.get("/type/getAll").then(response => {
            if (response.data.errorCode===0){
              this.types = response.data.data;
            }else {
              this.$message.error(response.data.msg);
            }
          });
        },
        initPAC(aid){
          //根据id获得点赞数和评论数,收藏数
          this.$http.get("/article/getPNum",{
            params:{
              id:aid
            }
          }).then(response => {
            if (response.data.errorCode===0){
              this.praiseNum = response.data.data;
            }else {
              this.$message.error(response.data.msg);
            }
          });
          this.$http.get("/article/getCNum",{
            params:{
              id:aid
            }
          }).then(response => {
            if (response.data.errorCode===0){
              this.commentNum = response.data.data;
            }else {
              this.$message.error(response.data.msg);
            }
          });
          this.$http.get("/article/getCommentById",{
            params:{
              id:aid
            }
          }).then(response => {
            if (response.data.errorCode===0){
              if(response.data.data.length===0){
                this.isComment=false;
              }else {
                this.isComment=true;
                this.cList = response.data.data;
              }
            }else {
              this.$message.error(response.data.msg);
            }
          });
          //根据id修改文章
          //下方显示评论区
        },
        requestData(aid){
          this.$http.get("/article/getArticleById",{
            params:{
              id:aid
            }
          }).then(response => {
            if (response.data.errorCode===0){
              this.list = response.data.data;
            }else {
              this.$message.error(response.data.msg);
            }
          });
        },
        back(){
          this.$router.go(-1)
        },
        edit(){
          this.isEdit=true;
          this.ruleForm = this.list;
          this.article = this.list;
        },
        del(id) {
          this.$confirm('此操作将永久删除该文章, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            typeId: 'warning'
          }).then(() => {
            this.$http.delete("/article/delete/"+id).then(response => {
              if (response.data.errorCode===0){
                this.$message.success(response.data.msg);
                this.$router.push("/VArticle");
              }else {
                this.$message.error(response.data.msg);
              }
            });
          }).catch(() => {
            this.$message({
              typeId: 'info',
              message: '已取消删除'
            })
          })
        },
        submitForm(formName) {
          this.$refs[formName].validate(valid => {
            if (valid) {
              this.$http.post("/article/update",{
                id:this.ruleForm.id,
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
          this.isEdit=false;
        }
      }
    }
</script>

<style scoped>
  .detail{
    width: 100%;
    margin-right: 100px;
    margin-top: 10px;
  }
  .back{
    float: left;
    color: blue;
    margin-left: 20px;
  }
  .edit{
    float: right;
    color: #42b983;
    margin-right: 20px;
  }
  a:hover{
    cursor: pointer;
  }
  i:hover{
    cursor: pointer;
  }
  /*.el-dropdown-link{*/
  /*  color: black;*/
  /*  font-size: 25px;*/
  /*}*/
  #content {
    margin-top: 20px;
    padding: 1rem;
    line-height: 2;
  }
  h3{
    margin-top: 40px;
  }
  img{
    max-width: 100%;
  }

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

  .commentArea{
    margin-top: 10%;
    width: 66%;
    /*margin-right: 30%;*/
    /*text-align: center;*/
    margin-left: 30%;
    font-size: 15px;
  }

  .list-group-item{
    margin-bottom: 15px;
    width: 50%;
    /*width: auto;*/
    padding: 5px 12px 0;
    background: beige;
    border-radius: 3px;
    text-align: left;
    font-size: 15px;
    list-style: none;
    box-shadow: 0 1px 2px rgba(151,151,151,0.58);
  }

  .noComment{
    float:left;
    margin-left: 25%;
    background: beige;
    padding: 5px 12px 0;
    border-radius: 3px;
    box-shadow: 0 1px 2px rgba(151,151,151,0.58);
  }

</style>
