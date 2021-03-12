<template>
<!--  <div class="aaaa">-->
<!--    <div class="div" v-for="(son,index) in list_a" :key="index">-->
<!--      <div class="question">问题:{{son.question }}</div>-->
<!--      <div class="type">类型：{{son.type=== 1 ? '单选' : '多选' }}</div>-->
<!--      <div v-if="son.type === 1" class="answer">-->
<!--        <li v-for="(sson,index1) in son.answer" :key="index1" >-->
<!--          <span>{{sson.value}}</span>-->
<!--          <input type="radio" :name="son.name"  :value="sson.value" @change="get_radio_value(index)" v-model="checkedValue[index]" >-->
<!--        </li>-->
<!--        <div style="clear: both"></div>-->
<!--      </div>-->
<!--      <div v-else class="answer">-->
<!--        <li v-for="(sson,index1) in son.answer" :key="index1">-->
<!--          <span>{{sson.value}}</span>-->
<!--          <input type="checkbox" :name="son.name" :value="sson.value" @change="get_checkbox_value(index)" v-model="checkedValue1" >-->
<!--        </li>-->
<!--      </div>-->
<!--      <hr>-->
<!--    </div>-->
<!--    <button @click="btnfun">提交</button>-->
<!--  </div>-->



  <main class="ui-page-body">
    <div class="ui-page-container" style="max-width: 1000px;">
      <div class="simple-answer-card">
        <ul class="answer-list">
          <li class="item">
            <button class="ui-raised-button round-btn unknown current" type="button" tabindex="0" style="user-select: none; outline: none; cursor: pointer; appearance: none;">
              <div class="ui-raised-button-wrapper">
<!--                <div class="mu-ripple-wrapper"></div>-->
                <span v-for="(item, index) in questionList" :key="index">
                 <button @click="skip(index)" v-if="answers.has(index+1)" type="button" class="btn btn-primary">{{index+1}}</button>
                 <button @click="skip(index)" v-else type="button" class="btn btn-danger">{{index+1}}</button>
                </span>
<!--                <span class="ui-raised-button-label">1</span>-->
              </div>
            </button>
          </li>
        </ul>
      </div>
      <div class="exam-box">
        <h3 class="title">所有的苹果都是水果</h3>
        <ul class="options">
          <li class="item">正确</li>
          <li class="item">错误</li>
        </ul>
        <ul class="options"></ul>
        <div class="op">
          <button class="ui-raised-button btn disabled" disabled="" type="button" style="outline: none; appearance: none;">
            <div class="ui-raised-button-wrapper">
              <span class="ui-raised-button-label">上一题</span>
            </div>
          </button>
          <button class="ui-raised-button btn ui-raised-button-primary ui-raised-button-inverse" type="button" tabindex="0" style="user-select: none; outline: none; cursor: pointer; appearance: none;">
            <div class="ui-raised-button-wrapper">
              <div class="mu-ripple-wrapper"></div>
              <span class="ui-raised-button-label">下一题</span>
            </div>
          </button>
          <button class="ui-raised-button btn" type="button" tabindex="0" style="user-select: none; outline: none; cursor: pointer; appearance: none;">
            <div class="ui-raised-button-wrapper">
              <div class="mu-ripple-wrapper"></div>
              <span class="ui-raised-button-label">提交</span>
            </div>
          </button>
        </div>
      </div>
    </div>
  </main>

</template>

<script>
    export default {
      name: "Other",
      // data: function () {
      //   return {
      //     all_list: [],
      //     checkedValue: [], // 绑定单选框的值
      //     checkedValue1: [] // 绑定复选框的值
      //   }
      // },
      // props: ['list_a'],
      // methods: {
      //   btnfun: function () {
      //     // 获取input框的值
      //     console.log(this.all_list)
      //     // 如果答案长度不匹配list_a
      //     // this.all_list = this.all_list.null
      //     // console.log(this.all_list)
      //     for (var i = 0; i < this.all_list.length; i++) {
      //       if (this.all_list[i] === '' || typeof (this.all_list[i]) === 'undefined') {
      //         this.all_list.splice(i, 1)
      //       }
      //     }
      //     // 循环
      //     if (this.list_a.length !== this.all_list.length) {
      //       console.log('答案没有选择完毕')
      //     } else {
      //       console.log('答案选择完毕')
      //       // 传值给调用页面
      //       this.$emit('transfer', this.all_list)
      //     }
      //   },
      //   get_radio_value: function (index) {
      //     // 获取当前radio当前值
      //     console.log((index + 1) + '题' + this.checkedValue)
      //     this.all_list[index] = this.checkedValue[index]
      //   },
      //   get_checkbox_value: function (index) {
      //     // 获取当前复选框的值
      //     console.log((index + 1) + '题' + this.checkedValue1)
      //     this.all_list[index] = this.checkedValue1
      //   }
      // }



      data(){
        return{
          // list_a: [
          //   {'type': 1, 'name': 'one', 'question': 'Are you programmer?', 'answer': [{'value': 'A.Yes'}, {'value': 'B.No'}]},
          //   {'type': 1, 'name': 'two', 'question': 'Are you a man?', 'answer': [{'value': 'A.Of course'}, {'value': 'B.No'}]},
          //   {'type': 1, 'name': 'three', 'question': 'Are you a Staff?', 'answer': [{'value': 'A.Yes'}, {'value': 'B.No'}]},
          //   {'type': 1, 'name': 'four', 'question': 'Are you in sichuan?', 'answer': [{'value': 'A.Yes'}, {'value': 'B. No'}]},
          //   {'type': 2, 'name': 'five', 'question': 'How about your in skills?', 'answer': [{'value': 'A.Python'}, {'value': 'B.Vue'}, {'value': 'C.Php'}, {'value': 'D.Java'}]}
          // ]
          score:0,
          isFinish: false,
          progress: 0,
          size: 0,
          answer: '',
          answers: new Map(),
          itemIndexs: ['A', 'B', 'C', 'D', 'E', 'F'],
          questionList: [
            {
              id: 1,
              title: '最好的编程语言?',
              items: ['PHP', 'C', 'JS', 'Java', 'GO'],
              answer: 'A'
            },
            {
              id: 2,
              title: '最高级的数据库？',
              items: ['Redit', 'MySQL', 'postgreSQL', 'MongDB'],
              answer: 'C'
            },
            {
              id: 3,
              title: '开源数据是？',
              items: ['DB2', 'Oracle', 'SQL Server', 'MySQL'],
              answer: 'A'
            },
            {
              id: 4,
              title: '市值最高的公司？',
              items: ['微软', '亚马逊', '苹果', '中国建行'],
              answer: 'C'
            },
            {
              id: 5,
              title: '最好的前端框架？',
              items: ['Angular', 'Vue', 'React', 'Java'],
              answer: 'B'
            }
          ]
        }
      },
      methods: {
        // postAnswer: function (msg) {
        //   console.log(msg)
        // }

        submit:function(){
          // 答题完毕，提交答案
          this.isFinish=true;
          for (const q of this.questionList) {
            if(q.answer===this.answers.get(q.id)){
              this.score+=20;
            }
          }
        },
        skip:function(i){
          this.progress=i;
          this.answer='';
        },
        setAnswer: function (id) {
          //判断是否重复的一道题
          if (!this.answers.has(id)) {
            // 答题的数量
            this.size++;
          }
          this.answers.set(id, this.answer);
        },
        next: function () {
          this.progress++;
          if (this.answers.has(this.progress)) {
            this.answer = this.answers.get(this.progress + 1);
          } else {
            this.answer = '';
          }
        },
        prev: function () {
          //获得上一题的答案并且复位
          this.answer = this.answers.get(this.progress);
          this.progress--;
        }
      },
      computed: {
        // 根据id去获取题目的正确答案
        correct(){
          return function (id) {
            for(const i of this.questionList){
              if(i.id==id){
                return i.answer;
              }
            }
          }
        },
        // isFinish:function(){
        //     return this.size===this.questionList.length;
        // },
        question: function () {
          return this.questionList[this.progress];
        },
        isLast: function () {
          return this.progress == this.questionList.length - 1;
        },
        // progressStyle: function () {
        //     return `width:${this.progressChange}%;`;
        // },
        progressBar: function () {
          return Math.round(this.size / this.questionList.length * 100);
        },
        progressStyle: function () {
          return `width:${this.progressBar}%;`;
        }
      },
      mounted() {
      }
    }
</script>

<style scoped>
  /*li{*/
  /*  list-style: none;*/
  /*}*/
  /*.div{*/
  /*  margin: 6px 0px;*/
  /*}*/
  /*.question {*/
  /*  width:300px;*/
  /*  text-align: left;*/
  /*}*/
  /*.type{*/
  /*  width:200px;*/
  /*  text-align: left;*/
  /*}*/
  /*.answer li{*/
  /*  width:100%;*/
  /*  height: 20px;*/
  /*}*/
  /*.answer span{*/
  /*  float: left;*/
  /*}*/
  /*.answer input{*/
  /*  float: right;*/
  /*}*/


  .ui-page-body {
    position: absolute;
    top: 64px;
    left: 0;
    right: 0;
    bottom: 0;
    width: 100%;
    padding-top: 0;
    overflow: auto;
  }

  .ui-page-container {
    margin: 0 auto;
    padding: 16px;
  }

  /*.meta-list {*/
  /*  margin-bottom: 16px;*/
  /*  color: #999;*/
  /*}*/

  /*.meta-list .item {*/
  /*  float: left;*/
  /*  margin-right: 16px;*/
  /*}*/

  .mu-ripple-wrapper {
    height: 100%;
    width: 100%;
    position: absolute;
    top: 0;
    left: 0;
    overflow: hidden;
  }

  li, ol, ul {
    list-style: none;
  }

  h1, h2, h3, h4, h5, h6, li, ol, ul {
    padding: 0;
    margin: 0;
    font-size: inherit;
    font-weight: inherit;
  }

  li {
    display: list-item;
    text-align: -webkit-match-parent;
  }

  /*.meta-list:after {*/
  /*  clear: both;*/
  /*  content: ".";*/
  /*  display: block;*/
  /*  height: 0;*/
  /*  line-height: 0;*/
  /*  overflow: hidden;*/
  /*}*/

  .simple-answer-card {
    padding: 16px;
    margin-bottom: 16px;
    box-shadow: 0 1px 6px rgba(0,0,0,.117647), 0 1px 4px rgba(0,0,0,.117647);
  }

  /*.simple-answer-card .card-title {*/
  /*  font-size: 18px;*/
  /*  margin-bottom: 16px;*/
  /*}*/

  /*h2 {*/
  /*  display: block;*/
  /*  font-size: 1.5em;*/
  /*  margin-block-start: 0.83em;*/
  /*  margin-block-end: 0.83em;*/
  /*  margin-inline-start: 0px;*/
  /*  margin-inline-end: 0px;*/
  /*  font-weight: bold;*/
  /*}*/

  .simple-answer-card .answer-list .item {
    float: left;
    margin-right: 16px;
    margin-bottom: 16px;
  }

  .simple-answer-card .answer-list:after {
    clear: both;
    content: ".";
    display: block;
    height: 0;
    line-height: 0;
    overflow: hidden;
  }

  .exam-box {
    padding: 16px;
    margin-bottom: 24px;
    box-shadow: 0 1px 6px rgba(0,0,0,.117647), 0 1px 4px rgba(0,0,0,.117647);
  }

  .exam-box .index, .exam-box .title, .exam-box .type {
    margin-bottom: 16px;
  }

  .exam-box .title {
    font-size: 30px;
  }

  h3 {
    display: block;
    font-size: 1.17em;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    font-weight: bold;
  }

  .exam-box .options .item {
    padding: 16px;
    margin-bottom: 4px;
    cursor: pointer;
    border: 1px solid #f1f1f1;
  }

  .exam-box .op .btn {
    margin-right: 8px;
  }

  .ui-raised-button.disabled {
    color: rgba(0,0,0,.3);
    cursor: not-allowed;
    background-color: #e6e6e6;
    box-shadow: none;
  }

  .ui-raised-button {
    display: inline-block;
    overflow: hidden;
    position: relative;
    border-radius: 2px;
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
    margin: 0;
    outline: 0;
    padding: 0;
    cursor: pointer;
    box-shadow: 0 1px 6px rgba(0,0,0,.117647), 0 1px 4px rgba(0,0,0,.117647);
  }
</style>
