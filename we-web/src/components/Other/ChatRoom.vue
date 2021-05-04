<template>
  <div class="dashboard-container">
    <div class="dashboard-text">
      <el-row>
        <el-col :span="6">
          <div class="grid-content bg-purple">
          <JwChat-rightbox :config="onlineUsers" @click="rightClick" /></div>
        </el-col>

        <!-- 如果选择了在线用户显示聊天窗口 -->
        <el-col :span="18" v-if="chatUserConfig.name != ''">
          <div class="grid-content bg-purple-light">
          <JwChat-index
            :config="chatUserConfig"
            :showRightBox="true"
            :taleList="chatlogTaleList"
            @enter="bindEnter"
            v-model="inputMsg"
            :toolConfig="toolConfig"
            scrollType="scroll"
            @clickTalk="clickTalk">
            <!-- 右边插槽 -->
            <template>
              <h3>聊天愉快</h3>
            </template>
          </JwChat-index>
        </div></el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
  // other.png 表示对方头像; myself.png 表示我自己
  import { MessageBox } from "element-ui";
  import {findCurrentUsername} from "@/utils/auth"

  export default {
    data() {
      return {
        // 在线用户相关信息
        onlineUsers: {
          tip: "选择在线人开始聊天",
          listTip: "当前在线",
          list: [],
        },

        // 输入框内默认的消息
        inputMsg: "",
        // 聊天记录
        chatlogTaleList: [
          // {
          //   date: "2020/04/25 21:19:07",
          //   text: { text: "起床不" },
          //   mine: false,
          //   name: "留恋人间不羡仙",
          //   img: "/images/other.png",
          // }
        ],
        // 展示的工具栏配置
        toolConfig: {
          // show: ['file', 'history', 'img', ['文件1', '', '美图']],
          show: null, // 关闭所有其他组件
          showEmoji: true,
          callback: this.toolEvent,
        },

        // 正在聊天的用户的信息
        chatUserConfig: {
          img: "/images/other.png",
          name: "",
          username: "",
          fullname: "",
          dept: "大部门",
          callback: this.bindCover,
          historyConfig: {
            show: true,
            tip: "加载更多",
            callback: this.bindLoadHistory,
          },
        },

        // 当前用户信息
        currentUser: {
          username: "",
          fullname: "",
        },

        socket: new Object(),
      };
    },

    created() {
      this.listOnlineUsers();
      this.findCurrentUserInfo();
      this.webSocket();
    },

    methods: {
      webSocket() {
        // 先记录this对象
        const that = this;
        if (typeof WebSocket == "undefined") {
          MessageBox.alert("浏览器暂不支持聊天", "提示信息");
        } else {
          // 实例化socket，这里我把用户名传给了后台，使后台能判断要把消息发给哪个用户，其实也可以后台直接获取用户IP来判断并推送
          const socketUrl = "ws://127.0.0.1:8091/ws/" + findCurrentUsername();
          this.socket = new WebSocket(socketUrl);
          // 监听socket打开
          this.socket.onopen = function () {
            console.log("浏览器WebSocket已打开");
          };
          // 监听socket消息接收
          this.socket.onmessage = function (messageEvent) {
            // 转换为json对象然后添加到chatlogTaleList
            let receivedLog = JSON.parse(messageEvent.data);
            console.log(receivedLog);
            let receivedLogs = new Array();
            receivedLogs.push(receivedLog);
            receivedLogs = that.rehandleChatLogs(receivedLogs);
            if (!that.chatlogTaleList) {
              that.chatlogTaleList = new Array();
            }
            that.chatlogTaleList = that.chatlogTaleList.concat(receivedLogs);
          };
          // 监听socket错误
          this.socket.onerror = function () {};
          // 监听socket关闭
          this.socket.onclose = function () {
            MessageBox.alert("WebSocket已关闭");
          };
        }
      },
      // 查询当前用户信息
      findCurrentUserInfo() {
        let url = "/user/getInfo";
        this.$http.post(url).then((res) => {
          this.currentUser = res.data;
        });
      },
      // 发送websocket 消息
      send(message) {
        if (!window.WebSocket) {
          return;
        }

        // 封装消息，然后发送消息
        const chatLog = {
          sendUsername: this.currentUser.username,
          sendFullname: this.currentUser.fullname,
          receiveUsername: this.chatUserConfig.username,
          receiveFullname: this.chatUserConfig.fullname,
          content: message,
          readed: false,
        };
        let socket = this.socket;
        if (socket.readyState == WebSocket.OPEN) {
          socket.send(JSON.stringify(chatLog));
        } else {
          MessageBox.alert("WebSocket 连接没有建立成功！");
        }
      },

      // 获取在线用户(有在线用户的情况下赋值到右边窗口)
      listOnlineUsers() {
        let url = "/user/listOnlineUser";
        this.$http.get(url).then((res) => {
          var onlineUsers = res.data;
          if (!onlineUsers || onlineUsers.length < 1) {
            return;
          }

          onlineUsers.forEach((element) => {
            element.name = element.username;
            element.img = "/images/cover.png";
          });
          this.onlineUsers.list = onlineUsers;
        });
      },
      // 点击在线人事件
      rightClick(type) {
        // 1.赋值给聊天人信息
        let chatUser = type.value;
        this.chatUserConfig.name = chatUser.fullname;
        this.chatUserConfig.username = chatUser.username;
        this.chatUserConfig.fullname = chatUser.fullname;
        // 2. 查询聊天记录
        let listChatlogurl = "/chat/log/list";
        let requestVO = {
          sendUsername: this.currentUser.username,
          receiveUsername: this.chatUserConfig.username,
          queryChangeRole: true,
        };
        this.$http.post(listChatlogurl, requestVO).then((res) => {
          this.chatlogTaleList = this.rehandleChatLogs(res.data);
        });
      },
      // 重新处理聊天记录， 主要是做特殊标记以及设置图像等操作
      rehandleChatLogs(chatlogs) {
        if (!chatlogs || chatlogs.length < 1) {
          return new Array();
        }

        chatlogs.forEach((element) => {
          element.date = element.createtimeStr;
          element.name = element.sendFullname;
          // 聊天内容（如下为设置文本，也可以设置其他video、图片等）
          element.text = new Object();
          element.text.text = element.content;
          if (element.sendUsername == this.currentUser.username) {
            element.mine = true;
            element.img = "/images/myself.png";
          } else {
            element.mine = false;
            element.img = "/images/other.png";
          }
        });
        return chatlogs;
      },

      // 点击左上角用户名称事件
      clickTalk(obj) {
        console.log(obj);
      },
      // 点击发送或者回车事件
      bindEnter(obj) {
        const msg = this.inputMsg;
        if (!msg) {
          MessageBox.alert("您不能发送空消息");
          return;
        }
        // 发送消息
        this.send(msg);
      },
      /**
       * @description:
       * @param {*} type 当前点击的按钮
       * @param {*} plyload 附加文件或者需要处理的数据
       * @return {*}
       */
      toolEvent(type, plyload) {
        console.log("tools", type, plyload);
      },
      /**
       * @description: 点击加载更多的回调函数
       * @param {*}
       * @return {*}
       */
      bindLoadHistory() {
        const history = new Array(3).fill().map((i, j) => {
          return {
            date: "2020/05/20 23:19:07",
            text: { text: j + new Date() },
            mine: false,
            name: "JwChat",
            img: "image/three.jpeg",
          };
        });
        let list = history.concat(this.list);
        this.list = list;
      },
      bindCover(type) {
        console.log("header", type);
      },
    },
  };
</script>
