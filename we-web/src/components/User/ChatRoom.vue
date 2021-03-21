<template>
  <div class="chat">
<!--    v-model		输入框中的文字-->
<!--    taleList	要渲染的数据-->
<!--    toolConfig 工具栏配置	-->
<!--    width		  聊天框宽度	-->
<!--    height		聊天框高度-->
<!--    config		组件配置项	-->
<!--    scrollType 消息自动到低	-->
<!--    quickList	自动匹配快捷回复	-->
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
        ];
        this.list = list
      }
    }
</script>

<style scoped>
  .chat{
    margin-top: 5%;
  }
</style>
