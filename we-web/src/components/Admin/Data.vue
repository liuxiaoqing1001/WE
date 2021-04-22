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
          <ve-pie :data="chartData2"></ve-pie>
        </el-col>
        <el-col :span="15">
          <ve-line :data="chartData" width="100%"></ve-line>
        </el-col>
      </el-row>

      <ve-bar :data="chartData"></ve-bar>

      <el-row :gutter="20">
        <el-col :span="15">
          <ve-histogram
            :data="chartData"
            :legend-visible="true"
            :loading="loading"
            :extend="extend"
            width="100%"
            :settings="chartSettings">
          </ve-histogram>
        </el-col>
        <el-col :span="6">
          <ve-ring :data="chartData2"></ve-ring>
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
        chartData: {
          columns: ['角色', '总数量','占比'],
          rows: [
            // { '角色': '普通用户', '总数量': 4 , '占比': 2},
            // { '角色': '自愿者', '总数量': 4 , '占比': 1},
            // { '角色': '管理员', '总数量': 4 , '占比': 1},
          ]
        },
        chartData2: {
          columns: ['角色', '数量'],
          rows: [
            { '角色': '普通用户', '数量': 2 },
            { '角色': '自愿者', '数量': 1 },
            { '角色': '管理员', '数量': 1 },
          ]
        },
        count: '',
      }
    },
    created () {
      this.load();
    },
    methods: {
      load () {
        this.getUserCountData();
      },
      getUserCountData () {
        this.$http.get("/user/getUserCountData").then(response => {
          if (response.data.errorCode===0){
            this.$message.success(response.data.msg);
            console.log(response);
            this.chartData.rows = response.data.data;
            // this.userList = response.data.data;
          }else {
            this.$message.error(response.data.msg);
          }
        });
      },

    },

    //用户的数据，文章的数据（时间，数量），树洞的数据（时间，数量），申请自愿者的数据（时间，数量）

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
