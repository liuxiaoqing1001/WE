<template>
  <div class="detail">
    <div id="content">
      <h2>{{list.title}}</h2>
      <!-- 用v-html解析后台传来的HTML代码 -->
      <div  v-html="list.content">
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
        //console.log(this.$route.params)/* 获取动态路由传值 */
        var aid=this.$route.params.aid;
        // console.log(aid)
        // 调用requestData()方法请求新闻内容
        this.requestData(aid);

      },
      methods:{
        requestData(aid){
          // 新闻详情接口
          var api='http://www.phonegap100.com/appapi.php?a=getPortalArticle&aid=1';//+aid;
          // get请求如果跨域的话，后台php java要允许跨域请求
          this.$http.get(api).then((res)=>{
            this.list=res.body.result[0]
          },(err)=>{
            console.log(err)
          })
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
