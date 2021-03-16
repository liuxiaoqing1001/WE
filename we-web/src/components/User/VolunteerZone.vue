<template>
<!--  一进来先判断是否是咨询师-->
<!--  否：-->
<!--  加入条件-->
<!--  上传证件-->
<!--  等待审核-->
<!--  是：整个页面切换至-->
<!--  聊天室（咨询中心）-->
<!--  文章-->
<!--  自愿者个人中心-->
  <div>
    <div class="main">
      <div v-if="!applyV" class="frame">
        <h2>咨询师入驻标准</h2>
        <div class="standard">
          <h5>1. 前提条件</h5>
          申请成为平台入驻自愿者咨询师需要同时符合以下条件：<br>
          (1) 拥有法律要求的执业资格。
          需持有国家二级心理咨询师、国家三级心理咨询师，注册系统咨询师，注册系统督导师，婚姻家庭咨询师，心理治疗师资质或港澳台及海外法律认可的执业资格等。<br>
          (2) 全日制大专及以上学历（1999年12月31日以前取得相应学历学位证书）。<br>
          (3) 一定的咨询经验是专业水平的保障。<br>
          <h5>2. 申请方式</h5>
          满足以上条件的申请者，点击<a class="intoA" @click="into()">网上入驻通道</a>进入，
          按要求提供材料进行审核，即可进入入驻考察环节，成为平台新晋咨询师，
          新晋咨询师入驻平台半年后，提交个案报告后要可参加转正面试,面试通过即可转为平台正式入驻咨询师。<br>
          <h5>3. 网上入驻提供材料</h5>
          学历证明（必须）、 法律要求的执业资格证（必须）、 相关论文和书籍（非必须）
        </div>
      </div>

      <div v-else class="frameAV">
        <el-button @click="del()" type="danger" size="mini" icon="" style="font-size: medium;float: right">X</el-button>
        <h2>咨询师入驻申请</h2>
        <div class="standard">
          申请表格、上传附件
        </div>
      </div>
    </div>
  </div>


</template>

<script>
    export default {
      name: "VolunteerZone",
      data(){
        return{
          applyV: false
        }
      },
      methods:{
        into:function () {
          this.applyV=true;
          this.$router.push("/VolunteerCenter");
          this.$message.success('跳转');
        },
        del:function () {
          this.applyV=false;
        },
      }
    }
</script>

<style scoped>
  .main{
    width: 100%;
    min-width: 1200px;
    height: 900px;
    background-image: url("../../assets/background/background.jpeg");
    background-repeat:no-repeat;
    background-size:100% 100%;
    -moz-background-size:100% 100%;
    overflow: hidden;
    filter: drop-shadow(0 0 10px white);
    /*color: white;*/
    /*position: relative;*/
  }

  .frame{
    background:rgba(312,500,500,0.5);
    width: 1200px;
    height: 600px;
    border-radius: 10px;
    position: absolute;
    left: 15%;
    top: 20%;
  }

  .frameAV{
    background: white;
    width: 1500px;
    height: 850px;
    /*border-radius: 10px;*/
    position: absolute;
    left: 8%;
    top: 5%;
  }

  .standard{
    /*color: white;*/
    /*font-size: 15px;*/
    text-align: left;
    margin-left: 20px;
    margin-right: 20px;
    /*position: absolute;*/
    /*left: 3%;*/
    /*bottom: 5%;*/
  }

  .intoA{
    color: blue;
  }

  a:hover{
    cursor: pointer;
  }
</style>
