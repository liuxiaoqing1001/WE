<template>
  <div>
    <el-container>
      <el-header>
        <!--面包屑导航区域-->
        <el-breadcrumb >
          <el-breadcrumb-item :to="{ path: '/Data' }">数据统计</el-breadcrumb-item>
        </el-breadcrumb>
      </el-header>

      <!--      data：就是我们要绑定的数据，下面会详细介绍-->
      <!--      colors：就是我们图表中每一项对应的颜色-->
      <!--      legend-visible：是否显示图例-->
      <!--      loading：是否显示loaidng-->
      <!--      data-empty：在数据为空的时候，是否显示暂无数据-->
      <!--      extend：就是我们自定义的Echarts的原有属性，在v-charts的一些属性满足不了我们的需求的情况下，我们可以配置extend，来直接使用Echarts的optios，来覆盖v-charts的属性。-->
      <!--      settings：一些v-charts封装好的设置-->

      <el-row :gutter="20">
        <el-col :span="8">
          <ve-ring :data="chartUserData"></ve-ring>
        </el-col>
        <el-col :span="15">
          <ve-line :data="chartArticleData" width="100%"></ve-line>
        </el-col>
      </el-row>

<!--      <ve-bar :data="chartUserData"></ve-bar>-->

      <el-row :gutter="20">
        <el-col :span="13">
          <ve-line :data="chartVolunteerData" width="100%"></ve-line>
<!--          <ve-histogram-->
<!--            :data="chartUserData"-->
<!--            :legend-visible="true"-->
<!--            :loading="loading"-->
<!--            :extend="extend"-->
<!--            width="100%"-->
<!--            :settings="chartSettings">-->
<!--          </ve-histogram>-->
        </el-col>
        <el-col :span="10">
          <ve-bar :data="chartSayData" width="100%"></ve-bar>
<!--          <ve-ring :data="chartData2"></ve-ring>-->
        </el-col>
      </el-row>

    </el-container>

  </div>
</template>

<script>
  export default {
    name: "Data",
    // 折线图: ‘line’
    // 饼图: ‘pie’
    // 环形图: ‘ring’
    // 条形图: ‘bar’
    data(){
      return{
        chartSettings:{
          area:true
        },
        chartUserData: {
          columns: ['角色','占比', '总数量'],
          rows: []
        },
        chartArticleData: {
          columns: ['发布时间', '发布数量'],
          rows: []
        },
        chartSayData: {
          columns: ['发布时间', '发布数量'],
          rows: []
        },
        chartVolunteerData: {
          columns: ['申请时间', '申请人数','审核通过人数'],
          rows: []
        },
      }
    },
    created () {
      this.load();
    },
    methods: {
      load () {
        this.getUserCountData();
        this.getArticleData();
        this.getSayData();
        this.getVolunteerData();
      },
      getUserCountData () {
        this.$http.get("/user/getUserCountData").then(response => {
          if (response.data.errorCode===0){
            this.chartUserData.rows = response.data.data;
          }else {
            this.$message.error(response.data.msg);
          }
        });
      },
      getArticleData(){
        this.$http.get("/article/getArticleData").then(response => {
          if (response.data.errorCode===0){
            this.chartArticleData.rows = response.data.data;
          }else {
            this.$message.error(response.data.msg);
          }
        });
      },
      getSayData(){
        this.$http.get("/user/getSayData").then(response => {
          if (response.data.errorCode===0){
            this.chartSayData.rows = response.data.data;
          }else {
            this.$message.error(response.data.msg);
          }
        });
      },
      getVolunteerData(){
        this.$http.get("/user/getVolunteerData").then(response => {
          if (response.data.errorCode===0){
            this.chartVolunteerData.rows = response.data.data;
          }else {
            this.$message.error(response.data.msg);
          }
        });
      }
    },
  }
</script>

<style scoped>
  .el-breadcrumb{
    /*margin-bottom: 20px;*/
    margin-top: 15px;
    font-size: 13px;
    height: auto;
  }
  .el-card{
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1) !important;
    height: auto;
  }
  .el-table{
    height: auto;
  }
</style>
