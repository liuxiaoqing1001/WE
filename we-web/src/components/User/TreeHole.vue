<template>
  <div>
    <div class="main">
      <vue-particles color="#ffffff" :particleOpacity="0.7" :particlesNumber="15"
                     shapeType="star" :particleSize="4" linesColor="#ffffff" :linesWidth="2"
                     :lineLinked="true" :lineOpacity="0.4" :linesDistance="150" :moveSpeed="1"
                     :hoverEffect="true" hoverMode="grab" :clickEffect="true" clickMode="push"></vue-particles>
      <div class="tips">
        嘿~你好呀！<br>
        见字如面，<br>
        我是未来的你，很高兴遇见你！<br>
        我知道，生而为人，每个人都有自己要经历的伤痛与苦难。<br>
        所以，我想看见你，<br>
        看见你的孤独、无助、悲伤、痛苦、快乐、希望……<br>
        和你站在一起，去面对这世界的狂风暴雨，<br>
        希望你在这里，可以做回从前那个少年，<br>
        难过了就哭，开心时就笑，累了就停下来休息，充满电就继续前行，<br>
        永远年轻，永远纯粹，永远朝气蓬勃，永远热泪盈眶，<br>
        仰望星空，脚踏实地，<br>
        世界和我爱着你！<br>
      </div>

      <div v-if="showIndex===0" class="listen_say">
        <div class="tip">
          告诉星星，也许会得到你想要的，放心，没人知道你是谁
        </div>
        <button @click="listen()" type="button" class="btn_listen">聆听</button>
        <button @click="say()" type="button" class="btn_say">倾诉</button>
      </div>

      <div v-if="showIndex===1" class="bulletFrame">
        <el-button @click="del()" type="danger" size="mini" icon="" style="font-size: medium;float: right">X</el-button>
        <div class="talkAbout">
          <div class="word">
            <span style="font-size: 15px;color: blue">来自缘分的轻语：</span>
            <br/>
            <p style="font-size: 20px;text-indent: 2em">{{randSay.content}}</p>
          </div>
          <div class="option">
            <!--            <i v-if="!isPraise" class="el-icon-star-off praise"></i>-->
            <!--            <i v-else class="el-icon-star-on praise" style="color: darkred"></i>-->
            <i @click="listen()" class="el-icon-refresh" style="color: blue; margin-right: 15px">
              <span style="font-size:15px;color: black">切换</span>
            </i>
            <i @click="comment()" class="el-icon-edit"></i>
          </div>
          <div v-if="isWrite" >
            <textarea class="comment" v-model="myComment" placeholder="To:亲爱的陌生人"></textarea>
<!--            <button @click="listen()" type="button" class="btn_say">换一个</button>-->
            <button @click="send2(randSay.id,randSay.sender)" type="button" class="btn_say" style="margin-left: 40%">发送</button>
          </div>

        </div>

      </div>
      <div v-if="showIndex===2" class="bulletFrame">
        <el-button @click="del()" type="danger" size="mini" icon="" style="font-size: medium;float: right">X</el-button>
        <div>
          <textarea class="write" v-model="myContent" placeholder="To:未来的自己"></textarea>
          <button @click="send1()" type="button" class="btn_say">发送</button>
        </div>
      </div>
    </div>
  </div>
<!--  color: String类型。默认'#dedede'。粒子颜色。-->
<!--  particleOpacity: Number类型。默认0.7。粒子透明度。-->
<!--  particlesNumber: Number类型。默认80。粒子数量。-->
<!--  shapeType: String类型。默认'circle'。可用的粒子外观类型有："circle","edge","triangle", "polygon","star"。-->
<!--  particleSize: Number类型。默认80。单个粒子大小。-->
<!--  linesColor: String类型。默认'#dedede'。线条颜色。-->
<!--  linesWidth: Number类型。默认1。线条宽度。-->
<!--  lineLinked: 布尔类型。默认true。连接线是否可用。-->
<!--  lineOpacity: Number类型。默认0.4。线条透明度。-->
<!--  linesDistance: Number类型。默认150。线条距离。-->
<!--  moveSpeed: Number类型。默认3。粒子运动速度。-->
<!--  hoverEffect: 布尔类型。默认true。是否有hover特效。-->
<!--  hoverMode: String类型。默认true。可用的hover模式有: "grab", "repulse", "bubble"。-->
<!--  clickEffect: 布尔类型。默认true。是否有click特效。-->
<!--  clickMode: String类型。默认true。可用的click模式有: "push", "remove", "repulse", "bubble"-->

</template>

<script>
    export default {
      name: "TreeHole",
      data() {
        return {
          showIndex: 0,
          isWrite: false,
          isShowWrite:false,
          myContent:'',
          randSay:{
            id:'',
            sender:'',
            content:''
          },
          myComment:''
        }
      },
      methods: {
        listen:function(){
          this.showIndex=1;
          this.$http.get("/user/getRandSay").then(response => {
            if (response.data.errorCode===0){
              this.randSay = response.data.data;
              this.myContent='';
              this.myComment='';
            }else {
              this.$message.error(response.data.msg);
            }
          });
          // this.$message.success('聆听');
        },
        say:function(){
          this.showIndex=2;
          // this.$message.success('倾诉');
        },
        del:function () {
          this.showIndex=0;
          this.isWrite=false;
          this.isShowWrite=false;
          this.myContent='';
          this.myComment='';
        },
        send1:function () {
          if(window.sessionStorage.getItem("id")===null){
            this.$message.error("未登录");
          }else {
            this.$http.post("/user/sendSay", {
              sender: window.sessionStorage.getItem("id"),
              content: this.myContent
            }).then(response => {
              if (response.data.errorCode === 0) {
                this.$message.success(response.data.msg);
                this.showIndex = 0;
                this.myContent = '';
              } else {
                this.$message.error(response.data.msg);
              }
            });
          }
        },
        send2:function (sid,receiver) {
          if(window.sessionStorage.getItem("id")===null){
            this.$message.error("未登录");
          }else {
            //发送评论
            this.$http.post("/comment/addS", {
              sid: sid,
              content: this.myComment,
              sender: window.sessionStorage.getItem("id"),
              receiver: receiver
            }).then(response => {
              if (response.data.errorCode === 0) {
                this.isWrite = false;
                this.showIndex = 0;
                this.$message.success(response.data.msg);
                this.myComment = '';
                this.reload();
              } else {
                this.$message.error(response.data.msg);
              }
            });
          }
        },
        comment:function () {
          if(!this.isShowWrite){
            this.isWrite=true;
            this.isShowWrite=true;
          }else{
            this.isWrite=false;
            this.isShowWrite=false;
          }
          // this.$message.success('评论');
        }
      },
    }
</script>

<style scoped>
  .main {
    width: 100%;
    height: 100%;
    min-height: 910px;
    /*background-color: #fff;*/
    /*background: radial-gradient(ellipse at bottom, #090a0f 0%, #000000 100%);*/
    background-image: url("https://img.zcool.cn/community/010d1d57620dde0000012e7e2aa1e7.jpg@3000w_1l_0o_100sh.jpg");
    background-repeat:no-repeat;
    background-size:100% 100%;
    -moz-background-size:100% 100%;
    overflow: auto;
    filter: drop-shadow(0 0 0px white);
    /*position: relative;*/
  }
  .tips{
    color: white;
    font-size: 15px;
    text-align: left;
    position: absolute;
    left: 3%;
    bottom: 10%;
  }
  .listen_say{
    color: white;
    font-size: 15px;
    /*text-align: left;*/
    position: absolute;
    left: 50%;
    top: 40%;
    /*bottom: 50%;*/
  }
  .btn_listen,.btn_say{
    display: inline-block;
    overflow: hidden;
    position: relative;
    border-radius: 10px;
    height: 36px;
    line-height: 36px;
    min-width: 88px;
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
    margin-top: 30px;
    margin-left: 15px;
    margin-right: 15px;
    outline: 0;
    padding: 0;
    cursor: pointer;
    box-shadow: 0 1px 6px rgba(0,0,0,.117647), 0 1px 4px rgba(0,0,0,.117647);
  }

  .bulletFrame{
    background:rgba(312,500,500,0.5);
    width: 750px;
    height: 400px;
    border-radius: 10px;
    position: absolute;
    left: 25%;
    top: 30%;
  }

  .talkAbout{
    /*background: white;*/
    width: 90%;
    height: 90%;
    /*position: absolute;*/
    /*left: 5%;*/
    /*top: 10%;*/
    margin-left: 30px;
    margin-top: 20px;
    text-align: left;
  }

  .word{
    margin-left: 10px;
    margin-top: 10px;
    margin-bottom: 10px;
    font-size: 25px;
  }

  .option{
    float: right;
    margin-right: 10px;
    margin-top: 10px;
    margin-bottom: 10px;
  }

  .write{
    width: 85%;
    height: 200px;
    max-height: 80%;
    margin-left: 30px;
    margin-top: 50px;
  }

  .comment{
    width: 90%;
    height: 100px;
    max-height: 80%;
    margin-left: 30px;
    margin-top: 30px;
  }

  i:hover{
    cursor: pointer;
  }

</style>
