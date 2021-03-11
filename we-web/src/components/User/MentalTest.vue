<template>
  <!-- view -->
  <div id="app">
    <!-- 答题区域 -->
    <div v-if="!isFinish" class="container ">
      <!-- 进度条 -->
      <div class="progress mb-3">
        <div class="progress-bar progress-bar-striped progress-bar-animated bg-primary" role="progressbar"
             :style="progressStyle" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">{{progressBar}}
        </div>
      </div>
      <div class="jumbotron">

        <h1 class="display-4"><span class="badge badge-dark mr-3">{{question.id}}</span>{{question.title}}</h1>

        <hr class="my-2">
        <!-- 选项 -->
        <div v-for="(item, i) in question.items" :key="i" class="form-check my-3">
          <label class="form-check-label">
            <input @change="setAnswer(question.id)" v-model="answer" type="radio" class="form-check-input"
                   name="item" id="" value="checkedValue" :value="itemIndexs[i]">
            <span class="badge badge-pill badge-danger mr-2">{{itemIndexs[i]}}</span>
            {{item}}
          </label>
        </div>

        <!-- 按钮 -->
        <!-- h行 -->
        <div class="row">
          <div class="col-lg-6 col-sm-12">
            <button v-if="progress==0" disabled type="button" class="btn btn-danger btn-block disabled">上一题
            </button>
            <button v-else @click="prev()" type="button" class="btn btn-danger btn-block">上一题</button>
          </div>
          <div class="col-lg-6 col-sm-12">
            <button v-if="isLast" @click="submit()" type="button" class="btn btn-primary btn-block mt-sm-3 mt-lg-0 ">提交</button>
            <button v-else @click="next()" type="button" class="btn btn-success btn-block mt-sm-3 mt-lg-0">下一题</button>
          </div>
        </div>
      </div>
      <!-- 题目的按钮 -->
      <span v-for="(item, index) in questionList" :key="index">
               <button @click="skip(index)" v-if="answers.has(index+1)" type="button" class="btn btn-primary">{{index+1}}</button>
               <button @click="skip(index)" v-else type="button" class="btn btn-danger">{{index+1}}</button>
      </span>
    </div>
    <!-- 显示答案 -->
    <div v-else class="container">
      <div class="jumbotron">
<!--        <h1 class="display-3 text-center">答案与成绩</h1>-->
        <hr class="my-2">
        <table class="table table-bordered table-striped">
<!--          <thead class="thead-dark text-center">-->
<!--          <tr>-->
<!--            <th>序号</th>-->
<!--            <th>答案</th>-->
<!--            <th>正确答案</th>-->
<!--            <th>得分</th>-->
<!--          </tr>-->
<!--          </thead>-->
          <tbody>
<!--          <tr  v-for="([k,v], index) in answers" :key="index">-->
<!--            <td>{{k}}</td>-->
<!--            <td><span v-if="v===correct(k)" class="badge badge-pill badge-danger">{{v}}</span>-->
<!--              <span v-else class="badge badge-pill badge-primary">{{v}}</span>-->
<!--            </td>-->
<!--            &lt;!&ndash; 根据id获取正确答案 &ndash;&gt;-->
<!--            <td><span class="badge badge-pill badge-danger">{{correct(k)}}</span></td>-->

<!--            &lt;!&ndash; 根据索引获取正确答案 &ndash;&gt;-->
<!--            &lt;!&ndash; <td><span class="badge badge-pill badge-danger">{{questionList[index].answer}}</span></td> &ndash;&gt;-->
<!--            <td>-->
<!--              {{v===correct(k)?20:0}}-->
<!--            </td>-->
<!--          </tr>-->

          <!-- 总计 -->
            <tr class="text-center h3 ">
              <td colspan="3">总成绩</td>
              <td > {{score}}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>

<!--  <div class="xxxx">-->
<!--    <test :list_a="list_a" @transfer = 'postAnswer'/>-->
<!--    &lt;!&ndash;<button @click="buttonFun">提交</button>&ndash;&gt;-->
<!--  </div>-->
</template>

<script>
    // import test from '../../components/User/Other';
    export default {
      name: "MentalTest",
      // components: {
      //   test
      // },
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

</style>








