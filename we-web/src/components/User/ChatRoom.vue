<template>
  <div class="chat">
    <div class="chat-box">
      <!--    v-model		输入框中的文字-->
      <!--    taleList	要渲染的数据-->
      <!--    toolConfig 工具栏配置	-->
      <!--    width		  聊天框宽度	-->
      <!--    height		聊天框高度-->
      <!--    config		组件配置项	-->
      <!--    scrollType 消息自动到低	-->
      <!--    quickList	自动匹配快捷回复	-->
      <JwChat-index
        :config="config"
        :showRightBox='false'
        scrollType="scroll"
        :taleList="list"
        @enter="bindEnter"
        v-model="inputMsg"
        :toolConfig="tool" />
    </div>
  </div>

</template>

<script>
    export default {
      name: "ChatRoom",
      data() {
        return {
          inputMsg: '',
          list: [],
          tool: {
            callback: this.toolEvent
          },
          config: {
            img: '../../../static/img/room.png',
            name: '咨询室',
            // dept: '最简单、最便捷',
            callback: this.bindCover,
            // historyConfig:{
            //   show: true,
            //   tip: '加载更多',
            //   callback: this.bindLoadHistory,
            // }
          },
          currentTime:"",
          role:0
        };
      },
      methods: {
        getCurrentTime() {
          let yy = new Date().getFullYear();
          var mm =
            new Date().getMonth() < 10
              ? "0" + (new Date().getMonth() + 1)
              : new Date().getMonth() + 1;
          var dd =
            new Date().getDate() < 10
              ? "0" + new Date().getDate()
              : new Date().getDate();
          let hh = new Date().getHours();
          let mf =
            new Date().getMinutes() < 10
              ? "0" + new Date().getMinutes()
              : new Date().getMinutes();
          let ss =
            new Date().getSeconds() < 10
              ? "0" + new Date().getSeconds()
              : new Date().getSeconds();
          this.currentTime = yy + "-" + mm + "-" + dd + " " + hh + ":" + mf + ":" + ss;
        },
        bindEnter () {
          const msg = this.inputMsg;
          if (!msg) return;
          this.getCurrentTime();
          var msgObj;
          if (this.role===2){
            msgObj = {
              "date": this.currentTime,
              "text": { "text": msg },
              "mine": true,
              // "name": "JwChat",
              "img": "../../../static/img/volunteer.png"
            };
          }else{
            msgObj = {
              "date": this.currentTime,
              "text": { "text": msg },
              "mine": true,
              // "name": "JwChat",
              "img": "../../../static/img/consultants.png"
            };
          }

          const other = {
            "cid": window.sessionStorage.getItem("cid"),
            "userId": window.sessionStorage.getItem("id"),
          };
          this.$http.post("/msg/addMsg",{
            msgObj:msgObj,
            other:other
          }).then(response => {
            if (response.data.errorCode===1){
              this.list.push(msgObj)
            }else {
              this.$message.error(response.data.msg);
            }
          });
        },
        toolEvent (type, obj) {
          console.log('tools', type, obj)
        },
        // bindLoadHistory () {
        //   this.getCurrentTime();
        //   const history = new Array(3).fill().map((i, j) => {
        //     return {
        //       "date": this.currentTime,
        //       "text": { "text": j + new Date() },
        //       "mine": false,
        //       // "name": "JwChat",
        //       "img": "../../../static/img/consultants.png"
        //     }
        //   });
        //   let list = history.concat(this.list);
        //   this.list = list;
        //   console.log('加载历史', list, history);
        // },
        bindCover (type) {
          console.log('header', type);
        },
        getRoleById(){
          this.$http.get("/user/getRoleById",{
            params:{
              id:window.sessionStorage.getItem("id"),
            }
          }).then(response => {
            if (response.data.errorCode===0){
              if(response.data.data==="2"){
                this.role=2;
              }else{
                this.role=1;
              }
            }else {
              this.$message.error(response.data.msg);
            }
          });
        },
        getAllByCid(cid){
          this.$http.get("/msg/getAllByCid",{
            params:{
              cid:cid,
              userId:window.sessionStorage.getItem("id")
            }
          }).then(response => {
            if (response.data.errorCode===0){
              // console.log(response);
              this.list = response.data.data;
              for (let i=0;i<this.list.length;i++){
                if((this.list[i].mine===false&&this.role===2)||(this.list[i].mine===true&&this.role===1)){
                  this.list[i].img="../../../static/img/consultants.png";
                  // this.list[i].name = ""
                }else {
                  this.list[i].img="../../../static/img/volunteer.png";
                  // this.list[i].name = ""
                }
                this.list[i].name = ""
              }
            }else {
              this.$message.error(response.data.msg);
            }
          });
        }
      },
      mounted () {
        var cid = window.sessionStorage.getItem("cid");
        console.log(cid);
        this.getRoleById();
        this.getAllByCid(cid);

        // const list = [
        //   {
        //     "date": "2020/04/25 21:19:07",
        //     "text": { "text": "起床不" },
        //     "mine": false,
        //     "name": "留恋人间不羡仙",
        //     "img": "/image/one.jpeg"
        //   },
        //   {
        //     "date": "2020/04/25 21:19:07",
        //     "text": { "text": "<audio data-src='https://www.w3school.com.cn/i/horse.mp3'/>" },
        //     "mine": false,
        //     "name": "只盼流星不盼雨",
        //     "img": "/image/two.jpeg"
        //   },
        //   {
        //     "date": "2020/04/25 21:19:07",
        //     "text": { "text": "<img data-src='"+img+"'/>" },
        //     "mine": false,
        //     "name": "只盼流星不盼雨",
        //     "img": "/image/two.jpeg"
        //   },
        //   {
        //     "date": "2020/04/16 21:19:07",
        //     "text": { "text": "<video data-src='https://www.w3school.com.cn/i/movie.mp4' controls='controls' />" },
        //     "mine": true,
        //     "name": "JwChat",
        //     "img": "/image/three.jpeg"
        //   },
        //   {
        //     "date": "2021/03/02 13:14:21",
        //     "mine": false,
        //     "name": "留恋人间不羡仙",
        //     "img": "/image/one.jpeg",
        //     "text": {
        //       system: {
        //         title: '在接入人工前，智能助手将为您首次应答。',
        //         subtitle: '猜您想问:',
        //         content: [
        //           {
        //             id: `system1`,
        //             text: '组件如何使用'
        //           },
        //           {
        //             id: `system2`,
        //             text: '组件参数在哪里查看'
        //           },
        //           {
        //             id: 'system',
        //             text: '我可不可把组件用在商业'
        //           }
        //         ]
        //       }
        //     }
        //   }
        // ];
        // this.list = list
      }
    }
</script>

<style scoped>
  .chat{
    width: 100%;
    min-width: 1200px;
    height: 920px;
    /*background-color: #fff;*/
    /*background: radial-gradient(ellipse at bottom, #090a0f 0%, #000000 100%);*/
    background-image: url("https://img.zcool.cn/community/010d1d57620dde0000012e7e2aa1e7.jpg@3000w_1l_0o_100sh.jpg");
    background-repeat:no-repeat;
    background-size:100% 100%;
    -moz-background-size:100% 100%;
    overflow: hidden;
    filter: drop-shadow(0 0 10px white);
    /*position: relative;*/
  }

  .chat-box{
    margin-top: 6%;
    /*background-color: darkseagreen;*/
  }

</style>
