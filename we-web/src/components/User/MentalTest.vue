<template>

<!--  考虑：-->
<!--    每题的每个答案的分数值-->
<!--    最终的答题情况形成文件发给咨询师-->
<!--    匹配咨询师，跳转聊天页面-->
<!--    咨询结束，用户给出评分，咨询师给出结论-->
<!--    -->
<!--    咨询师的总评分算法-->

  <main class="ui-page-body">
    <div v-if="!isFinish" class="ui-page-container" style="max-width: 1000px;">
      <div class="simple-answer-card">
        <ul class="answer-list">
          <span v-for="(item, index) in questionList" :key="index">
            <li class="item">
              <button @click="skip(index)" v-if="answers.has(index+1)" type="button" class="ui-raised-button1" style="user-select: none; outline: none; cursor: pointer; appearance: none;">
                {{index+1}}
              </button>
              <button @click="skip(index)" v-else type="button" class="ui-raised-button" style="user-select: none; outline: none; cursor: pointer; appearance: none;">
                {{index+1}}
              </button>
            </li>
          </span>
        </ul>
      </div>
      <router-link v-if="checkingResult" target="_blank" class="testTips" :to="{path:'/ChatRoom'}">{{tips}}</router-link>
      <div class="exam-box">
        <h3 class="title">
<!--          <span class="badge badge-dark">{{question.qid}}. </span>-->
          {{question.title}}
        </h3>

        <hr class="my-2">
        <!-- 选项 -->
        <ul class="options">
          <div v-for="(item, i) in question.items" :key="i" class="form-check">
            <li class="item">
              <label class="form-check-label">
                <input @change="setAnswer(question.id)" v-model="answer" type="radio" class="form-check-input"
                       name="item" id="" value="checkedValue" :value="itemIndexes[i]">
                <!--              <span class="badge badge-pill badge-danger mr-2">{{itemIndexs[i]}}</span>-->
                {{item}}
              </label>
            </li>
          </div>
        </ul>

        <div class="op">
          <button v-if="progress===0" disabled type="button" class="ui-raised-button btn btn-danger btn-block disabled">上一题</button>
          <button v-else @click="prev()" type="button" class="ui-raised-button btn btn-danger btn-block">上一题</button>
          <button v-if="isLast" @click="submit()" type="button" class="ui-raised-button btn btn-primary btn-block">提交</button>
          <button v-else @click="next()" type="button" class="ui-raised-button btn btn-success btn-block">下一题</button>
        </div>

      </div>
    </div>

    <!-- 显示答案 -->
    <div v-else class="container">
<!--      <div class="jumbotron">-->
<!--        <table class="table table-bordered table-striped">-->
<!--          <tbody>-->
<!--          &lt;!&ndash; 总计 &ndash;&gt;-->
<!--          <tr class="text-center h3 ">-->
<!--            <td colspan="3">总成绩</td>-->
<!--            <td > {{score}}</td>-->
<!--          </tr>-->
<!--          </tbody>-->
<!--        </table>-->
<!--      </div>-->
    </div>
  </main>
</template>

<script>
  export default {
    name: "MentalTest",
    data(){
      return{
        tips:"检测到您曾做过基础测试, 现在您可以直接点击进入咨询室>>>",
        checkingResult:false,
        score:0,
        isFinish: false,
        progress: 0,
        size: 0,
        answer: '',
        answers: new Map(),
        itemIndexes: ['A', 'B', 'C', 'D', 'E', 'F'],
        questionList: [
          {
            id: '',
            qid:'',
            title: '',
            items: [],
            answer: ''
          }
        ],
      }
    },
    methods: {
      checking(){
        this.$http.get("/test/getByUid",{
          params:{
            uid:window.sessionStorage.getItem("id"),
          }
        }).then(response => {
          if (response.data.errorCode===0){
            window.sessionStorage.setItem("cid",response.data.data.cid);
            this.checkingResult=true;
          }else {
            this.checkingResult=false;
          }
        });
      },
      submit(){
        // 答题完毕，提交答案
        var check =0;
        var score = 0;
        var qIds = "";
        var item = "";
        for (const q of this.questionList) {
          if(q.answer===this.answers.get(q.id)){
            score+=10;
          }
          if(this.answers.get(q.id)===undefined){
            check++;
          }
          qIds += q.qid+",";
          item += this.answers.get(q.id)+",";
        }
        if(check===10){
          this.$message.error('为了更好地为您开启心灵之旅，请认真对待人生的每一次测试');
        }else {
          // this.isFinish=true;
          this.$http.post("/test/addConsultants",{
            uid:window.sessionStorage.getItem("id")
          }).then(response => {
            if (response.data.errorCode===0){
              var cid = response.data.data;
              var testObj = {
                "uid": window.sessionStorage.getItem("id"),
                "cid": cid,
                "qIds": qIds,
                "item": item,
                "result": score
              };
              this.$http.post("/test/addTest",{
                testObj:testObj
              }).then(response => {
                if (response.data.errorCode===1){
                  //导出文件？保存msg，文件？
                  window.sessionStorage.setItem("cid",cid);
                  this.$message.success('已为您成功匹配咨询师');
                  window.open("/#/ChatRoom","_blank");
                }else {
                  this.$message.error(response.data.msg);
                }
              });
            }else {
              this.$message.error(response.data.msg);
            }
          });
        }
      },
      getRand(){
        this.$http.get("/test/getRand").then(response => {
          if (response.data.errorCode===0){
            this.questionList = response.data.data;
            console.log(this.questionList)
          }else {
            this.$message.error(response.data.msg);
          }
        });
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
            if(i.id===id){
              return i.answer;
            }
          }
        }
      },
      isFinish:function(){
          return this.size===this.questionList.length;
      },
      question: function () {
        return this.questionList[this.progress];
      },
      isLast: function () {
        return this.progress === this.questionList.length - 1;
      },
      progressBar: function () {
        return Math.round(this.size / this.questionList.length * 100);
      },
      progressStyle: function () {
        return `width:${this.progressBar}%;`;
      },

    },
    mounted() {
      if(window.sessionStorage.getItem("id")===null){
        this.$message.error("未登录");
      }else {
        this.getRand();
        this.checking();
      }
    }
  }
</script>

<style scoped>
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

  .testTips{
    font-size: 15px;
    margin-bottom: 10px;
  }

  .testTips:hover{
    cursor: pointer;
  }

  .submit{
    position: relative;
    top: 14px;
    font-size: 15px;
  }

  .op{
    margin-top: 25px;
  }

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

  .simple-answer-card {
    padding: 16px;
    margin-bottom: 16px;
    box-shadow: 0 1px 6px rgba(0,0,0,.117647), 0 1px 4px rgba(0,0,0,.117647);
  }

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

  .options{
    margin-top: 20px;
  }

  .exam-box .options .item {
    padding: 16px;
    margin-bottom: 5px;
    cursor: pointer;
    border: 1px solid #f1f1f1;
  }

  .exam-box .op .btn {
    margin-right: 8px;
    margin-top: 30px;
    margin-bottom: 20px;
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

  .ui-raised-button1 {
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
    background-color: deepskyblue;
    color: white;
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
