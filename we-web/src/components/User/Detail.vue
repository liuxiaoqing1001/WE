<template>
  <div class="detail">
    <div id="content">
      <h3>{{articleList.title}}</h3>
      <hr>
      <div>
        <span class="span_div">
          <span class="span_tip">作者：</span>
          {{articleList.sender}}
        </span>
        <span class="span_div">
          <span class="span_tip">发布时间：</span>
          {{articleList.sendDate}}
        </span>
        <span style="font-size:16px">
          <i v-if="!isPraise" class="el-icon-star-off" @click="changeP(articleList.id)" style="margin-right: 10px">
             <span class="span_num">{{praiseNum}}</span>
          </i>
          <i v-else class="el-icon-star-on " @click="changeP(articleList.id)" style="margin-right: 10px">
            <span class="span_num">{{praiseNum}}</span>
          </i>
          <i class="el-icon-edit">
            <span class="span_num">{{commentNum}}</span>
          </i>
        </span>
      </div>
      <!-- 用v-html解析后台传来的HTML代码 -->
      <div class="span_content" v-html="articleList.content"></div>
    </div>
    <div class="commentArea">
      <hr>
      <div class="send">
        <textarea v-model="comment.content" class="write" placeholder="说点什么吧！"></textarea>
        <button @click="send(articleList.id)" type="button" class="btn_say">发送</button>
      </div>

      <ul class="list-group">
        <li v-if="!isComment"><span class="noComment">暂无评论</span></li>
        <li v-else class="list-group-item" v-for="cItem in cList" :key="cItem.id">
          <span class="badge"> {{ cItem.sender }} >> </span>
          {{ cItem.content }}
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
    export default {
      name: "Detail",
      inject:['reload'],
      data(){
        return{
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
      created(){
        // 获取动态路由传值
        var aid=this.$route.query.id;
        // 调用requestData()方法请求数据
        this.requestData(aid);
      },
      methods:{
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
          if(window.sessionStorage.getItem("id")===null){
            this.$message.error("未登录");
          }else {
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
          }
        },
        send(aid) {
          if (window.sessionStorage.getItem("id") === null) {
            this.$message.error("未登录");
          } else {
            //发送评论
            this.$http.post("/comment/addA", {
              aid: aid,
              content: this.comment.content,
              sender: window.sessionStorage.getItem('token'),
              receiver: this.articleList.sender
            }).then(response => {
              if (response.data.errorCode === 0) {
                this.$message.success(response.data.msg);
                this.reload();
              } else {
                this.$message.error(response.data.msg);
              }
            });
          }
        }
      }
    }
</script>

<style scoped>
  .detail{
    /*width: 1100px;*/
    min-height: 855px;
    /*background: "../../assets/image/1.jpeg";*/
    background-image: url("../../assets/image/1.jpeg") ;
    background-repeat:no-repeat;
    background-size:100% 100%;
    /*margin-right: 100px;*/
    /*margin-top: 60px;*/
  }

  #content {
    padding: 1rem;
    line-height: 2;
  }

  .span_tip{
    font-size: 13px;
    color: #4a5064
  }

  .span_div{
    font-size: 15px;
    margin-right: 20px;
    color: #42b983;
    font-weight: bold;
  }

  .span_num{
    color: #42b983;
    font-weight: bold;
  }

  .span_content{
    text-align: left;
    width: 50%;
    font-size: 18px;
    /*float: left;*/
    margin-left: 25%;
    margin-right: 25%;
    text-indent: 2em;
    background: beige;
  }

  img{
    max-width: 100%;
  }

  .commentArea{
    margin-top: 10%;
    width: 100%;
    /*margin-bottom: 20%;*/
    /*!*margin-right: 30%;*!*/
    /*!*text-align: center;*!*/
    /*margin-left: 16%;*/
    font-size: 15px;
    /*background:rgba(312,500,500,0.5);*/
    /*background: #42b983;*/
    /*width: 500px;*/
    /*height: 400px;*/
    border-radius: 10px;
    /*position: absolute;*/
    /*left: 25%;*/
    /*top: 30%;*/
  }

  /*.send{*/
  /*  float: left;*/
  /*  height: 100px;*/
  /*  width: 100%;*/
  /*}*/


  /*.send{*/
  /*  background:rgba(312,500,500,0.5);*/
  /*  width: 500px;*/
  /*  !*height: 400px;*!*/
  /*  border-radius: 10px;*/
  /*  position: absolute;*/
  /*  !*left: 25%;*!*/
  /*  !*top: 30%;*!*/
  /*}*/

  .write{
    float: left;
    width: 50%;
    max-height: 80%;
    margin-left: 20%;
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
    margin-right: 15%;
    outline: 0;
    padding: 0;
    cursor: pointer;
    box-shadow: 0 1px 6px rgba(0,0,0,.117647), 0 1px 4px rgba(0,0,0,.117647);
  }

  .list-group-item{
    margin-top: 20px;
    /*margin-bottom: 10%;*/
    margin-left: 30%;
    width: 30%;
    /*width: auto;*/
    padding: 5px 12px 0;
    background: beige;
    border-radius: 3px;
    text-align: left;
    font-size: 15px;
    list-style: none;
    box-shadow: 0 3px 3px rgba(151,151,151,0.58);
  }

  li{
    list-style: none;
  }

  .noComment{
    /*float:left;*/
    /*margin-left: 10%;*/
    /*margin-top: 20px;*/
    /*margin-bottom: 20px;*/
    /*background: beige;*/
    /*padding: 5px 122px 0;*/
    /*border-radius: 3px;*/
    /*box-shadow: 0 1px 2px rgba(151,151,151,0.58);*/
  }
</style>
