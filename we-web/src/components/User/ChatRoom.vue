<template>
  <div class="chat">
    <div class="chat-box">
      <!--    v-model		输入框中的文字-->
      <!--    taleList	要渲染的数据-->
      <!--    toolConfig 工具栏配置	:toolConfig="tool"-->
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
        :toolConfig="tool"/>
    </div>
  </div>
</template>

<script>
  export default {
    name: "ChatRoom",
    data() {
      return {
        // 输入框内默认的消息
        inputMsg: "",
        // 聊天记录
        list: [],
        // 展示的工具栏配置
        tool: {
          // show: ['file', 'history', 'img', ['文件1', '', '美图']],
          show: null, // 关闭所有其他组件
          // showEmoji: true,
          callback: this.toolEvent,
        },

        // 正在聊天的用户的信息
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
        role:0,
        socket: new Object(),
        receiveUserId:0
      };
    },

    created() {
      this.getRoleById();
      this.getByCId();
      this.getAllByCid();
      this.webSocket();
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
      webSocket() {
        // 先记录this对象
        const that = this;
        if (typeof WebSocket == "undefined") {
          this.$message.error("浏览器暂不支持聊天");
          // MessageBox.alert("浏览器暂不支持聊天", "提示信息");
        } else {
          // 实例化socket，这里我把用户名传给了后台，使后台能判断要把消息发给哪个用户，其实也可以后台直接获取用户IP来判断并推送
          const socketUrl = "ws://127.0.0.1:8619/ws?id="+window.sessionStorage.getItem("id");
          this.socket = new WebSocket(socketUrl);
          // 监听socket打开
          this.socket.onopen = function () {
            console.log("浏览器WebSocket已打开");
          };
          // 监听socket消息接收
          this.socket.onmessage = function (messageEvent) {
            // 转换为json对象然后添加到chatlogTaleList
            let receivedLog = JSON.parse(messageEvent.data);
            let receivedLogs = new Array();
            receivedLogs.push(receivedLog);
            receivedLogs = that.reHandleChatLogs(receivedLogs);
            if (!that.list) {
              that.list = new Array();
            }
            that.list = that.list.concat(receivedLogs);
          };
          // 监听socket错误
          this.socket.onerror = function () {
            this.$message.error("连接失败");
          };
          // 监听socket关闭
          this.socket.onclose = function () {
            // MessageBox.alert("WebSocket已关闭");
            this.$message.error("端开连接");
          };
        }
      },
      // 发送websocket 消息
      send(message) {
        if (!window.WebSocket) {
          return;
        }
        // 封装消息，然后发送消息
        const chatLog = {
          userId: window.sessionStorage.getItem("id"),
          receiveUserId: this.receiveUserId,
          content: message,
          readed: false,
        };
        let socket = this.socket;
        if (socket.readyState === WebSocket.OPEN) {
          socket.send(JSON.stringify(chatLog));
          this.addMsg(message);
        } else {
          this.$message.error("连接建立失败");
          window.alert("连接建立失败");
          // MessageBox.alert("WebSocket 连接没有建立成功！");
        }
      },
      addMsg(msg){
        this.getCurrentTime();
        var msgObj;
        if (this.role===2){
          msgObj = {
            "date": this.currentTime,
            "text": { "text": msg },
            "mine": true,
            // "name": "",
            "img": "../../../static/img/volunteer.png"
          };
        }else{
          msgObj = {
            "date": this.currentTime,
            "text": { "text": msg },
            "mine": true,
            // "name": "",
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
            // this.list.push(msgObj)
          }else {
            this.$message.error(response.data.msg);
          }
        });
      },
      // 重新处理聊天记录， 主要是做特殊标记以及设置图像等操作
      reHandleChatLogs(receivedLogs) {
        if (!receivedLogs || receivedLogs.length < 1) {
          return new Array();
        }
        receivedLogs.forEach((element) => {
          this.getCurrentTime()
          element.date=this.currentTime;
          element.name = "";
          // 聊天内容（如下为设置文本，也可以设置其他video、图片等）
          element.text = new Object();
          element.text.text = element.content;
          console.log(element.userId==window.sessionStorage.getItem("id"))
          if (element.userId == window.sessionStorage.getItem("id")) {
            element.mine = true;
            element.img = "../../../static/img/volunteer.png";
          } else {
            element.mine = false;
            element.img = "../../../static/img/consultants.png";
          }
        });
        return receivedLogs;
      },
      bindEnter() {
        const msg = this.inputMsg;
        if (!msg) {
          // MessageBox.alert("您不能发送空消息");
          this.$message.error("消息发送失败，消息为空");
          return;
        }
        // 发送消息
        this.send(msg);
      },
      /**
       * @description:
       * @param {*} type 当前点击的按钮
       * @param {*} obj 附加文件或者需要处理的数据
       * @return {*}
       */
      toolEvent (type, obj) {
        console.log('tools', type, obj)
      },
      // /**
      //  * @description: 点击加载更多的回调函数
      //  * @param {*}
      //  * @return {*}
      //  */
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
      bindCover(type) {
        console.log("header", type);
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
      getByCId(){
        this.$http.get("/user/getByCId",{
          params:{
            id:window.sessionStorage.getItem("cid")
          }
        }).then(response => {
          if (response.data.errorCode===0){
            if(this.role===2){
              this.receiveUserId = response.data.data.uid;
            }else{
              this.receiveUserId = response.data.data.vid;
            }
          }else {
            this.$message.error(response.data.msg);
          }
        });
      },
      getAllByCid(){
        var cid = window.sessionStorage.getItem("cid");
        this.$http.get("/msg/getAllByCid",{
          params:{
            cid:cid,
            userId:window.sessionStorage.getItem("id")
          }
        }).then(response => {
          if (response.data.errorCode===0){
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
  };
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
