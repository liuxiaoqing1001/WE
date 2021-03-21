<template>
<!--  <div class="chat">-->
<!--    <div class="chat-box">-->
<!--      <header>聊天室 (在线:{{count}}人)</header>-->
<!--      <div class="msg-box" ref="msg-box">-->
<!--        <div-->
<!--          v-for="(i,index) in list"-->
<!--          :key="index"-->
<!--          class="msg"-->
<!--          :style="i.userId == userId?'flex-direction:row-reverse':''">-->
<!--          <div class="user-head">-->
<!--            <img :src="i.avatar" height="30" width="30" :title="i.username">-->
<!--          </div>-->
<!--          <div class="user-msg">-->
<!--            <span :style="i.userId == userId?' float: right;':''" :class="i.userId == userId?'right':'left'">{{i.content}}</span>-->
<!--          </div>-->
<!--        </div>-->
<!--      </div>-->
<!--      <div class="input-box">-->
<!--        <input type="text" ref="sendMsg" v-model="contentText" @keyup.enter="sendText()" />-->
<!--        <div class="btn" :class="{['btn-active']:contentText}" @click="sendText()">发送</div>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->

  <div class="chat">
    <JwChat :taleList="list" @enter="bindEnter" v-model="inputMsg"
            :toolConfig="tool":quickList="quickList">
      <template slot="tools">
        <div style="width:20rem;text-align:right;" @click="toolEvent('自定义')">
          <b slot="tools">插槽</b>
          <JwChat-icon type="icon-lishi" title="自定义"/>
        </div>
      </template>
    </JwChat>
  </div>

</template>

<script>
    export default {
      name: "ChatRoom",
      data() {
        return {
          // ws: null,
          // count: 0,
          // userId: '123', // 当前用户ID
          // username: 'liu', // 当前用户昵称
          // // avatar: this.$store.getters.avatar, // 当前用户头像
          // // userId: this.$store.getters.id, // 当前用户ID
          // // username: this.$store.getters.name, // 当前用户昵称
          // // avatar: this.$store.getters.avatar, // 当前用户头像
          // list: [], // 聊天记录的数组
          // contentText: "" // input输入的值

          inputMsg: '',
          list: [],
          tool: {
            callback: this.toolEvent
          },
          quickList: [
            {text: '这里是jwchat，您想了解什么问题。'},
            {text: 'jwchat是最好的聊天组件'},
            {text: '谁将烟焚散，散了纵横的牵绊；听弦断，断那三千痴缠。'},
            {text: '长夏逝去。山野间的初秋悄然涉足。'},
            {text: '江南风骨，天水成碧，天教心愿与身违。'},
            {text: '总在不经意的年生。回首彼岸。纵然发现光景绵长。'},
            {text: '外面的烟花奋力的燃着，屋里的人激情的说着情话'},
            {text: '假如你是云，我就是雨，一生相伴，风风雨雨；'},
            {text: '即使泪水在眼中打转，我依旧可以笑的很美，这是你学不来的坚强。'},
            {text: ' 因为不知来生来世会不会遇到你，所以今生今世我会加倍爱你。'},
          ]
        };
      },
      methods: {
        bindEnter () {
          const msg = this.inputMsg
          if (!msg) return;
          const msgObj = {
            "date": "2020/05/20 23:19:07",
            "text": { "text": msg },
            "mine": true,
            "name": "JwChat",
            "img": "../image/three.jpeg"
          }
          this.list.push(msgObj)
        },
        toolEvent (type, obj) {
          console.log('tools', type, obj)
        },
      },
      mounted () {
        const img = 'https://www.baidu.com/img/flexible/logo/pc/result.png'
        const list = [
          {
            "date": "2020/04/25 21:19:07",
            "text": { "text": "起床不" },
            "mine": false,
            "name": "留恋人间不羡仙",
            "img": "/image/one.jpeg"
          },
          {
            "date": "2020/04/25 21:19:07",
            "text": { "text": "<audio data-src='https://www.w3school.com.cn/i/horse.mp3'/>" },
            "mine": false,
            "name": "只盼流星不盼雨",
            "img": "/image/two.jpeg"
          },
          {
            "date": "2020/04/25 21:19:07",
            "text": { "text": "<img data-src='"+img+"'/>" },
            "mine": false,
            "name": "只盼流星不盼雨",
            "img": "/image/two.jpeg"
          },
          {
            "date": "2020/04/25 21:19:07",
            "text": { "text": "<img data-src='/image/three.jpeg'/>" },
            "mine": false,
            "name": "只盼流星不盼雨",
            "img": "/image/two.jpeg"
          },
          {
            "date": "2020/04/16 21:19:07",
            "text": { "text": "<video data-src='https://www.w3school.com.cn/i/movie.mp4' controls='controls' />" },
            "mine": true,
            "name": "JwChat",
            "img": "/image/three.jpeg"
          },
          {
            "date": "2021/03/02 13:14:21",
            "mine": false,
            "name": "留恋人间不羡仙",
            "img": "/image/one.jpeg",
            "text": {
              system: {
                title: '在接入人工前，智能助手将为您首次应答。',
                subtitle: '猜您想问:',
                content: [
                  {
                    id: `system1`,
                    text: '组件如何使用'
                  },
                  {
                    id: `system2`,
                    text: '组件参数在哪里查看'
                  },
                  {
                    id: 'system',
                    text: '我可不可把组件用在商业'
                  }
                ]
              }
            }
          }
        ]
        this.list = list
      }


      // mounted() {
      //   this.initWebSocket();
      // },
      // destroyed() {
      //   // 离开页面时关闭websocket连接
      //   this.ws.onclose(undefined);
      // },
      // methods: {
      //   // 发送聊天信息
      //   sendText() {
      //     let _this = this;
      //     _this.$refs["sendMsg"].focus();
      //     if (!_this.contentText) {
      //       return;
      //     }
      //     let params = {
      //       userId: _this.userId,
      //       username: _this.username,
      //       avatar: _this.avatar,
      //       msg: _this.contentText,
      //       count: _this.count
      //     };
      //     _this.ws.send(JSON.stringify(params)); //调用WebSocket send()发送信息的方法
      //     _this.contentText = "";
      //     setTimeout(() => {
      //       _this.scrollBottm();
      //     }, 500);
      //   },
      //   // 进入页面创建websocket连接
      //   initWebSocket() {
      //     let _this = this;
      //     // 判断页面有没有存在websocket连接
      //     if (window.WebSocket) {
      //       var serverHot =  window.location.hostname;
      //       let sip = '房间号'
      //       // 填写本地IP地址 此处的 :9101端口号 要与后端配置的一致！
      //       var url = 'ws://' + serverHot + ':9101' + '/groupChat/' + sip + '/' + this.userId; // `ws://127.0.0.1/9101/groupChat/10086/聊天室`
      //       let ws = new WebSocket(url);
      //       _this.ws = ws;
      //       ws.onopen = function(e) {
      //         console.log("服务器连接成功: " + url);
      //       };
      //       ws.onclose = function(e) {
      //         console.log("服务器连接关闭: " + url);
      //       };
      //       ws.onerror = function() {
      //         console.log("服务器连接出错: " + url);
      //       };
      //       ws.onmessage = function(e) {
      //         //接收服务器返回的数据
      //         let resData = JSON.parse(e.data)
      //         _this.count = resData.count;
      //         _this.list = [
      //           ..._this.list,
      //           { userId: resData.userId, username: resData.username, avatar: resData.avatar, content: resData.msg }
      //         ];
      //       };
      //     }
      //   },
      //   // 滚动条到底部
      //   scrollBottm() {
      //     let el = this.$refs["msg-box"];
      //     el.scrollTop = el.scrollHeight;
      //   }
      // }
    }
</script>

<style scoped>
  .chat{
    margin-top: 5%;
  }
  /*.chat-box {*/
  /*  margin: 0 auto;*/
  /*  background: #fafafa;*/
  /*  position: absolute;*/
  /*  height: 100%;*/
  /*  width: 100%;*/
  /*  max-width: 700px;*/
  /*}*/
  /*header {*/
  /*  position: fixed;*/
  /*  width: 100%;*/
  /*  height: 3rem;*/
  /*  background: #409eff;*/
  /*  max-width: 700px;*/
  /*  display: flex;*/
  /*  justify-content: center;*/
  /*  align-items: center;*/
  /*  font-weight: bold;*/
  /*  color: white;*/
  /*  font-size: 1rem;*/
  /*}*/
  /*.msg-box {*/
  /*  position: absolute;*/
  /*  height: calc(100% - 6.5rem);*/
  /*  width: 100%;*/
  /*  margin-top: 3rem;*/
  /*  overflow-y: scroll;*/
  /*}*/
  /*.msg {*/
  /*  width: 95%;*/
  /*  min-height: 2.5rem;*/
  /*  margin: 1rem 0.5rem;*/
  /*  position: relative;*/
  /*  display: flex;*/
  /*  justify-content: flex-start !important;*/
  /*}*/
  /*.user-head {*/
  /*  min-width: 2.5rem;*/
  /*  width: 20%;*/
  /*  width: 2.5rem;*/
  /*  height: 2.5rem;*/
  /*  border-radius: 50%;*/
  /*  background: #f1f1f1;*/
  /*  display: flex;*/
  /*  justify-content: center;*/
  /*  align-items: center;*/
  /*}*/
  /*.head {*/
  /*  width: 1.2rem;*/
  /*  height: 1.2rem;*/
  /*}*/
  /*!*position: absolute;*!*/
  /*!*}*!*/
  /*.user-msg {*/
  /*  width: 80%;*/
  /*  !*position: absolute;*!*/
  /*  word-break: break-all;*/
  /*  position: relative;*/
  /*  z-index: 5;*/
  /*}*/
  /*span {*/
  /*  display: inline-block;*/
  /*  padding: 0.5rem 0.7rem;*/
  /*  border-radius: 0.5rem;*/
  /*  margin-top: 0.2rem;*/
  /*  font-size: 0.88rem;*/
  /*}*/
  /*.left {*/
  /*  background: white;*/
  /*  animation: toLeft 0.5s ease both 1;*/
  /*}*/
  /*.right {*/
  /*  background: #53a8ff;*/
  /*  color: white;*/
  /*  animation: toright 0.5s ease both 1;*/
  /*}*/
  /*@keyframes toLeft {*/
  /*  0% {*/
  /*    opacity: 0;*/
  /*    transform: translateX(-10px);*/
  /*  }*/
  /*  100% {*/
  /*    opacity: 1;*/
  /*    transform: translateX(0px);*/
  /*  }*/
  /*}*/
  /*@keyframes toright {*/
  /*  0% {*/
  /*    opacity: 0;*/
  /*    transform: translateX(10px);*/
  /*  }*/
  /*  100% {*/
  /*    opacity: 1;*/
  /*    transform: translateX(0px);*/
  /*  }*/
  /*}*/
  /*.input-box {*/
  /*  padding: 0 0.5rem;*/
  /*  position: absolute;*/
  /*  bottom: 0;*/
  /*  width: 100%;*/
  /*  height: 3.5rem;*/
  /*  background: #fafafa;*/
  /*  box-shadow: 0 0 5px #ccc;*/
  /*  display: flex;*/
  /*  justify-content: space-between;*/
  /*  align-items: center;*/
  /*}*/
  /*input {*/
  /*  height: 2.3rem;*/
  /*  display: inline-block;*/
  /*  width: 100%;*/
  /*  padding: 0.5rem;*/
  /*  border: none;*/
  /*  border-radius: 0.2rem;*/
  /*  font-size: 0.88rem;*/
  /*}*/
  /*.btn {*/
  /*  height: 2.3rem;*/
  /*  min-width: 4rem;*/
  /*  background: #e0e0e0;*/
  /*  padding: 0.5rem;*/
  /*  font-size: 0.88rem;*/
  /*  color: white;*/
  /*  text-align: center;*/
  /*  border-radius: 0.2rem;*/
  /*  margin-left: 0.5rem;*/
  /*  transition: 0.5s;*/
  /*}*/
  /*.btn-active {*/
  /*  background: #409eff;*/
  /*}*/
</style>
