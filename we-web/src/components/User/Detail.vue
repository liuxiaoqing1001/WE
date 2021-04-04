<template>
  <div class="detail">
    <div id="content">
      <h3>{{list.title}}</h3>
      <h6>{{list.sender}}</h6>
      <div>
        <span style="font-size:15px">{{list.sendDate}}</span>
        <span style="font-size:18px">
          点赞
          评论
        </span>

      </div>
      <!-- 用v-html解析后台传来的HTML代码 -->
      <div v-html="list.content">
      </div>
    </div>
  </div>
</template>

<script>
    export default {
      name: "Detail",
      data(){
        return{
          list:[]
        }
      },
      mounted(){
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
              this.list = response.data.data;
            }else {
              this.$message.error(response.data.msg);
            }
          });
        }
      }
    }
</script>

<style scoped>
  .detail{
    width: 1100px;
    margin-right: 100px;
    margin-top: 60px;
  }


  #content {
    padding: 1rem;
    line-height: 2;
  }
  img{
    max-width: 100%;
  }
</style>
