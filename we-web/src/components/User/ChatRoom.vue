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
            img: '../image/cover.png',
            name: 'JwChat',
            dept: '最简单、最便捷',
            callback: this.bindCover,
            historyConfig:{
              show: true,
              tip: '加载更多',
              callback: this.bindLoadHistory,
            },

          },
        };
      },
      methods: {
        bindEnter () {
          const msg = this.inputMsg;
          if (!msg) return;
          const msgObj = {
            "date": "2020/05/10 23:19:07",
            "text": { "text": msg },
            "mine": true,
            "name": "JwChat",
            "img": "../../assets/logo/we_logo.png"
          };
          this.list.push(msgObj)
        },
        toolEvent (type, obj) {
          console.log('tools', type, obj)
        },
        bindLoadHistory () {
          const history = new Array(3).fill().map((i, j) => {
            return {
              "date": "2020/05/20 23:19:07",
              "text": { "text": j + new Date() },
              "mine": false,
              "name": "JwChat",
              "img": "image/three.jpeg"
            }
          });
          let list = history.concat(this.list);
          this.list = list;
          console.log('加载历史', list, history);
        },
        bindCover (type) {
          console.log('header', type);
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
        ];
        this.list = list
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
