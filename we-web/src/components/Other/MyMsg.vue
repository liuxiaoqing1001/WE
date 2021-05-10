<template>
  <div class="msg">
    <div v-if="!showArticle" class="index-wrapper">
      <ul>
        <li class="msg-wrapper" v-for="item in list" :key="item.id" v-on:click="toDetail(item.aid,item.sid)">
          <p class="msg-sender" style="color: blue">{{item.sender}}</p>
          <p class="msg-sendDate">{{item.time}}</p>
<!--          <h2 class="msg-title">{{item.title}}</h2>-->
          <p class="msg-msgContent">{{item.msgContent}}</p>
          <div class="msg-tag">
            <ul>
              <li>
                <span style="text-indent: 2em">{{item.content}}</span>
              </li>
            </ul>
          </div>
        </li>
      </ul>
    </div>
    <div v-else class="detail">
      <i class="el-icon-arrow-left" style="float: left;font-weight: bold" @click="comeBack()">
        <span style="font-weight: normal;font-size: 15px;color: blue">返回</span>
      </i>
      <div id="content">
        <h3>{{articleList.title}}</h3>
        <h6>{{articleList.sender}}</h6>
        <div>
          <span style="font-size:15px;margin-right: 30px">{{articleList.sendDate}}</span>
          <span style="font-size:18px">
          <i v-if="!isPraise" class="el-icon-star-off" @click="changeP(articleList.id)" style="margin-right: 10px">{{praiseNum}}</i>
          <i v-else class="el-icon-star-on " @click="changeP(articleList.id)" style="margin-right: 10px">{{praiseNum}}</i>
          <i class="el-icon-edit">{{commentNum}}</i>
        </span>
        </div>
        <!-- 用v-html解析后台传来的HTML代码 -->
        <div v-html="articleList.content"></div>
      </div>
      <div class="commentArea">
        <div class="send">
          <textarea v-model="comment.content" class="write" placeholder="说点什么吧！"></textarea>
          <button @click="send(articleList.id)" type="button" class="btn_say">发送</button>
        </div>
        <span v-if="!isComment" class="noComment">暂无评论</span>
        <ul v-else class="list-group">
          <li class="list-group-item" v-for="cItem in cList" :key="cItem.id">
            <span class="badge">{{ cItem.sender }}</span>
            {{ cItem.content }}
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
      name: "MyMsg",
      inject:['reload'],
      data(){
        return{
          list:[],
          showArticle:false,
          articleList:[],
          cList:[],
          comment:{
            content: ""
          },
          praiseNum:0,
          commentNum:0,
          isComment:false,
          isPraise:false
        }
      },
      created() {
        this.getMsgList(window.sessionStorage.getItem('id'));
      },
      methods:{
        toDetail(aid,sid){
          if(sid===null){
            // this.showArticle=true;
            // this.requestData(aid);
            this.$router.push({
              path: 'Detail',
              query: {
                id:aid
              }
            })
          }
        },
        getMsgList(id){
          this.$http.get("/user/getMsgList",{
            params:{
              receiver:id
            }
          }).then(response => {
            if (response.data.errorCode===0){
              this.list = response.data.data;
            }else {
              this.$message.error(response.data.msg);
            }
          });
        },
        requestData(aid){
          this.$http.get("/article/getArticleById",{
            params:{
              id:aid
            }
          }).then(response => {
            if (response.data.errorCode===0){
              this.articleList = response.data.data;
            }else {
              this.$message.error(response.data.msg);
            }
          });
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
          this.$http.get("/article/getIsP",{
            params:{
              aid:aid,
              uid:window.sessionStorage.getItem("id")
            }
          }).then(response => {
            if (response.data.errorCode===0){
              this.isPraise=true;
            }else {
              this.isPraise=false;
              // this.$message.error(response.data.msg);
            }
          });
        },
        changeP(aid){
          if(this.isPraise===false){
            this.$http.post("/article/addP",{
              aid:aid,
              uid:window.sessionStorage.getItem("id")
            }).then(response => {
              if (response.data.errorCode===0){
                this.isPraise=true;
                this.reload();
              }else {
                this.isPraise=false;
                this.$message.error(response.data.msg);
              }
            });
          }else {
            this.$http.delete("/article/delP/",{
              params:{
                aid:aid,
                uid:window.sessionStorage.getItem("id")
              }
            }).then(response => {
              if (response.data.errorCode===0){
                this.isPraise=false;
                this.reload();
              }else {
                this.isPraise=true;
              }
            });
          }
        },
        send(aid){
          //发送评论
          this.$http.post("/comment/addA",{
            aid:aid,
            content:this.comment.content,
            sender:window.sessionStorage.getItem('token'),
            receiver:this.articleList.sender
          }).then(response => {
            if (response.data.errorCode===0){
              this.$message.success(response.data.msg);
              this.reload();
            }else {
              this.$message.error(response.data.msg);
            }
          });
        },
        comeBack() {
          this.showArticle=false;
        }
      }
    }
</script>

<style scoped>
  .msg{
    width: 60%;
    margin-top: 60px;
    margin-left: 20%;
    margin-right: 20%;
  }
  .index-wrapper{
    margin-left: 10%;
    margin-right: 10%;
  }
  .msg-wrapper{
    margin-bottom: 30px;
    padding: 12px 12px 0;
    background: beige;
    border-radius: 3px;
    text-align: left;
    list-style: none;
    box-shadow: 0 1px 2px rgba(151,151,151,0.58);
    /*max-width: 100%;*/
    min-width: 700px;
  }
  .msg-sender,.msg-sendDate{
    line-height: 24px;
    margin: 0 0 0px;
    font-size: 13px;
    font-weight: bold;
    color: #727272;
    overflow: hidden;
  }
  .msg-msgContent{
    /*line-height: 24px;*/
    font-size: 15px;
    /*font-weight: bold;*/
    color: black;
    overflow: hidden;
    text-indent: 2em;
  }

  .msg-tag li{
    display: inline-block;
    margin: 0 8px 8px 0;
    border-radius: 2px;
    /*background: white;*/
    /*padding: 0 16px;*/
    line-height: 28px;
    color: mediumseagreen;
    font-size: 18px;
  }
  li:hover{
    cursor: pointer;
  }

  .detail{
    width: 70%;
    margin-right: 10%;
    margin-left: 10%;
    /*margin-top: 60px;*/
  }

  #content {
    padding: 1rem;
    line-height: 2;
  }
  img{
    max-width: 100%;
  }

  .commentArea{
    margin-top: 10%;
    margin-right: 20%;
    margin-left: 20%;
    font-size: 15px;
    background:rgba(312,500,500,0.5);
    width: 60%;
    border-radius: 10px;
  }

  .write{
    float: left;
    width: 75%;
    max-height: 80%;
    margin-left: 30px;
    margin-top: 15px;
    border: 1px solid #DCDFE6;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    box-shadow: 0 0 10px #909399;
  }

  i:hover{
    cursor: pointer;
  }

  .btn_say{
    display: inline-block;
    overflow: hidden;
    position: relative;
    border-radius: 10px;
    height: 36px;
    line-height: 36px;
    min-width: 60px;
    transition-duration: .3s;
    transition-timing-function: cubic-bezier(.23,1,.32,1);
    -webkit-transform: translateZ(0);
    transform: translateZ(0);
    text-decoration: none;
    text-transform: uppercase;
    border: none;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    background-color: #fff;
    color: rgba(0,0,0,.87);
    -webkit-box-flex: 0;
    -webkit-flex-shrink: 0;
    -ms-flex: 0 0 auto;
    -ms-flex-negative: 0;
    flex-shrink: 0;
    /*margin: 0;*/
    margin-top: 15px;
    /*margin-left: 15px;*/
    /*margin-right: 5px;*/
    outline: 0;
    padding: 0;
    cursor: pointer;
    box-shadow: 0 1px 6px rgba(0,0,0,.117647), 0 1px 4px rgba(0,0,0,.117647);
  }

  .list-group-item{
    margin-top: 20px;
    margin-bottom: 15px;
    margin-left: 18%;
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
    margin-left: 40%;
    margin-top: 20px;
    margin-bottom: 20px;
    background: beige;
    padding: 5px 12px 0;
    border-radius: 3px;
    box-shadow: 0 1px 2px rgba(151,151,151,0.58);
  }
</style>
