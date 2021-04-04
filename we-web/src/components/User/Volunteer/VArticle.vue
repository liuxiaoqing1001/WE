<template>
<div class="index-wrapper">
  <a class="intoW" @click="intoW()">写文章</a>
  <ul>
    <li class="blog-wrapper" v-for="item in articleList" :key="item.id" v-on:click="toDetail(item.id)">
      <p class="blog-sender">{{item.sender}}</p>
      <p class="blog-sendDate">{{item.sendDate}}</p>
      <h2 class="blog-title">{{item.title}}</h2>
      <div class="blog-tag">
        <ul>
          <li>{{item.type}}</li>
        </ul>
      </div>
    </li>
  </ul>
</div>
</template>

<script>
    export default {
      name: "VArticle",
      data(){
        return{
          articleList:[]
        }
      },
      created() {
        this.getArticleList();
      },
      methods:{
        intoW:function () {
          this.$router.push("/VWrite");
          // this.$message.success('跳转');
        },
        toDetail(id){
          this.$router.push({
            path: 'Detail',
            query: {
              id:id
            }
          })
        },
        getArticleList(id){
          this.$http.get("/article/getArticleByUser",{
            params:{
              sender:window.sessionStorage.getItem('id')
            }
          }).then(response => {
            if (response.data.errorCode===0){
              this.articleList = response.data.data;
            }else {
              this.$message.error(response.data.msg);
            }
          });
        }
      }
    }
</script>

<style scoped>
  .index-wrapper{
    max-width: 960px;
    margin: 30px auto 40px;
  }
  .blog-wrapper{
    margin-bottom: 30px;
    padding: 12px 12px 0;
    background: #fff;
    border-radius: 3px;
    text-align: left;
    list-style: none;
    box-shadow: 0 1px 2px rgba(151,151,151,0.58);
  }
  .blog-sender,.blog-sendDate{
    line-height: 24px;
    margin: 0 0 0px;
    font-size: 13px;
    font-weight: bold;
    color: #727272;
    overflow: hidden;
  }
  .blog-title{
    margin-bottom: 10px;
    font-size: 24px;
    line-height: 32px;
    color: #3f51b5;
    border-bottom: 1px solid #ddd;
    padding-bottom: 15px;
  }
  .blog-content{
    word-break: break-all;
    padding-bottom: 20px;
    line-height: 1.8;
  }
  .blog-more{
    display: inline-block;
    padding: 0 6px;
    font-weight: 500;
    color: #3f51b5 !important;
    border: none !important;
    border-radius: 3px;
  }
  .blog-tag{
    position: relative;
    margin-left: 80%;
    /*padding-top: 8px;*/
    /*margin: 0 -12px;*/
    /*float: right;*/
    /*padding-bottom: 10px;*/
    /*border-top: 1px solid #ddd;*/
  }
  .blog-tag li{
    display: inline-block;
    margin: 0 8px 8px 0;
    border-radius: 2px;
    background: #8bc34a;
    padding: 0 16px;
    line-height: 28px;
    color: rgba(255,255,255,0.8);
  }
  li:hover{
    cursor: pointer;
  }
</style>
